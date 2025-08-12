import javax.swing.*;

public class Game extends JFrame{ //creates the capbability of creating a game
    GamePanel game = new GamePanel(); //creates new  gamepanel
    public Game() { //Is used to actually run the code in the main class
        super("Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(game);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        new Game();
    } 
}
