import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpikesOrganizer {
    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    private Image spikePic;

    public SpikesOrganizer(){
        spikePic = new ImageIcon("../images/spikes.png").getImage();
    }

    public void add(Spikes s){
        spikes.add(s);
    }

    public void move(){
        Spikes s;
        for(int i=0; i<spikes.size(); i++){
            s = spikes.get(i);
            s.move();
        }
    }

    public void moveByPlayer(int xx, int yy){
        Spikes s;
        for(int i=0; i<spikes.size(); i++){
            s = spikes.get(i);
            s.moveByPlayer(xx, yy);
        }
    }

    public boolean death(int level, int xx, int yy, int ww, int hh){
        Spikes s;
        for(int i=0; i<spikes.size(); i++){
            s = spikes.get(i);
            if(s.getLev() == level){
                if(xx>s.getX()+s.getW() || xx+ww<s.getX() || yy>s.getY()+s.getH() || yy+hh<s.getY()){

                }
                else{
                    System.out.println("hi");
                    return true;
                }
            }
        }
        return false;
    }

    public void reset(){
        Spikes s;
        for(int i=0; i<spikes.size(); i++){
            s = spikes.get(i);
            s.reset();
        }
    }

    public void draw(Graphics g, int level){
        Spikes s;
        for(int i=0; i<spikes.size(); i++) {
            s = spikes.get(i);
            if(s.getLev() == level){
                g.drawImage(spikePic, s.getX(), s.getY(), s.getW(), s.getH(), null);
            }
        }
    }
}
