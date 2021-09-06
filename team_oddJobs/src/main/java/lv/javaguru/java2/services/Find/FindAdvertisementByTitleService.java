package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.database.Database;

public class FindAdvertisementByTitleService {

    private Database database;

    public FindAdvertisementByTitleService(Database database) {this.database = database;}

    public void execute(String advTitle) {database.findAdvertisementByTitle(advTitle);}
}
