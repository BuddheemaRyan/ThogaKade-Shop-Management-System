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
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
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
        String cusId=txtCustId.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getValue().toString();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province =txtProvince.getValue().toString();
        String postalCode = txtPostalCode.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, cusId);
            preparedStatement.setObject(2,title);
            preparedStatement.setObject(3,name);
            preparedStatement.setObject(4,dob);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,province);
            preparedStatement.setObject(9,postalCode);
            preparedStatement.execute();

            loadCustomerDetails();
            clearFields();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDelete(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID=?");
            pstm.setObject(1,txtCustId.getText());
            pstm.executeUpdate();
            clearFields();
            loadCustomerDetails();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try {
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            String sql = "UPDATE Customer SET  Title=?, Name=?, DateOfBirth=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1,txtTitle.getText());
            preparedStatement.setObject(2,txtName.getText());
            preparedStatement.setObject(3,txtDOB.getValue().toString());
            preparedStatement.setObject(4,Double.parseDouble(txtSalary.getText()));
            preparedStatement.setObject(5,txtAddress.getText());
            preparedStatement.setObject(6,txtCity.getText());
            preparedStatement.setObject(7,txtProvince.getValue().toString());
            preparedStatement.setObject(8,txtPostalCode.getText());
            preparedStatement.setObject(9,txtCustId.getText());

            preparedStatement.executeUpdate();
            clearFields();
            loadCustomerDetails();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

   private void  loadCustomerDetails(){
        customerInfoArray.clear();

       try {
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
                CustomerDTO customerDTO = new CustomerDTO(
                          resultSet.getString("CustomerID"),
                          resultSet.getString("Title"),
                          resultSet.getString("Name"),
                          resultSet.getString("DateOfBirth"),
                          resultSet.getDouble("Salary"),
                          resultSet.getString("Address"),
                          resultSet.getString("City"),
                          resultSet.getString("Province"),
                          resultSet.getString("PostalCode")
                );
                customerInfoArray.add(customerDTO);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       tblCustomer.setItems(customerInfoArray);

   }

   public void clearFields(){
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
