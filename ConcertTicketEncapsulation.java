public class ConcertTicketEncapsulation {

    // =============================================
    // CLASS dengan Encapsulation
    // Atribut private — tidak bisa diakses langsung
    // =============================================
    static class Ticket {
        private String customerName;
        private String concertName;
        private char   ticketClass;
        private int    quantity;
        private double pricePerTicket;

        // Constructor
        public Ticket(String customerName, String concertName, char ticketClass, int quantity) {
            this.customerName = customerName;
            this.concertName  = concertName;
            this.quantity     = quantity;
            setTicketClass(ticketClass);
        }

        // SETTER dengan validasi (Selection)
        public void setTicketClass(char ticketClass) {
            if (ticketClass == 'A') {
                this.ticketClass    = 'A';
                this.pricePerTicket = 2500000;
            } else if (ticketClass == 'B') {
                this.ticketClass    = 'B';
                this.pricePerTicket = 1500000;
            } else if (ticketClass == 'C') {
                this.ticketClass    = 'C';
                this.pricePerTicket = 750000;
            } else {
                System.out.println("Kelas tiket tidak valid! Default ke kelas C.");
                this.ticketClass    = 'C';
                this.pricePerTicket = 750000;
            }
        }

        public void setQuantity(int quantity) {
            if (quantity >= 1 && quantity <= 5) {
                this.quantity = quantity;
            } else {
                System.out.println("Jumlah tiket tidak valid! Maksimal 5 tiket.");
            }
        }

        // GETTER — satu-satunya cara mengakses data dari luar
        public String getCustomerName()   { return customerName; }
        public String getConcertName()    { return concertName; }
        public char   getTicketClass()    { return ticketClass; }
        public int    getQuantity()       { return quantity; }
        public double getPricePerTicket() { return pricePerTicket; }
        public double getTotalPrice()     { return pricePerTicket * quantity; }

        // Mencetak detail tiket
        public void printTicketDetails() {
            System.out.println("========================================");
            System.out.println("          CONCERT TICKET BOOKING        ");
            System.out.println("========================================");
            System.out.println("Customer   : " + customerName.toUpperCase());
            System.out.println("Concert    : " + concertName);
            System.out.println("Class      : " + ticketClass);
            System.out.println("Quantity   : " + quantity + " tiket");
            System.out.printf ("Harga/tiket: Rp %,.0f%n", pricePerTicket);
            System.out.println("----------------------------------------");

            // Repetition: mencetak setiap tiket satu per satu
            for (int i = 1; i <= quantity; i++) {
                System.out.printf("  Tiket #%d  -> Seat %c-%02d%n", i, ticketClass, i);
            }

            System.out.println("----------------------------------------");
            System.out.printf ("Total      : Rp %,.0f%n", getTotalPrice());
            System.out.println("========================================");
        }
    }

    // =============================================
    // MAIN METHOD
    // =============================================
    public static void main(String[] args) {

        // 1. ISI DATA — membuat objek tiket baru
        System.out.println(">>> [1] ISI DATA TIKET");
        Ticket ticket = new Ticket("Hikmani Syariful Fajar", "BTS World Tour 2026", 'A', 3);
        System.out.println("Data tiket berhasil dibuat.\n");

        // 2. GET DATA — menampilkan detail tiket
        System.out.println(">>> [2] GET DATA TIKET");
        ticket.printTicketDetails();

        // 3. UPDATE DATA — mengubah kelas tiket dari A ke B
        System.out.println("\n>>> [3] UPDATE DATA TIKET");
        System.out.println("Mengubah kelas tiket dari A ke B...");
        ticket.setTicketClass('B');
        System.out.println("Kelas tiket berhasil diperbarui.");

        // 4. DELETE 1 TIKET — mengurangi jumlah tiket sebanyak 1
        System.out.println("\n>>> [4] DELETE 1 TIKET");
        System.out.println("Jumlah tiket sebelum: " + ticket.getQuantity());
        ticket.setQuantity(ticket.getQuantity() - 1);
        System.out.println("1 tiket berhasil dihapus.");

        // 5. GET DATA BARU — menampilkan data setelah update dan delete
        System.out.println("\n>>> [5] GET DATA TIKET TERBARU");
        ticket.printTicketDetails();
    }
}
