package model;

public class Admin extends Person {
    private String adminCode;

    public Admin(String name, String email, String nik, String adminCode) {
        super(name, email, nik);
        this.adminCode = adminCode;
    }

    // Getter & Setter
    public String getAdminCode()            { return adminCode; }
    public void setAdminCode(String code)   { this.adminCode = code; }

    // Implementasi abstract method dari Person
    @Override
    public String getRole() {
        return "Admin";
    }
}
