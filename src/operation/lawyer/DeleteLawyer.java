/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.lawyer;

import domain.Lawyer;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class DeleteLawyer extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof Lawyer)) {
            throw new Exception("Invalid lawyer data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Lawyer) param);
    }
    
}
