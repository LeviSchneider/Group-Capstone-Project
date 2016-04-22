/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

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
import com.tsg.cms.dao.TagDbDao;
import com.tsg.cms.dto.TagContainer;
import javax.validation.Valid;

/**
 *
 * @author apprentice
 */
@Controller
public class TagController {

    private final TagDbDao dao;

    @Inject
    public TagController(TagDbDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/tag/{postId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TagContainer createTag(@PathVariable("postId") int postId, @Valid @RequestBody String tagName) {
        TagContainer tagContainer = new TagContainer();

        String[] tagArray = tagName.split(",");

        for (String s : tagArray) {
            tagContainer.setTagName(dao.addTag(s, postId));
        }

        return tagContainer;

    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("tagName") String tagName) {
        dao.removeTag(tagName);
    }

    @RequestMapping(value = "/tag/{oldTagName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public TagContainer updateTag(@PathVariable("oldTagName") String oldTagName, @RequestBody String newTag) {

        TagContainer tagContainer = new TagContainer();

        tagContainer.setTagName(dao.updateTag(newTag, oldTagName));

        return tagContainer;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @ResponseBody
    public TagContainer getAllTags() {
        TagContainer tagContainer = new TagContainer();

        tagContainer.setTagList(dao.getAllTags());
        return tagContainer;
    }

    @RequestMapping(value = "/tags/{num}", method = RequestMethod.GET)
    @ResponseBody
    public TagContainer getNumberOfTags(@PathVariable("num") int num) {
        TagContainer tagContainer = new TagContainer();
        tagContainer.setRankedTags(dao.getNumberOfTags(num));
        return tagContainer;
    }

    @RequestMapping(value = "/postTags/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public TagContainer getPostTags(@PathVariable("postId") int postId) {
        TagContainer tagContainer = new TagContainer();

        tagContainer.setTagList(dao.getPostTags(postId));
        return tagContainer;
    }
}
