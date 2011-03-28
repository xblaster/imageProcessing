package net.lo2k.edge;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	private BufferedImage img;

	public DisplayPanel(BufferedImage img) {
		this.img = img;
		Dimension imgDim = new Dimension((int)img.getWidth(), (int)img.getHeight());
		this.setMinimumSize(imgDim);
		this.setMaximumSize(imgDim);
		this.setPreferredSize(imgDim);
	}
	
	public void paint(Graphics g) {
	    g.drawImage(img, 0,0,this);
	  }

}
