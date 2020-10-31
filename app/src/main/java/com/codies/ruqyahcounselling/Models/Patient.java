package com.codies.ruqyahcounselling.Models;

import java.io.Serializable;

public class Patient implements Serializable {
    String name;
    String userId;
    String phoneNumber;
    String imageUrl;
    String gender;
    String age;
    String email;
    boolean relegious;
    String relegion;
    boolean beenToTherapyBefore;
    boolean experiencingSadness;
    String planForSuicide;
    boolean experiencingAnxiety;
    boolean takingMedication;
    String sleepingHabits;
    boolean chronicPain;
    String country;
    boolean spiritual;

    public Patient() {

    }

    public Patient(String name, String userId, String phoneNumber, String imageUrl, String gender, String age, String email, boolean relegious, String relegion, boolean beenToTherapyBefore, boolean experiencingSadness, String planForSuicide, boolean experiencingAnxiety, boolean takingMedication, String sleepingHabits, boolean chronicPain, String country, boolean spiritual) {
        this.name = name;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.relegious = relegious;
        this.relegion = relegion;
        this.beenToTherapyBefore = beenToTherapyBefore;
        this.experiencingSadness = experiencingSadness;
        this.planForSuicide = planForSuicide;
        this.experiencingAnxiety = experiencingAnxiety;
        this.takingMedication = takingMedication;
        this.sleepingHabits = sleepingHabits;
        this.chronicPain = chronicPain;
        this.country = country;
        this.spiritual = spiritual;
    }

    public boolean isRelegious() {
        return relegious;
    }

    public void setRelegious(boolean relegious) {
        this.relegious = relegious;
    }

    public String getRelegion() {
        return relegion;
    }

    public void setRelegion(String relegion) {
        this.relegion = relegion;
    }

    public boolean isBeenToTherapyBefore() {
        return beenToTherapyBefore;
    }

    public void setBeenToTherapyBefore(boolean beenToTherapyBefore) {
        this.beenToTherapyBefore = beenToTherapyBefore;
    }

    public boolean isExperiencingSadness() {
        return experiencingSadness;
    }

    public void setExperiencingSadness(boolean experiencingSadness) {
        this.experiencingSadness = experiencingSadness;
    }

    public String getPlanForSuicide() {
        return planForSuicide;
    }

    public void setPlanForSuicide(String planForSuicide) {
        this.planForSuicide = planForSuicide;
    }

    public boolean isExperiencingAnxiety() {
        return experiencingAnxiety;
    }

    public void setExperiencingAnxiety(boolean experiencingAnxiety) {
        this.experiencingAnxiety = experiencingAnxiety;
    }

    public boolean isTakingMedication() {
        return takingMedication;
    }

    public void setTakingMedication(boolean takingMedication) {
        this.takingMedication = takingMedication;
    }

    public String getSleepingHabits() {
        return sleepingHabits;
    }

    public void setSleepingHabits(String sleepingHabits) {
        this.sleepingHabits = sleepingHabits;
    }

    public boolean getChronicPain() {
        return chronicPain;
    }

    public void setChronicPain(boolean chronicPain) {
        this.chronicPain = chronicPain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSpiritual() {
        return spiritual;
    }

    public void setSpiritual(boolean spiritual) {
        this.spiritual = spiritual;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", relegious=" + relegious +
                ", relegion='" + relegion + '\'' +
                ", beenToTherapyBefore=" + beenToTherapyBefore +
                ", experiencingSadness=" + experiencingSadness +
                ", planForSuicide='" + planForSuicide + '\'' +
                ", experiencingAnxiety=" + experiencingAnxiety +
                ", takingMedication=" + takingMedication +
                ", sleepingHabits='" + sleepingHabits + '\'' +
                ", chronicPain=" + chronicPain +
                ", country='" + country + '\'' +
                ", spiritual=" + spiritual +
                '}';
    }
}
