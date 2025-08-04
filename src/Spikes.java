public class Spikes{

    private int x, y, width, height;
    private int px, py;
    private int level;
    private int moving, distance, limit, type;

    public Spikes(int lim, int t, int lev, int xx, int yy, int w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;

        px = x;
        py = y;

        moving = 2;

        level = lev;
        type = t;
        limit = lim;
    }

    public void moveByPlayer(int xx, int yy){
        x += xx;
        y += yy;
    }

    public void move(){

        if(type == 2){
            x += moving;
        }
        if(type == 3){
            y += moving;
        }
        distance += moving;
        if(Math.abs(distance) == limit){
            moving *= -1;
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return width;}
    public int getH(){return height;}
    public int getLev(){return level;}

    public void reset(){
        x = px;
        y = py;
    }
}
