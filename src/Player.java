import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int x, y, width, height;
    private int timer;
    private Image idle, idleLeft, idleRight, runLeftPic, runRightPic;
    private ArrayList<Image> runLeft = new ArrayList<Image>();
    private ArrayList<Image> runRight = new ArrayList<Image>();


    public Player(int xx, int  yy, int  w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;


        idleRight = new ImageIcon("idle_right.png").getImage();
        idleLeft = new ImageIcon("idle_left.png").getImage();
        idle = idleRight;


        for(int i = 1; i<=4; i++){
            runLeftPic = new ImageIcon("Knight_runLeft_"+i+".png").getImage();
            runRightPic = new ImageIcon("Knight_runRight_"+i+".png").getImage();
            for(int j = 1; j<=4; j++){
                runLeft.add(runLeftPic);
                runRight.add(runRightPic);
            }
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return width;}
    public int getH(){return height;}

    public void draw(Graphics g, boolean[] keys, int u, int d, int l, int r){

        if(keys[u] != true && keys[d] != true && keys[l] != true && keys[r] != true){
            timer = 0;
            g.drawImage(idle, x, y, width, height, null);
        }
        else if(keys[u] || keys[d]){
            if(idle == idleLeft){
                g.drawImage(runLeft.get(timer), x, y, width, height, null);
            }
            else{
                g.drawImage(runRight.get(timer), x, y, width, height, null);
            }
            timer+=1;
        }
        else if(keys[r]){
            idle = idleRight;
            g.drawImage(runRight.get(timer), x, y, width, height, null);
            timer+=1;
        }
        else if(keys[l]){
            idle = idleLeft;
            g.drawImage(runLeft.get(timer), x, y, width, height, null);
            timer+=1;
        }

        if(timer >= runLeft.size()){
            timer = 0;
        }
    }
}
