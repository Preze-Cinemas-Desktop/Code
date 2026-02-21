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

# README

## Preze Cinemas Desktop - Phase 4 Source Code

This repository phase contains the **full implementation** of the _Preze Cinemas Desktop_ system.  
Phase 4 focuses on transforming previous analysis and design phases into a **working desktop application**.

The system is implemented using:

- **Java Swing** for the desktop graphical interface
- **JDBC** for database connectivity
- **MySQL** databases for cinema and banking operations

---

## Table of Contents

| Section | Folder/File                                                                     | Description                                                                                 |
| ------: | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
|       1 | `assign/`                                                                       | Assignment instructions and supporting material                                             |
|     1.1 | `assign/seng_instructions_2022_23_v2.pdf`                                       | Assignment instructions (English)                                                           |
|     1.2 | `assign/λμηχ_οδηγίες_2022_23_β2.pdf`                                            | Assignment instructions (Greek)                                                             |
|       2 | `CinemaApplication/build/`                                                      | Compiled application files and build artifacts                                              |
|     2.1 | `CinemaApplication/build/classes/cinemaapplication/`                            | Compiled `.class` files for the CinemaApplication                                           |
|     2.2 | `CinemaApplication/build/classes/cinemaapplication/banksystem/BankSystem.class` | Bank system backend compiled class                                                          |
|     2.3 | `CinemaApplication/build/.netbeans_automatic_build/`                            | NetBeans automatic build metadata                                                           |
|     2.4 | `CinemaApplication/build/.netbeans_update_resources/`                           | NetBeans resource update files                                                              |
|     2.5 | `CinemaApplication/build/built-jar.properties`                                  | JAR build properties                                                                        |
|       3 | `CinemaApplication/nbproject/`                                                  | NetBeans project configuration files                                                        |
|     3.1 | `nbproject/private/`                                                            | Private project settings (`private.properties`, `private.xml`)                              |
|     3.2 | `build-impl.xml`                                                                | Ant build implementation file                                                               |
|     3.3 | `genfiles.properties`                                                           | Generated file properties                                                                   |
|     3.4 | `project.properties`                                                            | Project properties                                                                          |
|     3.5 | `project.xml`                                                                   | Project metadata                                                                            |
|       4 | `CinemaApplication/src/`                                                        | Source code of the CinemaApplication                                                        |
|     4.1 | `src/cinemaapplication/`                                                        | Main package containing application `.java` files                                           |
|     4.2 | `src/cinemaapplication/banksystem/BankSystem.java`                              | Bank system source code                                                                     |
|     4.3 | Other Java source files                                                         | Frames and application logic (`CheckAvailabilityFrame.java`, `ChooseMovieFrame.java`, etc.) |
|       5 | `CinemaApplication/`                                                            | Media and supporting files for the application                                              |
|     5.1 | `Bachelor2.png`, `Barbie.png`, ...                                              | Movie posters and related images                                                            |
|     5.2 | `build.xml`                                                                     | Ant build script                                                                            |
|     5.3 | `manifest.mf`                                                                   | Manifest file for JAR packaging                                                             |
|     5.4 | `TicketsBill123.txt`, `TransactionReceiptPanos.txt`, ...                        | Sample tickets and transaction receipts                                                     |
|       6 | `walkthrough/`                                                                  | Demonstration and walkthrough media                                                         |
|     6.1 | `walkthrough/Cinema-Desktop-App-TL.mkv`                                         | Video walkthrough of the Cinema Desktop Application                                         |
|       7 | `README.md`                                                                     | Project documentation                                                                       |
|       8 | `INSTALL.md`                                                                    | Usage instructions                                                                          |

---

## 1. Phase 4 Objective

The goal of this phase is to implement all previously modeled use cases as executable components of the system.

Each major use case is translated into a **Java Swing Frame**, enabling user interaction and system operations through graphical windows.

---

## 2. Implementation Overview

### 2.1 User Interface

The application uses **Java Swing** to build a desktop interface where:

- Each operation is represented as a separate window
- Navigation occurs between frames
- User inputs trigger database operations

Each use case corresponds to a dedicated class, such as:

- `CheckAvailabilityFrame.java`
- `ChooseMovieFrame.java`
- `ChooseTicketsFrame.java`
- `ChooseTimeViewFrame.java`
- `PayFrame.java`
- `DownloadTicketsFrame.java`

Example:
`CheckAvailabilityFrame.java` checks ticket availability and determines whether the user can proceed to payment.

---

## 3. Database Connectivity (JDBC)

The system connects to MySQL databases through **JDBC drivers**.

Two databases are used:

### 3.1 Cinema Database

Stores:

- Movies
- Screenings
- Available seats
- Reservations
- Customer information

### 3.2 Bank Database

Simulates payment processing by storing:

- Customer banking information
- Account balances
- Transaction updates

---

## 4. Banking System Module

A separate module simulates an external bank system.

The `BankSystem` class performs:

- Transaction validation
- Account balance checks
- Payment execution
- Account amount updates

This module allows the cinema system to verify payments before completing reservations.

---

## 5. Core Functional Flow

1. User logs in or registers
2. User selects movie and screening
3. User selects tickets
4. System checks availability
5. Payment is validated via bank system
6. Reservation is completed
7. Receipt is generated

Each step corresponds to a frame interaction and database transaction.

---

## 6. Folder Structure Overview

| Folder/File                     | Description                               |
| ------------------------------- | ----------------------------------------- |
| `cinemaapplication/`            | Main application source code              |
| `cinemaapplication/banksystem/` | Banking system simulation module          |
| `Frame.java` files              | UI windows implementing each use case     |
| `database/`                     | SQL schema or setup scripts (if included) |
| `README.md`                     | Project documentation                     |

---

## 7. Phase 4 Outcome

At the end of this phase:

- All system use cases are implemented
- GUI interaction is functional
- Database communication is operational
- Payment simulation is integrated
- Reservation workflow is executable

This phase results in a **fully functional desktop cinema reservation prototype**.

---

## 8. Conclusion

Phase 4 completes the transition from system design to implementation, delivering a working application capable of managing cinema reservations, availability checks, and payment validation through an integrated desktop system.
