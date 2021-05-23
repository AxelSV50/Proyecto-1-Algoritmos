/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author yeison
 */
public class Course {

 private String id;
 private String name;
  private int credits;
 private int careerID;

    public Course(String id, String name, int credits, int careerID) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.careerID = careerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCareerID() {
        return careerID;
    }

    public void setCareerID(int careerID) {
        this.careerID = careerID;
    }

    @Override
    public String toString() {
        return  id + "," + name + "," + credits + "," + careerID;
    }
 
}
