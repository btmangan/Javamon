# Javamon Project #
Creator- Brian Mangan

# How To Run- (For Windows) #
	* Make sure java.exe is in your PATH variable. (Google how to add if unsure)
	* Open Command Prompt. 
	* Navigate to folder containing java files in command prompt
	* type in "javac *.java"
	* if compile is successful, type in "java Pokemon" to run the main file and you are in the game.


# Files- #

#Pokemon.java- This is the main file. #
	* Startup sequence
	* Name entry and Pokemon choice
	* Main callstack, and movement input

# DisplayGUI.java- Handles map generation #
	* This generates map based upon location
	* Also used to check the destination for movement for activation objects and pathing

# Pokeman.java - File contains pokemon data #
	* Generate stats for pokemon based upon multipliers
	* Stats are 10*multipliers for first level, then += multiplier per level

# TextInput.java- Class for handling text input #
	* Text input handled through public method, include acceptable answers and question text
	* Verification method for displaying input to user and asking if it's okay in a y/n