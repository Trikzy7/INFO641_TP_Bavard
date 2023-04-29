import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Batiment louisArmand = new Batiment("Louis Armand");

        // -- CONCIERGE
        Concierge gege = new Concierge("Gege");
        louisArmand.addConcierge(gege);

        // -- BAVARD
        Bavard robert = new Bavard("Robert");
        Bavard henry = new Bavard("Henry");
        louisArmand.addBavard(robert);
        louisArmand.addBavard(henry);

            // -- Connect Bavard
        louisArmand.connectBavard(robert);
        louisArmand.connectBavard(henry);

            // -- Receive Message
        louisArmand.receiveMessageBavard(robert, true);
        louisArmand.receiveMessageBavard(henry, true);



//        // -- Un bavard envoie le message
        louisArmand.bavardSendMessage(robert, "Super Sujet", "Super Corps vraiment long");

        // -- Concierge envoie le message
        //louisArmand.conciergeSendMessage("Sujet de gege", "Corps de gege");


            // -- Test des interfaces
        //new Batiment_Interface();
        //new Batiment_Concierge_Interface();
        new Bavard_Interface();

    }
}