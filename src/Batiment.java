import java.util.ArrayList;

public class Batiment {

    private String name;

    Concierge concierge = null;
    ArrayList<Bavard> listeBavard = new ArrayList<Bavard>();

    public Concierge getConcierge() {
        return concierge;
    }

    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

    public ArrayList<Bavard> getListeBavard() {
        return listeBavard;
    }

    public void setListeBavard(ArrayList<Bavard> listeBavard) {
        this.listeBavard = listeBavard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Batiment(String name) {
        this.name = name;
    }



    // ----- Methods
    public boolean addConcierge(Concierge concierge) {
        if (this.getConcierge() == null) {
            this.setConcierge( concierge );
            return true;
        }
        else {
            System.out.println("Un concierge existe deja");
            return false;
        }


    }

    public void replaceConcierge(Concierge concierge) {
        this.setConcierge( concierge );
    }

    public void addBavard(Bavard bavard) {
        this.listeBavard.add( bavard );
    }

    public void removeBavard(Bavard bavard) {
        this.listeBavard.remove( bavard );
    }

    public void connectBavard (Bavard bavard) {

        for (Bavard b : this.getListeBavard()) {
            if (b == bavard)
                b.setOnLine(true);
        }
    }

    public void disconnectBavard (Bavard bavard) {

        for (Bavard b : this.getListeBavard()) {
            if (b == bavard)
                b.setOnLine(false);
        }
    }

    public void receiveMessageBavard (Bavard bavard, boolean wantReceiveMessage) {

        if (wantReceiveMessage) {
            for (Bavard b : this.getListeBavard()) {
                if (b == bavard) {
                    // -- Le bavard veut recevoir les messages du concierge
                    b.setWantReceiveMessage(true);

                    // -- On ajoute le concierge dans la liste du bavard
                    this.addConciergeInListBavard(b);

                    // -- On ajoute le bavard dans la liste du concierge
                    this.addBavardsInListConcierge(b);
                }

            }
        }
        else {
            for (Bavard b : this.getListeBavard()) {
                if (b == bavard) {
                    // -- Le bavard veut recevoir les messages du concierge
                    b.setWantReceiveMessage(false);

                    // -- On ajoute le concierge dans la liste du bavard
                    this.addConciergeInListBavard(b);

                    // -- On remove le bavard dans la liste du concierge
                    this.removeBavardsInListConcierge(b);
                }
            }
        }

    }


    public void addBavardsInListConcierge(Bavard b) {
        if (b.isWantReceiveMessage() && !this.getConcierge().destinataires.contains(b))
            // Add les bavards qui veulent recevoir les messages pour le concierge
            this.getConcierge().addPapotageListener(b);
    }

    public void addConciergeInListBavard(Bavard b) {
        // Add le concierge pour les bavards qui veulent recevoir les messages
        if (!b.destinataires.contains(this.getConcierge()))
            b.addPapotageListener(this.getConcierge());
    }

    public Bavard getBavardFromList(Bavard bavardParam) {
        /*
        Permet de récupérer un bavard de la liste des bavards
         */
        Bavard bavardFound = null;

        for ( Bavard b : this.getListeBavard() ) {
            if (b == bavardParam)
                bavardFound = b;
        }

        return bavardFound;
    }

    public void removeBavardsInListConcierge(Bavard b) {
        if (!b.isWantReceiveMessage())
            // Add les bavards qui veulent recevoir les messages pour le concierge
            this.getConcierge().removePapotageListener(b);
    }

    public void removeConciergeInListBavard(Bavard b) {
        if (!b.isWantReceiveMessage())
            // Add le concierge pour les bavards qui veulent recevoir les messages
            b.removePapotageListener(this.getConcierge());
    }

    public void bavardSendMessage(Bavard bavard, String sujet, String corps) {
        if (bavard.isOnLine()) {
            for (Bavard b : this.getListeBavard()) {
                if (b == bavard) {
                    b.generateNewPapotage(sujet, corps);
                }
            }
        }
    }

    public void conciergeSendMessage(String sujet, String corps) {
        this.getConcierge().generateNewPapotage(sujet, corps);
    }

}
