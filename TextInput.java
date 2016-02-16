import java.io.*;
import java.util.Scanner;

public class TextInput {
	
	//TO RECREATE ERROR, wrong input for pokemon choice, the correct, then press yes, forces you to go through again and press yes
	String Question = "";
	String PossibleAnswers = "";
	String Input = "";
	
	public TextInput(String querytext, String Answers){
		Question = querytext;
		PossibleAnswers = Answers;
	}
	
	public String QueryV(){
				
		System.out.println(Question);
		Scanner scan = new Scanner(System.in);
		Input = scan.nextLine(); 

		if(PossibleAnswers != ""){
			if(!PossibleAnswers.contains(Input)){
				System.out.println("Incorrect Input. Attempt re-entry");
				QueryV();
			}
		}
		
		VerifyInput(Input);

		return Input;
		
	}
	
	private String VerifyInput(String input) {
		
		System.out.println("=========================");
		System.out.println("You Entered " + input);
		System.out.println("Is this correct?(y/n)");
		String Verify = "";
		
		Scanner scan = new Scanner(System.in);
		Verify = scan.nextLine(); 

		if(Verify == null || Verify.isEmpty()){
			QueryV();
		}
		if(Verify.charAt(0) == 'n'){
			throw new CustomException("User Declined Input");
		}

		if(Verify.charAt(0) == 'y'){
			return "y";
		}		
		else{
			throw new CustomException("User Declined Input");
		}
	}	
	
	
	public class CustomException extends RuntimeException
	{
	  public CustomException(String message)
	  {
		super(message);
	  }
	}
	
}