package com.example.thestore.Methods;

import com.example.thestore.TablesFields.CustomerFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetFieldsByID {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";



    public static CustomerFields getCustomerFields(int id) {
        CustomerFields Customer = new CustomerFields();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE customerid="+id);


            if (resultSet.next()) {
                Customer.setCustomerID(resultSet.getInt("customerid"));
                Customer.setName(resultSet.getString("cname"));
                Customer.setAddress(resultSet.getString("address"));
                Customer.setContactInformation(resultSet.getString("contact_information"));
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  Customer;
    }















}
