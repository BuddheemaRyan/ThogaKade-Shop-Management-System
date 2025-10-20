package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    ObservableList<CustomerDTO> customerInfoArray = FXCollections.observableArrayList(
            new CustomerDTO("C001", "Mr.", "John Smith", "1985-03-14", 65000.00, "123 Elm Street", "New York", "NY", "10001"),
            new CustomerDTO("C002", "Ms.", "Sarah Johnson", "1990-07-22", 72000.00, "456 Pine Avenue", "Los Angeles", "CA", "90012"),
            new CustomerDTO("C003", "Dr.", "Michael Lee", "1978-11-03", 95000.00, "789 Oak Road", "Chicago", "IL", "60616"),
            new CustomerDTO("C004", "Mrs.", "Emily Davis", "1992-02-09", 58000.00, "321 Maple Lane", "Houston", "TX", "77002"),
            new CustomerDTO("C005", "Mr.", "Robert Wilson", "1980-09-30", 87000.00, "654 Birch Street", "Seattle", "WA", "98101")
    );

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
        String cusId=txtCustId.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getValue().toString();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province =txtProvince.getValue().toString();
        String postalCode = txtPostalCode.getText();

        CustomerDTO customer = new CustomerDTO(cusId,title,name,dob,salary,address,city,province,postalCode);
        customerInfoArray.add(customer);
    }

    @FXML
    void btnDelete(ActionEvent event) {
        CustomerDTO selectedInfo= tblCustomer.getSelectionModel().getSelectedItem();
        customerInfoArray.remove(selectedInfo);
        tblCustomer.refresh();
    }

    @FXML
    void btnEmployeeManage(ActionEvent event) {

    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        CustomerDTO customer = tblCustomer.getSelectionModel().getSelectedItem();

        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setCity(txtCity.getText());
        customer.setDob(txtDOB.getValue().toString());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));
        customer.setCusId(txtCustId.getText());
        customer.setProvince(txtProvince.getValue().toString());
        customer.setTitle(txtTitle.getText());
        customer.setPostalCode(txtPostalCode.getText());
        tblCustomer.refresh();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_cust_id.setCellValueFactory(new PropertyValueFactory<>("custId"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_province.setCellValueFactory(new PropertyValueFactory<>("province"));
        col_postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomer.setItems(customerInfoArray);

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
                "NY",
                "CA",
                "IL",
                "TX",
                "WA"
        );
        txtProvince.setValue("NY");
    }
}
