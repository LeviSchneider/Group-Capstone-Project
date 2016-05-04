/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    populateTagBlogPosts();


});


function populateTagBlogPosts() {

    var data = $('#tag-to-populate').val();
    var author = "The Big Cheese";

    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: '/CMS/taggedPosts/' + data

    }).success(function (data, status) {

        $.each(data, function (index, blogPostContainer) {

            var tagList = blogPostContainer.tagContainer.tagList;
            //var categoryList = blogPostContainer.categoryContainer.categoryList;
            var readMoreLink = "";
            if ((blogPostContainer.blogPost.postBody).length === 400) {

                readMoreLink = '<br/><br/><a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '">Read More...</a>';
            }
            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append('<h3 class="blog-post-title">' + blogPostContainer.blogPost.title + '</h3>'
                                            + '<p class="blog-post-meta">' + blogPostContainer.blogPost.timeCreated + ' by ' + author + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs pull-right">'
                                            + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>' + '</p>'
                                            ))
                            .append($('<div>')
                                    .addClass('panel-body')
                                    .append(blogPostContainer.blogPost.postBody + readMoreLink))
                            .append($('<div>')
                                    .addClass('panel-footer')
                                    .attr({'id': 'post' + blogPostContainer.blogPost.postId})
                                    ));
            $.each(tagList, function (index, tag) {
                $('#post' + blogPostContainer.blogPost.postId)
                        //.append($('<div>')).addClass('btn-group')
                        .append($('<a>').attr({'href': '/CMS/taggedPostsDisplay/' + tag})
                        .append($('<button>')
                                .addClass('btn btn-primary btn-xs btn-xs-margin')
                                .append(tag + " ")));
            });
        });
    });

}
