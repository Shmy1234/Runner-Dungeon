import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{ //helps with actually running the interface of the game and running other classes to actually create a functioning game
    private boolean []keys; //array used to see what keyboard inputs are being put in
    private Timer timer;
    private Player player; //used to create the player the user uses
    private Button1 button1; //the top button of the non-game screens
    private Button2 button2; //the bottom button of the non-game screens
    private Level levelBackground; //used to move and draw the background which includes the spikes

    private GhostOrganizer ghostOrganizer; //used to organize and move all the ghosts in a level all at once
    private Ghost ghost1, ghost2, ghost3, ghost4, ghost5, ghost6, ghost7, ghost8, ghost9, ghost10; //ghosts that will be seen in the game
    private SpikesOrganizer spikesOrganizer; //used to organize and move all the spikes in a level all at once
    //spikes that will be seen during the game
    private Spikes spike1, spike2, spike3, spike4, spike5, spike6, spike7, spike8, spike9; //level 1 and 2 spikes
    private Spikes spike10, spike11, spike12, spike13, spike14, spike15, spike16; //level 3 spikes
    private Spikes spike17, spike18, spike19, spike20, spike21, spike22, spike23, spike24, spike25; //level 4 spikes

    //screens which will be seen in the game
    private int intro=0, game=1, tutorial=2, complete=3, death=4;
    private int level = 1, hearts = 4; //other integers that are used for level transitions and the health bar
    private int screen = intro; //setting the initial screen to the intro one
    private BufferedImage level1, level2, level3, level4; //the level backgrounds masks used to create the buildings in the game
    private Image introBackground, tutorialBackground, deathBackground, completeBackground; //other background images
    private Image startPic, restartPic, tutorialPic, menuPic, star, greyStar, heart, darkness; //other smaller images that will be seen in the program

    public GamePanel(){ //constructor which helps create the game
        System.out.println("hi");
        try { //used to add the level background masks to bufferreader images without crashing
            level1 = ImageIO.read(new File("../images/Level_1_Mask.png"));
            level2 = ImageIO.read(new File("../images/Level_2_Mask.png"));
            level3 = ImageIO.read(new File("../images/Level_3_Mask.png"));
            level4 = ImageIO.read(new File("../images/Level_4_Mask.png"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        keys = new boolean[KeyEvent.KEY_LAST+1]; //initializes boolean array
        setPreferredSize(new Dimension(800, 800)); //setting the hieght and width of the window
        //adding images to variables
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
        player = new Player(250 ,385,40,35); //intializes player and setting there width and height and position
        button1 = new Button1(300, 450, 200, 60); //intializes button1 and setting there width and height position
        button2 = new Button2(300, 550, 200, 60);//intializes button2 and setting there width and height position
        levelBackground = new Level(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT); //used to intialize level variable
        spikesOrganizer = new SpikesOrganizer(); //used to intialize spikesOrganizer
        ghostOrganizer = new GhostOrganizer(); //used to intialize ghostOrganizer

        //creates spikes and deciding what level and position and where they'll move
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

        //adding the spikes to the level background so when the background moves the spikes can move as well
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

        //creates ghosts and deciding what level and position they'll be in
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

        //adding the ghosts to the level background so when the background moves the spikes can move as well
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
        timer.start(); //used to start the game so they can buttons and start the game as soon as the game opens
    }

    public void death(){ //used to make the player die
        hearts-=1;
        levelBackground.reset(); //resetting the position of the level background as the level restarts when player is hit
        ghostOrganizer.reset();
        spikesOrganizer.reset();
        if(hearts <= 0){ //if theres no hearts left restart the player from the beginning and transition to the death screen
            screen = death;
            level = 1;
            levelBackground.levelChange(1);
        }
    }

    public void move(){ //allows us to moves everything else other the player to create ethe illusion of movement
        levelBackground.move(spikesOrganizer, ghostOrganizer, keys, player.getX(), player.getY(), player.getW(), player.getH()); //used to move the background like the image and spikes
        spikesOrganizer.move(); //allows the spikes to move even if the player doesn't
        ghostOrganizer.move(player.getX(), player.getY()); //moves the ghosts if they are close enough to the player
        if(spikesOrganizer.death(level, player.getX(), player.getY(), player.getW(), player.getH())
                || ghostOrganizer.death(level, player.getX(), player.getY(), player.getW(), player.getH())){ //if player hit a spike or ghost invoke death method
            death();
        }
        if(levelBackground.exit(player.getX(), player.getY())){ //if player hits the exit of the level tranistion to the next part of the game
            levelBackground.reset();//resetting the level position so the background image is from the start
            ghostOrganizer.reset();
            spikesOrganizer.reset();
            //if statement that allows us to transition from one part of the game to the next
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
    public void actionPerformed(ActionEvent e){//helps us draw and move and basically allows us to preform our actions in the game
        if(screen == game){ //if we are in the actually game part the componets in the game can move
            move();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent k){ //when key is keyreleased mthat key false to let the program know the key is not being pressed
        int key = k.getKeyCode();
        keys[key] = false;
    }

    @Override
    public void keyPressed(KeyEvent k){ //when key is pressed that key position is true which allows us to use the keys function for other aspects of the game like moving
        int key = k.getKeyCode();
        keys[key] = true;
    }

    //other mouse and key methods that we don't change
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
    public void	mousePressed(MouseEvent e){ //used to create the button functionality as we can now click on buttons on the screen
        if (e.getX() > button1.getX() && e.getX() < button1.getX() + button1.getW()
                && e.getY() > button1.getY() && e.getY() < button1.getY() + button1.getH()) { //if top bottom is pressed and in the right screen are game or restart
            if (screen == intro || screen == death || screen == complete) {
                screen = game;
                level = 1;
                hearts = 4;
            }
        }
        if (e.getX() > button2.getX() && e.getX() < button2.getX() + button2.getW()
                && e.getY() > button2.getY() && e.getY() < button2.getY() + button2.getH()) { //if top bottom is pressed and in the right screen go back to menu or tutorial
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
    public void paint(Graphics g){ //used to draw the componets of what screen we are on
        if(screen == intro){ //used to draw the componets in our screen like text and images
            g.drawImage(introBackground,0,0,800, 800, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 170));
            g.drawString("RUNNER",50,350);
            g.setColor(Color.ORANGE);
            g.drawString("RUNNER",40,340);
            button1.draw(g, startPic);
            button2.draw(g, tutorialPic);
        }
        else if(screen == game){ //used to draw the componets when we are playing the game like text and images
            g.setColor(Color.BLACK);
            g.fillRect(0,0,800,800);
            levelBackground.draw(g);
            spikesOrganizer.draw(g, level);
            ghostOrganizer.draw(g, level);
            player.draw(g, keys, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
            g.drawImage(darkness,0,0,800, 800, null); //used to get darkening affect
            //if statement which allows us to draw a heart for each heart we still have
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
        else if(screen == tutorial){ //used to draw the componets of our sort of basic menu screen that shows the basic goal of the game of getting to the end and avoiding spikes
            g.drawImage(tutorialBackground,0,0,800, 800, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 120));
            g.drawString("Tutorial",200,200);
            g.setColor(Color.BLUE);
            g.drawString("Tutorial",208,190);
            button2.draw(g, menuPic);
        }
        else if(screen == complete){ //used to draw the componets of our complete screen like text and images
            g.drawImage(completeBackground,0,0,800, 800, null);
            //used to draw the stars as it sees how many hearts you have and the more hearts you lost the more grey stars you get out of 3
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
        else if(screen == death){ //used to draw the componets of our death screen like text and images
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