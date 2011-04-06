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
	
	public double getDistance() {
		RelativeHausdorffDistance hd = new RelativeHausdorffDistance();
		
		NearestPointImg nearImage = new NearestPointImg(image);
		NearestPointImg nearPattern = new NearestPointImg(pattern);
		
		double val1 = hd.iterate(nearImage,nearPattern );
		//debugImg1 = hd.getDebugImg();
		
		double val2 = hd.iterate(nearPattern, nearImage);
		//debugImg2 = hd.getDebugImg();
		
		//System.out.println("== "+val1+" - "+ val2+" ==");
		//System.out.println("==== RES ");
		if (val2>val1) {
			//System.out.println(val2);
			return val2;
		} else {
			//System.out.println(val1);
			return val1;
		}
	}
	
	public BufferedImage getDebugImg1() {
		return debugImg1;
	}

	public BufferedImage getDebugImg2() {
		return debugImg2;
	}


	
	
	
}
