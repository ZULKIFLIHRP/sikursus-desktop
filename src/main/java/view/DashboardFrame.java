package view;

import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import util.UserSession;
import util.WindowManager;

public class DashboardFrame extends JFrame {

    private static final Color BG_PAGE = new Color(248, 250, 252);
    private static final Color BLUE_PRIMARY = new Color(37, 99, 235);
    private static final Color BLUE_SOFT = new Color(239, 246, 255);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);
    private static final Color BORDER_LIGHT = new Color(226, 232, 240);

    private JMenu menuMaster;
    private JMenu menuTransaksi;
    private JMenu menuLaporan;
    private JMenu menuSistem;

    private JMenuItem miPeserta;
    private JMenuItem miInstruktur;
    private JMenuItem miKursus;
    private JMenuItem miKelas;
    private JMenuItem miPendaftaran;
    private JMenuItem miPembayaran;
    private JMenuItem miLaporanPeserta;
    private JMenuItem miLaporanPendaftaran;
    private JMenuItem miLogout;
    private JMenuItem miTutup;

    private JPopupMenu popupDashboard;

    private JLabel lblUser;
    private JLabel lblRole;
    private JLabel lblWelcome;
    private JLabel lblSectionTitle;

    private SimpleModuleCard cardPeserta;
    private SimpleModuleCard cardInstruktur;
    private SimpleModuleCard cardKursus;
    private SimpleModuleCard cardKelas;
    private SimpleModuleCard cardPendaftaran;
    private SimpleModuleCard cardPembayaran;

    public DashboardFrame() {
        initUI();
        setLocationRelativeTo(null);
        applySession();
    }

    private void initUI() {
        setTitle("Dashboard - SIKURSUS DESKTOP");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(860, 560);
        setMinimumSize(new Dimension(780, 520));
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_PAGE);

        setupMenuBar();
        setupPopupMenu();

        // Header simpel berwarna putih dengan garis bawah tipis
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_LIGHT),
                BorderFactory.createEmptyBorder(14, 28, 14, 28)));

        JLabel lblBrand = new JLabel("SIKURSUS DESKTOP");
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBrand.setForeground(BLUE_PRIMARY);

        JPanel userBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        userBox.setOpaque(false);

        JLabel lblUserPrefix = new JLabel("User:");
        lblUserPrefix.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUserPrefix.setForeground(TEXT_MUTED);

        lblUser = new JLabel("-");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(TEXT_DARK);

        lblRole = new JLabel("ROLE");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblRole.setOpaque(true);
        lblRole.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnLogout.setForeground(new Color(185, 28, 28));
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(248, 113, 113), 1),
                BorderFactory.createEmptyBorder(4, 12, 4, 12)));
        btnLogout.addActionListener(e -> handleLogout());

        userBox.add(lblUserPrefix);
        userBox.add(lblUser);
        userBox.add(lblRole);
        userBox.add(btnLogout);

        header.add(lblBrand, BorderLayout.WEST);
        header.add(userBox, BorderLayout.EAST);

        // Konten utama bersih dan lapang (tanpa banner berlebihan)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_PAGE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 20, 28));
        mainPanel.setComponentPopupMenu(popupDashboard);

        lblWelcome = new JLabel("Selamat Datang!");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblWelcome.setForeground(TEXT_DARK);
        lblWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDesc = new JLabel(
                "Kelola data peserta, instruktur, kursus, jadwal kelas, dan transaksi pelatihan melalui satu panel terpadu.");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(TEXT_MUTED);
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel divider = new JPanel();
        divider.setBackground(BORDER_LIGHT);
        divider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        divider.setPreferredSize(new Dimension(780, 1));
        divider.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblSectionTitle = new JLabel("Akses Cepat Modul");
        lblSectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSectionTitle.setForeground(TEXT_DARK);
        lblSectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Grid 3 baris x 2 kolom simpel dan simetris
        JPanel grid = new JPanel(new GridLayout(3, 2, 14, 12));
        grid.setOpaque(false);
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);
        grid.setComponentPopupMenu(popupDashboard);

        cardPeserta = new SimpleModuleCard(
                "PESERTA", "Data Peserta", "Kelola biodata & riwayat peserta",
                this::openPeserta);

        cardInstruktur = new SimpleModuleCard(
                "INSTRUKTUR", "Data Instruktur", "Manajemen pengajar & keahlian",
                this::openInstruktur);

        cardKursus = new SimpleModuleCard(
                "KURSUS", "Program Kursus", "Daftar materi, kurikulum & biaya",
                this::openKursus);

        cardKelas = new SimpleModuleCard(
                "KELAS", "Jadwal Kelas", "Atur ruang, kuota & jadwal",
                this::openKelas);

        cardPendaftaran = new SimpleModuleCard(
                "TRANSAKSI", "Pendaftaran", "Registrasi peserta ke kelas baru",
                this::openPendaftaran);

        cardPembayaran = new SimpleModuleCard(
                "KEUANGAN", "Pembayaran", "Verifikasi tagihan & bukti bayar",
                this::openPembayaran);

        grid.add(cardPeserta);
        grid.add(cardInstruktur);
        grid.add(cardKursus);
        grid.add(cardKelas);
        grid.add(cardPendaftaran);
        grid.add(cardPembayaran);

        JLabel lblTips = new JLabel(
                "Tips: Klik kartu di atas untuk membuka modul, atau klik kanan di area kosong untuk menu pintasan.");
        lblTips.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblTips.setForeground(TEXT_MUTED);
        lblTips.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(lblWelcome);
        mainPanel.add(Box.createVerticalStrut(4));
        mainPanel.add(lblDesc);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(divider);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(lblSectionTitle);
        mainPanel.add(Box.createVerticalStrut(12));
        mainPanel.add(grid);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(lblTips);

        add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        menuMaster = new JMenu("Master Data");
        miPeserta = new JMenuItem("Data Peserta", loadIcon("/icons/peserta.png", "P", BLUE_PRIMARY));
        miInstruktur = new JMenuItem("Data Instruktur", loadIcon("/icons/instruktur.png", "I", new Color(14, 116, 144)));
        miKursus = new JMenuItem("Data Kursus", loadIcon("/icons/kursus.png", "K", new Color(79, 70, 229)));
        miKelas = new JMenuItem("Data Kelas", loadIcon("/icons/kelas.png", "K", new Color(2, 132, 199)));

        miPeserta.addActionListener(e -> openPeserta());
        miInstruktur.addActionListener(e -> openInstruktur());
        miKursus.addActionListener(e -> openKursus());
        miKelas.addActionListener(e -> openKelas());

        menuMaster.add(miPeserta);
        menuMaster.add(miInstruktur);
        menuMaster.add(miKursus);
        menuMaster.add(miKelas);

        menuTransaksi = new JMenu("Transaksi");
        miPendaftaran = new JMenuItem("Pendaftaran Kursus");
        miPembayaran = new JMenuItem("Pembayaran");
        miPendaftaran.addActionListener(e -> openPendaftaran());
        miPembayaran.addActionListener(e -> openPembayaran());
        menuTransaksi.add(miPendaftaran);
        menuTransaksi.add(miPembayaran);

        menuLaporan = new JMenu("Laporan");
        miLaporanPeserta = new JMenuItem("Laporan Peserta");
        miLaporanPendaftaran = new JMenuItem("Laporan Pendaftaran");
        miLaporanPeserta.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Modul laporan diaktifkan pada Pertemuan 12 dengan Jaspersoft Studio."));
        miLaporanPendaftaran.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Modul laporan diaktifkan pada Pertemuan 12 dengan Jaspersoft Studio."));
        menuLaporan.add(miLaporanPeserta);
        menuLaporan.add(miLaporanPendaftaran);

        menuSistem = new JMenu("Sistem");
        miLogout = new JMenuItem("Logout Akun");
        miTutup = new JMenuItem("Tutup Aplikasi");
        miLogout.addActionListener(e -> handleLogout());
        miTutup.addActionListener(e -> handleExit());
        menuSistem.add(miLogout);
        menuSistem.addSeparator();
        menuSistem.add(miTutup);

        menuBar.add(menuMaster);
        menuBar.add(menuTransaksi);
        menuBar.add(menuLaporan);
        menuBar.add(menuSistem);
        setJMenuBar(menuBar);
    }

    private void setupPopupMenu() {
        popupDashboard = new JPopupMenu();

        JMenuItem miTentang = new JMenuItem("Tentang Aplikasi");
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "SIKURSUS Desktop\nProject PBO - Program Studi Informatika"));

        JMenuItem miRefresh = new JMenuItem("Refresh");
        miRefresh.addActionListener(e -> applySession());

        popupDashboard.add(miTentang);
        popupDashboard.add(miRefresh);
    }

    private void applySession() {
        User user = UserSession.getCurrentUser();

        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Session tidak ditemukan. Silakan login ulang.");
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            return;
        }

        lblUser.setText(user.getUsername());
        lblRole.setText(user.getRole());
        lblWelcome.setText("Selamat Datang, " + user.getUsername() + "!");

        boolean admin = user.isAdmin();

        if (admin) {
            lblRole.setBackground(BLUE_SOFT);
            lblRole.setForeground(BLUE_PRIMARY);
            lblSectionTitle.setText("Akses Cepat Modul (Mode Administrator - Akses Penuh)");
        } else {
            lblRole.setBackground(new Color(236, 254, 255));
            lblRole.setForeground(new Color(14, 116, 144));
            lblSectionTitle.setText("Akses Cepat Modul (Mode Operator - Akses Terbatas)");
        }

        miInstruktur.setEnabled(admin);
        miKursus.setEnabled(admin);
        miKelas.setEnabled(admin);

        cardInstruktur.setModuleEnabled(admin);
        cardKursus.setModuleEnabled(admin);
        cardKelas.setModuleEnabled(admin);

        miPeserta.setEnabled(true);
        menuTransaksi.setEnabled(true);
        menuLaporan.setEnabled(true);
        cardPeserta.setModuleEnabled(true);
        cardPendaftaran.setModuleEnabled(true);
        cardPembayaran.setModuleEnabled(true);
    }

    private Icon loadIcon(String path, String fallbackLetter, Color fallbackColor) {
        URL url = getClass().getResource(path);
        if (url != null) {
            ImageIcon raw = new ImageIcon(url);
            Image scaled = raw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return createBadgeIcon(fallbackLetter, fallbackColor);
    }

    private Icon createBadgeIcon(String letter, Color bg) {
        int size = 20;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, size, size, 6, 6);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        FontMetrics fm = g2.getFontMetrics();
        int x = (size - fm.stringWidth(letter)) / 2;
        int y = (size + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(letter, x, y);
        g2.dispose();
        return new ImageIcon(img);
    }

    private void openPeserta() {
        WindowManager.open(PesertaFrame.class, PesertaFrame::new);
    }

    private void openInstruktur() {
        WindowManager.open(InstrukturFrame.class, InstrukturFrame::new);
    }

    private void openKursus() {
        WindowManager.open(KursusFrame.class, KursusFrame::new);
    }

    private void openKelas() {
        WindowManager.open(KelasFrame.class, KelasFrame::new);
    }

    private void openPendaftaran() {
        JOptionPane.showMessageDialog(this,
                "Modul Pendaftaran sedang dikembangkan pada tahap project berikutnya.");
    }

    private void openPembayaran() {
        JOptionPane.showMessageDialog(this,
                "Modul Pembayaran sedang dikembangkan pada tahap project berikutnya.");
    }

    private void handleLogout() {
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Logout dari aplikasi?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {
            WindowManager.closeAll();
            UserSession.clear();
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }

    private void handleExit() {
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Tutup aplikasi SIKURSUS?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private static class SimpleModuleCard extends JPanel {
        private boolean hovered = false;
        private boolean moduleEnabled = true;

        private final String activeDesc;
        private final JLabel lblTag;
        private final JLabel lblRight;
        private final JLabel lblTitle;
        private final JLabel lblDesc;

        SimpleModuleCard(String tag, String title, String desc, final Runnable onClick) {
            this.activeDesc = desc;
            setOpaque(false);
            setLayout(new BorderLayout(0, 6));
            setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setToolTipText("Klik untuk membuka modul");

            JPanel topRow = new JPanel(new BorderLayout());
            topRow.setOpaque(false);

            lblTag = new JLabel(tag);
            lblTag.setFont(new Font("Segoe UI", Font.BOLD, 10));
            lblTag.setForeground(BLUE_PRIMARY);

            lblRight = new JLabel("\u2192");
            lblRight.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblRight.setForeground(TEXT_MUTED);

            topRow.add(lblTag, BorderLayout.WEST);
            topRow.add(lblRight, BorderLayout.EAST);

            JPanel center = new JPanel();
            center.setOpaque(false);
            center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

            lblTitle = new JLabel(title);
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblTitle.setForeground(TEXT_DARK);

            lblDesc = new JLabel(desc);
            lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            lblDesc.setForeground(TEXT_MUTED);

            center.add(lblTitle);
            center.add(Box.createVerticalStrut(3));
            center.add(lblDesc);

            add(topRow, BorderLayout.NORTH);
            add(center, BorderLayout.CENTER);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (moduleEnabled) {
                        hovered = true;
                        repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (moduleEnabled && SwingUtilities.isLeftMouseButton(e)) {
                        onClick.run();
                    }
                }
            });
        }

        void setModuleEnabled(boolean enabled) {
            this.moduleEnabled = enabled;
            if (enabled) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setToolTipText("Klik untuk membuka modul");
                lblTag.setForeground(BLUE_PRIMARY);
                lblTitle.setForeground(TEXT_DARK);
                lblDesc.setText(activeDesc);
                lblDesc.setForeground(TEXT_MUTED);
                lblRight.setText("\u2192");
                lblRight.setForeground(TEXT_MUTED);
                lblRight.setFont(new Font("Segoe UI", Font.BOLD, 13));
            } else {
                hovered = false;
                setCursor(Cursor.getDefaultCursor());
                setToolTipText("Khusus hak akses Administrator");
                lblTag.setForeground(new Color(148, 163, 184));
                lblTitle.setForeground(new Color(148, 163, 184));
                lblDesc.setText("Khusus hak akses Administrator");
                lblDesc.setForeground(new Color(148, 163, 184));
                lblRight.setText("TERKUNCI (ADMIN)");
                lblRight.setForeground(new Color(185, 28, 28));
                lblRight.setFont(new Font("Segoe UI", Font.BOLD, 9));
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(moduleEnabled ? (hovered ? BLUE_SOFT : Color.WHITE) : new Color(241, 245, 249));
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

            g2.setColor(hovered && moduleEnabled ? BLUE_PRIMARY : BORDER_LIGHT);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}