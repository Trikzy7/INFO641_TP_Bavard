import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Bavard_Interface extends JFrame{

    // -- ATTRIBUTE
    private JPanel panelContenuAllMessage;
    private JTextField inputSubject;
    private JComboBox<String> inputTheme;
    private JTextArea inputAreaMessage;
    private JRadioButton btnHideMessage ;
    private JRadioButton btnCinema;
    private JRadioButton btnSport;
    private JRadioButton btnLoisir;
    private JRadioButton btnLivre;
    private JRadioButton btnInfo;
    private ArrayList<JRadioButton> listebtnPeople = new ArrayList<JRadioButton>();
    private Batiment batiment;
    private Bavard currentBavard;
    private JList<String> list;
    private JList<String> listPseudoConnected;



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

        // -- Add the list message received du bavard a notre JList
        this.list = new JList(currentBavard.getListShort());

        this.getCurrentBavard().setOnLine(true);

        // -- Default : tous les bavards veulent recevoir les messages
        for (Bavard b : this.getBatiment().getListeBavard()) {
            this.getBatiment().receiveMessageBavard(b, true);
        }

        // -- Pour le bavard qui vient de connecter -> il veut recevoir les messages de tous les autres bavards
        for (Bavard b : this.getBatiment().getListeBavard()) {
            if (!this.currentBavard.getListePeople().contains(b.getPseudo()))
                this.currentBavard.addListePeople(b.getPseudo());
        }

        System.out.println("------------------------------------------------- LISTE DEBUT ET RETOUR");
        System.out.println(currentBavard.getListePeople());

        // -- Le bavard envoie une notif aux autres pour dire qu'il est connecte
        this.getBatiment().bavardSendNotifyConnexion( this.getCurrentBavard() );
        this.listPseudoConnected = new JList(this.batiment.getlistPseudoConnected());

//        System.out.println(this.getCurrentBavard().getListePeople());



//        System.out.println(this.getBatiment().getListeBavard());


        // On met l'interface principal du bavard
        Bavard_Interface_Principal();


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
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

        
        // Nom du bavard et btn detail
        JPanel panelHaut = new JPanel();
        JLabel labelNameBavard = new JLabel();
        labelNameBavard.setText( this.getCurrentBavard().getPseudo() );
        labelNameBavard.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le titre horizontalement
        Font labelFont = labelNameBavard.getFont();
        labelNameBavard.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
        labelNameBavard.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));

        JButton btnDeco = new JButton("Se déconnecter");
        panelHaut.add(labelNameBavard);
        panelHaut.add(Box.createRigidArea(new Dimension(50, 0)));
        panelHaut.add(btnDeco);
        panelHaut.add(Box.createRigidArea(new Dimension(350, 0)));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        btnDeco.addActionListener( (event) -> btnDecoListener(event) );


        // Création panel de gauche
        panelContenuAllMessage = new JPanel();
        panelContenuAllMessage.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panelContenuAllMessage.setBackground(new Color(255,255,255));


        panelContenuAllMessage.add(this.list);

        // Ajouter la zone de défilement autour de la zone de gauche
        JScrollPane scrollPane = new JScrollPane(panelContenuAllMessage);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);


        // Ajouter un panneau pour les boutons à droite de la zone de défilement
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.PAGE_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Ajouter des marges

        JButton btnShowMessageDetail = new JButton("Voir détails");
        panelBoutons.add(btnShowMessageDetail);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnShowMessageDetail.addActionListener( (event) -> btnShowMessageDetailListener(event) );

        btnHideMessage = new JRadioButton("Ne plus voir les messages");
        panelBoutons.add(btnHideMessage);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnHideMessage.addActionListener( (event) -> btnNotViewMessageListener(event) );


        JButton btnFilter = new JButton("Thèmes");
        panelBoutons.add(btnFilter);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnFilter.addActionListener( (event) -> btnFilterListener(event) );


        JButton btnPeople = new JButton("Users");
        panelBoutons.add(btnPeople);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnPeople.addActionListener( (event) -> btnPeopleListener(event) );


        JButton btnWriteMessage = new JButton("Ecire un Message");
        panelBoutons.add(btnWriteMessage);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les boutons
        btnWriteMessage.addActionListener( (event) -> btnWriteMessageListerner(event) );

        JLabel labelBavardOnLine = new JLabel("En ligne :");
        labelBavardOnLine.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        labelBavardOnLine.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        panelBoutons.add(labelBavardOnLine);

        // Création de la zone de défilement pour les bavards en ligne
        JPanel panelContenu2 = new JPanel();
        panelContenu2.setLayout(new BoxLayout(panelContenu2, BoxLayout.PAGE_AXIS));
        panelContenu2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panelContenu2.setBackground(new Color(255, 255, 255));
        panelContenu2.add(this.listPseudoConnected);

        // Ajouter la zone de défilement autour de la zone des bavards en ligne
        JScrollPane scrollPane2 = new JScrollPane(panelContenu2);

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
        Bavard_Interface_Filtre();
    }

    private void btnPeopleListener(ActionEvent e) {
        Bavard_Interface_People();
    }

    private void btnShowMessageDetailListener(ActionEvent e) {
        /*
        Afficher le détail du message selectionné
         */

        String messageSelected = list.getSelectedValue();
        int messageSelectedId = list.getSelectedIndex();

        if (messageSelectedId == -1 )
            JOptionPane.showMessageDialog(this, "Pas de message selectionné");
        else {
            String messageFull =  "Sujet : " + this.getCurrentBavard().getListMessageReceived().get( messageSelectedId ).getSujet() + "\n\n"
                    + "Corps : \n"
                    + this.getCurrentBavard().getListMessageReceived().get( messageSelectedId ).getCorps();

            JOptionPane.showMessageDialog(
                    this,
                    messageFull,
                    messageSelected,
                    JOptionPane.PLAIN_MESSAGE
            );
        }


    }

    private void btnNotViewMessageListener(ActionEvent e){
        if (btnHideMessage.isSelected()){
            this.batiment.receiveMessageBavard(this.currentBavard, false);
        }
        else {
            this.batiment.receiveMessageBavard(this.currentBavard, true);
        }

    }

    private void btnDecoListener(ActionEvent e){

        this.getBatiment().bavardSendNotifyDeconnexion(this.getCurrentBavard());
        this.getCurrentBavard().setOnLine(false);

        this.getCurrentBavard().getListMessageReceived().removeAll( this.getCurrentBavard().getListMessageReceived() );
        this.getCurrentBavard().getListShort().removeAllElements();


        dispose();
    }


    // -- Interface pour écrire un message
    public void Bavard_Interface_Write(){
        //setBounds(-1800, 250,800,400);
        setSize(1000,400);

        // Création du panneau principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Création du panneau supérieur avec un label, une zone de texte et un bouton
        JPanel panelHaut = new JPanel(new FlowLayout());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener( (event) -> btnBackListener(event) );

        JLabel labelSubject = new JLabel("Sujet : ");
        inputSubject = new JTextField(20);

        JLabel labelTheme = new JLabel("Thème : ");
        String[] choices = {"Cinema", "Sport", "Loisir", "Livre", "Info"};
        inputTheme = new JComboBox<>(choices);
        // Changer la taille de la zone de liste déroulante
        Dimension size = new Dimension(200, inputTheme.getPreferredSize().height);
        inputTheme.setPreferredSize(size);

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

    // --------------- METHODS BINDING BTN
    private void btnSendMessageListener(ActionEvent e) {
//        System.out.println("------------ Les destinataires de " + this.getCurrentBavard().getPseudo() + " :");
//        System.out.println(this.getCurrentBavard().destinataires);
//
//        System.out.println("------------ Les destinataires du concierge " + this.getCurrentBavard().destinataires.get(0) + " :");
//        System.out.println( this.getBatiment().getConcierge().destinataires);

        String subject = inputSubject.getText();
        String theme = (String) inputTheme.getSelectedItem();
        String message = inputAreaMessage.getText();
//
//        System.out.println(this.getCurrentBavard());
//        System.out.println(this.getBatiment().getListeBavard());

//        System.out.println( this.getBatiment().getListeBavard() );
//        System.out.println( this.getBatiment().getConcierge() );

//        this.inputSubject.setText(this.getCurrentBavard().getPseudo());
        this.getBatiment().bavardSendMessage( this.getCurrentBavard(), subject, theme, message );

        Bavard_Interface_Principal();
//        System.out.println(this.list);

    }

    private void btnBackListener(ActionEvent e) {
        Bavard_Interface_Principal();
    }


    // -- Interface pour sélectionner un filtre de thème
    private void Bavard_Interface_Filtre(){
        setSize(300,500);
        JPanel panelPrincipal = new JPanel();

        JButton btnback = new JButton("Back");
        btnback.addActionListener( (event) -> btnBackListener(event) );

        JLabel titre = new JLabel("Filtres");


        // Créer cinq boutons radio
        btnCinema = new JRadioButton("Cinema");
        btnSport = new JRadioButton("Sport");
        btnLoisir = new JRadioButton("Loisir");
        btnLivre = new JRadioButton("Livre");
        btnInfo = new JRadioButton("Info");

        ArrayList<JRadioButton> listebtnTheme = new ArrayList<JRadioButton>();
        listebtnTheme.add(btnCinema);
        listebtnTheme.add(btnSport);
        listebtnTheme.add(btnLoisir);
        listebtnTheme.add(btnLivre);
        listebtnTheme.add(btnInfo);

        for (JRadioButton rbtn : listebtnTheme){
            if (currentBavard.getlisteTheme().contains(rbtn.getText()))
                rbtn.setSelected(true);
        }

        JButton btnvalid = new JButton("Valider");
        btnvalid.addActionListener( (event) -> btnValiderFiltreListener(event) );



        // Créer un panneau pour contenir les boutons radio
        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10)); // Utilisation d'un GridLayout pour placer les boutons les uns en dessous des autres, avec un espacement vertical de 10 pixels entre les boutons

        panel.add(btnback);

        panel.add(titre);

        panel.add(btnCinema);
        panel.add(btnSport);
        panel.add(btnLoisir);
        panel.add(btnLivre);
        panel.add(btnInfo);

        panel.add(btnvalid);


        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // Ajouter le panneau à la fenêtre
        setContentPane(panel);

    }

    private void btnValiderFiltreListener(ActionEvent e){
        // Vide la liste des thèmes du current bavard
        currentBavard.getlisteTheme().removeAll(currentBavard.getlisteTheme());

        // Ajoute les themes selectionnés au bavard à sa liste
        if (btnCinema.isSelected()){
            currentBavard.getlisteTheme().add(btnCinema.getText());
        }
        if (btnSport.isSelected()){
            currentBavard.getlisteTheme().add(btnSport.getText());
        }
        if (btnLoisir.isSelected()){
            currentBavard.getlisteTheme().add(btnLoisir.getText());
        }
        if (btnLivre.isSelected()){
            currentBavard.getlisteTheme().add(btnLivre.getText());
        }
        if (btnInfo.isSelected()){
            currentBavard.getlisteTheme().add(btnInfo.getText());
        }
        System.out.println(currentBavard.getlisteTheme());
        Bavard_Interface_Principal();
    }



    // -- Interface pour sélectionner un filtre de people
    private void Bavard_Interface_People(){
        setSize(300,500);
        JPanel panelPrincipal = new JPanel();

        JButton btnback = new JButton("Back");
        btnback.addActionListener( (event) -> btnBackListener(event) );

        JLabel titre = new JLabel("People");

        listebtnPeople = new ArrayList<JRadioButton>();

        for (Bavard b : this.batiment.getListeBavard()){
            JRadioButton btnPeople = new JRadioButton(b.getPseudo());
            listebtnPeople.add(btnPeople);
        }


        for (JRadioButton rbtn : listebtnPeople){
            if (currentBavard.getListePeople().contains(rbtn.getText()))
                rbtn.setSelected(true);
        }

        JButton btnvalid = new JButton("Valider");
        btnvalid.addActionListener( (event) -> btnValiderPeopleListener(event) );



        // Créer un panneau pour contenir les boutons radio
        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10)); // Utilisation d'un GridLayout pour placer les boutons les uns en dessous des autres, avec un espacement vertical de 10 pixels entre les boutons

        panel.add(btnback);

        panel.add(titre);

        for (JRadioButton btn : listebtnPeople){
            panel.add(btn);
        }

        panel.add(btnvalid);


        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // Ajouter le panneau à la fenêtre
        setContentPane(panel);

    }

    private void btnValiderPeopleListener(ActionEvent e){
        // Vide la liste des people du current bavard
        currentBavard.getListePeople().removeAll(currentBavard.getListePeople());

        // Ajoute les personnes selectionnés au bavard à sa liste

        for (JRadioButton rbtn : listebtnPeople){
            if (rbtn.isSelected()){
                currentBavard.getListePeople().add(rbtn.getText());
            }
        }
        System.out.println("------------------------------------------------- LISTE APRES MODIF");
        System.out.println(currentBavard.getListePeople());
        Bavard_Interface_Principal();
    }
}
