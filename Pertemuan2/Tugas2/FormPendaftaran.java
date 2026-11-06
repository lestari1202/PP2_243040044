package Pertemuan2.Tugas2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormPendaftaran {
    private static final String FORM = "FORM";
    private static final String RINGKASAN = "RINGKASAN";
    private static final String BANTUAN = "BANTUAN";

    private final JFrame frame = new JFrame("Form Pendaftaran Kegiatan - Tugas 2");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    private final JTextField namaField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField umurField = new JTextField(6);
    private final JComboBox<String> kegiatanComboBox = new JComboBox<>(new String[]{
        "-- Pilih Kegiatan --",
        "Workshop Pemrograman Java Swing",
        "Pelatihan Git & GitHub Dasar",
        "Seminar UI/UX Design",
        "Bootcamp Web Development"
    });

    private final JLabel pesanLabel = new JLabel(" ");
    private final JTextArea ringkasanArea = new JTextArea(8, 30);
    private final JTextArea bantuanArea = new JTextArea(8, 30);

    private boolean adaPerubahan = false;

    public FormPendaftaran() {
        inisialisasiKomponen();
    }

    private void inisialisasiKomponen() {
        // 1. Panel Form
        JPanel formSubPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formSubPanel.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1; 
        formSubPanel.add(namaField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formSubPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; 
        formSubPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formSubPanel.add(new JLabel("Umur (15-100):"), gbc);
        gbc.gridx = 1; 
        formSubPanel.add(umurField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formSubPanel.add(new JLabel("Pilihan Kegiatan:"), gbc);
        gbc.gridx = 1; 
        formSubPanel.add(kegiatanComboBox, gbc);

        JPanel tombolFormPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        JButton simpanButton = new JButton("Simpan / Tampilkan");
        JButton resetButton = new JButton("Reset");
        JButton bantuanButton = new JButton("Bantuan");
        JButton keluarButton = new JButton("Keluar");

        tombolFormPanel.add(simpanButton);
        tombolFormPanel.add(resetButton);
        tombolFormPanel.add(bantuanButton);
        tombolFormPanel.add(keluarButton);

        pesanLabel.setForeground(Color.RED);

        JPanel panelFormUtama = new JPanel(new BorderLayout(10, 10));
        panelFormUtama.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelFormUtama.add(formSubPanel, BorderLayout.CENTER);

        JPanel bawahFormPanel = new JPanel(new BorderLayout());
        bawahFormPanel.add(pesanLabel, BorderLayout.NORTH);
        bawahFormPanel.add(tombolFormPanel, BorderLayout.SOUTH);
        panelFormUtama.add(bawahFormPanel, BorderLayout.SOUTH);

        // 2. Panel Ringkasan
        ringkasanArea.setEditable(false);
        ringkasanArea.setLineWrap(true);
        ringkasanArea.setWrapStyleWord(true);

        JPanel panelRingkasanUtama = new JPanel(new BorderLayout(10, 10));
        panelRingkasanUtama.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelRingkasanUtama.add(new JLabel("Ringkasan Data Pendaftaran"), BorderLayout.NORTH);
        panelRingkasanUtama.add(new JScrollPane(ringkasanArea), BorderLayout.CENTER);

        JButton kembaliDariRingkasan = new JButton("Kembali");
        JPanel bawahRingkasan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawahRingkasan.add(kembaliDariRingkasan);
        panelRingkasanUtama.add(bawahRingkasan, BorderLayout.SOUTH);

        // 3. Panel Bantuan
        bantuanArea.setEditable(false);
        bantuanArea.setLineWrap(true);
        bantuanArea.setWrapStyleWord(true);
        bantuanArea.setText("PANDUAN PENGISIAN FORM:\n\n" +
                "1. Nama Lengkap wajib diisi.\n" +
                "2. Email harus memiliki format valid (mengandung '@' dan '.').\n" +
                "3. Umur harus angka bulat antara 15 sampai 100 tahun.\n" +
                "4. Pilihan Kegiatan wajib dipilih dari dropdown.\n" +
                "5. Tekan tombol 'Simpan / Tampilkan' untuk melihat ringkasan.");

        JPanel panelBantuanUtama = new JPanel(new BorderLayout(10, 10));
        panelBantuanUtama.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelBantuanUtama.add(new JLabel("Petunjuk Bantuan"), BorderLayout.NORTH);
        panelBantuanUtama.add(new JScrollPane(bantuanArea), BorderLayout.CENTER);

        JButton kembaliDariBantuan = new JButton("Kembali");
        JPanel bawahBantuan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawahBantuan.add(kembaliDariBantuan);
        panelBantuanUtama.add(bawahBantuan, BorderLayout.SOUTH);

        mainPanel.add(panelFormUtama, FORM);
        mainPanel.add(panelRingkasanUtama, RINGKASAN);
        mainPanel.add(panelBantuanUtama, BANTUAN);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prosesKeluar();
            }
        });

        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        cardLayout.show(mainPanel, FORM);

        // Event Listeners
        simpanButton.addActionListener(e -> tampilkanRingkasan());
        resetButton.addActionListener(e -> resetForm());
        bantuanButton.addActionListener(e -> cardLayout.show(mainPanel, BANTUAN));
        keluarButton.addActionListener(e -> prosesKeluar());

        kembaliDariRingkasan.addActionListener(e -> {
            cardLayout.show(mainPanel, FORM);
            namaField.requestFocusInWindow();
        });

        kembaliDariBantuan.addActionListener(e -> {
            cardLayout.show(mainPanel, FORM);
            namaField.requestFocusInWindow();
        });

        DocumentListener listenerPerubahan = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { adaPerubahan = true; }
            public void removeUpdate(DocumentEvent e) { adaPerubahan = true; }
            public void changedUpdate(DocumentEvent e) { adaPerubahan = true; }
        };
        namaField.getDocument().addDocumentListener(listenerPerubahan);
        emailField.getDocument().addDocumentListener(listenerPerubahan);
        umurField.getDocument().addDocumentListener(listenerPerubahan);
        kegiatanComboBox.addActionListener(e -> adaPerubahan = true);
    }

    private void tampilkanRingkasan() {
        String nama = namaField.getText().trim();
        String email = emailField.getText().trim();
        String teksUmur = umurField.getText().trim();
        int pilihanIndex = kegiatanComboBox.getSelectedIndex();

        if (nama.isEmpty()) {
            tampilkanKesalahan("Nama wajib diisi!", namaField);
            return;
        }

        if (email.isEmpty() || !email.contains("@") || !email.contains(".") || email.indexOf('@') > email.lastIndexOf('.')) {
            tampilkanKesalahan("Email harus berformat valid! Contoh: nama@domain.com", emailField);
            return;
        }

        if (teksUmur.isEmpty()) {
            tampilkanKesalahan("Umur wajib diisi!", umurField);
            return;
        }

        final int umur;
        try {
            umur = Integer.parseInt(teksUmur);
        } catch (NumberFormatException e) {
            tampilkanKesalahan("Umur harus berupa bilangan bulat!", umurField);
            return;
        }

        if (umur < 15 || umur > 100) {
            tampilkanKesalahan("Umur harus berada pada rentang 15 - 100 tahun!", umurField);
            return;
        }

        if (pilihanIndex == 0) {
            pesanLabel.setText("Kegiatan wajib dipilih!");
            kegiatanComboBox.requestFocusInWindow();
            return;
        }

        String kegiatan = (String) kegiatanComboBox.getSelectedItem();

        String ringkasan = "=== RINGKASAN PENDAFTARAN ===\n\n" +
                "Nama Lengkap     : " + nama + "\n" +
                "Email            : " + email + "\n" +
                "Umur             : " + umur + " tahun\n" +
                "Pilihan Kegiatan : " + kegiatan + "\n\n" +
                "Status: Data valid dan ditampilkan di memori.";

        ringkasanArea.setText(ringkasan);
        ringkasanArea.setCaretPosition(0);
        pesanLabel.setText(" ");
        cardLayout.show(mainPanel, RINGKASAN);
    }

    private void resetForm() {
        namaField.setText("");
        emailField.setText("");
        umurField.setText("");
        kegiatanComboBox.setSelectedIndex(0);
        pesanLabel.setText("Form berhasil dibersihkan.");
        namaField.requestFocusInWindow();
        adaPerubahan = false;
    }

    private void tampilkanKesalahan(String pesan, JTextField field) {
        pesanLabel.setText(pesan);
        field.requestFocusInWindow();
        field.selectAll();
    }

    private void prosesKeluar() {
        if (adaPerubahan) {
            int opsi = JOptionPane.showConfirmDialog(
                frame,
                "Ada perubahan yang belum disimpan. Yakin ingin keluar?",
                "Konfirmasi Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (opsi == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormPendaftaran().frame.setVisible(true);
        });
    }
}
