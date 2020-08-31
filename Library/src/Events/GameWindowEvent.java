package Events;

import Misc.Graphics;

public interface GameWindowEvent {
	void tick();
	void tiedTick();
	void render(Graphics g);
}
