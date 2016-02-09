public class DisplayGUI {
	
	String[][] AbstractMap = new String[250][250];
	String[][] RealMap = new String[12][12];
	int[] Coords = new int[2];
	String Activation = "";
	String Interior = "";
	String Exterior = "";
	
	public void DrawMap(String interior, String exterior, int[] coords){
		Interior = interior;
		Exterior = exterior;
		int PlayerX = coords[0];
		int PlayerY = coords[1];
		Activation = "";
		
		//applybase mesh
		for(int i = 0; i < 249; i++){
			for(int j = 0; j < 249; j++){
				AbstractMap[i][j] = "X";
			}
		}
		
		
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
		
		
		
		
		System.out.println("=============================\n=============================");
		//PrintTheMap
		for(int i = 0; i < 11; i++){
			String Line = "";
			for(int j = 0; j < 11; j++){
				Line += (RealMap[j][i] + "  ");
			}
			System.out.println(Line + "\n");
		}
		
	}
	
	
	
	
	
	//AREAS 
	private void House(){ 
	
		//add basic walking area
		for(int i = 10; i < 15; i++){
			for(int j = 10; j < 18; j++){
				AbstractMap[i][j] = " ";
			}
		}
		
		//add doorway
		for(int i = 12; i < 14; i++){
			AbstractMap[i][18] = "0";
		}
	}
	
	private void CuteTown(){ 
		//Home Area
		for(int i=40; i<71; i++){
			for(int j=200; j < 220; j++){
				AbstractMap[i][j] = " ";
			}
		}
		
		//Oak House
		for(int i=50; i<54; i++){
			for(int j=210; j < 214; j++){
				AbstractMap[i][j] = "X";
			}
		}
		//Oak Door
		AbstractMap[52][213] = "0";
		
		
		//Our House
		for(int i=65; i<69; i++){
			for(int j=210; j < 214; j++){
				AbstractMap[i][j] = "X";
			}
		}
		//Our Door
		AbstractMap[67][213] = "0";
	}
	
	
	
	
	
	public int[] PlayerMove(String Direction, int[] Coords){		
		if(Direction.charAt(0) == 'w'){
			if(RealMap[5][4] == "X"){
			}
			else if(RealMap[5][4] == "0"){
				Activation = "Door";
			}
			else{
				Coords[1] -= 1;
			}
		}
		if(Direction.charAt(0) == 's'){
			if(RealMap[5][6] == "X"){
			}
			else if(RealMap[5][6] == "0"){
				Activation = "Door";
			}
			else{
				Coords[1] += 1;
			}
		}
		if(Direction.charAt(0) == 'a'){
			if(RealMap[4][5] == "X"){
			}
			else if(RealMap[4][5] == "0"){
				Activation = "Door";
			}
			else{
				Coords[0] -= 1;
			}
		}
		if(Direction.charAt(0) == 'd'){
			if(RealMap[6][5] == "X"){

			}
			else if(RealMap[6][5] == "0"){
				Activation = "Door";
			}
			else{
				Coords[0] += 1;
			}	
		}
		
		if(Activation == "Door"){
			if(Interior == "OakHouse"){
				Coords[0] = 52;
				Coords[1] = 214;
			}
			if(Interior == " " && Coords[0] == 52 && Coords[1] == 214){
				Coords[0] = 12;
				Coords[1] = 17;
			}
		}
		
		return Coords;
	}	
	public String ActivationCheck(){
		return Activation;
	}
	
	
}