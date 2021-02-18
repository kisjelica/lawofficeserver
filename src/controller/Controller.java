/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Client;
import domain.Invoice;
import domain.Lawyer;
import domain.Obligation;
import domain.ObligationAttendance;
import domain.Service;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.administrator.GetAllAdministrators;
import operation.client.CreateNewClient;
import operation.client.GetClientsByLawyer;
import operation.client.SaveClient;
import operation.invoice.CreateNewInvoice;
import operation.invoice.GetInvoiceByLawyer;
import operation.invoice.SaveInvoice;
import operation.lawyer.CreateNewLawyer;
import operation.lawyer.DeleteLawyer;
import operation.lawyer.GetAllLawyers;
import operation.lawyer.SaveLawyer;
import operation.obligation.CreateObligation;
import operation.obligation.GetAllObligations;
import operation.obligation.attendance.SaveObligationAttendance;
import operation.service.GetAllServices;

/**
 *
 * @author rastko
 */
public class Controller {

    private Controller() {

    }

    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }
    

    private static class ControllerHolder {

        private static final Controller INSTANCE = new Controller();
    }

    public Administrator loginAdmin(String username, String password) throws Exception {
        AbstractGenericOperation operation = new GetAllAdministrators();
        operation.execute(new Administrator());
        List<Administrator> administrators = ((GetAllAdministrators) operation).getAdministrators();
        for (Administrator administrator : administrators) {
            if (administrator.getUsername().equals(username) && administrator.getPassword().equals(password)) {
                return administrator;
            }
        }

        throw new Exception("Unknown user!");

    }

    public Lawyer loginLawyer(String username, String password) throws Exception {
        List<Lawyer> lawyers = getLawyers();
        for (Lawyer lawyer : lawyers) {
            if (lawyer.getUsername().equals(username) && lawyer.getPassword().equals(password)) {
                return lawyer;
            }
        }

        throw new Exception("Unknown user!");
    }

    public List<Lawyer> getLawyers() throws Exception {
        AbstractGenericOperation operation = new GetAllLawyers();
        operation.execute(new Lawyer());
        List<Lawyer> lawyers = ((GetAllLawyers) operation).getLawyers();
        return lawyers;
    }

    public Lawyer createLawyer() throws Exception {
        Lawyer newLawyer = new Lawyer();
        AbstractGenericOperation operation = new CreateNewLawyer();
        operation.execute(newLawyer);
        System.out.println(newLawyer.getLawyerID());
        return newLawyer;
    }

    public boolean checkUsernameExists(String username) throws Exception {
        List<Lawyer> lawyers = getLawyers();
        for (Lawyer lawyer : lawyers) {
            if (lawyer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void saveLawyer(Lawyer lawyer) throws Exception {
        AbstractGenericOperation operation = new SaveLawyer();
        operation.execute(lawyer);
    }

    public void deleteLawyer(Lawyer lawyer) throws Exception {
        AbstractGenericOperation operation = new DeleteLawyer();
        operation.execute(lawyer);
    }

    public Client createClient() throws Exception {
        Client newClient = new Client();
        AbstractGenericOperation operation = new CreateNewClient();
        operation.execute(newClient);
        return newClient;
    }

    public void saveClient(Client clientSave) throws Exception {
        AbstractGenericOperation operation = new SaveClient();
        operation.execute(clientSave);

    }

    public List<Client> getClientsOfLawyer(Lawyer lawyerOfClients) throws Exception {
        AbstractGenericOperation operation = new GetClientsByLawyer();
        operation.execute(lawyerOfClients);
        List<Client> clients = ((GetClientsByLawyer) operation).getClients();

        return clients;
    }

    public Invoice createNewInvoice() throws Exception {
        Invoice newInvoice = new Invoice();
        AbstractGenericOperation operation = new CreateNewInvoice();
        operation.execute(newInvoice);
        return newInvoice;
    }

    public List<Service> getServices() throws Exception {
        AbstractGenericOperation operation = new GetAllServices();
        operation.execute(new Service());
        List<Service> services = ((GetAllServices) operation).getServices();
        return services;
    }
    
    public void saveInvoice(Invoice invoiceToSave) throws Exception {
        AbstractGenericOperation operation = new SaveInvoice();
        operation.execute(invoiceToSave);
    }

    
    public List<Invoice> getInvoicesOfLawyer(Lawyer lawyer) throws Exception {
        AbstractGenericOperation operation = new GetInvoiceByLawyer();
        operation.execute(lawyer);
        List<Invoice> invoices = ((GetInvoiceByLawyer)operation).getInvoices();
        return invoices;
    }
    
    public Obligation createObligation(Obligation obligation) throws Exception {
        AbstractGenericOperation operation = new CreateObligation();
        operation.execute(obligation);
        
        return obligation;
    }
    
    public List<Obligation> getObligations() throws Exception {
        AbstractGenericOperation operation = new GetAllObligations();
        operation.execute(new Obligation());
        List<Obligation> obligations = ((GetAllObligations) operation).getObligations();
        return obligations;
    }
    
    public void saveObligationAttendance(ObligationAttendance obligationAttendance) throws Exception {
        AbstractGenericOperation operation = new SaveObligationAttendance();
        operation.execute(obligationAttendance);
    
    }
}
