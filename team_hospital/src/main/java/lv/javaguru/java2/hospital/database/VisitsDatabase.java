package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public interface VisitsDatabase {

    void recordVisit(Visit visit);

    boolean deleteVisit(Long id);

    List<Visit> showAllVisits();

    boolean editVisit(Long visitId, int userInput, String changes);
}
