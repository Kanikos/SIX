package com.kanikos.six.comp;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import com.kanikos.six.input.Keyboard;

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
	 * input methods
	 */

	 public void addKeyListener(Keyboard keyboard) {
	 	window.addKeyListener(keyboard);
	 }
	
	/*
	 * methods that draws to the screen
	 * in order to show what you have drawn you must call render when finished drawing the scene
	 */

	public void clear() {
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0x000000;
	}

	public void draw(int[] pixels, int x, int y, int width, int height) {
		// find out where to begin drawing the pixels
		int startX = 0, startY = 0;

		if(x < 0) startX = x * -1;
		if(y < 0) startY = y * -1;

		// draw the pixels onto the buffer
		int xIndex, yIndex;
		for(int yOffset = startY; yOffset < height; yOffset++) {
			// check if the yOffset is within bounds of the raster
			yIndex = y + yOffset;
			if(yIndex >= raster.getHeight()) break;

			for(int xOffset = startX; xOffset < width; xOffset++) {
				// check if the xOffset is within bounds of the raster
				xIndex = x + xOffset;
				if(xIndex >= raster.getWidth()) break;

				// copy over a pixel onto the raster
				this.pixels[((yIndex * raster.getWidth()) + xIndex)] = pixels[(yOffset * width) + xOffset];
			}
		}
	}

	public void render() {
		BufferStrategy bs = viewport.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		g.drawImage(raster, 0, 0, viewport.getWidth(), viewport.getHeight(), null);
		bs.show();
		g.dispose();
	}
}
