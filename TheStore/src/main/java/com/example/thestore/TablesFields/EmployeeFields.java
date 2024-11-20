package com.example.thestore.TablesFields;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeFields {

    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String Email;
    private String warehouse;
    private LocalDate HireDate ;
    private Integer salary;
    private Character gender;
    private Integer ID;
    private String Phone;
    private String choiceRoll;
    private LocalDate BirthDate ;
    private String BankInfo;
    private String password;



    private LocalDate HireTo;
    private Integer salaryTo;

    public LocalDate getHireTo() {
        return HireTo;
    }

    public void setHireTo(LocalDate hireTo) {
        HireTo = hireTo;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getHireDate() {
        return HireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        HireDate = hireDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }

    public String getChoiceRoll() {
        return choiceRoll;
    }

    public void setChoiceRoll(String choiceRoll) {
        this.choiceRoll = choiceRoll;
    }

    public String getBankInfo() {
        return BankInfo;
    }

    public void setBankInfo(String bankInfo) {
        BankInfo = bankInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmployeeFields{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", Email='" + Email + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", HireDate=" + HireDate +
                ", salary=" + salary +
                ", gender=" + gender +
                ", ID=" + ID +
                ", Phone='" + Phone + '\'' +
                ", choiceRoll='" + choiceRoll + '\'' +
                ", BirthDate=" + BirthDate +
                ", BankInfo='" + BankInfo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public EmployeeFields(){}
    public EmployeeFields(String firstName, String lastName, String city, String street, String email, String warehouse, LocalDate hireDate, Integer salary, char gender, Integer ID, String phone, String choiceRoll, LocalDate birthDate, String bankInfo, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        Email = email;
        this.warehouse = warehouse;
        HireDate = hireDate;
        this.salary = salary;
        this.gender = gender;
        this.ID = ID;
        Phone = phone;
        this.choiceRoll = choiceRoll;
        BirthDate = birthDate;
        BankInfo = bankInfo;
        this.password = password;
    }
}
