public class Ghost{

    private int x, y, width, height;
    private int px, py;
    private int level, direction, moving;

    public Ghost(int lev, int d, int xx, int yy, int w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;

        px = x;
        py = y;

        direction = 1;
        moving = 1;
        level = lev;
    }

    public void moveByPlayer(int xx, int yy){
        x += xx;
        y += yy;
    }
    public void move(int xx, int yy){

        if(Math.abs(xx-x) <= 400 && Math.abs(yy-y) <= 400){
            if(x>xx){
                x-=moving;
                direction = -1;
            }
            if(xx>x){
                x+=moving;
                direction = 1;
            }
            if(y>yy){
                y-=moving;
            }
            if(yy>y){
                y+=moving;
            }
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return width;}
    public int getH(){return height;}
    public int getLev(){return level;}
    public int getDir(){return direction;}

    public void reset(){
        x = px;
        y = py;
    }
}
