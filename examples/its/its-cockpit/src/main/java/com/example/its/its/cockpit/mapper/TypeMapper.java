package com.example.its.its.cockpit.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public class TypeMapper {

    private boolean bidirectional;
    private Map<Class, Class> mappings = new HashMap<>();

    public TypeMapper(boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

    public TypeMapper register(Class from, Class to) {
        mappings.put(from, to);
        if (bidirectional) {
            mappings.put(to, from);
        }
        return this;
    }

    public <T> T map(Object source) {
        Class from = source.getClass();
        Class<T> to = mappings.get(from);



        if (to == null) {
            return (T) source;
        }

        try {
            Object target = Arrays.stream(to.getDeclaredConstructors())
                    .filter(c -> c.getParameters().length == 0)
                    .findFirst()
                    .get()
                    .newInstance();
            Arrays.stream(to.getDeclaredFields()).forEach(field -> {
                try {
                    field.setAccessible(true);
                    Field sourceField = from.getDeclaredField(field.getName());
                    sourceField.setAccessible(true);
                    Object obj = sourceField.get(source);
                    if (mappings.containsKey(sourceField.getType())) {
                        obj = map(obj);
                    } else if (sourceField.getType().isEnum() && field.getType().equals(String.class)) {
                        obj = obj.toString();
                    } else if (field.getType().isEnum() && sourceField.getType().equals(String.class)) {
                        obj = Enum.valueOf((Class<Enum>) field.getType(), obj.toString());
                    } else if (List.class.isAssignableFrom(field.getType())) {
                        // List
                        List sourceList = (List) obj;
                        obj = sourceList.stream().map(e -> map(e)).collect(Collectors.toList());
                    }
                    field.set(target, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return (T) target;
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T map2(Object source) {
        Class from = source.getClass();
        Class<T> to = mappings.get(from);

        if (to == null) {
            throw new RuntimeException("Unregistered Type " + to.getName());
        }

        return map(source, to);
    }

    public <T> T map(Object source, Class<T> to) {
        if (source == null) {
            return null;
        }

        Class from = source.getClass();

        // SubType
        if (mappings.containsKey(from)) {
            to = mappings.get(from);
        }

        // Same type or primitive
        if (from.equals(to) || source.getClass().isPrimitive()) {
            return (T) source;
        }

        // Enum
        if (from.isEnum() && to.equals(String.class)) {
            return (T) source.toString();
        } else if (to.isEnum() && from.equals(String.class)) {
            return (T) Enum.valueOf((Class<Enum>) to, source.toString());
        }

        // List
        if (List.class.isAssignableFrom(from)) {
            List sourceList = (List) source;
            Class toClass = to;
            return (T) sourceList.stream()
                    .map(e -> map(e, toClass))
                    .collect(Collectors.toList());
        }

        // No constructor
        if (to.getDeclaredConstructors().length == 0) {
            return map2(source);
        }

        try {
            Object target = Arrays.stream(to.getDeclaredConstructors())
                    .filter(c -> c.getParameters().length == 0)
                    .findFirst()
                    .get()
                    .newInstance();
            Arrays.stream(to.getDeclaredFields()).forEach(field -> {
                try {
                    field.setAccessible(true);
                    Field sourceField = from.getDeclaredField(field.getName());
                    sourceField.setAccessible(true);
                    Object obj = sourceField.get(source);
                    field.set(target, map(obj, List.class.isAssignableFrom(field.getType()) ?
                                    (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] :
                                    field.getType()
                            ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return (T) target;
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
