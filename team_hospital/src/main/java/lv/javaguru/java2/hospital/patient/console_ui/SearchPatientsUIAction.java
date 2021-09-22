package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.Paging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.SearchPatientsService;
import java.util.Locale;
import java.util.Scanner;

public class SearchPatientsUIAction implements PatientUIActions {
    private final SearchPatientsService searchPatientsService;

    public SearchPatientsUIAction(SearchPatientsService searchPatientsService) {
        this.searchPatientsService = searchPatientsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient`s name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient`s surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter patient`s personal code: ");
        String personalCode = scanner.nextLine();

        Ordering ordering = getOrdering(scanner);
        Paging paging = getPaging(scanner);

        SearchPatientsRequest request =
                new SearchPatientsRequest(name, surname, personalCode, ordering, paging);

        SearchPatientsResponse response = searchPatientsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(response.getPatientList());
        }
    }

    private Ordering getOrdering(Scanner scanner) {
        System.out.println("Do you want to order list? "
        + "Enter 'yes' or 'no': ");
        String answer = scanner.nextLine().toLowerCase(Locale.ROOT);

        Ordering ordering = null;

        if (answer.equals("yes")) {
            System.out.println("Enter orderBy (Name or Surname): ");
            String orderBy = scanner.nextLine();
            System.out.println("Enter orderDirection (Ascending||Descending): ");
            String orderDirection = scanner.nextLine();
            ordering = new Ordering(orderBy, orderDirection);
        }
        return ordering;
    }

    private Paging getPaging(Scanner scanner) {
        System.out.println("Doy want to see list in pages? " +
                "Enter 'yes' or 'no': ");
        String answer = scanner.nextLine().toLowerCase(Locale.ROOT);

        Paging paging = null;

        if (answer.equals("yes")) {
            System.out.println("Enter pageNumber: ");
            String pageNumber = scanner.nextLine();
            System.out.println("Enter pageSize: ");
            String pageSize = scanner.nextLine();
            paging = new Paging(pageNumber, pageSize);
        }
        return paging;
    }
}
