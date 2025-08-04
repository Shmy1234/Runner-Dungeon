import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GhostOrganizer {
    private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    private Image ghostLeft, ghostRight;

    public GhostOrganizer(){
        ghostLeft = new ImageIcon("ghost_right.png").getImage();
        ghostRight = new ImageIcon("ghost_left.png").getImage();
    }

    public void add(Ghost g){
        ghosts.add(g);
    }

    public void move(int xx, int yy){
        Ghost g;
        for(int i=0; i<ghosts.size(); i++){
            g = ghosts.get(i);
            g.move(xx, yy);
        }
    }

    public void moveByPlayer(int xx, int yy){
        Ghost g;
        for(int i=0; i<ghosts.size(); i++){
            g = ghosts.get(i);
            g.moveByPlayer(xx, yy);
        }
    }

    public boolean death(int level, int xx, int yy, int ww, int hh){
        Ghost g;
        for(int i=0; i<ghosts.size(); i++){
            g = ghosts.get(i);
            if(g.getLev() == level) {
                if (xx > g.getX() + g.getW() || xx + ww < g.getX() || yy > g.getY() + g.getH() || yy + hh < g.getY()) {
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void reset(){
        Ghost g;
        for(int i=0; i<ghosts.size(); i++){
            g = ghosts.get(i);
            g.reset();
        }
    }

    public void draw(Graphics g, int level){
        Ghost gg;
        for(int i=0; i<ghosts.size(); i++) {
            gg = ghosts.get(i);
            if(gg.getLev() == level){
                if(gg.getDir() == 1){
                    g.drawImage(ghostLeft, gg.getX(), gg.getY(), gg.getW(), gg.getH(), null);
                }
                else{
                    g.drawImage(ghostRight, gg.getX(), gg.getY(), gg.getW(), gg.getH(), null);
                }
            }
        }
    }
}
