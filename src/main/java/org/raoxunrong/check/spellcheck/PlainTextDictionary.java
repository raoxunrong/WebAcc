package org.raoxunrong.check.spellcheck;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.exec.util.StringUtils;
import org.raoxunrong.check.spellcheck.CustomisedDictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PlainTextDictionary implements CustomisedDictionary {

    private String rawString;
    private HashSet<String> words;

    public PlainTextDictionary(String filePath) throws IOException {
        this(filePath, "\n");
    }

    public PlainTextDictionary(String filePath, String separatorChars) throws IOException {
        initWords(filePath, separatorChars);
    }

    private void initWords(String filePath, String separatorChars) throws IOException {
        this.rawString = getStringFromFile(filePath);
        this.words = getHashSetFromString(rawString, separatorChars);
    }

    private HashSet<String> getHashSetFromString(String rawString, String separatorChars) {
        HashSet<String> wordHashSet = new HashSet<String>();
        List<String> wordList = Arrays.asList(StringUtils.split(rawString, separatorChars));
        for (String word : wordList) {
            wordHashSet.add(word.trim());
        }
        return wordHashSet;
    }

    private String getStringFromFile(String fileNameOrPath) throws IOException {
        URL url = getURL(fileNameOrPath);
        if(!isURLValid(url)) {
            throw new FileNotFoundException(fileNameOrPath + " is not exist");
        }
        return Resources.toString(url, Charsets.UTF_8);
    }

    private boolean isURLValid(URL url) {
        boolean valid = false;
        if (url != null) {
            try {
                File file = new File(url.toURI());
                if (file.exists() && file.isFile()) {
                    valid = true;
                }
            } catch (URISyntaxException e) {
                //nothing to do, just return valid is false
            }
        }

        return valid;
    }

    private URL getURL(String nameOrPath) throws MalformedURLException {
        File file = new File(nameOrPath);
        return ((file.exists()) ? getFileURLByPath(nameOrPath) : getFileURLByResourceName(nameOrPath));
    }

    private URL getFileURLByPath(String filePath) throws MalformedURLException {
        File file = new File(filePath);
        return file.toURI().toURL();
    }

    private URL getFileURLByResourceName(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResource(resourceName);
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
