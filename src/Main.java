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
        louisArmand.receiveMessageBavard(robert, false);
        System.out.println(louisArmand.getConcierge().destinataires);

        for (Bavard b : louisArmand.getListeBavard()) {
            if (b == robert) {
                System.out.println(robert.destinataires);
            }
        }




//        louisArmand.receiveMessageBavard(henry, true);
//
//
//        // -- Un bavard envoie le message
//        louisArmand.bavardSendMessage(robert, "Super Sujet", "Super Corps vraiment long");

        // -- Concierge envoie le message
//        louisArmand.conciergeSendMessage("Sujet de gege", "Corps de gege");
    }
}