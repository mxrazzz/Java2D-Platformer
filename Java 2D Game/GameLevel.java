package game;
import city.cs.engine.SoundClip;
import city.cs.engine.World;

/** A class that allows objects to be used on all levels*/

public abstract class GameLevel extends World {

    private MainCharacter myCharacter;
    private Game game;
    public static SoundClip gameMusic; //allows the background music to be used everywhere

    public GameLevel(Game game){
        myCharacter = new MainCharacter(this);
        this.game = game;
    }
    public MainCharacter getMyCharacter() {
        return myCharacter;
    }

    public Game getGame() {
        return game;
    }


    public abstract String getLevelName();

    public abstract boolean isComplete();
}
