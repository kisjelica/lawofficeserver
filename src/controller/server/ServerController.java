/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.server;

import view.ServerForm;

/**
 *
 * @author rastko
 */
public class ServerController {
    private final ServerForm sf;
    private ServerController() {
        sf = new ServerForm();
      
    }
    
    public static ServerController getInstance() {
        return ServerControllerHolder.INSTANCE;
    }
    
    private static class ServerControllerHolder {

        private static final ServerController INSTANCE = new ServerController();
    }
}
