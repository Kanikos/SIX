package com.kanikos.six;

import com.kanikos.six.comp.Window;
import com.kanikos.six.graphics.Spritesheet;
import com.kanikos.six.graphics.Sprite;

public class Main {
	public static void main(String[] args) throws Exception {
		Window window = new Window("Dev", 64, 64, 10);
		Spritesheet sprites = new Spritesheet("test.png", 16);

		while(true)
			window.render(sprites.getSprite(0), 0, 0, Sprite.FLIP_N);
	}
}
