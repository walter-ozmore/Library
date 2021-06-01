package Events;

import Rendering.Graphics;

public interface GameWindowEvent {
	void tick();
	void tiedTick();
	void render(Graphics g);
}
