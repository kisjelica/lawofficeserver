/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.client;

import domain.Client;
import domain.Lawyer;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetClientsByLawyer extends AbstractGenericOperation {

    private List<Client> clients;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        clients = repository.getAll(new Client());
        List<Client> filteredClients = new ArrayList<>();
        for (Client client : clients) {
            if (client.getLawyer().equals((Lawyer) param)) {
                System.out.println(client);
                filteredClients.add(client);
            }
        }
        clients = filteredClients;
    }

    public List<Client> getClients() {
        return clients;
    }

}
