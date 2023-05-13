package air;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private boolean isPaid;
    private double paymentAmount;
    private Payment payment;

    public Flight(String flightNumber, String origin, String destination, String departureTime, String arrivalTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.isPaid = false;
        this.paymentAmount = 0.0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flight Number: ").append(flightNumber).append("\n");
        sb.append("Origin: ").append(origin).append("\n");
        sb.append("Destination: ").append(destination).append("\n");
        sb.append("Departure Time: ").append(departureTime).append("\n");
        sb.append("Arrival Time: ").append(arrivalTime).append("\n");
        sb.append("Paid: ").append(isPaid ? "Yes" : "No").append("\n");
        sb.append("Payment Amount: $").append(paymentAmount).append("\n");
        if (payment != null) {
            sb.append("Payment Details: ").append(payment.toString()).append("\n");
        } else {
            sb.append("Payment Details: N/A").append("\n");
        }
        return sb.toString();
    }

    public Object[] toTableRow() {
        return new Object[]{flightNumber, origin, destination, departureTime, arrivalTime, isPaid, paymentAmount, payment};
    }
}

class Payment {
    private double amount;
    private String paymentMethod;
    private String transactionId;

    public Payment(double amount, String paymentMethod, String transactionId) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Payment Amount: $").append(amount).append("\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Transaction ID: ").append(transactionId);
        return sb.toString();
    }
}

class BookingSystem {
    private List<Flight> flights;
    private DefaultTableModel tableModel;

    public BookingSystem() {
        flights = new ArrayList<>();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Flight Number");
        tableModel.addColumn("Origin");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Paid");
        tableModel.addColumn("Payment Amount");
        tableModel.addColumn("Payment Details");
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        updateTableModel();
    }

    private void updateTableModel() {
        tableModel.setRowCount(0);

        for (Flight flight : flights) {
            tableModel.addRow(flight.toTableRow());
        }
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void displayFlights() {
        if (flights.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No flights available.", "Flight Booking System", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scrollPane, "Flight Booking System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addFlightWithInput() {
        JTextField flightNumberField = new JTextField();
        JTextField originField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField arrivalTimeField = new JTextField();
        JTextField paymentAmountField = new JTextField();
        JTextField paymentMethodField = new JTextField();
        JTextField transactionIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Flight Number:"));
        panel.add(flightNumberField);
        panel.add(new JLabel("Origin:"));
        panel.add(originField);
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);
        panel.add(new JLabel("Departure Time:"));
        panel.add(departureTimeField);
        panel.add(new JLabel("Arrival Time:"));
        panel.add(arrivalTimeField);
        panel.add(new JLabel("Payment Amount:"));
        panel.add(paymentAmountField);
        panel.add(new JLabel("Payment Method:"));
        panel.add(paymentMethodField);
        panel.add(new JLabel("Transaction ID:"));
        panel.add(transactionIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String flightNumber = flightNumberField.getText();
            String origin = originField.getText();
            String destination = destinationField.getText();
            String departureTime = departureTimeField.getText();
            String arrivalTime = arrivalTimeField.getText();
            double paymentAmount = Double.parseDouble(paymentAmountField.getText());
            String paymentMethod = paymentMethodField.getText();
            String transactionId = transactionIdField.getText();

            Flight flight = new Flight(flightNumber, origin, destination, departureTime, arrivalTime);
            flight.setPaid(true);
            flight.setPaymentAmount(paymentAmount);
            Payment payment = new Payment(paymentAmount, paymentMethod, transactionId);
            flight.setPayment(payment);

            addFlight(flight);

            JOptionPane.showMessageDialog(null, "Flight added successfully.", "Flight Booking System", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

 
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
                    bookingSystem.addFlightWithInput();
                    break;
                    case 3:
                    AirportDetailsFrame airportDetailsFrame = new AirportDetailsFrame();
                    airportDetailsFrame.displayAirportDetailsFrame();
                    break;

     
                case 4:
                    JOptionPane.showMessageDialog(null, "Thank you for using the Flight Booking System.", "Flight Booking System", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Flight Booking System", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (option != 4);
    }
}


