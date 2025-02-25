package game;
import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import game.Objects.Bullet;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/** A class that allows the character to move using the keyboard */

public class CharacterController implements KeyListener {
    private static int coinCount = 0;
    private static int killCount = 0;
    private static int liveCount = 10;
    private static final float WALKING_SPEED = 5; //sets how fast the character moves
    private MainCharacter myCharacter;
    public static SoundClip jumpingSound;
    public static SoundClip bulletSound;
    private static int d;
    private static int characterState;
    private static int muteSound;
    private static int deadState;
    private Game game;
    public static GameLevel getLevel;

    private static final BodyImage invertedCharacter =
            new BodyImage("data/kiritoFlipped.png", 3f); //flipped image

    private static final BodyImage image =
            new BodyImage("data/kirito.png", 3f); //original image

    public CharacterController(MainCharacter s) {
        myCharacter = s;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            if (getCharacterState() != 1){// Pressing A to go left
                myCharacter.startWalking(-WALKING_SPEED);
                myCharacter.removeAllImages();
                myCharacter.addImage(invertedCharacter); //flipped image is added when changing direction
                setD(-1); }
        }
        else if (code == KeyEvent.VK_D) {
            if (getCharacterState() != 1){//Pressing D to go right
                myCharacter.startWalking(WALKING_SPEED);
                myCharacter.removeAllImages();
                myCharacter.addImage(image);
                setD(1); }
        }
        else if (code == KeyEvent.VK_W){
            if (getCharacterState() != 1){//Pressing W to jump up
                myCharacter.jump(10);//makes a sound effect whenever the character jumps
                if (getMuteSound() != 1){
                    jumpingSound.play();
                    jumpingSound.setVolume(0.05); }
                }
        }
        else if (code == KeyEvent.VK_SPACE ){//pressing space to shoot
            if (getCharacterState() != 1){  //can't shoot if game is paused
                Bullet b = new Bullet(Game.getLevel().getMyCharacter().getWorld(), Game.getLevel().getMyCharacter().getPosition().add(new Vec2(getD(),0)), 500);
                if (getMuteSound() != 1){
                    bulletSound.play();
                    bulletSound.setVolume(0.05);}
            }
        }
        else if (code == KeyEvent.VK_P){ //pressing P to save game
            try {
                GameSaverLoader.save(Game.getLevel(), "data/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (code == KeyEvent.VK_L){  //pressing L to load game
            try {
               GameSaverLoader.load(Game.getLevel(),"data/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    //this makes sure the character stops moving once you stop pressing the keys
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            myCharacter.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            myCharacter.stopWalking();
        }
    }

    //adding a static sound effect so whenever you jump it makes a sound
    static {
        try {
            jumpingSound = new SoundClip("data/jump.wav");
            System.out.println("Loading jump sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //sound effect for whenever you shoot a bullet
    static {
        try {
            bulletSound = new SoundClip("data/laser.wav");
            System.out.println("Loading bullet sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //this is so that you can still access the controller when switching between levels
    public void updateCharacter(MainCharacter myCharacter) {
        this.myCharacter = myCharacter;
    }

    // getter and setter for the coins collected
    public static void addCoin(){
        coinCount++;
    }
    public static int getCoinCount() {
        return coinCount;
    }

    //puts the coins+kills into the save file
    public static void setKillCount(int kc){
        killCount = kc;
    }
    public static void setCoinCount(int cc){
        coinCount = cc;
    }

    //getter and setter for the kills
    public static void addKill(){  //getter and setter for the kills collected
        killCount++;
    }
    public static int getKillCount() {
        return killCount;
    }

    //getter and setter for your lives
    public static void loseLives(){
        liveCount = liveCount -1;
    }
    public static int getLiveCount(){
        return liveCount;
    }

    //getter and setter to stop the character from moving
    public static int getCharacterState() {
        return characterState;
    }
    public static void setCharacterState(int characterState) {
        CharacterController.characterState = characterState;
    }

    //getter and setter to stop all sound if mute button is pressed
    public static int getMuteSound() {
        return muteSound;
    }
    public static void setMuteSound(int muteSound) {
        CharacterController.muteSound = muteSound;
    }

    //getter and setter to change the direction of bullets depending on position of player
    public static int getD() {
        return d;
    }
    public static void setD(int d) {
        CharacterController.d = d;
    }

    //getter and setter to check whether player is dead, so the death screen can be shown
    public static int getDeadState() {
        return deadState;
    }
    public static void setDeadState(int deadState) {
        CharacterController.deadState = deadState;
    }
}
