package net.lo2k.edge;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class HausdorffDistance {
	
	private BufferedImage pattern;
	private BufferedImage image;
	
	private BufferedImage debugImg1;
	private BufferedImage debugImg2;
	
	public HausdorffDistance() {
		
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
		RelativeHausdorffDistance hd = new RelativeHausdorffDistance();
		
		double val1 = hd.iterate(image,pattern );
		//debugImg1 = hd.getDebugImg();
		
		double val2 = hd.iterate(pattern, image);
		//debugImg2 = hd.getDebugImg();
		
		System.out.println("== "+val1+" - "+ val2+" ==");
		System.out.println("==== RES ");
		if (val2>val1) {
			System.out.println(val2);
		} else {
			System.out.println(val1);
		}
	}
	
	public BufferedImage getDebugImg1() {
		return debugImg1;
	}

	public BufferedImage getDebugImg2() {
		return debugImg2;
	}

	public BufferedImage getEdgesFor(BufferedImage img) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		BufferedImage resized = ImgUtil.resize(img, 100,100, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		detector.setSourceImage(resized);
		detector.process();
		return detector.getEdgesImage();
	}
	
	
	
}
