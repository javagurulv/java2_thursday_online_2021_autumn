package lv.javaguru.java2.qwe;

class Cash extends Security {

    public Cash() {
        super("Cash", "Cash", "USD", 1);
    }

    @Override
    public String toString() {
        return "Cash{" +
                "name=" + this.getName() +
                '}';
    }





}