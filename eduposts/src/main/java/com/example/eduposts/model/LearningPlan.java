//package com.example.eduposts.model;
//
//import jakarta.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "learning_plans")
//public class LearningPlan {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String subject;
//
//    @Column(nullable = false)
//    private String topics;
//
//    @Column(nullable = false, columnDefinition = "TEXT")
//    private String resources;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date date;
//
//    @Column(nullable = false)
//    private boolean saved;  // Changed from 'savePlan' to match field name
//
//    // Constructors
//    public LearningPlan() {}
//
//    public LearningPlan(String title, String subject, String topics, String resources, Date date, boolean saved) {
//        this.title = title;
//        this.subject = subject;
//        this.topics = topics;
//        this.resources = resources;
//        this.date = date;
//        this.saved = saved;
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
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
//    public boolean isSaved() {  // Changed from isSavePlan() to match field name
//        return saved;
//    }
//
//    public void setSaved(boolean saved) {  // Changed from setSavePlan() to match field name
//        this.saved = saved;
//    }
//}
package com.example.eduposts.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "learning_plans")
public class LearningPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String topics;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String resources;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private boolean saved;

    @Embedded
    private Progress progress = new Progress();

    // Constructors
    public LearningPlan() {
        this.progress = new Progress();
    }

    public LearningPlan(String title, String subject, String topics, String resources, Date date, boolean saved) {
        this.title = title;
        this.subject = subject;
        this.topics = topics;
        this.resources = resources;
        this.date = date;
        this.saved = saved;
        this.progress = new Progress();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}