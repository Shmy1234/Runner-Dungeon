import java.awt.*;

public class Button1 {
    private int x, y, width, height;

    public Button1(int xx, int yy, int w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return width;}
    public int getH(){return height;}


    public void draw(Graphics g, Image pic){
        g.setColor(Color.RED);
        g.drawImage(pic, x, y, width, height, null);
    }
}
