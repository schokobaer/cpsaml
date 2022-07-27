package «pkg».cockpit.model.monitor.predefined;

public enum PrimitiveType {
    INT, FLOAT, BOOL, STRING ;


    public boolean isNumber() {
        return this == INT || this == FLOAT;
    }

    public static PrimitiveType fromClass(Class<?> clazz) {
        if (clazz.equals(int.class) || clazz.equals(Integer.class)
                || clazz.equals(long.class) || clazz.equals(Long.class)
                || clazz.equals(byte.class) || clazz.equals(Byte.class)) {
            return INT;
        } else if (clazz.equals(float.class) || clazz.equals(Float.class)
                || clazz.equals(double.class) || clazz.equals(Double.class)) {
            return FLOAT;
        } else if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
            return BOOL;
        } else if (clazz.equals(String.class) || clazz.equals(char.class) || clazz.equals(Character.class)) {
            return STRING;
        }
        throw new IllegalArgumentException("Invalid type: " + clazz.getName());
    }
}
