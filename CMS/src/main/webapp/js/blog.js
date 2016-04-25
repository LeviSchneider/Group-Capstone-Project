/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    populateBlogPosts();

    $('[data-toggle=offcanvas]').click(function () {
        $('.row-offcanvas-left').toggleClass('active');
    });

    $('[data-toggle=offcanvasright]').click(function () {
        $('.row-offcanvas-right').toggleClass('active');
    });


});

function readMap() {
    $.ajax({
        type: 'GET',
        url: 'tag/3',
    }).success(function (data, tagMap) {
        var tagRanking = [];
        for (var key in tag.rankedTags)
        {
            tagRanking.push(tag.rankedTags[key] + " :" + key);
        }
        $.each(tagRanking, function (index, tag) {
            $.append();
        });
    });
}


function initMenu() {
    $('#menu ul').hide();
    $('#menu ul').children('.current').parent().show();
    //$('#menu ul:first').show();
    $('#menu li a').click(
            function () {
                var checkElement = $(this).next();
                if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                    return false;
                }
                if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                    $('#menu ul:visible').slideUp('normal');
                    checkElement.slideDown('normal');
                    return false;
                }
            }
    );
}


function populateBlogPosts(data, status) {
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'blogPosts'

    }).success(function (data, status) {

        $.each(data, function (index, blogPostContainer) {

            var tagList = blogPostContainer.tagContainer.tagList;
            var categoryList = blogPostContainer.categoryContainer.categoryList;

            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.dateSubmitted + ')'
                                            + ' (Status: ' + blogPostContainer.blogPost.status + ')'
                                            + '<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))
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
            $.each(categoryList, function (index, category) {
                $('#post' + blogPostContainer.blogPost.postId)
                        .append($('<span>')
                                .addClass('panel-body-blogcategories')
                                .append("(In category: " + category.categoryName + ")"));
            });
        });
    });

}

function clearPopulatedPosts() {
    $('#blog-post-display').empty();
}

function populatedTagPosts(data){
    alert(data);
    clearPopulatedPosts();
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'taggedPosts/' + data
    }).success(function(data, status) {
        $.each(data, function (index, blogPostContainer) {

            var tagList = blogPostContainer.tagContainer.tagList;
            var categoryList = blogPostContainer.categoryContainer.categoryList;

            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.dateSubmitted + ')'
                                            + ' (Status: ' + blogPostContainer.blogPost.status + ')'
                                            + '<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))
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
            $.each(categoryList, function (index, category) {
                $('#post' + blogPostContainer.blogPost.postId)
                        .append($('<span>')
                                .addClass('panel-body-blogcategories')
                                .append("(In category: " + category.categoryName + ")"));
            });
        });
    });
};
