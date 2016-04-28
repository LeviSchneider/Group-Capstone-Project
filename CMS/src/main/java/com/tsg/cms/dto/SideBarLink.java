/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class SideBarLink {

    private String sideBarLinkName;
    private String sideBarLinkUrl;
    private int linkId;
    private String sideBarLinkJsp;
    private String sideBarLinkJavaScript;
    private int sideBarLinkPosition;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.sideBarLinkName);
        hash = 53 * hash + Objects.hashCode(this.sideBarLinkUrl);
        hash = 53 * hash + this.linkId;
        hash = 53 * hash + Objects.hashCode(this.sideBarLinkJsp);
        hash = 53 * hash + Objects.hashCode(this.sideBarLinkJavaScript);
        hash = 53 * hash + this.sideBarLinkPosition;
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
        final SideBarLink other = (SideBarLink) obj;
        if (this.linkId != other.linkId) {
            return false;
        }
        if (this.sideBarLinkPosition != other.sideBarLinkPosition) {
            return false;
        }
        if (!Objects.equals(this.sideBarLinkName, other.sideBarLinkName)) {
            return false;
        }
        if (!Objects.equals(this.sideBarLinkUrl, other.sideBarLinkUrl)) {
            return false;
        }
        if (!Objects.equals(this.sideBarLinkJsp, other.sideBarLinkJsp)) {
            return false;
        }
        if (!Objects.equals(this.sideBarLinkJavaScript, other.sideBarLinkJavaScript)) {
            return false;
        }
        return true;
    }

    public String getSideBarLinkName() {
        return sideBarLinkName;
    }

    public void setSideBarLinkName(String sideBarLinkName) {
        this.sideBarLinkName = sideBarLinkName;
    }

    public String getSideBarLinkUrl() {
        return sideBarLinkUrl;
    }

    public void setSideBarLinkUrl(String sideBarLinkUrl) {
        this.sideBarLinkUrl = sideBarLinkUrl;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getSideBarLinkJsp() {
        return sideBarLinkJsp;
    }

    public void setSideBarLinkJsp(String sideBarLinkJsp) {
        this.sideBarLinkJsp = sideBarLinkJsp;
    }

    public String getSideBarLinkJavaScript() {
        return sideBarLinkJavaScript;
    }

    public void setSideBarLinkJavaScript(String sideBarLinkJavaScript) {
        this.sideBarLinkJavaScript = sideBarLinkJavaScript;
    }

    public int getSideBarLinkPosition() {
        return sideBarLinkPosition;
    }

    public void setSideBarLinkPosition(int sideBarLinkPosition) {
        this.sideBarLinkPosition = sideBarLinkPosition;
    }
}
