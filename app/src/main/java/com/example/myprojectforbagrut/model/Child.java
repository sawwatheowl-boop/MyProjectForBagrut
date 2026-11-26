package com.example.myprojectforbagrut.model;

public class Child extends User {

    private int age;
    private String groupName;

    public Child(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.age = 0;
        this.groupName = "";
    }

    public Child(String firstName, String lastName, String email, String password, int age, String groupName) {
        super(firstName, lastName, email, password);
        setAge(age);
        setGroupName(groupName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if (groupName == null || groupName.trim().isEmpty()) {
            this.groupName = "";
        } else {
            this.groupName = groupName.trim();
        }
    }

    @Override
    public String toString() {
        return "Child{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", age=" + age +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}