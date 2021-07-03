package com.kanikos.six.graphics;

import java.awt.image.BufferedImage;

public class Sprite {
	// transformation variables

	public static byte
		FLIP_N = 0b000,
		FLIP_X = 0b001,
		FLIP_Y = 0b010,
		FLIP_D = 0b100;

	// properties

	private int[] sprite;
	private int dimensions;

	// constructors
	
	public Sprite(BufferedImage spritesheet, int x, int y, int dimensions) {
		this.dimensions = dimensions;

		sprite = new int[dimensions * dimensions];
		spritesheet.getRGB(x, y, dimensions, dimensions, sprite, 0, dimensions);
	}

	// draws the sprite onto a buffer
	
	public void render(int[] raster, int rWidth, int rHeight, int x, int y, byte transformation) {
		// find out where to begin drawing the sprite
		int startX = 0, startY = 0;

		if(x < 0) startX = x * -1;
		if(y < 0) startY = y * -1;

		// draw the sprite onto the screen
		int yIndex, xIndex;
		for(int yOffset = startY; yOffset < dimensions; yOffset++) {
			// check if the yOffset is within bounds of the raster 
			yIndex = y + yOffset;	
			if(yIndex >= rHeight) break;

			for(int xOffset = 0; xOffset < dimensions; xOffset++) {
				// check if the xOffset is within bounds of the raster
				xIndex = x + xOffset;
				if(xIndex >= rWidth) break;
				
				// copy over a pixel onto the raster
				raster[((yIndex * rWidth) + xIndex)] = getPixel(x, y, transformation);
			}
		}
	}

	public int getPixel(int x, int y, byte transformation) {
		// TODO: implement transformation
		return sprite[(y * dimensions) + x];
	}
}
