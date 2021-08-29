package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.Client;
import lv.javaguru.java2.oddJobs.Specialist;

import java.util.ArrayList;
import java.util.List;


public class InMemoryDatabaseImpl implements Database {
    private Long nextId = 1L;
    private List<Specialist> specialists = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    @Override
    public void saveSpecialist(Specialist specialist) {
        specialist.setId(nextId);
        ++nextId;
        specialists.add(specialist);

    }

    @Override
    public void saveClient(Client client) {
        client.setId(nextId);
        ++nextId;
        clients.add(client);
    }

    @Override
    public void deleteSpecialistById(Long id) {
        specialists.stream()
                .filter(specialist -> specialist.getId().equals(id))
                .findFirst()
                .ifPresent(specialist -> specialists.remove(specialist));
    }

    @Override
    public void deleteClientById(Long id) {
        clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .ifPresent(client -> clients.remove(client));
    }


    @Override
    public void findSpecialistByProfession(String profession) {
        for (Specialist specialist : specialists) {
            if (profession.equals(specialist.getProfession())) {
                System.out.println(specialist);
                if (!profession.equals(specialist.getProfession())) {
                    System.out.println("Specialist with current profession not found");
                }
            }
        }
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        return specialists;
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }
}
