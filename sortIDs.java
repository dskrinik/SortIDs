import java.io.*;
import java.util.*;

public class sortIDs {
	
	public static void main(String[] args){

		String sourceFile;
		String[] unsortedArray;
		String[] sortedArray;
		
		sortIDs sorter = new sortIDs();
		sourceFile = sorter.readFromFile("sourceFile.txt");
		unsortedArray = sorter.stringToArray(sourceFile);
		sortedArray = sorter.sort(unsortedArray);
		sorter.writeToFile(sortedArray);
	}
	
	String[] sort(String[] a){
		
		char[] charA;
		String letterChunk = "";
		Integer intChunk;
		String digitsString = "";
		String alphanumsString = "";
		String lettersString = "";
		Integer[] sortedDigits;
		String[] sortedAlphanums;
		String[] sortedLetters;
	
		for (int i = 0; i < a.length; i++){
			charA = stringToCharArray(a[i]);

			if (charA.length > 1 && alternatingAlphaNums(charA)) {
				System.out.println("IDs with alternating digits and letters like " + a[i] + " can't be sorted");
			} else if (charA.length > 1 && a[i].matches(".*\\d+.*") && a[i].matches(".*[a-zA-Z].*")) {
				alphanumsString += a[i] + ", ";
			} else if (!a[i].matches(".*\\d+.*")) {
				letterChunk = pullLetters(charA);
				lettersString += letterChunk + ", ";
			} else if (!a[i].matches(".*[a-zA-Z].*")) {
				intChunk = pullInts(charA);
				digitsString += intChunk + ", ";
			} else
				System.out.println("IDs with characters other than digits or letter are invalid");

		}		
		sortedDigits = sortDigits(digitsString);
		sortedAlphanums = sortAlphanums(alphanumsString);
		sortedLetters = sortLetters(lettersString);
		a = combine(sortedDigits, sortedAlphanums, sortedLetters);
		return a;
	}
	
	String[] combine(Integer[] a1, String[] a2, String[] a3){
		String[] result = new String[a1.length + a2.length + a3.length];
		for(int i = 0; i<a1.length; i++){
			result[i] = String.valueOf(a1[i]);
		}
			for(int i = 0; i<a2.length; i++){
			result[a1.length+i] = a2[i];
		}
			for(int i = 0; i<a3.length; i++){
			result[a1.length + a2.length + i] = a3[i];
		}
		return result;
	}
	
	static boolean alternatingAlphaNums(char[] a) {
		int changeCounter = 0;
		
		for(int i = 0; i<a.length-1; i++) {
			if((Character.isDigit(a[i]) && Character.isAlphabetic(a[i+1])) ||
					(Character.isDigit(a[i+1]) && Character.isAlphabetic(a[i])))
				changeCounter += 1; 
		}
		return changeCounter>1;
	}
	
	static Integer[] sortDigits(String s){
		String[] tempIntA = s.split("\\s*,\\s*");
		Integer[] result = new Integer[tempIntA.length];
		for(int i = 0; i<result.length; i++){
			try { 
				result[i] = Integer.parseInt(tempIntA[i]);
			}catch (NumberFormatException e){System.out.println("not a number");}
		}
		for(int i = 0; i<result.length-1; i++){
			for(int j = 0; j<result.length-1-i; j++){
				if(result[j]>result[j+1]){
					result = (Integer[]) swapSubsequentElems(result, j);
				}
			}
		}
		return result;
	}
	
	static String[] sortLetters(String s){
		String[] result = s.split("\\s*,\\s*");		
		for(int j = 0; j<result.length-1;j++){
			for(int i = 0; i<result.length-1-j;i++){
				if(result[i].compareTo(result[i+1])>0){
					result = (String[]) swapSubsequentElems(result, i);
				}
			}	
		}
		return result;
	}
	
	static String[] sortAlphanums(String s){
		
		Integer digitPart1;
		Integer digitPart2;
		String letterPart1;
		String letterPart2;
		char[] charArray1;
		char[] charArray2;
		
		String[] result = s.split("\\s*,\\s*");
		for(int j = 0; j<result.length-1; j++){
			for(int i = 0; i<result.length-1-j; i++){
				
				charArray1 = stringToCharArray(result[i]);
				charArray2 = stringToCharArray(result[i+1]);	
				digitPart1 = pullInts(charArray1);
				digitPart2 = pullInts(charArray2);
				
				if(digitPart1>digitPart2){
					result = (String[]) swapSubsequentElems(result, i);
				}
				charArray1 = stringToCharArray(result[i]);
				charArray2 = stringToCharArray(result[i+1]);
				letterPart1 = pullLetters(charArray1);
				letterPart2 = pullLetters(charArray2);
				
				if(digitPart1 == digitPart2){
					if(letterPart1.compareTo(letterPart2)>0){
						result = (String[]) swapSubsequentElems(result, i);
					}
				}
			}
		}
		return result;
	}
	
	static Object[] swapSubsequentElems(Object[] o, int i){
		if(o instanceof String[]){
			String[] a = Arrays.stream(o).toArray(String[]::new);
			String temp;
			temp = a[i];
			a[i] = a[i+1];
			a[i+1] = temp;
			return a;
		}
		else{
			Integer[] a = Arrays.stream(o).toArray(Integer[]::new);
			int temp;
			temp = a[i];
			a[i] = a[i+1];
			a[i+1] = temp;
			return a;
		}
	}
	
	static char[] stringToCharArray(String s){
		char[] result=s.toCharArray();
		return result;
	}
	
	static Integer pullInts(char[] charA){
		Integer result = null;
		String stringOfInts = "";
		
		for(int i = 0; i<charA.length; i++){
			if(Character.isDigit(charA[i])){
			  stringOfInts += charA[i];
			}
		}
		try { 
			result = Integer.valueOf(stringOfInts);
		}catch (NumberFormatException e){System.out.println("Hi there. This is not a number");}  
		return result;
	}
	
	static String pullLetters(char[] charA){
		int[] result;
		String lettersSt = "";
		
		for(int i = 0; i<charA.length; i++){
			if(Character.isLetter(charA[i])){
			  lettersSt += charA[i];
			}
		}
		return lettersSt;
	}

	void writeToFile(String[] file){
		String lineSeparator = System.getProperty("line.separator");
			try(BufferedWriter w = new BufferedWriter(new FileWriter("outputcsv.txt"))){
				for(int i = 0; i<file.length; i++){
					w.write(file[i] + lineSeparator);
				}
			} catch(IOException e) {e.printStackTrace();}
	}
	
	String readFromFile(String fileName){
		String input;
		String result = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			while((input = br.readLine())!=null){
				result = result + input + ",";
			}
		} catch(IOException e){e.printStackTrace();}
		return result;
	}
	
	String[] stringToArray(String file){
		String[] result;
		result = file.split("\\s*,\\s*");
		return result;
	}
}

