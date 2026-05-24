package model;

public class Concert {
    private String concertName;
    private String date;
    private String venue;
    private int    stockTribun;
    private int    stockFestival;
    private int    stockVip;

    public static final double PRICE_TRIBUN   = 750000;
    public static final double PRICE_FESTIVAL = 1500000;
    public static final double PRICE_VIP      = 2500000;

    public Concert(String concertName, String date, String venue,
                   int stockTribun, int stockFestival, int stockVip) {
        this.concertName   = concertName;
        this.date          = date;
        this.venue         = venue;
        this.stockTribun   = stockTribun;
        this.stockFestival = stockFestival;
        this.stockVip      = stockVip;
    }

    // Getter
    public String getConcertName()  { return concertName; }
    public String getDate()         { return date; }
    public String getVenue()        { return venue; }
    public int    getStockTribun()  { return stockTribun; }
    public int    getStockFestival(){ return stockFestival; }
    public int    getStockVip()     { return stockVip; }

    // Setter
    public void setConcertName(String concertName)  { this.concertName = concertName; }
    public void setDate(String date)                { this.date = date; }
    public void setVenue(String venue)              { this.venue = venue; }
    public void setStockTribun(int stock)           { this.stockTribun = stock; }
    public void setStockFestival(int stock)         { this.stockFestival = stock; }
    public void setStockVip(int stock)              { this.stockVip = stock; }

    // Kurangi stok saat tiket dibeli
    public boolean reduceStock(char ticketClass) {
        switch (ticketClass) {
            case 'T':
                if (stockTribun > 0)   { stockTribun--;   return true; }
                break;
            case 'F':
                if (stockFestival > 0) { stockFestival--; return true; }
                break;
            case 'V':
                if (stockVip > 0)      { stockVip--;      return true; }
                break;
        }
        return false;
    }

    public void printConcertInfo() {
        System.out.println("Konser  : " + concertName);
        System.out.println("Tanggal : " + date);
        System.out.println("Venue   : " + venue);
    }

    public void printTicketTypes() {
        System.out.println("----------------------------------------");
        System.out.printf("  [T] Tribun   - Rp %,.0f  - Sisa: %d%n", PRICE_TRIBUN,   stockTribun);
        System.out.printf("  [F] Festival - Rp %,.0f - Sisa: %d%n", PRICE_FESTIVAL, stockFestival);
        System.out.printf("  [V] VIP      - Rp %,.0f - Sisa: %d%n", PRICE_VIP,      stockVip);
        System.out.println("----------------------------------------");
    }
}
