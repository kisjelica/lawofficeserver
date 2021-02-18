/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.obligation;

import domain.Obligation;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class CreateObligation extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Obligation))
            throw new Exception("Invalid obligation data!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Obligation)param);
    }
    
}
