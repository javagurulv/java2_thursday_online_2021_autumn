package lv.javaguru.java2.database;


import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.Client;
import lv.javaguru.java2.Specialist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
/*        specialists.stream()
//                .filter(specialist -> specialist.getId().equals(id))
//                .findFirst()
               .ifPresent(specialist -> specialists.remove(specialist));

 */

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
/*        clients.stream()
//                .filter(client -> client.getId().equals(id))
//                .findFirst()
                .ifPresent(client -> clients.remove(client));

 */

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
    public void findSpecialistByProfession(String profession) {
        for (Specialist specialist : specialists) {
            if (profession.equals(specialist.getSpecialistProfession())) {
                System.out.println(specialist);
                if (!profession.equals(specialist.getSpecialistProfession())) {
                    System.out.println("Specialist with current profession not found");
                }
            }
        }
    }

    @Override
    public void findClientById(Long id) {


        for (Client client : clients) {
            if (!id.equals(client.getClientId())) {
                System.out.println("Client by such search criteria not found, please try again!");
            } else {
                System.out.println(client);

            }
        }

    }

    @Override
    public void findClientByName(String name) {


        for (Client client : clients) {
            if (!name.equals(client.getClientName())) {
                System.out.println("Client by such search criteria not found, please try again!");
            } else {
                System.out.println(client);
            }
        }

    }

    @Override
    public void findClientBySurname(String surName) {


        for (Client client : clients) {
            if (!surName.equals(client.getClientName())) {
                System.out.println("Client by such search criteria not found, please try again!");
            } else {
                System.out.println(client);
            }
        }

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


//    @Override
//    public void findClientById(Long id, String name, String surName) {
//        for (Client client : clients) {
//
//            if (!id.equals(client.getClientId()) && name.equals(client.getClientName()) && surName.equals(client.getClientSurname())) {
//                System.out.println("Client by such search criteria not found, please try again!");
//            } else {
//                System.out.println(client);
//            }
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


}
