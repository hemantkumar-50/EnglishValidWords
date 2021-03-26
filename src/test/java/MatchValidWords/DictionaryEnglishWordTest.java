package MatchValidWords;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import findEnglishWord.DictionarySearch;
import findEnglishWord.DictionaryService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class DictionaryEnglishWordTest {

    private DictionarySearch dictionary;
    private DictionaryService dictService;
    List<String> dictionaryList;

    @Before
    public void setUp() {
        dictionary = new DictionarySearch();
        dictService = mock(DictionaryService.class);
        dictionary.setDictionaryService(dictService);
   
        when(dictService.getDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictService.getDictionary();
    }

    /**
     * Create String list based on the Dictionary file (EnglishWords in this case) to mock the dictionary service
     * @return String list with the dictionary content
     */
    static List<String> createDictionaryArray() {
        List<String> listDictionary = new ArrayList<String>();
        BufferedReader reader;

        try {
            ClassLoader loader = DictionaryEnglishWordTest.class.getClassLoader();
            File file = new File(loader.getResource("EnglishWords").getFile());
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                listDictionary.add(line);
                line = reader.readLine(); 
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }

    /**
     * Validate if given word exists in the dictionary (EnglishWords in this case) to mock the isEnglishWord function
     * @param word
     * @return boolean based if the word was found in the dictionary
     */
    public boolean isThisValidEnglish(String word) {
        for (String w : dictionaryList) {
            if (w.equals(word.toLowerCase())) {
                System.out.println(word + " is a valid");
                return true;
            }
        }
        return false;
    }

    @Test
    public void validateWorkingWord() {
        when(dictService.isEnglishWord("WORKING")).thenReturn(isThisValidEnglish("WORKING"));
        Assert.assertTrue(dictionary.isEnglishWord("WORKING"));
        // Print all possible words
        dictionary.findPossibleWords("WORKING");
    }
    
}

