package «pkg».cockpit.util;

import «pkg».cockpit.annotation.CpsDevice;
import «pkg».cockpit.model.monitor.Cps;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public final class ReflectionUtils {
    private ReflectionUtils() { }

    public static Cps resolveCps(Object instance) {
        CpsDevice anno = instance.getClass().getAnnotation(CpsDevice.class);
        if (anno == null) {
            return null;
        }
        return anno.value();
    }

    public static String resolveId(Object instance) {
        Optional<Field> field = Arrays.stream(instance.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst();
        if (field.isPresent()) {
            field.get().setAccessible(true);
            try {
                return (String) field.get().get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
