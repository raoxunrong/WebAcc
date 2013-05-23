package org.raoxunrong.check;

import org.junit.Test;
import java.util.HashSet;
import static org.junit.Assert.assertTrue;

public class PlainTextDictionaryTest {
    private final String PLAINTEXT_DIC_RESOURCE_NAME = "plaintext_dictionary.txt";

    @Test
    public void shouldInitPlainTextDictionaryByResourceName(){
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        HashSet<String> expectedHashSet = new HashSet<String>(){{
            add("google");
            add("baidu");
            add("tw");
        }};
        HashSet<String> words = plainTextDictionary.getWords();
        for(String word: words){
            assertTrue(expectedHashSet.contains(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByResourceNameAndSeparator(){
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME, "\n");
        HashSet<String> expectedHashSet = new HashSet<String>(){{
            add("google");
            add("baidu");
            add("tw");
        }};
        HashSet<String> words = plainTextDictionary.getWords();
        for(String word: words){
            assertTrue(expectedHashSet.contains(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePath(){
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath);
        HashSet<String> expectedHashSet = new HashSet<String>(){{
            add("google");
            add("baidu");
            add("tw");
        }};
        HashSet<String> words = plainTextDictionary.getWords();
        for(String word: words){
            assertTrue(expectedHashSet.contains(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePathAndSeparator(){
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath, "\n");
        HashSet<String> expectedHashSet = new HashSet<String>(){{
            add("google");
            add("baidu");
            add("tw");
        }};
        HashSet<String> words = plainTextDictionary.getWords();
        for(String word: words){
            assertTrue(expectedHashSet.contains(word));
        }
    }

}


