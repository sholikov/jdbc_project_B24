package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P03_MetadataTest {


    String dbUrl = "jdbc:oracle:thin:@34.192.175.227:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {

        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // using with statement we will execute query
        ResultSet rs=statement.executeQuery("select * from regions");


        // Database Metadata --> Information about database

        DatabaseMetaData dbMetaData = conn.getMetaData();

        System.out.println(dbMetaData.getUserName());
        System.out.println(dbMetaData.getDatabaseProductName());
        System.out.println(dbMetaData.getDatabaseProductVersion());
        System.out.println(dbMetaData.getDriverName());
        System.out.println(dbMetaData.getDriverVersion());

        /*
        You will not use it as a testing purpose.This is extra information about Metadata
         */

        //ResultSet MetaData

        ResultSetMetaData rsmd = rs.getMetaData();

        // how many column we have
        int columnCount = rsmd.getColumnCount();
        System.out.println(columnCount);


        // how can we learn columnName for second column
        System.out.println(rsmd.getColumnName(2));

        System.out.println("Dynamic print");
        //print all column name as dynamicly
        for (int i = 1; i <=columnCount; i++) {
            System.out.println(rsmd.getColumnName(i));

        }

        /*

            ResultSet
                    rs.next();   -- > to iterate each row
                    rs.getString(index)  --> to get data from related column


            ResultMetaData
                     rsmd.getColumnCount()    -->  to get column Get
                     rsmd.getColumnName(index) --> to get column information / name

         */
        // REGION ID-1
        // REGION NAME-EUROPE
        while(rs.next()){

            for (int i = 1; i <=columnCount; i++) {
                System.out.println(rsmd.getColumnName(i)+"-"+rs.getString(i));

            }
        }




        //close conn
        rs.close();
        statement.close();
        conn.close();

    }

}