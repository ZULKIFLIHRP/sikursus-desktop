package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import model.Peserta;

public class PesertaDao {

    // Method untuk menyimpan data peserta
    public boolean insert(Peserta p) throws SQLException {

        String sql = "INSERT INTO peserta "
                + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp());
            ps.setString(6, p.getEmail());

            return ps.executeUpdate() == 1;
        }
        
    }

    // Method untuk mengambil seluruh data peserta
public DefaultTableModel getAll() throws SQLException {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("NIM");
    model.addColumn("Nama");
    model.addColumn("JK");
    model.addColumn("Prodi");
    model.addColumn("No. Hp");
    model.addColumn("Email");

    String sql = "SELECT id_peserta, nim, nama, jenis_kelamin, prodi, no_hp, email "
               + "FROM peserta";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id_peserta"),
                rs.getString("nim"),
                rs.getString("nama"),
                rs.getString("jenis_kelamin"),
                rs.getString("prodi"),
                rs.getString("no_hp"),
                rs.getString("email")
            });

        }
    }

    return model;
}
// Method untuk mencari peserta berdasarkan NIM atau Nama
public DefaultTableModel search(String keyword) throws SQLException {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("NIM");
    model.addColumn("Nama");
    model.addColumn("JK");
    model.addColumn("Prodi");
    model.addColumn("No. Hp");
    model.addColumn("Email");

    String sql = "SELECT id_peserta, nim, nama, jenis_kelamin, "
               + "prodi, no_hp, email "
               + "FROM peserta "
               + "WHERE nim LIKE ? OR nama LIKE ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id_peserta"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("prodi"),
                    rs.getString("no_hp"),
                    rs.getString("email")
                });

            }
        }
    }

    return model;
}
public boolean update(int idPeserta, Peserta p) throws SQLException {
    String sql = "UPDATE peserta SET nim = ?, nama = ?, "
            + "jenis_kelamin = ?, prodi = ?, no_hp = ?, email = ? "
            + "WHERE id_peserta = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, p.getNim());
        ps.setString(2, p.getNama());
        ps.setString(3, p.getJenisKelamin());
        ps.setString(4, p.getProdi());
        ps.setString(5, p.getNoHp());
        ps.setString(6, p.getEmail());
        ps.setInt(7, idPeserta);

        return ps.executeUpdate() > 0;
    }
}
public boolean delete(int idPeserta) throws SQLException {
    String sql = "DELETE FROM peserta WHERE id_peserta = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idPeserta);

        return ps.executeUpdate() > 0;
    }
}
}