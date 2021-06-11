/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Enrollment {
    
    private int id;
    private Date date;
    private String studentID;
    private String courseID;
    private String schedule;
    
    public Enrollment(Date date, String studentID, String courseID, String schedule, int id){
        
        this.id = id;
        this.date = date;
        this.courseID = courseID;
        this.schedule = schedule;
        this.studentID = studentID;
    }



    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return studentID+"~"+courseID+"~"+schedule+"~"+util.Utility.dateFormat(date)+"~"+id+"\n";
    }
    
}
