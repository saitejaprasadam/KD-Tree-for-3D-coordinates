package KDTree;

public class Node {

	private Point3D coordinates;
	private int level;
	
	Node leftNode, rightNode;
	
	public Node(Point3D coordinates, int level) {
		this.coordinates = coordinates;
		this.level = level;
	}
	
	public Point3D getCoordinates() {
		return coordinates;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getAxis() {
		return Math.abs(level - getNearestWholeMultiple(level)); 
	}
	
	static int getNearestWholeMultiple(int input) {
		
		int output = Math.round(input / 3);
	    if (output == 0 && input > 0) 
	    	output += 1;
	      
	    output *= 3;
	    return output;   
    }
	
}