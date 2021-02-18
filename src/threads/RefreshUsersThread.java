/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.ServerForm;

/**
 *
 * @author rastko
 */
public class RefreshUsersThread extends Thread {

    ServerForm sf;

    public RefreshUsersThread(ServerForm sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sf.refreshTable();
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RefreshUsersThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
