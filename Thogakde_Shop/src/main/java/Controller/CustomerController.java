package Controller;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

import java.sql.*;

public class CustomerController {
    public void addCustomer(String cusId, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, cusId);
            preparedStatement.setObject(2, title);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(String cusId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID=?");
            pstm.setObject(1, cusId);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(String cusId, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "UPDATE Customer SET  Title=?, Name=?, DateOfBirth=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, dob);
            preparedStatement.setObject(4, salary);
            preparedStatement.setObject(5, address);
            preparedStatement.setObject(6, city);
            preparedStatement.setObject(7, province);
            preparedStatement.setObject(8, postalCode);
            preparedStatement.setObject(9, cusId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<CustomerDTO> getAllCustomerDetails() {
        ObservableList<CustomerDTO> customerList = javafx.collections.FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerList.add(new CustomerDTO(
                                resultSet.getString("CustomerID"),
                                resultSet.getString("Title"),
                                resultSet.getString("Name"),
                                resultSet.getString("DateOfBirth"),
                                resultSet.getDouble("Salary"),
                                resultSet.getString("Address"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode")
                        )
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;

    }
}