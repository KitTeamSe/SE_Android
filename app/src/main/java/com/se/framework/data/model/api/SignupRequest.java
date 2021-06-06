package com.se.framework.data.model.api;

public final class SignupRequest {
    private String ans;

    private String email;

    private String id;

    private String name;

    private String nickname;

    private String pw;

    private int questionid;

    private String phoneNumber;

    private int stuNum;

    public SignupRequest(String ans, String email, String id, String name, String nickname, String pw, int questionid, String phoneNumber, int stuNum) {
        this.ans = ans;
        this.email = email;
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.pw = pw;
        this.questionid = questionid;
        this.phoneNumber = phoneNumber;
        this.stuNum = stuNum;
    }

    public String getAns() {
        return ans;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPw() {
        return pw;
    }

    public int getQuestionid() {
        return questionid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getStuNum() {
        return stuNum;
    }
}
