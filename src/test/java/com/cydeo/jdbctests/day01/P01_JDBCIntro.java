package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P01_JDBCIntro {


    String dbUrl = "jdbc:oracle:thin:@54.163.191.98:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {
        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement();

        // using with statement we will execute query
        ResultSet rs = statement.executeQuery("select * from departments where manager_id is not null ");

        /*

        10 - Administration - 200 -1700

         */

        while (rs.next()) {

            System.out.println(rs.getInt(1) + "-" + rs.getString("department_name") + "-" + rs.getInt(3) + "-" + rs.getInt(4));
        }


        //close all conn
        rs.close();
        statement.close();
        conn.close();
    }
}



