package com.acko.htmlgenerator.entities;


public class Attributes {

    private String attribute_name;
    private String display_name;
    private String attribute_value;

    @Override
    public String toString() {
        return "Attributes{" +
                "attributeName='" + attribute_name + '\'' +
                ", displayName='" + display_name + '\'' +
                ", attributeValue='" + attribute_value + '\'' +
                '}';
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getAttribute_value() {
        return attribute_value;
    }

    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
    }

    public Attributes(String attributeName, String display_name, String attribute_value) {
        this.attribute_name = attributeName;
        this.attribute_value = attribute_value;
        this.display_name = display_name;
    }
}
