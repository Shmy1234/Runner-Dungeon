import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Level {
    private int x, y;
    private int up, down, left, right;
    private int px, py;
    private int level;
    private BufferedImage level1, level2, level3, level4;
    private Image level1Pic, level2Pic, level3Pic, level4Pic, spike;

    public Level(int u, int d, int l, int r){
        try {
            level1 = ImageIO.read(new File("../images/Level_1_Mask.png"));
            level2 = ImageIO.read(new File("../images/Level_2_Mask.png"));
            level3 = ImageIO.read(new File("../images/Level_3_Mask.png"));
            level4 = ImageIO.read(new File("../images/Level_4_Mask.png"));
        }
        catch (IOException e) {
            System.out.println(e);
        }

        level1Pic = new ImageIcon("../images/Level_1_Background.png").getImage();
        level2Pic = new ImageIcon("../images/Level_2_Background.png").getImage();
        level3Pic = new ImageIcon("../images/Level_3_Background.png").getImage();
        level4Pic = new ImageIcon("../images/Level_4_Background.png").getImage();
        spike = new ImageIcon("../images/spikes.png").getImage();

        x = 0;
        y = 0;
        up = u;
        down = d;
        left = l;
        right = r;

        level = 1;

        px = x;
        py = y;
    }

    public void levelChange(int l){
        level = l;
    }

    private boolean clear(int xx, int yy, int w, int h){
        int wall = -13369856;
        int a, b, c, d;

        int dx = xx - x;
        int dy = yy - y;

        if(level == 1){
            a = level1.getRGB(dx, dy);
            b = level1.getRGB(dx+w, dy);
            c = level1.getRGB(dx, dy+h);
            d = level1.getRGB(dx+w, dy+h);
        }
        else if(level == 2){
            a = level2.getRGB(dx, dy);
            b = level2.getRGB(dx+w, dy);
            c = level2.getRGB(dx, dy+h);
            d = level2 .getRGB(dx+w, dy+h);
        }
        else if(level == 3){
            a = level3.getRGB(dx, dy);
            b = level3.getRGB(dx+w, dy);
            c = level3.getRGB(dx, dy+h);
            d = level3.getRGB(dx+w, dy+h);
        }
        else{
            a = level4.getRGB(dx, dy);
            b = level4.getRGB(dx+w, dy);
            c = level4.getRGB(dx, dy+h);
            d = level4.getRGB(dx+w, dy+h);
        }

        if(a != wall && b != wall && c != wall && d != wall){
            return true;
        }
        else{
            return false;
        }
    }

    public void move(SpikesOrganizer s, GhostOrganizer g, boolean []keys, int xx, int yy, int w, int h){
        if(keys[up] && clear(xx,yy-6, w, h)){

            y += 6;
            s.moveByPlayer(0, 6);
            g.moveByPlayer(0, 6);
        }
        if(keys[down] && clear(xx,yy+6, w, h)){
            y -= 6;
            s.moveByPlayer(0, -6);
            g.moveByPlayer(0, -6);
        }
        if(keys[left] && clear(xx-6,yy, w, h)){
            x += 6;
            s.moveByPlayer(6, 0);
            g.moveByPlayer(6, 0);
        }
        if(keys[right] && clear(xx+6,yy, w, h)){
            x -= 6;
            s.moveByPlayer(-6, 0);
            g.moveByPlayer(-6, 0);
        }
    }

    public boolean exit(int xx, int yy){
        int wall = -130839;

        int dx = xx + px - x;
        int dy = yy + py - y;
        int c;

        if(level == 1){
            c = level1.getRGB(dx, dy);
        }
        else if(level == 2){
            c = level2.getRGB(dx, dy);
        }
        else if(level == 3){
            c = level3.getRGB(dx, dy);
        }
        else{
            c = level4.getRGB(dx, dy);
        }

        if(c != wall){
            return false;
        }
        else{
            if(level == 4){
                levelChange(1);
            }
            else if(level == 3){
                levelChange(4);
            }
            else if(level == 2){
                levelChange(3);
            }
            else{
                levelChange(2);
            }
            return true;
        }
    }

    public void reset(){
        x = px;
        y = py;
    }

    public void draw(Graphics g){
        if(level == 1){
            g.drawImage(level1Pic, x, y,null);
        }
        else if(level == 2){
            g.drawImage(level2Pic, x, y,null);
        }
        else if(level == 3){
            g.drawImage(level3Pic, x, y,null);
        }
        else{
            g.drawImage(level4Pic, x, y,null);
        }
    }
}
