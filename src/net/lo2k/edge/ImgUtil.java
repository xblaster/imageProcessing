package net.lo2k.edge;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
	
	
	public static boolean isWhitePixel(BufferedImage i, int x, int y) {
		if (x < 0) {
			return false;
		}
		if (y < 0) {
			return false;
		}
		
		if (x >= i.getWidth()) {
			return false;
		}
		
		if (y >= i.getHeight()) {
			return false;
		}
		
		return ((short)i.getRGB(x, y)!=0);
	}
	
	 public static BufferedImage resize(BufferedImage source, int destWidth, int destHeight, Object interpolation) {
	        if (source == null)
	            throw new NullPointerException("source image is NULL!");
	        if (destWidth <= 0 && destHeight <= 0)
	            throw new IllegalArgumentException("destination width & height are both <=0!");
	        int sourceWidth = source.getWidth();
	        int sourceHeight = source.getHeight();
	        double xScale = ((double) destWidth) / (double) sourceWidth;
	        double yScale = ((double) destHeight) / (double) sourceHeight;
	        if (destWidth <= 0) {
	            xScale = yScale;
	            destWidth = (int) Math.rint(xScale * sourceWidth);
	        }
	        if (destHeight <= 0) {
	            yScale = xScale;
	            destHeight = (int) Math.rint(yScale * sourceHeight);
	        }
	        GraphicsConfiguration gc = getDefaultConfiguration();
	        BufferedImage result = gc.createCompatibleImage(destWidth, destHeight, source.getColorModel().getTransparency());
	        Graphics2D g2d = null;
	        try {
	            g2d = result.createGraphics();
	            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
	            AffineTransform at =
	                    AffineTransform.getScaleInstance(xScale, yScale);
	            g2d.drawRenderedImage(source, at);
	        } finally {
	            if (g2d != null)
	                g2d.dispose();
	        }
	        return result;
	    }
	 
	  public static GraphicsConfiguration getDefaultConfiguration() {
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();
	        return gd.getDefaultConfiguration();
	  }
}
