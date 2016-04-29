/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    populateBlogPosts();
});


function populateBlogPosts(data, status) {
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'blogPosts'

    }).success(function (data, status) {

        $.each(data, function (index, blogPostContainer) {

            var tagList = blogPostContainer.tagContainer.tagList;
            //var categoryList = blogPostContainer.categoryContainer.categoryList;

            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.timeCreated + ')'
                                            + ' (Status: ' + blogPostContainer.blogPost.status + ')'
                                            + '<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'
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
//            $.each(categoryList, function (index, category) {
//                $('#post' + blogPostContainer.blogPost.postId)
//                        .append($('<span>')
//                                .addClass('panel-body-blogcategories')
//                                .append("(In category: " + category.categoryName + ")"));
//            });
        });
    });

}

