# Kindle_Contents_Exporter

#### Repository that allows you to export; 1. Notes and 2. Vocab-Builder directly from the Amazon Kindle to a Word Document (.docx). Although relatively simple in nature it is a very convenient way of storing and viewing your Kindle content. 

#### Sample of Exported Kindle Notes (Notes): 

![ExportedKindleNotes](https://user-images.githubusercontent.com/36043248/112213886-7b41a180-8c16-11eb-816e-910bdc803f36.PNG)

#### Sample of Exported Kindle Vocab-Builder (Notes): 

![Website](https://user-images.githubusercontent.com/36043248/104812522-b381c980-57fa-11eb-886d-c49ff65a9087.PNG)

-------------------------------------------------------------------------------------------------------------------------------

1. Open IntelliJ IDE and create a new Java project 
2. Clone the repository and add the two files 'exportNotes.java' and 'exportVocabBuilder.java' to the source file in the project folder

## Configuring 'exportNotes.java'
3. Download the following zip files @ https://bit.ly/3rkiewF
4. Navigate to 'File' -> 'Project Stucture' -> 'Modules' -> '+' to create a new project library. Add all the .jar files within the 'Apache POI' folder click 'Apply'. For Help: https://youtu.be/aPr-R-Ue0Ew
5. Navigate to 'File' -> 'Project Stucture' -> 'Modules' -> '+' to create a new project library. Add all the .jar files within the 'Spire XLS' folder click 'Apply' i.e. just 'Spire.Xls.jar'

![ProjectStructureLibraries](https://user-images.githubusercontent.com/36043248/112219480-f5752480-8c1c-11eb-98eb-db572665a4a9.PNG)

## Configuring 'exportVocabBuilder.java'
6. Download Kindle Mate @ https://kmate.me/download/
7. Open the app, connect your Kindle and navigate to the 'Vocabulary Words' -> 'Learning'. You will now see all the words that exist in your Kindle Vocab Builder

![KindleMateSetup_1](https://user-images.githubusercontent.com/36043248/112213614-2d2c9e00-8c16-11eb-8e76-e4ca32eacb9e.PNG)

8. Highlight the entire table (CTRL+A) and press 'F12'. This will retrieve the definition for every word in the table
9. Highlight the entire table again after retriving the defintions and export the data to a file named 'Kindle_VocabBuilder_Initial.xlsx' (store in same location as Apache POI folder i.e. in project folder)

## Finalisation
10. Simply run 'exportNotes.java' followed by 'exportVocabBuilder.java'. Two files will be outputted in the current directory.

N.B: Ensure the path of the 'kindleInputFile' variable in 'exportNotes.java' is correct
