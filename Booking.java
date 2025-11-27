public class Booking {
    private Customer customer;
    private Room room;
    private int nights;

    public Booking(Customer customer, Room room, int nights) {
        this.customer = customer;
        this.room = room;
        this.nights = nights;
        room.setBooked(true); // mark room as booked
    }

    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public int getNights() { return nights; }

    public double getTotalBill() {
        return room.getPricePerNight() * nights;
    }

    @Override
    public String toString() {
        return customer + " | " + room + " | Nights: " + nights + " | Total Bill: " + getTotalBill();
    }
}
