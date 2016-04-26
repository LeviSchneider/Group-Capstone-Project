///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tsg.cms;
//
//import com.tsg.cms.dao.CategoryDbDao;
//import com.tsg.cms.dao.StaticPageDbDao;
//import com.tsg.cms.dao.TagDbDao;
//import com.tsg.cms.dto.StaticPageContainer;
//import com.tsg.cms.dto.TagContainer;
//import javax.inject.Inject;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
///**
// *
// * @author apprentice
// */
//public class StaticPageController {
//
//    private final StaticPageDbDao dao;
//    private final CategoryDbDao categoryDao;
//
//    @Inject
//    public StaticPageController(StaticPageDbDao dao, CategoryDbDao categoryDao) {
//        this.dao = dao;
//        this.categoryDao = categoryDao;
//    }
//
//    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public StaticPageContainer getStaticPage(@PathVariable("id") int id) {
//
//        StaticPageContainer container = new StaticPageContainer();
//        container.setStaticPage(dao.getStaticPageById(id));
//
//        return container;
//    }
//
//    @RequestMapping(value = "/staticPage", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public StaticPage createStaticPage(@RequestBody StaticPage staticPage) {
//        staticPage.setUserIdFK(999);
//        staticPage = dao.addStaticPage(staticPage);
//
//        String body = staticPage.getPageBody();
//        List<String> tags = hashTagMatcher.findHashTags(body);
//
//        for (String tag : tags) {
//
//            tagDao.addTag(tag, staticPage.getPageId());
//        }
//
//        return staticPage;
//    }
//
//    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteStaticPage(@PathVariable("id") int id) {
//        dao.removeStaticPage(id);
//    }
//
//    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public StaticPage updateStaticPage(@PathVariable("id") int id, @RequestBody StaticPage staticPage) {
//        staticPage.setPageId(id);
//
//        String body = staticPage.getPageBody();
//        List<String> tags = hashTagMatcher.findHashTags(body);
//
//        for (String tag : tags) {
//            tagDao.addTag(tag, staticPage.getPageId());
//        }
//
//        dao.updateStaticPage(staticPage);
//        return staticPage;
//    }
////        
////    @RequestMapping(value = "/pubstaticPage", method = RequestMethod.GET)
////    @ResponseBody
////    public List<StaticPage> getPublishedStaticPage () {
////        return dao.getPublishedStaticPage();
////    }
//
//    @RequestMapping(value = "/staticPages", method = RequestMethod.GET)
//    @ResponseBody
//    public List<StaticPageContainer> getAllStaticPage() {
//
//        List<StaticPageContainer> staticPageContainerList = new ArrayList<>();
//        List<StaticPage> staticPages = dao.getAllStaticPage();
//
//        for (StaticPage staticPage : staticPages) {
//
//            StaticPageContainer staticPageContainer = new StaticPageContainer();
//
//            TagContainer tagContainer = new TagContainer();
//            tagContainer.setTagList(tagDao.getPageTags(staticPage.getPageId()));
//            staticPageContainer.setTagContainer(tagContainer);
//
//            CategoryContainer categoryContainer = new CategoryContainer();
//            //categoryContainer.setCategoryList(categoryDao.getPageCategories(staticPage.getPageId()));
//            staticPageContainer.setCategoryContainer(categoryContainer);
//
//            staticPageContainer.setStaticPage(staticPage);
//
//            staticPageContainerList.add(staticPageContainer);
//        }
//        return staticPageContainerList;
//
//    }
//
//    @RequestMapping(value = "/tinymce", method = RequestMethod.GET)
//    public String showEditor(Map<String, Object> model, HttpSession session) {
//
//        session.setAttribute("page", "tinymce");
//
//        session.setAttribute("js_page", "tinymce.js");
//        return "home";
//    }
//
//    @RequestMapping(value = "/tinymce/{id}", method = RequestMethod.GET)
//    public String showPopulatedEditor(@PathVariable("id") int id, Map<String, Object> model, HttpSession session) {
//
//        //model.put("staticPage", dao.getStaticPageById(id));
//        session.setAttribute("page", "tinymce");
//
//        session.setAttribute("js_page", "tinymce.js");
//
//        model.put("editStaticPageId", id);
//        return "home";
//    }
//
//    @RequestMapping(value = "/link/{titleNumber}", method = RequestMethod.GET)
//    public String getLinkedPage(@PathVariable("titleNumber") String titleNumber, Map<String, Object> model, HttpSession session) {
//
//        session.setAttribute("page", "singlePage");
//
//        session.setAttribute("js_page", "singlePage.js");
//        int id = dao.getStaticPageByTitleNumber(titleNumber).getPageId();
//        model.put("id", id);
//        return "home";
//    }
//
//    @RequestMapping(value = "/taggedPages/{tag}", method = RequestMethod.GET)
//    @ResponseBody
//    public List<StaticPageContainer> getPagesByTag(@PathVariable("tag") String tag) {
//        tag = "#" + tag;
//        List<StaticPageContainer> staticPageContainerList = new ArrayList<>();
//        List<StaticPage> staticPages = dao.getStaticPageByTag(tag);
//
//        for (StaticPage staticPage : staticPages) {
//
//            StaticPageContainer staticPageContainer = new StaticPageContainer();
//
//            TagContainer tagContainer = new TagContainer();
//            tagContainer.setTagList(tagDao.getPageTags(staticPage.getPageId()));
//            staticPageContainer.setTagContainer(tagContainer);
//
//            CategoryContainer categoryContainer = new CategoryContainer();
//            //categoryContainer.setCategoryList(categoryDao.getPageCategories(staticPage.getPageId()));
//            staticPageContainer.setCategoryContainer(categoryContainer);
//
//            staticPageContainer.setStaticPage(staticPage);
//
//            staticPageContainerList.add(staticPageContainer);
//        }
//        return staticPageContainerList;
//
//    }
//
//}
