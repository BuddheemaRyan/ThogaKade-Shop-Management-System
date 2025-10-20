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
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    ObservableList<ItemDTO> itemInfoArray = FXCollections.observableArrayList(
            new ItemDTO("I001", "Basmathi Rice 5kg", "Groceries", 120, 1650.00),
            new ItemDTO("I002", "Nescafe 100g", "Beverages", 80, 950.00),
            new ItemDTO("I003", "Sunlight Soap 120g", "Household", 200, 180.00),
            new ItemDTO("I004", "Maliban Marie 400g", "Snacks", 150, 420.00),
            new ItemDTO("I005", "Anchor Milk Powder 1kg", "Dairy", 90, 1250.00)
    );

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
        String category =txtCategory.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemInfoArrray = new ItemDTO(itemCode, description, category, qtyOnHand, unitPrice);
        itemInfoArray.add(itemInfoArrray);

    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {
        ItemDTO selectedInfo = tblItem.getSelectionModel().getSelectedItem();
        itemInfoArray.remove(selectedInfo);
        tblItem.refresh();
    }

    @FXML
    void btnEmployeeManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        ItemDTO itemInfo = tblItem.getSelectionModel().getSelectedItem();

        itemInfo.setItemCode(txtItemCode.getText());
        itemInfo.setCategory(txtCategory.getText());
        itemInfo.setDescription(txtDescription.getText());
        itemInfo.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        itemInfo.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));
        tblItem.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_item_code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_qty_on_hand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        col_unit_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.setItems(itemInfoArray);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, itemDTO, t1) -> {
            if (t1 != null){
           txtItemCode.setText(t1.getItemCode());
           txtCategory.setText(t1.getCategory());
           txtDescription.setText((t1.getDescription()));
           txtUnitPrice.setText(String.valueOf(t1.getUnitPrice()));
           txtQtyOnHand.setText(String.valueOf(t1.getQtyOnHand()));
            }
        });
    }
}
