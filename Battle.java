import java.lang.Math;

public class Battle {
	
	//INIT
	boolean End_Conditions = false;
	String[][] PlayerLineup = new String[10][10];
	//enemy Pokemon attributes
	String Pokemon = "";
	int Level = 0;
	int EnemyHealth = 100;
	
	//Our Pokemon attributes
	String PokemonName = "";
	int PokemonLevel = 0;
	int PokemonHealth = 100;
	String Attack1 = "";
	String Attack2 = "";
	String Attack3 = "";
	String Attack4 = "";
	
	double Random;
	double Random2; 

	
	//Enter a Battle Callstack
	public void EnterBattle(){
		
		End_Conditions = false;
		
		//BATTLE CALLSTACK
		while(End_Conditions == false){
			
		}
	}
	
	
	public void WildBattle(String[] EnemyPokemon, boolean Wild, String EnemyMinLvl, String EnemyMaxLvl, String[][] PokemonLineup){
		
		PlayerLineup = PokemonLineup;
		
		//Type Conversions 
		int intEnemyMinLvl = Integer.parseInt(EnemyMinLvl);
		int intEnemyMaxLvl = Integer.parseInt(EnemyMaxLvl);

		//Determine Enemy Level and Pokemon
		Random = Math.random() * 100;
		Random2 = Math.random() * 100;
		
		//META- control structure
		int Counter = 0;
		int PokemonLength = 0;
		int SelectionIndex = 0;

		
		//Determine Length of Enemy Pokemon in array
		while(Counter < EnemyPokemon.length){
			if(EnemyPokemon[Counter] == null || EnemyPokemon[Counter].equals("")){

			}
			else{
				PokemonLength++;
			}
			Counter++;
		}
		Counter = 0;		
		End_Conditions = false;

		
		//Randomly Choses Pokemon from list if Pokemon is wild spawn
		if(Wild == true){
			Pokemon = EnemyPokemon[Counter];
			while( (100/PokemonLength) * (Counter) < Random ){
				if( (Counter + 1) * (100/PokemonLength) >= Random ){
					Pokemon = EnemyPokemon[Counter];
				}
				Counter++;
			}
			
			//determines random pokemon level of wild spawn
			Counter = 0;
			while((100/intEnemyMaxLvl - intEnemyMinLvl) * Counter < Random2 ){
				
				if( (Counter + 1) * (100/intEnemyMaxLvl - intEnemyMinLvl) >= Random2 ){
					Level = (intEnemyMinLvl + Counter);
				}
				Counter++;
			}			
		}
		//Chooses first Pokemon if Pokemon is from enemy trainer
		else{
			Pokemon = EnemyPokemon[SelectionIndex];
			Level = intEnemyMinLvl;
		}
		
		
		//INITIAL BATTLE 
		System.out.println("Enemy " + Pokemon + " Has Appeared! Who will you choose to send out?");
		ChosePokemon();
		System.out.println("I choose you, " + PokemonName);
		
		
		//BATTLE LOOP
		while(End_Conditions == false){
			char Choice = ' ';
			char Attack = ' ';
			System.out.println("ENEMY-"+Pokemon + " Lvl-"+Level + "HP-"+EnemyHealth  );
			System.out.println("YOUR-"+PokemonName + " Lvl-"+PokemonLevel + "HP-"+PokemonHealth);
			System.out.println("What will you do?");
			
			//Stage 1 Player Input
			TextInput ti = new TextInput("Will you... Run(r), Item(i), Attack(a), Switch(s)?::(rias)::", "rias");
			try{
				Choice = ti.QueryV().charAt(0);
			}
			catch(Exception e){
				Choice = ti.QueryV().charAt(0);
			}
			
			//Run 
			if(Choice == 'r'){
				if(Wild == false){
					System.out.println("You Cannot Run From A Trainer Battle!");
					continue;
				}
				else{
					RunAway(Level, PokemonLevel);
					if(End_Conditions == true){
						break;
					}
				}
			}
			
			//Attack
			if(Choice == 'a'){ 
				System.out.println("Choose Attack...");
				ti = new TextInput("1-"+Attack1+"(1)" + " 2-"+Attack2+"(2)" + " 3-"+Attack3+"(3)" + " 4-"+Attack4+"(4)" + " ?::(1234)::", "1234");
				try{
					Attack = ti.QueryV().charAt(0);
				}
				catch(Exception e){
					Attack = ti.QueryV().charAt(0);
				}
			}
			
			if(Wild == true){
				if(EnemyHealth <= 0){
					End_Conditions = true;
				}
			}
			if(Wild == false){
				if(EnemyHealth <= 0){
					if(SelectionIndex < PokemonLength){
						
					}
				}
			}
			
			
		}
	}
	
	
	private char ChosePokemon(){
		
		char PokemonChoice = ' ';
		int LineupIt = 0;
		boolean ValidChoice = false;
		
		while(LineupIt < 6){
			System.out.println("(" + LineupIt + ")" + PlayerLineup[LineupIt][0] + " Lvl:" + PlayerLineup[LineupIt][1] + " Hp%:" + PlayerLineup[LineupIt][2]);
			LineupIt++;
		}
		
		while(ValidChoice == false){
			TextInput ti = new TextInput("Which Pokemon Do You Wish To Choose?::(012345)::", "012345");
			try{
				PokemonChoice = ti.QueryV().charAt(0);
			}
			catch(Exception e){
				PokemonChoice = ti.QueryV().charAt(0);
			}
			if(!(PlayerLineup[Character.getNumericValue(PokemonChoice)][0].equals("empty"))){
				ValidChoice = true;
				int Index = Character.getNumericValue(PokemonChoice);
				PokemonName = PlayerLineup[Index][0];
				PokemonHealth = Integer.parseInt(PlayerLineup[Index][2]);
				PokemonLevel = Integer.parseInt(PlayerLineup[Index][1]);
				Attack1 = PlayerLineup[Index][4];
				Attack2 = PlayerLineup[Index][5];
				Attack3 = PlayerLineup[Index][6];
				Attack4 = PlayerLineup[Index][7];
			}
			else{
				System.out.println("You cannot send out EMPTY to fight");
			}
		}
		
		return PokemonChoice;
		
	}
	
	private void RunAway(int Level, int PokemonLevel){
		int ConditionGate = 100;
		int LevelCheck = PokemonLevel;
		if(PokemonLevel > Level){
			ConditionGate = 100;
		}
		if(PokemonLevel == Level){
			ConditionGate = 95;
		}
		else{
			while(LevelCheck < Level){
				ConditionGate -= 10;
				LevelCheck++;
			}
		}
		
		System.out.println(Random2 + "---" + ConditionGate);
		if(Random2 < ConditionGate){
			End_Conditions = true;
			System.out.println("You Got Away Safely!");
		}
		else{
			System.out.println("Couldn't Escape!");
		}
	}
	
	
}