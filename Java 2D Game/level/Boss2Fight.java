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
import game.Objects.FallingBall;
import game.Objects.FireBallCollision;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Date;

/**A class for the second boss fight*/

public class Boss2Fight extends GameLevel {

    public Boss2Fight(Game game) {
        super(game);

        //the ground shape
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(Color.RED.darker());
        ground.setPosition(new Vec2(0, -11.5f));

        //ground where the boss stands
        Shape bossGround = new BoxShape(2.5f, 0.5f);
        Body groundBoss = new StaticBody(this, bossGround);
        groundBoss.setFillColor(Color.BLUE.darker());
        groundBoss.setPosition(new Vec2(-0, -3));

        //placing the boss
        Boss2 boss2 = new Boss2(this);
        boss2.setPosition(new Vec2(-0.5f,0));

        //placing character into level
        getMyCharacter().setPosition(new Vec2(0,-9));

        // adding platform
        Shape platform1Shape = new BoxShape(2.5f, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setFillColor(Color.BLUE.darker());
        platform1.setPosition(new Vec2(8, -7));

        //placing coins into the world
        Coins coinObject = new Coins(this);
        coinObject.setPosition(new Vec2(8,-5.5f));

        // adding platform
        Shape platform2Shape = new BoxShape(2.5f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setFillColor(Color.BLUE.darker());
        platform2.setPosition(new Vec2(-8, -7));

        //placing coins into the world
        Coins coinObject2 = new Coins(this);
        coinObject2.setPosition(new Vec2(-8,-5.5f));

        // adding platform
        Shape platform3Shape = new BoxShape(3, 0.5f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setFillColor(Color.BLUE.darker());
        platform3.setPosition(new Vec2(8, 0));

        //placing coins into the world
        Coins coinObject3 = new Coins(this);
        coinObject3.setPosition(new Vec2(8,1.5f));

        //adding platform
        Shape platform4Shape = new BoxShape(3, 0.5f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setFillColor(Color.BLUE.darker());
        platform4.setPosition(new Vec2(-8, 0));

        //placing coins into the world
        Coins coinObject4 = new Coins(this);
        coinObject4.setPosition(new Vec2(-8,1.5f));

        //adding platform
        Shape platform5Shape = new BoxShape(2, 0.5f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setFillColor(Color.BLUE.darker());
        platform5.setPosition(new Vec2(-12, -4));

        //adding platform
        Shape platform6Shape = new BoxShape(2, 0.5f);
        StaticBody platform6 = new StaticBody(this, platform6Shape);
        platform6.setFillColor(Color.BLUE.darker());
        platform6.setPosition(new Vec2(12, -4));

        //adding collision between boss and character
        Boss2Collision boss2Collision = new Boss2Collision(this, game);
        getMyCharacter().addCollisionListener(boss2Collision);

        //collision listener for picking up coins
        CoinPickup pickup = new CoinPickup(getMyCharacter());
        getMyCharacter().addCollisionListener(pickup);

        //placing goblins into the map
        Goblin goblin1 = new Goblin(this);
        goblin1.setPosition(new Vec2(-8, -8));

        Goblin goblin2 = new Goblin(this);
        goblin2.setPosition(new Vec2(10,-8));

        //adding collision listeners between character and goblins
        GoblinCollision goblinCollision = new GoblinCollision(this, game);
        getMyCharacter().addCollisionListener(goblinCollision);

        //spawns 3 random fireballs
        fireballSpawn();

        //spawns fireball at x location
        fireballSpawnCustom(-2);
        fireballSpawnCustom(-6);
        fireballSpawnCustom(-10);

        /**
         *spawns the fireballs every 5 seconds
         * */
        addStepListener(new StepListener(){
            double previousTime = new Date().getTime() / 1000.;
            public void preStep(StepEvent e) {
                double currentTime = new Date().getTime() / 1000.;
                if (currentTime - previousTime > 3.f) {
                    fireballSpawn();
                    fireballSpawnCustom(-2);
                    fireballSpawnCustom(-6);
                    fireballSpawnCustom(-10);
                    previousTime = currentTime;
                }
            }
            public void postStep(StepEvent e) { }
        });
        // background music
        try {
            gameMusic = new SoundClip("data/bossfight.wav");   // Open an audio input stream
            gameMusic.loop();// Set it to continuous playback (looping)
            gameMusic.setVolume(0.05); //setting volume
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        if (CharacterController.getMuteSound() != 1){
            gameMusic.play(); }
    }
    //creates a random spawn for the fireballs
    public void fireballSpawn(){
        for (int i=0; i<3; i++){
            FallingBall fireball2 = new FallingBall(this);
            FireBallCollision fireballCollision2 = new FireBallCollision(fireball2);
            fireball2.addCollisionListener(fireballCollision2);
        }
    }
    //another spawn but can customise location
    public void fireballSpawnCustom(int x){
        FallingBall fireball3 = new FallingBall(this);
        FireBallCollision fireballCollision3 = new FireBallCollision(fireball3);
        fireball3.addCollisionListener(fireballCollision3);
        fireball3.setPosition(new Vec2(x, 10));
    }

    @Override
    public String getLevelName() {
        return "Bossfight:2";
    }
    @Override
    public boolean isComplete() {
        if (CharacterController.getCoinCount() >= 13)
            return true;
        else
            return false;
    }
}