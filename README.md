# Car-Rental-System-Project

🚗 Car Rental System (Java)
A console-based Car Rental System built with Java. This project demonstrates the application of Object-Oriented Programming (OOP) principles, input validation, and clean code practices.

🌟 Key Features
Complete Rental Logic: Manage car rentals and returns seamlessly.

Input Validation (Regex): Ensures customer names contain only letters and spaces using Regular Expressions.

Error Handling: Uses try-catch blocks to prevent crashes from invalid inputs (entering letters instead of numbers ...).

User Experience (UX): Formatted table outputs using printf.

Case-insensitive search (C001 is the same as c001 ...).

Dynamic listing of all cars with their current status (Available/Rented).

Clean Architecture: Code is organized into separate classes (Car, Customer, Rental, CarRentalSystem).

🛠️ Technical Implementation
Language: Java

Tools: Git, GitHub

Validation: Regex ^[a-zA-Z\s\u0600-\u06FF]+$ for multilingual support (English/Arabic).

Data Structures: Used ArrayList to manage collections of cars, customers, and rentals.

🚀 How to Run
Clone the repository:

Bash

git clone https://github.com/ahmedtamer793/Car-Rental-System-Project.git
Navigate to the project directory:

Bash

cd Car-Rental-System-Project
Compile and Run the Main.java file.

📂 Project Structure
Car.java: Represents the vehicle entity.

Customer.java: Represents the user of the system.

Rental.java: Handles the relationship between a car and a customer.

CarRentalSystem.java: The core logic that manages the entire process.

Main.java: The entry point of the application.


