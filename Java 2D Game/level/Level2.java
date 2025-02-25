package game.level;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.Enemy.Boss2;
import game.Enemy.Boss2Collision;
import game.Enemy.Goblin;
import game.Enemy.GoblinCollision;
import game.Objects.CoinPickup;
import game.Objects.Coins;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/** A class for the second level of the game*/

public class Level2 extends GameLevel {

    public Level2(Game game) {
        super(game);
        //the ground shape
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(Color.BLUE.darker());
        ground.setPosition(new Vec2(0, -11.5f));

        // a wall in the right - prevent falling off
        Shape rightWallShape = new BoxShape(0.5f, 32);
        StaticBody rightWall = new StaticBody(this, rightWallShape);
        rightWall.setFillColor(Color.BLUE.darker());
        rightWall.setPosition(new Vec2(19.5f, -6));

        // a wall in the left - prevent falling off
        Shape leftWallShape = new BoxShape(0.5f, 32);
        StaticBody leftWall = new StaticBody(this, leftWallShape);
        leftWall.setFillColor(Color.BLUE.darker());
        leftWall.setPosition(new Vec2(-19.5f, -6));

        Shape middleWallShape = new BoxShape(0.5f, 6);
        StaticBody middleWall = new StaticBody(this, middleWallShape);
        middleWall.setFillColor(Color.BLUE.darker());
        middleWall.setPosition(new Vec2(0, -6));

        // 1st platform
        Shape platform1Shape = new BoxShape(2.5f, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setFillColor(Color.BLUE.darker());
        platform1.setPosition(new Vec2(-6, -8));

        //2nd platform -coin
        Shape platform2Shape = new BoxShape(3, 0.5f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setFillColor(Color.YELLOW);
        platform2.setPosition(new Vec2(-15, -5));

        //3rd platform
        Shape platform3Shape = new BoxShape(1, 0.5f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setFillColor(Color.BLUE.darker());
        platform3.setPosition(new Vec2(-19, -2));

        //4th platform
        Shape platform4Shape = new BoxShape(3, 0.5f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setFillColor(Color.BLUE.darker());
        platform4.setPosition(new Vec2(-10, 2));

        //5th platform -coin
        Shape platform5Shape = new BoxShape(0.5f, 0.5f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setFillColor(Color.YELLOW);
        platform5.setPosition(new Vec2(-3, 4.5f));

        //6th platform
        Shape platform6Shape = new BoxShape(1, 0.5f);
        StaticBody platform6 = new StaticBody(this, platform6Shape);
        platform6.setFillColor(Color.BLUE.darker());
        platform6.setPosition(new Vec2(3, -1));

        //7th platform -coin
        Shape platform7Shape = new BoxShape(1, 0.5f);
        StaticBody platform7 = new StaticBody(this, platform7Shape);
        platform7.setFillColor(Color.YELLOW);
        platform7.setPosition(new Vec2(7, 3));

        //8th platform
        Shape platform8Shape = new BoxShape(1, 0.5f);
        StaticBody platform8 = new StaticBody(this, platform8Shape);
        platform8.setFillColor(Color.blue.darker());
        platform8.setPosition(new Vec2(8, -5));

        //9th platform
        Shape platform9Shape = new BoxShape(3, 0.5f);
        StaticBody platform9 = new StaticBody(this, platform9Shape);
        platform9.setFillColor(Color.BLUE.darker());
        platform9.setPosition(new Vec2(16, -8));

        //final platform - boss fight
        Shape finalPlatformShape = new BoxShape(4, 0.5f);
        StaticBody finalPlatform = new StaticBody(this, finalPlatformShape);
        finalPlatform.setFillColor(Color.BLUE.darker());
        finalPlatform.setPosition(new Vec2(15, 6.5f));

        //placing the character
        getMyCharacter().setPosition(new Vec2(-14,-8));

        //placing the boss
        Boss2 boss2 = new Boss2(this);
        boss2.setPosition(new Vec2(15.6f,9.5f));

        //adding collision between boss and character
        Boss2Collision boss2Collision = new Boss2Collision(this, game);
        getMyCharacter().addCollisionListener(boss2Collision);

        //collision listener for picking up coins
        CoinPickup pickup = new CoinPickup(getMyCharacter());
        getMyCharacter().addCollisionListener(pickup);

        //placing enemy goblins
        Goblin goblin1 = new Goblin(this);
        goblin1.setPosition(new Vec2(-6, -8));

        Goblin goblin2 = new Goblin(this);
        goblin2.setPosition(new Vec2(-10,2));

        Goblin goblin3 = new Goblin(this);
        goblin3.setPosition(new Vec2(16,-8));

        //adding collision listeners between character and goblins
        GoblinCollision goblinCollision = new GoblinCollision(this, game);
        getMyCharacter().addCollisionListener(goblinCollision);

        // placing coin object
        Coins coinObject = new Coins(this);
        coinObject.setPosition(new Vec2(-15,-3));

        Coins coinObject2 = new Coins(this);
        coinObject2.setPosition(new Vec2(-3,6));

        Coins coinObject3 = new Coins(this);
        coinObject3.setPosition(new Vec2(7,4.5f));

        Coins coinObject4 = new Coins(this);
        coinObject4.setPosition(new Vec2(17.8f,-9.7f));

        //background music
        try {
            gameMusic = new SoundClip("data/level2ost.wav");   // Open an audio input stream
            gameMusic.loop();// Set it to continuous playback (looping)
            gameMusic.setVolume(0.05); //setting volume
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        if (CharacterController.getMuteSound() != 1){
            gameMusic.play(); }
    }
    @Override
    public String getLevelName() {
        return "Level2";
    }
    @Override
    public boolean isComplete() {
        if (CharacterController.getCoinCount() >= 9)
            return true;
        else
            return false;
    }
}
