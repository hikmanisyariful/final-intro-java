public class ConcertTicket {
    public static void main(String[] args) {

        // Data pelanggan - menggunakan String (rangkaian karakter)
        String customerName  = "Hikmani Syariful Fajar";
        String concertName   = "BTS World Tour 2026";
        String venue         = "Gelora Bung Karno, Jakarta";
        String date          = "14 Juni 2026";
        String seatNumber    = "A-07";

        // Data tiket - menggunakan char (satu karakter)
        char   ticketClass   = 'B';   // A = VIP, B = Festival, C = Tribun
        char   paymentStatus = 'Y';   // Y = Paid, N = Unpaid
        char   gateEntry     = '3';   // Nomor gate masuk

        // Cetak struk tiket
        System.out.println("========================================");
        System.out.println("         CONCERT TICKET BOOKING         ");
        System.out.println("========================================");
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Concert        : " + concertName);
        System.out.println("Venue          : " + venue);
        System.out.println("Date           : " + date);
        System.out.println("Seat Number    : " + seatNumber);
        System.out.println("Ticket Class   : " + ticketClass);
        System.out.println("Gate Entry     : " + gateEntry);
        System.out.println("Payment Status : " + (paymentStatus == 'Y' ? "PAID" : "UNPAID"));
        System.out.println("========================================");

        // Konfirmasi booking menggunakan metode String
        System.out.println("\nBooking confirmed for: " + customerName.toUpperCase());
        System.out.println("Total characters in customer name: " + customerName.length());
        System.out.println("First name: " + customerName.substring(0, customerName.indexOf(' ')));
    }
}
