package com.misio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Person {

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    private List<String> param1;
    private List<String> param2;
    private List<String> param3;
    private List<String> param4;
    private List<String> param5;
    private List<String> param6;
    private List<String> param7;
    private List<String> param8;
    private List<String> param0;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getParam1() {
        return param1;
    }

    public void setParam1(List<String> param1) {
        this.param1 = param1;
    }

    public List<String> getParam2() {
        return param2;
    }

    public void setParam2(List<String> param2) {
        this.param2 = param2;
    }

    public List<String> getParam3() {
        return param3;
    }

    public void setParam3(List<String> param3) {
        this.param3 = param3;
    }

    public List<String> getParam4() {
        return param4;
    }

    public void setParam4(List<String> param4) {
        this.param4 = param4;
    }

    public List<String> getParam5() {
        return param5;
    }

    public void setParam5(List<String> param5) {
        this.param5 = param5;
    }

    public List<String> getParam6() {
        return param6;
    }

    public void setParam6(List<String> param6) {
        this.param6 = param6;
    }

    public List<String> getParam7() {
        return param7;
    }

    public void setParam7(List<String> param7) {
        this.param7 = param7;
    }

    public List<String> getParam8() {
        return param8;
    }

    public void setParam8(List<String> param8) {
        this.param8 = param8;
    }

    public List<String> getParam0() {
        return param0;
    }

    public void setParam0(List<String> param0) {
        this.param0 = param0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                ", param5=" + param5 +
                ", param6=" + param6 +
                ", param7=" + param7 +
                ", param8=" + param8 +
                ", param0=" + param0 +
                '}';
    }
}
