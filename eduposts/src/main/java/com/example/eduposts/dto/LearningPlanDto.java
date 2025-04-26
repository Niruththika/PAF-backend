//package com.example.eduposts.dto;
//
//import java.util.Date;
//
//public class LearningPlanDto {
//    private String title;
//    private String subject;
//    private String topics;
//    private String resources;
//    private Date date;
//    private boolean savePlan;
//
//    // Constructors, getters, and setters
//    public LearningPlanDto() {}
//
//    public LearningPlanDto(String title, String subject, String topics, String resources, Date date, boolean savePlan) {
//        this.title = title;
//        this.subject = subject;
//        this.topics = topics;
//        this.resources = resources;
//        this.date = date;
//        this.savePlan = savePlan;
//    }
//
//    // Getters and setters
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getTopics() {
//        return topics;
//    }
//
//    public void setTopics(String topics) {
//        this.topics = topics;
//    }
//
//    public String getResources() {
//        return resources;
//    }
//
//    public void setResources(String resources) {
//        this.resources = resources;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public boolean isSavePlan() {
//        return savePlan;
//    }
//
//    public void setSavePlan(boolean savePlan) {
//        this.savePlan = savePlan;
//    }
//}
package com.example.eduposts.dto;

import java.util.Date;

public class LearningPlanDto {
    private String title;
    private String subject;
    private String topics;
    private String resources;
    private Date date;
    private boolean savePlan;
    private ProgressDto progress;

    // Constructors
    public LearningPlanDto() {}

    public LearningPlanDto(String title, String subject, String topics, String resources, Date date, boolean savePlan) {
        this.title = title;
        this.subject = subject;
        this.topics = topics;
        this.resources = resources;
        this.date = date;
        this.savePlan = savePlan;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSavePlan() {
        return savePlan;
    }

    public void setSavePlan(boolean savePlan) {
        this.savePlan = savePlan;
    }

    public ProgressDto getProgress() {
        return progress;
    }

    public void setProgress(ProgressDto progress) {
        this.progress = progress;
    }
}