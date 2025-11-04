package Controller;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import java.sql.*;

public class ItemController {
    public void addItem(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            String sql = "INSERT INTO Item VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, itemCode);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, category);
            preparedStatement.setObject(4, qtyOnHand);
            preparedStatement.setObject(5, unitPrice);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateItem(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            String sql = "UPDATE Item SET Description=?, Category=?, QtyOnHand=?, UnitPrice=? WHERE ItemCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, description);
            preparedStatement.setObject(2, category);
            preparedStatement.setObject(3, qtyOnHand);
            preparedStatement.setObject(4, unitPrice);
            preparedStatement.setObject(5, itemCode);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(String itemCode) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Item WHERE ItemCode=?");
            preparedStatement.setObject(1,itemCode);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<ItemDTO> getALlItemsDetails(){
        ObservableList<ItemDTO> itemList = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               itemList.add(new ItemDTO (
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("Category"),
                        resultSet.getInt("QtyOnHand"),
                        resultSet.getDouble("UnitPrice")
                       )
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
