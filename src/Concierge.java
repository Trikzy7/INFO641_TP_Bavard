import java.util.ArrayList;

public class Concierge implements PapotageListener {
    private String pseudo;

    // Liste contenant tous les bavards
    ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    public String getPseudo() {
        return pseudo;
    }

    public void setPeudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Concierge(String pseudo) {
        this.pseudo = "[CONCIERGE] " + pseudo;
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
            pl.newPapotage(papotage);
        }
    }


    @Override
    public void newPapotage(PapotageEvent papotage) {
        papotage.getSource();

        System.out.println(this.getPseudo() + " a bien reçu le message");
        System.out.println("Sujet : " + papotage.getSujet());
        System.out.println("Corps : " + papotage.getCorps() +"\n");

        for (PapotageListener pl : this.destinataires) {
            pl.newPapotage(papotage);
        }
    }
}
