package org.raoxunrong.check.spellcheck.dic;

public interface CustomisedDictionary {
    boolean isWord(String word);

    void addNewWord(String word);
}
