/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.administrator;

import domain.Administrator;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetAllAdministrators extends AbstractGenericOperation{

    private List<Administrator> administrators;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        administrators = repository.getAll((Administrator) param);
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }
    
}
