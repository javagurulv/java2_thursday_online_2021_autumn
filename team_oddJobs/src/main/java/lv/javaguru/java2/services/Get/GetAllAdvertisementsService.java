package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class GetAllAdvertisementsService {
    private Database database;

    public GetAllAdvertisementsService(Database database) {this.database=database;}

    public List <Advertisement> execute() {return database.getAllAdvertisemets();}
}
