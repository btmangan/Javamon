import java.lang.Math;

public class Battle {
	
	//INIT
	boolean End_Conditions = false;
	
	
	//Enter a Battle Callstack
	public void EnterBattle(){
		
		End_Conditions = false;
		
		//BATTLE CALLSTACK
		while(End_Conditions == false){
			
		}
	}
	
	
	public void WildBattle(String[] EnemyPokemon, boolean Wild, String EnemyMinLvl, String EnemyMaxLvl, String[][] PokemonLineup){
		
		//Type Conversions 
		int intEnemyMinLvl = Integer.parseInt(EnemyMinLvl);
		int intEnemyMaxLvl = Integer.parseInt(EnemyMaxLvl);

		//enemy Pokemon attributes
		String Pokemon = "";
		int Level = 0;
		int EnemyHealth = 100;

		//Determine Enemy Level and Pokemon
		double Random = Math.random() * 100.00;
		double Random2 = Math.random() * 100.00;
		
		//control structure
		int Counter = 0;
		int PokemonLength = EnemyPokemon.length;
		int SelectionIndex = 0;
		
		End_Conditions = false;

		
		//Randomly Choses Pokemon from list if Pokemon is wild spawn
		if(Wild == true){
			while( (100/PokemonLength) * Counter < 100 ){
				if( (Counter + 1) * (100/PokemonLength) >= 100 ){
					Pokemon = EnemyPokemon[Counter];
				}
				Counter++;
			}
			
			//determines random pokemon level of wild spawn
			Counter = 0;
			while((100/intEnemyMaxLvl - intEnemyMinLvl) * Counter < 100 ){
				
				if( (Counter + 1) * (100/intEnemyMaxLvl - intEnemyMinLvl) >= 100 ){
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
		
		System.out.println("Enemy " + Pokemon + " Has Appeared! Who will you choose to send out?");
		
		int LineupIt = 0;
		while(LineupIt < 6){
			System.out.println("(" + LineupIt + ")" + PokemonLineup[LineupIt][0] + " Lvl:" + PokemonLineup[LineupIt][1] + " Hp%:" + PokemonLineup[LineupIt][2]);
			LineupIt++;
		}
		
		while(End_Conditions == false){
			
			
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
	
	
}