/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

import com.tsg.cms.dao.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class BlogPost {

    //We will probably need a private String category;
    //and perhaps a private List<tags> tags; 
    private int postId;
    private Date timeCreated;
    private Date timeEdited;
    private Date startDate;
    private Date endDate;
    private String title;
    private String postBody;
    private int userIdFK;
    private String titleNumber;
    private Status status;
    //titleNumber concatenates the title, and if the title isn't unique, the 
    //ordinal number of the title, starting with 1.

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.postId;
        hash = 59 * hash + Objects.hashCode(this.timeCreated);
        hash = 59 * hash + Objects.hashCode(this.timeEdited);
        hash = 59 * hash + Objects.hashCode(this.startDate);
        hash = 59 * hash + Objects.hashCode(this.endDate);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.postBody);
        hash = 59 * hash + this.userIdFK;
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.titleNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogPost other = (BlogPost) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userIdFK != other.userIdFK) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.postBody, other.postBody)) {
            return false;
        }
        if (!Objects.equals(this.titleNumber, other.titleNumber)) {
            return false;
        }
        if (!Objects.equals(this.timeCreated, other.timeCreated)) {
            return false;
        }
        if (!Objects.equals(this.timeEdited, other.timeEdited)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(Date timeEdited) {
        this.timeEdited = timeEdited;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(int userIdFK) {
        this.userIdFK = userIdFK;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

}
