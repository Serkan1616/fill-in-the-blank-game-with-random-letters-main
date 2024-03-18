
import java.io.BufferedWriter;
import java.io.File;   
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;   
import java.util.Random;

public class Game {
	

	public static void main(String[] args) throws IOException {
		
	 Stack S1=new Stack(500);
	 Stack S2=new Stack(500);
	 Stack S3 = new Stack(500);	
	 Stack S4 = new Stack(500);
	 
	 Stack S1_temp = new Stack(500);
	 Stack S2_temp = new Stack(500);
	 Stack S3_temp=new Stack(500);
	 Stack S4_temp=new Stack(500);
	 
	 Queue Q1 = new Queue(5000); 
	 Queue Q2=  new Queue(5000);
	 
	 Stack HighScoreTableNames=new Stack(500);
	 Stack HighScoreTableScores=new Stack(500);
	 
	 
	 Scanner scanner=new Scanner(System.in);
	 System.out.println("Please enter your name: ");
	 
	 String user=scanner.nextLine();
	 
     read_countries_file(S1_temp,"countries.txt");
    

   //S2 push elements
   //------------------------------------------------------------------- 
     int asciiValue = 65;
     for(int i = asciiValue; i <= 90; i++){
         String convertedChar = Character.toString(i);
         S2_temp.push(convertedChar);
     }
     while(!(S2_temp.isEmpty())) {
    	 
    	 S2.push(S2_temp.pop());
     }   
   //-------------------------------------------------------------------   
    
     
     
    
	 
	//Sorted countries
	//-------------------------------------------------------------------- 
	boolean flag=true;
	String firstword="ZZZZZZZ";
	Stack tempstack = new Stack(500);
	String word=null;
	String Targetword=null;
	int len=0;
	int sizenumber=S1_temp.size();
	
	while(!(S1.size()==sizenumber)){
	firstword="ZZZZZZZ";	
	
	while(!S1_temp.isEmpty()) {
		
		word=(String) S1_temp.peek();
		tempstack.push(S1_temp.pop());
		
		if(firstword.length()>=word.length()) {
			len=firstword.length();
		}
		else
			len=word.length();
		
		for(int i=0;i<len;i++) {			
			if(firstword.charAt(i)>word.charAt(i)) {
				firstword=word;
				flag=false;
				break;
			}
			else if(firstword.charAt(i)<word.charAt(i)) {
				flag=false;
				break;
			}											
		}
		
		
	}
	

	while (! (tempstack.isEmpty())) {
		Targetword=(String) tempstack.peek();
		if(Targetword.equals(firstword)) {
			tempstack.pop();
		}
		else
			S1_temp.push(tempstack.pop());			
	}
	
	S1.push(firstword);
	
	}
	//--------------------------------------------------------------------
     
	
	// Randomly selected country
	//--------------------------------------------------------------------
	
	Random r=new Random(); //random class
	int random_number=r.nextInt(sizenumber)+1;
     				
	String Find_selected_word=null;
	int Find_word=0;
	Stack temps =new Stack(500);
	
	int random_number2=S1.size()-random_number+1;
	int number=0;
	while(!(S1.isEmpty())) {
		number++;
		if(number==random_number2) {
			Find_selected_word=(String) S1.peek();
		}
		
		temps.push(S1.pop());
		
	}
	
	while(!(temps.isEmpty())) {
		S1.push(temps.pop());
		
	}
	
	
	//--------------------------------------------------------------------
	
	System.out.println("Randomly generated number: "+random_number2);
	
	
	//take the selected word and put in Q1,create Q2
	//--------------------------------------------------------------------
	for(int i=0;i<Find_selected_word.length();i++) {
		
		Q1.enqueue(Find_selected_word.charAt(i));
	}
	
	for(int i=0;i<Q1.size();i++) {
		
		Q2.enqueue('-');
	}
	System.out.println("");
	System.out.print("Word: ");
	for(int i = 0; i <Q1.size(); i++) {
		System.out.print(Q2.peek() + " ");
		Q2.enqueue(Q2.dequeue());
	}
	
	
	//--------------------------------------------------------------------
	boolean game_start=true;
	int score=0;
	int step=1;
		
	while(game_start) {
		String random_letter=null;
		int random_letter_order=r.nextInt(S2.size())+1;
		String letter=null;
		
		//Random letter and print S2
		//--------------------------------------------------------------------
		if(step==1) {
			System.out.print("\tstep:1 \t"+"score:"+score+"        ");
		}
		step++;
        while(!(S2.isEmpty())) {
			
			System.out.print(S2.peek());
			S2_temp.push(S2.pop());
		}
		while(!(S2_temp.isEmpty())) {
			S2.push(S2_temp.pop());
		}
		

		
         while(!(S2.isEmpty())) {
			
			random_letter_order--;
			if(random_letter_order==0) {
				random_letter=(String) S2.peek();
			}
			
			S2_temp.push(S2.pop());
		}
		
		while(!(S2_temp.isEmpty())) {
			letter=(String) S2_temp.peek();
			if(random_letter.equals(letter)) {
				S2_temp.pop();
			}
			else
				S2.push(S2_temp.pop());
				
	}
		
		System.out.println("");
    //--------------------------------------------------------------------
		String wheel= Wheel();
		System.out.println("Wheel: "+wheel);
		
		System.out.println("Quess: "+random_letter);
		
		
		
	 //find the letters in q2 and display
	 //--------------------------------------------------------------------
		int count_letter=0;
	   char matching_letter=' ';
	   char char_random_letter=random_letter.charAt(0);
		for(int i=0;i<Q1.size();i++) {
			
			matching_letter=(char) Q1.peek();
			
			if(char_random_letter==matching_letter) {
				Q2.dequeue();
				Q2.enqueue(matching_letter);
				count_letter++;
			}
			else
				Q2.enqueue(Q2.dequeue());
			Q1.enqueue(Q1.dequeue());
		}
		
		System.out.print("Word: ");
		for(int i = 0; i <Q1.size(); i++) {
			System.out.print(Q2.peek() + " ");
			Q2.enqueue(Q2.dequeue());
		}
		//--------------------------------------------------------------------
		
		//Wheel and add score
		 //--------------------------------------------------------------------
		if(wheel.equals("10")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("50")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("100")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("250")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("500")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("1000")&&count_letter!=0) {
			int i=Integer.parseInt(wheel);  
			score=score+i*count_letter;
		}
		else if(wheel.equals("Double Money")&&count_letter!=0) {
			 
			score=score*2;
		}
		else if(wheel.equals("Bankrupt")) {
			score=0;
		}
		
		
		//display space
		String space="";
		if(score<10) {
			space="        ";
		}
		else if(score>=10&&score<100) {
			space="       ";
		}
		else if(score>=100&&score<1000) {
			space="      ";
		}
		else if(score>=1000&&score<10000) {
			space="     ";
		}
		else
			space="    ";
		
		
		System.out.print("\tstep:"+step+"\tscore:"+score+space);		
	 //--------------------------------------------------------------------
		
	//checking game is over 
    //--------------------------------------------------------------------
	boolean checking_game_is_over=true;
		char check=' ';
		for(int i = 0; i <Q2.size(); i++) {
			check=(char) Q2.peek();
			if(check=='-') {
				checking_game_is_over=false;
			}
			Q2.enqueue(Q2.dequeue());
		}
		if(checking_game_is_over) {
			game_start=false;
			System.out.println(" ");
			System.out.println(" ");
		}
		
	}
	System.out.println("You Win $"+score);
	System.out.println("");
		
		
	
	//Read HighScoreTable put in stacks
    //-------------------------------------------------------------------   
     String fileName="HighScoreTable.txt";
     Path HighScoreTablePath = Paths.get(fileName);											
	 Scanner HighScoreTable = new Scanner(HighScoreTablePath);	
	 int ColumnCount=2;
	 //counting the questions file lines for arrays rows.
	 int RowCount = (int)Files.lines(HighScoreTablePath).count();
	 //creating 2d array for questions.															
	 String[][] arrayHighScoreTable = new String[RowCount][ColumnCount];	
     
	 while (HighScoreTable.hasNextLine()) {						
			for(int i=0;i<arrayHighScoreTable.length;i++) {
				String[] line = HighScoreTable.nextLine().trim().split(" ");
				for (int j=0; j<line.length; j++) {
					arrayHighScoreTable[i][j] =line[j];					               							
				}
			}
		}	 
	 for(int i=0;i<arrayHighScoreTable.length;i++) {
		 
		 S3_temp.push(arrayHighScoreTable[i][0]);
		 S4_temp.push(arrayHighScoreTable[i][1]);
	 }
	 //--------------------------------------------------------------------
	 
	 
	 //Sort High Score Table
	 //--------------------------------------------------------------------
		
	int sizenumberS3=S3_temp.size()+1;
	int sizenumberS4=S4_temp.size()+1;
	
	int first_score=0;
	String first_name=null;
	String inside_stack_score=null;
	int inside_stack_score_int=0;
	String inside_stack_name=null;
	boolean flag_score=true;
	
	Stack tempstackS3 = new Stack(500);
	Stack tempstackS4 = new Stack(500);
		
	while(!(S3.size()==sizenumberS3&&S4.size()==sizenumberS4)) {
		
		
		first_score=0;
		first_name=null;
		
		while(!(S3_temp.isEmpty()&&S4_temp.isEmpty())) {
			
			inside_stack_score=(String) S4_temp.peek();
			inside_stack_score_int = Integer.parseInt(inside_stack_score);
			inside_stack_name=(String) S3_temp.peek();
			tempstackS4.push(S4_temp.pop());
			tempstackS3.push(S3_temp.pop());
			
			if(inside_stack_score_int>=first_score) {
				first_score=inside_stack_score_int;
				first_name=inside_stack_name;
			}
					
		}
		if(score>=first_score&&flag_score) {
			flag_score=false;
			
		while (! (tempstackS4.isEmpty()&&tempstackS3.isEmpty())) {
			
			S3_temp.push(tempstackS3.pop());
			S4_temp.push(tempstackS4.pop());						
		}
		S3.push(user);
		S4.push(score);
			
		}
		else {
			while (! (tempstackS4.isEmpty()&&tempstackS3.isEmpty())) {
				String Targetscore=(String) tempstackS4.peek();
				int Targetscore_int=Integer.parseInt(Targetscore);
				String Targetname=(String) tempstackS3.peek();
				if(Targetscore_int==first_score&&first_name.equals(Targetname)) {
					tempstackS4.pop();
					tempstackS3.pop();
				}
				else {
					S3_temp.push(tempstackS3.pop());
					S4_temp.push(tempstackS4.pop());
				}
						
			}
			
			S3.push(first_name);
			S4.push(first_score);
			
		}		
	}
	 //--------------------------------------------------------------------
    
   
	
	//add to file the user
	//--------------------------------------------------------------------
	
	  String user_score_string=String.valueOf(score);
	  String user_name_and_score_String=user+" "+user_score_string;	     	     		
      Path path = Paths.get("HighScoreTable.txt");
	  appendToFile(path,user_name_and_score_String + NEW_LINE);
	//--------------------------------------------------------------------
	
	      
	      
	      
	//print High Score Table
	 //-------------------------------------------------------------------
	System.out.println("High Score Table");
	int top_10_counter=0;
	
	while(!(S3.isEmpty())) {
		
		HighScoreTableNames.push(S3.pop());
		HighScoreTableScores.push(S4.pop());
	}
	while(!(HighScoreTableNames.isEmpty())){
		if(!(top_10_counter>=10)) {
			top_10_counter++;
			System.out.print(HighScoreTableNames.pop()+" ");
			System.out.print(HighScoreTableScores.pop());
			System.out.println(" ");			
		}
		else {
			HighScoreTableNames.pop();
			HighScoreTableScores.pop();
		}
		
		
				
		
	}
	//thread ekle tabloyu hizala 
	//--------------------------------------------------------------------
									
	}
	
	 private static final String NEW_LINE = System.lineSeparator();

	
	 private static void appendToFile(Path path, String content)
				throws IOException {    
     Files.write(path, content.getBytes(StandardCharsets.UTF_8),
             StandardOpenOption.CREATE,
             StandardOpenOption.APPEND);

 }

	public static Object read_countries_file(Stack inputstack,String inputtxt) {						
		 try {  
	            // Creat f1 object of the file to read data  
	            File f1 = new File(inputtxt);    
	            Scanner dataReader = new Scanner(f1);  
	            while (dataReader.hasNextLine()) {  	            	
	            	inputstack.push(dataReader.nextLine().toUpperCase(Locale.ENGLISH));                
	            }  
	            dataReader.close();  
	        } catch (FileNotFoundException exception) {  
	            System.out.println("Unexcpected error occurred!");  
	            exception.printStackTrace();  
	        }  						
		return  inputstack;
	}
	
	public static String Wheel() {
		Random r=new Random(); //random class
		int random_number=r.nextInt(8)+1;
		String result=null;
		
		
		switch(random_number) {
		
		case 1:
			result="10";
			
			
			break;
		case 2:
			result="50";
			
			break;
		case 3:
			result="100";
			
			
		case 4:
			result="250";
				
			
			break;
		case 5:
			result="500";
				
			
			break;
		case 6:
			result="1000";
			
			
		case 7:
			result="Double Money";
					
			break;
		case 8:
			result="Bankrupt";
			
			break;
		}
		
		
		return result;
	}
	
	
}

