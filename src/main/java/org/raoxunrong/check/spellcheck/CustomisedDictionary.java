package org.raoxunrong.check.spellcheck;

public interface CustomisedDictionary {
    boolean isWord(String word);

    void addNewWord(String word);
}
