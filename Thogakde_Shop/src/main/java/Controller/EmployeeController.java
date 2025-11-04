package Controller;

import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import javafx.collections.ObservableList;
import model.dto.EmployeeDTO;

import javax.xml.namespace.QName;
import java.sql.*;

public class EmployeeController {
    public void addEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, employeeId);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,nic);
            preparedStatement.setObject(4,dob);
            preparedStatement.setObject(5,position);
            preparedStatement.setObject(6,salary);
            preparedStatement.setObject(7,contactNumber);
            preparedStatement.setObject(8,address);
            preparedStatement.setObject(9,joinedDate);
            preparedStatement.setObject(10,status);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(String employeeId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Employee WHERE EmployeeID=?");
            pstm.setObject(1,employeeId);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmployee(String name, String employeeId, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "UPDATE Employee SET Name=?, NIC=?, DateOfBirth=?, Position=?, Salary=?, ContactNumber=?, Address=?, JoinedDate=?, Status=? WHERE EmployeeID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, nic);
            preparedStatement.setObject(3, dob);
            preparedStatement.setObject(4, position);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, contactNumber);
            preparedStatement.setObject(7, address);
            preparedStatement.setObject(8, joinedDate);
            preparedStatement.setObject(9, status);
            preparedStatement.setObject(10, employeeId);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<EmployeeDTO> getAllEmployees(){
        ObservableList<EmployeeDTO> employeeList = javafx.collections.FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new EmployeeDTO(

                        // column name pass
                        resultSet.getString("EmployeeID"),
                        resultSet.getString("Name"),
                        resultSet.getString("NIC"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Position"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("ContactNumber"),
                        resultSet.getString("Address"),
                        resultSet.getString("JoinedDate"),
                        resultSet.getString("Status")
                        )
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}
