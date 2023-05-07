import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // -------- Without Interface

        Batiment louisArmand = new Batiment("Louis Armand");

        // -- CONCIERGE
        Concierge gege = new Concierge("Gege");
        louisArmand.addConcierge(gege);
//
//        // -- BAVARD
        Bavard math = new Bavard("Math");
        Bavard lila = new Bavard("Lila");
        Bavard theo = new Bavard("Theo");

//        System.out.println(robert.equals(henry));

//        System.out.println( louisArmand.getListeBavard().contains(henry) );


        louisArmand.addBavard(math);
        louisArmand.addBavard(lila);
        louisArmand.addBavard(theo);

//        for(Bavard b : louisArmand.getListeBavard()) {
//            // -- Si le bavard qu'on veut créer a déjà été créé
//            if (b.getPseudo().equals( new Bavard("Henry").getPseudo() )) {
//                System.out.println("test");
//            }
//            System.out.println(b.getPseudo());
//        }
//
//            // -- Connect Bavard
//        louisArmand.connectBavard(robert);
//        louisArmand.connectBavard(henry);
//
//            // -- Receive Message
//        louisArmand.receiveMessageBavard(robert, true);
//        louisArmand.receiveMessageBavard(henry, true);
//
//
//
////        // -- Un bavard envoie le message
//        louisArmand.bavardSendMessage(robert, "Super Sujet", "Super Corps vraiment long");
//
//        // -- Concierge envoie le message
//        //louisArmand.conciergeSendMessage("Sujet de gege", "Corps de gege");
//
//
//            // -- Test des interfaces
//        //new Batiment_Interface();
//        //new Batiment_Concierge_Interface();
//





        // -------- With Interface

//        Batiment louisArmand = new Batiment("Louis Armand");

//        new Bavard_Interface();
        //new Batiment_Concierge_Interface(louisArmand);
        new Batiment_Interface(louisArmand);

    }
}