package game.Objects;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/** A class to create the body of the falling meteor*/

public class FallingBall extends DynamicBody {

    private static  final Shape fireBall =
            new PolygonShape(
                    -0.45f,1.84f, -0.48f,-1.71f, 0.03f,-1.86f, 0.48f,-1.62f, 0.34f,1.86f);

    private static final BodyImage image3 = new BodyImage("data/fireball.png", 4f); //image of fireball

    public FallingBall (World world){
        super(world, fireBall);
        addImage(image3);

        float x = (float) Math.random()*20; //randomising the position when the fireballs spawn
        setPosition(new Vec2(x,10));
    }
}
