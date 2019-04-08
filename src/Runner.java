import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
				
				//if(coordinates.size() > 500)
				//	break;
			}
		
			System.out.println("Building KD Tree");
			KDTree KDTreeObject = new KDTree(coordinates);
			KDTreeObject.build();
			System.out.println("Size of KD Tree: " + KDTreeObject.getSize());
			
			Point3D temp = new Point3D(0f, 0f, 0f);
			Point3D temp2 = new Point3D(1000f, 1000f, 1000f);
			
			System.out.println("\nRange Searching between for " + temp + " <==> " + temp2);
			KDTreeObject.rangeQuery(KDTreeObject.rootNode, temp, temp2);
			System.out.println(KDTreeObject.range_coordiantes.size());
			
			bufferedReader.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}