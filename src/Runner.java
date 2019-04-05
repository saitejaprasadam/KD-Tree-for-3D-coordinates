import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
			
			KDTree KDTreeObject = new KDTree();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = null;		
			while((line = bufferedReader.readLine()) != null) {
				line = line.trim().replace("(", "").replace(")", "");
				
				String[] coordinates = line.split(",");
				
				float x = Float.parseFloat(coordinates[0].trim());
				float y = Float.parseFloat(coordinates[1].trim());
				float z = Float.parseFloat(coordinates[2].trim());
				
				Point3D point = new Point3D(x, y, z);
				KDTreeObject.insert(point);
								
			}
			
			System.out.println(KDTreeObject.getSize());
			bufferedReader.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}