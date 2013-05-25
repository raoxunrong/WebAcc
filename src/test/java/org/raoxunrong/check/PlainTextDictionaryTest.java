package org.raoxunrong.check;

import org.junit.Test;
import org.raoxunrong.check.spellcheck.PlainTextDictionary;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlainTextDictionaryTest {
    private final String PLAINTEXT_DIC_RESOURCE_NAME = "plaintext_dictionary.txt";

    @Test
    public void shouldInitPlainTextDictionaryByResourceName() throws IOException {
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        for(String word: getAdditionalWords()){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByResourceNameAndSeparator() throws IOException {
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(PLAINTEXT_DIC_RESOURCE_NAME, "\n");
        for(String word: getAdditionalWords()){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePath() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath);
        for(String word: getAdditionalWords()){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePathAndSeparator() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextDictionary plainTextDictionary = new PlainTextDictionary(filePath, "\n");
        for(String word: getAdditionalWords()){
            assertTrue(plainTextDictionary.isWord(word));
        }
    }

    @Test
    public void shouldAddNewWord() throws IOException {
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

    @Test(expected = IOException.class)
    public void shouldGetExceptionWhenResourceNotExist() throws IOException {
        new PlainTextDictionary("/notExist");
    }

    @Test(expected = IOException.class)
    public void shouldGetExceptionWhenResourceIsEmpty() throws IOException {
        new PlainTextDictionary("");
    }

    private String[] getAdditionalWords() {
        return new String[]{"google", "baidu", "tw"};
    }
}


