import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CarRentalSystem {
    // Lists to store our data
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    // Constructor to initialize the lists
    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    // Add a new car to the system
    public void addCar(Car car) {
        cars.add(car);
    }

    // Add a new customer to the system
    public void addCustomers(Customer customer) {
        customers.add(customer);
    }

    // Logic to rent a car
    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent(); // Change car status to not available
            rentals.add(new Rental(car, customer, days)); // Create rental record
        } else {
            System.out.println("Car Is Not Available For Rent.");
        }
    }

    // Logic to return a car
    public void returnCar(Car car) {
        car.returnCar(); // Change car status to available
        // Remove the rental record from the list
        rentals.removeIf(rental -> rental.getCar().equals(car));
    }

    // The main menu of the system
    public void menu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n====================================");
            System.out.println("       CAR RENTAL SYSTEM       ");
            System.out.println("====================================");
            System.out.println("[1] Rent A Car");
            System.out.println("[2] Return A Car");
            System.out.println("[3] Exit");
            System.out.print("Enter Your Choice: ");

            // Read choice as String and convert to int to avoid errors
            String choiceStr = input.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
                continue;
            }

            // OPTION 1: RENT A CAR
            if (choice == 1) {
                System.out.println("\n--- Rent A Car ---");

                // Get customer name with Regex (Letters and spaces only)
                String customerName;
                while (true) {
                    System.out.print("Enter Your Name: ");
                    customerName = input.nextLine().trim();
                    if (customerName.matches("^[a-zA-Z\\s\\u0600-\\u06FF]+$")) {
                        break;
                    }
                    System.out.println("Invalid Name! Please use letters and spaces only.");
                }

                // Show only available cars
                System.out.println("\nAvailable Cars:");
                System.out.printf("%-10s | %-15s | %-15s%n", "ID", "Brand", "Model");
                System.out.println("----------------------------------------------");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.printf("%-10s | %-15s | %-15s%n", car.getCarID(), car.getBrand(), car.getModel());
                    }
                }

                System.out.print("\nEnter Car ID to rent: ");
                String carID = input.nextLine().trim();

                System.out.print("Enter Number of Days: ");
                int rentalDays;
                try {
                    rentalDays = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of days.");
                    continue;
                }

                // Create new customer object
                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);

                // Find the car by ID (Ignore case like 'car01' or 'CAR01')
                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarID().equalsIgnoreCase(carID) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                // Confirm and finish rental
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n============================");
                    System.out.println("     RENTAL CONFIRMATION     ");
                    System.out.println("============================");
                    System.out.printf("Customer: %s (%s)%n", newCustomer.getName(), newCustomer.getCustomerID());
                    System.out.printf("Car:      %s %s%n", selectedCar.getBrand(), selectedCar.getModel());
                    System.out.printf("Days:     %d Days%n", rentalDays);
                    System.out.printf("Total:    $%.2f%n", totalPrice);
                    System.out.println("============================");
                    System.out.print("Confirm Rental (Y/N): ");

                    String confirm = input.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        addCustomers(newCustomer);
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar Rented Successfully!");
                    } else {
                        System.out.println("\nRental Canceled.");
                    }
                } else {
                    System.out.println("\nCar not found or already rented.");
                }

                // OPTION 2: RETURN A CAR
            } else if (choice == 2) {
                System.out.println("\n--- Return A Car ---");

                // Show ALL cars (Available and Rented)
                System.out.println("All Cars in System:");
                System.out.printf("%-10s | %-12s | %-12s | %-10s%n", "ID", "Brand", "Model", "Status");
                System.out.println("----------------------------------------------------------");
                for (Car car : cars) {
                    String status = car.isAvailable() ? "Available" : "Rented";
                    System.out.printf("%-10s | %-12s | %-12s | %-10s%n",
                            car.getCarID(), car.getBrand(), car.getModel(), status);
                }

                System.out.print("\nEnter The Car ID You Want To Return: ");
                String carID = input.nextLine().trim();

                // Find the car to return
                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarID().equalsIgnoreCase(carID)) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    if (!carToReturn.isAvailable()) {
                        // Find who rented this car
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar().equals(carToReturn)) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }

                        returnCar(carToReturn);
                        String customerName = (customer != null) ? customer.getName() : "Unknown";
                        System.out.println("Car [" + carToReturn.getBrand() + " " + carToReturn.getModel() +
                                "] Returned Successfully by: " + customerName);
                    } else {
                        System.out.println("This car is already in the garage (Not Rented).");
                    }
                } else {
                    System.out.println("Invalid Car ID. Please check the ID from the list above.");
                }

                // OPTION 3: EXIT
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid Choice.");
            }
        }
        System.out.println("\nThank you for choosing us! ❤️");
    }
}