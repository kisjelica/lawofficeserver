/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import domain.InvoiceItem;
import domain.Lawyer;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class GetInvoiceByLawyer extends AbstractGenericOperation {

    private List<Invoice> invoices;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Lawyer)) {
            throw new Exception("Invalid search by lawyer");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        invoices = repository.getAll(new Invoice());
        List<Invoice> filteredList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getLawyer().equals((Lawyer) param)) {
                filteredList.add(invoice);
            }
        }
        
        invoices = filteredList;
        
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

}
