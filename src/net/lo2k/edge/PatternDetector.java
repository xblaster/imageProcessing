package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class PatternDetector {
	
	private BufferedImage pattern;
	private BufferedImage image;
	
	public PatternDetector() {
		
	}
	
	public void setPattern(BufferedImage pattern) {
		this.pattern = pattern;
	}

	public BufferedImage getPattern() {
		return pattern;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void detect() {
		
	}
	
}
