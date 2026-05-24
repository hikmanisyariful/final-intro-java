# RANCANGAN APLIKASI GetConcertTicket (GTC)
# Studi Kasus: Booking Tiket Konser BTS — Java Console

---

## 1. ALUR PROGRAM (dari awal sampai akhir)

```
[START]
   |
   v
Tampil Menu Utama
   |
   |--- [1] Login sebagai USER
   |         |
   |         v
   |       Input identitas (nama, email, NIK)
   |         |
   |         v
   |       Verifikasi identitas → Sukses / Gagal
   |         |
   |         v
   |       Tambah daftar teman/keluarga (opsional) + verifikasi tiap orang
   |         |
   |         v
   |       Menu USER:
   |         [1] Lihat daftar konser BTS
   |         [2] Lihat detail tiket (Tribun / Festival / VIP)
   |         [3] Beli tiket → pilih konser → pilih jenis → pilih nama pembeli
   |         [4] Pembayaran → pilih metode → konfirmasi sukses
   |         [5] Lihat tiket aktif (konser belum lewat)
   |         [6] Lihat histori pembelian
   |         [0] Logout
   |
   |--- [2] Login sebagai ADMIN
   |         |
   |         v
   |       Menu ADMIN:
   |         [1] Tambah konser baru
   |         [2] Lihat semua konser
   |         [3] Update detail konser
   |         [4] Atur stok tiket per jenis
   |         [0] Logout
   |
   |--- [0] Keluar
   |
[END]
```

---

## 2. DAFTAR CLASS YANG DISARANKAN

```
GTC/
├── model/
│   ├── Person.java          ← Abstract Class
│   ├── User.java            ← extends Person
│   ├── Admin.java           ← extends Person
│   ├── Companion.java       ← teman/keluarga yang dibelikan tiket
│   ├── Concert.java         ← data jadwal konser
│   ├── Ticket.java          ← Abstract Class
│   ├── TribunTicket.java    ← extends Ticket
│   ├── FestivalTicket.java  ← extends Ticket
│   ├── VIPTicket.java       ← extends Ticket
│   ├── Booking.java         ← data pembelian tiket
│   └── Payment.java         ← simulasi pembayaran
│
├── interfaces/
│   ├── Verifiable.java      ← Interface
│   └── Payable.java         ← Interface
│
└── GTCApp.java              ← Main class (menu utama)
```

---

## 3. TANGGUNG JAWAB SETIAP CLASS

| Class           | Tanggung Jawab |
|-----------------|----------------|
| `Person`        | Menyimpan data dasar (nama, email, NIK). Abstract — tidak bisa dibuat objeknya langsung |
| `User`          | Turunan Person. Mengelola daftar teman & histori booking |
| `Admin`         | Turunan Person. Mengelola data konser dan stok tiket |
| `Companion`     | Data teman/keluarga yang ikut dibelikan tiket, punya status verifikasi |
| `Concert`       | Menyimpan nama konser, tanggal, venue, dan stok tiket per jenis |
| `Ticket`        | Abstract. Mendefinisikan atribut dan method dasar tiket |
| `TribunTicket`  | Jenis tiket Tribun dengan harga dan kursi khusus |
| `FestivalTicket`| Jenis tiket Festival dengan harga dan kursi khusus |
| `VIPTicket`     | Jenis tiket VIP dengan harga dan kursi khusus |
| `Booking`       | Menyimpan rekaman pembelian: siapa, konser apa, tiket apa, kapan beli |
| `Payment`       | Simulasi proses pembayaran dan pilihan metode |
| `Verifiable`    | Interface kontrak: siapapun yang bisa diverifikasi harus punya method verify() |
| `Payable`       | Interface kontrak: siapapun yang bisa bayar harus punya method pay() |
| `GTCApp`        | Titik masuk program. Menampilkan menu dan menghubungkan semua class |

---

## 4. MAPPING KONSEP OOP

### A. INHERITANCE (Pewarisan)
```
Person (abstract)
├── User          → mewarisi nama, email, NIK dari Person
└── Admin         → mewarisi nama, email, NIK dari Person

Ticket (abstract)
├── TribunTicket  → mewarisi tipe, harga dasar dari Ticket
├── FestivalTicket
└── VIPTicket
```

### B. ENCAPSULATION (Pembungkusan)
Semua atribut dibuat `private`, akses hanya lewat getter/setter:

| Class     | Atribut Private |
|-----------|----------------|
| `Person`  | name, email, nik |
| `User`    | companions (list), bookingHistory (list) |
| `Concert` | concertName, date, venue, availableSeats |
| `Ticket`  | ticketType, price, seatCode |
| `Booking` | bookingId, buyerName, concert, ticket, purchaseDate |
| `Payment` | amount, method, status |

### C. POLYMORPHISM (Banyak Bentuk)
Method yang di-override di setiap subclass:

| Method            | Di class mana di-override | Perbedaan perilaku |
|-------------------|--------------------------|-------------------|
| `printInfo()`     | TribunTicket, FestivalTicket, VIPTicket | Format tampilan berbeda per jenis tiket |
| `getPrice()`      | TribunTicket, FestivalTicket, VIPTicket | Harga berbeda tiap jenis |
| `getSeatCode()`   | TribunTicket, FestivalTicket, VIPTicket | Kode kursi berbeda (T-, F-, V-) |
| `getRole()`       | User, Admin | Mengembalikan "User" atau "Admin" |

### D. ABSTRACT CLASS
```java
abstract class Person {
    private String name;
    private String email;
    private String nik;
    // getter & setter ...
    public abstract String getRole(); // wajib diimplementasi subclass
}

abstract class Ticket {
    private String ticketType;
    private double price;
    // getter & setter ...
    public abstract void printInfo();   // wajib diimplementasi subclass
    public abstract String getSeatCode();
}
```

### E. INTERFACE
```java
interface Verifiable {
    boolean verify();        // cek apakah identitas valid
    String getVerifyStatus(); // return "VERIFIED" / "UNVERIFIED"
}

interface Payable {
    boolean pay(String method, double amount); // simulasi pembayaran
    void printPaymentReceipt();
}

// Penerapan:
class User extends Person implements Verifiable { ... }
class Companion implements Verifiable { ... }
class Payment implements Payable { ... }
```

---

## 5. PSEUDOCODE FITUR UTAMA

### [A] Verifikasi Identitas
```
FUNCTION verifyIdentity(nik):
    IF panjang nik == 16 digit AND nik hanya angka:
        SET status = "VERIFIED"
        RETURN true
    ELSE:
        SET status = "UNVERIFIED"
        RETURN false
```

### [B] Lihat Daftar Konser
```
FUNCTION showConcerts(concertList):
    PRINT header tabel
    FOR SETIAP concert IN concertList:
        PRINT nomor, nama konser, tanggal, venue
    END FOR
```

### [C] Lihat Detail Jenis Tiket
```
FUNCTION showTicketTypes(concert):
    PRINT "Jenis tiket untuk: " + concert.name
    PRINT "1. Tribun   - Rp 750.000  - Sisa: " + concert.tribun
    PRINT "2. Festival - Rp 1.500.000 - Sisa: " + concert.festival
    PRINT "3. VIP      - Rp 2.500.000 - Sisa: " + concert.vip
```

### [D] Beli Tiket
```
FUNCTION buyTicket(user, concert, ticketType, buyerName):
    IF stok ticketType > 0:
        buat objek Ticket sesuai ticketType
        buat objek Booking(user, concert, ticket, buyerName, tanggalSekarang)
        kurangi stok concert.ticketType -= 1
        tambahkan ke user.bookingHistory
        PRINT "Tiket berhasil dipesan!"
    ELSE:
        PRINT "Maaf, tiket habis."
```

### [E] Simulasi Pembayaran
```
FUNCTION processPayment(totalAmount):
    PRINT "Pilih metode pembayaran:"
    PRINT "1. Transfer Bank"
    PRINT "2. QRIS"
    PRINT "3. Kartu Kredit"
    INPUT pilihan

    IF pilihan valid (1/2/3):
        SET payment.method = pilihan
        SET payment.status = "SUCCESS"
        PRINT "Pembayaran berhasil!"
        PRINT struk pembayaran
    ELSE:
        PRINT "Metode tidak valid."
```

### [F] Lihat Tiket Aktif
```
FUNCTION showActiveTickets(user):
    FOR SETIAP booking IN user.bookingHistory:
        IF booking.concert.date > tanggalHariIni:
            PRINT detail booking
        END IF
    END FOR
```

### [G] Lihat Histori Pembelian
```
FUNCTION showBookingHistory(user):
    IF bookingHistory kosong:
        PRINT "Belum ada histori pembelian."
    ELSE:
        FOR SETIAP booking IN user.bookingHistory:
            PRINT bookingId, nama konser, jenis tiket, tanggal beli
        END FOR
```

---

## 6. STRUKTUR MENU CONSOLE

```
============================================
     WELCOME TO GET CONCERT TICKET (GTC)
============================================
[1] Login sebagai User
[2] Login sebagai Admin
[0] Keluar
Pilih: _

--------------------------------------------
           MENU USER — Hikmani S.F.
--------------------------------------------
[1] Lihat Daftar Konser
[2] Lihat Detail Tiket
[3] Beli Tiket
[4] Pembayaran
[5] Tiket Aktif Saya
[6] Histori Pembelian
[0] Logout
Pilih: _

--------------------------------------------
           MENU ADMIN
--------------------------------------------
[1] Tambah Konser Baru
[2] Lihat Semua Konser
[3] Update Konser
[4] Atur Stok Tiket
[0] Logout
Pilih: _
```

---

## 7. CONTOH OUTPUT TERMINAL

```
============================================
     WELCOME TO GET CONCERT TICKET (GTC)
============================================
Pilih: 1

--- INPUT IDENTITAS ---
Nama lengkap : Hikmani Syariful Fajar
Email        : hikmani@email.com
NIK          : 3273011234567890
> Verifikasi identitas... BERHASIL ✓

--- TAMBAH TEMAN/KELUARGA ---
Tambah teman? (y/n): y
Nama teman   : JOHN DOE
NIK teman    : 3273019876543210
> Verifikasi JOHN DOE... BERHASIL ✓
Tambah lagi? (y/n): n

============================================
           MENU USER — Hikmani S.F.
============================================
Pilih: 1

--- DAFTAR KONSER BTS ---
No  Nama Konser              Tanggal        Venue
--  -----------------------  -------------  -------------------
1   BTS World Tour 2026      14 Jun 2026    GBK, Jakarta
2   BTS Encore Night         15 Jun 2026    GBK, Jakarta

Pilih: 3

--- BELI TIKET ---
Pilih konser (1-2): 1
Pilih jenis tiket:
  [A] Tribun   - Rp   750.000  (Sisa: 120)
  [B] Festival - Rp 1.500.000  (Sisa: 80)
  [C] VIP      - Rp 2.500.000  (Sisa: 20)
Pilih kelas: B

Pilih nama pembeli:
  [1] Hikmani Syariful Fajar (saya sendiri)
  [2] JOHN DOE
Pilih: 1

> Tiket berhasil dipesan!

--- PEMBAYARAN ---
Total: Rp 1.500.000
Pilih metode:
  [1] Transfer Bank
  [2] QRIS
  [3] Kartu Kredit
Pilih: 2

> Pembayaran via QRIS berhasil!

========================================
            STRUK PEMBELIAN
========================================
Booking ID  : GTC-20260524-001
Nama        : Hikmani Syariful Fajar
Konser      : BTS World Tour 2026
Tanggal     : 14 Juni 2026
Venue       : GBK, Jakarta
Kelas       : Festival
Seat        : F-080
Metode      : QRIS
Total       : Rp 1.500.000
Status      : LUNAS
========================================
```

---

## 8. CHECKLIST IMPLEMENTASI

### Fitur User
- [ ] Input identitas pembeli (nama, email, NIK)
- [ ] Verifikasi identitas (validasi NIK 16 digit)
- [ ] Tambah daftar teman/keluarga
- [ ] Verifikasi identitas tiap teman/keluarga
- [ ] Lihat daftar konser BTS
- [ ] Lihat detail jenis tiket (Tribun, Festival, VIP) + harga
- [ ] Beli tiket berdasarkan kategori
- [ ] Pilih nama pembeli (diri sendiri atau teman)
- [ ] Simulasi pembayaran (pilih metode → otomatis sukses)
- [ ] Cetak struk pembayaran
- [ ] Lihat tiket aktif (filter konser belum lewat)
- [ ] Lihat histori pembelian

### Fitur Admin
- [ ] Tambah konser baru (nama, tanggal, venue)
- [ ] Lihat semua konser
- [ ] Update detail konser
- [ ] Atur stok tiket per jenis (Tribun, Festival, VIP)

### Konsep OOP
- [ ] Abstract Class: `Person` dan `Ticket`
- [ ] Inheritance: `User` dan `Admin` extends `Person`
- [ ] Inheritance: `TribunTicket`, `FestivalTicket`, `VIPTicket` extends `Ticket`
- [ ] Encapsulation: semua atribut `private` + getter/setter
- [ ] Polymorphism: `printInfo()` dan `getPrice()` di-override tiap subclass Ticket
- [ ] Interface `Verifiable`: diterapkan di `User` dan `Companion`
- [ ] Interface `Payable`: diterapkan di `Payment`

### Teknis
- [ ] Program berjalan di console/terminal
- [ ] Tidak menggunakan database (data disimpan di List sementara)
- [ ] Tidak ada GUI
- [ ] Input menggunakan `Scanner`
- [ ] Menu dengan perulangan `while` dan pilihan dengan `switch`
