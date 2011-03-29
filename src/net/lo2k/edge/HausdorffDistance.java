package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class HausdorffDistance {
	public double iterate(BufferedImage groupA, BufferedImage groupB) {
		
		int nbWhitePoint = 0;
		long distance = 0l;
		
		int width = groupA.getWidth();
		int height = groupA.getHeight();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (ImgUtil.isWhitePixel(groupA, i, j)) {
					nbWhitePoint++;
					distance += calculateNeareastPointDistance(groupB,i,j);
				}
			}
		}
		System.out.println("DEBUG info");
		System.out.println("nbPoint: "+nbWhitePoint );
		System.out.println("distance: "+distance);
		return (distance/(float)nbWhitePoint);
	}


	private double calculateNeareastPointDistance(BufferedImage groupB2, int x, int y) {
		System.out.println("Nearest for " + x + "," + y);
		if (ImgUtil.isWhitePixel(groupB2, x, y)) {
			return 0l;
		}
		double bestDistance = Long.MAX_VALUE;
		for (int i = 0; i < groupB2.getWidth(); i++) {
			for (int j = 0; j < groupB2.getHeight(); j++) {
				if (ImgUtil.isWhitePixel(groupB2, i, j)) {
					//small optimisation
					if (Math.abs(i - x) < bestDistance) {
						if (Math.abs(j - y) < bestDistance) {
							double euclidianDistance = Math.sqrt((i + x) ^ 2
									+ (j + y) ^ 2);

							if (euclidianDistance < bestDistance) {
								bestDistance = euclidianDistance;
							}
						}
					}
				}
			}
		}

		return bestDistance;
	}


	
}
