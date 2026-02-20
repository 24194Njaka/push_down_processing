package org.example;

import java.util.Locale;

public class InvoiceTaxSummary {
    private  Integer invoiceId;
    private  Double totalHt;
    private  Double totalTva;
    private  Double totalTtc;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getTotalHt() {
        return totalHt;
    }

    public void setTotalHt(Double totalHt) {
        this.totalHt = totalHt;
    }

    public Double getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(Double totalTva) {
        this.totalTva = totalTva;
    }

    public Double getTotalTtc() {
        return totalTtc;
    }

    public void setTotalTtc(Double totalTtc) {
        this.totalTtc = totalTtc;
    }

    public InvoiceTaxSummary(Integer invoiceId, Double totalHt, Double totalTva, Double totalTtc) {
        this.invoiceId = invoiceId;
        this.totalHt = totalHt;
        this.totalTva = totalTva;
        this.totalTtc = totalTtc;


    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "%d | HT %.2f | TVA %.2f | TTC %.2f",
                invoiceId, totalHt, totalTva, totalTtc);
    }
}
