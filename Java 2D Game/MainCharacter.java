package game;
import city.cs.engine.*;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;

/**A class for the main characters body and counting his coins*/

public class MainCharacter extends Walker {


    private static final Shape characterShape =
            new PolygonShape(
                    -0.47f,0.85f, -0.65f,-1.1f, 0.45f,-1.08f, 0.35f,0.82f

            );

    private static final BodyImage image = new BodyImage("data/kirito.png", 3f); //adding image

    public MainCharacter(World world) {
        super(world, characterShape);
        addImage(image);

    }


}
