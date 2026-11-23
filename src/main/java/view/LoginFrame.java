package view;

import Model.User;
import dao.UserDAO;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.AbstractBorder;
import util.UserSession;

public class LoginFrame extends JFrame {

    private static final Color BG_CANVAS = new Color(239, 246, 255);
    private static final Color NAVY_DARK = new Color(15, 23, 42);
    private static final Color BLUE_ROYAL = new Color(29, 78, 216);
    private static final Color BLUE_PRIMARY = new Color(37, 99, 235);
    private static final Color BLUE_HOVER = new Color(30, 64, 175);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);
    private static final Color BORDER_SLATE = new Color(203, 213, 225);
    private static final Color INPUT_BG = new Color(248, 250, 252);
    private static final Color BTN_OUTLINE_HOVER = new Color(241, 245, 249);

    private final UserDAO userDAO = new UserDAO();

    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JCheckBox chkShowPassword;
    private JButton btnLogin;
    private JButton btnKeluar;
    private JLabel lblInfo;
    private char defaultEchoChar;

    public LoginFrame() {
        initUI();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnLogin);
    }

    private void initUI() {
        setTitle("Login - SIKURSUS Desktop");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520, 540);
        setResizable(false);

        JPanel backdrop = new JPanel(new GridBagLayout());
        backdrop.setBackground(BG_CANVAS);

        // Kartu login terpusat (berbeda dari layout split-screen)
        RoundedCard card = new RoundedCard(16);
        card.setPreferredSize(new Dimension(420, 450));
        card.setLayout(new BorderLayout());

        // Header biru navy di bagian atas kartu
        BlueHeaderPanel header = new BlueHeaderPanel();
        header.setPreferredSize(new Dimension(420, 126));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(24, 28, 20, 28));

        JLabel lblBadge = new JLabel("PORTAL AKSES");
        lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblBadge.setForeground(new Color(147, 197, 253));
        lblBadge.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel("SIKURSUS Desktop");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Sistem Informasi Manajemen Kursus & Pelatihan");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(219, 234, 254));
        lblSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(lblBadge);
        header.add(Box.createVerticalStrut(4));
        header.add(lblTitle);
        header.add(Box.createVerticalStrut(4));
        header.add(lblSubtitle);

        // Body form putih
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));

        lblInfo = new JLabel("Masukkan akun SIKURSUS untuk melanjutkan");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInfo.setForeground(TEXT_MUTED);
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(TEXT_DARK);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsername = new JTextField();
        styleInputField(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(TEXT_DARK);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwdPassword = new JPasswordField();
        defaultEchoChar = pwdPassword.getEchoChar();
        styleInputField(pwdPassword);

        chkShowPassword = new JCheckBox("Tampilkan password");
        chkShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chkShowPassword.setForeground(TEXT_MUTED);
        chkShowPassword.setOpaque(false);
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chkShowPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                pwdPassword.setEchoChar((char) 0);
            } else {
                pwdPassword.setEchoChar(defaultEchoChar);
            }
        });

        // Tombol berdampingan (Keluar di kiri, Login di kanan)
        JPanel btnRow = new JPanel(new GridLayout(1, 2, 12, 0));
        btnRow.setOpaque(false);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        btnKeluar = createStyledButton("Keluar", Color.WHITE, BTN_OUTLINE_HOVER, TEXT_DARK, BORDER_SLATE);
        btnKeluar.addActionListener(e -> System.exit(0));

        btnLogin = createStyledButton("Masuk", BLUE_PRIMARY, BLUE_HOVER, Color.WHITE, BLUE_PRIMARY);
        btnLogin.addActionListener(e -> handleLogin());

        btnRow.add(btnKeluar);
        btnRow.add(btnLogin);

        formPanel.add(lblInfo);
        formPanel.add(Box.createVerticalStrut(18));
        formPanel.add(lblUser);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(14));
        formPanel.add(lblPass);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(pwdPassword);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(chkShowPassword);
        formPanel.add(Box.createVerticalStrut(22));
        formPanel.add(btnRow);

        card.add(header, BorderLayout.NORTH);
        card.add(formPanel, BorderLayout.CENTER);

        backdrop.add(card);
        setContentPane(backdrop);
    }

    private void styleInputField(final JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setForeground(TEXT_DARK);
        field.setBackground(INPUT_BG);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setPreferredSize(new Dimension(340, 40));
        field.setBorder(new RoundedBorder(BORDER_SLATE, 1, 8, new Insets(6, 12, 6, 12)));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBackground(Color.WHITE);
                field.setBorder(new RoundedBorder(BLUE_PRIMARY, 2, 8, new Insets(5, 11, 5, 11)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBackground(INPUT_BG);
                field.setBorder(new RoundedBorder(BORDER_SLATE, 1, 8, new Insets(6, 12, 6, 12)));
            }
        });
    }

    private JButton createStyledButton(String text, final Color bg, final Color hoverBg,
            final Color fg, final Color borderColor) {
        final JButton btn = new JButton(text) {
            private boolean hovered = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hovered = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isEnabled() ? (hovered ? hoverBg : bg) : BORDER_SLATE);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.setColor(fg);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        char[] password = pwdPassword.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password wajib diisi.");
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        btnLogin.setEnabled(false);

        try {
            User user = userDAO.authenticate(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(this,
                        "Login gagal. Periksa username, password, atau status user.",
                        "Login Gagal",
                        JOptionPane.WARNING_MESSAGE);
                pwdPassword.setText("");
                pwdPassword.requestFocus();
                return;
            }

            UserSession.setCurrentUser(user);
            dispose();
            SwingUtilities.invokeLater(
                    () -> new DashboardFrame().setVisible(true));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Tidak dapat mengakses database: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Arrays.fill(password, '\0');
            btnLogin.setEnabled(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private static class RoundedCard extends JPanel {
        private final int radius;

        RoundedCard(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(new Color(219, 234, 254));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class BlueHeaderPanel extends JPanel {
        BlueHeaderPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, NAVY_DARK, getWidth(), getHeight(), BLUE_ROYAL);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight() + 16, 16, 16);

            g2.setColor(new Color(255, 255, 255, 18));
            g2.fillOval(getWidth() - 110, -40, 150, 150);
            g2.fillOval(getWidth() - 60, 45, 90, 90);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;
        private final int radius;
        private final Insets insets;

        RoundedBorder(Color color, int thickness, int radius, Insets insets) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
            this.insets = insets;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            int offset = thickness / 2;
            g2.drawRoundRect(x + offset, y + offset,
                    width - thickness - 1, height - thickness - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets target) {
            target.set(insets.top, insets.left, insets.bottom, insets.right);
            return target;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}