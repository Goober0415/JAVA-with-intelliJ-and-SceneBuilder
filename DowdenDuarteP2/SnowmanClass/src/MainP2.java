/**************************
 * Your Name  jdowdenduarte@cnm.edu
 * Class MainP2
 * CIS2235
 * File: MainP2.java
 **************************/

public class MainP2 {

    public static void main(String[] args) {

        // create the first Snowman object
        Snowman snowman = new Snowman();

        // create the UI, passing it the Snowman reference
        SnowmanUI ui = new SnowmanUI(snowman);

        // start the game — returns when the player is done
        ui.play();

        // program terminates
        System.exit(0);
    }

}