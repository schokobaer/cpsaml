package com.example.its.its.cockpit.service;

import com.example.its.its.cockpit.model.monitor.ScalarFunction;
import com.example.its.its.cockpit.model.monitor.PathFieldMapping;
import com.example.its.its.cockpit.model.monitor.VectorFunction;
import com.example.its.its.cockpit.model.monitor.logic.*;
import com.example.its.its.cockpit.model.monitor.predefined.DataType;
import com.example.its.its.cockpit.model.monitor.predefined.PrimitiveType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.example.its.its.cockpit.util.Utils.runCatching;

@Service
public class LogicalEvaluator {

    /**
     * Resolves the path for a single PathFieldMapping. Use this method for an iterative navigation through an object.
     * @param instance
     * @param fieldMapping
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public Object resolvePathFieldMapping(Object instance, PathFieldMapping fieldMapping) throws NoSuchFieldException, IllegalAccessException {
        if (fieldMapping == null) {
            return instance;
        }
        Field f = instance.getClass().getDeclaredField(fieldMapping.getField());
        f.setAccessible(true);
        instance = f.get(instance);
        if (fieldMapping.getFunction() != null) {
            List list = (List) instance;
            if (fieldMapping.getFunction().isA()) {
                // Scalar function
                ScalarFunction function = fieldMapping.getFunction().getA();
                if (function == ScalarFunction.COUNT) {
                    instance = list.size();
                } else if (function == ScalarFunction.FIRST) {
                    instance = list.isEmpty() ? null : list.get(0);
                } else if (function == ScalarFunction.LAST) {
                    instance = list.isEmpty() ? null : list.get(list.size() - 1);
                } else if (function == ScalarFunction.EMPTY) {
                    instance = list.isEmpty();
                }
            }
        }
        return instance;
    }

    public Object resolvePathRec(Object instance, List<PathFieldMapping> path) throws NoSuchFieldException, IllegalAccessException {
        if (path.isEmpty()) {
            return instance;
        }
        if (List.class.isAssignableFrom(instance.getClass())) {
            throw new RuntimeException("List has a sub path, which is not allowed! " + path.toString());
        }

        PathFieldMapping fieldMapping = path.get(0);
        List<PathFieldMapping> subPath = path.stream().skip(1).collect(Collectors.toList());
        Field field = instance.getClass().getDeclaredField(fieldMapping.getField());
        field.setAccessible(true);
        Object ctx = field.get(instance);

        if (List.class.isAssignableFrom(ctx.getClass())) {
            List list = (List) ctx;

            if (fieldMapping.getFunction() == null) {
                return list.stream()
                        .map(elem -> runCatching(() -> resolvePathRec(elem, subPath)))
                        .collect(Collectors.toList());
            } else if (fieldMapping.getFunction().isB()) {
                throw new RuntimeException("VectorFunction not allowed in this place!" + fieldMapping.getFunction().getB());
            }

            // dead end functions (count|empty)
            if (fieldMapping.getFunction().getA() == ScalarFunction.COUNT) {
                return list.size();
            } else if (fieldMapping.getFunction().getA() == ScalarFunction.EMPTY) {
                return list.isEmpty();
            }

            // index functions (first|last)
            if (fieldMapping.getFunction().getA() == ScalarFunction.FIRST) {
                if (list.isEmpty()) {
                    return null;
                }
                ctx = list.get(0);
                return resolvePathRec(ctx, subPath);
            } else if (fieldMapping.getFunction().getA() == ScalarFunction.LAST) {
                if (list.isEmpty()) {
                    return null;
                }
                ctx = list.get(list.size() - 1);
                return resolvePathRec(ctx, subPath);
            }

            // aggregations with subpath
            if (list.isEmpty()) {
                return 0;
            }
            DoubleStream stream = list.stream()
                    .map(elem -> runCatching(() -> resolvePathRec(elem, subPath)))
                    .filter(res -> res != null)
                    .mapToDouble(x -> Double.parseDouble(x.toString()));
            if (fieldMapping.getFunction().getA() == ScalarFunction.AVG) {
                return stream.average().orElse(0);
            } else if (fieldMapping.getFunction().getA() == ScalarFunction.MIN) {
                return stream.min();
            } else if (fieldMapping.getFunction().getA() == ScalarFunction.MAX) {
                return stream.max();
            } else if (fieldMapping.getFunction().getA() == ScalarFunction.SUM) {
                return stream.sum();
            } else {
                throw new RuntimeException("Invalid ScalarFunction: " + fieldMapping.getFunction().getA());
            }
        }

        // scalar field -> no function available
        return resolvePathRec(ctx, subPath);
    }

    public boolean evaluate(Object instance, Logical logical) {
        return logical instanceof LogicalExpression ? evaluate(instance, (LogicalExpression) logical) :
                logical instanceof Comparison ? evaluate(instance, (Comparison) logical) : false;
    }

    private boolean evaluate(Object instance, LogicalExpression expression) {

        boolean result = false;
        if (expression.getOperator() == LogicalOperator.AND) {
            result = expression.getExpressions().stream().allMatch(e -> evaluate(instance, e));
        } else if (expression.getOperator() == LogicalOperator.OR) {
            result = expression.getExpressions().isEmpty() ||
                    expression.getExpressions().stream().anyMatch(e -> evaluate(instance, e));
        } else if (expression.getOperator() == LogicalOperator.IMPLIES) {
            result = expression.getExpressions().size() < 2 ||
                    !evaluate(instance, expression.getExpressions().get(0)) ||
                    evaluate(instance, expression.getExpressions().get(1));
        }

        if (expression.isNot()) {
            result = !result;
        }

        return result;
    }

    private boolean evaluate(Object instance, Comparison expression) {
        if (expression.getPath().isEmpty()) {
            // final comparison. only point of termination
            return compare(instance, expression.getComparator(), expression.getValue());
        }

        try {
            PathFieldMapping p = expression.getPath().get(0);
            List<PathFieldMapping> subPath = expression.getPath().stream()
                    .skip(1)
                    .collect(Collectors.toList());
            Comparison subComparison = new Comparison(
                    subPath,
                    expression.getComparator(),
                    expression.getValue()
            );

            if (p.getFunction() == null) {
                instance = resolvePathFieldMapping(instance, p);
                return evaluate(instance, subComparison);
            } else if (p.getFunction().isA()) {
                if (p.getFunction().getA().isIndexFunction() || p.getFunction().getA().isDeadEndAggregation()) {
                    instance = resolvePathFieldMapping(instance, p);
                    return evaluate(instance, subComparison);
                } else {
                    // SubAggregation function
                    instance = resolvePathRec(instance, expression.getPath());
                    return evaluate(instance, new Comparison(
                            List.of(),
                            expression.getComparator(),
                            expression.getValue()
                    ));
                }
            } else {
                // Vector function
                instance = resolvePathFieldMapping(instance, p);
                List list = (List) instance;
                VectorFunction function = p.getFunction().getB();
                if (function == VectorFunction.ALL) {
                    return list.stream().allMatch(e -> evaluate(e, subComparison));
                } else if (function == VectorFunction.EXISTS) {
                    return list.stream().anyMatch(e -> evaluate(e, subComparison));
                } else if (function == VectorFunction.NONE) {
                    return list.stream().noneMatch(e -> evaluate(e, subComparison));
                }
                throw new RuntimeException("Invalid VectorFunction: " + function);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean compare(Object instance, Comparator comparator, Object value) {
        if (instance.getClass().isEnum() && value instanceof String) {
            instance = ((Enum) instance).name();
        }
        if (comparator == Comparator.EQ) {
            if (value == null || instance == null) {
                return value == instance;
            }
            return value.equals(instance);
        } else if (comparator == Comparator.NEQ) {
            if (value == null || instance == null) {
                return value != instance;
            }
            return !value.equals(instance);
        } else if (comparator == Comparator.GRT) {
            if (value == null || instance == null) {
                return false;
            }
            if (isNumber(instance)) {
                return Double.compare(Double.parseDouble(instance.toString()), Double.parseDouble(value.toString())) > 0;
            }
            return ((Comparable) instance).compareTo(value) > 0;
        } else if (comparator == Comparator.GRE) {
            if (value == null || instance == null) {
                return false;
            }
            if (isNumber(instance)) {
                return Double.compare(Double.parseDouble(instance.toString()), Double.parseDouble(value.toString())) >= 0;
            }
            return ((Comparable) instance).compareTo(value) >= 0;
        } else if (comparator == Comparator.SMT) {
            if (value == null || instance == null) {
                return false;
            }
            if (isNumber(instance)) {
                return Double.compare(Double.parseDouble(instance.toString()), Double.parseDouble(value.toString())) < 0;
            }
            return ((Comparable) instance).compareTo(value) < 0;
        } else if (comparator == Comparator.SME) {
            if (value == null || instance == null) {
                return false;
            }
            if (isNumber(instance)) {
                return Double.compare(Double.parseDouble(instance.toString()), Double.parseDouble(value.toString())) <= 0;
            }
            return ((Comparable) instance).compareTo(value) <= 0;
        }
        return false;
    }

    private boolean isNumber(Object instance) {
        return PrimitiveType.fromClass(instance.getClass()).isNumber();
    }
}
