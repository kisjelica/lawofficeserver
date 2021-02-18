/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rastko
 */
public class RunServerThread extends Thread{
    
    private ServerSocket serverSocket;
    private List<ProcessClientRequestsThread> clients;

    public RunServerThread() throws IOException {
        serverSocket = new ServerSocket(1389);
        clients = new ArrayList<>();
    }
    
    
    @Override
    public void run() {
       while(!serverSocket.isClosed()){
            System.out.println("waiting...");
            try {
                Socket socket=serverSocket.accept();
                ProcessClientRequestsThread client=new ProcessClientRequestsThread(socket);
                client.start();
                clients.add(client);
                System.out.println("Client connected!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        stopClientHandlers();
    }
    
     public  void stopServerThread() throws IOException{
        serverSocket.close();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    private void stopClientHandlers(){
        for (ProcessClientRequestsThread client : clients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
