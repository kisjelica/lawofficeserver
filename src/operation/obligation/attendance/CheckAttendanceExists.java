/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.obligation.attendance;

import domain.ObligationAttendance;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class CheckAttendanceExists extends AbstractGenericOperation{
    private boolean exists;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof ObligationAttendance))
            throw new Exception("Invalid attendance data!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<ObligationAttendance> attendances = repository.getAll(new ObligationAttendance());
        for (ObligationAttendance attendance : attendances) {
            if(attendance.equals((ObligationAttendance) param)){
                exists = true;
                return;
            }
        }
        exists = false;
    }

    public boolean isExists() {
        System.out.println(exists);
        return exists;
    }
    
    
}
