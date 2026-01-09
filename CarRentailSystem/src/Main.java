import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        Car car4 = new Car("C004", "Mercedes", "C-Class", 200.0);
        Car car5 = new Car("C005", "BMW", "M4", 250.0);
        Car car6 = new Car("C006", "Hyundai", "Elantra", 50.0);
        Car car7 = new Car("C007", "Tesla", "Model 3", 180.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);
        rentalSystem.addCar(car7);

        rentalSystem.menu();
    }
}