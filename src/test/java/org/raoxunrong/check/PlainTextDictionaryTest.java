package org.raoxunrong.check;

import org.junit.Test;
import org.raoxunrong.check.spellcheck.dic.PlainTextFileDictionary;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlainTextDictionaryTest {
    private final String PLAINTEXT_DIC_RESOURCE_NAME = "plaintext_dictionary.txt";

    @Test
    public void shouldInitPlainTextDictionaryByResourceName() throws IOException {
        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        for(String word: getAdditionalWords()){
            assertTrue(plainTextFileDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByResourceNameAndSeparator() throws IOException {
        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary(PLAINTEXT_DIC_RESOURCE_NAME, "\n");
        for(String word: getAdditionalWords()){
            assertTrue(plainTextFileDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePath() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary(filePath);
        for(String word: getAdditionalWords()){
            assertTrue(plainTextFileDictionary.isWord(word));
        }
    }

    @Test
    public void shouldInitPlainTextDictionaryByFilePathAndSeparator() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource(PLAINTEXT_DIC_RESOURCE_NAME).getFile();
        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary(filePath, "\n");
        for(String word: getAdditionalWords()){
            assertTrue(plainTextFileDictionary.isWord(word));
        }
    }

    @Test
    public void shouldAddNewWord() throws IOException {
        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary(PLAINTEXT_DIC_RESOURCE_NAME);
        String[] words = new String[]{"new", "nothing"};
        for(String word: words){
            assertFalse(plainTextFileDictionary.isWord(word));
            plainTextFileDictionary.addNewWord(word);
        }

        for(String word: words){
            assertTrue(plainTextFileDictionary.isWord(word));
        }
    }

    @Test(expected = IOException.class)
    public void shouldGetExceptionWhenResourceNotExist() throws IOException {
        new PlainTextFileDictionary("/notExist");
    }

    @Test(expected = IOException.class)
    public void shouldGetExceptionWhenResourceIsEmpty() throws IOException {
        new PlainTextFileDictionary("");
    }

    private String[] getAdditionalWords() {
        return new String[]{"google", "baidu", "tw"};
    }
}


