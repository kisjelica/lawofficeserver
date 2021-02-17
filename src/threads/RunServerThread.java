/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rastko
 */
public class RunServerThread extends Thread{

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1389);
            System.out.println("Server is up...");
            while (true) {                
                Socket s = ss.accept();
                System.out.println("Client connected.");
                ProcessClientRequestsThread pcrt = new ProcessClientRequestsThread(s);
                pcrt.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(RunServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
