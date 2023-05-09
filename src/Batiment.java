import javax.swing.*;
import java.util.ArrayList;

public class Batiment {
    //ATTRIBUT
    private String name;
    private Concierge concierge = null;
    private ArrayList<Bavard> listeBavard = new ArrayList<Bavard>();
    private DefaultListModel listPseudoConnected = new DefaultListModel();

    // GETTER ET SETTER
    public DefaultListModel getlistPseudoConnected(){return listPseudoConnected;}

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

    // CONSTRUCTOR
    public Batiment(String name) {
        this.name = name;
    }



    // ----- Methods

    public boolean addConcierge(Concierge concierge) {
        // ajout concierge
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
        //permet de remplacer le concierge deja existant
        this.setConcierge( concierge );
    }

    public void addBavard(Bavard bavard) {
        // ajouter un bavard à la liste de bavard
        this.listeBavard.add( bavard );
    }

    public void removeBavard(Bavard bavard) {
        // enleve le bavard de la liste de bavards
        this.listeBavard.remove( bavard );
    }

    public void connectBavard (Bavard bavard) {
        // connecte un bavard en le mettant en OnLine

        for (Bavard b : this.getListeBavard()) {
            if (b == bavard)
                b.setOnLine(true);
        }
    }

    public void disconnectBavard (Bavard bavard) {
        // deconnecte un bavard en le mettant en OffLine
        for (Bavard b : this.getListeBavard()) {
            if (b == bavard)
                b.setOnLine(false);
        }
    }


    // --------------------- METHODS SEND MESSAGE
    public void receiveMessageBavard (Bavard bavard, boolean wantReceiveMessage) {
        // permet de recevoir un message

        if (wantReceiveMessage) { // si le bavard veut recevoir des messages
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
        else { // si il ne veut pas recevoir de message
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
        // Add les bavards qui veulent recevoir les messages pour le concierge
        if (b.isWantReceiveMessage() && !this.getConcierge().destinataires.contains(b))
            this.getConcierge().addPapotageListener(b);
    }

    public void addConciergeInListBavard(Bavard b) {
        // Add le concierge pour les bavards qui veulent recevoir les messages
        if (!b.destinataires.contains(this.getConcierge()))
            b.addPapotageListener(this.getConcierge());
    }

    public void removeBavardsInListConcierge(Bavard b) {
        // Add les bavards qui veulent recevoir les messages pour le concierge
        if (!b.isWantReceiveMessage())
            this.getConcierge().removePapotageListener(b);
    }

    public void removeConciergeInListBavard(Bavard b) {
        // Add le concierge pour les bavards qui veulent recevoir les messages
        if (!b.isWantReceiveMessage())
            b.removePapotageListener(this.getConcierge());
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

    public void bavardSendMessage(Bavard bavard, String sujet,String theme, String corps) {
        // envoie d'un message par un bavard
        if (bavard.isOnLine()) { // s'il est en ligne
            for (Bavard b : this.getListeBavard()) {
                if (b == bavard) {
                    b.generateNewPapotage(sujet,theme, corps); // genere un nouveau papotage
                }
            }
        }
    }

    public void conciergeSendMessage(String sujet, String theme, String corps) {
        // le concierge envoie un message
        this.getConcierge().generateNewPapotage(sujet,theme, corps);
    }


    // --------------------- METHODS NOTIFY CONNEXION
    public void addBavardsInListOnLineConcierge(Bavard b) {
        /*
        Permet d'ajouter un bavard dans la liste des personnes onLine s'il n'est pas déjà dedans
         */
        if (!this.getConcierge().destinatairesOnLine.contains(b))
            this.getConcierge().addOnLineListener(b);
    }

    public void addConciergeInListOnLineBavard(Bavard b) {
        // Add le concierge pour les bavards qui veulent recevoir les messages
        if (!b.destinatairesOnLine.contains(this.getConcierge()))
            b.addOnLineListener(this.getConcierge());
    }

    public void removeBavardsInListOnLineConcierge(Bavard b) {
        /*
        Permet de supprimer un bavard dans la liste des personnes onLine
         */
            this.getConcierge().removeOnLineListener(b);
    }

    public void removeConciergeInListOnLineBavard(Bavard b) {
        // remove le concierge pour les bavards qui veulent recevoir les messages
        b.removeOnLineListener(this.getConcierge());
    }

    public void bavardSendNotifyConnexion(Bavard bavard) {
        if (bavard.isOnLine()) {

            // -- Ajout du concierge a la listOnLine du bavard
            addConciergeInListOnLineBavard(bavard);

            // -- Ajout du bavard a la listOnLine du concierge
            addBavardsInListOnLineConcierge(bavard);

            // -- Ajout du bavard a la liste de pseudoConnected du bâtiment
            if (!this.getlistPseudoConnected().contains(bavard.getPseudo() + " <ON LINE>")){
                this.getlistPseudoConnected().addElement(bavard.getPseudo() + " <ON LINE>" );


                // -- Envoyer un message au concierge pour dire que le bavard est connecté
                for (Bavard b : this.getListeBavard()) {
                    if (b == bavard) {
                        b.generateNewPapotage(b.getPseudo() + " connexion", "ONLINE",b.getPseudo() + " vient de se connecter");
                    }
                }

            }
        }
    }

    public void bavardSendNotifyDeconnexion(Bavard bavard) {

        // -- Ajout du concierge a la listOnLine du bavard
        removeConciergeInListOnLineBavard(bavard);

        // -- Ajout du bavard a la listOnLine du concierge
        removeBavardsInListOnLineConcierge(bavard);

        // -- Ajout du bavard a la liste de pseudoConnected du bâtiment
        if (this.getlistPseudoConnected().contains(bavard.getPseudo() + " <ON LINE>")){
            this.getlistPseudoConnected().removeElement(bavard.getPseudo() + " <ON LINE>" );

            // -- Envoyer un message au concierge pour dire que le bavard est connecté
            for (Bavard b : this.getListeBavard()) {
                if (b == bavard) {
                    b.generateNewPapotage(b.getPseudo() + " deconnexion", "",b.getPseudo() + " vient de se déconnecter");
                }
            }
        }
    }
}
