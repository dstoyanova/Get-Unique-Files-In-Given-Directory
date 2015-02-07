import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Main {

	private static List<File> getAllFiles(String directoryPath){
		List<File> files = new ArrayList<File>();
		Queue<File> directories = new LinkedList<File>();
		
		directories.add(new File(directoryPath));
		
		while (!directories.isEmpty()) {
		  for (File f : directories.poll().listFiles()) {
		    if (f.isDirectory()) {
		      directories.add(f);
		    } else if (f.isFile()) {
		      files.add(f);
		    }
		  }
		}
		
		return files;
	}
	
	private static List<File> getUniqueFiles(String directoryPath) throws Exception{
		List<File> list1 = getAllFiles(directoryPath);
		List<File> list2 = getAllFiles(directoryPath);
		List<File> result = new ArrayList<File>();
		
		for(File f1 : list1){
			int count = 0;
			for(File f2 : list2){
				if(FileUtils.contentEquals(f1, f2)){
					count = count + 1;
				}
			}
			// Every file could be met once at least
			// If count > 1 this automatically means that there is another duplicate
			if(count == 1){
				result.add(f1);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter directory's path: ");
		String directoryPath = sc.next();
		
		List<File> files = getUniqueFiles(directoryPath);
		
		for(File f : files){
			System.out.println(f.getName());
		}
		
		sc.close();
	}
}
