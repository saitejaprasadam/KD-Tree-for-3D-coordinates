package KDTree;

import java.util.Comparator;

public class Point3D {
	
	Float x, y, z;
	
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Float cooridnateIndex(int index) {
		
		if(index == 0) return x;
		else if(index == 1) return y;
		else if(index == 2) return z;
		
		System.out.println("asdasd");
		return -1.0f;
	}
	
	public String toString() {
		return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z) + ")";
	}
	
	public double ssd(Point3D point) {
		return Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2) + Math.pow(point.z - z, 2));
	}
	
	public static class SortbyX implements Comparator<Point3D> { 

	    public int compare(Point3D a, Point3D b) { 
	    	if (a.x < b.x) return -1;	    	
	    	if (a.x > b.x) return 1;	    	
	    	return 0;
	    } 
	} 
	  
	public static class SortbyY implements Comparator<Point3D> {
		
	    public int compare(Point3D a, Point3D b) {	    	
	    	if (a.y < b.y) return -1;
	    	if (a.y > b.y) return 1;
	    	return 0;
	    } 
	} 

	public static class SortbyZ implements Comparator<Point3D> { 
		
		public int compare(Point3D a, Point3D b) { 
			if (a.z < b.z) return -1;
			if (a.z > b.z) return 1;
			return 0;
		} 
	}
	
}