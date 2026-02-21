<p align="center">
  <img src="https://www.especial.gr/wp-content/uploads/2019/03/panepisthmio-dut-attikhs.png" alt="UNIWA" width="150"/>
</p>

<p align="center">
  <strong>UNIVERSITY OF WEST ATTICA</strong><br>
  SCHOOL OF ENGINEERING<br>
  DEPARTMENT OF COMPUTER ENGINEERING AND INFORMATICS
</p>

<p align="center">
  <a href="https://www.uniwa.gr" target="_blank">University of West Attica</a> ·
  <a href="https://ice.uniwa.gr" target="_blank">Department of Computer Engineering and Informatics</a>
</p>

---

<p align="center">
  <strong>Software Engineering</strong>
</p>

<h1 align="center">
  Preze Cinemas Desktop - Phase 4 <br>
  Source Code
</h1>

<p align="center">
  <strong>Vasileios Evangelos Athanasiou</strong><br>
  Student ID: 19390005
</p>

<p align="center">
  <a href="https://github.com/Ath21" target="_blank">GitHub</a> ·
  <a href="https://www.linkedin.com/in/vasilis-athanasiou-7036b53a4/" target="_blank">LinkedIn</a>
</p>

<hr/>

<p align="center">
  <strong>Supervision</strong>
</p>

<p align="center">
  Supervisor: Georgios Prezerakos, Professor
</p>
<p align="center">
  <a href="https://ice.uniwa.gr/en/emd_person/george-prezerakos/" target="_blank">UNIWA Profile</a> ·
  <a href="https://www.linkedin.com/in/georgenprezerakos/" target="_blank">LinkedIn</a>
</p>

</hr>

---

<p align="center">
  Athens, August 2023
</p>

---

<p align="center">
  <img src="https://c8.alamy.com/comp/HE5CYM/movie-ticket-online-reservation-interface-concept-HE5CYM.jpg" width="250"/>
</p>

---

# INSTALL

## Preze Cinemas Desktop - Phase 4 Source Code

This guide will help you set up and run the **Preze Cinemas Desktop** application on your local machine.

---

## 1.Prerequisites

### 1.1 Software Requirements

- **Java Development Kit (JDK 11 or later)**
  - Download: [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **NetBeans IDE** (or any Java IDE supporting Swing & JDBC)
  - Download: [https://netbeans.apache.org/download/index.html](https://netbeans.apache.org/download/index.html)
- **MySQL Server** (≥ 8.0 recommended)
  - Download: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
- **MySQL Workbench** (optional, for database inspection)
- **Git** (optional, for cloning the repository)

### 1.2 Optional Tools

- Command-line terminal
- Text editor for configuration file edits

---

## 2. Clone the Repository

```bash
git clone https://github.com/Preze-Cinemas-Desktop/Code.git
cd Code
```

The project folder structure:

```bash
assign/
CinemaApplication/
README.md
```

`CinemaApplication/` contains:

- `src/` → Java source files for GUI and business logic
- `build/` → compiled .class files
- `nbproject/` → NetBeans project files
- `build.xml` → build script
- `manifest.mf` → JAR packaging file

---

## 3. Set Up MySQL Databases

The application requires two databases: `cinema` and `bank`.

### 3.1 Create Databases

1. Log in to MySQL:

```bash
mysql -u root -p
```

2. Create databases:

```bash
CREATE DATABASE cinema;
CREATE DATABASE bank;
```

### 3.2 Import Schema and Sample Data

If `.sql` scripts are provided in the repository (e.g., `CinemaApplication/database/`), import them:

```bash
mysql -u root -p cinema < cinema_schema.sql
mysql -u root -p bank < bank_schema.sql
```

If no scripts are provided, manually create tables according to the project documentation:

- **Cinema Database Tables**: `movies`, `screenings`, `seats`, `reservations`, `customers`
- **Bank Database Tables**: `accounts`, `transactions`, `balances`

---

## 4. Configure Database Connection in Java

Open the main configuration file (or `CinemaApplication.java/BankSystem.java`) and check the JDBC connection strings:

```bash
String urlCinema = "jdbc:mysql://localhost:3306/cinema";
String urlBank = "jdbc:mysql://localhost:3306/bank";
String user = "root";
String password = "your_mysql_password";
```

Update the username and password to match your MySQL setup.

---

## 5. Open Project in NetBeans

1. Launch NetBeans IDE.
2. Click File → Open Project.
3. Navigate to `CinemaApplication/` and select the folder.
4. NetBeans should detect the project as a Java application.
5. Wait until the IDE fully loads the project and resolves dependencies.

---

## 6. Build the Application

1. Right-click the project → Clean and Build.
2. Check for compilation errors in the Output window.
3. The executable `.class` files will be generated in `build/`.

---

## 7. Run the Application

Right-click the main class `CinemaApplication.java` → Run File.

Or run from command line:

```bash
cd CinemaApplication/build/classes
java cinemaapplication.CinemaApplication
```

---

## 8. Using the Application

1. **Login or Register**: Start by creating a new user or logging in.
2. **Choose Movie**: Select a movie and screening time.
3. **Select Tickets**: Pick the number and type of tickets.
4. **Check Availability**: Ensure tickets are available.
5. **Payment**: Simulated via `BankSystem`.
6. **Download Receipts/Tickets**: Save outputs as `.txt`.

---

## 9. Troubleshooting

- **JDBC Driver Not Found**: Ensure `mysql-connector-java.jar` is in the classpath.
- **Database Connection Error**: Verify credentials and MySQL service status.
- **GUI Issues**: Use NetBeans’ “Clean and Build” to resolve corrupted Swing layouts.
- **Payment Validation Errors**: Ensure bank database contains sufficient account balances.
