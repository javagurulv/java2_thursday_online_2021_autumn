package lv.javaguru.java2.hospital.doctor.core.responses;

public class CoreError {

    private final String field;
    private final String message;

    public CoreError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
