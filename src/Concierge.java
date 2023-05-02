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
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl) {
        destinataires.remove(pl);
    }

    public void generateNewPapotage(String sujet, String corps) {
        PapotageEvent papotage = new PapotageEvent(this, sujet, corps);

        for (PapotageListener pl : this.destinataires) {
            pl.newPapotage(papotage);
        }
    }

    @Override
    public void newPapotage(PapotageEvent papotage) {

        Bavard bavardSRC = (Bavard) papotage.getSource();
        System.out.println( bavardSRC.getPseudo() );

        System.out.println("Source : " + this.getPseudo() + " a bien reçu le message");
        System.out.println("Sujet : " + papotage.getSujet());
        System.out.println("Corps : " + papotage.getCorps() +"\n");

        this.addMessage(papotage);
        this.getListShort().addElement(bavardSRC.getPseudo()+" : "+papotage.getSujet());

//        System.out.println(this.getListMessageReceived());

        for (PapotageListener pl : this.destinataires) {
            pl.newPapotage(papotage);
        }

    }


    // -- Methods OnLine
    public void addOnLineListener(OnLineBavardListener ol) {
        this.destinatairesOnLine.add(ol);
    }

    public void removeOnLineListener(OnLineBavardListener ol) {
        this.destinatairesOnLine.remove(ol);
    }

    public void generateNewOnLine(Bavard bavard) {
        OnLineBavardEvent online = new OnLineBavardEvent(this, bavard);

        for (OnLineBavardListener ol : this.destinatairesOnLine) {
            ol.newOnLine(online);
        }
    }

    @Override
    public void newOnLine(OnLineBavardEvent online) {

        Bavard bavardSRC = (Bavard) online.getSource();

        System.out.println("Source connected : " + bavardSRC.getPseudo() );
        System.out.println(this.getPseudo() + " a bien reçu la notif");

//        this.addMessage(papotage);
//        this.getListShort().addElement(bavardSRC.getPseudo()+" : "+papotage.getSujet());

//        System.out.println(this.getListMessageReceived());

        for (OnLineBavardListener ol : this.destinatairesOnLine) {
            ol.newOnLine(online);
        }
    }
}
