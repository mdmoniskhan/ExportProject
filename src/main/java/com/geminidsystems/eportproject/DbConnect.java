
package com.geminidsystems.eportproject;

import java.sql.*;

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
    
    public void execute(String...queries){
        
        for(String query:queries){
            try {
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();                     
                // Iterate through the data in the result set and display it. 
                while (rs.next()) {
                    //Print one row          
                    for(int i = 1 ; i <= columnsNumber; i++){
                        System.out.print(rs.getString(i) + " "); //Print one element of a row
                    }
                    System.out.println();//Move to the next line to print the next row.           
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}