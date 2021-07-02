package com.kanikos.six;

import com.kanikos.six.comp.Window;

public class Main {
	public static void main(String[] args) {
		Window window = new Window("Dev", 64, 64, 10);

		while(true)
			window.render();
	}
}
