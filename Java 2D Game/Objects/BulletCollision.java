package game.Objects;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import game.CharacterController;
import game.Enemy.Goblin;
import game.Game;
import game.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/** A class to create a collision listener between the bullet and other objects */

public class BulletCollision implements CollisionListener {

    private GameLevel level;
    private Game game;
    private Bullet bullet;
    public static SoundClip killSound;

    public BulletCollision(Bullet b ){
        this.bullet = b;
    }
    //plays sound whenever enemy is killed
    static {
        try {
            killSound = new SoundClip("data/kill.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    /**
     * this creates collision between the bullet and goblins - destroying it
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Goblin){
            System.out.println("touched goblin");
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
            CharacterController.addKill();
            if (CharacterController.getMuteSound() != 1){ //plays sound if game isn't muted
                killSound.play();
                killSound.setVolume(0.07);
            }
        }
        else if (e.getOtherBody() instanceof StaticBody){ //if it touches other objects, destroys bullets
            e.getReportingBody().destroy();
        }
    }
}
