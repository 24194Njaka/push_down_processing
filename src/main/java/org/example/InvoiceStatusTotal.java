package org.example;

public class InvoiceStatusTotal {
    private Double invoicePaid;
    private Double invoiceConfirmed;
    private Double invoiceDraft;
    public InvoiceStatusTotal(Double invoicePaid, Double invoiceConfirmed, Double invoiceDraft) {
        this.invoicePaid = invoicePaid;
        this.invoiceConfirmed = invoiceConfirmed;
        this.invoiceDraft = invoiceDraft;

    }

    public Double getInvoicePaid() {
        return invoicePaid;
    }

    public void setInvoicePaid(Double invoicePaid) {
        this.invoicePaid = invoicePaid;
    }

    public Double getInvoiceConfirmed() {
        return invoiceConfirmed;
    }

    public void setInvoiceConfirmed(Double invoiceConfirmed) {
        this.invoiceConfirmed = invoiceConfirmed;
    }

    public Double getInvoiceDraft() {
        return invoiceDraft;
    }

    public void setInvoiceDraft(Double invoiceDraft) {
        this.invoiceDraft = invoiceDraft;
    }


    @Override
    public String toString() {
        return
                "total_Paid=" + invoicePaid +
                ", total_Confirmed=" + invoiceConfirmed +
                ", total_Draft=" + invoiceDraft ;
    }
}
