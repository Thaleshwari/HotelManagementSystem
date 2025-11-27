import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Hotel Management System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewAllBookings();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.print("Exiting");
        for (int i = 0; i < 3; i++) {
           try {
              Thread.sleep(300); // wait 300 milliseconds (0.5 seconds)
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         System.out.print(".");
}
System.out.println(" Thank you!");

    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Double", 1500));
        rooms.add(new Room(103, "Suite", 3000));
        rooms.add(new Room(104, "Single", 1000));
        rooms.add(new Room(105, "Double", 1500));
    }

    private static void viewAvailableRooms() {
    System.out.println("\n--- Available Rooms ---");

    // Print table header with fixed-width columns
    System.out.printf("%-10s %-10s %-10s\n", "Room No", "Type", "Price");
    System.out.println("-------------------------------");

    boolean anyAvailable = false;
    for (Room room : rooms) {
        if (!room.isBooked()) {
            // Print each room in fixed-width format
            System.out.printf("%-10d %-10s %-10.2f\n", room.getRoomNumber(), room.getType(), room.getPricePerNight());
            anyAvailable = true;
        }
    }

    if (!anyAvailable) {
        System.out.println("No rooms available!");
    }
}


    private static void bookRoom() {
    String name;
    String phone = "";
    int phoneAttempts = 0;

    System.out.print("Enter Customer Name: ");
    name = sc.nextLine();

    // Phone number validation: 3 attempts
    while (phoneAttempts < 3) {
        System.out.print("Enter Phone Number (10 digits): ");
        phone = sc.nextLine();
        if (phone.matches("\\d{10}")) {
            break; // valid phone number
        } else {
            phoneAttempts++;
            System.out.println("Invalid phone number! Please enter 10 digits only.");
        }
    }

    if (phoneAttempts == 3) {
        System.out.println("Maximum attempts reached. Booking cancelled.");
        return;
    }

    // Display available rooms
    viewAvailableRooms();

    int roomNumber = 0;
    boolean validRoom = false;
    while (!validRoom) {
        System.out.print("Enter Room Number to Book: ");
        roomNumber = sc.nextInt();

        // Check if room exists
        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook == null) {
            System.out.println("Room number does not exist. Please enter a valid room number.");
        } else if (roomToBook.isBooked()) {
            System.out.println("Room is already booked. Choose another room.");
        } else {
            validRoom = true;
        }
    }

    System.out.print("Enter Number of Nights: ");
    int nights = sc.nextInt();
    sc.nextLine(); // consume newline

    // Book the room
    Room roomToBook = null;
    for (Room room : rooms) {
        if (room.getRoomNumber() == roomNumber) {
            roomToBook = room;
            break;
        }
    }

    Customer customer = new Customer(name, phone);
    Booking booking = new Booking(customer, roomToBook, nights);
    bookings.add(booking);
    System.out.println("Booking Successful!");
    System.out.println(booking);
}

    private static void cancelBooking() {
        System.out.print("Enter Room Number to Cancel Booking: ");
        int roomNumber = sc.nextInt();
        sc.nextLine(); // consume newline

        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            bookingToCancel.getRoom().setBooked(false);
            bookings.remove(bookingToCancel);
            System.out.println("Booking Cancelled Successfully!");
        } else {
            System.out.println("No booking found for this room number.");
        }
    }

    private static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}
