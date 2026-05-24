package model;

import interfaces.Verifiable;

public class Companion implements Verifiable {
    private String name;
    private String nik;
    private String verifyStatus;

    public Companion(String name, String nik) {
        this.name         = name;
        this.nik          = nik;
        this.verifyStatus = "UNVERIFIED";
    }

    // Getter & Setter
    public String getName()             { return name; }
    public String getNik()              { return nik; }
    public void setName(String name)    { this.name = name; }
    public void setNik(String nik)      { this.nik = nik; }

    // Implementasi dari interface Verifiable
    @Override
    public boolean verify() {
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

    public void printInfo() {
        System.out.println("Nama   : " + name);
        System.out.println("NIK    : " + nik);
        System.out.println("Status : " + verifyStatus);
    }
}
