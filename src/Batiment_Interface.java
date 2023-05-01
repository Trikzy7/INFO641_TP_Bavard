import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Batiment_Interface extends JFrame implements ActionListener {
    private JTextField inputNameBavard;
    private JTextField inputConnectedBavard;

    public Batiment_Interface( ArrayList<Bavard> listBavardCreated ) {
        super("Batiment_Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel labelCreateBavard = new JLabel("Créer un Bavard : ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelCreateBavard, c);

        JLabel labelCreateBavardPseudo = new JLabel("Pseudo : ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelCreateBavardPseudo, c);

        inputNameBavard = new JTextField(10);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(inputNameBavard, c);

        JButton btnCreateBavard = new JButton("Créer Bavard");
        c.gridx = 1;
        c.gridy = 2;
        //c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        //c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnCreateBavard, c);
        btnCreateBavard.addActionListener( (event) -> btnCreateBavardListener(event) );

        JLabel labelConnectBavard = new JLabel("Se Connecter : ");
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelConnectBavard, c);

        JLabel labelConnectBavardPseudo = new JLabel("Pseudo : ");
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelConnectBavardPseudo, c);

        inputConnectedBavard = new JTextField(10);
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(inputConnectedBavard, c);


        JButton btnConnectBavard = new JButton("Connection");
        c.gridx = 5;
        c.gridy = 2;
        //c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(btnConnectBavard, c);
        btnConnectBavard.addActionListener( (event) -> btnConnectBavardListener(event) );

        getContentPane().add(panel);
        //pack();
        setVisible(true);
    }

    private void btnCreateBavardListener( ActionEvent e) {
        /*
        Permet de créer un bavard
         */

        String pseudo = inputNameBavard.getText();

        if (pseudo.length() == 0) {
            JOptionPane.showMessageDialog(this, "Le champ est vide");
        } else {

            System.out.println(pseudo);
        }
    }
    private void btnConnectBavardListener( ActionEvent e) {
        /*
        Permet de connecter un bavard
         */
        System.out.println("Btn connect cliqued");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}