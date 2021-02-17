/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.lawyer;

import domain.Lawyer;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetAllLawyers extends AbstractGenericOperation{

    private List<Lawyer> lawyers;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lawyers = repository.getAll((Lawyer) param);
    }

    public List<Lawyer> getLawyers() {
        return lawyers;
    }
    
    
}
