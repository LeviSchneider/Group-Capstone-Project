/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

/**
 *
 * @author apprentice
 */
public enum Status {

    DRAFT,
    READY_FOR_APPROVAL,
    APPROVED,
    UNPUBLISHED,
    PUBLISHED;

//    
//    DRAFT("Draft"),
//    READY_FOR_APPROVAL("Ready for approval"),
//    APPROVED("Approved"),
//    UNPUBLISHED("Unpublished"),
//    PUBLISHED("Published");
//
//    
//    
//    private String statusName;
//
//    Status(String statusName) {
//        this.statusName = statusName;
//    }
//    @Override
//    public String toString() {
//        return this.statusName;
//    }
//    public Status fromString(String statusName) {
//        if (statusName != null) {
//            for (Status status : Status.values()) {
//                if (statusName.equals(status.statusName)) {
//                    return status;
//                }
//            }
//        }
//        return null;
//    }
    //calling blogPost.getStatus should return human-readable status
    //
}
