
package com.geminidsystems.eportproject;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class DbConnect {
    
    Connection connObj = null;
    Statement st = null;

    public void Connect(String JDBC_Url, String DriverName, String username, String password) {
        
        try {
            // register the driver 
            Class.forName(DriverName);
            connObj = DriverManager.getConnection(JDBC_Url,username,password);
            if(connObj != null) {
                System.out.println("Connection to SQLite has been established.");
                st = connObj.createStatement();
            }else{
                System.out.println("No Connection.");
            }
        } catch (SQLException sqlException) {
             sqlException.printStackTrace();
            System.out.println("DataBase or Password or Table Name is incorrect");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Class Not Found!");
        }
    }
    
    public void execute(String... queries) {

        for (String query : queries) {
            try {
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                // Iterate through the data in the result set and display it. 
                String SAMPLE_CSV_FILE = rsmd.getTableName(1);
                try (
                        BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
                        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);) {
                    while (rs.next()) {
                        //Print one row 
                        for (int i = 1; i <= columnsNumber; i++) {
                            csvPrinter.print(rs.getString(i));
                        }
                        csvPrinter.println();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}