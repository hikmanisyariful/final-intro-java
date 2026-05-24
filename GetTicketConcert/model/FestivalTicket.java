package model;

public class FestivalTicket extends Ticket {
    private static final double PRICE      = 1500000;
    private static final String CLASS_NAME = "Festival";
    private int seatNumber;

    public FestivalTicket(String ticketId, String concertName, String date, String venue,
                          String ownerName, int seatNumber) {
        super(ticketId, concertName, date, venue, ownerName);
        this.seatNumber = seatNumber;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }

    @Override
    public String getTicketClass() {
        return CLASS_NAME;
    }

    @Override
    public String getSeatCode() {
        return String.format("F-%03d", seatNumber);
    }

    @Override
    public void printInfo() {
        System.out.println("========================================");
        System.out.println("             TIKET KONSER               ");
        System.out.println("========================================");
        System.out.println("ID Tiket  : " + getTicketId());
        System.out.println("Konser    : " + getConcertName());
        System.out.println("Tanggal   : " + getDate());
        System.out.println("Venue     : " + getVenue());
        System.out.println("Nama      : " + getOwnerName());
        System.out.println("Kelas     : " + CLASS_NAME);
        System.out.println("Kursi     : " + getSeatCode());
        System.out.printf ("Harga     : Rp %,.0f%n", PRICE);
        System.out.println("========================================");
    }
}
