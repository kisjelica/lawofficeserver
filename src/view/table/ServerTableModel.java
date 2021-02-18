/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import util.User;

/**
 *
 * @author rastko
 */
public class ServerTableModel extends AbstractTableModel{

    private final List<User> users;
    private final String[] columnNames = new String[]{"Name", "Username"};
    public ServerTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user =  users.get(rowIndex);
        switch(columnIndex){
            case 0:
                return user.getName();
            case 1:
                return user.getUsername();
            default:
                return "n/a";
        }
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
