package com.github.cundream.spring_boot_mail.vo;

import java.util.List;

/**
 * @author : lc
 * @Date: 2019/7/18 14:57
 * @Description:
 */
public class MailTemplate {
    private String date;
    private List<User> userList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public MailTemplate(String date, List<User> userList) {
        this.date = date;
        this.userList = userList;
    }

    public MailTemplate() {
    }

    @Override
    public String toString() {
        return "MailTemplate{" +
                "date='" + date + '\'' +
                ", userList=" + userList +
                '}';
    }
}
