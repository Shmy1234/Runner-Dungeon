import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    private boolean []keys;
    private Timer timer;
    private Player player;
    private Button1 button1;
    private Button2 button2;
    private Level levelBackground;

    private GhostOrganizer ghostOrganizer;
    private Ghost ghost1, ghost2, ghost3, ghost4, ghost5, ghost6, ghost7, ghost8, ghost9, ghost10;
    private SpikesOrganizer spikesOrganizer;
    private Spikes spike1, spike2, spike3, spike4, spike5, spike6, spike7, spike8, spike9;
    private Spikes spike10, spike11, spike12, spike13, spike14, spike15, spike16;
    private Spikes spike17, spike18, spike19, spike20, spike21, spike22, spike23, spike24, spike25;

    private int intro=0, game=1, tutorial=2, complete=3, death=4;
    private int level = 1, hearts = 4;
    private int screen = intro;
    private BufferedImage level1, level2, level3, level4;
    private Image introBackground, tutorialBackground, deathBackground, completeBackground;
    private Image startPic, restartPic, tutorialPic, menuPic, star, greyStar, heart, darkness;

    public GamePanel(){
        System.out.println("hi");
        try {
            level1 = ImageIO.read(new File("../images/Level_1_Mask.png"));
            level2 = ImageIO.read(new File("../images/Level_2_Mask.png"));
            level3 = ImageIO.read(new File("../images/Level_3_Mask.png"));
            level4 = ImageIO.read(new File("../images/Level_4_Mask.png"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        keys = new boolean[KeyEvent.KEY_LAST+1];
        setPreferredSize(new Dimension(800, 800));
        introBackground = new ImageIcon("../images/Castle.png").getImage();
        tutorialBackground = new ImageIcon("../images/Instructions.png").getImage();
        deathBackground = new ImageIcon("../images/death_background.png").getImage();
        completeBackground = new ImageIcon("../images/complete_background.png").getImage();
        startPic = new ImageIcon("../images/start.png").getImage();
        restartPic = new ImageIcon("../images/restart.png").getImage();
        tutorialPic = new ImageIcon("../images/tutorial.png").getImage();
        menuPic = new ImageIcon("../images/menu.png").getImage();
        star = new ImageIcon("../images/star.png").getImage();
        greyStar = new ImageIcon("../images/grey_star.png").getImage();
        heart = new ImageIcon("../images/heart.png").getImage();
        darkness = new ImageIcon("../images/darkness.png").getImage();
        player = new Player(250 ,385,40,35);
        button1 = new Button1(300, 450, 200, 60);
        button2 = new Button2(300, 550, 200, 60);
        levelBackground = new Level(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        spikesOrganizer = new SpikesOrganizer();
        ghostOrganizer = new GhostOrganizer();

        spike1 = new Spikes(130, 2, 1, 400, 390, 60, 42);
        spike2 = new Spikes(40, 3, 2, 500, 185, 60, 42);
        spike3 = new Spikes(30, 3, 2, 500, 620, 60, 42);
        spike4 = new Spikes(40, 3, 2, 920, 185, 60, 42);
        spike5 = new Spikes(30, 3, 2, 920, 620, 60, 42);
        spike6 = new Spikes(150, 2, 2, 1450, 650, 60, 42);
        spike7 = new Spikes(150, 2, 2, 1450, 200, 60, 42);
        spike8 = new Spikes(150, 2, 2, 1700, 600, 60, 42);
        spike9 = new Spikes(150, 2, 2, 1700, 250, 60, 42);

        spike10 = new Spikes(130, 2, 3, 470, 180, 100, 45);
        spike11 = new Spikes(130, 2, 3, 470, 400, 100, 45);
        spike12 = new Spikes(130, 2, 3, 470, 620, 100, 45);
        spike13 = new Spikes(150, 3, 3, 1615, 400, 90, 45);
        spike14 = new Spikes(150, 3, 3, 1900, 400, 100, 45);
        spike15 = new Spikes(250, 2, 3, 2600, 210, 100, 45);
        spike16 = new Spikes(200, 2, 3, 2600, 580, 100, 45);

        spike17 = new Spikes(600, 3, 4, 200, 500, 100, 45);
        spike18 = new Spikes(600, 3, 4, 1200, 500, 100, 45);
        spike19 = new Spikes(400, 3, 4, 1600, 500, 100, 45);
        spike20 = new Spikes(200, 2, 4, 2200, 1000, 100, 45);
        spike21 = new Spikes(100, 3, 4, 2850, 300, 100, 45);
        spike22 = new Spikes(80, 3, 4, 2600, 900, 100, 45);
        spike23 = new Spikes(150, 2, 4, 2200, 1380, 100, 45);
        spike24 = new Spikes(150, 2, 4, 2600, 1500, 100, 45);

        spikesOrganizer.add(spike1);
        spikesOrganizer.add(spike2);
        spikesOrganizer.add(spike3);
        spikesOrganizer.add(spike4);
        spikesOrganizer.add(spike5);
        spikesOrganizer.add(spike6);
        spikesOrganizer.add(spike7);
        spikesOrganizer.add(spike8);
        spikesOrganizer.add(spike9);
        spikesOrganizer.add(spike10);
        spikesOrganizer.add(spike11);
        spikesOrganizer.add(spike12);
        spikesOrganizer.add(spike13);
        spikesOrganizer.add(spike14);
        spikesOrganizer.add(spike15);
        spikesOrganizer.add(spike16);
        spikesOrganizer.add(spike17);
        spikesOrganizer.add(spike18);
        spikesOrganizer.add(spike19);
        spikesOrganizer.add(spike20);
        spikesOrganizer.add(spike21);
        spikesOrganizer.add(spike22);
        spikesOrganizer.add(spike23);
        spikesOrganizer.add(spike24);
        ghost1 = new Ghost(2, 1, 1200, 390, 32, 40);
        ghost2 = new Ghost(2, 1, 1800, 600, 32, 40);
        ghost3 = new Ghost(3, 1, 1000, 390, 32, 40);
        ghost4 = new Ghost(3, 1, 2400, 390, 32, 40);
        ghost5 = new Ghost(3, 1, 3800, 400, 32, 40);
        ghost6 = new Ghost(4, 1, 150, 300, 32, 40);
        ghost7 = new Ghost(4, 1, 1640, 900, 32, 40);
        ghost8 = new Ghost(4, 1, 1640, 250, 32, 40);
        ghost9 = new Ghost(4, 1, 2700, 800, 32, 40);
        ghost10 = new Ghost(4, 1, 2300, 300, 32, 40);
        ghostOrganizer.add(ghost1);
        ghostOrganizer.add(ghost2);
        ghostOrganizer.add(ghost3);
        ghostOrganizer.add(ghost4);
        ghostOrganizer.add(ghost5);
        ghostOrganizer.add(ghost6);
        ghostOrganizer.add(ghost7);
        ghostOrganizer.add(ghost8);
        ghostOrganizer.add(ghost9);
        ghostOrganizer.add(ghost10);

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(20, this);
        timer.start();
    }

    public void death(){
        hearts-=1;
        levelBackground.reset();
        ghostOrganizer.reset();
        spikesOrganizer.reset();
        if(hearts <= 0){
            screen = death;
            level = 1;
            levelBackground.levelChange(1);
        }
    }

    public void move(){
        levelBackground.move(spikesOrganizer, ghostOrganizer, keys, player.getX(), player.getY(), player.getW(), player.getH());
        spikesOrganizer.move();
        ghostOrganizer.move(player.getX(), player.getY());
        if(spikesOrganizer.death(level, player.getX(), player.getY(), player.getW(), player.getH())
                || ghostOrganizer.death(level, player.getX(), player.getY(), player.getW(), player.getH())){
            death();
        }
        if(levelBackground.exit(player.getX(), player.getY())){
            levelBackground.reset();
            ghostOrganizer.reset();
            spikesOrganizer.reset();
            if(level == 4){
                screen = complete;
                level = 1;
            }
            else if(level == 3){
                level = 4;
            }
            else if(level == 2){
                level = 3;
            }
            else{
                level = 2;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(screen == game){
            move();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent k){
        int key = k.getKeyCode();
        keys[key] = false;
    }

    @Override
    public void keyPressed(KeyEvent k){
        int key = k.getKeyCode();
        keys[key] = true;
    }

    @Override
    public void keyTyped(KeyEvent k){}
    @Override
    public void	mouseClicked(MouseEvent e){}
    @Override
    public void	mouseEntered(MouseEvent e){}
    @Override
    public void	mouseExited(MouseEvent e){}
    @Override
    public void	mouseReleased(MouseEvent e){}
    @Override
    public void	mousePressed(MouseEvent e){
        if (e.getX() > button1.getX() && e.getX() < button1.getX() + button1.getW()
                && e.getY() > button1.getY() && e.getY() < button1.getY() + button1.getH()) {
            if (screen == intro || screen == death || screen == complete) {
                screen = game;
                level = 1;
                hearts = 4;
            }
        }
        if (e.getX() > button2.getX() && e.getX() < button2.getX() + button2.getW()
                && e.getY() > button2.getY() && e.getY() < button2.getY() + button2.getH()) {
            if(screen == intro){
                screen = tutorial;
            }
            else if(screen == complete || screen == death || screen == tutorial) {
                screen = intro;
                level = 1;
                hearts = 4;
            }
        }
    }

    @Override
    public void paint(Graphics g){
        if(screen == intro){
            g.drawImage(introBackground,0,0,800, 800, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 170));
            g.drawString("RUNNER",50,350);
            g.setColor(Color.ORANGE);
            g.drawString("RUNNER",40,340);
            button1.draw(g, startPic);
            button2.draw(g, tutorialPic);
        }
        else if(screen == game){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,800,800);
            levelBackground.draw(g);
            spikesOrganizer.draw(g, level);
            ghostOrganizer.draw(g, level);
            player.draw(g, keys, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
            g.drawImage(darkness,0,0,800, 800, null);
            if(hearts > 0){
                g.drawImage(heart,40,30,100, 100, null);
            }
            if(hearts > 1){
                g.drawImage(heart,180,30,100, 100, null);
            }
            if(hearts > 2){
                g.drawImage(heart,320,30,100, 100, null);
            }
            if(hearts > 3){
                g.drawImage(heart,460,30,100, 100, null);
            }
        }
        else if(screen == tutorial){
            g.drawImage(tutorialBackground,0,0,800, 800, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 120));
            g.drawString("Tutorial",200,200);
            g.setColor(Color.BLUE);
            g.drawString("Tutorial",208,190);
            button2.draw(g, menuPic);
        }
        else if(screen == complete){
            g.drawImage(completeBackground,0,0,800, 800, null);
            if(hearts == 4){
                g.drawImage(star,50,50,200, 200, null);
                g.drawImage(star,300,50,200, 200, null);
                g.drawImage(star,550,50,200, 200, null);
            }
            else if(hearts == 3){
                g.drawImage(star,50,50,200, 200, null);
                g.drawImage(star,300,50,200, 200, null);
                g.drawImage(greyStar,560,50,200, 200, null);
            }
            else if(hearts == 2){
                g.drawImage(star,50,50,200, 200, null);
                g.drawImage(greyStar,300,50,200, 200, null);
                g.drawImage(greyStar,550,50,200, 200, null);
            }
            else{
                g.drawImage(greyStar,50,50,200, 200, null);
                g.drawImage(greyStar,300,50,200, 200, null);
                g.drawImage(greyStar,550,50,200, 200, null);
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 70));
            g.drawString("Congratulations",160,410);
            g.setColor(Color.YELLOW);
            g.drawString("Congratulations",154,400);
            button1.draw(g, restartPic);
            button2.draw(g, menuPic);
        }
        else if(screen == death){
            g.drawImage(deathBackground,0,0,800, 800, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 130));
            g.drawString("Try Again",100,350);
            g.setColor(Color.BLUE);
            g.drawString("Try Again",108,342);
            button1.draw(g, restartPic);
            button2.draw(g, menuPic);
        }
    }
}
