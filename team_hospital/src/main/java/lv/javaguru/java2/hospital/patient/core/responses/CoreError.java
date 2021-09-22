package lv.javaguru.java2.hospital.patient.core.responses;

public class CoreError {
    private String field;
    private String description;

    public CoreError(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "field='" + field + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
