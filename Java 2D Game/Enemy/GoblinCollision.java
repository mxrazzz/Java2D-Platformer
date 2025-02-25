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

/** A class for the collision of the goblins */

public class GoblinCollision implements CollisionListener {

    private GameLevel level;
    private Game game;
    public static SoundClip deathSound;
    public static SoundClip damageSound;

    public GoblinCollision(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }

    //sound for when character dies
    static {
        try {
            deathSound = new SoundClip("data/death.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //sound for taking damage
    static {
        try {
            damageSound = new SoundClip("data/hitting.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    /**
     * Collision between character and goblin - leads to game ending
       */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Goblin){
            System.out.println("touched goblin");
            CharacterController.loseLives(); //reduces lives
            damageSound.play();
            if (CharacterController.getLiveCount() == 0){ //kills player
                e.getReportingBody().destroy();
                deathSound.play();
                Game.level.stop();
                GameLevel.gameMusic.close();
                CharacterController.jumpingSound.close();
                CharacterController.bulletSound.close();
                CharacterController.setCharacterState(1);
                CharacterController.setDeadState(1);
            }
        }
    }
}
