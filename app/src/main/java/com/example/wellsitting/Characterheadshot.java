package com.example.wellsitting;

public class Characterheadshot {
    private String name;
    private String pic_headshot;
    private String brief_intro;
    private boolean check_value;

    public Characterheadshot(){

    }
    public Characterheadshot(String name,String pic_headshot,String brief_intro,boolean check_value){
        this.name=name;
        this.pic_headshot=pic_headshot;
        this.brief_intro=brief_intro;
        this.check_value=check_value;
    }

    public String getName() {
        return name;
    }

    public boolean isCheck_value() {
        return check_value;
    }

    public void setCheck_value(boolean check_value) {
        this.check_value = check_value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_headshot() {
        return pic_headshot;
    }

    public void setPic_headshot(String pic_headshot) {
        this.pic_headshot = pic_headshot;
    }

    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }
}

