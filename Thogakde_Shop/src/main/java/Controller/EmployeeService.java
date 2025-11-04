package Controller;

import javafx.collections.ObservableList;
import model.dto.EmployeeDTO;

public interface EmployeeService {
    void addEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status);
    void deleteEmployee(String employeeId);
    void updateEmployee(String name, String employeeId, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status);
    ObservableList<EmployeeDTO> getAllEmployees();
}
