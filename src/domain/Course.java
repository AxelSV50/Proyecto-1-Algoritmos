/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.list.DoublyLinkedList;
import domain.list.ListException;

/**
 *
 * @author yeison
 */
public class Course {

    private String id;
    private String name;
    private int credits;
    private int careerID;
    private String descriptionCarreer;

    public Course(String id, String name, int credits, int careerID) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.careerID = careerID;
        this.descriptionCarreer = getDescription();

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

    public String getDescriptionCarreer() {
        return descriptionCarreer;
    }

    private String getDescription() {

        DoublyLinkedList list = util.Utility.getCareersList();
        int index;
        String description = null;
        try {
            if (list.contains(new Career(careerID, ""))) {

                index = list.indexOf(new Career(careerID, ""));
                Career c = (Career) list.getNode(index).data;
                description = c.getDescription();
            }

        } catch (ListException ex) {
        }

        return description;
    }

    public void setDescriptionCarreer(String descriptionCarreer) {
        this.descriptionCarreer = descriptionCarreer;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + credits + "," + careerID;
    }

}
