import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;


public class fileAnalysis {
		
		// Variables
		public String fName;
		public int charCount;
		public int wordCount;
		public int senCount;
		public int[] freq;
		public String[] wordTable;
		public String content;
		public int fSize;
		
		// constructor 
		public fileAnalysis(String f) {
			fName = f;
			charCount = 0;
			wordCount = 0;
			senCount = 0;
		}
		
		// reads the file and stores the contents in a variable (must be run before other methods)
		public void readFile() {
			try {	
			File input = new File(fName);
			Scanner temp = new Scanner(input).useDelimiter("\\Z");
			content = temp.next();
			fSize = (int)input.length();
			temp.close();	
			} catch (FileNotFoundException x) { System.err.println("File Not Found");} 
		}
		
		// creates a frequency list for all words in a txt file and sorts it
		public void freqList() {
		int tempI;
		String tempS;
		String cont = content.toLowerCase();
		cont = cont.replace(".", "");

		  String[] words = cont.split(" |\\r\\n|\\n|\\r");
		  wordTable = new String[words.length];
		  freq = new int[words.length];
		  int match=0;
		  int index=0;
		  
		  // Counts the number of each word
		  for(int a = 0; a < words.length; a++)
		  {
			  match = 0;
			  for(int b = 0; b <= a & match!=1; b++)
			  {
				  if(words[a].equals(wordTable[b]) && wordTable[b]!=null) // already seen word
				  {
					  match = 1;
					  freq[b]++;
				  }
			  }
			  if(match!=1 && words!=null) // new word
			  { 	  
				  wordTable[index] = words[a];	  
				  freq[index]++;
				  index++;
			  }  
			  
		  }

		 // Sorts words by frequency 
		 for(int k=0; k< wordTable.length && wordTable[k]!=null;k++)
		 { 
		  for(int j =0; j < wordTable.length && wordTable[j+1] != null; j++)
		  {
			  if(freq[j]<freq[j+1])
			  {
				  tempI = freq[j];
				  freq[j] = freq[j+1];
				  freq[j+1] = tempI;
				  tempS = wordTable[j];
				  wordTable[j] = wordTable[j+1];
				  wordTable[j+1] = tempS;
			  }
		  }
		 }
	}
		// Counts the number of characters, words, and sentences in a txt file (requires freqList method to be run first)
		public void countAnalysis(){
			int temp;
			for(int i=0;i<content.length();i++) {
				if(content.charAt(i)=='.')
					 senCount++; 
			}
			for(int j = 0; j<wordTable.length && wordTable[j] !=null; j++)
			{	
				temp = 0;
				for(int k = 0; k<wordTable[j].length();k++) {
				temp++;	
				}
				charCount += (temp*freq[j]);
				wordCount += freq[j];
			}	
		}

		// Creates and returns a string variable that contains the freq list 
		public String printFreqList()
		{
			String List = "Freq List: \n";  
			for(int f=0; f<wordTable.length && wordTable[f] != null;f++) 
				  List += wordTable[f] + " " + freq[f] + "\n";
			return List;
		}
		
		// creates an output file with the Count Statistics written
		public void statsFile(){
			try{
			    PrintWriter writer = new PrintWriter("output.txt");
			    writer.println("Number of Characters: " + charCount);
			    writer.println("Number of Words: " + wordCount);
			    writer.println("Number of Sentances: " + senCount);
			    writer.println("Average Characters per Word: " + (charCount/wordCount));
			    writer.println("Average Words per Sentance" + (wordCount/senCount));
			    writer.close();
			} catch (Exception e) {
			   System.out.println("Error Writing File");
			}
		}
		
		// creates an output file with the freq list written
		public void freqFile() {
			try{
			    PrintWriter writer = new PrintWriter("output.txt");
			    for(int j = 0; j<wordTable.length && wordTable[j] !=null; j++)
			    	writer.println(wordTable[j] + " " + freq[j]);
			    writer.close();
			} catch (Exception e) {
			   System.out.println("Error Writing File");
			}
		}
		
		// creates an output file with both the Count Statistics and freq list written
		public void printAll() {
			try{
			    PrintWriter writer = new PrintWriter("output.txt");
			    writer.println("Number of Characters: " + charCount);
			    writer.println("Number of Words: " + wordCount);
			    writer.println("Number of Sentances: " + senCount);
			    writer.println("Average Characters per Word: " + (charCount/wordCount));
			    writer.println("Average Words per Sentance" + (wordCount/senCount));
			    writer.println("Freq Chart:");
			    for(int j = 0; j<wordTable.length && wordTable[j] !=null; j++)
			    	writer.println(wordTable[j] + " " + freq[j]);
			    writer.close();
			} catch (Exception e) {
			   System.out.println("Error Writing File");
			}
		}
}
