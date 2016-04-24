/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();

    if ($('#post-to-edit-id').val().length !== 0) {
        
        populateEditPostData();
        
    }
    
    $('#tiny-save').click(function (event) {

        event.preventDefault();
        $('#post-status').val();
        createPost();

    });

    $('#tiny-publish').click(function (event) {

        event.preventDefault();
        $('#post-status').val('Published');
        updatePost();
        //this only redirects on a Publish.
        // hitting Save does not redirect 
        // autosave does not redirect
        //However if you try to publish a saved post, it doesn't update
        //so we need to have the Update functionality working first

        //window.location = 'blog';
    });

    //autosaves every 1 minute
    //setInterval(createPost, 60000);

});

function createPost() {

    $.ajax({
        type: 'POST',
        url: '/CMS/blogPost',
        data: JSON.stringify({
            dateSubmitted: '2016-12-28',
            startDate: $('#start-date').val(),
            endDate: $('#end-date').val(),
            title: $('#post-title').val(),
            postBody: tinyMCE.activeEditor.getContent(),
            status: $('#post-status').val(),
            postType: 'blogPost'
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        var postId = data.postId;
        $('#tiny-blogpost-id').val(postId);
        //var tagString = $('#csvHashTags').val();
        var category = $('#categories').val();
//            if (tagString !== "") {
//                $.ajax({
//                    type: 'POST',
//                    url: 'tag/' + postId,
//                    data:
//                            tagString
//                    ,
//                    headers: {
//                        'Accept': 'text/plain',
//                        'Content-Type': 'text/plain'
//
//                    },
//                    'dataType': 'json'
//                });
//            }

        if (category !== "none") {
            $.ajax({
                type: 'POST',
                url: '/CMS/category/' + postId,
                data: JSON.stringify({
                    categoryId: category

                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            });
        }


    });
}
function updatePost() {
    alert($('#post-status').val());
    var postId = $('#tiny-blogpost-id').val();
    $.ajax({
        type: 'PUT',
        url: '/CMS/blogPost/' + postId,
        data: JSON.stringify({
            dateSubmitted: '2016-12-28',
            startDate: $('#start-date').val(),
            endDate: $('#end-date').val(),
            title: $('#post-title').val(),
            postBody: tinyMCE.activeEditor.getContent(),
            status: $('#post-status').val(),
            postType: 'blogPost'
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {

        var category = $('#categories').val();
        if (category !== "none") {
            $.ajax({
                type: 'POST',
                url: 'category/' + postId,
                data: JSON.stringify({
                    categoryId: category

                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            });
        }


    });
}

function loadCategories() {
    var contentDiv = $('#categories');
    $.ajax({
        type: 'GET',
        url: '/CMS/categories'
    }).success(function (data, status) {

        $.each(data, function (index, category) {

            contentDiv
                    .append($('<option>')
                            .attr({'value': category.categoryId})
                            .text(category.categoryName));
        });
    });
}

function addCategoryButton() {
    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'category',
        data: JSON.stringify({
            categoryName: $('#add-category').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#add-category').val('');
        var contentDiv = $('#categories');
        var categoryIdForDropDown;
        contentDiv
                .append($('<option>')
                        .attr({'value': data.category.categoryId})
                        .text(data.category.categoryName));
        categoryIdForDropDown = data.category.categoryId;
        $('#categories').effect("highlight");
        $('#categories').val(categoryIdForDropDown);
    }).error(function (data, status) {
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {

            alert(validationError.message);
        });
    });
}

function populateEditPostData() {
    
    $.ajax({
        type: 'GET',
        url: '/CMS/blogPost/' + $('#post-to-edit-id').val()
        
    }).success(function (blogPostContainer, status) {

            //var categoryList = blogPostContainer.categoryContainer.categoryList;
            alert(blogPostContainer.categoryContainer.categoryList[0].categoryName);
            //$('#start-date').val(blogPostContainer.blogPost.startDate);
            $('#post-title').val(blogPostContainer.blogPost.title);
            $('#htmlOutput').val(blogPostContainer.blogPost.postBody);
            $('#start-date').val(blogPostContainer.blogPost.startDate);
            $('#end-date').val(blogPostContainer.blogPost.endDate);
            $('#tiny-blogpost-id').val(blogPostContainer.blogPost.postId);
            $('select[name="post-status"]').find('option:contains("'+ blogPostContainer.blogPost.status +'")').attr("selected",true);

            $('select[name="categories"]').find('option:contains("'+ blogPostContainer.categoryContainer.categoryList[0].categoryName +'")').attr("selected",true);
           // $("#post-status[option=" + blogPostContainer.blogPost.status + "]").attr('selected', 'selected'); 
//                    .append($('<div>')
//                            .addClass("panel panel-default")
//                            .append($('<div>')
//                                    .addClass('panel-heading')
//                                    .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.dateSubmitted + ')'
//                                            + ' (Status: ' + blogPostContainer.blogPost.status + ')'
//                                            + '<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
//                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))
//                            .append($('<div>')
//                                    .addClass('panel-body')
//                                    .append(blogPostContainer.blogPost.postBody))
//                            .append($('<div>')
//                                    .addClass('panel-footer')
//                                    .attr({'id': 'post' + blogPostContainer.blogPost.postId})
//                                    ));
//            
//            $.each(categoryList, function (index, category) {
//
//                $('#post' + blogPostContainer.blogPost.postId)
//                        .append($('<span>')
//                                .addClass('panel-body-blogcategories')
//                                .append("(In category: " + category.categoryName + ")"));
//            });
        });
   
    
}