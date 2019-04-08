package KDTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import KDTree.Point3D;

public class KDTree {

	public Node rootNode;
	private int size = 0;
	private ArrayList<Point3D> coordiantes;
	public ArrayList<Point3D> range_coordiantes;
	
	public KDTree(ArrayList<Point3D> coordiantes) {
		this.coordiantes = coordiantes;
		this.range_coordiantes = new ArrayList<>();
	}

	private int median(List<Point3D> list, int axis) {
		
		Comparator<Point3D> comparator = null;
		
		if(axis == 0) comparator = new Point3D.SortbyX();
		else if(axis == 1) comparator = new Point3D.SortbyY();
		else if(axis == 2) comparator = new Point3D.SortbyZ();
		
		Collections.sort(list, comparator);
		int size = list.size();
		
		if(size % 2 == 1)
			return (size - 1) / 2;
		 else 
			return size / 2;
		
	}
	
	public void build() {
		int median = median(coordiantes, 0);
		rootNode = new Node(coordiantes.get(median), 0, coordiantes.subList(0, median), coordiantes.subList(median + 1, coordiantes.size()));
		insert(rootNode);
	}
	
	private void insert(Node currentNode) {
	
		if(currentNode == null)
			return;
		
		int axis = currentNode.getAxis();
		
		if(currentNode.leftList.size() > 0) {	
			
			int median = median(currentNode.leftList, axis);			
			currentNode.leftNode = new Node(currentNode.leftList.get(median), currentNode.getLevel() + 1, currentNode.leftList.subList(0, median), currentNode.leftList.subList(median + 1, currentNode.leftList.size()));

			//System.out.println("Left => " + currentNode.leftList.size());
			//currentNode.clearList();	
			
		}
		
		if(currentNode.rightList.size() > 0) {

			int median = median(currentNode.rightList, axis);			
			currentNode.rightNode = new Node(currentNode.rightList.get(median), currentNode.getLevel() + 1, currentNode.rightList.subList(0, median), currentNode.rightList.subList(median + 1, currentNode.rightList.size()));

			//System.out.println("Right => " + currentNode.rightList.size());			
			//currentNode.clearList();	
			
		}
			
		size++;	
		insert(currentNode.leftNode);
		insert(currentNode.rightNode);
		
	}

	public void rangeQuery(Node currentNode, Point3D lowerRange, Point3D upperRange) {
		
		if(currentNode == null)
			return;
	
		if(currentNode.checkIfRange(lowerRange, upperRange))
			range_coordiantes.add(currentNode.point);
		
		if(currentNode.getLevel() < 3) {
			
			if(lowerRange.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) <= 0) {
				rangeQuery(currentNode.leftNode, lowerRange, upperRange);
			}
			
			if(upperRange.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) >= 0) {

				rangeQuery(currentNode.rightNode, lowerRange, upperRange);
			}
			
		} else {
			rangeQuery(currentNode.leftNode, lowerRange, upperRange);
			rangeQuery(currentNode.rightNode, lowerRange, upperRange);
		}
	
	}
	
	/*public void nearestNeighbor(Node currentNode, Point3D point) {
				
		if(point.cooridnateIndex(currentNode.getAxis()) == currentNode.key) {
						
			if(currentNode.rightLeaf != null)
				System.out.println(currentNode.rightLeaf);
			else 
				nearestNeighbor(currentNode.rightNode, point);
		
		} else if(point.cooridnateIndex(currentNode.getAxis()) < currentNode.key || currentNode.leftLeaf != null) {
		
			if(currentNode.leftLeaf != null)
				System.out.println(currentNode.leftLeaf);
			else 
				nearestNeighbor(currentNode.leftNode, point);
		
		} else if(point.cooridnateIndex(currentNode.getAxis()) > currentNode.key || currentNode.rightLeaf != null) { 			
			
			if(currentNode.rightLeaf != null)
				System.out.println(currentNode.rightLeaf);
			else 
				nearestNeighbor(currentNode.rightNode, point);
		
		} 
		
	}*/
	
	public int getSize() {
		return size;
	}

}