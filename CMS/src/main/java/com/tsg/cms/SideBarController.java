/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.SideBarLinksDbDao;
import com.tsg.cms.dto.SideBarLink;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class SideBarController {

    private final SideBarLinksDbDao sideBarDbDao;

    @Inject
    public SideBarController(SideBarLinksDbDao sideBarDbDao) {

        this.sideBarDbDao = sideBarDbDao;

    }

    @RequestMapping(value = {"/sideBarLinks"}, method = RequestMethod.GET)
    @ResponseBody
    public List<SideBarLink> getSideBarLinks() {

        return sideBarDbDao.getAllSideBarLinks();

    }
}
