package game.level;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.CharacterController;
import game.Game;
import game.GameLevel;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/** A class that indicates that the game has finished*/

public class LevelEnd extends GameLevel {

    public LevelEnd(Game game) {
        super(game);

        //ground shape
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(Color.WHITE);
        ground.setPosition(new Vec2(0, -11.5f));

        //placing character into level
        getMyCharacter().setPosition(new Vec2(0,20));

        //background music
        try {
            gameMusic = new SoundClip("data/creditsong.wav");   // Open an audio input stream
            gameMusic.loop();// Set it to continuous playback (looping)
            gameMusic.setVolume(0.08); //setting volume
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        if (CharacterController.getMuteSound() != 1){
            gameMusic.play();
        }
    }
    @Override
    public String getLevelName() {
        return "LevelEnd";
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
