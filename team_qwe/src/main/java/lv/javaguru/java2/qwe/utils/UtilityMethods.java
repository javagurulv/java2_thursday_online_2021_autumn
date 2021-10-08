package lv.javaguru.java2.qwe.utils;

import lv.javaguru.java2.qwe.ApplicationDemo;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.User;
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

    private static boolean importDataEnabled;
    private static boolean marketPriceSimulatorEnabled;
    private static int marketPriceSimulatorInitDelay;
    private static int marketPriceSimulatorPeriod;
    private static boolean dateSimulatorEnabled;
    private static int dateSimulatorInitDelay;
    private static int dateSimulatorPeriod;

    @Value("${importData.enabled}")
    public void setImportDataEnabled(boolean importDataEnabled) {
        UtilityMethods.importDataEnabled = importDataEnabled;
    }

    @Value("${simulator.marketPrice.enabled}")
    public void setMarketPriceSimulatorEnabled(boolean marketPriceSimulatorEnabled) {
        UtilityMethods.marketPriceSimulatorEnabled = marketPriceSimulatorEnabled;
    }

    @Value("${simulator.marketPrice.initDelay}")
    public void setMarketPriceSimulatorInitDelay(int marketPriceSimulatorInitDelay) {
        UtilityMethods.marketPriceSimulatorInitDelay = marketPriceSimulatorInitDelay;
    }

    @Value("${simulator.marketPrice.period}")
    public void setMarketPriceSimulatorPeriod(int marketPriceSimulatorPeriod) {
        UtilityMethods.marketPriceSimulatorPeriod = marketPriceSimulatorPeriod;
    }

    @Value("${simulator.date.enabled}")
    public void setDateSimulatorEnabled(boolean dateSimulatorEnabled) {
        UtilityMethods.dateSimulatorEnabled = dateSimulatorEnabled;
    }

    @Value("${simulator.date.initDelay}")
    public void setDateSimulatorInitDelay(int dateSimulatorInitDelay) {
        UtilityMethods.dateSimulatorInitDelay = dateSimulatorInitDelay;
    }

    @Value("${simulator.date.period}")
    public void setDateSimulatorPeriod(int dateSimulatorPeriod) {
        UtilityMethods.dateSimulatorPeriod = dateSimulatorPeriod;
    }

    public static String[] convertToStringArray(UserData userData) {
        return userData.getUserList().stream()
                .map(User::getName)
                .toArray(String[]::new);
    }

    public static void setMarketPriceSimulator(ApplicationContext context) {
        if (marketPriceSimulatorEnabled && marketPriceSimulatorPeriod > 0) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            Database database = context.getBean(Database.class);
            Runnable simulator = () -> simulateMarketPrices(database.getSecurityList());
            scheduledExecutorService.scheduleAtFixedRate(
                    simulator, marketPriceSimulatorInitDelay, marketPriceSimulatorPeriod, TimeUnit.SECONDS
            );
        }
    }

    public static void setDateSimulator(ApplicationContext context) {
        if (dateSimulatorEnabled && dateSimulatorPeriod > 0) {
            ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(1);
            UserData userData = context.getBean(UserData.class);
            Runnable simulator1 = () -> userData.setCurrentDate(userData.getCurrentDate().plusDays(1));
            scheduledExecutorService1.scheduleAtFixedRate(
                    simulator1, dateSimulatorInitDelay, dateSimulatorPeriod, TimeUnit.SECONDS);
        }
    }

    private static void simulateMarketPrices(List<Security> list) {
        if (list.size() > 1) {
            IntStream.rangeClosed(0, list.size() - 1)
                    .filter(i -> !list.get(i).getClass().getSimpleName().equals("Cash"))
                    .forEach(i -> list.get(i).setMarketPrice(generateNextPrice(
                            list.get(i).getMarketPrice(), evenIsPositive(i)
                    )));
        }
    }

    public static String inputDialog(String text) {
        return Optional.ofNullable(showInputDialog(null, text)).orElse("");
    }

    public static String inputDialog(String request, String title, String[] arr) {
        return Optional.ofNullable((String) showInputDialog(
                null, request,
                title, JOptionPane.QUESTION_MESSAGE, null,
                arr, arr[0])).orElse("");
    }

    public static String[] generateIndustriesArray() {
        return new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                "Industrials", "Consumer Discretionary"};
    }

    public static String printErrorList(CoreResponse response) {
        return response.getErrors().stream()
                .map(error -> error.getField() + ": " + error.getMessage())
                .collect(Collectors.joining("\n"));
    }

    public static boolean isNotDouble(String text) {
        try {
            Double.parseDouble(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotInteger(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static void messageDialog(String text) {
        showMessageDialog(null, text);
    }

    public static int convertToInt(double amount) {
        return (int) amount;
    }

    public static double round(double amount) {
        return Math.round(amount * 100.) / 100.;
    }

    private static double generateNextPrice(double currentPrice, boolean positive) {
        return (positive) ? round(currentPrice * (1 + (Math.random()) / 100)) :
                round(currentPrice * (1 - (Math.random()) / 100));
    }

    private static boolean evenIsPositive(int number) {
        return number % 2 == 0;
    }

    public static void importData() {
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