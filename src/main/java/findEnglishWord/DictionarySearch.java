package findEnglishWord;

import java.util.ArrayList;
import java.util.List;

public class DictionarySearch {

    private DictionaryService dictListService;
    ArrayList <String> matchesList = new ArrayList <String> ();

    public void setDictionaryService(DictionaryService dictListService){
        this.dictListService = dictListService;
    }

    /**
     * Represents the getDictionary function
     * @return String list with the dictionary content
     */
    public List<String> getDictionary() {
        return dictListService.getDictionary();
    }

    /**
     * Represents the isEnglishWord function
     * @param word any given string
     * @return boolean based if the word was found in the dictionary
     */
    public boolean isEnglishWord(String word) {
        return dictListService.isEnglishWord(word);
    }

    /**
     * Find all possible words in a given string
     * @param inputData any given string
     * @return String list with all the possible word combinations from the dictionary based on the given inputData
     */
    public List<String> findPossibleWords(String inputData) {
        
        List<String> dictionary = getDictionary();
        inputData = inputData.toLowerCase();
      
        // for each word in dictionary
        for (String word : dictionary) {
            // match flag
            Boolean nonMatchList = true;

            for (char chWord : word.toCharArray()) {
                String w = Character.toString(chWord);
                
                // if the count of chWord in word is equal to its count in inputData, then they are match
               
                if (inputData.length() - inputData.replace(w, "").length() != 
                		word.length() - word.replace(w, "").length()) {
                			nonMatchList = false;
                            break;
                		}
                }
           
            if (nonMatchList) {
            	// Adding words whose length greater than 3 and less
            	if (word.length() > 3 && word.length() <= 6) {
            		matchesList.add(word);
            	}
            }
        }
        System.out.println(matchesList);
        return matchesList;
    }
}
