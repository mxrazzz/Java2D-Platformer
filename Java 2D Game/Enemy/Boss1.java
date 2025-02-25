package game.Enemy;
import city.cs.engine.*;
import city.cs.engine.BodyImage;

/** A class for the body of the first boss */

public class Boss1 extends StaticBody  {
    private static  final Shape firstBoss =
            new PolygonShape(
                    -0.25f,2.02f, -1.62f,0.82f, -1.67f,-1.69f, 1.61f,-1.74f, 1.23f,1.61f);

    private static final BodyImage image1 = new BodyImage("data/boss-1.png", 5f); //image of boss

    public Boss1 (World world){ //placing boss into world
        super(world, firstBoss);
        addImage(image1);
    }
}
