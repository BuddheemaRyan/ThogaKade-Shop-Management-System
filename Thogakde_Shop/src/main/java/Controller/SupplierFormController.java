package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.SupplierDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    SupplierService supplierService = new SupplierController();
    ObservableList<SupplierDTO> supplierInfoArray = FXCollections.observableArrayList(

    );
    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_city;

    @FXML
    private TableColumn<?, ?> col_company_name;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_phone;

    @FXML
    private TableColumn<?, ?> col_postal_code;

    @FXML
    private TableColumn<?, ?> col_province;

    @FXML
    private TableColumn<?, ?> col_supplier_id;

    @FXML
    private TableView<SupplierDTO> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private ChoiceBox<String> txtProvince;

    @FXML
    private TextField txtSupId;

    @FXML
    void btnAdd(ActionEvent event) {
        String supplierId = txtSupId.getText();
        String name = txtName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getValue().toString();
        String postalCode = txtPostalCode.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();

        SupplierController supplierController = new SupplierController();
        supplierController.addSupplier(supplierId, name, companyName, address, city, province, postalCode, phone, email);

        loadSupplierDetails();
        clearFeilds();


    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

        SupplierController supplierController = new SupplierController();
        supplierController.deleteSupplier(txtSupId.getText());


        clearFeilds();
        loadSupplierDetails();

    }

    @FXML
    void btnEmployeeManage(ActionEvent event) {

    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        SupplierController supplierController = new SupplierController();
        supplierController.updateSupplier(txtSupId.getText(),
                txtName.getText(),
                txtCompanyName.getText(),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getValue().toString(),
                txtPostalCode.getText(),
                txtPhone.getText(),
                txtEmail.getText()
        );

        clearFeilds();
        loadSupplierDetails();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_supplier_id.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_company_name.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_province.setCellValueFactory(new PropertyValueFactory<>("province"));
        col_postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadSupplierDetails();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, supplierInfoDto, t1) -> {
            if (t1 != null) {
                txtSupId.setText(t1.getSupplierId());
                txtName.setText(t1.getName());
                txtCompanyName.setText(t1.getCompanyName());
                txtAddress.setText(t1.getAddress());
                txtCity.setText(t1.getCity());
                txtProvince.setValue(t1.getProvince());
                txtPostalCode.setText(t1.getPostalCode());
                txtPhone.setText(t1.getPhone());
                txtEmail.setText(t1.getEmail());
            }
        });

        txtProvince.getItems().addAll(
                "Central",
                "Eastern",
                "Northern",
                "North Central",
                "North Western",
                "Sabaragamuwa",
                "Southern",
                "Uva",
                "Western"
        );

        // default value
        txtProvince.setValue("Western");

    }

    private void loadSupplierDetails() {
        supplierInfoArray.clear();

        SupplierController supplierController = new SupplierController();


        tblSupplier.setItems(supplierController.getAllSuppliers());

    }

    public void clearFeilds() {
        txtSupId.clear();
        txtName.clear();
        txtCompanyName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.setValue(null);
        txtPostalCode.clear();
        txtPhone.clear();
        txtEmail.clear();
    }

}
