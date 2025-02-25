package game;
import city.cs.engine.UserView;
import city.cs.engine.World;
import game.level.*;
import javax.swing.*;
import java.awt.*;


/**A class to create the basic GUI of the game*/

public class MyView extends UserView {

    private Image background;

    public MyView(World w, int width, int height){
        super(w,width,height);

        //this changes the background whenever the level changes
        if (Game.level instanceof Level1){
            background = new ImageIcon("data/intro.gif").getImage();
        }

        else if (Game.level instanceof Boss1Fight){
            background = new ImageIcon("data/level1gif.gif").getImage();
        }
        else if (Game.level instanceof Level2){
            background = new ImageIcon("data/level2.gif").getImage();
        }
        else if (Game.level instanceof Boss2Fight){
            background = new ImageIcon("data/background3.png").getImage();
        }
        else if (Game.level instanceof LevelEnd){
            background = new ImageIcon("data/ending.gif").getImage();
        }
    }
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    /**adding on-screen text*/
   @Override
    protected void paintForeground(Graphics2D g) {

       if (Game.level instanceof Level1){
           g.setFont(new Font("SansSerif", Font.BOLD, 30));
           g.setColor(Color.BLACK);
           g.fillRect(20,20,105,40);
           g.fillRect(20,70,140,40);
           g.fillRect(20,150,250,40);
           g.fillRect(20,200,255,40);
           g.fillRect(20,250,320,40);
           g.fillRect(20,300,320,40);

           g.setColor(Color.WHITE);
           g.drawString("Kills: " + CharacterController.getKillCount(), 20, 50);
           g.setColor(Color.WHITE);
           g.drawString("Coins: " + CharacterController.getCoinCount(), 20,100);
           g.setColor(Color.GREEN.darker());
           g.drawString("HP: " + CharacterController.getLiveCount(), 600,70);

           g.setColor(Color.WHITE);
           g.drawString("Move with W,A,D", 20,180);
           g.drawString("Shoot with Space", 20,230);
           g.drawString("Save the game with P", 20,280);
           g.drawString("Load the game with L", 20,330);

       }
       else if (Game.level instanceof Boss1Fight){
           g.setFont(new Font("SansSerif", Font.BOLD, 30));
           g.setColor(Color.BLACK);
           g.fillRect(20,20,105,40);
           g.fillRect(20,70,140,40);
           g.setColor(Color.WHITE);
           g.drawString("Coins: " + CharacterController.getCoinCount(),20,100);
           g.setColor(Color.WHITE);
           g.drawString("Kills: " + CharacterController.getKillCount(), 20, 50);
           g.setColor(Color.GREEN.darker());
           g.drawString("HP: " + CharacterController.getLiveCount(), 600,70);

       }
       else if (Game.level instanceof Level2){
           g.setFont(new Font("SansSerif", Font.BOLD, 30));
           g.setColor(Color.BLACK);
           g.fillRect(20,20,105,40);
           g.fillRect(20,70,140,40);
           g.setColor(Color.WHITE);
           g.drawString("Coins: " + CharacterController.getCoinCount(),20,100);
           g.setColor(Color.WHITE);
           g.drawString("Kills: " + CharacterController.getKillCount(), 20, 50);
           g.setColor(Color.GREEN.darker());
           g.drawString("HP: " + CharacterController.getLiveCount(), 600,70);

       }
       else if (Game.level instanceof Boss2Fight){
           g.setFont(new Font("SansSerif", Font.BOLD, 30));
           g.setColor(Color.BLACK);
           g.fillRect(20,20,105,40);
           g.fillRect(20,70,140,40);
           g.setColor(Color.WHITE);
           g.drawString("Coins: " + CharacterController.getCoinCount(),20,100);
           g.setColor(Color.WHITE);
           g.drawString("Kills: " + CharacterController.getKillCount(), 20, 50);
           g.setColor(Color.GREEN.darker());
           g.drawString("HP: " + CharacterController.getLiveCount(), 600,70);

       }
       else if (Game.level instanceof LevelEnd){
           g.setFont(new Font("SansSerif", Font.BOLD, 30));
           g.setColor(Color.BLACK);
           g.drawString("Thank you for playing." , 480,120);
           g.setColor(Color.CYAN.darker());
           g.drawString("Your stats: ", 550,170);
           g.setColor(Color.YELLOW);
           g.drawString("Coins: " + CharacterController.getCoinCount(),480,220);
           g.setColor(Color.RED);
           g.drawString("Kills: " + CharacterController.getKillCount(), 650, 220);
           g.setColor(Color.GREEN.darker());
           g.drawString("HP: " + CharacterController.getLiveCount(), 780,220);


       }
       if (CharacterController.getDeadState()== 1){

           g.setColor(Color.BLACK);
           g.fillRect(0,0,1280,720);
           g.setFont(new Font("SansSerif", Font.BOLD, 60));
           g.setColor(Color.RED);
           g.drawString("YOU DIED" , 480,120);
           g.setColor(Color.YELLOW);
           g.drawString("Coins: " + CharacterController.getCoinCount(),325,220);
           g.setColor(Color.RED);
           g.drawString("Kills: " + CharacterController.getKillCount(), 650, 220);
           g.setColor(Color.GREEN.darker());
       }
   }

}
