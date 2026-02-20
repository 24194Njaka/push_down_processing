package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public List<InvoiceTotal> findInvoiceTotals() {
        List<InvoiceTotal> list = new ArrayList<>();
        String sql = """
    SELECT i.id, i.customer_name, i.status, 
           SUM(il.quantity * il.unit_price) AS total_amount
    FROM invoice i
    JOIN invoice_line il ON i.id = il.invoice_id
    GROUP BY i.id, i.customer_name, i.status
    ORDER BY i.id;
    """;


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new InvoiceTotal(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        InvoiceStatus.valueOf(rs.getString("status")),
                        rs.getDouble("total_amount")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public List<InvoiceTotal> findConfirmedAndPaidInvoiceTotals(){
        List<InvoiceTotal> list = new ArrayList<>();
        String  sql = """
         SELECT i.id, i.customer_name, i.status,SUM(il.quantity * il.unit_price) AS total_amount
    FROM invoice i
    JOIN invoice_line il ON i.id = il.invoice_id
    WHERE i.status IN ('CONFIRMED', 'PAID')
    GROUP BY i.id, i.customer_name, i.status
    ORDER BY i.id;
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                InvoiceTotal invoiceTotal = new InvoiceTotal(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        InvoiceStatus.valueOf(rs.getString("status")),
                        rs.getDouble("total_amount")
                );
                list.add(invoiceTotal);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }




}
