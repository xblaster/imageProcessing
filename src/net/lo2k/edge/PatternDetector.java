package net.lo2k.edge;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class PatternDetector {
	
	private BufferedImage pattern;
	private BufferedImage image;
	
	public PatternDetector() {
		
	}
	
	public void setPattern(BufferedImage pattern) {
		this.pattern = getEdgesFor(pattern);
	}

	public BufferedImage getPattern() {
		return pattern;
	}

	public void setImage(BufferedImage image) {
		this.image = getEdgesFor(image);
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void detect() {
		HausdorffDistance hd = new HausdorffDistance();
		
		System.out.println(hd.iterate(pattern, image));
	}
	
	private BufferedImage getEdgesFor(BufferedImage img) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		BufferedImage resized = ImgUtil.resize(img, 250,250, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		detector.setSourceImage(resized);

		detector.process();
		return detector.getEdgesImage();
	}
	
	
	
}
