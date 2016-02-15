import java.io.*;
import java.lang.StringBuilder;
import java.util.Scanner;

public class Pokemon{

	String inputString = "No Input";
	Pokeman pokemanobj = new Pokeman();
	
	//Location Control
	String Interior = "OakHouse";
	String Exterior = "CuteTown";
	
	DisplayGUI dGUI = new DisplayGUI();
	int[] Grid = {12, 12};
	


	String Choice = "";


	String PlayerName = "";
	int intBallCount = 0;
	int intCash = 100;
	String[] inventory = {}; 

	boolean inBattle = false;
	boolean inMenu = false;
	boolean startup = true;

	//ONE-TIME START FUNCTION
	public static void main(String[] args) throws IOException {

		Pokemon pkm = new Pokemon();
		pkm.EntrySequence();
		//Recursive Update starts here
		pkm.pkmCallstack();

	}

	//RECURSIVE UPDATE FUNCTION
	public void pkmCallstack(){
		
		while(true) {
			System.out.println(" Exterior : " + Exterior);
			System.out.println(" Interior : " + Interior + " X-" + Grid[0] + " Y-" + Grid[1]);

			String Input = "";
			//TODO- Make DisplayGUI display a readable abstract map!
			DisplayGUI gui = new DisplayGUI();
			gui.DrawMap(Interior, Exterior, Grid);
			
			Scanner scan = new Scanner(System.in);
			Input = scan.nextLine(); 
			if(Input.trim().length() == 0){
				continue;
			}
			
			
			if(Input.charAt(0) == 'w' || Input.charAt(0) == 'd' || Input.charAt(0) == 'a' || Input.charAt(0) == 's'){
				
				Grid = gui.PlayerMove(Input, Grid);
				
				String[] Activation = new String[7];
				Activation = gui.ActivationCheck();
				
				if(Activation != null && Activation[0] != null){
					if(Activation[0].charAt(0) != ' '){ 
						Interior = Activation[2];
						Grid[0] = Integer.parseInt(Activation[5]);
						Grid[1] = Integer.parseInt(Activation[6]);
					}
				}				
			}
			
			
			
		}
	}

	public void EntrySequence(){
		
		char PokemonChoice = ' ';
		String PokemonName = "";

		String Description1 = new StringBuilder()
			.append("DESC: YOU ARE AN ASPIRING POKEMON TRAINER.\n")
			.append("YOU HAVE BEEN SUMMONED TO SEE PROFESSOR OAK,\n")
			.append("A WELL KNOWN POKEMON EXPERT. YOU ENTER HIS LAB.")
			.toString();
			
		String Meta1 = new StringBuilder()
			.append("Welcome to Brian Mangan's Pokemon ripoff.\n")
			.append("Good luck catching them all and have fun!")
			.toString();
			
		String Description2 = new StringBuilder()
			.append("PROFESSOR OAK PRESENTS YOU WITH 3 POKEBALLS.\n")
			.append("OAK: I know you are an aspiring Pokemon trainer.\n")
			.append("OAK: My collection is small,\n")
			.append("OAK: But I believe you are owed a shot to become a Pokemon trainer.\n")
			.toString();
		
		//Opening Messages
		System.out.println(Meta1);
		System.out.println(Description1);
		
		TextInput ti = new TextInput("What is your name?::", "");
		PlayerName = ti.QueryV();
		
		System.out.println(Description2);
		ti = new TextInput("Will you choose Bulbasaur, Charmander, or Squirtle?::(bcs)::", "bcs");
		PokemonChoice = ti.QueryV().charAt(0);
		
		if(PokemonChoice == 'b'){
			String[][] PokemonReceiver = pokemanobj.ReturnPokeman("Bulbasaur",4);
		}
		if(PokemonChoice == 'c'){
			String[][] PokemonReceiver = pokemanobj.ReturnPokeman("Charmander",4);
		}
		if(PokemonChoice == 's'){
			String[][] PokemonReceiver = pokemanobj.ReturnPokeman("Squirtle",4);
		}
		
		System.out.println("OAK: GOOD LUCK IN YOUR ADVENTURES YOUNG MAN!"); 
		
		return;
	}
}