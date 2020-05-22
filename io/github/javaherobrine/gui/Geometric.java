package io.github.javaherobrine.gui;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
public class Geometric {
	public static void parallelMove(double degree,double distance,Rectangle r) {
		double[] degs=new double[] {Double.parseDouble(degree(degree).split("=")[0]),Double.parseDouble(degree(degree).split("=")[1])};
		if(degs[0]==0) {
			switch((int)degs[1]) {
			case 0:
				r.x+=distance;
			case 1:
				r.y+=distance;
			case 2:
				r.x-=distance;
			case 3:
				r.y-=distance;
			}
		}else {
			double b=distance*Math.sin(degs[0]);
			double a=Math.sqrt(distance*distance-b*b);
			switch((int)degs[1]) {
			case 0:
				r.x+=b;
				r.y+=a;
			case 1:
				r.x-=b;
				r.y+=a;
			case 2:
				r.x-=b;
				r.y-=b;
			case 3:
				r.x+=b;
				r.y-=a;
			}
		}
		r.setLocation(r.x,r.y);
	}
	public static void parallelMove(double degree,double distance,int ovalX,int ovalY,int ovalHeight,int ovalWidth) {
		double[] degs=new double[] {Double.parseDouble(degree(degree).split("=")[0]),Double.parseDouble(degree(degree).split("=")[1])};
		if(degs[0]==0) {
			switch((int)degs[1]) {
			case 0:
				ovalX+=distance;
			case 1:
				ovalY+=distance;
			case 2:
				ovalX-=distance;
			case 3:
				ovalY-=distance;
			}
		}else {
			double b=distance*Math.sin(degs[0]);
			double a=Math.sqrt(distance*distance-b*b);
			switch((int)degs[1]) {
			case 0:
				ovalX+=b;
				ovalY+=a;
			case 1:
				ovalX-=b;
				ovalY+=a;
			case 2:
				ovalX-=b;
				ovalY-=b;
			case 3:
				ovalX+=b;
				ovalY-=a;
			}
		}
	}
	public static String degree(double degree) {
		degree%=360;
		if(degree<0) {
			degree+=360;
		}
		return (degree/90)/180*Math.PI+"="+(int)degree/90;
	}
	public static void scaling(Rectangle r,double power) {
		r.height*=power;
		r.width*=power;
		r.x-=r.width/power*(power-1);
		r.y-=r.height/power*(power-1);
		r.setLocation(r.x,r.y);
	}
	public static void scaling(double power,int ovalX,int ovalY,int ovalHeight,int ovalWidth) {
		ovalHeight*=power;
		ovalWidth*=power;
		ovalX-=ovalWidth/power*(power-1);
		ovalY-=ovalWidth/power*(power-1);
	}
	public static void roll(Rectangle r,double degree,Point from) {
        r.contains(from);
        AffineTransform at = AffineTransform.getRotateInstance(
        Double.parseDouble(degree(degree).split("=")[0]), r.getCenterX(), r.getCenterY());
        Polygon p = new Polygon(); 
        PathIterator i = r.getPathIterator(at);
        while (!i.isDone()) {
            double[] xy = new double[2];
            i.currentSegment(xy);
            p.addPoint((int) xy[0], (int) xy[1]);
            i.next();
        }
	}
	public static Shape roll(double degree,int ovalX,int ovalY,int ovalWidth,int ovalHeight) {
		return AffineTransform.getRotateInstance(Double.parseDouble(degree(degree).split("=")[0]))
		  .createTransformedShape(new Ellipse2D.Double(ovalX, ovalY, ovalWidth, ovalHeight));
	}
}
