package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class KursusFrame extends JFrame {

    private static final Color NAVY_DARK = new Color(15, 23, 42);
    private static final Color BLUE_ROYAL = new Color(29, 78, 216);
    private static final Color BG_PAGE = new Color(241, 245, 249);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);

    public KursusFrame() {
        setTitle("Master Kursus - SIKURSUS Desktop");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(560, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, NAVY_DARK, getWidth(), getHeight(), BLUE_ROYAL));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel lblTitle = new JLabel("Master Data Kursus");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Pengaturan program pelatihan, kurikulum, dan biaya kursus");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(219, 234, 254));

        header.add(lblTitle);
        header.add(Box.createVerticalStrut(4));
        header.add(lblSub);

        JPanel body = new JPanel();
        body.setBackground(BG_PAGE);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(28, 28, 24, 28));

        JPanel infoCard = new JPanel();
        infoCard.setBackground(Color.WHITE);
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel lblHeading = new JLabel("Modul Master Kursus Siap Diintegrasikan");
        lblHeading.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHeading.setForeground(TEXT_DARK);

        JLabel lblDesc = new JLabel("");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(TEXT_MUTED);

        JButton btnTutup = new JButton("Tutup Jendela");
        btnTutup.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setBackground(BLUE_ROYAL);
        btnTutup.setFocusPainted(false);
        btnTutup.setBorderPainted(false);
        btnTutup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTutup.setMaximumSize(new Dimension(140, 36));
        btnTutup.addActionListener(e -> dispose());

        infoCard.add(lblHeading);
        infoCard.add(Box.createVerticalStrut(8));
        infoCard.add(lblDesc);
        infoCard.add(Box.createVerticalStrut(18));
        infoCard.add(btnTutup);

        body.add(infoCard);

        add(header, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
    }
}