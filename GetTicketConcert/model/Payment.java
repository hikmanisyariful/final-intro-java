package model;

import interfaces.Payable;
import java.util.Scanner;

public class Payment implements Payable {
    private String bookingId;
    private double amount;
    private String method;
    private String status;

    public Payment(String bookingId, double amount) {
        this.bookingId = bookingId;
        this.amount    = amount;
        this.status    = "PENDING";
    }

    // Getter
    public String getMethod() { return method; }
    public String getStatus() { return status; }
    public double getAmount() { return amount; }

    // Dipanggil dari GTCApp untuk proses pembayaran interaktif
    public boolean processPayment(Scanner scanner) {
        System.out.println("\n--- PEMBAYARAN ---");
        System.out.printf("Total Tagihan : Rp %,.0f%n", amount);
        System.out.println("Pilih metode pembayaran:");
        System.out.println("  [1] Transfer Bank");
        System.out.println("  [2] QRIS");
        System.out.println("  [3] Kartu Kredit");
        System.out.print("Pilih: ");

        String input = scanner.nextLine();
        String selectedMethod;
        switch (input) {
            case "1": selectedMethod = "Transfer Bank"; break;
            case "2": selectedMethod = "QRIS";          break;
            case "3": selectedMethod = "Kartu Kredit";  break;
            default:
                System.out.println("Metode tidak valid. Pembayaran dibatalkan.");
                return false;
        }

        return pay(selectedMethod, amount);
    }

    // Implementasi dari interface Payable
    @Override
    public boolean pay(String method, double amount) {
        this.method = method;
        this.status = "SUCCESS";
        System.out.println("> Pembayaran via " + method + " berhasil!");
        return true;
    }

    @Override
    public void printPaymentReceipt() {
        System.out.println("========================================");
        System.out.println("           STRUK PEMBAYARAN             ");
        System.out.println("========================================");
        System.out.println("Booking ID  : " + bookingId);
        System.out.printf ("Total Bayar : Rp %,.0f%n", amount);
        System.out.println("Metode      : " + method);
        System.out.println("Status      : " + status);
        System.out.println("========================================");
    }
}
