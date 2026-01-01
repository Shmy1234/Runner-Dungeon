import javax.swing.*;

public class Game extends JFrame{
    GamePanel game = new GamePanel();
    public Game() {
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
