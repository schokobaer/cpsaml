package «pkg».cockpit.model.monitor.predefined;

public class DataType {
    private PrimitiveType type;
    private boolean list;

    public DataType() { }

    public DataType(PrimitiveType type) {
        this.type = type;
        list = false;
    }

    public DataType(PrimitiveType type, boolean list) {
        this.type = type;
        this.list = list;
    }

    public PrimitiveType getType() {
        return type;
    }

    public boolean isList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataType dataType = (DataType) o;

        if (list != dataType.list) return false;
        return type == dataType.type;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (list ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        if (list) {
            return "List<" + type.name() + ">";
        }
        return type.name();
    }
}
