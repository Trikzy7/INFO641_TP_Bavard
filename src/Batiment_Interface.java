import javax.swing.*;
import java.awt.*;

public class Batiment_Interface extends JFrame {
    private JTextField nomBavard;
    private JTextField connecterBavard;

    public Batiment_Interface() {
        super("Batiment_Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel label1 = new JLabel("Créer un Bavard : ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(label1, c);

        JLabel nom1 = new JLabel("Pseudo : ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(nom1, c);

        nomBavard = new JTextField(10);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(nomBavard, c);

        JButton creerBavard = new JButton("Créer Bavard");
        c.gridx = 1;
        c.gridy = 2;
        //c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        //c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(creerBavard, c);

        JLabel label2 = new JLabel("Se Connecter : ");
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(label2, c);

        JLabel nom2 = new JLabel("Pseudo : ");
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(nom2, c);

        connecterBavard = new JTextField(10);
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(connecterBavard, c);


        JButton seConnecterBavard = new JButton("Connection");
        c.gridx = 5;
        c.gridy = 2;
        //c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(seConnecterBavard, c);

        getContentPane().add(panel);
        //pack();
        setVisible(true);
    }
}