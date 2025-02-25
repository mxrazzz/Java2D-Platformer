package game.Enemy;
import city.cs.engine.*;
import city.cs.engine.BodyImage;

/** Class for the body of the second boss*/

public class Boss2 extends StaticBody  {

    private static  final Shape secondBoss =
            new PolygonShape(
                    -0.72f,2.42f, -3.38f,1.33f, -3.06f,0.07f, 0.24f,-2.39f, 3.27f,-2.34f, 2.18f,2.28f);

    private static final BodyImage image2 = new BodyImage("data/boss2.png", 5f); //image of boss

    public Boss2 (World world){ //placing boss into world
        super(world, secondBoss);
        addImage(image2);
    }
}
