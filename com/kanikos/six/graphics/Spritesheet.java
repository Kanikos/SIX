package com.kanikos.six.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {
	// properties
	
	private Sprite[] sprites;
	private int dimensions;
	private int width, height;

	// constructors
	public Spritesheet(String path, int dimensions) throws IOException {
		// attempt to open the image contains the sprites
		File image = new File(path);
		if(!image.exists())
			throw new IOException("File Does Not Exists");
		
		BufferedImage spritesheet = ImageIO.read(image);

		// initialize spritesheet properties
		this.dimensions	= dimensions;
		this.width	= spritesheet.getWidth() / dimensions;
		this.height	= spritesheet.getHeight() / dimensions;
		this.sprites	= new Sprite[width * height];

		// extract sprites from the spritesheet
		for(int y = 0; y < height; y++) 
			for(int x = 0; x < width; x++)
				sprites[(y * width) + x] = new Sprite(spritesheet, x * dimensions, y * dimensions, dimensions);
	}

	// draws the specified sprite onto the raster

	public void render(int[] raster, int rWidth, int rHeight, int ID, int x, int y, byte transformation) {
		sprites[ID].render(raster, rWidth, rHeight, x, y, transformation);
	}

	public Sprite getSprite(int ID) {
		return sprites[ID % sprites.length];
	}
}
