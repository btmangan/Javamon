import java.lang.*;
import java.util.*;

public class Pokeman {
	
	int ReferenceNumber;
	
	//{name, life, attack, defense, special, speed, }
	String[] bulbasaur = {"Bulbasaur", "1.2", "1.0", "1.2", "1.2", "1.0"};
	String[] bulbasaur_moveset = {"Bulbasaur", "0_tackle", "0_leer"};
	

	String[] charmander = {"Charmander", "1.2", "1.0", "1.2", "1.2", "1.0"};
	String[] charmander_moveset = {"Charmander", "0_scratch", "0_growl"};

	String[] squirtle = {"Squirtle", "1.2", "1.0", "1.2", "1.2", "1.0"};
	String[] squirtle_moveset = {"Squirtle", "0_punch", "0_tailwhip"};
	
	String[][] PokemonStats = {bulbasaur, charmander, squirtle};
	String[][] PokemonMoves = {bulbasaur_moveset, charmander_moveset, squirtle_moveset};
	
	
	
	
	
	
	public String[][] ReturnPokeman(String name, int level){ 
		String[] Stats = {};
		String[] Moves = {};
		
		//return stats
		Stats = ReturnStats(name, level);
		
		//return moves
		Moves = ReturnMoves(name, level);
		
		String[][] ReturnValue = {Stats, Moves};
		return ReturnValue;
	}
	
	
	
	
	
	
	public String[] ReturnStats(String name, int level){
		
		ReferenceNumber = -1;
		boolean failure = true;
		
		float[] Stats = {0, 0, 0, 0, 0};
		String[] Stats2 = {"", "", "", "", "", name};
		int Counter = 0;
		int PokemonLength = PokemonStats.length;
		
		float hp;
		float attack;
		float defense;
		float special;
		float speed;
		
		while(Counter < PokemonLength){
			
			if(PokemonStats[Counter][0] == name){				
				hp = Float.parseFloat(PokemonStats[Counter][1]);
				attack = Float.parseFloat(PokemonStats[Counter][2]);
				defense = Float.parseFloat(PokemonStats[Counter][3]);
				special = Float.parseFloat(PokemonStats[Counter][4]);
				speed = Float.parseFloat(PokemonStats[Counter][5]);
				
				Stats[0] = hp;
				Stats[1] = attack;
				Stats[2] = defense;
				Stats[3] = special;
				Stats[4] = speed;
				ReferenceNumber = Counter;
				failure = false;
			}
			Counter++;
		}
		
		if(failure == true){
			System.out.println("FAILED TO DISCOVER POKEMON IN POKEMAN CLASS LOOKUP");
		}
		
		Counter = 0;
		while(Counter < Stats.length){
			Stats[Counter] = (Stats[Counter] * 10) + (Stats[Counter] * level);
			Counter++;
		}
		
		Counter = 0;
		while(Counter < Stats.length){
			Stats2[Counter] = Float.toString(Stats[Counter]);
			Counter++;
		}
		
		return Stats2;		
	}
	
	
	
	
	
	
	
	
	public String[] ReturnMoves(String name, int level){
		
		String[] ReturnMoves = {"empty", "empty", "empty", "empty"};
		int Counter = 0;
		
		if(ReferenceNumber == -1){
			return ReturnMoves;
		}
		int MovesetLen = PokemonMoves[ReferenceNumber].length; 

		while(Counter < MovesetLen){
			if(PokemonMoves[ReferenceNumber][Counter].contains("_")){
				String[] parsedstring = PokemonMoves[ReferenceNumber][Counter].split("_");
				
				if((Integer.parseInt(parsedstring[0])) < level){ 
					ReturnMoves[0] = parsedstring[1];
					ReturnMoves[1] = ReturnMoves[0];
					ReturnMoves[2] = ReturnMoves[1];
					ReturnMoves[3] = ReturnMoves[2];
				}
				
			}
			Counter++;
		}		
		
		return ReturnMoves;
	}	
}