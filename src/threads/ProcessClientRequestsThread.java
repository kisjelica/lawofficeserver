/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Administrator;
import domain.Client;
import domain.Invoice;
import domain.Lawyer;
import domain.Obligation;
import domain.ObligationAttendance;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rastko
 */
public class ProcessClientRequestsThread extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientRequestsThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {

                    switch (request.getOperation()) {
                        case LOGIN_ADMIN:
                            Administrator administrator = (Administrator) request.getArgument();
                            response.setResult(Controller.getInstance().loginAdmin(administrator.getUsername(), administrator.getUsername()));
                            break;
                        case LOGIN_LAWYER:
                            Lawyer lawyerLogin = (Lawyer) request.getArgument();
                            response.setResult(Controller.getInstance().loginLawyer(lawyerLogin.getUsername(),lawyerLogin.getPassword()));
                            break;
                        case CREATE_LAWYER:
                            response.setResult(Controller.getInstance().createLawyer());
                            break;
                        case CHECK_USERNAME_EXISTS:
                            String username = (String) request.getArgument();
                            response.setResult(Controller.getInstance().checkUsernameExists(username));
                            break;
                        case SAVE_LAWYER:
                            Lawyer lawyerSave = (Lawyer) request.getArgument();
                            Controller.getInstance().saveLawyer(lawyerSave);
                            break;
                        case GET_LAWYERS:
                            response.setResult(Controller.getInstance().getLawyers());
                            break;
                        case DELETE_LAWYER:
                            Lawyer lawyerDelete = (Lawyer) request.getArgument();
                            Controller.getInstance().deleteLawyer(lawyerDelete);
                            break;
                        case CREATE_CLIENT:
                            response.setResult(Controller.getInstance().createClient());
                            break;
                        case SAVE_CLIENT:
                            Client clientSave = (Client) request.getArgument();
                            Controller.getInstance().saveClient(clientSave);
                            break;
                        case GET_CLIENTS_OF_LAWYER:
                            Lawyer lawyerOfClients = (Lawyer) request.getArgument();
                            response.setResult(Controller.getInstance().getClientsOfLawyer(lawyerOfClients));
                            break;
                        case CREATE_INVOICE:
                            response.setResult(Controller.getInstance().createNewInvoice());
                            break;
                        case GET_SERVICES:
                            response.setResult(Controller.getInstance().getServices());
                            break;
                        case SAVE_INVOICE:
                            Invoice invoiceToSave = (Invoice) request.getArgument();
                            Controller.getInstance().saveInvoice(invoiceToSave);
                            break;
                        case GET_INVOICES_OF_LAWYER:
                            Lawyer lawyer = (Lawyer) request.getArgument();
                            response.setResult(Controller.getInstance().getInvoicesOfLawyer(lawyer));
                            break;
                        case CREATE_OBLIGATION:
                            Obligation obligation = (Obligation) request.getArgument();
                            response.setResult(Controller.getInstance().createObligation(obligation));
                            break;
                        case GET_OBLIGATIONS:
                            response.setResult(Controller.getInstance().getObligations());
                            break;
                        case SAVE_ATTENDANCE:
                            ObligationAttendance obligationAttendance = (ObligationAttendance) request.getArgument();
                            Controller.getInstance().saveObligationAttendance(obligationAttendance);
                            break;
                        case ATTENDANCE_EXISTS:
                            ObligationAttendance obligationAttendanceCheck = (ObligationAttendance) request.getArgument();
                            response.setResult(Controller.getInstance().attendanceExists(obligationAttendanceCheck));
                            break;
                    }
                } catch (Exception e) {
                    response.setException(e);
                }
                sender.send(response);

            } catch (Exception ex) {

                Logger.getLogger(ProcessClientRequestsThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
