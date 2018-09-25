
package com.geminidsystems.eportproject;

import java.sql.SQLException;

public class Test {
    public static void main(String...args) throws SQLException{
        String sTempDb = "chinook.db";
        String sJdbc = "jdbc:sqlite";
        String JDBC_Url = sJdbc + ":" + sTempDb;
        String DriverName = "org.sqlite.JDBC";
        String username = "", password = "";
        String query1 = "select * from tracks";
        String query2 = "select * from albums";
        String query3 = "select * from employees";
        DbConnect db =new DbConnect();
        db.Connect(JDBC_Url, DriverName, username, password);
        db.execute(query1,query2,query3);
    }
}
