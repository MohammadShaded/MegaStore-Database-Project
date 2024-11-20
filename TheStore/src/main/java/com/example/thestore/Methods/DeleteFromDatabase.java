package com.example.thestore.Methods;

import com.example.thestore.TablesFields.OrderDetailsFields;

import java.sql.*;

public class DeleteFromDatabase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";


    public static void deleteCustomer(int customerId) {
        //String deleteQuery = "DELETE FROM customers WHERE customerid = ?";
        String sql = "WITH DeletedCustomer AS ( " +
                "    UPDATE  \"order\" " +
                "    SET customerid = NULL " +
                "    WHERE customerid = ? " +
                "    RETURNING customerid " +
                ") " +
                "DELETE FROM customers " +
                "WHERE customerid = (SELECT customerid FROM DeletedCustomer)";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameter value in the prepared statement
            preparedStatement.setInt(1, customerId);
            // Execute the delete statement
            preparedStatement.executeUpdate();
            System.out.println("Customer with ID " + customerId + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }



    public static void deleteSupplier(int supplierId) {
        String updateSql = "UPDATE \"purchase_order\" SET supplierid = NULL WHERE supplierid = ?";
        String deleteSql = "DELETE FROM supplier WHERE supplierid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
               updateStatement.setInt(1, supplierId);  // Set the actual supplier ID
            updateStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
                deleteStatement.setInt(1, supplierId);  // Set the actual supplier ID
                deleteStatement.executeUpdate();
           // preparedStatement.executeUpdate();
            System.out.println("Supplier with ID " + supplierId + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }

    public static void deleteWarehouse(int warehouseID) {
        String updateSql = "UPDATE \"employee\" SET warehouseid = NULL WHERE warehouseid = ?";
        String updateSql1 = "UPDATE \"orderdetail\" SET warehouseid = NULL WHERE warehouseid = ?";
        String updateSql2 = "UPDATE \"porderdetail\" SET warehouseid = NULL WHERE warehouseid = ?";

        String deleteSql = "DELETE FROM warehouse WHERE warehouseid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, warehouseID);  // Set the actual supplier ID
            updateStatement.executeUpdate();

            PreparedStatement updateStatement1 = connection.prepareStatement(updateSql1);
            updateStatement1.setInt(1, warehouseID);  // Set the actual supplier ID
            updateStatement1.executeUpdate();

            PreparedStatement updateStatement2 = connection.prepareStatement(updateSql2);
            updateStatement2.setInt(1, warehouseID);  // Set the actual supplier ID
            updateStatement2.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, warehouseID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Warehouse with ID " + warehouseID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }

    public static void deleteProduct(int productID) {

        String deleteSql = "DELETE FROM product WHERE productid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);


            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, productID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Product with ID " + productID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }


    public static void deleteOrder(int orderID) {

        String deleteSql = "DELETE FROM \"order\" WHERE orderid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);


            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, orderID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Order with ID " + orderID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }


    public static void deleteTransaction(int TransID) {

        String deleteSql = "DELETE FROM \"financialtransactions\" WHERE transactionid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);


            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, TransID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Transaction with ID " + TransID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }

    public static void deletePOrder(int POrderID) {

        String deleteSql = "DELETE FROM purchase_order WHERE purchaseorderid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);


            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, POrderID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("POrder with ID " + POrderID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }


    public static void deleteEmployee(int employeeID) {
        String updateSql = "UPDATE \"warehouse\" SET manager = NULL WHERE manager = ?";
        String deleteSql = "DELETE FROM employee WHERE employeeid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, employeeID);  // Set the actual supplier ID
            updateStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, employeeID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Employee with ID " + employeeID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }



    public static void deleteInventory(int inventoryID) {
        String deleteSql = "DELETE FROM inventory WHERE inventoryid = ?";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);


            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, inventoryID);  // Set the actual supplier ID
            deleteStatement.executeUpdate();
            // preparedStatement.executeUpdate();
            System.out.println("Inventory with ID " + inventoryID + " deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }



    public static void deleteOrderDetail(OrderDetailsFields orderDetail,int ID) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

        // SQL statement for deleting the row
        String sql = "DELETE FROM orderDetail " +
                "WHERE orderid = ? " +
                "AND productiondate = ? " +
                "AND productid  = ? " +
                "AND warehouseid  = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set values for placeholders in the prepared statement
            preparedStatement.setInt(1, ID);
            preparedStatement.setDate(2, Date.valueOf(orderDetail.getProductionDate()));
            preparedStatement.setInt(3, DataBase.getProductId(orderDetail.getProduct()));
            preparedStatement.setInt(4, DataBase.getWarehouseId(orderDetail.getWarhouse()));

            // Execute the delete operation
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("OrderDetail row deleted successfully.");
            } else {
                System.out.println("No matching row found for deletion.");
            }}
              catch (SQLException e) {
                e.printStackTrace();
                // Handle any SQL exceptions here
            }
        }



    public static void deletePOrderDetail(OrderDetailsFields orderDetail,int ID) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // SQL statement for deleting the row
            String sql = "DELETE FROM porderDetail " +
                    "WHERE purchaseorderid = ? " +
                    "AND productiondate = ? " +
                    "AND productid  = ? " +
                    "AND warehouseid  = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set values for placeholders in the prepared statement
            preparedStatement.setInt(1, ID);
            preparedStatement.setDate(2, Date.valueOf(orderDetail.getProductionDate()));
            preparedStatement.setInt(3, DataBase.getProductId(orderDetail.getProduct()));
            preparedStatement.setInt(4, DataBase.getWarehouseId(orderDetail.getWarhouse()));

            // Execute the delete operation
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("OrderDetail row deleted successfully.");
            } else {
                System.out.println("No matching row found for deletion.");
            }}
        catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }
    }










    }

