package game.Enemy;

import city.cs.engine.*;

/** A class for the body of the enemy goblin*/

public class Goblin extends DynamicBody {
    private static  final Shape goblinShape = new PolygonShape(0.41f,1.29f, -1.16f,1.22f, -1.17f,-1.11f, 1.43f,-1.24f, 1.42f,1.31f);

    private static final BodyImage image3 = new BodyImage("data/goblinRight.png", 3f); //image of goblin

    public Goblin (World world){ //placing goblin into world
        super(world, goblinShape);
        addImage(image3);
    }
}
