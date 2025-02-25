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
import game.Objects.FallingBall;
import game.Objects.FireBallCollision;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Date;

/** A class for the first boss fight */

public class Boss1Fight extends GameLevel {

    public Boss1Fight(Game game) {
        super(game);

        //placing character into level
        getMyCharacter().setPosition(new Vec2(0,-9));

        //placing the boss
        Boss1 boss1 = new Boss1(this);
        boss1.setPosition(new Vec2(-14,-4.5f));

        //adding collision between boss and character
        Boss1Collision boss1Collision = new Boss1Collision(this, game);
        getMyCharacter().addCollisionListener(boss1Collision);

        //ground shape for the boss fight
        Shape groundShape = new BoxShape(20, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(Color.RED);
        ground.setPosition(new Vec2(0, -11.5f));

        // a wall in the right - prevent falling off
        Shape rightWallShape = new BoxShape(0.5f, 8);
        StaticBody rightWall = new StaticBody(this, rightWallShape);
        rightWall.setFillColor(Color.RED);
        rightWall.setPosition(new Vec2(19.5f,-4));

        // a wall in the left - prevent falling off
        Shape leftWallShape = new BoxShape(0.5f, 8);
        StaticBody leftWall = new StaticBody(this, leftWallShape);
        leftWall.setFillColor(Color.RED);
        leftWall.setPosition(new Vec2(-19.5f,-4));

        //ground where the boss stands
        Shape bossGround = new BoxShape(2, 0.5f);
        Body groundBoss = new StaticBody(this, bossGround);
        groundBoss.setFillColor(Color.RED);
        groundBoss.setPosition(new Vec2(-14, -7));

        //collision listener for picking up coins
        CoinPickup pickup = new CoinPickup(getMyCharacter());
        getMyCharacter().addCollisionListener(pickup);

        //placing goblins into the map
        Goblin goblin1 = new Goblin(this);
        goblin1.setPosition(new Vec2(-4,-8));

        Goblin goblin2 = new Goblin(this);
        goblin2.setPosition(new Vec2(8.3f,-8));

        //adding collision listeners between character and goblins
        GoblinCollision goblinCollision = new GoblinCollision(this, game);
        getMyCharacter().addCollisionListener(goblinCollision);

        //placing coins into the world
        Coins coinObject = new Coins(this);
        coinObject.setPosition(new Vec2(-6,-9.5f));

        //placing coins into the world
        Coins coinObject2 = new Coins(this);
        coinObject2.setPosition(new Vec2(6,-9.5f));

        //placing coins into the world
        Coins coinObject3 = new Coins(this);
        coinObject3.setPosition(new Vec2(12,-9.5f));

        //spawns 3 random fireballs
        fireballSpawn();
        fireballSpawn1(-2);
        fireballSpawn1(-5);

        /**
         *spawns the fireballs every 5 seconds
         * */
        addStepListener(new StepListener(){
            double previousTime = new Date().getTime() / 1000.;
            public void preStep(StepEvent e) {
                double currentTime = new Date().getTime() / 1000.;
                if (currentTime - previousTime > 5.f) {
                    fireballSpawn();
                    fireballSpawn1(-2);
                    fireballSpawn1(-5);
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
    //another spawn but in a different location
    public void fireballSpawn1(int x){
        FallingBall fireball3 = new FallingBall(this);
        FireBallCollision fireballCollision3 = new FireBallCollision(fireball3);
        fireball3.addCollisionListener(fireballCollision3);
        fireball3.setPosition(new Vec2(x, 10));
}

    @Override
    public String getLevelName() {
        return "Bossfight:1";
    }

    @Override
    public boolean isComplete() {
        if (CharacterController.getCoinCount() >= 5)
            return true;
        else
            return false;
    }

}
