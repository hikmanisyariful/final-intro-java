package model;

public class Booking {
    private String bookingId;
    private String buyerName;
    private String concertName;
    private String concertDate;
    private String venue;
    private String ticketClass;
    private String seatCode;
    private double price;
    private String paymentMethod;
    private String purchaseDate;

    public Booking(String bookingId, String buyerName, String concertName, String concertDate,
                   String venue, String ticketClass, String seatCode,
                   double price, String paymentMethod, String purchaseDate) {
        this.bookingId     = bookingId;
        this.buyerName     = buyerName;
        this.concertName   = concertName;
        this.concertDate   = concertDate;
        this.venue         = venue;
        this.ticketClass   = ticketClass;
        this.seatCode      = seatCode;
        this.price         = price;
        this.paymentMethod = paymentMethod;
        this.purchaseDate  = purchaseDate;
    }

    // Getter
    public String getBookingId()     { return bookingId; }
    public String getBuyerName()     { return buyerName; }
    public String getConcertName()   { return concertName; }
    public String getConcertDate()   { return concertDate; }
    public String getVenue()         { return venue; }
    public String getTicketClass()   { return ticketClass; }
    public String getSeatCode()      { return seatCode; }
    public double getPrice()         { return price; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPurchaseDate()  { return purchaseDate; }

    public void printInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Booking ID  : " + bookingId);
        System.out.println("Nama        : " + buyerName);
        System.out.println("Konser      : " + concertName);
        System.out.println("Tanggal     : " + concertDate);
        System.out.println("Venue       : " + venue);
        System.out.println("Kelas       : " + ticketClass);
        System.out.println("Kursi       : " + seatCode);
        System.out.printf ("Harga       : Rp %,.0f%n", price);
        System.out.println("Pembayaran  : " + paymentMethod);
        System.out.println("Tgl Beli    : " + purchaseDate);
        System.out.println("----------------------------------------");
    }
}
