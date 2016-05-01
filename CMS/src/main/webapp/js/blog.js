/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var limit = 0;
var amount = 10;
$(document).ready(function () {

    populateBlogPosts(limit);
    $(window).scroll(function () {
        if ($(window).scrollTop() === $(document).height() - $(window).height()) {
            limit = limit + amount;
            populateBlogPosts(limit);
        }
    });

});


function populateBlogPosts(limit) {


    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'blogPosts/' + limit

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
                                    .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.timeCreated + ')'
                                            + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'))
                            .append($('<div>')
                                    .addClass('panel-body')
                                    .append(blogPostContainer.blogPost.postBody + readMoreLink))
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
