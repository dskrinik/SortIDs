import java.io.*;
import java.util.*;

class SortIDs {
	String[] a;	
public String[] sortStringList(List<String> list){
	a = list.toArray(new String[list.size()]);
	for (int i = 0; i < a.length-1; i++){
		for (int j = 0; j < a.length-1; j++){
			if(a[j].compareTo(a[j+1])>0){
				String temp;
				temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}
	}
	return a;
}
private void saveToFile(String[] a2){
	String newLine = System.getProperty("line.separator");
	try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))){
		for (int i = 0; i < a2.length; i++)
			writer.write(a[i] + newLine);	
	} catch(IOException e){e.printStackTrace();}
	 catch(NullPointerException e){e.printStackTrace();}	
}
public static void main(String[] args){
	SortIDs tester = new SortIDs();
	String temp="";
	List<String> result = new ArrayList<>();
	String[] a;
	System.out.println("Please enter Id's.");
	Scanner keyboard = new Scanner(System.in);
	while(keyboard.hasNext()){
		temp = keyboard.next();
		if(temp.contains("y"))
			break;
		result.add(temp);
		System.out.println("\nThanks\nAre you done? Enter 'y' for yes.");
	}
	a = tester.sortStringList(result);
	tester.saveToFile(a);
}
}