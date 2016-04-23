/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author apprentice
 */
public class BlogPost {
    //We will probably need a private String category;
    //and perhaps a private List<tags> tags; 
    private int postId;
    private Date dateSubmitted;
    private Date startDate;
    private Date endDate;
    private String title;
    private String postBody;
    private int userIdFK;
    private String status;
    private String postType; // <<<---- I am uncertain what the postType is for.
    private String titleNumber;
    //titleNumber concatenates the title, and if the title isn't unique, the 
    //ordinal number of the title, starting with 1.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.postId;
        hash = 83 * hash + Objects.hashCode(this.dateSubmitted);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.endDate);
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.postBody);
        hash = 83 * hash + this.userIdFK;
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.postType);
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
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.postType, other.postType)) {
            return false;
        }
        if (!Objects.equals(this.dateSubmitted, other.dateSubmitted)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
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

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }
    
}
