package interfaces;

public interface Payable {
    boolean pay(String method, double amount);
    void printPaymentReceipt();
}
