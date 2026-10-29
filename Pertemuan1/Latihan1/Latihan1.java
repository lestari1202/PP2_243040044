
package Pertemuan1.Latihan1;

import javax.swing.*;

public class Latihan1 {
 public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Hello world");
        frame.add(new JLabel("Hallo PP2",JLabel.CENTER));
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    });
 }
}
