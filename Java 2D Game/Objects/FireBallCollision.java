package game.Objects;
import city.cs.engine.*;
import game.CharacterController;
import game.Enemy.Goblin;
import game.Game;
import game.GameLevel;
import game.MainCharacter;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/** A class that detects collision between the fireballs and the ground or the player*/

public class FireBallCollision implements CollisionListener {

    private FallingBall fireball;
    private GameLevel level;
    private Game game;
    public static SoundClip deathSound;
    public static SoundClip damageSound;

    public FireBallCollision(FallingBall f ){
        this.fireball = f;
    }

    public GameLevel getLevel() {
        return level;
    }

    //plays sound when player dies
    static {
        try {
            deathSound = new SoundClip("data/death.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //plays when player takes damage
    static {
        try {
            damageSound = new SoundClip("data/hitting.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //collision listener for the fireball - kills character when touched
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof StaticBody ) {  //if it touches the floor, it should destroy itself
            fireball.destroy();
        }
        else if (e.getOtherBody() instanceof MainCharacter){  //if it touches the character, it destroys him
            fireball.destroy();
            CharacterController.loseLives(); //reduces lives
            damageSound.play();
            if (CharacterController.getLiveCount() == 0){ //kills player
                e.getOtherBody().destroy();
                System.out.println("died");
                deathSound.play();
                Game.level.stop();
                GameLevel.gameMusic.close();
                CharacterController.jumpingSound.close();
                CharacterController.bulletSound.close();
                CharacterController.setCharacterState(1);
                CharacterController.setDeadState(1);
            }
        }
        else if (e.getOtherBody() instanceof Goblin){
            fireball.destroy();
        }
    }
}
