package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P02_FlexibleNavigation {

    String dbUrl = "jdbc:oracle:thin:@34.192.175.227:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {
        /*
        -ResultSet methods
    .next( );        -will move the cursor to the next row. (Returns Boolean)
    .getRow( );        -it will return the number of the row in Int (Returns Int)
    .absolute( num);    -it will jump to the given row number (Returns Int)
    .previous( );        -it will jump one previous row (Returns Int)
    .last();        -jumps to the last row and we can use .getRow( ); to get the total row number
    .beforeFirst ( );    -It will make it to jump to the beginning of the rows (before the first row)
    .first( );        -Jump to the firs row (Returns Int)
    .afterLast( );        -It will jump to the end of the row (Returns 0 - zero)
         */

        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

         /*
        ResultSet.TYPE_SCROLL_INSENSITIVE  --> to be able to scroll your cursor or to jump directly in a certain row
        ResultSet.CONCUR_READ_ONLY         --> we are saying I dont wanna change anything in database
                                                makes the object immutable

         */

        // using with statement we will execute query
        ResultSet rs=statement.executeQuery("select first_name,last_name from employees");

        //first row
        rs.next();
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        // second row
        rs.next();
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        // last row
        rs.last();
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        // how to find how many row we have
        int row = rs.getRow();
        System.out.println(row);

        // jump to row 8
        rs.absolute(8);
        row = rs.getRow();
        System.out.println(row);
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        // go previous row
        rs.previous();
        row=rs.getRow();
        System.out.println(row);
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        //last
        rs.last();
        row=rs.getRow();
        System.out.println(row);


        //beforeFirst, we need to write this method if we want to get information to be printed
        rs.beforeFirst();

        while (rs.next()) {

            System.out.println(rs.getString(1)+"-"+rs.getString(2));

        }

        // jump to the first row

        rs.first();
        System.out.println(rs.getRow());

        //close conn
        rs.close();
        statement.close();
        conn.close();

    }
}
