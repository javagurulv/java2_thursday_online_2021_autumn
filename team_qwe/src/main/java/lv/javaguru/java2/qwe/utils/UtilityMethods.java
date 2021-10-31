package lv.javaguru.java2.qwe.utils;

import lv.javaguru.java2.qwe.ApplicationDemo;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;
import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

@Component
public class UtilityMethods {

    @Value("${importData.enabled}")
    private boolean importDataEnabled;

    @Value("${simulator.marketPrice.enabled}")
    private boolean marketPriceSimulatorEnabled;

    @Value("${simulator.marketPrice.initDelay}")
    private int marketPriceSimulatorInitDelay;

    @Value("${simulator.marketPrice.period}")
    private int marketPriceSimulatorPeriod;

    @Value("${simulator.date.enabled}")
    private boolean dateSimulatorEnabled;

    @Value("${simulator.date.initDelay}")
    private int dateSimulatorInitDelay;

    @Value("${simulator.date.period}")
    private int dateSimulatorPeriod;

    public String[] convertToStringArray(UserData userData) {
        return userData.getAllUserList().stream()
                .map(User::getName)
                .toArray(String[]::new);
    }

    public void setMarketPriceSimulator(ApplicationContext context) {
        if (marketPriceSimulatorEnabled && marketPriceSimulatorPeriod > 0) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            Database database = context.getBean(Database.class);
            Runnable simulator = () -> simulateMarketPrices(database.getAllSecurityList());
            scheduledExecutorService.scheduleAtFixedRate(
                    simulator, marketPriceSimulatorInitDelay, marketPriceSimulatorPeriod, TimeUnit.SECONDS
            );
        }
    }

/*    public void setDateSimulator(ApplicationContext context) {
        if (dateSimulatorEnabled && dateSimulatorPeriod > 0) {
            ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(1);
            UserData userData = context.getBean(UserData.class);
            Runnable simulator1 = () -> userData.setCurrentDate(userData.getCurrentDate().plusDays(1));
            scheduledExecutorService1.scheduleAtFixedRate(
                    simulator1, dateSimulatorInitDelay, dateSimulatorPeriod, TimeUnit.SECONDS);
        }
    }*/

    private void simulateMarketPrices(List<Security> list) {
        if (list.size() > 1) {
            IntStream.rangeClosed(0, list.size() - 1)
                    .filter(i -> !list.get(i).getClass().getSimpleName().equals("Cash"))
                    .forEach(i -> list.get(i).setMarketPrice(generateNextPrice(
                            list.get(i).getMarketPrice(), evenIsPositive(i)
                    )));
        }
    }

    public String inputDialog(String text) {
        return Optional.ofNullable(showInputDialog(null, text)).orElse("");
    }

    public String inputDialog(String request, String title, String[] arr) {
        return Optional.ofNullable((String) showInputDialog(
                null, request,
                title, JOptionPane.QUESTION_MESSAGE, null,
                arr, arr[0])).orElse("");
    }

    public String[] generateIndustriesArray() {
        return new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                "Industrials", "Consumer Discretionary"};
    }

    public String printErrorList(CoreResponse response) {
        return response.getErrors().stream()
                .map(error -> error.getField() + ": " + error.getMessage())
                .collect(Collectors.joining("\n"));
    }

    public boolean isNotDouble(String text) {
        try {
            Double.parseDouble(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isNotInteger(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isLong(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void messageDialog(String text) {
        showMessageDialog(null, text);
    }

    public int convertToInt(double amount) {
        return (int) amount;
    }

    public double round(double amount) {
        return Math.round(amount * 100.) / 100.;
    }

    private double generateNextPrice(double currentPrice, boolean positive) {
        return (positive) ? round(currentPrice * (1 + (Math.random()) / 100)) :
                round(currentPrice * (1 - (Math.random()) / 100));
    }

    private boolean evenIsPositive(int number) {
        return number % 2 == 0;
    }

    public void importData() {
        if (importDataEnabled) {
            File file = new File("./team_qwe/src/main/docs/stocks_list_import.txt");
            ImportSecuritiesService service =
                    ApplicationDemo.getApplicationContext().getBean(ImportSecuritiesService.class);
            try {
                service.execute(file.getPath());
                System.out.println("Data imported to database!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void importDataForTests(ApplicationContext context) {
        File file = new File("./src/test/docs/stocks_list_import.txt");
        ImportSecuritiesService service =
                context.getBean(ImportSecuritiesService.class);
        try {
            service.execute(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}