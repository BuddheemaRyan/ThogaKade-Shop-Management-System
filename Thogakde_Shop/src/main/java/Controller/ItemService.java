package Controller;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

public interface ItemService {
    void addItem(String itemCode, String description, String category, int qtyOnHand, double unitPrice);
    void updateItem(String itemCode, String description, String category, int qtyOnHand, double unitPrice);
    void deleteItem(String itemCode);
    ObservableList<ItemDTO> getALlItemsDetails();
}
