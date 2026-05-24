package model;

import interfaces.Verifiable;
import java.util.ArrayList;
import java.util.List;

public class User extends Person implements Verifiable {
    private String       verifyStatus;
    private List<String> companions;      // daftar nama teman/keluarga
    private List<String> bookingHistory;  // histori pembelian tiket

    public User(String name, String email, String nik) {
        super(name, email, nik);
        this.verifyStatus   = "UNVERIFIED";
        this.companions     = new ArrayList<>();
        this.bookingHistory = new ArrayList<>();
    }

    // Implementasi dari interface Verifiable
    @Override
    public boolean verify() {
        String nik = getNik();
        if (nik != null && nik.matches("\\d{16}")) {
            this.verifyStatus = "VERIFIED";
            return true;
        }
        this.verifyStatus = "UNVERIFIED";
        return false;
    }

    @Override
    public String getVerifyStatus() {
        return verifyStatus;
    }

    // Implementasi dari abstract class Person
    @Override
    public String getRole() {
        return "User";
    }

    // Kelola daftar teman/keluarga
    public void addCompanion(String companionName) {
        companions.add(companionName);
    }

    public List<String> getCompanions() {
        return companions;
    }

    // Kelola histori pembelian
    public void addBooking(String bookingInfo) {
        bookingHistory.add(bookingInfo);
    }

    public List<String> getBookingHistory() {
        return bookingHistory;
    }

    public void printCompanions() {
        if (companions.isEmpty()) {
            System.out.println("Belum ada teman/keluarga yang ditambahkan.");
            return;
        }
        System.out.println("Daftar teman/keluarga:");
        for (int i = 0; i < companions.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + companions.get(i));
        }
    }

    public void printBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("Belum ada histori pembelian.");
            return;
        }
        System.out.println("Histori Pembelian:");
        for (int i = 0; i < bookingHistory.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + bookingHistory.get(i));
        }
    }
}
