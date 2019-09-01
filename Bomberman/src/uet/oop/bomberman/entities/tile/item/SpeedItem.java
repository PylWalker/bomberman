package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
	private boolean status = true;

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if(e instanceof Bomber) {
			if(status) {
				Sound powerUpAudio = new Sound(Sound.POWER_UP);
				powerUpAudio.play();
				double tmp = Game.getBomberSpeed();
				tmp+=0.2;
				tmp = (double)(Math.round(tmp*10))/10;
				Game.setBomberspeed(tmp);//System.out.println(Game.getBomberSpeed());
				status = false;
			}
			remove();
			return true;
		}
		return false;
	}
}
