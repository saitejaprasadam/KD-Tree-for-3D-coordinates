package KDTree;

import java.util.List;

public class Node{
	
	Point3D point;
	volatile List<Point3D> leftList = null;
	volatile List<Point3D> rightList = null;
	Node leftNode, rightNode;
	private int level;
	
	public Node(Point3D point, int level, List<Point3D> leftList, List<Point3D> rightList){
		this.point  = point;
		this.level = level;
		this.rightList = rightList;
		this.leftList = leftList;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getAxis() {
		return level % 3;
	}
	
	public void clearList() {
		leftList = null;
		rightList = null;
	}
	
	public boolean checkIfRange(Point3D lowerRange, Point3D upperRange) {
	
		if(lowerRange.x <= point.x && point.x <= upperRange.x && 
				lowerRange.y <= point.y && point.y <= upperRange.y && 
				lowerRange.z <= point.z && point.z <= upperRange.z) {
			return true;
		}
	
		return false;
	}
	
}