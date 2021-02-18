/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.obligation;

import domain.Obligation;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetAllObligations extends AbstractGenericOperation{

    private List<Obligation> obligations;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        obligations = repository.getAll((Obligation) param);
    }

    public List<Obligation> getObligations() {
        return obligations;
    }
    
    
    
}
