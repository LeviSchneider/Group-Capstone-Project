/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();

    $('#tiny-save').click(function (event) {

        event.preventDefault();
        $('#post-status').val();
        createPost();

    });

    $('#tiny-publish').click(function (event) {

        event.preventDefault();
        $('#post-status').val("Published");
        createPost();
        //this only redirects on a Publish.
        // hitting Save does not redirect 
        // autosave does not redirect
        //However if you try to publish a saved post, it doesn't update
        //so we need to have the Update functionality working first
        
        window.location = 'blog';
    });

    //autosaves every 1 minute
    setInterval(createPost, 60000);

});

function createPost() {
    $.ajax({
        type: 'POST',
        url: 'blogPost',
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
        url: 'categories'
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
