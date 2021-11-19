import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        final String[] s = new String[1];

        JFrame f= new JFrame("Huffman Compression");
        JTextField t1;
        JButton b1;
        t1 =new JTextField();
        b1 = new JButton("Compress");
        b1.setBounds(150,300,150,50);
        f.add(b1);
        t1.setBounds(50,200, 400,30);
        b1.addActionListener(e -> {

            if(t1.getText().length() != 0) {
                s[0] = t1.getText();
                Standard_Huffman H = new Standard_Huffman(s[0]);
                H.Compression();
                H.decompression();
            }
            else{

                JOptionPane.showMessageDialog(f,
                        "Please Enter Some Text To Compress!");

            }

        });

        f.add(t1);
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);



    }
}
