package game;
import city.cs.engine.DebugViewer;
import game.level.*;

import javax.swing.*;
import java.awt.*;

/**
 * A world with some bodies.
 */

public class Game {

    private JFrame frame;

    /** The World in which the bodies move and interact. */
    public static GameLevel level;

    /** A graphical display of the world (a specialised JPanel). */
    private MyView view;

    /** A controller to control the character*/
    private CharacterController controller;
    
    /** Initialise a new Game. */
    public Game() {
        // make the world
        level = new Level1(this);
        view = new MyView(level, 1280, 720);

        // initialising the controller
        controller = new CharacterController(level.getMyCharacter());
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));
        //student.setAlwaysOutline(true);
        //view.setGridResolution(1);
        // add the view to a frame (Java top level window)
        frame = new JFrame("Entering: SAO");
        frame.add(view);
        ControlPanel controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.SOUTH);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        /* size the frame to fit the world view */
        frame.pack();
        //makes game start in center
        frame.setLocationRelativeTo(null); //makes game start in center
        // finally, make the frame visible
        frame.setVisible(true);

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(level, 720, 480);

        // start our game world simulation!
        level.start();
    }

    public void setLevel(int i ){

        this.level.stop();
        frame.remove(view);
        GameLevel.gameMusic.stop();
        if (i == 1){
            level = new Level1(level.getGame());
        }
        else if (i == 2) {
            level = new Level2(level.getGame());
        }
        else if (i ==3) {
            level = new Boss1Fight(level.getGame());
        }
        else if (i ==4) {
            level = new Boss2Fight(level.getGame());
        }
        else if (i ==5) {
            level = new LevelEnd(level.getGame());
        }
        view.setWorld(level);
        view = new MyView(level, 1280, 720);
        frame.setTitle(""+ level.getLevelName());
        frame.add(view);
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));
        frame.pack();
        controller.updateCharacter(level.getMyCharacter());
        level.start();
       // JFrame debugView = new DebugViewer(level, 720, 480);
    }
    public static GameLevel getLevel() {
        return level;
    }
    /** this will move the character to the next level once the initial level has been completed*/

    public void goToNextLevel(){

        if (level instanceof Level1){
            System.out.println("Level 1 Complete");
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Boss1Fight(this);
            view.setWorld(level);
            view = new MyView(level, 1280, 720);
            frame.setTitle("Boss Fight: 1");
            frame.add(view);
            view.addKeyListener(controller);
            view.addMouseListener(new GiveFocus(view));
            frame.pack();
            controller.updateCharacter(level.getMyCharacter());
            level.start();
            //JFrame debugView = new DebugViewer(level, 720, 480);

        }
        else if (level instanceof Boss1Fight){
            System.out.println("Boss fight Complete");
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Level2(this);
            view = new MyView(level, 1280, 720);
            view.setWorld(level);
            frame.setTitle("Level 2");
            frame.add(view);
            view.addKeyListener(controller);
            view.addMouseListener(new GiveFocus(view));
            frame.pack();
            controller.updateCharacter(level.getMyCharacter());
            level.start();
            //JFrame debugView = new DebugViewer(level, 720, 480);
        }
        else if (level instanceof Level2){
            System.out.println("Level 2Complete");
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Boss2Fight(this);
            view = new MyView(level, 1280, 720);
            view.setWorld(level);
            frame.setTitle("Boss Fight: 2");
            frame.add(view);
            view.addKeyListener(controller);
            view.addMouseListener(new GiveFocus(view));
            frame.pack();
            controller.updateCharacter(level.getMyCharacter());
            level.start();
            //JFrame debugView = new DebugViewer(level, 720, 480);

        }
        else if (level instanceof Boss2Fight) {
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new LevelEnd(this);
            view = new MyView(level, 1280, 720);
            view.setWorld(level);
            frame.setTitle("Thank you");
            frame.add(view);
            view.addKeyListener(controller);
            view.addMouseListener(new GiveFocus(view));
            frame.pack();
            controller.updateCharacter(level.getMyCharacter());
            level.start();
            //JFrame debugView = new DebugViewer(level, 720, 480);
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
