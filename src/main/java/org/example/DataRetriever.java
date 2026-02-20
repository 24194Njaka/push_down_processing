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
      SELECT i.id, i.customer_name, i.status,SUM(il.quantity * il.unit_price) AS total_amount
                    FROM invoice i
                    JOIN invoice_line il ON i.id = il.invoice_id
                    GROUP BY i.id, i.customer_name,i.status
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

    public List<InvoiceTotal> findConfirmedAndPaidInvoiceTotals() {
        List<InvoiceTotal> list = new ArrayList<>();
        String sql =
                """
         SELECT i.id, i.customer_name, i.status,SUM(il.quantity * il.unit_price) AS total_amount
                    FROM invoice i
                    JOIN invoice_line il ON i.id = il.invoice_id
                    where  i.status IN ('CONFIRMED', 'PAID')
                    GROUP BY i.id, i.customer_name,i.status
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


    public InvoiceStatusTotal computeStatusTotals(){
        String sql = """
        SELECT sum(case when i.status = 'PAID' then il.quantity * il.unit_price else 0 end) as total_paid,
               sum(case when i.status = 'CONFIRMED' then il.quantity * il.unit_price else 0 end) as total_confirmed,
               sum(case when i.status = 'DRAFT' then il.quantity * il.unit_price else 0 end) as total_draft
        FROM invoice i
        JOIN invoice_line il ON i.id = il.invoice_id;
        """;

        try(
             Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            if(rs.next()){
                return new InvoiceStatusTotal(
                        rs.getDouble("total_paid"),
                        rs.getDouble("total_confirmed"),
                        rs.getDouble("total_draft")

                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;


    }


    public  Double computeWeightedTurnover() {
        String sql = """
                      SELECT SUM(
                           CASE i.status
                               WHEN 'PAID'      THEN il.quantity * il.unit_price * 1.0
                               WHEN 'CONFIRMED' THEN il.quantity * il.unit_price * 0.5
                               WHEN 'DRAFT'     THEN il.quantity * il.unit_price * 0.0
                               ELSE 0
                           END
                       ) AS revenus_price
                       FROM invoice_line il
                       JOIN invoice i ON i.id = il.invoice_id
                
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("revenus_price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  List<InvoiceTaxSummary> findInvoiceTaxSummaries() {
        List<InvoiceTaxSummary> list = new ArrayList<>();

        String sql = """
                  SELECT\s
                            i.id,
                            SUM(il.quantity * il.unit_price) AS total_ht,
                            SUM(il.quantity * il.unit_price) * (t.rate / 100) AS total_tva,
                            SUM(il.quantity * il.unit_price) * (1 + t.rate / 100) AS total_ttc
                        FROM invoice i
                        JOIN invoice_line il ON i.id = il.invoice_id
                        JOIN tax_config t ON t.id = 1
                        GROUP BY i.id, t.rate
                        ORDER BY i.id
                """;
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();){
            while (rs.next()){
                list.add(new InvoiceTaxSummary(
                    rs.getInt("id"),
                    rs.getDouble("total_ht"),
                    rs.getDouble("total_tva"),
                    rs.getDouble("total_ttc")
                ));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }




}






















