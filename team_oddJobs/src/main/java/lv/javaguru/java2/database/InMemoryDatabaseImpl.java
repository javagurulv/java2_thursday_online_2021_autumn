package lv.javaguru.java2.database;


import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.Client;
import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DIComponent
public class InMemoryDatabaseImpl implements Database {

    private Long nextId = 1L;
    private List<Specialist> specialists = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Advertisement> advBoards = new ArrayList<>();

    @Override
    public void addSpecialist(Specialist specialist) {
        specialist.setSpecialistId(nextId);
        ++nextId;
        specialists.add(specialist);

    }

    @Override
    public void addClient(Client client) {
        client.setClientId(nextId);
        ++nextId;
        clients.add(client);
    }

    @Override
    public boolean removeSpecialist(Long id, String name, String surname) {

        boolean isSpecialistDeleted = false;
        Optional<Specialist> specialistToDelete = specialists.stream()
                .filter(specialist -> specialist.getSpecialistId().equals(id) && specialist.getSpecialistName().equals(name) && specialist.getSpecialistSurname().equals(surname))
                .findFirst();
        if (specialistToDelete.isPresent()) {
            Specialist specialistToRemove = specialistToDelete.get();
            isSpecialistDeleted = specialists.remove(specialistToRemove);
        }
        return isSpecialistDeleted;
    }

    @Override
    public boolean removeClient(Long id, String name, String surname) {

        boolean isClientRemoved = false;

        Optional<Client> clientToDelete = clients.stream()
                .filter(client -> client.getClientId().equals(id) && client.getClientName().equals(name) && client.getClientSurname().equals(surname))
                .findFirst();
        if (clientToDelete.isPresent()) {
            Client clientToRemove = clientToDelete.get();
            isClientRemoved = clients.remove(clientToRemove);
        }
        return isClientRemoved;
    }


    @Override
    public List<Client> findClientsById(Long clientId) {
        return clients.stream()
                .filter(client -> client.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientsByName(String clientName) {
        return clients.stream()
                .filter(client -> client.getClientName().equals(clientName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientBySurname(String clientSurname) {
        return clients.stream()
                .filter(client -> client.getClientSurname().equals(clientSurname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientByIdAndNameAndSurname(Long clientId, String clientName, String clientSurname) {
        return clients.stream()
                .filter(client -> client.getClientId().equals(clientId))
                .filter(client -> client.getClientName().equals(clientName))
                .filter(client -> client.getClientSurname().equals(clientSurname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        return specialists.stream()
                .filter(specialist -> specialistId.equals(specialist.getSpecialistId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        return specialists.stream()
                .filter(specialist -> specialistName.equals(specialist.getSpecialistName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        return specialists.stream()
                .filter(specialist -> specialistSurname.equals(specialist.getSpecialistSurname()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String profession) {
        return specialists.stream()
                .filter(specialist -> specialist.getSpecialistProfession().equals(profession))
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        return specialists.stream()
                .filter(specialist -> specialistName.equals(specialist.getSpecialistName()))
                .filter(specialist -> specialistSurname.equals(specialist.getSpecialistSurname()))
                .filter(specialist -> specialistProfession.equals(specialist.getSpecialistProfession()))
                .collect(Collectors.toList());
    }


    @Override
    public void addAdvertisement(Advertisement advBoard) {
        advBoard.setAdvId(nextId);
        ++nextId;
        advBoards.add(advBoard);
    }


    @Override
    public boolean removeAdvertisement(Long advId, String advBoardTitle) {
        boolean isAdvRemoved = false;

        Optional<Advertisement> advToDelete = advBoards.stream()
                .filter(advertisementBoard -> advertisementBoard.getAdvId().equals(advId) && advertisementBoard.getAdvTitle().equals(advBoardTitle))
                .findFirst();
        if (advToDelete.isPresent()) {
            Advertisement advBoardToRemove = advToDelete.get();
            isAdvRemoved = advBoards.remove(advBoardToRemove);
        }
        return isAdvRemoved;
    }

    @Override
    public List<Advertisement> findAdvertisementByTitle(String advTitle) {
        return advBoards.stream()
                .filter(advBoard -> advBoard.getAdvTitle().equals(advTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Advertisement> findAdvertisementById(long advId) {
        return advBoards.stream()
                .filter(advBoard -> advBoard.getAdvId().equals(advId))
                .collect(Collectors.toList());
    }
//
//    @Override
//    public void findAdvertisementById(long advId) {
//        for (Advertisement advertisement : advBoards) {
//            if (advId==(advertisement.getAdvId())) {
//                System.out.println(advertisement);
//            } /*else {
//                System.out.println("Advertisement is not found");
//            }*/
//        }
//    }

//    @Override
//    public void findAdvertisementById(long advId) {
//        for (Advertisement advertisement : advBoards) {
//            if (advId==(advertisement.getAdvId())) {
//                System.out.println(advertisement);
//            } /*else {
//                System.out.println("Advertisement is not found");
//            }*/
//        }
//    }

    @Override
    public List<Specialist> getAllSpecialist() {
        return specialists;
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public List<Advertisement> getAllAdvertisemets() {
        return advBoards;
    }


}








