package Events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface MouseListenerEvent {
	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);
	void mouseDragged(MouseEvent e);
	void mouseClicked(MouseEvent e);
	void mouseWheelMoved(MouseWheelEvent e);
}
