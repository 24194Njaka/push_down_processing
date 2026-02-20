package org.example;

import java.util.Locale;

public class InvoiceTotal {
    private Integer invoiceId;
    private String customerName;
    private InvoiceStatus status;
    private Double total;


    public InvoiceTotal(Integer invoiceId, String customerName, InvoiceStatus status, Double total) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.status = status;
        this.total = total;
    }


    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public InvoiceStatus getStatus() {
        return status;
    }
    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return invoiceId + " | " + customerName + " | " + status + " | "
                + String.format(Locale.US, "%.2f", total);
    }



}
