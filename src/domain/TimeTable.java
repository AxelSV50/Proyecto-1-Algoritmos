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
public class TimeTable {

    private String courseID;
    private String period;
    private String schedule1;
    private String schedule2;
    private String codeCourse;

    public TimeTable(String courseID, String period, String schedule1, String schedule2) {
        this.courseID = courseID;
        this.period = period;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSchedule1() {
        return schedule1;
    }

    public void setSchedule1(String schedule1) {
        this.schedule1 = schedule1;
    }

    public String getSchedule2() {
        return schedule2;
    }

    public void setSchedule2(String schedule2) {
        this.schedule2 = schedule2;
    }
    private String getCodeCourse() {

        CircularDoublyLinkedList list = util.Utility.getCoursesList();
        int index;
        String description = null;
        try {
            if (list.contains(new Course(courseID, "",0,0))) {

                index = list.indexOf(new Course(courseID, "",0,0));
                Course c = (Course) list.getNode(index).data;
                description = c.getId();
            }

        } catch (ListException ex) {
        }

        return description;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }
    @Override
    public String toString() {
        return courseID + "," + period + "," + schedule1 + "," + schedule2;
    }

}
