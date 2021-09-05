package lv.javaguru.java2.core.responce.Add;

import lv.javaguru.java2.Specialist;

public class AddSpecialistResponse {

    private Specialist specialist;

    public AddSpecialistResponse(Specialist specialist) {
        this.specialist = specialist;
    }

    public Specialist getSpecialist() {
        return specialist;
    }
}
