package lv.javaguru.java2.qwe.core.domain;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void shouldReturnCorrectRiskTolerance1() {
        User user = new User(1, "Marina", 42, Type.WEALTHY, 500_000);
        Assert.assertEquals(user.getRiskTolerance(), 3);
    }

    @Test
    public void shouldReturnCorrectRiskTolerance2() {
        User user = new User(1,"Alexander", 25, Type.SUPER_RICH, 1_000_000);
        Assert.assertEquals(user.getRiskTolerance(), 5);
    }

    @Test
    public void shouldReturnCorrectRiskTolerance3() {
        User user = new User(1,"Vladimir", 60, Type.LOWER_MIDDLE, 30_000);
        Assert.assertEquals(user.getRiskTolerance(), 1);
    }

    @Test
    public void shouldReturnCorrectRiskTolerance4() {
        User user = new User(1,"John", 75, Type.SUPER_RICH, 30_000);
        Assert.assertEquals(user.getRiskTolerance(), 3);
    }

    @Test
    public void shouldReturnCorrectRiskTolerance5() {
        User user = new User(1,"Janis", 22, Type.LOWER_MIDDLE, 15_000);
        Assert.assertEquals(user.getRiskTolerance(), 3);
    }

    @Test
    public void shouldReturnCorrectRiskTolerance6() {
        User user = new User(1,"Sergey", 39, Type.WEALTHY, 15_000);
        Assert.assertEquals(user.getRiskTolerance(), 4);
    }

}