package com.example.thestore.Methods;

import com.example.thestore.TablesFields.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Refreash {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";

    public static void populateEmployeeTableView(TableView<EmployeeFields> employeeTable,boolean Admin,String Warehouse) {
        ObservableList<EmployeeFields> employeeData = FXCollections.observableArrayList();

        // TODO: Fetch data from the employee table in the database
        // and add it to the employeeData list

        // Example:
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            if(Admin) {
                 resultSet = statement.executeQuery("SELECT * FROM employeeT");
            }
            else{
                 resultSet = statement.executeQuery("SELECT * FROM employeet where wname='"+Warehouse+"'");

            }

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
                // Set the data to the TableView
            }
            employeeTable.setItems(employeeData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

    }
        public static void populateWarehouseTableView(TableView <WarehouseFields> warehouseTable) {


            ObservableList<WarehouseFields> warehouseData = FXCollections.observableArrayList();

            // TODO: Fetch data from the employee table in the database
            // and add it to the employeeData list

            // Example:
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM warehouseT");

                while (resultSet.next()) {
                    WarehouseFields warehouse = new WarehouseFields();

                    warehouse.setManager(resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
                    warehouse.setCapacity(resultSet.getInt("inventorycapacity"));
                    warehouse.setCity(resultSet.getString("city"));
                    warehouse.setStreet(resultSet.getString("street"));
                    warehouse.setWarehouseid(resultSet.getInt("warehouseid"));
                    warehouse.setWname(resultSet.getString("wname"));

                   warehouseData.add(warehouse);
                    // Set the data to the TableView
                }
                warehouseTable.setItems(warehouseData);

                // Close resources
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exceptions
            }


    }


    public static void populateProductTableView(TableView<ProductFields> productTable) {

        ObservableList<ProductFields> productData = FXCollections.observableArrayList();

        // TODO: Fetch data from the employee table in the database
        // and add it to the employeeData list

        // Example:
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

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
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }



    public static void populateSupplierTableView(TableView<SupplierFields> SupplierTable) {
        ObservableList<SupplierFields> SupplierData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM supplier");


            while (resultSet.next()) {
                SupplierFields Supplier = new SupplierFields();
                Supplier.setSupplierID(resultSet.getInt("supplierid"));
                Supplier.setName(resultSet.getString("company_name"));
                Supplier.setLocation(resultSet.getString("supplier_location"));
                Supplier.setContactInformation(resultSet.getString("contact_information"));
                SupplierData.add(Supplier);
            }

            SupplierTable.setItems(SupplierData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    public static void populateCustomerTableView(TableView<CustomerFields> CustomerTable) {
        ObservableList<CustomerFields> CustomerData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");


            while (resultSet.next()) {
                CustomerFields Customer = new CustomerFields();
                Customer.setCustomerID(resultSet.getInt("customerid"));
                Customer.setName(resultSet.getString("cname"));
                Customer.setAddress(resultSet.getString("address"));
                Customer.setContactInformation(resultSet.getString("contact_information"));
                CustomerData.add(Customer);
            }


            CustomerTable.setItems(CustomerData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void populateOrderTableView(TableView<OrderFields> OrderTable) {
        ObservableList<OrderFields> OrderData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ordert");


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
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void populatePOrderTableView(TableView<POrderFields> pOrderTable) {
        ObservableList<POrderFields> PorderData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pordert");


            while (resultSet.next()) {
                POrderFields Porder = new POrderFields();

                Porder.setPOrderID(resultSet.getInt("purchaseorderid"));
                Porder.setSupplier(resultSet.getString("company_name"));
                Porder.setOrderdate(resultSet.getTimestamp("order_date"));
                Porder.setTotal(resultSet.getDouble("totalprice"));
                PorderData.add(Porder);
            }


            pOrderTable.setItems(PorderData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void populateTransTableView(TableView<TransactionFields> TransTable) {
        ObservableList<TransactionFields> PorderData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM financialtransactions");


            while (resultSet.next()) {
                TransactionFields Trans = new TransactionFields();

                Trans.setTransID(resultSet.getInt("transactionid"));
                Trans.setTransType(resultSet.getString("transactiontype"));
                Trans.setTransAmount(resultSet.getDouble("amount"));
                Trans.setTransDate(resultSet.getDate("transactiondate").toLocalDate());
                Trans.setDescription(resultSet.getString("description"));

                PorderData.add(Trans);
            }
            TransTable.setItems(PorderData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void populateOrderDetailsTableView(TableView<OrderDetailsFields> DetailsTable,Integer ID) {
        ObservableList<OrderDetailsFields> DetailsData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM orderdetailt where orderid="+ID);


            while (resultSet.next()) {
                OrderDetailsFields Detail = new OrderDetailsFields();

                Detail.setExpiryDate(resultSet.getDate("expirydate").toLocalDate());
                Detail.setProductionDate(resultSet.getDate("productiondate").toLocalDate());
                Detail.setProduct((resultSet.getString("pname")));
                Detail.setWarhouse((resultSet.getString("warehouse_name")));
                Detail.setQuantity((resultSet.getInt("quantity")));

                DetailsData.add(Detail);
            }


            DetailsTable.setItems(DetailsData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }



    public static void populatePOrderDetailsTableView(TableView<OrderDetailsFields> DetailsTable,Integer ID) {
        ObservableList<OrderDetailsFields> DetailsData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM porderdetailt where purchaseorderid="+ID);


            while (resultSet.next()) {
                OrderDetailsFields Detail = new OrderDetailsFields();

                Detail.setExpiryDate(resultSet.getDate("expirydate").toLocalDate());
                Detail.setProductionDate(resultSet.getDate("productiondate").toLocalDate());
                Detail.setProduct((resultSet.getString("pname")));
                Detail.setWarhouse((resultSet.getString("warehouse_name")));
                Detail.setQuantity((resultSet.getInt("quantity")));

                DetailsData.add(Detail);
            }


            DetailsTable.setItems(DetailsData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    public static void populateInventoryTableView(TableView<InventoryFields> InventoryTable,boolean Admin,String Warehouse) {
        ObservableList<InventoryFields> InventoryData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet ;
            if(Admin) {
                resultSet = statement.executeQuery("SELECT * FROM inventoryt");
            }
            else{
                resultSet = statement.executeQuery("SELECT * FROM inventoryt where warehouse_name='"+Warehouse+"'");
            }

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


            InventoryTable.setItems(InventoryData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Compute sum quantities for each product
            Map<String, Integer> productQuantities = InventoryData.stream()
                    .collect(Collectors.groupingBy(InventoryFields::getProduct,
                            Collectors.summingInt(InventoryFields::getQuantity)));


            // Find the top 5 products with the highest quantities
            List<String> topProducts = productQuantities.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Create a new list to hold the aggregated data
            ObservableList<InventoryFields> aggregatedData = FXCollections.observableArrayList();

            // Aggregate data for top 5 products
            for (String productName : topProducts) {
                int sumQuantity = productQuantities.get(productName);
                aggregatedData.add(new InventoryFields(null, productName, null, null, null, sumQuantity));
            }

            // Aggregate data for other products
            int sumOtherQuantities = productQuantities.entrySet().stream()
                    .filter(entry -> !topProducts.contains(entry.getKey()))
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            aggregatedData.add(new InventoryFields(null, "Other", null, null, null, sumOtherQuantities));

            // Display the aggregated data

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (String product : topProducts) {
                int sumQuantity = productQuantities.get(product);
                pieChartData.add(new PieChart.Data(product, sumQuantity));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }



    }

    public static ObservableList<PieChart.Data> populatePieChart() {
        ObservableList<InventoryFields> InventoryData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet ;
                resultSet = statement.executeQuery("SELECT * FROM inventoryt");



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

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Compute sum quantities for each product
            Map<String, Integer> productQuantities = InventoryData.stream()
                    .collect(Collectors.groupingBy(InventoryFields::getProduct,
                            Collectors.summingInt(InventoryFields::getQuantity)));


            // Find the top 5 products with the highest quantities
            List<String> topProducts = productQuantities.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Create a new list to hold the aggregated data
            ObservableList<InventoryFields> aggregatedData = FXCollections.observableArrayList();

            // Aggregate data for top 5 products
            for (String productName : topProducts) {
                int sumQuantity = productQuantities.get(productName);
                aggregatedData.add(new InventoryFields(null, productName, null, null, null, sumQuantity));
            }

            // Aggregate data for other products
            int sumOtherQuantities = productQuantities.entrySet().stream()
                    .filter(entry -> !topProducts.contains(entry.getKey()))
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            aggregatedData.add(new InventoryFields(null, "Other", null, null, null, sumOtherQuantities));

            // Display the aggregated data

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (String product : topProducts) {
                int sumQuantity = productQuantities.get(product);
                pieChartData.add(new PieChart.Data(product, sumQuantity));
            }
            return pieChartData;

        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }


        return null;
    }

    public static ObservableList<PieChart.Data> populateWarehousePieChart() {
        ObservableList<InventoryFields> inventoryData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventoryt");

            while (resultSet.next()) {
                InventoryFields inventory = new InventoryFields();

                inventory.setInventoryid(resultSet.getInt("inventoryid"));
                inventory.setProduct(resultSet.getString("pname"));
                inventory.setProductionDate(resultSet.getDate("productiondate").toLocalDate());
                inventory.setExpiryDate(resultSet.getDate("expirydate").toLocalDate());
                inventory.setWarehouse(resultSet.getString("warehouse_name"));
                inventory.setQuantity(resultSet.getInt("quantity"));

                inventoryData.add(inventory);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Compute sum quantities for each warehouse
            Map<String, Integer> warehouseQuantities = inventoryData.stream()
                    .collect(Collectors.groupingBy(InventoryFields::getWarehouse,
                            Collectors.summingInt(InventoryFields::getQuantity)));

            // Create a new list to hold the aggregated data
            ObservableList<InventoryFields> aggregatedData = FXCollections.observableArrayList();

            // Aggregate data for each warehouse
            for (Map.Entry<String, Integer> entry : warehouseQuantities.entrySet()) {
                aggregatedData.add(new InventoryFields(null, null, entry.getKey(), null, null, entry.getValue()));
            }

            // Display the aggregated data
            System.out.println(aggregatedData);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : warehouseQuantities.entrySet()) {
                int sumQuantity = entry.getValue();
                pieChartData.add(new PieChart.Data(entry.getKey(), sumQuantity));
            }
            return pieChartData;

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return null;
    }
    public static ObservableList<PieChart.Data> populateCustomerPieChart() {

        ObservableList<OrderFields> OrderData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ordert");


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

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Compute sum totals for each customer
            Map<String, Double> customerTotals = OrderData.stream()
                    .collect(Collectors.groupingBy(OrderFields::getCustomer,
                            Collectors.summingDouble(OrderFields::getTotal)));

            // Find the top 5 customers with the highest totals
            List<String> topCustomers = customerTotals.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Create a new list to hold the aggregated data
            ObservableList<OrderFields> aggregatedData = FXCollections.observableArrayList();

            // Aggregate data for top 5 customers
            for (String customerName : topCustomers) {
                double sumTotal = customerTotals.get(customerName);
                aggregatedData.add(new OrderFields(null, customerName, null, sumTotal, null));
            }

            // Aggregate data for other customers
            double sumOtherTotals = customerTotals.entrySet().stream()
                    .filter(entry -> !topCustomers.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum();

            aggregatedData.add(new OrderFields(null, "Other", null, sumOtherTotals, null));


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (String customer : topCustomers) {
                double sumTotal = customerTotals.get(customer);
                pieChartData.add(new PieChart.Data(customer, sumTotal));
            }
            return pieChartData;

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return null;
    }
    public static ObservableList<PieChart.Data> populateSupplierPieChart() {

        ObservableList<POrderFields> OrderData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pordert");


            while (resultSet.next()) {
                POrderFields Order = new POrderFields();
                Order.setPOrderID(resultSet.getInt("purchaseorderid"));

                // Use "Unknown" if customer name is null
                String customerName = resultSet.getString("company_name");
                Order.setSupplier(customerName != null ? customerName : "Unknown");

                //Order.setCustomer(resultSet.getString("cname"));
                Order.setOrderdate(resultSet.getTimestamp("order_date"));
                Order.setTotal(resultSet.getDouble("totalprice"));
                OrderData.add(Order);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Compute sum totals for each customer
            Map<String, Double> supplierTotals = OrderData.stream()
                    .collect(Collectors.groupingBy(POrderFields::getSupplier,
                            Collectors.summingDouble(POrderFields::getTotal)));

            // Find the top 5 customers with the highest totals
            List<String> topSuppliers = supplierTotals.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Create a new list to hold the aggregated data
            ObservableList<POrderFields> aggregatedData = FXCollections.observableArrayList();

            // Aggregate data for top 5 customers
            for (String customerName : topSuppliers) {
                double sumTotal = supplierTotals.get(customerName);
                aggregatedData.add(new POrderFields(null, customerName, null , sumTotal));
            }

            // Aggregate data for other customers
            double sumOtherTotals = supplierTotals.entrySet().stream()
                    .filter(entry -> !topSuppliers.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum();

            aggregatedData.add(new POrderFields(null, "Other", null, sumOtherTotals));

            // Display the aggregated data
            //System.out.println(aggregatedData);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (String supplier : topSuppliers) {
                double sumTotal = supplierTotals.get(supplier);
                pieChartData.add(new PieChart.Data(supplier, sumTotal));
            }
            return pieChartData;

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return null;
    }


    public static void populateSmallTableView(TableView<SmallTableFields> InventoryTable,String Warehouse,String Product) {
        ObservableList<SmallTableFields> InventoryData = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet ;

                resultSet = statement.executeQuery("SELECT inventoryid,quantity,productiondate FROM inventoryt where warehouse_name='"+Warehouse+"' And pname='"+Product+"'");


            while (resultSet.next()) {
                SmallTableFields inventory = new SmallTableFields();

                inventory.setSmallID(resultSet.getInt("inventoryid"));
                inventory.setSmallDate(resultSet.getDate("productiondate").toLocalDate());
                inventory.setSmallQuantity(resultSet.getInt("quantity"));

                InventoryData.add(inventory);
            }


            InventoryTable.setItems(InventoryData);

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}
