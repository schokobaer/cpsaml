package at.ac.tuwien.big.cps.annotationprocessor.util;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class TypeMirrorUtils {

    public static Class<?> toClass(TypeMirror typeMirror) {
        // int -> ClassType extends DeclaredType
        // Integer -> PrimitiveType

        try {
            if (typeMirror instanceof DeclaredType) {
                DeclaredType declaredType = (DeclaredType) typeMirror;
                return Class.forName(declaredType.asElement().toString());
            }
        } catch (Throwable e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static boolean isApplicable(TypeMirror typeMirror, List<Class<?>> clazzes) {
        final String qualifiedName = typeMirror.toString();
        return clazzes.stream()
                .map(c -> c.getName())
                .filter(c -> c.equals(qualifiedName)).findAny().isPresent();
    }

    public static boolean isPrimitiveOrListOfPrimitives(TypeMirror typeMirror) {
        if (typeMirror instanceof DeclaredType) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            if (TypeMirrorUtils.isList(declaredType)) {
                if (declaredType.getTypeArguments().isEmpty()) {
                    return false;
                }
                return TypeMirrorUtils.isApplicable(
                        declaredType.getTypeArguments().get(0),
                        TypeMirrorUtils.PRIMITIVES
                );
            } else {
                return TypeMirrorUtils.isApplicable(declaredType, TypeMirrorUtils.PRIMITIVES);
            }
        } else {
            // is java primitive type
            return TypeMirrorUtils.isApplicable(typeMirror, TypeMirrorUtils.PRIMITIVES);
        }
    }

    public static boolean typeHasIdAnnotatedField(TypeMirror typeMirror) {
        if (typeMirror instanceof DeclaredType) {
            DeclaredType inputTypeDeclared = (DeclaredType) typeMirror;
            return inputTypeDeclared.asElement().getEnclosedElements().stream()
                    .filter(elem -> elem.getAnnotation(Id.class) != null)
                    .findAny().isPresent();
        }
        return false;
    }

    public static boolean isList(TypeMirror typeMirror) {
        Class<?> clazz = toClass(typeMirror);
        if (clazz == null) return true;
        return List.class.isAssignableFrom(clazz);
    }

    public static final List<Class<?>> NUMBERS = List.of(
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class,
            byte.class, Byte.class,
            short.class, Short.class
    );
    public static final List<Class<?>> PRIMITIVES = List.of(
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class,
            byte.class, Byte.class,
            boolean.class, Boolean.class,
            char.class, Character.class,
            String.class
    );
    public static final List<Class<?>> BOOLS = List.of(boolean.class, Boolean.class);


}
