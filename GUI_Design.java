import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_Design implements ActionListener { 
	
	// Initialization of GUI components
	fileAnalysis one;
	String file;
	
	JFileChooser choose = new JFileChooser();
	
	JTextArea text = new JTextArea("");
	JTextArea freqList = new JTextArea("");
	JScrollPane scroll = new JScrollPane(text);
	JScrollPane freqScroll = new JScrollPane(freqList);
	
	JCheckBox printStats = new JCheckBox("Write Statistics");
	JCheckBox printFreq = new JCheckBox("Write Freq List");
	JCheckBox printBoth = new JCheckBox("Write Both");
	ButtonGroup print = new ButtonGroup();
	
	Label charNum = new Label("Number of Characters: " );
	Label wordNum = new Label("Number of Words: ");
	Label sentNum = new Label("Number of Sentances: ");
	Label wordSize = new Label("Average Characters per Word: ");
	Label sentSize = new Label("Average Words per Sentance: ");
	
	JButton enter = new JButton("Start");
	JButton chooseButton = new JButton("Choose");
//	JTextField message = new JTextField("Enter Text File");
	
	JFrame frame = new JFrame("Data Analysis");
	JFrame intro = new JFrame("Data Entry");
	
	GUI_Design() {
		
		// Settings for frames
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		freqScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		intro.setVisible(true);
		intro.setLocationRelativeTo(null);
		intro.setSize(600,200);
		intro.setLayout(new FlowLayout());
		frame.setLayout(new GridLayout(10,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// File Entry frame
		//intro.add(message);
		intro.add(chooseButton);
		intro.add(enter);
		
		// Only one check box can be clicked at a time
		print.add(printStats);
		print.add(printFreq);
		print.add(printBoth);
		
		// Data Analysis frame
		frame.add(scroll);
		frame.add(charNum);
		frame.add(wordNum);
		frame.add(sentNum);
		frame.add(wordSize);
		frame.add(sentSize);
		frame.add(freqScroll);
		frame.add(printStats);
		frame.add(printFreq);
		frame.add(printBoth);
		
		// Sets up buttons and check boxes
		enter.addActionListener(this);
		printStats.addActionListener(this);
		printFreq.addActionListener(this);
		printBoth.addActionListener(this);
		chooseButton.addActionListener(this);
	
	}		

	public static void main(String[] args) { 
		GUI_Design tgui = new GUI_Design();
	}
	
	public void actionPerformed(ActionEvent e)  {
		
		// Writes to an output file based on check boxes (output will be last check box clicked)
	    Object source = e.getSource();
	    if (source == printStats) {
	        one.statsFile();
	    } else if (source == printFreq) {
	        one.freqFile();
	    } else if (source == printBoth) {
	        one.printAll();
	    }
	    else if (source == chooseButton){
	    	if(choose.showOpenDialog(intro) == JFileChooser.APPROVE_OPTION) {
	    		file = choose.getSelectedFile().getPath();
	    	}	
	    }	
	    else {
		
		// Reading and Calculation functions for given text file
		one = new fileAnalysis(file);
		one.readFile();
		one.freqList();
		one.countAnalysis();
		// one.debug();
		
		// Analysis of chosen file, adding statistics to the data analysis frame
		frame.setTitle("File - " + file + " Size - " + one.fSize + " Bytes");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(400,800);
		text.setText(one.content);
		charNum.setText("Number of Characters: " + one.charCount);
		wordNum.setText("Number of Words: " + one.wordCount);
		sentNum.setText("Number of Sentances: " + one.senCount);
		wordSize.setText("Average Characters per Word: " + (one.charCount/one.wordCount));
		sentSize.setText("Average Words per Sentance: " + (one.wordCount/one.senCount));
		freqList.setText(one.printFreqList());	
	    }
	}
}