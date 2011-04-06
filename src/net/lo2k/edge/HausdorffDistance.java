package net.lo2k.edge;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class HausdorffDistance {
	
	private NearestPointImg pattern;
	private NearestPointImg image;
	
	public HausdorffDistance() {
		
	}
	
	public void setPattern(NearestPointImg pattern) {
		this.pattern = pattern;
	}

	public NearestPointImg getPattern() {
		return pattern;
	}

	public void setImage(NearestPointImg image) {
		this.image = image;
	}

	public NearestPointImg getImage() {
		return image;
	}
	
	public double getDistance() {
		RelativeHausdorffDistance hd = new RelativeHausdorffDistance();

		double val1 = hd.iterate(image,pattern );
		//debugImg1 = hd.getDebugImg();
		
		double val2 = hd.iterate(pattern, image);
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
	



	
	
	
}
