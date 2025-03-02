# shuffler

This is a small application was created to shuffle the *.mp3 files by changing their names.  
Since some audio players sort their list of songs by name, each run of this application will generate a new sequense.

## Example:

`Some Artist - Cool Song.mp3` - will be changed to `<UUID>.mp3`, where might be something like - 1431ba06-e13e-430b-a376-27a4f22be9ab.

## How to use:

1) You need to pull it from the git, build it by running a command:  
`java package`  
the output will be a `.jar` file (probably shuffler.jar) 
2) After that, place that `shuffler.jar` file into the root folder on your flash card, next to music folder.  
   - Example: Kingston Flash (G:)  
     - ./.Trash-1000
     - ./music 
     - ./shuffler.jar
     - ./  
3) Run the following command:  
`java -jar shuffler.jar`

## TODO List:
- Make script OS independent.
