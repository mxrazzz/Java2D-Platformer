package game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**A class that ensures that the mouse is inside the window*/

public class GiveFocus implements MouseListener {
    private MyView view;

    public GiveFocus(MyView v) {
        this.view = v;
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
        this.view.requestFocus();
    }
    public void mouseExited(MouseEvent e) {
    }
}
