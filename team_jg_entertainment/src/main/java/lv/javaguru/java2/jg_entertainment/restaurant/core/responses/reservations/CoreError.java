package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations;

import java.util.StringJoiner;

public class CoreError {

    private String field;
    private String messageError;

    public CoreError(String field, String messageError) {
        this.field = field;
        this.messageError = messageError;
    }

    public String getField() {
        return field;
    }

    public String getMessageError() {
        return messageError;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoreError.class.getSimpleName() + "[", "]")
                .add("field='" + field + "'")
                .add("messageError='" + messageError + "'")
                .toString();
    }
}
