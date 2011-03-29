package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class HausdorffDistance {
	public double iterate(BufferedImage groupA, BufferedImage groupB) {
		
		int nbWhitePoint = 0;
		double distance = 0d;
		
		int width = groupA.getWidth();
		int height = groupA.getHeight();
		
		
		for (int i = 0; i < width; i++) {
			double offset = height / 3;
			for (int j = 0; j < height; j++) {
				if (ImgUtil.isWhitePixel(groupA, i, j)) {
					nbWhitePoint++;
					double localDistance = calculateNeareastPointDistance(groupB,i,j,(int)offset); 
					distance += localDistance;
					offset = localDistance;
				} else {
					offset += 1d;
					//limit the offset
					if (offset > height/3) 
						offset = height/3;
				}
			}
		}
		System.out.println("DEBUG info");
		System.out.println("nbPoint: "+nbWhitePoint );
		System.out.println("distance: "+distance);
		return (distance/(double)nbWhitePoint);
	}


	private double calculateNeareastPointDistance(BufferedImage groupB2, int x, int y, int offset) {
		System.out.println("===========================");
		System.out.println("Nearest for " + x + "," + y);
		System.out.println("offset "+offset);
		if (ImgUtil.isWhitePixel(groupB2, x, y)) {
			System.out.println("Exact match ! 0l");
			return 0l;
		}
		double bestDistance = Long.MAX_VALUE;
		/*for (int i = 0; i < groupB2.getWidth(); i++) {
			for (int j = 0; j < groupB2.getHeight(); j++) {*/
		for (int i = x-offset/2; i < x+offset/2; i++) {
			for (int j = y-offset/2; j < y+offset/2; j++) {
				if (ImgUtil.isWhitePixel(groupB2, i, j)) {
					System.out.println(i+","+j+" -> X");
					//small optimisation
					if (Math.abs(i - x) < bestDistance) {
						if (Math.abs(j - y) < bestDistance) {
							double euclidianDistance = Math.sqrt((i - x)*(i - x)
									+ (j - y)*(j - y));

							if (euclidianDistance < bestDistance) {
								System.out.println("Best at "+i+", "+j+" -> "+euclidianDistance);
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
		System.out.println("best "+bestDistance);
		return bestDistance;
	}


	
}
