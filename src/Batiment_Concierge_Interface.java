import javax.swing.*;
import java.awt.*;

public class Batiment_Concierge_Interface extends JFrame {
    public Batiment_Concierge_Interface() {
        super("Batiment_Concierge_Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(-1500, 300,850,530);
        setSize(850,530);

        // Création panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.PAGE_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40)); // Marges autour du panneau


        // Création du titre et Création btn refresh
        JPanel panelHaut = new JPanel();
        JButton buttonRefresh = new JButton("Refresh");
        panelHaut.add(buttonRefresh);
        JLabel titre = new JLabel("Concierge");
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le titre horizontalement
        Font labelFont = titre.getFont();
        int labelFontSize = 30; // taille souhaitée en points
        titre.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFontSize));
        titre.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        panelHaut.add(titre);
        panelHaut.add(buttonRefresh);
        panelPrincipal.add(panelHaut);

        // Création panel principal
        JPanel panelContenu = new JPanel();
        panelContenu.setLayout(new BoxLayout(panelContenu, BoxLayout.PAGE_AXIS));

        // Parcours de la liste des messages pour les afficher
        for (int i = 1; i <= 20; i++) {
            JLabel label = new JLabel("Ligne " + i);
            JButton button = new JButton("B" + i);
            JPanel line = new JPanel(new BorderLayout());
            line.add(label, BorderLayout.WEST);
            button.setPreferredSize(new Dimension(60, 20)); // Définir la taille du bouton
            line.add(button, BorderLayout.EAST);
            panelContenu.add(line);

            // Ajouter une ligne verticale après chaque ligne, sauf la dernière
            if (i < 20) {
                panelContenu.add(Box.createRigidArea(new Dimension(0, 5)));
                panelContenu.add(new JSeparator(SwingConstants.HORIZONTAL));
                panelContenu.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        // Ajouter la zone de défilement autour de la zone centrale
        JScrollPane scrollPane = new JScrollPane(panelContenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panelPrincipal.add(scrollPane);

        setContentPane(panelPrincipal);
        setVisible(true);
    }


}
