/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.service;

import domain.Service;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetAllServices extends AbstractGenericOperation{

    private List<Service> services;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        services = repository.getAll((Service)param);
    }

    public List<Service> getServices() {
        return services;
    }
    
    
}
