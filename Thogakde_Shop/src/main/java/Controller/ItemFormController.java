package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.ItemDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ObservableList<ItemDTO> itemInfoArray = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> col_category;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TableColumn<?, ?> col_item_code;

    @FXML
    private TableColumn<?, ?> col_qty_on_hand;

    @FXML
    private TableColumn<?, ?> col_unit_price;

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAdd(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String category = txtCategory.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemController itemController = new ItemController();
        itemController.addItem(itemCode, description, category, qtyOnHand, unitPrice);

        loadItemDetails();
        clearFields();


    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

        ItemController itemController = new ItemController();
        itemController.deleteItem(txtItemCode.getText());

        clearFields();
        loadItemDetails();
    }

    @FXML
    void btnEmployeeManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

        ItemController itemController = new ItemController();
        itemController.updateItem(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtCategory.getText(),
                Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );
        loadItemDetails();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_item_code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_qty_on_hand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        col_unit_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        loadItemDetails();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, itemDTO, t1) -> {
            if (t1 != null) {
                txtItemCode.setText(t1.getItemCode());
                txtCategory.setText(t1.getCategory());
                txtDescription.setText((t1.getDescription()));
                txtUnitPrice.setText(String.valueOf(t1.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(t1.getQtyOnHand()));
            }
        });

    }

    private void loadItemDetails() {
        itemInfoArray.clear();
        ItemController itemController = new ItemController();
        tblItem.setItems(itemController.getALlItemsDetails());
    }

    public void clearFields() {
        txtItemCode.clear();
        txtDescription.clear();
        txtCategory.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }
}
