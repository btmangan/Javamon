import java.io.*;


public class TextInput {
	
	//TO RECREATE ERROR, wrong input for pokemon choice, the correct, then press yes, forces you to go through again and press yes
	InputStreamReader ir = new InputStreamReader(System.in);
	String Question = "";
	String PossibleAnswers = "";
	String Input = "";
	
	public TextInput(String querytext, String Answers){
		Question = querytext;
		PossibleAnswers = Answers;
	}
	
	public String QueryV(){
		
		BufferedReader br = new BufferedReader(ir);
		System.out.println(Question);
		
		try{	
			Input = br.readLine();
		}
		catch(Exception e){
			System.out.println("Input Error. Attempt re-entry.");
			QueryV();
		}

		if(PossibleAnswers != ""){
			if(!PossibleAnswers.contains(Input)){
				System.out.println("Incorrect Input. Attempt re-entry");
				QueryV();
			}
		}
		
		VerifyInput(Input);

		return Input;
		
	}
	
	private String VerifyInput(String input){
		
		BufferedReader br = new BufferedReader(ir);		
		System.out.println("=========================");
		System.out.println("You Entered " + input);
		System.out.println("Is this correct?(y/n)");
		String Verify = "";
		
		try{
			Verify = br.readLine();
		}
		catch(Exception e){
			System.out.println("Input Error. Attempt re-entry.");
			QueryV();
		}
		if(Verify == null || Verify.isEmpty()){
			QueryV();
		}
		if(Verify.charAt(0) == 'n'){
			QueryV();
		}

		if(Verify.charAt(0) == 'y'){
			return "y";
		}		
		else{
			VerifyInput(input);
		}
		return "";
	}	
}