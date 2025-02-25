package game.Objects;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.CharacterController;
import game.MainCharacter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/** A class that detects collision when touching a coin object so it destroys it*/

public class CoinPickup implements CollisionListener {

private MainCharacter myCharacter;

public static SoundClip coinPickupSound;

public CoinPickup(MainCharacter s){
    myCharacter =s;
}

    @Override//detects collision with the coin + destroys the object once picked up

    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) {
            System.out.println("collided with coin");
            CharacterController.addCoin();
            e.getOtherBody().destroy();
            if (CharacterController.getMuteSound() != 1){ //checks if game is muted
                coinPickupSound.play();  //makes a noise everytime coin is picked up
                coinPickupSound.setVolume(0.07);
            }
        }
    }

    //creates a static sound effect when picking up a coin
    static {
        try {
            coinPickupSound = new SoundClip("data/coinSound.wav");
            System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }
}
