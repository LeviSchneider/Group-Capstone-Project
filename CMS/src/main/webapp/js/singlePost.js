/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {


    if ($('#post-id').val().length !== 0) {

        populatePostData();

    }

});

function populatePostData() {
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: '/CMS/blogPost/' + $('#post-id').val()

    }).success(function (blogPostContainer, status) {

        var tagList = blogPostContainer.tagContainer.tagList;
        //var categoryList = blogPostContainer.categoryContainer.categoryList;

        blogPanel
                .append($('<div>')
                        .addClass("panel panel-default")
                        .append($('<div>')
                                .addClass('panel-heading')
                                .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.timeCreated + ')'
                                        + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                        + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'))
                        .append($('<div>')
                                .addClass('panel-body')
                                .append(blogPostContainer.blogPost.postBody))
                        .append($('<div>')
                                .addClass('panel-footer')
                                .attr({'id': 'post' + blogPostContainer.blogPost.postId})
                                ));
        $.each(tagList, function (index, tag) {

            $('#post' + blogPostContainer.blogPost.postId)
                    .append($('<span>')
                            .addClass('panel-body-blogtags')
                            .append(tag + " "));
        });
//        $.each(categoryList, function (index, category) {
//            $('#post' + blogPostContainer.blogPost.postId)
//                    .append($('<span>')
//                            .addClass('panel-body-blogcategories')
//                            .append("(In category: " + category.categoryName + ")"));
//        });
    });
}