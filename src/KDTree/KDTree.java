package KDTree;

import KDTree.Point3D;

public class KDTree {

	private Node rootNode;
	private int size = 0;
	
	public void insert(Point3D coordinates) {
		
		if(rootNode == null) 
			rootNode = new Node(coordinates, 0);		
	
		else
			insert(rootNode, coordinates);
			
		size++;
	}
	
	private void insert(Node currentNode, Point3D coordinates) {
		int axis = currentNode.getAxis();
		
		if(currentNode.getCoordinates().cooridnateIndex(axis) < coordinates.cooridnateIndex(axis)) {
			
			if(currentNode.leftNode == null) 
				currentNode.leftNode = new Node(coordinates, currentNode.getLevel() + 1);

			else 
				insert(currentNode.leftNode, coordinates);
			
		} else if(currentNode.getCoordinates().cooridnateIndex(axis) > coordinates.cooridnateIndex(axis)) {
			
			if(currentNode.rightNode == null) 
				currentNode.rightNode = new Node(coordinates, currentNode.getLevel() + 1);

			else 
				insert(currentNode.rightNode, coordinates);
			
		}  
		
	}

	
	public int getSize() {
		return size;
	}

}