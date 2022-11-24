package com.acko.htmlgenerator.entities;

import com.acko.htmlgenerator.models.TemplateHistory;

public class TemplateHistoryWithHtmlContent {

    public TemplateHistory templateHistory;
    public String htmlContent;

    @Override
    public String toString() {
        return "TemplateHistoryWithHtmlContent{" +
                "templateHistory=" + templateHistory +
                ", htmlContent='" + htmlContent + '\'' +
                '}';
    }

    public void setTemplateHistory(TemplateHistory templateHistory) {
        this.templateHistory = templateHistory;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public TemplateHistory getTemplateHistory() {
        return templateHistory;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public TemplateHistoryWithHtmlContent(TemplateHistory templateHistory, String htmlContent) {
        this.templateHistory = templateHistory;
        this.htmlContent = htmlContent;
    }
}
