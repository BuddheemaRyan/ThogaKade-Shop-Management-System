package Controller;

import javafx.collections.ObservableList;
import model.dto.SupplierDTO;

public interface SupplierService {
    void addSupplier(String supplierId, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email);
    void deleteSupplier(String supplierId);
    void updateSupplier(String supplierId, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email);
    ObservableList<SupplierDTO> getAllSuppliers();
}
