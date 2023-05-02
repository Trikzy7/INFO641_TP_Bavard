import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Bavard_Interface extends JFrame{

    // -- ATTRIBUTE
    private JPanel panelContenuAllMessage;
    private JTextField inputSubject;
    private JTextField inputTheme;
    private JTextArea inputAreaMessage;
    private Batiment batiment;
    private Bavard currentBavard;


    // -- GETTER AND SETTER
    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public Bavard getCurrentBavard() {
        return currentBavard;
    }

    public void setCurrentBavard(Bavard currentBavard) {
        this.currentBavard = currentBavard;
    }


    // -- MEHTODS


    // --------------- CONSTRUCTOR INTERFACE
    public Bavard_Interface(Batiment batiment, Bavard bavard) {
        super("Bavard_Interface");


        this.batiment = batiment;
        this.currentBavard = bavard;

        this.getCurrentBavard().setOnLine(true);

        for(Bavard b : this.getBatiment().getListeBavard()) {
            this.getBatiment().receiveMessageBavard(b, true);
//            System.out.println(b.getPseudo());
        }

        System.out.println(this.getBatiment().getListeBavard());


        // On met l'interface principal du bavard
        Bavard_Interface_Principal();


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }



    // -- Interface principal des bavards
    public void Bavard_Interface_Principal(){
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        
        // Création panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40)); // Marges autour du panneau

        
        // Nom du bavard et btn refresh
        JPanel panelHaut = new JPanel();
        JLabel labelNameBavard = new JLabel();
        labelNameBavard.setText( this.getCurrentBavard().getPseudo() );
        labelNameBavard.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le titre horizontalement
        Font labelFont = labelNameBavard.getFont();
        labelNameBavard.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
        labelNameBavard.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        JButton boutonRefreshPage = new JButton("Refresh Page");
        panelHaut.add(labelNameBavard);
        panelHaut.add(Box.createRigidArea(new Dimension(50, 0)));
        panelHaut.add(boutonRefreshPage);
        panelHaut.add(Box.createRigidArea(new Dimension(350, 0)));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);

        
        // Création panel de gauche
        panelContenuAllMessage = new JPanel();
        panelContenuAllMessage.setLayout(new BoxLayout(panelContenuAllMessage, BoxLayout.PAGE_AXIS));
        panelContenuAllMessage.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        // Parcours de la liste des messages que peut voir le bavard
        for (int i = 1; i <= 20; i++) {
            JLabel label = new JLabel("Ligne " + i);
            JButton button = new JButton("Détails");
            JPanel line = new JPanel(new BorderLayout());
            line.add(label, BorderLayout.WEST);
            button.setPreferredSize(new Dimension(80, 20)); // Définir la taille du bouton
            line.add(button, BorderLayout.EAST);
            panelContenuAllMessage.add(line);


            // Ajouter une ligne verticale après chaque ligne, sauf la dernière
            if (i < 20) {
                panelContenuAllMessage.add(Box.createRigidArea(new Dimension(0, 5)));
                panelContenuAllMessage.add(new JSeparator(SwingConstants.HORIZONTAL));
                panelContenuAllMessage.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }


        // Ajouter la zone de défilement autour de la zone de gauche
        JScrollPane scrollPane = new JScrollPane(panelContenuAllMessage);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Ajouter un panneau pour les boutons à droite de la zone de défilement
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.PAGE_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Ajouter des marges

        JRadioButton btnHideMessage = new JRadioButton("Ne plus voir les messages");
        panelBoutons.add(btnHideMessage);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons

        JButton btnFilter = new JButton("Filtres");
        panelBoutons.add(btnFilter);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnFilter.addActionListener( (event) -> btnFilterListener(event) );

        JButton btnWriteMessage = new JButton("Ecire un Message");
        panelBoutons.add(btnWriteMessage);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnWriteMessage.addActionListener( (event) -> btnWriteMessageListerner(event) );

        JButton btnRefreshOnline = new JButton("Refresh People");
        panelBoutons.add(btnRefreshOnline);
        //btnWriteMessage.addActionListener( (event) -> btnWriteMessageListerner(event) );

        JLabel labelBavardOnLine = new JLabel("En ligne :");
        labelBavardOnLine.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        labelBavardOnLine.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        panelBoutons.add(labelBavardOnLine);

        // Création de la zone de défilement pour les bavards en ligne
        JPanel panelContenu2 = new JPanel();
        panelContenu2.setLayout(new BoxLayout(panelContenu2, BoxLayout.PAGE_AXIS));
        panelContenu2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Parcours de la liste des bavards en ligne
        for (int i = 1; i <= 10; i++) {
            JLabel label = new JLabel("Ligne " + i);
            JPanel line = new JPanel(new BorderLayout());
            line.add(label, BorderLayout.WEST);
            panelContenu2.add(line);

            // Ajouter une ligne verticale après chaque ligne, sauf la dernière
            if (i < 10) {
                panelContenu2.add(Box.createRigidArea(new Dimension(0, 5)));
                panelContenu2.add(new JSeparator(SwingConstants.HORIZONTAL));
                panelContenu2.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        // Ajouter la zone de défilement autour de la zone des bavards en ligne
        JScrollPane scrollPane2 = new JScrollPane(panelContenu2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Ajout de la zone de défilement à droite
        panelBoutons.add(scrollPane2, BorderLayout.CENTER);

        // Ajout de la zone de droite au panel principal
        panelPrincipal.add(panelBoutons, BorderLayout.LINE_END);

        setContentPane(panelPrincipal);

    }

    // --------------- METHODS BINDING BTN
    private void btnWriteMessageListerner(ActionEvent e) {
        Bavard_Interface_Write();
    }

    private void btnFilterListener(ActionEvent e) {
        /*
        Permet de refresh la liste des messages reçus
         */

        int i = 0;
        for (PapotageEvent pe : this.getCurrentBavard().getListMessageReceived()) {

            // -- Get l'envoyeur src du message
            Bavard bavardSRC = (Bavard) pe.getSource();

            JLabel label = new JLabel(bavardSRC.getPseudo() + " : " + pe.getSujet());

            JButton button = new JButton("Détails");
            JPanel line = new JPanel(new BorderLayout());
            line.add(label, BorderLayout.WEST);
            button.setPreferredSize(new Dimension(80, 20)); // Définir la taille du bouton
            line.add(button, BorderLayout.EAST);
            panelContenuAllMessage.add(line);


            // Ajouter une ligne verticale après chaque ligne, sauf la dernière
            if (i < 20) {
                panelContenuAllMessage.add(Box.createRigidArea(new Dimension(0, 5)));
                panelContenuAllMessage.add(new JSeparator(SwingConstants.HORIZONTAL));
                panelContenuAllMessage.add(Box.createRigidArea(new Dimension(0, 5)));
            }

            i++;
        }
    }

    // -- Interface pour écrire un message
    public void Bavard_Interface_Write(){
        //setBounds(-1800, 250,800,400);
        setSize(800,400);

        // Création du panneau principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Création du panneau supérieur avec un label, une zone de texte et un bouton
        JPanel panelHaut = new JPanel(new FlowLayout());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener( (event) -> btnSendMessageListener(event) );

        JLabel labelSubject = new JLabel("Sujet : ");
        inputSubject = new JTextField(20);

        JLabel labelTheme = new JLabel("Thème : ");
        inputTheme = new JTextField(20);

        JButton btnSendMessage = new JButton("ENVOYER");
        btnSendMessage.addActionListener( (event) -> btnSendMessageListener(event) );

        panelHaut.add(btnBack);
        panelHaut.add(Box.createRigidArea(new Dimension(10, 0)));
        panelHaut.add(labelSubject);
        panelHaut.add(inputSubject);
        panelHaut.add(Box.createRigidArea(new Dimension(30, 0)));
        panelHaut.add(labelTheme);
        panelHaut.add(inputTheme);
        panelHaut.add(Box.createRigidArea(new Dimension(30, 0)));
        panelHaut.add(btnSendMessage);

        // Création de la zone de texte en bas
        inputAreaMessage = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(inputAreaMessage); // ajout d'une barre de défilement

        // Ajout des panneaux au panneau principal
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panelPrincipal); // affectation du panneau principal à la fenêtre

    }

    private void btnSendMessageListener(ActionEvent e) {
//        System.out.println("------------ Les destinataires de " + this.getCurrentBavard().getPseudo() + " :");
//        System.out.println(this.getCurrentBavard().destinataires);
//
//        System.out.println("------------ Les destinataires du concierge " + this.getCurrentBavard().destinataires.get(0) + " :");
//        System.out.println( this.getBatiment().getConcierge().destinataires);

        String subject = inputSubject.getText();
        String theme = inputTheme.getText();
        String message = inputAreaMessage.getText();
//
//        System.out.println(this.getCurrentBavard());
//        System.out.println(this.getBatiment().getListeBavard());

//        System.out.println( this.getBatiment().getListeBavard() );
//        System.out.println( this.getBatiment().getConcierge() );

//        this.inputSubject.setText(this.getCurrentBavard().getPseudo());
        this.getBatiment().bavardSendMessage( this.getCurrentBavard(), subject, message );

    }
}
