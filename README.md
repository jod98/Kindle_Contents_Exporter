# Kindle_Contents_Exporter

#### Repository that allows you to export; 1. Notes and 2. Vocab-Builder directly from the Amazon Kindle to a Word Document (.docx). Although relatively simple in nature it is a very convenient way of storing and viewing your Kindle content. 

#### Sample of Exported Kindle Notes (Notes): 

![Website](https://user-images.githubusercontent.com/36043248/104812522-b381c980-57fa-11eb-886d-c49ff65a9087.PNG)

#### Sample of Exported Kindle Vocab-Builder (Notes): 

![Website](https://user-images.githubusercontent.com/36043248/104812522-b381c980-57fa-11eb-886d-c49ff65a9087.PNG)

-------------------------------------------------------------------------------------------------------------------------------

1. Open IntelliJ IDE and create a new Java project 
2. Clone the repository and add the two files 'exportNotes.java' and 'exportVocabBuilder.java' to the source file in the project folder

## Configuring 'exportNotes.java'
3. Download Apache POI (.zip) @ https://poi.apache.org/ 
4. Unzip the Apache POI file to the project folder
5. Navigate to 'File' -> 'Project Stucture' -> 'Modules' -> '+' to create a new project library. Add all the .jar files within the Apache POI folder in your repository and click 'Apply'. For Help: https://youtu.be/aPr-R-Ue0Ew

## Configuring 'exportVocabBuilder.java'
6. Download Kindle Mate @ https://kmate.me/download/
7. Open the app and navigate to the 'Vocabulary Words' -> 'Learning'. You will now see all the words that exist in your Kindle Vocab Builder

8. Highlight the entire table (CTRL+A) and press 'F12'. This will retrieve the definition for every word in the table
9. Highlight the entire table again after retriving the defintions and export the data to a '.xlsx' file (store in same location as Apache POI folder i.e. in project folder)

## Finalisation
10. Simply run 'exportNotes.java' followed by 'exportVocabBuilder.java'. Two files will be outputted in the current directory.
