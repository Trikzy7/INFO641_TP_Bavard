import java.util.ArrayList;
import java.util.Objects;

public class Bavard  implements PapotageListener{

    private String pseudo;
    private boolean onLine;
    private boolean wantReceiveMessage;

    // Liste contenant 1 concierge
    ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

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

    public Bavard(String pseudo) {
        this.pseudo = "[MOLDU] " + pseudo;
    }

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

    public void generateNewPapotage(String sujet, String corps) {
        /*
        GOAL : Permet qu'un bavard envoie un message a son concierge et que le concierge réenvoie les messages
         */

        PapotageEvent papotage = new PapotageEvent(this, sujet, corps);

        for (PapotageListener pl : this.destinataires) {
            // -- Le concierge reçoit le message
            pl.newPapotage(papotage);

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

    @Override
    public void newPapotage(PapotageEvent papotage) {
        /*
        GOAL : Ajouter l'element pl a la liste
         */
        papotage.getSource();

        if (this.isOnLine()) {
            System.out.println(this.getPseudo() + " a bien reçu le message");
            System.out.println("Sujet : " + papotage.getSujet());
            System.out.println("Corps : " + papotage.getCorps() +"\n");
        }
    }
}
