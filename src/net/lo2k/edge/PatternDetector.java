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
		
		double val1 = hd.iterate(pattern, image);
		double val2 = hd.iterate(image, pattern);
		System.out.println("== "+val1+" - "+ val2+" ==");
		System.out.println("==== RES ");
		if (val2>val1) {
			System.out.println(val2);
		} else {
			System.out.println(val1);
		}
	}
	
	private BufferedImage getEdgesFor(BufferedImage img) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		BufferedImage resized = ImgUtil.resize(img, 250,250, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		detector.setSourceImage(resized);
		detector.process();
		return detector.getEdgesImage();
	}
	
	
	
}
