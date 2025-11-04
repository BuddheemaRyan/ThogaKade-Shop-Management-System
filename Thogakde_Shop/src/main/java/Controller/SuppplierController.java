package Controller;

import javafx.collections.ObservableList;
import model.dto.SupplierDTO;

import java.sql.*;

public class SuppplierController {
    public void addSupplier(String supplierId, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "INSERT INTO Supplier Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, supplierId);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, companyName);
            preparedStatement.setObject(4, address);
            preparedStatement.setObject(5, city);
            preparedStatement.setObject(6, province);
            preparedStatement.setObject(7, postalCode);
            preparedStatement.setObject(8, phone);
            preparedStatement.setObject(9, email);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSupplier(String supplierId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Supplier WHERE SupplierId=?");
            preparedStatement.setObject(1, supplierId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSupplier(String supplierId, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "UPDATE Supplier SET Name=?, CompanyName=?, Address=?, City=?, Province=?, PostalCode=?, Phone=?, Email=? WHERE SupplierId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, companyName);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, city);
            preparedStatement.setObject(5, province);
            preparedStatement.setObject(6, postalCode);
            preparedStatement.setObject(7, phone);
            preparedStatement.setObject(8, email);
            preparedStatement.setObject(9, supplierId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<SupplierDTO> getAllSuppliers() {
        ObservableList<SupplierDTO> supplierList = javafx.collections.FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Supplier");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                supplierList.add(new SupplierDTO(
                                resultSet.getString("SupplierId"),
                                resultSet.getString("Name"),
                                resultSet.getString("CompanyName"),
                                resultSet.getString("Address"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        )
                );

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }
}
