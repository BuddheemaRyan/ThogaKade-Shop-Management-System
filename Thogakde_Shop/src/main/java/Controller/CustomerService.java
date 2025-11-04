package Controller;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    void addCustomer(String cusId, String title, String name, String dob, double salary, String address, String city, String province, String postalCode);
    void deleteCustomer(String cusId);
    void updateCustomer(String cusId, String title, String name, String dob, double salary, String address, String city, String province, String postalCode);

    ObservableList<CustomerDTO> getAllCustomerDetails();
}
