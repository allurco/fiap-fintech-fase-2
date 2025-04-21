# Investment Management System

This project is a **Java-based Investment Management System** designed to manage users, banks, bank accounts, and investments. It provides a command-line interface (CLI) for users to interact with the system and perform various operations such as user registration, bank account creation, and investment management.

## Features

### User Management
- Register new users with name, email, and password.
- List all registered users with pagination.
- Search for a user by email.

### Bank and Account Management
- Add new banks with name and code.
- Search for banks by name or initials.
- Create bank accounts for registered users.
- List and view details of bank accounts.

### Investment Management
- Register new investments.
- Make contributions (aportes) to investments.
- View investment goals and long-term balances.

## Project Structure

The project follows a modular structure with the following key components:

- **DAO Layer**: Handles database operations (e.g., `ContaBancariaDao`, `UsuarioDao`).
- **Service Layer**: Contains business logic (e.g., `UsuarioService`, `BancosService`).
- **Model Layer**: Represents the data models (e.g., `Usuario`, `Banco`, `ContaBancaria`).
- **Menu**: Provides a CLI for user interaction (`Menu.java`).
- **Exceptions**: Custom exceptions for error handling (e.g., `UserNotFoundException`).

## Technologies Used

- **Programming Language**: Java
- **Build Tool**: Maven
- **Database**: Oracle Database
- **IDE**: IntelliJ IDEA

## Database Configuration

This project uses **Oracle Database** for data storage. Below are the steps to configure the database:

1. **Set Up Oracle Database**:
    - Install and configure Oracle Database.
    - Create a database schema for the application.

2. **Update Database Connection**:
    - Configure the connection details in the `ConnectionFactory` class:
      ```java
      private static final String URL = "jdbc:oracle:thin:@<host>:<port>:<service_name>";
      private static final String USER = "<username>";
      private static final String PASSWORD = "<password>";
      ```
      Replace `<host>`, `<port>`, `<service_name>`, `<username>`, and `<password>` with your Oracle Database details.

3. **SQL Scripts**:
    - Use the provided SQL scripts to create the necessary tables. Ensure the syntax is compatible with Oracle (e.g., `VARCHAR2`, `SYSDATE`).

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-folder>