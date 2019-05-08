package com.example.wellsitting;

public class StoryInformation {
    public String title;
    public String background;
    public String head;
    public String character_name;
    public String scene;
    public Integer order;
    public boolean check_value;

    public StoryInformation(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgroud() {
        return background;
    }

    public void setBackgroud(String backgroud) {
        this.background = backgroud;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public String getSence() {
        return scene;
    }

    public void setSence(String sence) {
        this.scene = sence;
    }

    public boolean isCheck_value() {
        return check_value;
    }

    public void setCheck_value(boolean check_value) {
        this.check_value = check_value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
