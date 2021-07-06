package com.kanikos.six.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.kanikos.six.comp.Window;

public class Spritesheet {
	// properties
	
	private int[][] sprites;
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
		this.sprites	= new int[width * height][dimensions * dimensions];

		// extract sprites from the spritesheet
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++)
				spritesheet.getRGB(x * dimensions, y * dimensions, dimensions, dimensions, sprites[(y * width) + x], 0, dimensions);
	}

	// draws the specified sprite onto the raster
	public void draw(int ID, Window window, int x, int y) {
		window.draw(sprites[ID], x, y, dimensions, dimensions);
	}

	public int[] getSprite(int ID) {
		return sprites[ID];
	}
}
