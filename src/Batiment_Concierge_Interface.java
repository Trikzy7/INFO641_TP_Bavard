import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Batiment_Concierge_Interface extends JFrame {

    // -- ATTRIBUTE
    private Batiment batiment;
    JPanel panelContenuAllMessage;
    private JList<String> listMessageShortJList;


    // -- GETTER AND SETTER
    public Batiment getBatiment() {
        return batiment;
    }
    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }



    // --------------- CONSTRUCTOR INTERFACE
    public Batiment_Concierge_Interface(Batiment batiment) {
        super("Batiment_Concierge_Interface");

        this.batiment = batiment;
        this.listMessageShortJList = new JList(this.getBatiment().getConcierge().getListShort());

        // -- Construction Window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        // Création panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40)); // Marges autour du panneau


        // Création du titre et Création btn detail
        JPanel panelHaut = new JPanel();

        JButton btnShowMessageDetail = new JButton("Voir détail");
        panelHaut.add(btnShowMessageDetail);
        btnShowMessageDetail.addActionListener( (event) -> btnShowMessageDetailListener(event) );

        JLabel titre = new JLabel("Concierge");
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le titre horizontalement
        Font labelFont = titre.getFont();
        titre.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
        titre.setBorder(BorderFactory.createEmptyBorder(10,0,20,10));
        panelHaut.add(titre);
        panelHaut.add(btnShowMessageDetail);
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);

        // Création panel principal
        panelContenuAllMessage = new JPanel();
        panelContenuAllMessage.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panelContenuAllMessage.setBackground(new Color(255,255,255));


        panelContenuAllMessage.add(this.listMessageShortJList);

        // Ajouter la zone de défilement autour de la zone de gauche
        JScrollPane scrollPane = new JScrollPane(panelContenuAllMessage);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());



        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
        setVisible(true);
    }

    // --------------- METHODS BINDING BTN
    // mathode pour voir le détail du message
    private void btnShowMessageDetailListener(ActionEvent e) {
        String messageSelected = this.listMessageShortJList.getSelectedValue();
        int messageSelectedId = this.listMessageShortJList.getSelectedIndex();


        if (messageSelectedId == -1 )
            JOptionPane.showMessageDialog(this, "Pas de message selectionné");
        else {
            String messageFull =  "Sujet : " + this.getBatiment().getConcierge().getListMessageReceived().get( messageSelectedId ).getSujet() + "\n\n"
                    + "Corps : \n"
                    + this.getBatiment().getConcierge().getListMessageReceived().get( messageSelectedId ).getCorps();

            JOptionPane.showMessageDialog(
                    this,
                    messageFull,
                    messageSelected,
                    JOptionPane.PLAIN_MESSAGE
            );
        }

    }

}
