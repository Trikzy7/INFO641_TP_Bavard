import javax.swing.*;
import java.util.ArrayList;

public class Concierge implements PapotageListener, OnLineBavardListener {
    private String pseudo;

    private ArrayList<PapotageEvent> listMessageReceived = new ArrayList<PapotageEvent>();

    private DefaultListModel listMessageShort = new DefaultListModel();

    private DefaultListModel listPseudoConnected = new DefaultListModel();

    // Liste contenant tous les bavards
    ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    // Liste contenant tous les bavards
    ArrayList<OnLineBavardListener> destinatairesOnLine = new ArrayList<OnLineBavardListener>();

    public String getPseudo() {
        return pseudo;
    }

    public void setPeudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<PapotageEvent> getListMessageReceived() {
        return listMessageReceived;
    }

    public void setListMessageReceived(ArrayList<PapotageEvent> listMessageReceived) {
        this.listMessageReceived = listMessageReceived;
    }

    public DefaultListModel getListShort(){return listMessageShort;}

    public DefaultListModel getlistPseudoConnected(){return listPseudoConnected;}

    public Concierge(String pseudo) {
        this.pseudo = "[CONCIERGE] " + pseudo;
    }

    public void addMessage(PapotageEvent pe) {
        this.getListMessageReceived().add(pe);
    }

    public void removeMessage(PapotageEvent pe) {
        this.getListMessageReceived().remove(pe);
    }


    // -- Methods Papotage
    public void addPapotageListener(PapotageListener pl) {
        /*
        Permet d'ajouter un papotage à la liste
         */
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl) {
        /*
        Permet d'ajouter un papotage à la liste
         */
        destinataires.remove(pl);
    }

    public void generateNewPapotage(String sujet, String theme, String corps) {
        /*
        GOAL : Permet qu'un concierge envoie un message a tout ses destinataires bavards
         */
        PapotageEvent papotage = new PapotageEvent(this, sujet,theme, corps);

        for (PapotageListener pl : this.destinataires) {
            pl.newPapotage(papotage);
        }
    }

    @Override
    public void newPapotage(PapotageEvent papotage) {
        /*
            GOAL : Envoyer les données à l'interface (avec les listes)
            Permet de recevoir un message venant source (d'un bavard)
         */

        Bavard bavardSRC = (Bavard) papotage.getSource();
        System.out.println( bavardSRC.getPseudo() );

        System.out.println("Source : " + this.getPseudo() + " a bien reçu le message");
        System.out.println("Sujet : " + papotage.getSujet());
        System.out.println("Thème : " + papotage.getTheme());
        System.out.println("Corps : " + papotage.getCorps() +"\n");


        // -- Ajout à la liste des message
        this.addMessage(papotage);

        // -- Ajout à la liste short des messages -> list qui va etre affiche dans la JList niveau graphique
        this.getListShort().addElement(bavardSRC.getPseudo()+" : "+papotage.getSujet());

        // -- On envoie le message à tous les destinataires
        for (PapotageListener pl : this.destinataires) {
            pl.newPapotage(papotage);
        }

    }


    // -- Methods OnLine
    public void addOnLineListener(OnLineBavardListener ol) {
        /*
        Permet d'ajouter un OnLineListener à la liste
         */
        this.destinatairesOnLine.add(ol);
    }

    public void removeOnLineListener(OnLineBavardListener ol) {
        /*
        Permet de retirer un OnLineListener à la liste
         */
        this.destinatairesOnLine.remove(ol);
    }

    public void generateNewOnLine(Bavard bavard) {
        /*
        Permet qu'un concierge envoie une notification a tous les bavards pour dire qu'un bavard s'est connecté
         */
        OnLineBavardEvent online = new OnLineBavardEvent(this, bavard);

        for (OnLineBavardListener ol : this.destinatairesOnLine) {
            ol.newOnLine(online);
        }
    }

    @Override
    public void newOnLine(OnLineBavardEvent online) {
        /*
            GOAL : Envoyer les données à l'interface
            Permet de recevoir une notification venant source (d'un bavard)
         */

        Bavard bavardSRC = (Bavard) online.getSource();

        System.out.println("Source connected : " + bavardSRC.getPseudo() );
        System.out.println(this.getPseudo() + " a bien reçu la notif");

        // -- On envoie la notif à tous les destinataires
        for (OnLineBavardListener ol : this.destinatairesOnLine) {
            ol.newOnLine(online);
        }
    }
}
