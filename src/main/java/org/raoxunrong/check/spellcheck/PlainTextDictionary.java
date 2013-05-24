package org.raoxunrong.check.spellcheck;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.exec.util.StringUtils;
import org.raoxunrong.check.spellcheck.CustomisedDictionary;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PlainTextDictionary implements CustomisedDictionary {

    private String rawString;
    private HashSet<String> words;

    public PlainTextDictionary(String filePath){
        this(filePath, "\n");
    }

    public PlainTextDictionary(String filePath, String separatorChars){
        initWords(filePath, separatorChars);
    }

    private void initWords(String filePath, String separatorChars){
        this.rawString = getStringFromFile(filePath);
        this.words = getHashSetFromString(rawString, separatorChars);
    }

    private HashSet<String> getHashSetFromString(String rawString, String separatorChars){
        HashSet<String> wordHashSet = new HashSet<String>();
        List<String> wordList = Arrays.asList(StringUtils.split(rawString, separatorChars));
        for(String word: wordList){
           wordHashSet.add(word.trim());
        }
        return wordHashSet;
    }

    private String getStringFromFile(String fileNameOrPath){
        URL url = getURL(fileNameOrPath);
        String textToString = null;
        try {
            textToString = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textToString;
    }

    private URL getURL(String nameOrPath){
        File file = new File(nameOrPath);
        URL fileURL = null;
        if(file.exists()){
            fileURL = getFileURLByPath(nameOrPath);
        } else {
            fileURL = getFileURLByResourceName(nameOrPath);
        }
        return fileURL;
    }

    private URL getFileURLByPath(String filePath){
        File file = new File(filePath);
        URL fileURL = null;
        try {
            fileURL = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return fileURL;
    }

    private URL getFileURLByResourceName(String resourceName){
        URL resourceFileURL = this.getClass().getClassLoader().getResource(resourceName);
        return resourceFileURL;
    }


    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public void addNewWord(String word) {
        this.words.add(word);
    }
}
