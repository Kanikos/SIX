package com.kanikos.six.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	public void keyPressed(KeyEvent e) {
		System.out.printf("Pressed:\t%03d\n", e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		System.out.printf("Released:\t%03d\n", e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {}
}
