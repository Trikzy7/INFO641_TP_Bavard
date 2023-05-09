import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Bavard  implements PapotageListener, OnLineBavardListener{

    private String pseudo;
    private boolean onLine;
    private boolean wantReceiveMessage;
    private ArrayList<PapotageEvent> listMessageReceived = new ArrayList<PapotageEvent>();
    private ArrayList<String> listeTheme = new ArrayList<String>();
    private ArrayList<String> listePeople = new ArrayList<String>();

    public ArrayList<String> getListePeople() {
        return listePeople;
    }

    public void setListePeople(ArrayList<String> listePeople) {
        this.listePeople = listePeople;
    }
    public void addListePeople(String people) {
        this.listePeople.add(people);
    }

    public ArrayList<String> getlisteTheme() {
        return listeTheme;
    }

    public void setlisteTheme(ArrayList<String> listeTheme) {
        this.listeTheme = listeTheme;
    }

    private DefaultListModel listMessageShort = new DefaultListModel();
    public DefaultListModel getListShort(){return listMessageShort;}

    private DefaultListModel listPseudoConnected = new DefaultListModel();
    public DefaultListModel getlistPseudoConnected(){return listPseudoConnected;}

    // Liste contenant 1 concierge
    ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    // Liste contenant 1 concierge
    ArrayList<OnLineBavardListener> destinatairesOnLine = new ArrayList<OnLineBavardListener>();

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public boolean isWantReceiveMessage() {
        return wantReceiveMessage;
    }

    public void setWantReceiveMessage(boolean wantReceiveMessage) {
        this.wantReceiveMessage = wantReceiveMessage;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<PapotageEvent> getListMessageReceived() {
        return listMessageReceived;
    }

    public void setListMessageReceived(ArrayList<PapotageEvent> listMessageReceived) {
        this.listMessageReceived = listMessageReceived;
    }


    // -- Constructor
    public Bavard(String pseudo) {
        this.pseudo = "[MOLDU] " + pseudo;
        this.getlisteTheme().add("Cinema");
        this.getlisteTheme().add("Sport");
        this.getlisteTheme().add("Loisir");
        this.getlisteTheme().add("Livre");
        this.getlisteTheme().add("Info");
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
        GOAL : Ajouter l'element pl a la liste
         */
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl) {
        /*
        GOAL : Remove l'element pl a la liste
         */
        destinataires.remove(pl);
    }

    public void generateNewPapotage(String sujet,String theme, String corps) {
        /*
        GOAL : Permet qu'un bavard envoie un message a son concierge et que le concierge réenvoie les messages
         */

        PapotageEvent papotage = new PapotageEvent(this, sujet, theme, corps);

        for (PapotageListener pl : this.destinataires) {
            // -- Le concierge reçoit le message
            pl.newPapotage(papotage);

        }
    }

    @Override
    public void newPapotage(PapotageEvent papotage) {
        /*
        GOAL : Envoyer les données à l'interface
         */

//        System.out.println( papotage.getSource().getClass() );

        Bavard bavardSRC = (Bavard) papotage.getSource();

        if (this.isOnLine()) {
            System.out.println("Source : " + bavardSRC.getPseudo() );
            System.out.println(this.getPseudo() + " a bien reçu le message");
            System.out.println("Sujet : " + papotage.getSujet());
            System.out.println("Corps : " + papotage.getCorps() +"\n");

            System.out.println("------------------------------------------------------------ ::::::::::::::::::::::::::::::::::::::::: " + this.getPseudo());
            System.out.println(this.getListePeople());
            System.out.println( ((Bavard) papotage.getSource()).getPseudo());

            if ( ( ( this.getlisteTheme().contains(papotage.getTheme())) || papotage.getTheme().contains("ONLINE") ) &&
                    ( this.getListePeople().contains( ((Bavard) papotage.getSource()).getPseudo() ) ) ){

                this.addMessage(papotage);
                this.getListShort().addElement(bavardSRC.getPseudo()+" : "+papotage.getSujet());
            }

//            System.out.println(this.getListMessageReceived());
        }
    }


    // -- Methods OnLine
    public void addOnLineListener(OnLineBavardListener ol){
        /*
        GOAL : Ajouter l'element ol a la liste destinatairesOnLine
         */
        this.destinatairesOnLine.add(ol);
    }
    public void removeOnLineListener(OnLineBavardListener ol){
        /*
        GOAL : Retirer l'element ol a la liste destinatairesOnLine
         */
        this.destinatairesOnLine.remove(ol);
    }

    public void generateNewOnLine(Bavard bavard) {
        /*
        GOAL : Permet qu'un bavard envoie une notification a son concierge et que le concierge réenvoie la notifications a ces bavards
         */

        OnLineBavardEvent online = new OnLineBavardEvent(this, bavard);

        for (OnLineBavardListener ol : this.destinatairesOnLine) {
            // -- Le concierge reçoit le message
            ol.newOnLine(online);

        }
    }
    @Override
    public void newOnLine(OnLineBavardEvent online) {
        /*
        GOAL : Envoyer les données à l'interface
         */

        Bavard bavardSRC = (Bavard) online.getSource();

        if (this.isOnLine()) {
            System.out.println("Source connected : " + bavardSRC.getPseudo() );
            System.out.println(this.getPseudo() + " a bien reçu la notif");


            this.getlistPseudoConnected().addElement(bavardSRC.getPseudo() + "<ON LINE>" );

            System.out.println(this.getlistPseudoConnected());
        }
    }

    @Override
    public boolean equals(Object o) {
        /*
            Compare 2 bavard on their pseudo
         */
        Bavard otherBavard = (Bavard) o;

        return this.getPseudo().equals(otherBavard.getPseudo());

    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, onLine, wantReceiveMessage, destinataires);
    }

}
