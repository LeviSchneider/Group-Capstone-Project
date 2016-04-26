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

    private String staticPageName;
    private String staticPageUrl;
    private int pageId;
    private String staticPageJsp;
    private String staticPageJavaScript;
    private int staticPagePosition;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.staticPageName);
        hash = 53 * hash + Objects.hashCode(this.staticPageUrl);
        hash = 53 * hash + this.pageId;
        hash = 53 * hash + Objects.hashCode(this.staticPageJsp);
        hash = 53 * hash + Objects.hashCode(this.staticPageJavaScript);
        hash = 53 * hash + this.staticPagePosition;
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
        if (this.pageId != other.pageId) {
            return false;
        }
        if (this.staticPagePosition != other.staticPagePosition) {
            return false;
        }
        if (!Objects.equals(this.staticPageName, other.staticPageName)) {
            return false;
        }
        if (!Objects.equals(this.staticPageUrl, other.staticPageUrl)) {
            return false;
        }
        if (!Objects.equals(this.staticPageJsp, other.staticPageJsp)) {
            return false;
        }
        if (!Objects.equals(this.staticPageJavaScript, other.staticPageJavaScript)) {
            return false;
        }
        return true;
    }

    public String getStaticPageName() {
        return staticPageName;
    }

    public void setStaticPageName(String staticPageName) {
        this.staticPageName = staticPageName;
    }

    public String getStaticPageUrl() {
        return staticPageUrl;
    }

    public void setStaticPageUrl(String staticPageUrl) {
        this.staticPageUrl = staticPageUrl;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getStaticPageJsp() {
        return staticPageJsp;
    }

    public void setStaticPageJsp(String staticPageJsp) {
        this.staticPageJsp = staticPageJsp;
    }

    public String getStaticPageJavaScript() {
        return staticPageJavaScript;
    }

    public void setStaticPageJavaScript(String staticPageJavaScript) {
        this.staticPageJavaScript = staticPageJavaScript;
    }

    public int getStaticPagePosition() {
        return staticPagePosition;
    }

    public void setStaticPagePosition(int staticPagePosition) {
        this.staticPagePosition = staticPagePosition;
    }
}
