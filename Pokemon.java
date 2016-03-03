import java.io.*;
import java.lang.StringBuilder;
import java.util.Scanner;
import java.lang.Math;

public class Pokemon{
	//TODO - 
	//	-FIX LEER SPAM ON MOVE ASSIGNMENT (IN POKEMAN)

	String inputString = "No Input";
	Pokeman pokemanobj = new Pokeman();
	
	//Location Control
	String Interior = "OakHouse";
	String Exterior = "CuteTown";
	
	DisplayGUI dGUI = new DisplayGUI();
	int[] Grid = {12, 12};
	

	//For Saving Input Text Values
	String Choice = "";
	
	//Player Danger Iterator
	int intDanger = 0;

	//Player Inventory Values
	String PlayerName = "";
	int intBallCount = 0;
	int intCash = 100;
	
	//PokemonName, PokemonLevel, PokemonHealthPercentage, conditionaleffects, move1, move2, move3, move4
	String[][] PokemonLineup = {
		{"empty", "level", "health", "effects", "move", "move", "move", "move"},
		{"empty", "level", "health", "effects", "move", "move", "move", "move"},
		{"empty", "level", "health", "effects", "move", "move", "move", "move"},
		{"empty", "level", "health", "effects", "move", "move", "move", "move"},
		{"empty", "level", "health", "effects", "move", "move", "move", "move"},
		{"empty", "level", "health", "effects", "move", "move", "move", "move"}
	};
	String Move1 = "";
	String Move2 = "";
	String Move3 = "";
	String Move4 = "";
	
	
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
		
		String[] WildPokemonList = new String[10]; 
		
		while(true) {
			System.out.println("===============================");
			System.out.println(" Exterior : " + Exterior + " X-" + Grid[0] + " Y-" + Grid[1]);
			System.out.println(" Interior : " + Interior);

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
				
				//Return player move grid depending on obstacle
				Grid = gui.PlayerMove(Input, Grid);
				
				//init for door activation
				String[] Activation = new String[7];
				Activation = gui.ActivationCheck();
				
				//Check Activation of Door
				if(Activation != null && Activation[0] != null){
					if(Activation[0].charAt(0) != ' '){ 
						Interior = Activation[2];
						Grid[0] = Integer.parseInt(Activation[5]);
						Grid[1] = Integer.parseInt(Activation[6]);
					}
				}
				
				//Return array of wilddangercheck(tall grass)
				String[] DangerCheck = gui.WildDangerCheck();
				System.out.println(DangerCheck[0]);
				if(DangerCheck[0] != "0"){
					intDanger++;
					if(intDanger >= 8){
						
						//Create Array of EnemyPokemon In Wild Grass
						int Counter = 2;
						while(DangerCheck[Counter] != ""){
							WildPokemonList[Counter-2] = DangerCheck[Counter];
							System.out.println(DangerCheck[Counter]);
							Counter++;
						}
						
						//initialize tall grass battle
						Battle btl = new Battle();
						btl.WildBattle(WildPokemonList, true, DangerCheck[0], DangerCheck[1], PokemonLineup);
						intDanger = 0;
						
					}
				}
				
			}
			
			
			
		}
	}
	
	

	public void EntrySequence(){
		
		char PokemonChoice = ' ';
		String PokemonName = "";
		String[][] PokemonReceiver = pokemanobj.ReturnPokeman("Bulbasaur",4);

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
		
		
		
		
		//NAME INPUT DIALOG
		TextInput ti = new TextInput("What is your name?::", "");
		try{
			PlayerName = ti.QueryV();
		}
		catch(Exception e){
			PlayerName = ti.QueryV();
		}
		
		
		//POKEMON CHOICE DIALOGUE
		System.out.println(Description2);
		ti = new TextInput("Will you choose Bulbasaur, Charmander, or Squirtle?::(bcs)::", "bcs");
		try{
			PokemonChoice = ti.QueryV().charAt(0);
		}
		catch(Exception e){
			PokemonChoice = ti.QueryV().charAt(0);
		}
		
		
		
		//Assigns pokemon based upon choice
		if(PokemonChoice == 'b'){
			PokemonReceiver = pokemanobj.ReturnPokeman("Bulbasaur",4);
		}
		if(PokemonChoice == 'c'){
			PokemonReceiver = pokemanobj.ReturnPokeman("Charmander",4);
		}
		if(PokemonChoice == 's'){
			PokemonReceiver = pokemanobj.ReturnPokeman("Squirtle",4);
		}
		
		AddPokemon(PokemonReceiver[0][5], "3", "100", "", PokemonReceiver[1]);
		System.out.println(PokemonLineup[0][0] + PokemonLineup[0][1] + PokemonLineup[0][2] + PokemonLineup[0][3] + PokemonLineup[0][4] + PokemonLineup[0][5] + PokemonLineup[0][6] + PokemonLineup[0][7]); //debug inventory add
		System.out.println("OAK: GOOD LUCK IN YOUR ADVENTURES YOUNG MAN!"); 
		
		return;
	}
	
	
	
	
	
	//ADDING POKEMON TO INVENTORY HANDLED HERE
	//=================================================
	private void AddPokemon(String Name, String Level, String Health, String Effects, String[] Moveset){
		
		int PokemonLevel = Integer.parseInt(Level);
		int Index = FirstEmpty();
		
		int MoveSetLength = Moveset.length;
		
		if(Index == -1){
			TextInput ti = new TextInput("Which Pokemon Do You Wish To Discard?::(0,1,2,3,4,5)", "012345");
			Index = ti.QueryV().charAt(0) -'0';
		}
		
		PokemonLineup[Index][0] = Name;
		PokemonLineup[Index][1] = Level;
		PokemonLineup[Index][2] = Health;
		PokemonLineup[Index][3] = Effects;
		
		PokemonLineup[Index][4] = Moveset[0];
		PokemonLineup[Index][5] = Moveset[1];
		PokemonLineup[Index][6] = Moveset[2];
		PokemonLineup[Index][7] = Moveset[3];
	}
	private int FirstEmpty(){ 		//Extension of above function
									
		int Counter = 0;			//Determines the index of the first empty slot in a player's inventory
		while(Counter < 6){			//-1 if there are no empty slots
			if(PokemonLineup[Counter][0].equals("empty")){
				return Counter;
			}
			Counter++;
		}
		
		Counter = -1;
		return Counter;
		
	}
	
	
}