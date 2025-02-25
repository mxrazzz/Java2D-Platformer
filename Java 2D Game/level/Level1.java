package game.level;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.Enemy.Boss1;
import game.Enemy.Boss1Collision;
import game.Enemy.Goblin;
import game.Enemy.GoblinCollision;
import game.Objects.CoinPickup;
import game.Objects.Coins;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/** A class for the first level of the game*/

public class Level1 extends GameLevel {

    public Level1(Game game){
        super(game);

        //the ground shape
        Shape groundShape = new BoxShape(11, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(Color.red.darker());
        ground.setPosition(new Vec2(0, -11.5f));

        // a wall in the right - prevent falling off
        Shape rightWallShape = new BoxShape(0.5f, 20);
        StaticBody rightWall = new StaticBody(this, rightWallShape);
        rightWall.setFillColor(Color.red.darker());
        rightWall.setPosition(new Vec2(10.5f,-6));

        // a wall in the left - prevent falling off
        Shape leftWallShape = new BoxShape(0.5f, 20);
        StaticBody leftWall = new StaticBody(this, leftWallShape);
        leftWall.setFillColor(Color.red.darker());
        leftWall.setPosition(new Vec2(-10.5f,-6));

        // 1st platform
        Shape platform1Shape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setFillColor(Color.CYAN.darker());
        platform1.setPosition(new Vec2(-2, -8));

        //2nd platform
        Shape platform2Shape = new BoxShape(3, 0.5f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setFillColor(Color.CYAN.darker());
        platform2.setPosition(new Vec2(4, -5));

        //3rd platform
        Shape platform3Shape = new BoxShape(2, 0.5f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setFillColor(Color.CYAN.darker());
        platform3.setPosition(new Vec2(-2, -2));

        //4th platform
        Shape platform4Shape = new BoxShape(3, 0.5f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setFillColor(Color.CYAN.darker());
        platform4.setPosition(new Vec2(-6, 1));

        //5th platform
        Shape platform5Shape = new BoxShape(0.5f, 0.5f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setFillColor(Color.CYAN.darker());
        platform5.setPosition(new Vec2(-7, 4.5f));

        //final platform - boss fight
        Shape finalPlatformShape = new BoxShape(4, 0.5f);
        StaticBody finalPlatform = new StaticBody(this, finalPlatformShape);
        finalPlatform.setFillColor(Color.CYAN.darker());
        finalPlatform.setPosition(new Vec2(0, 6.5f));

        //placing character into level
        getMyCharacter().setPosition(new Vec2(-8,-9));

        //collision listener for picking up coins
        CoinPickup pickup = new CoinPickup(getMyCharacter());
        getMyCharacter().addCollisionListener(pickup);

        //placing first boss
        Boss1 boss1 = new Boss1(this);
        boss1.setPosition(new Vec2(0,8.5f));

        //adding collision between boss and character
        Boss1Collision boss1Collision = new Boss1Collision(this, game);
        getMyCharacter().addCollisionListener(boss1Collision);

        //placing enemy goblins
        Goblin goblin1 = new Goblin(this);
        goblin1.setPosition(new Vec2(-2,-6));

        //adding collision listeners between character and goblins
        GoblinCollision goblinCollision = new GoblinCollision(this, game);
        getMyCharacter().addCollisionListener(goblinCollision);

        // placing coin object
        Coins coinObject = new Coins(this);
        coinObject.setPosition(new Vec2(-2,0));

        Coins coinObject2 = new Coins(this);
        coinObject2.setPosition(new Vec2(-7,6.5f));

       // background music
        try {
            gameMusic = new SoundClip("data/ost1.wav");   // Open an audio input stream
            gameMusic.loop();// Set it to continuous playback (looping)
            gameMusic.setVolume(0.05); //setting volume
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
        if (CharacterController.getMuteSound() != 1){
            gameMusic.play(); }
    }
    @Override
    public String getLevelName() {
        return "Level1";
    }

    @Override
    public boolean isComplete() {
        if (CharacterController.getCoinCount() >= 2 )
            return true;
        else
        return false;
    }

}
