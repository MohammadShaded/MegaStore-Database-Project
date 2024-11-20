package com.example.thestore.Methods;

import com.example.thestore.TablesFields.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchFromDatabase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";



    public static void searchEmployees(EmployeeFields fields, TableView<EmployeeFields> employeeTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM employeeT WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();

        // Add conditions based on the provided input values.
        if (!fields.getFirstName().isEmpty()) {
            sql.append(" AND first_name LIKE ?");
            parameters.add("%" + fields.getFirstName() + "%");
        }

        if (!fields.getLastName().isEmpty()) {
            sql.append(" AND last_name LIKE ?");
            parameters.add("%" + fields.getLastName()+ "%");
        }
        if (!fields.getCity().isEmpty()) {
            sql.append(" AND city LIKE ?");
            parameters.add("%" + fields.getCity() + "%");
        }
        if (!fields.getStreet().isEmpty()) {
            sql.append(" AND street LIKE ?");
            parameters.add("%" + fields.getStreet() + "%");
        }

        if (!fields.getWarehouse().isEmpty()) {
            sql.append(" AND wname LIKE ?");
            parameters.add("%" + fields.getWarehouse() + "%");
        }

        if (!fields.getEmail().isEmpty()) {
            sql.append(" AND email_address LIKE ?");
            parameters.add("%" + fields.getEmail() + "%");
        }

        if (fields.getID()!=-1) {
            sql.append(" AND employeeid = ?");
            parameters.add(fields.getID());
        }

        if (!fields.getPhone().isEmpty()) {
            sql.append(" AND phone_number LIKE ?");
            parameters.add("%" + fields.getPhone() + "%");
        }

        if (fields.getGender()!='K') {
            sql.append(" AND gender = ?");
            parameters.add(fields.getGender());
        }
        if(fields.getHireDate()!=null){
            sql.append(" AND hire_date BETWEEN ? AND ?");
            parameters.add(fields.getHireDate());
            parameters.add(fields.getHireTo());
        }
        if(!fields.getChoiceRoll().isEmpty()){
            sql.append(" AND role = ?");
            parameters.add(fields.getChoiceRoll());
        }
        if(fields.getSalary()!=null){
            sql.append(" AND salary BETWEEN ? AND ?");
            parameters.add(fields.getSalary());
            parameters.add(fields.getSalaryTo());
        }

        try{
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

        // Execute the query.
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and populate a list of EmployeeFields.
            ObservableList<EmployeeFields> employeeData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                EmployeeFields employee = new EmployeeFields();
                employee.setID(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("First_Name"));
                employee.setLastName(resultSet.getString("Last_Name"));
                employee.setCity(resultSet.getString("City"));
                employee.setStreet(resultSet.getString("Street"));
                employee.setEmail(resultSet.getString("Email_Address"));
                employee.setPhone(resultSet.getString("Phone_Number"));
                employee.setGender(resultSet.getString("Gender").charAt(0));
                employee.setSalary(resultSet.getInt("Salary"));
                employee.setHireDate(resultSet.getDate("Hire_Date").toLocalDate());
                employee.setBankInfo(resultSet.getString("Bank_Account_Information"));
                employee.setBirthDate(resultSet.getDate("Birthdate").toLocalDate());
                employee.setWarehouse(resultSet.getString("wname")); // Assuming you have joined with the warehouse table
                employee.setPassword(resultSet.getString("password"));
                employee.setChoiceRoll(resultSet.getString("role"));

                employeeData.add(employee);
            }
            employeeTable.setItems( employeeData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }



    public static void searchCustomer(CustomerFields fields, TableView<CustomerFields> customerTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();

        // Add conditions based on the provided input values.
        if (!fields.getName().isEmpty()) {
            sql.append(" AND cname LIKE ?");
            parameters.add("%" + fields.getName() + "%");
        }

        if (!fields.getContactInformation().isEmpty()) {
            sql.append(" AND contact_information LIKE ?");
            parameters.add("%" + fields.getContactInformation() + "%");
        }
        if (!fields.getAddress().isEmpty()) {
            sql.append(" AND address LIKE ?");
            parameters.add("%" + fields.getAddress() + "%");
        }
        if (fields.getCustomerID()!=-1) {
            sql.append(" AND customerid = ?");
            parameters.add(fields.getCustomerID());
        }

        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and populate a list of EmployeeFields.
            ObservableList<CustomerFields> customerData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                CustomerFields customer = new CustomerFields();
                customer.setCustomerID(resultSet.getInt("customerid"));
                customer.setAddress(resultSet.getString("address"));
                customer.setName(resultSet.getString("cname"));
                customer.setContactInformation(resultSet.getString("contact_information"));

                customerData.add(customer);
            }
            customerTable.setItems( customerData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }

    public static void searchSupplier(SupplierFields fields, TableView<SupplierFields> supplierTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM supplier WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();

        // Add conditions based on the provided input values.
        if (!fields.getName().isEmpty()) {
            sql.append(" AND company_name LIKE ?");
            parameters.add("%" + fields.getName() + "%");
        }

        if (!fields.getContactInformation().isEmpty()) {
            sql.append(" AND contact_information LIKE ?");
            parameters.add("%" + fields.getContactInformation() + "%");
        }
        if (!fields.getLocation().isEmpty()) {
            sql.append(" AND supplier_location LIKE ?");
            parameters.add("%" + fields.getLocation() + "%");
        }
        if (fields.getSupplierID()!=-1) {
            sql.append(" AND supplierid = ?");
            parameters.add(fields.getSupplierID());
        }

        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and populate a list of EmployeeFields.
            ObservableList<SupplierFields> supplierData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                SupplierFields suppleir = new SupplierFields();
                suppleir.setSupplierID(resultSet.getInt("supplierid"));
                suppleir.setLocation(resultSet.getString("supplier_location"));
                suppleir.setName(resultSet.getString("company_name"));
                suppleir.setContactInformation(resultSet.getString("contact_information"));

                supplierData.add(suppleir);
            }
            supplierTable.setItems( supplierData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }
    public static void searchWarehouse(WarehouseFields fields, TableView<WarehouseFields> warehouseTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM warehouset WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();

        // Add conditions based on the provided input values.
        if (!fields.getWname().isEmpty()) {
            sql.append(" AND wname LIKE ?");
            parameters.add("%" + fields.getWname() + "%");
        }

        if (!fields.getCity().isEmpty()) {
            sql.append(" AND city LIKE ?");
            parameters.add("%" + fields.getCity()+ "%");
        }
        if (!fields.getStreet().isEmpty()) {
            sql.append(" AND street LIKE ?");
            parameters.add("%" + fields.getStreet() + "%");
        }
        if (!fields.getStreet().isEmpty()) {
            sql.append(" AND street LIKE ?");
            parameters.add("%" + fields.getStreet() + "%");
        }

        if(fields.getCapacity()!=null){
            sql.append(" AND inventorycapacity BETWEEN ? AND ?");
            parameters.add(fields.getCapacity());
            parameters.add(fields.getCapacityTo());
        }

        if (fields.getWarehouseid()!=-1) {
            sql.append(" AND warehouseid = ?");
            parameters.add(fields.getWarehouseid());
        }

        if (!fields.getManager().isEmpty()) {
            String[] names = fields.getManager().split("\\s+");

            // Append conditions for both first and last names
            if (names.length > 0) {
                sql.append(" AND first_name LIKE ?");
                parameters.add("%" + names[0] + "%");
            }

            if (names.length > 1) {
                sql.append(" AND last_name LIKE ?");
                parameters.add("%" + names[1] + "%");
            }
        }

        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and populate a list of EmployeeFields.
            ObservableList<WarehouseFields> warehouseData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                WarehouseFields warehouse = new WarehouseFields();

                warehouse.setManager(resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
                warehouse.setCapacity(resultSet.getInt("inventorycapacity"));
                warehouse.setCity(resultSet.getString("city"));
                warehouse.setStreet(resultSet.getString("street"));
                warehouse.setWarehouseid(resultSet.getInt("warehouseid"));
                warehouse.setWname(resultSet.getString("wname"));

                warehouseData.add(warehouse);
            }
            warehouseTable.setItems( warehouseData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }


    public static void searchProduct(ProductFields fields, TableView<ProductFields> productTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();
        // Add conditions based on the provided input values.
        if (fields.getProductid()!=-1) {
            sql.append(" AND productid = ?");
            parameters.add(fields.getProductid());
        }


        if (!fields.getPname().isEmpty()) {
            sql.append(" AND pname LIKE ?");
            parameters.add("%" + fields.getPname()+ "%");
        }

        if (!fields.getBrand().isEmpty()) {
            sql.append(" AND brand LIKE ?");
            parameters.add("%" + fields.getBrand()+ "%");
        }
        if (!fields.getManufacturer().isEmpty()) {
            sql.append(" AND manufacturer LIKE ?");
            parameters.add("%" + fields.getManufacturer() + "%");
        }

        if(fields.getPrice()!=null){
            sql.append(" AND price BETWEEN ? AND ?");
            parameters.add(fields.getPrice());
            parameters.add(fields.getPriceTo());
        }

        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and populate a list of EmployeeFields.
            ObservableList<ProductFields> productData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ProductFields product = new ProductFields();

                // Parse the shelfLifeString and set it in your ProductFields instance
                product.setPname(resultSet.getString("pname"));
                product.setPrice(resultSet.getInt("price"));
                product.setBrand(resultSet.getString("brand"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setShelfLife(resultSet.getString("shelf_life"));
                product.setProductid(resultSet.getInt("productid"));

                productData.add(product);
                // Set the data to the TableView
            }
            productTable.setItems(productData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }

    public static void searchInventory(InventoryFields fields, TableView<InventoryFields> inventoryTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM inventoryt WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();
        // Add conditions based on the provided input values.
        if (fields.getInventoryid()!=-1) {
            sql.append(" AND inventoryid = ?");
            parameters.add(fields.getInventoryid());
        }

        if (!fields.getProduct().isEmpty()) {
            sql.append(" AND pname LIKE ?");
            parameters.add("%" + fields.getProduct()+ "%");
        }

        if (!fields.getWarehouse().isEmpty()) {
            sql.append(" AND warehouse_name LIKE ?");
            parameters.add("%" + fields.getWarehouse() + "%");
        }

        if(fields.getQuantity()!=null){
            sql.append(" AND quantity BETWEEN ? AND ?");
            parameters.add(fields.getQuantity());
            parameters.add(fields.getQuantityTo());
        }
        if(fields.getProductionDate()!=null){
            sql.append(" AND productiondate BETWEEN ? AND ?");
            parameters.add(fields.getProductionDate());
            parameters.add(fields.getProductionDateTo());
        }
        if(fields.getExpiryDate()!=null){
            sql.append(" AND expirydate BETWEEN ? AND ?");
            parameters.add(fields.getExpiryDate());
            parameters.add(fields.getExpiryDateTo());
        }

        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and populate a list of EmployeeFields.
            ObservableList<InventoryFields> InventoryData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                InventoryFields inventory = new InventoryFields();

                inventory.setInventoryid(resultSet.getInt("inventoryid"));
                inventory.setProduct(resultSet.getString("pname"));
                inventory.setProductionDate(resultSet.getDate("productiondate").toLocalDate());
                inventory.setExpiryDate(resultSet.getDate("expirydate").toLocalDate());
                inventory.setWarehouse(resultSet.getString("warehouse_name"));
                inventory.setQuantity(resultSet.getInt("quantity"));

                InventoryData.add(inventory);
            }


            inventoryTable.setItems(InventoryData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }

    public static void searchOrder(OrderFields fields, TableView<OrderFields> OrderTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM ordert WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();
        // Add conditions based on the provided input values.
        if (fields.getOrderID()!=-1) {
            sql.append(" AND orderid = ?");
            parameters.add(fields.getOrderID());
        }

        if (!fields.getCustomer().isEmpty()) {
            sql.append(" AND cname LIKE ?");
            parameters.add("%" + fields.getCustomer()+ "%");
        }

        if(fields.getTotal()!=null){
            sql.append(" AND total BETWEEN ? AND ?");
            parameters.add(fields.getTotal());
            parameters.add(fields.getTotalTo());
        }
        if(fields.getOrderdate()!=null){
            sql.append(" AND orderdate BETWEEN ? AND ?");
            parameters.add(fields.getOrderdate());
            parameters.add(fields.getOrderdateTo());
        }


        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and populate a list of EmployeeFields.
            ObservableList<OrderFields> OrderData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                OrderFields Order = new OrderFields();
                Order.setOrderID(resultSet.getInt("orderid"));

                // Use "Unknown" if customer name is null
                String customerName = resultSet.getString("cname");
                Order.setCustomer(customerName != null ? customerName : "Unknown");

                //Order.setCustomer(resultSet.getString("cname"));
                Order.setOrderdate(resultSet.getTimestamp("orderdate"));
                Order.setOrdernote(resultSet.getString("ordernotes"));
                Order.setTotal(resultSet.getDouble("total"));
                OrderData.add(Order);
            }


            OrderTable.setItems(OrderData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }

    public static void searchPOrder(POrderFields fields, TableView<POrderFields> POrderTable) {
        // SQL query for searching employees dynamically.
        StringBuilder sql = new StringBuilder("SELECT * FROM pordert WHERE 1=1");

        // List to store parameters for the PreparedStatement.
        List<Object> parameters = new ArrayList<>();
        // Add conditions based on the provided input values.
        if (fields.getPOrderID()!=-1) {
            sql.append(" AND purchaseorderid = ?");
            parameters.add(fields.getPOrderID());
        }

        if (!fields.getSupplier().isEmpty()) {
            sql.append(" AND company_name LIKE ?");
            parameters.add("%" + fields.getSupplier()+ "%");
        }

        if(fields.getTotal()!=null){
            sql.append(" AND totalprice BETWEEN ? AND ?");
            parameters.add(fields.getTotal());
            parameters.add(fields.getTotalTo());
        }
        if(fields.getOrderdate()!=null){
            sql.append(" AND order_date BETWEEN ? AND ?");
            parameters.add(fields.getOrderdate());
            parameters.add(fields.getOrderdateTo());
        }


        try{
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            // Set parameters for the PreparedStatement.
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            // Execute the query and retrieve the result set.
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and populate a list of EmployeeFields.
            ObservableList<POrderFields> POrderData = FXCollections.observableArrayList();


            while (resultSet.next()) {
                POrderFields Porder = new POrderFields();

                Porder.setPOrderID(resultSet.getInt("purchaseorderid"));
                Porder.setSupplier(resultSet.getString("company_name"));
                Porder.setOrderdate(resultSet.getTimestamp("order_date"));
                Porder.setTotal(resultSet.getDouble("totalprice"));
                POrderData.add(Porder);
            }


            POrderTable.setItems(POrderData);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions
        }
    }



}
