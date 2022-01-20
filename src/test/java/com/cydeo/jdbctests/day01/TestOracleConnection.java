package com.cydeo.jdbctests.day01;

import java.sql.*;

public class TestOracleConnection {

    public static void main(String[] args) throws SQLException {

/* How can I get this in real ?
             This is called as Connection String
             You will ask this info your team to use .Basically TAKE IT AND USE IT
             They will you port number,IP,Username,password
         */

        String dbUrl = "jdbc:oracle:thin:@54.163.191.98:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";


        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement();

        // using with statement we will execute query
        ResultSet rs = statement.executeQuery("select * from regions");

        // move to cursor first row
        rs.next();

        // now we are at first row w will get data
        System.out.println(rs.getString(2));
        System.out.println(rs.getString("region_name"));

         /*
        // move to cursor first row
       rs.next();

       // now we are at first row w will get data
        System.out.println(rs.getString(2));
        System.out.println(rs.getString("region_name"));

        //  1- Europe
        //  2- Americas

        // second row
        rs.next();

        System.out.println(rs.getInt(1)+"-"+rs.getString(2));

        //third row
        rs.next();
        System.out.println(rs.getInt("region_id")+"-"+rs.getString("region_name"));

         */

        while(rs.next()){
            System.out.println(rs.getInt(1)+"-"+rs.getString(2));
        }






        // close connection
        rs.close();
        statement.close();
        conn.close();


    }
}
