package com.acko.htmlgenerator.entities;


public class Attributes {

    private String attributeName;
    private String displayName;
    private String attributeValue;

    @Override
    public String toString() {
        return "Attributes{" +
                "attributeName='" + attributeName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Attributes(String attributeName, String displayName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.displayName = displayName;
    }
}
