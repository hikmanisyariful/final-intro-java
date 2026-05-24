package model;

public abstract class Ticket {
    private String ticketId;
    private String concertName;
    private String date;
    private String venue;
    private String ownerName;

    public Ticket(String ticketId, String concertName, String date, String venue, String ownerName) {
        this.ticketId    = ticketId;
        this.concertName = concertName;
        this.date        = date;
        this.venue       = venue;
        this.ownerName   = ownerName;
    }

    // Getter
    public String getTicketId()    { return ticketId; }
    public String getConcertName() { return concertName; }
    public String getDate()        { return date; }
    public String getVenue()       { return venue; }
    public String getOwnerName()   { return ownerName; }

    // Setter
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    // Abstract — wajib diimplementasi oleh setiap subclass
    public abstract double  getPrice();
    public abstract String  getTicketClass();
    public abstract String  getSeatCode();
    public abstract void    printInfo();
}
