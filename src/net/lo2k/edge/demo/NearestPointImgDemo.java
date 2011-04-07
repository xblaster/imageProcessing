package net.lo2k.edge.demo;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.lo2k.edge.Detector;
import net.lo2k.edge.DisplayPanel;
import net.lo2k.edge.NearestPointImg;

public class NearestPointImgDemo extends JPanel {

	
	private static final String IMG_URL = "seek.jpg";
	
	
	public static void main(String args[]) throws MalformedURLException,
			IOException {

		final BufferedImage imageOne = ImageIO.read(new File(IMG_URL));
		final JFrame frame = new JFrame();

		frame.setLayout(new GridLayout(2, 2));
		
		Detector detector = new Detector();
		
		
		//frame.add(new DisplayPanel(imageOne));
		
		BufferedImage edges = detector.getEdgesFor(imageOne);
		
		frame.add(new DisplayPanel(edges));
		
		NearestPointImg img = new NearestPointImg(edges);
		
		frame.add(new DisplayPanel(img.getDebugImage()));
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

}