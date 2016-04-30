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
public class StaticPage {

    private int pageId;
    private Date timeCreated;
    private Date timeEdited;
    private Date startDate;
    private Date endDate;
    private String title;
    private String pageBody;
    private int userIdFK;
    private Integer categoryIdFK;
    private String titleNumber;
    private Status status;
    private int sideBarPosition;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.pageId;
        hash = 43 * hash + Objects.hashCode(this.timeCreated);
        hash = 43 * hash + Objects.hashCode(this.timeEdited);
        hash = 43 * hash + Objects.hashCode(this.startDate);
        hash = 43 * hash + Objects.hashCode(this.endDate);
        hash = 43 * hash + Objects.hashCode(this.title);
        hash = 43 * hash + Objects.hashCode(this.pageBody);
        hash = 43 * hash + this.userIdFK;
        hash = 43 * hash + Objects.hashCode(this.categoryIdFK);
        hash = 43 * hash + Objects.hashCode(this.titleNumber);
        hash = 43 * hash + Objects.hashCode(this.status);
        hash = 43 * hash + this.sideBarPosition;
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
        final StaticPage other = (StaticPage) obj;
        if (this.pageId != other.pageId) {
            return false;
        }
        if (this.userIdFK != other.userIdFK) {
            return false;
        }
        if (this.sideBarPosition != other.sideBarPosition) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.pageBody, other.pageBody)) {
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
        if (!Objects.equals(this.categoryIdFK, other.categoryIdFK)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }
    
  
    
    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
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

    public String getPageBody() {
        return pageBody;
    }

    public void setPageBody(String pageBody) {
        this.pageBody = pageBody;
    }

    public int getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(int userIdFK) {
        this.userIdFK = userIdFK;
    }

    public Integer getCategoryIdFK() {
        return categoryIdFK;
    }

    public void setCategoryIdFK(Integer categoryIdFK) {
        this.categoryIdFK = categoryIdFK;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getSideBarPosition() {
        return sideBarPosition;
    }

    public void setSideBarPosition(int sideBarPosition) {
        this.sideBarPosition = sideBarPosition;
    }

}
