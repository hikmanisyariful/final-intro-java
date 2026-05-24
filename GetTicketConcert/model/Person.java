package model;

public abstract class Person {
    private String name;
    private String email;
    private String nik;

    public Person(String name, String email, String nik) {
        this.name  = name;
        this.email = email;
        this.nik   = nik;
    }

    // Getter
    public String getName()  { return name; }
    public String getEmail() { return email; }
    public String getNik()   { return nik; }

    // Setter
    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setNik(String nik)     { this.nik = nik; }

    // Setiap subclass wajib menjelaskan perannya
    public abstract String getRole();

    public void printProfile() {
        System.out.println("Nama  : " + name);
        System.out.println("Email : " + email);
        System.out.println("NIK   : " + nik);
        System.out.println("Role  : " + getRole());
    }
}
