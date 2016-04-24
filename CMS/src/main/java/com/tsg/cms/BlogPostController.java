/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dto.BlogPost;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tsg.cms.dao.BlogPostDbDao;
import com.tsg.cms.dao.CategoryDbDao;
import com.tsg.cms.dao.TagDbDao;
import com.tsg.cms.dto.BlogPostContainer;
import com.tsg.cms.dto.CategoryContainer;
import com.tsg.cms.dto.TagContainer;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author apprentice
 */
//It may be more desirable to bifurcate blogPost into
//"static" pages and blog posts.
@Controller
public class BlogPostController {

    private final BlogPostDbDao dao;
    private final TagDbDao tagDao;
    private final CategoryDbDao categoryDao;
    private final HashTagMatcher hashTagMatcher;

    @Inject
    public BlogPostController(BlogPostDbDao dao, TagDbDao tagDao, CategoryDbDao categoryDao, HashTagMatcher hashTagMatcher) {
        this.dao = dao;
        this.tagDao = tagDao;
        this.categoryDao = categoryDao;
        this.hashTagMatcher = hashTagMatcher;

    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogPostContainer getBlogPost(@PathVariable("id") int id) {

        BlogPostContainer container = new BlogPostContainer();
        container.setBlogPost(dao.getBlogPostById(id));

        TagContainer tagContainer = new TagContainer();
        tagContainer.setTagList(tagDao.getPostTags(id));

        CategoryContainer categoryContainer = new CategoryContainer();
        categoryContainer.setCategoryList(categoryDao.getPostCategories(id));

        container.setTagContainer(tagContainer);
        container.setCategoryContainer(categoryContainer);

        return container;
    }

    @RequestMapping(value = "/blogPost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        blogPost.setUserIdFK(999);
        blogPost = dao.addBlogPost(blogPost);

        String body = blogPost.getPostBody();
        List<String> tags = hashTagMatcher.findHashTags(body);

        for (String tag : tags) {

            tagDao.addTag(tag, blogPost.getPostId());
        }

        return blogPost;
    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") int id) {
        dao.removeBlogPost(id);
    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBlogPost(@PathVariable("id") int id, @RequestBody BlogPost blogPost) {
        blogPost.setPostId(id);

        String body = blogPost.getPostBody();
        List<String> tags = hashTagMatcher.findHashTags(body);

        for (String tag : tags) {
            tagDao.addTag(tag, blogPost.getPostId());
        }

        dao.updateBlogPost(blogPost);
    }
//        
//    @RequestMapping(value = "/pubblogPost", method = RequestMethod.GET)
//    @ResponseBody
//    public List<BlogPost> getPublishedBlogPost () {
//        return dao.getPublishedBlogPost();
//    }

    @RequestMapping(value = "/blogPosts", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogPostContainer> getAllBlogPost() {

        List<BlogPostContainer> blogPostContainerList = new ArrayList<>();
        List<BlogPost> blogPosts = dao.getAllBlogPost();

        for (BlogPost blogPost : blogPosts) {

            BlogPostContainer blogPostContainer = new BlogPostContainer();

            TagContainer tagContainer = new TagContainer();
            tagContainer.setTagList(tagDao.getPostTags(blogPost.getPostId()));
            blogPostContainer.setTagContainer(tagContainer);

            CategoryContainer categoryContainer = new CategoryContainer();
            categoryContainer.setCategoryList(categoryDao.getPostCategories(blogPost.getPostId()));
            blogPostContainer.setCategoryContainer(categoryContainer);

            blogPostContainer.setBlogPost(blogPost);

            blogPostContainerList.add(blogPostContainer);
        }
        return blogPostContainerList;

    }

    @RequestMapping(value = "/tinymce", method = RequestMethod.GET)
    public String showEditor(Map<String, Object> model, HttpSession session) {
      
        session.setAttribute("page", "tinymce");

        session.setAttribute("js_page", "tinymce.js");
        return "home";
    }

    @RequestMapping(value = "/tinymce/{id}", method = RequestMethod.GET)
    public String showPopulatedEditor(@PathVariable("id") int id, Map<String, Object> model, HttpSession session) {
        
        //model.put("blogPost", dao.getBlogPostById(id));
        session.setAttribute("page", "tinymce");

        session.setAttribute("js_page", "tinymce.js");
        model.put("editBlogPostId", id);
        return "home";
    }

}
