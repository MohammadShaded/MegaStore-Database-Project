package com.example.thestore.Methods;

import com.example.thestore.AdminControllers.*;
import com.example.thestore.TablesFields.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Supplier;

public class FieldsReader {
    public static EmployeeFields checkEmployee(EmployeeControl employeeControl) {
        EmployeeFields result = new EmployeeFields();
        result.setFirstName(employeeControl.searchFierstName.getText());
        result.setLastName(employeeControl.searchLastName.getText());
        result.setWarehouse(employeeControl.searchWarehouse.getText());
        result.setPhone(employeeControl.searchPhone.getText());
        result.setEmail(employeeControl.searchEmail.getText());
        result.setCity(employeeControl.searchCity.getText());
        result.setStreet(employeeControl.searchStreet.getText());

        result.setChoiceRoll((String) employeeControl.choiceRoll.getValue());

        if (employeeControl.searchID.getText().isEmpty()) result.setID(-1);
        else result.setID(Integer.valueOf(employeeControl.searchID.getText()));

        if (employeeControl.DateCheck.isSelected()) {
            result.setHireDate(employeeControl.DateFrom.getValue());
            result.setHireTo(employeeControl.DateTo.getValue());
        } else result.setHireDate(null);

        if (employeeControl.spinnersCheck.isSelected()) {
            result.setSalary(employeeControl.salaryFrom.getValue());
            result.setSalaryTo(employeeControl.salaryTo.getValue());
        } else result.setSalary(null);

        if (employeeControl.GenderCheck.isSelected()) {
            result.setGender(employeeControl.Female.isSelected() ? 'F' : 'M');
        } else result.setGender('K');


        return result;

    }

    public static CustomerFields checkCustomer(CustomerControl customerControl) {
        CustomerFields result = new CustomerFields();

        result.setName(customerControl.searchName.getText());
        result.setAddress(customerControl.searchAddress.getText());
        result.setContactInformation(customerControl.searchInfo.getText());

        if (customerControl.searchID.getText().isEmpty()) result.setCustomerID(-1);
        else result.setCustomerID(Integer.valueOf(customerControl.searchID.getText()));

        return result;

    }


    public static SupplierFields checkSupplier(SupplierControl supplierControl) {
        SupplierFields result = new SupplierFields();


        result.setName(supplierControl.searchName.getText());
        result.setLocation(supplierControl.searchLocation.getText());
        result.setContactInformation(supplierControl.searchInfo.getText());

        if (supplierControl.searchID.getText().isEmpty()) result.setSupplierID(-1);
        else result.setSupplierID(Integer.valueOf(supplierControl.searchID.getText()));

        return result;

    }

    public static WarehouseFields checkWarehouse(WarehouseControl warehouseControl) {
        WarehouseFields result = new WarehouseFields();
        result.setWname(warehouseControl.searchName.getText());
        result.setCity(warehouseControl.searchCity.getText());
        result.setStreet(warehouseControl.searchStreet.getText());

        if (warehouseControl.spinnersCheck.isSelected()) {
            result.setCapacity(warehouseControl.capacityFrom.getValue());
            result.setCapacityTo(warehouseControl.capacityTo.getValue());
        }
        if (warehouseControl.searchID.getText().isEmpty())
            result.setWarehouseid(-1);
        else
            result.setWarehouseid(Integer.valueOf(warehouseControl.searchID.getText()));
        result.setManager(warehouseControl.searchManager.getValue());

        return result;

    }


    public static ProductFields checkProduct(ProductControl productControl) {
        ProductFields result = new ProductFields();
        result.setPname(productControl.searchName.getText());
        result.setBrand(productControl.searchBrand.getText());
        result.setManufacturer(productControl.choiceRoll.getValue());
        if (productControl.searchID.getText().isEmpty())
            result.setProductid(-1);
        else
            result.setProductid(Integer.valueOf(productControl.searchID.getText()));

        if (productControl.PriceCheck.isSelected()) {
            result.setPrice(productControl.PriceFrom.getValue());
            result.setPriceTo(productControl.PriceTo.getValue());
        } else result.setPrice(null);

        return result;

    }


    public static InventoryFields checkInventory(InventoryControl inventoryControl) {
        InventoryFields result = new InventoryFields();

        result.setProduct(inventoryControl.searchProduct.getValue());
        result.setWarehouse(inventoryControl.searchWarehouse.getValue());


        if (inventoryControl.spinnersCheck.isSelected()) {
            result.setQuantity(inventoryControl.quantityFrom.getValue());
            result.setQuantityTo(inventoryControl.quantityTo.getValue());
        } else result.setQuantity(null);

        if (inventoryControl.DateCheck.isSelected()) {
            result.setProductionDate(inventoryControl.DateFrom.getValue());
            result.setProductionDateTo(inventoryControl.DateTo.getValue());
        } else result.setProductionDate(null);

        if (inventoryControl.EDateCheck.isSelected()) {
            result.setExpiryDate(inventoryControl.EDateFrom.getValue());
            result.setExpiryDateTo(inventoryControl.EDateTo.getValue());
        } else result.setExpiryDate(null);

        if (inventoryControl.searchIID.getText().isEmpty())
            result.setInventoryid(-1);
        else
            result.setInventoryid(Integer.valueOf(inventoryControl.searchIID.getText()));

        return result;

    }

    public static OrderFields checkOrder(OrderControl orderControl) {
        OrderFields result = new OrderFields();

        result.setCustomer(orderControl.searchCustomer.getValue());
        if (orderControl.searchOID.getText().isEmpty())
            result.setOrderID(-1);
        else
            result.setOrderID(Integer.valueOf(orderControl.searchOID.getText()));


        if (orderControl.spinnersCheck.isSelected()) {
            result.setTotal(Double.valueOf(orderControl.totalFrom.getValue()));
            result.setTotalTo(Double.valueOf(orderControl.totalTo.getValue()));
        } else result.setTotal(null);


        if (orderControl.DateCheck.isSelected()) {
            result.setOrderdate(Timestamp.from(orderControl.DateFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            result.setOrderdateTo(Timestamp.from(orderControl.DateTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }

        return result;

    }

    public static POrderFields checkPOrder(POrderControl pOrderControl) {
        POrderFields result = new POrderFields();

        result.setSupplier(pOrderControl.searchSupplier.getValue());

        if (pOrderControl.searchPID.getText().isEmpty())
            result.setPOrderID(-1);
        else
            result.setPOrderID(Integer.valueOf(pOrderControl.searchPID.getText()));


        if (pOrderControl.PspinnersCheck.isSelected()) {
            result.setTotal(Double.valueOf(pOrderControl.PtotalFrom.getValue()));
            result.setTotalTo(Double.valueOf(pOrderControl.PtotalTo.getValue()));
        } else result.setTotal(null);


        if (pOrderControl.PDateCheck.isSelected()) {
            result.setOrderdate(Timestamp.from(pOrderControl.PDateFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            result.setOrderdateTo(Timestamp.from(pOrderControl.PDateTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }

        return result;

    }
}