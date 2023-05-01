import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Batiment_Interface extends JFrame implements ActionListener {

    // -- ATTRIBUTE
    private JTextField inputNameBavard;
    private JTextField inputConnectedBavard;
    private Batiment batiment;
    private ArrayList<String> listBavardCreate = new ArrayList<String>();


    // -- GETTER AND SETTER
    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public ArrayList<String> getListBavardCreate() {
        return listBavardCreate;
    }

    public void setListBavardCreate(ArrayList<String> listBavardCreate) {
        this.listBavardCreate = listBavardCreate;
    }


    // -- MEHTODS
    public boolean addBavardIfDoesntExist(String pseudo) {
        /*
        Permet d'ajouter un bavard à la liste lors de la création s'il n'a pas encore été crée
        Return :
            - True : Si le bavard n'existe pas encore et a été ajouté à la liste
            - False : Si le bavard existe déjà
         */

        if (this.getListBavardCreate().contains(pseudo))
            return false;
        else {
            this.getListBavardCreate().add(pseudo);
            return true;
        }
    }

    public boolean checkIfBavardExist(String pseudo) {
        /*
        Permet de checker si un bavard a bien été créer au préalable
        Retrun :
            - True : Si le bavard est dans la liste de bavard créé
            - False : Si le bavard n'est pas dans la liste des bavards créé
         */

        return this.getListBavardCreate().contains(pseudo);
    }

    // --------------- Constructor Interface
    public Batiment_Interface( String nameBatiment ) {

        // -- Construct Window
        super("Batiment_Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // -- CREATE BAVARD
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


        // -- CONNECT BAVARD
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


        // -- Create Batiment
        this.batiment = new Batiment(nameBatiment);
    }


    // --------------- Mehtods Binding BTN
    private void btnCreateBavardListener( ActionEvent e) {
        /*
        Permet de créer un bavard
         */

        // -- Get text in input field create bavard
        String pseudo = inputNameBavard.getText();

        // -- If text is empty
        if (pseudo.length() == 0)
            JOptionPane.showMessageDialog(this, "Le champ est vide");
        else {

            // -- Add Bavard to the list if he doesn't exist yet
            boolean bavardAddedInList = this.addBavardIfDoesntExist( pseudo );

            // -- If bavard already exist
            if ( !bavardAddedInList )
                JOptionPane.showMessageDialog(this, "Le bavard " + pseudo + " existe déjà");

        }
    }
    private void btnConnectBavardListener( ActionEvent e) {
        /*
        Permet de connecter un bavard
        Et redirige sur l'interface d'un bavard avec le current bavard
         */

        // -- Get text in input field create bavard
        String pseudo = inputConnectedBavard.getText();

        // -- If text is empty
        if (pseudo.length() == 0)
            JOptionPane.showMessageDialog(this, "Le champ est vide");
        else {

            // -- If Bavard exist -> Redirect on Interface Bavard with batiment created and the current Bavard
            if (this.checkIfBavardExist(pseudo))
                new Bavard_Interface(this.getBatiment(), new Bavard(pseudo) );
            else
                JOptionPane.showMessageDialog(this, "Le bavard n'existe pas");
        }

        System.out.println("Btn connect cliqued");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}