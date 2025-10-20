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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeManagement implements Initializable {
    ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Anura Perera", "NIT12345", "1988-04-12", "Manager", 85000.00, "071-1234567", "12 Main Mawatha, Colombo", "2015-06-01", "Sakriya"),
            new EmployeeDTO("E002", "Kavindu Jayasinghe", "NIT23456", "1992-09-25", "Software Developer", 72000.00, "071-2345678", "45 Temple Road, Kandy", "2018-03-12", "Sakriya"),
            new EmployeeDTO("E003", "Nimali Silva", "NIT34567", "1985-01-30", "HR Officer", 65000.00, "071-3456789", "89 Galle Road, Matara", "2016-09-20", "Nisakriya"),
            new EmployeeDTO("E004", "Sampath Fernando", "NIT45678", "1990-07-18", "Accountant", 70000.00, "071-4567890", "23 Lake View, Kurunegala", "2017-11-05", "Sakriya"),
            new EmployeeDTO("E005", "Tharushi Ranasinghe", "NIT56789", "1995-12-02", "Graphic Designer", 68000.00, "071-5678901", "56 Beach Road, Negombo", "2020-01-15", "Sakriya")

    );

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
        String nic= txtNic.getText();
        String dob = txtDOB.getValue().toString();
        String position =txtPosition.getText();
        double salary =Double.parseDouble(txtSalary.getText());
        String contactNUmber =txtContactNo.getText();
        String address =txtAddress.getText();
        String joinedDate =txtJoinedDate.getValue().toString();
        String status =txtStatus.getText();

        EmployeeDTO newEmployee = new EmployeeDTO(employeeId, name, nic, dob, position, salary, contactNUmber, address, joinedDate, status);
        employeeList.add(newEmployee);
    }

    @FXML
    void btnCustomerManage(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnItemManage(ActionEvent event) {

    }

    @FXML
    void btnSupplierManage(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        EmployeeDTO employeeInfo =  tblEmployee.getSelectionModel().getSelectedItem();
        employeeInfo.setName(txtName.getText());
        employeeInfo.setEmployeeId(txtEmpId.getText());
        employeeInfo.setDob(txtDOB.getValue().toString());
        employeeInfo.setNic(txtNic.getText());
        employeeInfo.setAddress(txtAddress.getText());
        employeeInfo.setSalary(Double.parseDouble(txtSalary.getText()));
        employeeInfo.setStatus(txtStatus.getText());
        employeeInfo.setPosition(txtPosition.getText());
        employeeInfo.setJoinedDate(txtJoinedDate.getValue().toString());
        employeeInfo.setContactNumber(txtContactNo.getText());
        tblEmployee.refresh();
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

        tblEmployee.setItems(employeeList);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, employeeInfoDto, t1) -> {

            if (t1!=null){
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
}
