# 🛒 ThogaKade Shop Management System

A desktop-based shop management application built with Java and JavaFX, designed to manage customers, employees, items, and suppliers for a retail store.

---

## 🚀 Tech Stack

![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-19-blue?style=for-the-badge&logo=openjfx&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-9.4.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18.40-BC4521?style=for-the-badge&logo=lombok&logoColor=white)
![FXML](https://img.shields.io/badge/FXML-UI%20Layout-6DB33F?style=for-the-badge&logoColor=white)

---

## 📋 Features

- 🔐 **Login System** – Secure authentication for shop staff
- 👥 **Customer Management** – Add, view, update, and delete customer records
- 🧑‍💼 **Employee Management** – Manage employee information
- 📦 **Item Management** – Track inventory items and their details
- 🚚 **Supplier Management** – Maintain supplier records
- 🗂️ **Central Management Dashboard** – Unified view to navigate all modules

---

## 🏗️ Project Structure

```
Thogakde_Shop/
├── src/main/java/
│   ├── Controller/          # JavaFX controllers & service classes
│   │   ├── CustomerController.java
│   │   ├── CustomerFormController.java
│   │   ├── CustomerService.java
│   │   ├── EmployeeController.java
│   │   ├── EmployeeFormManagement.java
│   │   ├── EmployeeService.java
│   │   ├── ItemController.java
│   │   ├── ItemFormController.java
│   │   ├── ItemService.java
│   │   ├── SupplierController.java
│   │   ├── SupplierFormController.java
│   │   ├── SupplierService.java
│   │   ├── LoginController.java
│   │   └── ManagementController.java
│   ├── db/
│   │   └── DBConnection.java  # Singleton DB connection
│   ├── model/dto/             # Data Transfer Objects
│   ├── Main.java
│   └── Starter.java
└── src/main/resources/
    ├── view/                  # FXML UI layouts
    └── images/
```

---

## ⚙️ Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL Server (running on `localhost:3306`)

---

## 🗄️ Database Setup

1. Start your MySQL server.
2. Create a database named `Togakademanagement`:
   ```sql
   CREATE DATABASE Togakademanagement;
   ```
3. The default credentials used by the app are:
   - **User:** `root`
   - **Password:** `1234`

   > You can update these in `src/main/java/db/DBConnection.java` if needed.

---

## ▶️ Running the Application

```bash
# Clone the repository
git clone https://github.com/BuddheemaRyan/ThogaKade-Shop-Management-System.git
cd ThogaKade-Shop-Management-System/Thogakde_Shop

# Build and run with Maven
mvn javafx:run
```

---

## 📦 Dependencies

| Dependency        | Version |
|-------------------|---------|
| JavaFX Controls   | 19      |
| JavaFX FXML       | 19      |
| Lombok            | 1.18.40 |
| MySQL Connector/J | 9.4.0   |

---
## License
This project is for educational purposes.

## 🙌 Author

**BuddheemaRyan**  
[GitHub Profile](https://github.com/BuddheemaRyan)
