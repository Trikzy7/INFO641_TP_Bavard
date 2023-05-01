import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bavard_Interface extends JFrame{

    // -- ATTRIBUTE
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

        System.out.println(this.getBatiment().getListeBavard());

        System.out.println(this.getCurrentBavard().getPseudo());

        int i=0;
        if (i==0) Bavard_Interface_Principal();
        if (i==1) Bavard_Interface_Write();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);



    }



    // Interface principal des bavards
    public void Bavard_Interface_Principal(){
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        
        // Création panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40)); // Marges autour du panneau

        
        // Nom du bavard
        JLabel labelNameBavard = new JLabel();
        labelNameBavard.setText( this.getCurrentBavard().getPseudo() );
        labelNameBavard.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le titre horizontalement
        Font labelFont = labelNameBavard.getFont();
        labelNameBavard.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
        labelNameBavard.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        panelPrincipal.add(labelNameBavard, BorderLayout.NORTH);

        
        // Création panel de gauche
        JPanel panelContenu = new JPanel();
        panelContenu.setLayout(new BoxLayout(panelContenu, BoxLayout.PAGE_AXIS));
        panelContenu.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        
        // Parcours de la liste des messages que peut voir le bavard
        for (int i = 1; i <= 20; i++) {
            JLabel label = new JLabel("Ligne " + i);
            JButton button = new JButton("Détails");
            JPanel line = new JPanel(new BorderLayout());
            line.add(label, BorderLayout.WEST);
            button.setPreferredSize(new Dimension(80, 20)); // Définir la taille du bouton
            line.add(button, BorderLayout.EAST);
            panelContenu.add(line);

            
            // Ajouter une ligne verticale après chaque ligne, sauf la dernière
            if (i < 20) {
                panelContenu.add(Box.createRigidArea(new Dimension(0, 5)));
                panelContenu.add(new JSeparator(SwingConstants.HORIZONTAL));
                panelContenu.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        

        // Ajouter la zone de défilement autour de la zone de gauche
        JScrollPane scrollPane = new JScrollPane(panelContenu);
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

        JButton btnWriteMessage = new JButton("Ecire un Message");
        panelBoutons.add(btnWriteMessage);

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

    // Interface pour écrire un message
    public void Bavard_Interface_Write(){
        //setBounds(-1800, 250,800,400);
        setSize(800,400);

        // Création du panneau principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Création du panneau supérieur avec un label, une zone de texte et un bouton
        JPanel panelHaut = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Sujet : ");
        JTextField textField = new JTextField(20);
        JLabel label2 = new JLabel("Thème : ");
        JTextField textField2 = new JTextField(20);
        JButton bouton = new JButton("ENVOYER");
        panelHaut.add(label);
        panelHaut.add(textField);
        panelHaut.add(Box.createRigidArea(new Dimension(30, 0)));
        panelHaut.add(label2);
        panelHaut.add(textField2);
        panelHaut.add(Box.createRigidArea(new Dimension(30, 0)));
        panelHaut.add(bouton);

        // Création de la zone de texte en bas
        JTextArea zoneTexte = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(zoneTexte); // ajout d'une barre de défilement

        // Ajout des panneaux au panneau principal
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panelPrincipal); // affectation du panneau principal à la fenêtre



    }
}
