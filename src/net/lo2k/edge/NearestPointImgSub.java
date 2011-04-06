package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class NearestPointImgSub extends NearestPointImg{
	private int offsetX;
	private NearestPointImg offsetedImg;
	private int offsetY;
	private int width;
	private int height;

	public NearestPointImgSub(NearestPointImg img, int offsetX, int offsetY, int width, int height) {
		super();
		this.offsetedImg = img;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public BufferedImage getDebugImage() {
		return offsetedImg.getDebugImage();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getNearestDistanceFrom(int i, int j) {
		return offsetedImg.getNearestDistanceFrom(i+offsetX, j+offsetY);
	}
	
	
}
