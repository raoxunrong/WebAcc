package org.raoxunrong.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.FileUtils;
import org.raoxunrong.check.CheckType;
import org.raoxunrong.domain.item.CheckedItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.raoxunrong.check.CheckType.SpellingCheck;

public final class CheckedItemStatistic {

    private static final String CheckReportTemplate = "PageCheckReport.html";

    private static Map<CheckType, Set<CheckedItem>> checkedMap = new HashMap<CheckType, Set<CheckedItem>>();

    static {
        for (CheckType checkType : CheckType.values()) {
            checkedMap.put(checkType, new LinkedHashSet<CheckedItem>());
        }
    }

    private CheckedItemStatistic() {
    }

    public static void addCheckedItem(CheckedItem checkedItem) {
        checkedMap.get(checkedItem.getCheckType()).add(checkedItem);
    }

    public static Collection<CheckedItem> getStatisticInfo(CheckType checkType) {
        return Collections.unmodifiableCollection(checkedMap.get(checkType));
    }

    public static void generateReport(String reportGeneratePath, CheckType checkType) throws IOException {
        String checkReport = getCheckReportPlainText();
        StringTemplate checkReportTemplate = buildCheckReportTemplate(checkType, checkReport);

        File file = new File(reportGeneratePath, checkType.name() + ".html");
        FileUtils.writeStringToFile(file, checkReportTemplate.toString());
    }

    private static StringTemplate buildCheckReportTemplate(CheckType checkType, String checkReport) {
        StringTemplate checkReportTemplate = new StringTemplate(checkReport);
        checkReportTemplate.setAttribute("checkType", checkType.name());
        checkReportTemplate.setAttribute("reports", getStatisticInfo(checkType));
        return checkReportTemplate;
    }

    private static String getCheckReportPlainText() throws IOException {
        URL reportTemplateURL = Thread.currentThread().getContextClassLoader().getResource(CheckReportTemplate);
        return Resources.toString(reportTemplateURL, Charsets.UTF_8);
    }

}
