package Pertemuan2.Latihan2;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Contoh_Modul_1 {
    private int JumlahKlik = 0;

    private void tampilan(){
        JFrame frame = new JFrame("Contoh Modul 1");
        JLabel pesan = new JLabel("Tekan tombol ini.");
        JButton tombol = new JButton("Klik saya");

        tombol.addActionListener(event -> {
            JumlahKlik++;
            pesan.setText("Saya sudah mengklik sebanyak:" + JumlahKlik);
        });

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(pesan, BorderLayout.CENTER);
        panel.add(tombol, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Contoh_Modul_1().tampilan());
    }
}
