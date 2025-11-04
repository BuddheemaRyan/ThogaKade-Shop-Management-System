package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    ObservableList<CustomerDTO> customerInfoArray = FXCollections.observableArrayList();


    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_city;

    @FXML
    private TableColumn<?, ?> col_cust_id;

    @FXML
    private TableColumn<?, ?> col_dob;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_postal_code;

    @FXML
    private TableColumn<?, ?> col_province;

    @FXML
    private TableColumn<?, ?> col_salary;

    @FXML
    private TableColumn<?, ?> col_title;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustId;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private ChoiceBox<String> txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnAdd(ActionEvent event) {
        String cusId = txtCustId.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getValue().toString();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getValue().toString();
        String postalCode = txtPostalCode.getText();

        CustomerController customerController = new CustomerController();
        customerController.addCustomer(cusId, title, name, dob, salary, address, city, province, postalCode);

        loadCustomerDetails();
        clearFields();


    }

    @FXML
    void btnDelete(ActionEvent event) {

        CustomerController customerController = new CustomerController();
        customerController.deleteCustomer(txtCustId.getText());
        clearFields();
        loadCustomerDetails();
    }

    @FXML
    public void btnEmployeeManage(ActionEvent event) {
        Stage stage1 = new Stage();
        try {
            stage1.setScene(new Scene(FXMLLoader.<Parent>load(getClass().getResource("/view/employee_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.show();
    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

        CustomerController customerController = new CustomerController();
        customerController.updateCustomer(txtCustId.getText(), txtTitle.getText(), txtName.getText(), txtDOB.getValue().toString(), Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(), txtProvince.getValue().toString(), txtPostalCode.getText());
        clearFields();
        loadCustomerDetails();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_cust_id.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_province.setCellValueFactory(new PropertyValueFactory<>("province"));
        col_postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCustomerDetails();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCustId.setText(newValue.getCusId());
                txtTitle.setText(newValue.getTitle());
                txtName.setText(newValue.getName());
                txtDOB.setValue(java.time.LocalDate.parse(newValue.getDob()));
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setValue(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        });
        txtProvince.getItems().addAll(
                "Southern",
                "Northern",
                "Eastern",
                "Western",
                "Central"

        );
        txtProvince.setValue("Southern");
    }


    //--------------------All Methods--------------------------------

    private void loadCustomerDetails() {
        customerInfoArray.clear();

        CustomerController customerController = new CustomerController();

        tblCustomer.setItems(customerController.getAllCustomerDetails());

    }

    public void clearFields() {
        txtCustId.clear();
        txtTitle.clear();
        txtName.clear();
        txtDOB.setValue(null);
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.setValue("Southern");
        txtPostalCode.clear();
    }

}
