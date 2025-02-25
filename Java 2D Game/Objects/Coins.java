package game.Objects;
import city.cs.engine.*;
import city.cs.engine.Shape;

/** A class that creates the coin body*/

public class Coins extends StaticBody { //creating coin class to add coin object to world

    private static final Shape coinShape= new CircleShape(1);

    private static final BodyImage image = new BodyImage("data/coin.gif", 3f);  //gif image of coin

    public Coins(World world){
        super(world, coinShape);
        addImage(image);
}
}
