/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {


    if ($('#page-id').val().length !== 0) {

        populateStaticPageData();

    }

});

function populateStaticPageData() {
    var staticPagePanel = $('#staticpage-display');
    $.ajax({
        type: 'GET',
        url: '/CMS/staticPage/' + $('#page-id').val()

    }).success(function (staticPage, status) {

        //var tagList = blogPostContainer.tagContainer.tagList;
        //var categoryList = blogPostContainer.categoryContainer.categoryList;

        staticPagePanel
                .append($('<div>')
                        .append(staticPage.pageBody));

//        $.each(categoryList, function (index, category) {
//            $('#post' + blogPostContainer.blogPost.postId)
//                    .append($('<span>')
//                            .addClass('panel-body-blogcategories')
//                            .append("(In category: " + category.categoryName + ")"));
//        });
    });
}