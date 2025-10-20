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
import java.util.function.Supplier;

public class SupplierController implements Initializable {
    ObservableList<SupplierDTO> supplierInfoArray= FXCollections.observableArrayList(
            new SupplierDTO("S001", "Ruwan Perera", "Lanka Traders", "45 Main Street", "Colombo", "Western", "00100", "071-2345678", "info@lankatraders.lk"),
            new SupplierDTO("S002", "Nadeesha Silva", "Sunrise Distributors", "12 Temple Road", "Kandy", "Central", "20000", "071-3456789", "sales@sunrise.lk"),
            new SupplierDTO("S003", "Kamal Fernando", "Evergreen Supplies", "78 Galle Road", "Matara", "Southern", "81000", "071-4567890", "contact@evergreen.lk"),
            new SupplierDTO("S004", "Thilini Jayawardena", "Highland Imports", "23 Lake View", "Kurunegala", "North Western", "60000", "071-5678901", "support@highland.lk"),
            new SupplierDTO("S005", "Ashan Ranasinghe", "Royal Foods", "56 Beach Road", "Negombo", "Western", "11500", "071-6789012", "royalfoods@gmail.com")

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
    private TextField txtEMail;

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
        String supplierId=txtSupId.getText();
        String name=txtName.getText();
        String companyName=txtCompanyName.getText();
        String address=txtAddress.getText();
        String city=txtCity.getText();
        String province=txtProvince.getValue().toString();
        String postalCode=txtPostalCode.getText();
        String phone=txtPhone.getText();
        String email=txtEMail.getText();

        SupplierDTO supplierInfo=new SupplierDTO(supplierId,name,companyName,address,city,province,postalCode,phone,email);
        supplierInfoArray.add(supplierInfo);
    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {
        SupplierDTO selectedInfo= tblSupplier.getSelectionModel().getSelectedItem();
        supplierInfoArray.remove(selectedInfo);
        tblSupplier.refresh();
    }

    @FXML
    void btnEmployeeManage(ActionEvent event) {

    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        SupplierDTO supplierInfo=tblSupplier.getSelectionModel().getSelectedItem();

        supplierInfo.setSupplierId(txtSupId.getText());
        supplierInfo.setName(txtName.getText());
        supplierInfo.setAddress(txtAddress.getText());
        supplierInfo.setCity(txtCity.getText());
        supplierInfo.setEmail(txtEMail.getText());
        supplierInfo.setPhone(txtPhone.getText());
        supplierInfo.setCompanyName(txtCompanyName.getText());
        supplierInfo.setPostalCode(txtPostalCode.getText());
        supplierInfo.setProvince(txtProvince.getValue().toString());

        tblSupplier.refresh();
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

        tblSupplier.setItems(supplierInfoArray);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, supplierInfoDto, t1) -> {
            if (t1!=null){
                txtSupId.setText(t1.getSupplierId());
                txtName.setText(t1.getName());
                txtCompanyName.setText(t1.getCompanyName());
                txtAddress.setText(t1.getAddress());
                txtCity.setText(t1.getCity());
                txtProvince.setValue(t1.getProvince());
                txtPostalCode.setText(t1.getPostalCode());
                txtPhone.setText(t1.getPhone());
                txtEMail.setText(t1.getEmail());
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
}
