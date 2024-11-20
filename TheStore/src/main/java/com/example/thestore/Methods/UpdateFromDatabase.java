package com.example.thestore.Methods;

import com.example.thestore.TablesFields.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFromDatabase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";
    public static void updateCustomer(CustomerFields customer) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE customers SET cname=?, contact_information=?, address=? WHERE customerid=?";

        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getContactInformation());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getCustomerID());

            // Execute the update query.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with ID: " + customer.getCustomerID());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }





    public static void updateEmployee(EmployeeFields employee) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE  employee SET EmployeeID=?, First_Name=?, Last_Name=?, City=? , Street=?, Email_Address=?, " +
                "Phone_Number=?, Gender=?, Salary=?, Hire_Date=?, Bank_Account_Information=?, Birthdate=?,warehouseid=?," +
                "password=?, role=? WHERE employeeid=?";

        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            pstmt.setInt(1, employee.getID());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setString(4, employee.getCity());
            pstmt.setString(5, employee.getStreet());
            pstmt.setString(6, employee.getEmail());
            pstmt.setString(7, employee.getPhone());
            pstmt.setString(8, String.valueOf(employee.getGender()));
            pstmt.setInt(9,employee.getSalary());
            pstmt.setDate(10, java.sql.Date.valueOf(employee.getHireDate()));
            pstmt.setString(11, employee.getBankInfo());
            pstmt.setDate(12, java.sql.Date.valueOf(employee.getBirthDate()));
            pstmt.setInt(13, DataBase.getWarehouseId(employee.getWarehouse())
            );
            pstmt.setString(14, employee.getPassword());
            pstmt.setString(15, employee.getChoiceRoll());
            pstmt.setInt(16,employee.getID());

            // Execute the update query.
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }
    public static void updateSupplier(SupplierFields Supplier) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE supplier SET company_name=?, contact_information=?, supplier_location=? WHERE supplierid=?";

        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            preparedStatement.setString(1, Supplier.getName());
            preparedStatement.setString(2, Supplier.getContactInformation());
            preparedStatement.setString(3, Supplier.getLocation());
            preparedStatement.setInt(4, Supplier.getSupplierID());

            // Execute the update query.
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }
    public static void updateInventory(InventoryFields Inventory) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE inventory SET productid=?, quantity=?, productiondate=? , warehouseid=? WHERE inventoryid=?";

        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            preparedStatement.setInt(1,DataBase.getProductId(Inventory.getProduct()));
            preparedStatement.setDouble(2, Inventory.getQuantity());
            preparedStatement.setDate(3, java.sql.Date.valueOf(Inventory.getProductionDate()));
            preparedStatement.setInt(4, DataBase.getWarehouseId(Inventory.getWarehouse()));
            preparedStatement.setInt(5, Inventory.getInventoryid());
            // Execute the update query.
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }

    public static void updateProduct(ProductFields Product) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE Product SET pname=?, price=?, manufacturer=? , brand=? WHERE productid=?";

        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            preparedStatement.setInt(5, Product.getProductid());
            preparedStatement.setString(1, Product.getPname());
            preparedStatement.setDouble(2, Product.getPrice());
            preparedStatement.setString(3, Product.getManufacturer());
            preparedStatement.setString(4, Product.getBrand());
            // Execute the update query.
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }
    public static void updateWarehouse(WarehouseFields Warehouse) {
        // SQL query for updating a customer based on their ID.
        String sql = "UPDATE warehouse SET wname=?, city=?, street=? , inventorycapacity=? , manager=? WHERE warehouseid=?";
        Integer managerID =DataBase.getManagerId(Warehouse.getManager());
        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set the parameters for the update query based on the CustomerFields object.
            preparedStatement.setInt(6, Warehouse.getWarehouseid());
            preparedStatement.setString(1, Warehouse.getWname());
            preparedStatement.setString(2, Warehouse.getCity());
            preparedStatement.setString(3, Warehouse.getStreet());
            if (managerID == -1) {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(5, managerID);
            }            preparedStatement.setInt(4, Warehouse.getCapacity());
            // Execute the update query.
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }
}
