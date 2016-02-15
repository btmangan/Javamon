public class DisplayGUI {
	
	//Build an interior/exterior class, that maps the exterior
	//the interiors that fall under that exterior, 
	//each doorway (grid, interior, exterior), and the target (g,i,e) for that doorway
	
	//Glossary-
	//1.0 DRAW MAP MAIN FUNCTION
	//2.0 DRAW FUNCTIONS
	//3.0 WALK FUNCTIONALITY
	//4.0 ACTIVATION RETURN (returns the nature of the activation (doors, etc?))
	
	
	
	//SECTION 0.0- INIT 
	//Location Arrays
	//--------------------------------------------------------------
	//The Abstract map is a map of the entire exterior layed out in a 2 dimensional array
	//The Real map is all of the values that surround the player's position (Coords)
	//When the player moves, the player's new Coords are checked against 
	//the abstract map to create the real map
	String[][] AbstractMap = new String[250][250];
	String[][] RealMap = new String[12][12];
	int[] Coords = new int[2];
	
	
	//Location Values
	//--------------------------------------------------------------
	//The Word is made up of Exteriors
	//Being Outside in an Exterior is indicated by an interior of " "
	//Any other value but " " as an Interior means you are inside
	String Interior = "";
	String Exterior = "";

	//Door Wiring, ["Exterior", "Current Interior", "Target Interior", "currentx", "currenty", "targetx", "targety"];
	String[][] DoorArray = {
		{"CuteTown", "OakHouse", " ", "12", "18", "52", "214"}, //oakhouseexit
		{"CuteTown", " ", "OakHouse", "52", "213", "12", "17"}, //oakhouseentry
		{"CuteTown", " ", "OurHouse", "58", "213", "12", "17"}, //ourhouseentry
		{"CuteTown", "OurHouse", " ", "12", "18", "58", "214"}	//ourhouseexit
	};
	String[] Activation = new String[7];
	
	
	//Wild Pokemon Wiring ("Exterior","Interior",      "Minx", "Miny", "Maxx", "Maxy",    "minlevel", "maxlevel",     "Pokemon", "Pokemon", etc...)
	String[][] WildPokemon = {
		{"CuteTown", " ",      "45", "217", "50", "220",      "2","3",      "Pidgey", "Ratrat"}
	};	
	String[] WildDangerCheck = {"0", "", "", "", "", "", "", "", "", "", ""};
	
	
	//SECTION 1.0- DRAWMAP
	//This function is called from the main class (Pokemon) from within a recursive loop
	//The Map is drawn each loop
	//================================================================
	public void DrawMap(String interior, String exterior, int[] coords){
		Interior = interior;
		Exterior = exterior;
		int PlayerX = coords[0];
		int PlayerY = coords[1];
		Activation = new String[7];
		String[] WildDangerCheck = {"0", "", "", "", "", "", "", "", "", "", ""};
		
		//applybase mesh
		DrawSquare("X", 250, 250);
		
		
		//ASSIGNrealmapcoordinates based upon player location
		int Counter = 0;
		int Counter2 = 0;
		int Counterx = PlayerX;
		int Countery = PlayerY;
		
		//Check to see if player is in an interior to begin wtih
		if(interior != " "){
			
			//interior- house
			if(interior.contains("House")){
				House();
			}			
		}
		else{ 
			//exterior- cutetown
			if(exterior == ("CuteTown")){
				CuteTown();
			}
		}
		
		
		
		//TRANSLATE ABSTRACT MAP TO PLAYERMAP BASED UPON PLAYER POSITION
		//The Y Loop
		while(Counter < 11){
			
			Counter2 = 0;
			
			//The X Loop
			while(Counter2 < 11){
				RealMap[Counter2][Counter] = AbstractMap[(PlayerX - 5) + Counter2][(PlayerY - 5) + Counter];
				Counter2++; 
				
			}
			Counter++;
		}
		
		
		
		//DrawPlayer
		RealMap[5][5] = "!";
		
		
		
		
		System.out.println("===============================\n===============================");
		//PrintTheMap
		for(int i = 0; i < 11; i++){
			String Line = "";
			for(int j = 0; j < 11; j++){
				Line += (RealMap[j][i] + "  ");
			}
			System.out.println(Line + "\n");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	//SECTION 2.0- AREAS Draw Control
	//===========================================================
	
	private void House(){ 
	
		//add basic walking area
		DrawSquareMinMax(" ", 10, 10, 15, 18);
		//add doorway
		DrawPoint("0", 12, 18);
		
	}
	
	private void CuteTown(){ 
	
	
	//AREA CLEARING
		
		//Home Area
		DrawSquareMinMax(" ", 45, 205, 65, 220);
		
		
		
	//HOUSES
		
		//Oak House
		DrawSquareMinMax("X", 50, 210, 54, 214);
		//Oak Door
		DrawPoint("0", 52, 213);
		//Our House
		DrawSquareMinMax("X", 56, 210, 60, 214);
		//Our Door
		DrawPoint("0", 58, 213);
		
		
	//POKEMON FIELDS
		
		//Oak House Field
		DrawSquareMinMax("-", 45, 217, 50, 220);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//SECTION 2.5- DRAWHELPERS
	//======================================================
	private void DrawSquare(String Type, int MaxX, int MaxY){
		for(int i=0; i<MaxY; i++){
			for(int j=0; j<MaxX; j++){
				AbstractMap[j][i] = Type;
			}
		}
		return;
	}
	
	private void DrawSquareMinMax(String Type, int x, int y, int xM, int yM){
		for(int i=y; i<yM; i++){
			for(int j=x; j<xM; j++){
				AbstractMap[j][i] = Type;
			}
		}
		return;
	}
	
	private void DrawPoint(String Type, int x, int y){
		AbstractMap[x][y] = "0";
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//SECTION 3.0
	//WALKING FUNCTIONALITY
	public int[] PlayerMove(String Direction, int[] Coords){
				
		if(Direction.charAt(0) == 'w'){
			if(RealMap[5][4] == "X"){
			}
			else if(RealMap[5][4] == "0"){
				Activation = SearchDoorList(Coords[0], Coords[1]-1);
			}
			else{
				Coords[1] -= 1;
				if(RealMap[5][4] == "-"){
					WildPokemonFieldSearch(Coords[0], Coords[1]);
				}
			}
		}
		if(Direction.charAt(0) == 's'){
			if(RealMap[5][6] == "X"){
			}
			else if(RealMap[5][6] == "0"){
				Activation = SearchDoorList(Coords[0], Coords[1]+1);
			}
			else{
				Coords[1] += 1;
				if(RealMap[5][6] == "-"){
					WildPokemonFieldSearch(Coords[0], Coords[1]);
				}
			}
		}
		if(Direction.charAt(0) == 'a'){
			if(RealMap[4][5] == "X"){
			}
			else if(RealMap[4][5] == "0"){
				Activation = SearchDoorList(Coords[0]-1, Coords[1]);
			}
			else{
				Coords[0] -= 1;
				if(RealMap[4][5] == "-"){
					WildPokemonFieldSearch(Coords[0], Coords[1]);
				}
			}
		}
		if(Direction.charAt(0) == 'd'){
			if(RealMap[6][5] == "X"){

			}
			else if(RealMap[6][5] == "0"){
				Activation = SearchDoorList(Coords[0]+1, Coords[1]);
			}
			else{
				Coords[0] += 1;
				if(RealMap[6][5] == "-"){
					WildPokemonFieldSearch(Coords[0], Coords[1]);
				}
			}	
		}
		return Coords;
	}	
	
	private String[] SearchDoorList(int x, int y){
				
		String[] Result = new String[7];
		int Counter = 0;
		
		
		while(Counter < DoorArray.length){
			
			String Str_x = (x + "").trim();
			String Str_y = (y + "").trim();
			
			if(DoorArray[Counter][0] == Exterior){  //checks to see if door trigger has same exterior
				if(DoorArray[Counter][1] == Interior){ //checks to see if door trigger has same interior
					if(DoorArray[Counter][3].equals(Str_x)){  
						if(DoorArray[Counter][4].equals(Str_y)){
							Result = DoorArray[Counter];
							System.out.println(Interior + " To " + DoorArray[Counter][2] + " " + x + "," + y);
						}
					}
					else{
						System.out.println(DoorArray[Counter][3] + " Does Not Equal " + Str_x);
						System.out.println(DoorArray[Counter][3].equals(Str_x));
					}
				}
			}
			
			Counter++;
			
			
		}
		return Result;
	}
	
	
	
	private void WildPokemonFieldSearch(int x, int y){
		
		int Counter = 0;
		
		String Str_x = (x + "").trim();
		String Str_y = (y + "").trim();
		
		
		while(Counter < WildPokemon.length){
			
			int SubArrayLength = WildPokemon[Counter].length;
			int Counter8 = 8;
			
			int Min_x = Integer.parseInt(WildPokemon[Counter][2]) -1;
			int Max_x = Integer.parseInt(WildPokemon[Counter][4]);
			int Min_y = Integer.parseInt(WildPokemon[Counter][3]) -1;
			int Max_y = Integer.parseInt(WildPokemon[Counter][5]);
			
			if(WildPokemon[Counter][0].equals(Exterior)){
				if(WildPokemon[Counter][1].equals(Interior)){
					if(x > Min_x){
						if(y > Min_y){
							if(x < Max_x){
								if(y < Max_y){
									
									System.out.println("hit");
									WildDangerCheck[0] = WildPokemon[Counter][6];
									WildDangerCheck[1] = WildPokemon[Counter][7];
									
									//Generic Pokemon
									while(Counter8 < SubArrayLength){
										WildDangerCheck[Counter8-6] = WildPokemon[Counter][Counter8];
										Counter8++;
									}
									
									
									
								}
							}
						}
					}
				}
			}
			
			
			
			Counter++;
		}	
	}
	
	
		
	
	
	
	//SECTION 4.0
	//ACTIVATION PULL 
	public String[] ActivationCheck(){
		return Activation;
	}
	
	public String[] WildDangerCheck(){		
		return WildDangerCheck;
	}
	
	
}