package org.find.one.data.model;


import java.util.Date;

// TODO: 2022/8/6
public class User {

    private long id;

    private String username;

    private String sex;

    private Integer age;

    private String description;

    private int imgRes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return "Name：" + username + "\nAge：" + age + "\nSex：" + sex + "\nDescription：" + description;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public long getId() {
        return id;
    }

    public User(String username, String sex, Integer age, String description, int imgRes) {
        this.id = new Date().getTime();
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.description = description;
        this.imgRes = imgRes;
    }

    public User(long id, String username, String sex, Integer age, String description, int imgRes) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.description = description;
        this.imgRes = imgRes;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", imgRes=" + imgRes +
                '}';
    }
}
