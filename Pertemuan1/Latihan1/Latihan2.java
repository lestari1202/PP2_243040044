
package Pertemuan1.Latihan1;

import java.awt.BorderLayout;

import javax.swing.*;

public class Latihan2 {
 public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Latihan2");
        frame.add(new JLabel("Hallo PP2",JLabel.CENTER), BorderLayout.NORTH);
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    });
 }
}
