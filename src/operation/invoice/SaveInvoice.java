/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import domain.InvoiceItem;
import operation.AbstractGenericOperation;

/**
 *
 * @author rastko
 */
public class SaveInvoice extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Invoice)) {
            throw new Exception("Invalid invoice data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Invoice invoice = (Invoice) param;
        repository.edit(invoice);
        for (InvoiceItem item : invoice.getItems()) {
            repository.add(item);
        }
    }
    
}
