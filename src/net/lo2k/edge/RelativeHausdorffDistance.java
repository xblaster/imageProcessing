package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class RelativeHausdorffDistance {
	
	//BufferedImage debugImg;
	//private Graphics2D g2d;

	//will not search farther than width/4 size of the image
	private static final short DIVISED_OFFSET = 4;
	private boolean debug;
	private BufferedImage debugImg;
	
	public RelativeHausdorffDistance(boolean debug) {
		this.debug = debug;
	}
	
	
	public RelativeHausdorffDistance() {
		this(false);
	}
	
	public double iterate(NearestPointImg groupA, NearestPointImg groupB) {
		
		
		double distance = 0d;
		int width = groupA.getWidth();
		int height = groupA.getHeight();
		int nbWhitePoint = 0;
		
		if (debug) {
			debugImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}
		
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (groupA.getNearestDistanceFrom(i, j)==0) {
					nbWhitePoint++;
					double localDistance = calculateNeareastPointDistance(groupB,i,j); 
					distance += localDistance;
					
					
					//debug img
					if (debug) {
						int greyLevel = 255-((int) localDistance*20);
						if (greyLevel < 0) {
							greyLevel = 0;
						}
						debugImg.setRGB(i, j, ImgUtil.toRGB(greyLevel, greyLevel, greyLevel));	
					}
					
				} 
			}
		}

		/*System.out.println("DEBUG info");
		System.out.println("nbPoint: "+nbWhitePoint );
		System.out.println("distance: "+distance);*/
		return (distance/(double)nbWhitePoint);
	}


	private double calculateNeareastPointDistance(NearestPointImg groupB2, int x, int y) {
		return groupB2.getNearestDistanceFrom(x, y);
	}

	public BufferedImage getDebugImg() {
		return debugImg;
	}


	
}
