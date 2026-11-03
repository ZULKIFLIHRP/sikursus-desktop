package view;

import model.Kursus;
import javax.swing.JOptionPane;

public class FormPendaftaranKursus extends javax.swing.JFrame {
 private javax.swing.ButtonGroup grupJenis;
public FormPendaftaranKursus() {
     initComponents();

    // Pengaturan jendela aplikasi
    setTitle("SIKURSUS Desktop - Pendaftaran Kursus");
    setLocationRelativeTo(null);
    setResizable(false);

    // Mengatur daftar pilihan kursus
    cmbKursus.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            new String[] {
                "-- Pilih Kursus --",
                "Java Desktop",
                "Data Science",
                "Web Programming",
                "UI/UX Design"
            }
        )
    );

    cmbKursus.setSelectedIndex(0);

    // Mengatur pilihan jenis peserta
    grupJenis = new javax.swing.ButtonGroup();
    grupJenis.add(jRadioButton1);
    grupJenis.add(jRadioButton2);

    // Mengatur area ringkasan pendaftaran
    jTextArea1.setEditable(false);
    jTextArea1.setLineWrap(true);
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.setText("");

    // Mengosongkan kolom kode akses
    txtKodeAkses.setText("");

    // Mengatur label informasi kode akses
    lblInfoKode.setText(
        "Kode akses demo praktikum: PBO2026"
    );
    lblInfoKode.setOpaque(true);
    lblInfoKode.setBackground(
        new java.awt.Color(255, 255, 180)
    );
    lblInfoKode.setForeground(
        java.awt.Color.BLACK
    );
    lblInfoKode.setVisible(false);

}

private double hitungTotal(double biaya, int jumlah) {
    return biaya * jumlah;
}

// Menentukan biaya dasar berdasarkan kursus
private double hitungBiayaDasar(String kursus) {
    switch (kursus) {
        case "Java Desktop":
            return 500000;
        case "Data Science":
            return 650000;
        case "Web Programming":
            return 450000;
        case "UI/UX Design":
            return 400000;
        default:
            return 0;
    }
}

// Menghitung biaya seluruh fasilitas yang dipilih
private double hitungBiayaFasilitas() {
    double total = 0;

    if (jCheckBox1.isSelected()) {
        total += 50000;  // Modul Cetak
    }

    if (jCheckBox2.isSelected()) {
        total += 75000;  // Sertifikat
    }

    if (jCheckBox3.isSelected()) {
        total += 40000;  // Akses Rekaman
    }

    return total;
}

// Membuat daftar fasilitas dengan array dan perulangan
private String buatRingkasanFasilitas() {
    String[] fasilitas = new String[3];
    int jumlah = 0;

    if (jCheckBox1.isSelected()) {
        fasilitas[jumlah++] = "Modul Cetak";
    }

    if (jCheckBox2.isSelected()) {
        fasilitas[jumlah++] = "Sertifikat";
    }

    if (jCheckBox3.isSelected()) {
        fasilitas[jumlah++] = "Akses Rekaman";
    }

    if (jumlah == 0) {
        return "- Tidak ada fasilitas tambahan\n";
    }

    StringBuilder hasil = new StringBuilder();

    for (int i = 0; i < jumlah; i++) {
        hasil.append("- ")
             .append(fasilitas[i])
             .append("\n");
    }

    return hasil.toString();
}

// Memformat angka menjadi format rupiah Indonesia
private String formatRupiah(double nilai) {
    java.text.NumberFormat format =
        java.text.NumberFormat.getNumberInstance(
            new java.util.Locale("id", "ID")
        );

    format.setMaximumFractionDigits(0);
    format.setMinimumFractionDigits(0);

    return "Rp" + format.format(nilai);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtNamaPendaftar = new javax.swing.JTextField();
        cmbKursus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        lblInfoKode = new javax.swing.JLabel();
        txtKodeAkses = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama Peserta");

        txtNamaPendaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPendaftarActionPerformed(evt);
            }
        });

        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java Desktop Fundamental", "Data Science", "UI/UX" }));
        cmbKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKursusActionPerformed(evt);
            }
        });

        jButton1.setText("Proses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Jenis Peserta");

        jLabel4.setText("Nama Kursus");

        jLabel5.setText("FORM PENDAFTARAN KURSUS");

        jLabel6.setText("Kode Akses");

        jRadioButton1.setText("Mahasiswa");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Umum");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Fasilitas");

        jCheckBox1.setText("Modul Cetak");

        jCheckBox2.setText("Sertifikat");

        jCheckBox3.setText("Akses Rekaman");

        jLabel9.setText("Ringkasan");

        lblInfoKode.setBackground(new java.awt.Color(255, 255, 0));
        lblInfoKode.setText("“Kode akses demo praktikum: PBO2026”");
        lblInfoKode.setOpaque(true);

        txtKodeAkses.setText("jPasswordField1");
        txtKodeAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeAksesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(131, 131, 131)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(40, 40, 40))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(42, 42, 42)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jRadioButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButton2))
                                        .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCheckBox1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox3))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(36, 36, 36)
                                            .addComponent(txtKodeAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addComponent(txtNamaPendaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblInfoKode))))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNamaPendaftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jCheckBox1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInfoKode)
                            .addComponent(txtKodeAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox2))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKursusActionPerformed
    String namaKursus = (String) cmbKursus.getSelectedItem();
    System.out.println("Kursus dipilih: " + namaKursus);
    }//GEN-LAST:event_cmbKursusActionPerformed

    private void txtNamaPendaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPendaftarActionPerformed

    txtKodeAkses.requestFocus();
    }//GEN-LAST:event_txtNamaPendaftarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


    int pilihan = JOptionPane.showConfirmDialog(
        this,
        "Kosongkan seluruh input?",
        "Konfirmasi Reset",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (pilihan == JOptionPane.YES_OPTION) {
        txtNamaPendaftar.setText("");
        txtKodeAkses.setText("");

        cmbKursus.setSelectedIndex(0);

        grupJenis.clearSelection();

        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);

        jTextArea1.setText("");
        lblInfoKode.setVisible(false);

        txtNamaPendaftar.requestFocusInWindow();
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  // 1. Mengambil data dari form
    String nama = txtNamaPendaftar.getText().trim();
    String kode = new String(txtKodeAkses.getPassword());

    // 2. Validasi nama peserta
    if (nama.isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "Nama peserta wajib diisi.",
            "Validasi",
            JOptionPane.WARNING_MESSAGE
        );
        txtNamaPendaftar.requestFocusInWindow();
        return;
    }

    // 3. Validasi kode akses
    if (!kode.equals("PBO2026")) {
        lblInfoKode.setText(
            "Kode akses demo praktikum: PBO2026"
        );
        lblInfoKode.setOpaque(true);
        lblInfoKode.setBackground(
            new java.awt.Color(255, 255, 180)
        );
        lblInfoKode.setForeground(
            java.awt.Color.BLACK
        );
        lblInfoKode.setVisible(true);

        JOptionPane.showMessageDialog(
            this,
            "Kode akses salah.",
            "Validasi",
            JOptionPane.WARNING_MESSAGE
        );

        txtKodeAkses.setText("");
        txtKodeAkses.requestFocusInWindow();
        return;
    }

    lblInfoKode.setVisible(false);

    // 4. Validasi jenis peserta
    if (!jRadioButton1.isSelected()
            && !jRadioButton2.isSelected()) {
        JOptionPane.showMessageDialog(
            this,
            "Silakan pilih jenis peserta.",
            "Validasi",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // 5. Validasi kursus
    if (cmbKursus.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(
            this,
            "Silakan pilih kursus terlebih dahulu.",
            "Validasi",
            JOptionPane.WARNING_MESSAGE
        );
        cmbKursus.requestFocusInWindow();
        return;
    }

    // 6. Mengambil pilihan pengguna
    String jenis = jRadioButton1.isSelected()
            ? "Mahasiswa" : "Umum";

    String kursus = cmbKursus.getSelectedItem().toString();

    // 7. Menghitung biaya, diskon, dan fasilitas
    double biayaDasar = hitungBiayaDasar(kursus);

    double diskon = jenis.equals("Mahasiswa")
            ? biayaDasar * 0.10 : 0;

    double biayaFasilitas = hitungBiayaFasilitas();

    double total = biayaDasar - diskon + biayaFasilitas;

    // 8. Membuat daftar fasilitas
    String fasilitas = buatRingkasanFasilitas();

    // 9. Membuat ringkasan pendaftaran
    String ringkasan =
            "PENDAFTARAN KURSUS\n"
            + "================================\n"
            + "Nama           : " + nama + "\n"
            + "Jenis Peserta  : " + jenis + "\n"
            + "Kursus         : " + kursus + "\n"
            + "Biaya Dasar    : " + formatRupiah(biayaDasar) + "\n"
            + "Diskon         : " + formatRupiah(diskon) + "\n"
            + "Fasilitas:\n"
            + fasilitas
            + "Biaya Fasilitas: " + formatRupiah(biayaFasilitas) + "\n"
            + "================================\n"
            + "TOTAL          : " + formatRupiah(total);

    // 10. Menampilkan ringkasan
    jTextArea1.setText(ringkasan);
    jTextArea1.setCaretPosition(0);

    // 11. Notifikasi keberhasilan
    JOptionPane.showMessageDialog(
        this,
        "Pendaftaran berhasil diproses!",
        "Pendaftaran Berhasil",
        JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void txtKodeAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeAksesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeAksesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendaftaranKursus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblInfoKode;
    private javax.swing.JPasswordField txtKodeAkses;
    private javax.swing.JTextField txtNamaPendaftar;
    // End of variables declaration//GEN-END:variables
}
