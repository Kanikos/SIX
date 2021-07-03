package com.kanikos.six.comp;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import com.kanikos.six.graphics.Sprite;

public class Window {
	// window properties 
	private JFrame window;
	private Canvas viewport;

	// the single layer in which sprites and such are drawn
	private BufferedImage raster;
	private int[] pixels;

	public Window(String title, int width, int height, int scale) {
		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the viewport
		Dimension d = new Dimension(width * scale, height * scale);

		viewport = new Canvas();
		viewport.setPreferredSize(d);
		window.add(viewport);

		raster = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) raster.getRaster().getDataBuffer()).getData();

		// make the window visible and unresizable 
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		viewport.createBufferStrategy(3);
	}
	
	/*
	 * fill the screen with the entered rgb value
	 */
	public void clear() {
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0x000000;
	}

	public void render(Sprite sprite, int x, int y, byte transformation) {
		clear();

		BufferStrategy bs = viewport.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		sprite.render(pixels, raster.getWidth(), raster.getHeight(), x, y, transformation);

		g.drawImage(raster, 0, 0, viewport.getWidth(), viewport.getHeight(), null);
		bs.show();
		g.dispose();
	}
}
