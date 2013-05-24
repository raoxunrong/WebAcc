package org.raoxunrong.check;

import org.junit.Test;
import org.raoxunrong.check.spellcheck.PlainTextDictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlainTextDictionaryTest {
    private final String PLAINTEXT_DIC_RESOURCE_NAME = "plaintext_dictionary.txt";

    @Test
    public void shouldInitPlainTextDictionaryByResourceName(){
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        String[] words = new String[]{"google", "baidu", "tw"};
        for(String word: words){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByResourceNameAndSeparator(){
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME, "\n");
        String[] words = new String[]{"google", "baidu", "tw"};
        for(String word: words){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePath(){
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath);
        String[] words = new String[]{"google", "baidu", "tw"};
        for(String word: words){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePathAndSeparator(){
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath, "\n");
        String[] words = new String[]{"google", "baidu", "tw"};
        for(String word: words){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldAddNewWord(){
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        String[] words = new String[]{"new", "nothing"};
        for(String word: words){
            assertFalse(plainTextDictionary.isWord(word));
            plainTextDictionary.addNewWord(word);
        }

        for(String word: words){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

}


