package Events;

import Rendering.Graphics;

public interface GameWindowEvent {
	void tick();
	void render(Graphics g);
}
