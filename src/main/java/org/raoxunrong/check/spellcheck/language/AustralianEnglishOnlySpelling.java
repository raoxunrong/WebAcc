package org.raoxunrong.check.spellcheck.language;

import java.util.ArrayList;
import java.util.List;

import org.languagetool.language.English;
import org.languagetool.rules.Rule;
import org.languagetool.rules.en.MorfologikAustralianSpellerRule;

public class AustralianEnglishOnlySpelling extends English {

  @Override
  public final String[] getCountryVariants() {
    return new String[]{"AU"};
  }

  @Override
  public final String getName() {
    return "English (Australian)";
  }

  @Override
  public List<Class<? extends Rule>> getRelevantRules() {
    final List<Class<? extends Rule>> rules = new ArrayList<Class<? extends Rule>>();
    rules.add(MorfologikAustralianSpellerRule.class);
    return rules;
  }

}
