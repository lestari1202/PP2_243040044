package Pertemuan1.Tugas1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FormPendaftaran {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormPendaftaran::buatDanTampilkanGUI);
    }

    private static void buatDanTampilkanGUI() {
        JFrame frame = new JFrame("Formulir Pendaftaran Kegiatan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Komponen Kelompok 1: Identitas
        JTextField namaField = new JTextField(20);
        JTextField npmField = new JTextField("243040044", 20);

        // Komponen Kelompok 2: Pilihan Kegiatan & Sesi
        String[] pilihanKegiatan = {"Workshop Java Swing", "Seminar Cybersecurity", "Pelatihan Git & Docker"};
        JComboBox<String> kegiatanComboBox = new JComboBox<>(pilihanKegiatan);

        JRadioButton rbPagi = new JRadioButton("Pagi", true);
        JRadioButton rbSiang = new JRadioButton("Siang");
        ButtonGroup bgSesi = new ButtonGroup();
        bgSesi.add(rbPagi);
        bgSesi.add(rbSiang);

        JCheckBox chkSertifikat = new JCheckBox("Sertifikat Cetak");

        // Komponen Kelompok 3: Catatan
        JTextArea catatanArea = new JTextArea(4, 20);
        catatanArea.setLineWrap(true);
        catatanArea.setWrapStyleWord(true);
        JScrollPane scrollCatatan = new JScrollPane(catatanArea);

        JLabel statusLabel = new JLabel("Silakan isi formulir pendaftaran.");
        JButton simpanButton = new JButton("Daftar");
        JButton keluarButton = new JButton("Keluar");

        // Panel Form (Mengelompokkan Identitas, Pilihan Kegiatan, dan Catatan)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        // Identitas: Nama
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1;
        formPanel.add(namaField, gbc);

        // Identitas: NPM
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("NPM:"), gbc);
        gbc.gridx = 1;
        formPanel.add(npmField, gbc);

        // Pilihan Kegiatan
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Pilihan Kegiatan:"), gbc);
        gbc.gridx = 1;
        formPanel.add(kegiatanComboBox, gbc);

        // Sesi (JRadioButton)
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Sesi:"), gbc);
        gbc.gridx = 1;
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.add(rbPagi);
        panelRadio.add(rbSiang);
        formPanel.add(panelRadio, gbc);

        // Fasilitas (JCheckBox)
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Fasilitas Tambahan:"), gbc);
        gbc.gridx = 1;
        formPanel.add(chkSertifikat, gbc);

        // Catatan (JTextArea)
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Catatan/Motivasi:"), gbc);
        gbc.gridx = 1;
        formPanel.add(scrollCatatan, gbc);

        // Panel Tindakan (Bagian Bawah)
        JPanel tindakanPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        tindakanPanel.add(statusLabel);
        tindakanPanel.add(simpanButton);
        tindakanPanel.add(keluarButton);

        // Panel Utama dengan BorderLayout (Kombinasi minimal 2 Layout Manager)
        JPanel utamaPanel = new JPanel(new BorderLayout(10, 10));
        utamaPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel judulLabel = new JLabel("FORMULIR PENDAFTARAN KEGIATAN", JLabel.CENTER);
        judulLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        utamaPanel.add(judulLabel, BorderLayout.NORTH);
        utamaPanel.add(formPanel, BorderLayout.CENTER);
        utamaPanel.add(tindakanPanel, BorderLayout.SOUTH);

        // Event handling sederhana (sesuai gaya contoh dosen)
        simpanButton.addActionListener(event -> {
            String nama = namaField.getText().trim();
            if (nama.isEmpty()) {
                statusLabel.setText("Nama wajib diisi!");
                namaField.requestFocusInWindow();
                return;
            }
            statusLabel.setText("Pendaftaran berhasil untuk: " + nama);
        });

        keluarButton.addActionListener(event -> System.exit(0));

        frame.setContentPane(utamaPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
