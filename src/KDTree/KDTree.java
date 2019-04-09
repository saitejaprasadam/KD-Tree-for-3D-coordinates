package KDTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import KDTree.Point3D;
import javafx.util.Pair;

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
		System.out.println("Building KD Tree");
		
		int median = median(coordiantes, 0);
		rootNode = new Node(coordiantes.get(median), 0, coordiantes.subList(0, median), coordiantes.subList(median + 1, coordiantes.size()));
		insert(rootNode);
		System.out.println("Size of KD Tree: " + size);
	}
	
	private void insert(Node currentNode) {
	
		if(currentNode == null)
			return;
		
		int axis = currentNode.getAxis();
		
		if(currentNode.leftList.size() > 0) {	
			int median = median(currentNode.leftList, axis);			
			currentNode.leftNode = new Node(currentNode.leftList.get(median), currentNode.getLevel() + 1, currentNode.leftList.subList(0, median), currentNode.leftList.subList(median + 1, currentNode.leftList.size()));
		}
		
		if(currentNode.rightList.size() > 0) {
			int median = median(currentNode.rightList, axis);			
			currentNode.rightNode = new Node(currentNode.rightList.get(median), currentNode.getLevel() + 1, currentNode.rightList.subList(0, median), currentNode.rightList.subList(median + 1, currentNode.rightList.size()));
		}
			
		size++;	
		insert(currentNode.leftNode);
		insert(currentNode.rightNode);
		currentNode.clearList();
	}

	public void rangeQuery(Node currentNode, Point3D lowerRange, Point3D upperRange, int freeLeftRight) {
		
		if(currentNode == null)
			return;
	
		if(currentNode.checkIfRange(lowerRange, upperRange))
			range_coordiantes.add(currentNode.point);
				
		if(currentNode.getLevel() < 3) {
			
			if(lowerRange.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) <= 0 || freeLeftRight == -1) 
				rangeQuery(currentNode.leftNode, lowerRange, upperRange, 1);
			
			if(upperRange.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) >= 0 || freeLeftRight == 1)
				rangeQuery(currentNode.rightNode, lowerRange, upperRange, -1);
			
		} else {
			rangeQuery(currentNode.leftNode, lowerRange, upperRange, 0);
			rangeQuery(currentNode.rightNode, lowerRange, upperRange, 0);
		}
	
	}
	
	public Pair<Double, Point3D> nearestNeighbor(Node currentNode, Point3D point, Pair<Double, Point3D> nearest) {
		
		if(currentNode == null)
			return nearest;
	
		Double ssd = currentNode.point.ssd(point);
		if(nearest == null || nearest.getKey() >= ssd)
			nearest = new Pair<Double, Point3D>(ssd, currentNode.point);
		
		if(currentNode.getLevel() < 3) {
			
			if(point.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) <= 0) 
				nearest = nearestNeighbor(currentNode.leftNode, point, nearest);
			
			if(point.cooridnateIndex(currentNode.getAxis()).compareTo(currentNode.point.cooridnateIndex(currentNode.getAxis())) >= 0)
				nearest = nearestNeighbor(currentNode.rightNode, point, nearest);
			
		} else {
			nearest = nearestNeighbor(currentNode.leftNode, point, nearest);
			nearest = nearestNeighbor(currentNode.rightNode, point, nearest);
		}
		
		return nearest;
	}

}