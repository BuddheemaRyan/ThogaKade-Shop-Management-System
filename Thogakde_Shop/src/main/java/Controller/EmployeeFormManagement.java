package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.EmployeeDTO;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeFormManagement implements Initializable {
    EmployeeService employeeService = new EmployeeController();
    ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList();


    @FXML
    private TableColumn<?, ?> col_DOB;

    @FXML
    private TableColumn<?, ?> col_NIC;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_contact_no;

    @FXML
    private TableColumn<?, ?> col_emp_id;

    @FXML
    private TableColumn<?, ?> col_joined_date;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_position;

    @FXML
    private TableColumn<?, ?> col_salary;

    @FXML
    private TableColumn<?, ?> col_status;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtEmpId;

    @FXML
    private DatePicker txtJoinedDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtStatus;

    @FXML
    void btnAdd(ActionEvent event) {
        String employeeId = txtEmpId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = txtDOB.getValue().toString();
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactNumber = txtContactNo.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinedDate.getValue().toString();
        String status = txtStatus.getText();

        EmployeeController employeeController = new EmployeeController();
        employeeController.addEmployee(employeeId, name, nic, dob, position, salary, contactNumber, address, joinedDate, status);

        loadEmployeeDetails();
        clearFields();
    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.deleteEmployee(txtEmpId.getText());
        clearFields();
        loadEmployeeDetails();
    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.updateEmployee(txtName.getText(), txtEmpId.getText(), txtNic.getText(), txtDOB.getValue().toString(), txtPosition.getText(),
                Double.parseDouble(txtSalary.getText()), txtContactNo.getText(), txtAddress.getText(),txtJoinedDate.getValue().toString(), txtStatus.getText());

        clearFields();
        loadEmployeeDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_emp_id.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_NIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_DOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_contact_no.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_joined_date.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadEmployeeDetails();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, employeeInfoDto, t1) -> {

            if (t1 != null) {
                txtEmpId.setText(t1.getEmployeeId());
                txtName.setText(t1.getName());
                txtNic.setText(t1.getNic());
                txtDOB.setValue(LocalDate.parse(t1.getDob()));
                txtPosition.setText(t1.getPosition());
                txtSalary.setText(String.valueOf(t1.getSalary()));
                txtContactNo.setText(t1.getContactNumber());
                txtAddress.setText(t1.getAddress());
                txtJoinedDate.setValue(LocalDate.parse(t1.getJoinedDate()));
                txtStatus.setText(t1.getStatus());
            }
        });
    }

    private void loadEmployeeDetails() {
        employeeList.clear();

       EmployeeController employeeController = new EmployeeController();

        tblEmployee.setItems(employeeController.getAllEmployees());

    }

    public void clearFields() {
        txtEmpId.clear();
        txtName.clear();
        txtNic.clear();
        txtDOB.setValue(null);
        txtPosition.clear();
        txtSalary.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtJoinedDate.setValue(null);
        txtStatus.clear();
    }

}
