package net.lo2k.edge;

import java.awt.image.BufferedImage;

public class ImgUtil {
	
	public static int getRed(BufferedImage i, int x, int y) {
		return ((i.getRGB(x,y) >> 16) & 255);
	}
	
	public static int getGreen(BufferedImage i, int x, int y) {
		return ((i.getRGB(x,y) >> 8) & 255);
	}
	
	public static int getBlue(BufferedImage i, int x, int y) {
		return ((i.getRGB(x,y)) & 255);
	}
	
	public static int[] getRGB(BufferedImage i, int x, int y) {
		int rgb = i.getRGB(x,y);
		
		int[] res = new int[3];
		res[0] = (rgb >> 16) & 255;
		res[1] = (rgb >> 8) & 255;
		res[2] = (rgb) & 255;
		return res;
	}
	
	public static int getGreyScale(BufferedImage i, int x, int y) {
		int[] rgb = getRGB(i, x, y);
		
		return (rgb[0]+rgb[1]+rgb[2])/3;
	}
	
	public static int toRGB(int red, int green, int blue) {
		return (red<<16)|(green<<8)|(blue);
	}
}
