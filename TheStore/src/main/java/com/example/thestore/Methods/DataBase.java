package com.example.thestore.Methods;

import com.example.thestore.TablesFields.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DataBase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";


    public static void PinsertEmployee(EmployeeFields newEmp) {


        String sql = "INSERT INTO employee (EmployeeID, First_Name, Last_Name, City, Street, Email_Address, " +
                "Phone_Number, Gender, Salary, Hire_Date, Bank_Account_Information, Birthdate,warehouseid, " +
                "password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";

        try  {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            Integer ID =getWarehouseId(newEmp.getWarehouse());


            // Set parameter values
            pstmt.setInt(1, newEmp.getID());
            pstmt.setString(2, newEmp.getFirstName());
            pstmt.setString(3, newEmp.getLastName());
            pstmt.setString(4, newEmp.getCity());
            pstmt.setString(5, newEmp.getStreet());
            pstmt.setString(6, newEmp.getEmail());
            pstmt.setString(7, newEmp.getPhone());
            pstmt.setString(8, String.valueOf(newEmp.getGender()));
            pstmt.setInt(9, newEmp.getSalary());
            pstmt.setDate(10, java.sql.Date.valueOf(newEmp.getHireDate()));
            pstmt.setString(11, newEmp.getBankInfo());
            pstmt.setDate(12, java.sql.Date.valueOf(newEmp.getBirthDate()));
            pstmt.setObject(13, ID==-1?null:ID);
            pstmt.setString(14, newEmp.getPassword());
            pstmt.setString(15, newEmp.getChoiceRoll());

            String ss =newEmp.getWarehouse();

            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static void PinsertPOrder(POrderFields ONew) {


        String sql = "INSERT INTO \"purchase_order\" (supplierid ,purchaseorderid,  order_date) VALUES ( ?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer SupplierID = getSupplierId(ONew.getSupplier());
            // Set parameter values

            pstmt.setInt(1, SupplierID);
            pstmt.setInt(2, ONew.getPOrderID());
            pstmt.setTimestamp(3, ONew.getOrderdate());
            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static void PinsertCustomer(CustomerFields CusNew) {


        String sql = "INSERT INTO customers (CustomerID, cname, Address, contact_information) VALUES (?, ?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set parameter values
            pstmt.setInt(1, CusNew.getCustomerID());
            pstmt.setString(2, CusNew.getName());
            pstmt.setString(3, CusNew.getContactInformation());
            pstmt.setString(4, CusNew.getAddress());


            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    public static void PinsertSupplier(SupplierFields SupNew) {


        String sql = "INSERT INTO supplier (supplierID, company_name, supplier_location, contact_information) VALUES (?, ?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set parameter values
            pstmt.setInt(1, SupNew.getSupplierID());
            pstmt.setString(2, SupNew.getName());
            pstmt.setString(3, SupNew.getContactInformation());
            pstmt.setString(4, SupNew.getLocation());


            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    public static void PinsertTransaction(TransactionFields TransNew) {


        String sql = "INSERT INTO financialtransactions (transactionid, transactiontype, amount, transactiondate, description) VALUES (?, ?, ?, ?,?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set parameter values
            pstmt.setInt(1, TransNew.getTransID());
            pstmt.setString(2, TransNew.getTransType());
            pstmt.setDouble(3, TransNew.getTransAmount());
            pstmt.setDate(4, Date.valueOf(TransNew.getTransDate()));
            pstmt.setString(5, TransNew.getDescription());


            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    public static void PinsertOrder(OrderFields ONew) {


        String sql = "INSERT INTO \"order\" (orderid, customerid, orderdate, ordernotes) VALUES (?, ?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer CustomerID = getCustomerId(ONew.getCustomer());
            // Set parameter values
            pstmt.setInt(1, ONew.getOrderID());
            pstmt.setInt(2, CustomerID);
            pstmt.setTimestamp(3, ONew.getOrderdate());
            pstmt.setString(4, ONew.getOrdernote());
            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void PinsertOrderDetils(OrderDetailsFields NewDetail,Integer orderID) {


        String sql = "INSERT INTO \"orderdetail\" (orderdetailid,orderid ,productid,quantity ,warehouseid, productiondate) VALUES ( ?,?,?,?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer orderdetailID = getMaxOrderDetailId()+1;
            Integer productID=getProductId(NewDetail.getProduct());
            Integer warehouseID=getWarehouseId(NewDetail.getWarhouse());

            // Set parameter values

            pstmt.setInt(1, orderdetailID);
            pstmt.setInt(2, orderID);
            pstmt.setInt(3, productID);
            pstmt.setInt(4, NewDetail.getQuantity());
            pstmt.setInt(5, warehouseID);
            pstmt.setDate(6,java.sql.Date.valueOf( NewDetail.getProductionDate()));
            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void PinsertPOrderDetils(OrderDetailsFields NewDetail,Integer PorderID) {


        String sql = "INSERT INTO \"porderdetail\" (porderdetailid,purchaseorderid ,productid,quantity ,warehouseid, productiondate) VALUES ( ?,?,?,?, ?, ?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer PorderdetailID = getMaxPOrderDetailId()+1;
            Integer productID=getProductId(NewDetail.getProduct());
            Integer warehouseID=getWarehouseId(NewDetail.getWarhouse());

            // Set parameter values

            pstmt.setInt(1, PorderdetailID);
            pstmt.setInt(2, PorderID);
            pstmt.setInt(3, productID);
            pstmt.setInt(4, NewDetail.getQuantity());
            pstmt.setInt(5, warehouseID);
            pstmt.setDate(6,java.sql.Date.valueOf( NewDetail.getProductionDate()));
            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void PinsertProduct(ProductFields newProduct) {


        String sql = "INSERT INTO \"product\" (productid, pname, price, manufacturer,shelf_life,brand) VALUES (?, ?, ?, ?,?,?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            // Set parameter values
            pstmt.setInt(1, newProduct.getProductid());
            pstmt.setString(2, newProduct.getPname());
            pstmt.setInt(3, newProduct.getPrice());
            pstmt.setString(4, newProduct.getManufacturer());
            pstmt.setObject(5, newProduct.getShelfLife(), java.sql.Types.OTHER); // Use java.sql.Types.OTHER for PostgreSQL interval type
            pstmt.setString(6, newProduct.getBrand());

            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static void PinsertWarehouse(WarehouseFields newWarehouse) {


        String sql = "INSERT INTO \"warehouse\" (warehouseid, wname, city, street,inventorycapacity, manager) VALUES (?, ?, ?, ?,?,?)";

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer managerID =getManagerId(newWarehouse.getManager());
            // Set parameter values
            pstmt.setInt(1, newWarehouse.getWarehouseid());
            pstmt.setString(2, newWarehouse.getWname());
            pstmt.setString(3, newWarehouse.getCity());
            pstmt.setString(4, newWarehouse.getStreet());
            pstmt.setInt(5, newWarehouse.getCapacity());
            pstmt.setInt(6, managerID);
            // Execute the update
            pstmt.executeUpdate();
            updateWarehouseId(managerID,newWarehouse.getWarehouseid());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void PinsertInventory(InventoryFields newInventory) {


        String sql = "INSERT INTO \"inventory\" (inventoryid, productid, quantity, productiondate,warehouseid ) VALUES (?, ?, ?, ?,?)";

        int warehouseID=getWarehouseId(newInventory.getWarehouse());
        int productID=getProductId(newInventory.getProduct());
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set parameter values
            pstmt.setInt(1, newInventory.getInventoryid());
            pstmt.setInt(2,productID);
            pstmt.setInt(3, newInventory.getQuantity());
            pstmt.setInt(3, newInventory.getQuantity());
            pstmt.setDate(4, java.sql.Date.valueOf(newInventory.getProductionDate()));
            pstmt.setInt(5, warehouseID);
            // Execute the update
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static List<String> fetchCustomerNamesFromDatabase() {
        List<String> CustomerNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT cname FROM customers";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    CustomerNames.add(resultSet.getString("cname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CustomerNames;
    }

    public static List<String> fetchProductNamesFromDatabase() {
        List<String> ProductNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT pname,brand FROM product";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductNames.add(resultSet.getString("pname")+", "+resultSet.getString("brand"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ProductNames;
    }



    public static List<String> fetchSupplierNamesFromDatabase() {
        List<String> SupplierNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT company_name FROM supplier";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    SupplierNames.add(resultSet.getString("company_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return SupplierNames;
    }


    public static List<String> fetchWarehouseNamesFromDatabase() {
        List<String> warehouseNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT wname FROM warehouse";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    warehouseNames.add(resultSet.getString("wname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouseNames;
    }
    public static EmployeeFields fetchemailFromDatabase(String email) {
        EmployeeFields emails = new EmployeeFields();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT first_name, last_name, email_address, role, password,wname FROM employeet WHERE email_address = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        emails.setFirstName(resultSet.getString("first_name"));
                        emails.setLastName(resultSet.getString("last_name"));
                        emails.setEmail(resultSet.getString("email_address"));
                        emails.setChoiceRoll(resultSet.getString("role"));
                        emails.setPassword(resultSet.getString("password"));
                        emails.setWarehouse(resultSet.getString("wname"));

                        // You may add more fields as needed
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return emails;
    }
    public static void updateWarehouse(WarehouseFields settings, int warehouseID) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            // Create the SQL update query
            String updateQuery = "UPDATE warehouse SET wname = ?, inventorycapacity = ?, street = ?, city = ? WHERE warehouseid = ?";
            preparedStatement = connection.prepareStatement(updateQuery);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, settings.getWname());
            preparedStatement.setInt(2, settings.getCapacity());
            preparedStatement.setString(3, settings.getStreet());
            preparedStatement.setString(4, settings.getCity());
            preparedStatement.setInt(5, warehouseID);

            // Execute the update query
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }

    public static WarehouseFields getWarehouseSettings(String WareName) {
        WarehouseFields warehouseFields = new WarehouseFields();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT wname,warehouseid, city, street, inventorycapacity FROM warehouse WHERE wname = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, WareName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        warehouseFields.setWarehouseid(resultSet.getInt("warehouseid"));
                        warehouseFields.setCity(resultSet.getString("city"));
                        warehouseFields.setStreet(resultSet.getString("street"));
                        warehouseFields.setCapacity(resultSet.getInt("inventorycapacity"));
                        warehouseFields.setWname(resultSet.getString("wname"));
                        warehouseFields.setWarehouseid(getWarehouseId(warehouseFields.getWname()));
                        // You may add more fields as needed
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return warehouseFields;
    }


    public static List<String> fetchManagerNamesFromDatabase(boolean addingFlag) {
        List<String> warehouseNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT first_name, last_name FROM employee WHERE role = 'Manager'";
            if(addingFlag)query+=" AND warehouseid IS NULL";
            //String query = "SELECT first_name,last_name FROM employee where role='Manager' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    warehouseNames.add(resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return warehouseNames;
    }

    public static int getMaxOrderDetailId() {
        // SQL query
        String sql = "SELECT MAX(orderdetailid) AS max_orderdetailid FROM orderdetail";

        try (
                // Establishing a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                // Creating a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Executing the query and getting the result set
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                // Retrieving the result as an integer
                return resultSet.getInt("max_orderdetailid");
            } else {
                System.out.println("No results found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return a default value or throw an exception based on your requirements
        return -1;
    }

    public static int getMaxPOrderDetailId() {
        // SQL query
        String sql = "SELECT MAX(porderdetailid) AS max_orderdetailid FROM porderdetail";

        try (
                // Establishing a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                // Creating a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Executing the query and getting the result set
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                // Retrieving the result as an integer
                return resultSet.getInt("max_orderdetailid");
            } else {
                System.out.println("No results found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return a default value or throw an exception based on your requirements
        return -1;
    }


    private static int getCustomerId(String cname) {
        int customerId = -1;

        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query = "SELECT customerid FROM customers WHERE cname = ?";

            PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, cname);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                customerId = resultSet.getInt("customerid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return customerId;
    }

    public static int getSupplierId(String company_name) {
        int supplierId = -1;

        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query = "SELECT supplierid FROM supplier WHERE company_name = ?";

            PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, company_name);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                supplierId = resultSet.getInt("supplierid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return supplierId;
    }



    // Method to get warehouseid based on wname
    public static int getWarehouseId(String wname) {
        int warehouseId = -1;

        try{
        DriverManager.registerDriver(new org.postgresql.Driver());
        String connInfo = "jdbc:postgresql://localhost:5432/my_one";
        Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        String query = "SELECT warehouseid FROM warehouse WHERE wname = ?";

        PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, wname);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                warehouseId = resultSet.getInt("warehouseid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return warehouseId;
    }


    public static int getManagerId(String fullName) {
        int managerId = -1;

        String[] name = fullName.split(" ");

        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query = "SELECT employeeid FROM employee WHERE first_name = ? And last_name = ?";

            PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, name[0]);
            pstmt.setString(2, name[1]);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                managerId = resultSet.getInt("employeeid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return managerId;
    }




    public static void updateWarehouseId( int employeeId,   int ID) {

        String updateQuery = "UPDATE employee SET warehouseid = ? WHERE employeeid = ?";
          try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(updateQuery)) {

            // Set parameter values
            pstmt.setObject(1, ID);
            pstmt.setInt(2, employeeId);

            // Execute the update
            pstmt.executeUpdate();

            System.out.println("Warehouseid updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static String getUserName(String Email) {

       String UserName ="";



        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            //String query = "SELECT productid FROM product WHERE pname = ? And brand = ?";
            String query = "SELECT first_name, last_name FROM employee WHERE email_address=?";

            PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, Email);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                UserName=resultSet.getString("first_name")+" "+resultSet.getString("last_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return UserName;

    }

    public static int getProductId(String fullName) {
        int productID = -1;

        String[] name = fullName.split(",\\s*");


        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query = "SELECT productid FROM product WHERE pname = ? And brand = ?";

            PreparedStatement pstmt = con.prepareStatement(query) ;
            pstmt.setString(1, name[0]);
            pstmt.setString(2, name[1]);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                productID = resultSet.getInt("productid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return productID;
    }

    public static int[] shelfParser(String shelfLife){

// Split the string into parts
        String[] parts = shelfLife.split("\\s+");

        // Variables to store extracted values
        int shelf[]=new int[3];
        shelf[0]=shelf[1]=shelf[2]=0;
        // Iterate through parts and extract values
        for (int i = 0; i < parts.length - 1; i += 2) {
            int value = Integer.parseInt(parts[i]);
            String unit = parts[i + 1].toLowerCase();

            switch (unit) {
                case "day":
                case "days":
                    shelf[0] += value;
                    break;
                case "mon":
                case "mons":
                    shelf[1] += value;
                    break;
                case "year":
                case "years":
                    shelf[2] += value;
                    break;
            }

        }

    return shelf;

    }
}

