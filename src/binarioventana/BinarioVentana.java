/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarioventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Usuario
 */
public class BinarioVentana extends JFrame {

    private JLabel jlbBinari;
    private JTextField jtfBinari;
    private JLabel jlbDecimal;
    private JTextField jtfDecimal;
    private JButton jbtAcceptar;
    private Object objJTextField;

    public BinarioVentana() {
        setSize(400, 200);
        setTitle("Conversió decimal-binari");
        initComponents();
    }

    private void initComponents() {
        jlbBinari = new JLabel();
        jtfBinari = new JTextField();
        jlbDecimal = new JLabel();
        jtfDecimal = new JTextField();
        jbtAcceptar = new JButton();
        objJTextField = new Object();

        getContentPane().setLayout(null);
        Font font = new Font("Calibri", Font.BOLD, 12);
        addWindowListener(new WindowAdapter() {
            //Esdeveniment de finestra oberta per controlar el focus
            @Override
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }

            @Override
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        jlbBinari.setText("Nombre Binari");
        jlbBinari.setFont(font);
        jlbBinari.setBounds(15, 30, 150, 25);
        getContentPane().add(jlbBinari);

        jlbDecimal.setText("Nombre Decimal");
        jlbDecimal.setFont(font);
        jlbDecimal.setBounds(15, 90, 150, 25);
        getContentPane().add(jlbDecimal);

        jtfBinari.setText("0");
        jtfBinari.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfBinari.setBounds(150, 30, 150, 25);
        getContentPane().add(jtfBinari);

        jtfDecimal.setText("0");
        jtfDecimal.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfDecimal.setBounds(150, 90, 150, 25);
        getContentPane().add(jtfDecimal);

        jbtAcceptar.setText("Conversio");
        jbtAcceptar.setBounds(150, 130, 120, 25);
        getContentPane().add(jbtAcceptar);

        //Per controlar els esdeveniments KeyEvent a les finestres de text
        KeyAdapter kl = new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent evt) {
                jtfKeyTyped(evt);
            }
        };

        jtfBinari.addKeyListener(kl);
        jtfDecimal.addKeyListener(kl);

        jbtAcceptar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                jbtAcceptarActionPerformed(evt);
            }
        });

    }

    private void jtfKeyTyped(KeyEvent evt) {
        objJTextField = evt.getSource(); //objecte que ha produit l'esdeveniment
    }

    private void jbtAcceptarActionPerformed(ActionEvent evt) {
        //conversio
        try {
            if (objJTextField == jtfBinari) {
                int decimal = Integer.parseInt(jtfBinari.getText(), 2);     //el segundo campo indica la base
                jtfDecimal.setText(String.valueOf(decimal));
            }

            if (objJTextField == jtfDecimal) {
                int exp = 0;
                double binario = 0;
                int digito;
                int numero = Integer.parseInt(jtfDecimal.getText());
                
                int resultat;
                while (numero != 0) {
                    digito = numero % 2;
                    binario = binario + digito * Math.pow(10, exp);
                    exp++;
                    numero = numero / 2;
                }
                
                resultat = (int) Math.round(binario);

                jtfBinari.setText(String.valueOf(resultat));
            }
        } catch (NumberFormatException e) {
            jtfBinari.setText("0");
            jtfDecimal.setText("0");
        }
    }

    private void formWindowOpened(WindowEvent evt) {
        jtfDecimal.requestFocus();
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    public static void main(String[] args) {
        try { //Control de l'aspecte
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No s'ha establert el look desitjat: " + e);
        }
        new BinarioVentana().setVisible(true);
    }
}
