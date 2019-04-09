import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import KDTree.KDTree;
import KDTree.Point3D;

public class Runner {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		process();
				
		long time = System.currentTimeMillis() - start;			
		String timeString = String.format("%d min, %d sec or %d milliseconds", 
				(int) ((time / (1000*60)) % 60),
				(int) (time / 1000) % 60,
				(int) time);
		
		System.out.println("\nDone in " + timeString);
		
	}
	
	private static void process() {		
		indexWithKDTree("input.txt");
	}

	private static void indexWithKDTree(String fileName) {
		
		try {
			
			Point3D lowerBound = new Point3D(357.3839f, 512.5141f, 626.4071f);
			Point3D upperBound = new Point3D(857.3839f, 612.5141f, 638.4071f);
			Point3D nearestTo = new Point3D(0.993822f, 4.021896f, 2.58316f);
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			ArrayList<Point3D> coordinates = new ArrayList<Point3D>(); 
			
			System.out.println("Reading Data");
			String line = null;		
			while((line = bufferedReader.readLine()) != null) {
				line = line.trim().replace("(", "").replace(")", "");
				
				String[] temp = line.split(",");
				
				float x = Float.parseFloat(temp[0].trim());
				float y = Float.parseFloat(temp[1].trim());
				float z = Float.parseFloat(temp[2].trim());
				
				Point3D point = new Point3D(x, y, z);
				coordinates.add(point);
			
			}
		
			KDTree KDTreeObject = new KDTree(coordinates);
			KDTreeObject.build();
			
			System.out.println("\nRange Searching between for " + lowerBound + " <==> " + upperBound);
			KDTreeObject.rangeQuery(KDTreeObject.rootNode, lowerBound, upperBound, 0);			
			System.out.println("Found " + KDTreeObject.range_coordiantes.size() + " in range");
			
			System.out.print("Do you want to see them (Y/N): ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			scanner.close();
			
			if(input.equalsIgnoreCase("Y"))
				for (Point3D range_points : KDTreeObject.range_coordiantes) 
					System.out.println(range_points);
			
			System.out.println("\nLooking for nearest neighbor of " + nearestTo);
			System.out.println(KDTreeObject.nearestNeighbor(KDTreeObject.rootNode, nearestTo, null).getValue());
			
			bufferedReader.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}