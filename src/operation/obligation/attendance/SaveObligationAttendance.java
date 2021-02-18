/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.obligation.attendance;

import domain.ObligationAttendance;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class SaveObligationAttendance extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof ObligationAttendance))
            throw new Exception("Invalid attendance data");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((ObligationAttendance) param);
    }
    
}
