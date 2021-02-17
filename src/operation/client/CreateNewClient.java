/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.client;

import domain.Client;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class CreateNewClient extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Client)) {
            throw new Exception("Invalid client data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Client) param);
    }

}
