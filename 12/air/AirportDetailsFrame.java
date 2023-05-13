package air;

import javax.swing.*;

class AirportDetailsFrame {
    public void displayAirportDetailsFrame() {
        String name = "PHILIPPINE AIRLINE";
        String code = "Ph_Air_2.0";
        String location = "Philippines, Tarlac";

        AbstractAirport airport = new Airport(name, code, location);
        airport.displayDetails();
    }
}

abstract class AbstractAirport {
    private String name;
    private String code;
    private String location;

    public AbstractAirport(String name, String code, String location) {
        this.name = name;
        this.code = code;
        this.location = location;
    }

    public abstract void displayDetails();

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

class Airport extends AbstractAirport {
    public Airport(String name, String code, String location) {
        super(name, code, location);
    }

    @Override
    public void displayDetails() {
        JOptionPane.showMessageDialog(null, "Airport Details:\n\n" +
                "Name: " + getName() + "\n" +
                "Code: " + getCode() + "\n" +
                "Location: " + getLocation());
    }
}

class Main {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

        Flight flight1 = new Flight("ABC123", "New York", "London", "08:00", "16:00");
        flight1.setPaid(true);
        flight1.setPaymentAmount(1000.0);
        Payment payment1 = new Payment(1000.0, "Credit Card", "123456789");
        flight1.setPayment(payment1);

        Flight flight2 = new Flight("DEF456", "Tokyo", "Paris", "14:30", "22:30");
        flight2.setPaid(false);
        flight2.setPaymentAmount(0.0);

        bookingSystem.addFlight(flight1);
        bookingSystem.addFlight(flight2);

        int option;
        do {
            String menu = "Flight Booking System\n" +
                    "1. Display Flights\n" +
                    "2. Add Flight\n" +
                    "3. Display Airport Details\n" +
                    "4. Exit\n\n" +
                    "Enter your option:";
            option = Integer.parseInt(JOptionPane.showInputDialog(null, menu));

            switch (option) {
                case 1:
                    bookingSystem.displayFlights();
                    break;
                case 2:
                    bookingSystem.displayFlights();
                    System.out.println("Adding a Flight");
                    bookingSystem.addFlightWithInput();
                    break;
                case 3:
                    System.out.println("Displaying Airport Details");
                    AirportDetailsFrame airportDetailsFrame = new AirportDetailsFrame();
                    airportDetailsFrame.displayAirportDetailsFrame();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Thank you for using the Flight Booking System.",
                            "Flight Booking System", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.",
                            "Flight Booking System", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (option != 4);
    }
}
