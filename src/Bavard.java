import java.util.ArrayList;

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
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl) {
        destinataires.remove(pl);
    }

    public void generateNewPapotage(String sujet, String corps) {
        PapotageEvent papotage = new PapotageEvent(this, sujet, corps);

        for (PapotageListener pl : this.destinataires) {
            // -- Le concierge reçoit le message
            pl.newPapotage(papotage);

            // -- Le concierge réenvoie le message à sa liste de bavard
            pl.generateNewPapotage(sujet, corps);
        }
    }

    @Override
    public void newPapotage(PapotageEvent papotage) {
        papotage.getSource();

        if (this.isOnLine()) {
            System.out.println(this.getPseudo() + " a bien reçu le message");
            System.out.println("Sujet : " + papotage.getSujet());
            System.out.println("Corps : " + papotage.getCorps() +"\n");
        }
    }
}
