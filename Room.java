public class Room {
    private int roomNumber;
    private String type;
    private boolean isBooked;
    private double pricePerNight;

    public Room(int roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }

    // Getters and setters
    public int getRoomNumber() { 
        return roomNumber; 
    }
    public String getType() {
         return type; 
        }
    public boolean isBooked() {
         return isBooked;
         }
    public double getPricePerNight() {
         return pricePerNight; 
        }

    public void setBooked(boolean booked) {
         isBooked = booked;
         }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + type + " | Price: " + pricePerNight + " | " + (isBooked ? "Booked" : "Available");
    }
}
