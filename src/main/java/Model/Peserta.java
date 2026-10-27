package model;

public class Peserta extends Orang {

    private String nim;
    private String prodi;
    private String jenisKelamin;
    private String email;

    // Constructor lama (tetap dipertahankan)
    public Peserta(int id, String nama, String noHp,
                   String nim, String prodi) {
        super(id, nama, noHp);
        setNim(nim);
        setProdi(prodi);
    }

    // Constructor baru untuk kebutuhan JDBC
    public Peserta(String nim, String nama, String jenisKelamin,
                   String prodi, String noHp, String email) {
        super(0, nama, noHp);
        setNim(nim);
        setProdi(prodi);
        setJenisKelamin(jenisKelamin);
        setEmail(email);
    }

    // Getter
    public int getIdPeserta() {
        return getId();
    }

    public String getNim() {
        return nim;
    }

    public String getProdi() {
        return prodi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    // Setter
    public void setIdPeserta(int idPeserta) {
        setId(idPeserta);
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (!"L".equals(jenisKelamin) && !"P".equals(jenisKelamin)) {
            throw new IllegalArgumentException(
                    "Jenis kelamin harus L atau P");
        }
        this.jenisKelamin = jenisKelamin;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email.trim();
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi()
                + " | JK: " + getJenisKelamin()
                + " | Email: "
                + (getEmail().isEmpty() ? "-" : getEmail());
    }
}