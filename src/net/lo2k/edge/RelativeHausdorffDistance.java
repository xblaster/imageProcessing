package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class RelativeHausdorffDistance {
	
	//BufferedImage debugImg;
	//private Graphics2D g2d;

	//will not search farther than width/4 size of the image
	private static final short DIVISED_OFFSET = 4;
	
	public RelativeHausdorffDistance() {
	}
	
	public double iterate(BufferedImage groupA, BufferedImage groupB) {
		
		//debugImg = new BufferedImage(groupA.getWidth(), groupB.getHeight(), BufferedImage.TYPE_INT_RGB);
		//g2d = debugImg.createGraphics();
		int nbWhitePoint = 0;
		double distance = 0d;
		
		int width = groupA.getWidth();
		int height = groupA.getHeight();
		
		
		for (int i = 0; i < width; i++) {
			double offset = height / DIVISED_OFFSET;
			for (int j = 0; j < height; j++) {
				if (ImgUtil.isWhitePixel(groupA, i, j)) {
					nbWhitePoint++;
					double localDistance = calculateNeareastPointDistance(groupB,i,j,(int)offset); 
					distance += localDistance;
					offset = localDistance;
					
					//debug img
					int greyLevel = 255-((int) localDistance*20);
					if (greyLevel < 0) {
						greyLevel = 0;
					}
					//debugImg.setRGB(i, j, ImgUtil.toRGB(greyLevel, greyLevel, greyLevel));
				} else {
					offset += 1d;
					//limit the offset
					if (offset > height/DIVISED_OFFSET) 
						offset = height/DIVISED_OFFSET;
				}
			}
		}
		/*System.out.println("DEBUG info");
		System.out.println("nbPoint: "+nbWhitePoint );
		System.out.println("distance: "+distance);*/
		return (distance/(double)nbWhitePoint);
	}


	private double calculateNeareastPointDistance(BufferedImage groupB2, int x, int y, int offset) {
		/*System.out.println("===========================");
		System.out.println("Nearest for " + x + "," + y);
		System.out.println("offset "+offset);*/
		if (ImgUtil.isWhitePixel(groupB2, x, y)) {
			//System.out.println("Exact match ! 0l");
			return 0l;
		}
		double bestDistance = Long.MAX_VALUE;
		
		int bestX = -1;
		int bestY = -1;
		
		for (int i = x-offset/2; i < x+offset/2; i++) {
			for (int j = y-offset/2; j < y+offset/2; j++) {
				if (ImgUtil.isWhitePixel(groupB2, i, j)) {
					//small optimisation
					if (Math.abs(i - x) < bestDistance) {
						if (Math.abs(j - y) < bestDistance) {
							//double euclidianDistance = Math.sqrt((i - x)*(i - x)
								//	+ (j - y)*(j - y));
							double euclidianDistance = (i - x)*(i - x)+ (j - y)*(j - y);
							if (euclidianDistance < bestDistance) {
								//System.out.println("Best at "+i+", "+j+" -> "+euclidianDistance);
								bestX = i;
								bestY = j;
								bestDistance = euclidianDistance;
							}
						}
					}
				}
			}
		}
		//System.out.println(bestDistance);
		if (bestDistance == Long.MAX_VALUE) {
			bestDistance = offset+1d;
		}
		
		/*if (bestX!=-1) {
			g2d.setColor(new Color(0.2f,1,1,0.2f));
			BasicStroke bs = new BasicStroke(1);
			g2d.setStroke(bs);
			g2d.drawLine(x,y, bestX, bestY);
		}*/
		//System.out.println("best "+bestDistance);
		return bestDistance;
	}

	/*public BufferedImage getDebugImg() {
		return debugImg;
	}*/


	
}
