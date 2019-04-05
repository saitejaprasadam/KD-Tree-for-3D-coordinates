package KDTree;

public class Point3D {
	
	float x, y, z;
	
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float cooridnateIndex(int index) {
		
		if(index == 0) return x;
		else if(index == 1) return y;
		else if(index == 2) return z;
		
		System.out.println("asdasd");
		return -1;
	}
	
	public String toString() {
		return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z) + ")";
	}
	
}