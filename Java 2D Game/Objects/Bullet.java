package game.Objects;
import city.cs.engine.*;
import game.CharacterController;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** A class for the bullet object */

public class Bullet implements ActionListener {
    private DynamicBody bullet;

    public Bullet(World world, Vec2 pos, int time){

        bullet = new DynamicBody(world, new PolygonShape(0.106f,0.114f, -0.395f,0.021f, -0.005f,-0.098f, 0.394f,-0.054f, 0.365f,0.118f));
        bullet.addImage(new BodyImage("data/bullet.png"));
        bullet.setPosition(pos);
        bullet.setLinearVelocity(new Vec2(CharacterController.getD()*10,1)); //shoots straight

        //creates collision listener for the bullet object
        BulletCollision bulletCollision = new BulletCollision(this);
        bullet.addCollisionListener(bulletCollision);

        //creating a timer to destroy bullets after it has been shot
        Timer t = new Timer(time, this);
        t.start();
        t.setRepeats(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        bullet.destroy();
    }
}
