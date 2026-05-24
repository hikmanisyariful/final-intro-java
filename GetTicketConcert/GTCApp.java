import model.*;
import java.util.*;
import java.time.LocalDate;

public class GTCApp {

    static Scanner         scanner      = new Scanner(System.in);
    static List<Concert>   concerts     = new ArrayList<>();
    static User            currentUser  = null;
    static List<Booking>   userBookings = new ArrayList<>();
    static List<Companion> companions   = new ArrayList<>();

    static int tribCounter    = 1;
    static int festCounter    = 1;
    static int vipCounter     = 1;
    static int bookingCounter = 1;

    public static void main(String[] args) {
        seedConcerts();
        mainMenu();
        scanner.close();
    }

    // ============================================================
    // DATA AWAL KONSER
    // ============================================================
    static void seedConcerts() {
        concerts.add(new Concert("BTS World Tour 2026 - Night 1", "2026-06-14", "GBK, Jakarta",        120, 80, 20));
        concerts.add(new Concert("BTS World Tour 2026 - Night 2", "2026-06-15", "GBK, Jakarta",        120, 80, 20));
        concerts.add(new Concert("BTS Encore Night",              "2026-08-20", "ICE BSD, Tangerang",  100, 60, 15));
    }

    // ============================================================
    // MENU UTAMA
    // ============================================================
    static void mainMenu() {
        while (true) {
            System.out.println("\n============================================");
            System.out.println("   WELCOME TO GET CONCERT TICKET (GTC)");
            System.out.println("============================================");
            System.out.println("[1] Login sebagai User");
            System.out.println("[2] Login sebagai Admin");
            System.out.println("[0] Keluar");
            System.out.print("Pilih: ");

            switch (scanner.nextLine()) {
                case "1": userFlow();  break;
                case "2": adminFlow(); break;
                case "0":
                    System.out.println("\nTerima kasih telah menggunakan GTC. Sampai jumpa!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ============================================================
    // ALUR USER: INPUT IDENTITAS & TAMBAH TEMAN
    // ============================================================
    static void userFlow() {
        System.out.println("\n--- INPUT IDENTITAS ---");
        System.out.print("Nama lengkap  : ");
        String name = scanner.nextLine();
        System.out.print("Email         : ");
        String email = scanner.nextLine();
        System.out.print("NIK (16 digit): ");
        String nik = scanner.nextLine();

        currentUser  = new User(name, email, nik);
        userBookings = new ArrayList<>();
        companions   = new ArrayList<>();

        if (currentUser.verify()) {
            System.out.println("> Verifikasi identitas... BERHASIL [" + currentUser.getVerifyStatus() + "]");
        } else {
            System.out.println("> Verifikasi identitas... GAGAL. NIK harus 16 digit angka.");
        }

        System.out.print("\nTambah teman/keluarga? (y/n): ");
        while (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Nama          : ");
            String cName = scanner.nextLine();
            System.out.print("NIK (16 digit): ");
            String cNik  = scanner.nextLine();

            Companion comp = new Companion(cName, cNik);
            System.out.println("> Verifikasi " + cName + "... " +
                (comp.verify() ? "BERHASIL [VERIFIED]" : "GAGAL [UNVERIFIED]"));
            companions.add(comp);

            System.out.print("Tambah lagi? (y/n): ");
        }

        userMenu();
    }

    static void userMenu() {
        while (true) {
            System.out.println("\n--------------------------------------------");
            System.out.println("  MENU USER — " + currentUser.getName());
            System.out.println("  Status    : " + currentUser.getVerifyStatus());
            System.out.println("--------------------------------------------");
            System.out.println("[1] Lihat Daftar Konser");
            System.out.println("[2] Lihat Detail Tiket");
            System.out.println("[3] Beli Tiket");
            System.out.println("[4] Lihat Tiket Aktif");
            System.out.println("[5] Histori Pembelian");
            System.out.println("[0] Logout");
            System.out.print("Pilih: ");

            switch (scanner.nextLine()) {
                case "1": showConcerts();      break;
                case "2": showTicketTypes();   break;
                case "3": buyTicket();         break;
                case "4": showActiveTickets(); break;
                case "5": showHistory();       break;
                case "0":
                    System.out.println("Logout berhasil. Sampai jumpa, " + currentUser.getName() + "!");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ============================================================
    // FITUR: LIHAT KONSER
    // ============================================================
    static void showConcerts() {
        System.out.println("\n--- DAFTAR KONSER BTS ---");
        System.out.printf("%-4s %-35s %-12s %-22s%n", "No", "Nama Konser", "Tanggal", "Venue");
        System.out.println("-".repeat(75));
        for (int i = 0; i < concerts.size(); i++) {
            Concert c = concerts.get(i);
            System.out.printf("%-4d %-35s %-12s %-22s%n",
                (i + 1), c.getConcertName(), c.getDate(), c.getVenue());
        }
    }

    // ============================================================
    // FITUR: LIHAT DETAIL TIKET
    // ============================================================
    static void showTicketTypes() {
        showConcerts();
        System.out.print("\nPilih nomor konser: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx < 0 || idx >= concerts.size()) { System.out.println("Nomor tidak valid."); return; }
            Concert c = concerts.get(idx);
            System.out.println("\n--- DETAIL TIKET: " + c.getConcertName() + " ---");
            c.printTicketTypes();
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka.");
        }
    }

    // ============================================================
    // FITUR: BELI TIKET
    // ============================================================
    static void buyTicket() {
        if (!currentUser.getVerifyStatus().equals("VERIFIED")) {
            System.out.println("Anda harus terverifikasi untuk membeli tiket.");
            return;
        }

        // Pilih konser
        showConcerts();
        System.out.print("\nPilih nomor konser: ");
        int concertIdx;
        try {
            concertIdx = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid."); return;
        }
        if (concertIdx < 0 || concertIdx >= concerts.size()) { System.out.println("Nomor tidak valid."); return; }
        Concert concert = concerts.get(concertIdx);

        // Pilih jenis tiket
        System.out.println("\n--- PILIH JENIS TIKET ---");
        concert.printTicketTypes();
        System.out.print("Pilih kelas [T/F/V]: ");
        String classInput = scanner.nextLine().toUpperCase().trim();
        if (classInput.isEmpty()) { System.out.println("Input tidak valid."); return; }
        char ticketClass = classInput.charAt(0);

        if (ticketClass != 'T' && ticketClass != 'F' && ticketClass != 'V') {
            System.out.println("Kelas tidak valid. Pilih T (Tribun), F (Festival), atau V (VIP).");
            return;
        }

        if (!concert.reduceStock(ticketClass)) {
            System.out.println("Maaf, tiket kelas ini sudah habis.");
            return;
        }

        // Pilih nama pembeli
        System.out.println("\n--- PILIH NAMA PEMBELI ---");
        System.out.println("[1] " + currentUser.getName() + " (saya sendiri)");
        for (int i = 0; i < companions.size(); i++) {
            System.out.println("[" + (i + 2) + "] " + companions.get(i).getName());
        }
        System.out.print("Pilih: ");
        int buyerIdx;
        try {
            buyerIdx = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid."); return;
        }

        String buyerName;
        if (buyerIdx == 0) {
            buyerName = currentUser.getName();
        } else if (buyerIdx >= 1 && buyerIdx <= companions.size()) {
            buyerName = companions.get(buyerIdx - 1).getName();
        } else {
            System.out.println("Pilihan tidak valid."); return;
        }

        // Buat tiket — Polymorphism: setiap subclass punya printInfo() & getSeatCode() sendiri
        String bookingId = String.format("GTC-%s-%03d",
            LocalDate.now().toString().replace("-", ""), bookingCounter++);
        Ticket ticket;
        switch (ticketClass) {
            case 'T': ticket = new TribunTicket  (bookingId, concert.getConcertName(), concert.getDate(), concert.getVenue(), buyerName, tribCounter++); break;
            case 'F': ticket = new FestivalTicket(bookingId, concert.getConcertName(), concert.getDate(), concert.getVenue(), buyerName, festCounter++); break;
            default:  ticket = new VIPTicket     (bookingId, concert.getConcertName(), concert.getDate(), concert.getVenue(), buyerName, vipCounter++); break;
        }

        // Proses pembayaran
        Payment payment = new Payment(bookingId, ticket.getPrice());
        if (!payment.processPayment(scanner)) return;

        // Simpan booking
        Booking booking = new Booking(
            bookingId, buyerName,
            concert.getConcertName(), concert.getDate(), concert.getVenue(),
            ticket.getTicketClass(), ticket.getSeatCode(),
            ticket.getPrice(), payment.getMethod(),
            LocalDate.now().toString()
        );
        userBookings.add(booking);

        System.out.println("\n> Tiket berhasil dipesan!");
        ticket.printInfo();
        payment.printPaymentReceipt();
    }

    // ============================================================
    // FITUR: TIKET AKTIF (konser belum lewat)
    // ============================================================
    static void showActiveTickets() {
        String today = LocalDate.now().toString(); // format: yyyy-MM-dd
        System.out.println("\n--- TIKET AKTIF (Konser Belum Lewat) ---");
        boolean found = false;
        for (Booking b : userBookings) {
            if (b.getConcertDate().compareTo(today) >= 0) {
                b.printInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Tidak ada tiket aktif.");
    }

    // ============================================================
    // FITUR: HISTORI PEMBELIAN
    // ============================================================
    static void showHistory() {
        System.out.println("\n--- HISTORI PEMBELIAN ---");
        if (userBookings.isEmpty()) {
            System.out.println("Belum ada histori pembelian.");
            return;
        }
        for (int i = 0; i < userBookings.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            userBookings.get(i).printInfo();
        }
    }

    // ============================================================
    // ALUR ADMIN
    // ============================================================
    static void adminFlow() {
        System.out.println("\n[Info] Kode admin default: admin123");
        System.out.print("Masukkan kode admin: ");
        if (!scanner.nextLine().equals("admin123")) {
            System.out.println("Kode admin salah."); return;
        }
        Admin admin = new Admin("Admin GTC", "admin@gtc.com", "0000000000000000", "admin123");
        adminMenu(admin);
    }

    static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--------------------------------------------");
            System.out.println("  MENU ADMIN — " + admin.getName());
            System.out.println("--------------------------------------------");
            System.out.println("[1] Tambah Konser Baru");
            System.out.println("[2] Lihat Semua Konser");
            System.out.println("[3] Update Stok Tiket");
            System.out.println("[0] Logout");
            System.out.print("Pilih: ");

            switch (scanner.nextLine()) {
                case "1": adminAddConcert();  break;
                case "2": showConcerts();     break;
                case "3": adminUpdateStock(); break;
                case "0":
                    System.out.println("Logout admin berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void adminAddConcert() {
        System.out.println("\n--- TAMBAH KONSER BARU ---");
        System.out.print("Nama konser         : ");
        String name = scanner.nextLine();
        System.out.print("Tanggal (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("Venue               : ");
        String venue = scanner.nextLine();
        System.out.print("Stok Tribun         : ");
        int tribun   = Integer.parseInt(scanner.nextLine());
        System.out.print("Stok Festival       : ");
        int festival = Integer.parseInt(scanner.nextLine());
        System.out.print("Stok VIP            : ");
        int vip      = Integer.parseInt(scanner.nextLine());

        concerts.add(new Concert(name, date, venue, tribun, festival, vip));
        System.out.println("> Konser berhasil ditambahkan!");
    }

    static void adminUpdateStock() {
        showConcerts();
        System.out.print("\nPilih nomor konser: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx < 0 || idx >= concerts.size()) { System.out.println("Nomor tidak valid."); return; }
            Concert c = concerts.get(idx);
            System.out.println("\n--- UPDATE STOK: " + c.getConcertName() + " ---");
            System.out.print("Stok Tribun baru   : ");
            c.setStockTribun(Integer.parseInt(scanner.nextLine()));
            System.out.print("Stok Festival baru : ");
            c.setStockFestival(Integer.parseInt(scanner.nextLine()));
            System.out.print("Stok VIP baru      : ");
            c.setStockVip(Integer.parseInt(scanner.nextLine()));
            System.out.println("> Stok berhasil diperbarui!");
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka.");
        }
    }
}
