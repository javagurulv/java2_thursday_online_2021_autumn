package lv.javaguru.java2.qwe.core.domain;

public class Cash extends Security {

    public Cash() {
        super("CASH", "Cash", "Cash", "USD", 1);
    }

    @Override
    public String toString() {
        return "Cash{" +
                "ticker=" + this.getTicker() +
                ", name=" + this.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

}