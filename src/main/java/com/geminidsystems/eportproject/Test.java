
package com.geminidsystems.eportproject;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

public class Test {
    
    static Properties prop = null;
        
    public static void main(String...args) throws SQLException{
        //method call to load .properties file
        parameterize();
        //getting values from .properties file
        String sTempDb = prop.getProperty("sTempDb");
        String sJdbc = prop.getProperty("sJdbc");
        String JDBC_Url = sJdbc + ":" + sTempDb;
        String DriverName = prop.getProperty("DriverName");
        String username = "", password = "";
        String query1 = prop.getProperty("query1");
        String query2 = prop.getProperty("query2");
        String query3 = prop.getProperty("query3");
        
        DbConnect db =new DbConnect();
        
        db.Connect(JDBC_Url, DriverName, username, password);   //connectiong to DB
        
        db.exportQueryResultToCSV(query1,query2,query3);   //executing queries
    }
    
    public static void parameterize(){

	try(FileReader file = new FileReader("Conf.properties")) {
            prop = new Properties();
            prop.load(file);
    	} catch (IOException ex) {
		ex.printStackTrace();
	}
    }
}