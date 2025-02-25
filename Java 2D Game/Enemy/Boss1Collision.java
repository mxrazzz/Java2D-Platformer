package game.Enemy;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.CharacterController;
import game.Game;
import game.GameLevel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/** A class to detect collision with the boss */

public class Boss1Collision implements CollisionListener {

    private static SoundClip bossTouchSound;
    private GameLevel level;
    private Game game;

    public Boss1Collision(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }

    @Override //detects a collision with boss (triggers boss fight)

    public void collide(CollisionEvent e) {
       if (e.getOtherBody() instanceof Boss1 && level.isComplete()) {
            System.out.println("touched boss");
           if (CharacterController.getMuteSound() != 1){
               bossTouchSound.play(); //plays sound when boss is touched
               bossTouchSound.setVolume(0.07);   //sets volume
           }
            level.getGame().goToNextLevel();    //will move to the next level once touched
        }
    }

    //will make a sound when boss is touched
    static {
        try {
             bossTouchSound = new SoundClip("data/boss1touch.wav");
            System.out.println("Loading boss sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}
