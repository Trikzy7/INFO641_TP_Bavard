import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // -------- CONSTRUCT OUR BATIMENT

        Batiment louisArmand = new Batiment("Louis Armand");

        // -- CONCIERGE
        Concierge gege = new Concierge("Gege");
        louisArmand.addConcierge(gege);

       // -- BAVARD
        Bavard math = new Bavard("Math");
        Bavard lila = new Bavard("Lila");
        Bavard theo = new Bavard("Theo");

        // -- ADD BAVARD
        louisArmand.addBavard(math);
        louisArmand.addBavard(lila);
        louisArmand.addBavard(theo);


        // -------- INTERFACE

        new Batiment_Concierge_Interface(louisArmand);
        new Batiment_Interface(louisArmand);

    }
}