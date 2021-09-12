package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors;

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
}
