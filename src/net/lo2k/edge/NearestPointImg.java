package net.lo2k.edge;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class NearestPointImg {
	
	private double[][] nearestArr;
	protected BufferedImage img;
	
	private class PopulateAction {
		
		public PopulateAction(int x, int y, double value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		public int x, y;
		public double value;
	}
	
	private List<PopulateAction> actions;
	
	public NearestPointImg() {
		
	}
	
	public NearestPointImg(BufferedImage img) {
		this.img = img;
		nearestArr = new double[img.getWidth()][img.getHeight()];
		
		//init with max value;
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				nearestArr[i][j] = Double.MAX_VALUE;
			}
		}
		actions = new LinkedList<PopulateAction>();
		constructArr();
	}

	private void constructArr() {
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				if (ImgUtil.isWhitePixel(img, i, j)) {
					propagate(i,j,0);
				}
			}
		}
		
		
		while(!actions.isEmpty()) {
			PopulateAction act = actions.get(0);
			propagate(act.x, act.y, act.value);
			actions.remove(0);
		}
	}
	
	public double getNearestDistanceFrom(int i, int j) {
		if (i<0) {
			return getNearestDistanceFrom(0, j)-i;
		}
		if(j<0) {
			return getNearestDistanceFrom(i, 0)-j;
		}
		if (i>= img.getWidth()) {
			return getNearestDistanceFrom(img.getWidth()-1, j)+(i-img.getWidth());
		}
		if (j>= img.getHeight()) {
			return getNearestDistanceFrom(i, img.getHeight()-1)+(j-img.getHeight());
		}
		return nearestArr[i][j];
	}
	
	private double innerGetValFor(int i, int j) {
		if (i<0) {
			return -1;
		}
		if(j<0) {
			return -1;
		}
		if (i>= img.getWidth()) {
			return -1;
		}
		if (j>= img.getHeight()) {
			return -1;
		}
		
		return nearestArr[i][j];
	}
	
	private void propagate(int i, int j, double value) {
		
		
		if (nearestArr[i][j] > value) {
			nearestArr[i][j] = value;
			//dirt but avoid stack overflow
			if (innerGetValFor(i-1,j) > value +1) {
				actions.add(new PopulateAction(i-1, j, value+1));
			}
			if (innerGetValFor(i+1,j) > value +1) {
				actions.add(new PopulateAction(i+1, j, value+1));
			}
			if (innerGetValFor(i,j-1) > value +1) {
				actions.add(new PopulateAction(i, j-1, value+1));
			}
			if (innerGetValFor(i,j+1) > value +1) {
				actions.add(new PopulateAction(i, j+1, value+1));
			}
		}
		
	}
	
	public BufferedImage getDebugImage() {
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int level = (int) nearestArr[i][j];
				res.setRGB(i, j, ImgUtil.toRGB(level, level, level));
			}
		}
		return res;
	}

	public int getWidth() {
		return img.getWidth();
	}
	
	public int getHeight() {
		return img.getHeight();
	}
}
