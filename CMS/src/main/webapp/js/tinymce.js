/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadTags();
    loadCategories();

    $('#tiny-submit').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'blogPost',
            data: JSON.stringify({
                dateSubmitted: '2016-12-28',
                title: 'This is a title',
                postBody: tinyMCE.activeEditor.getContent(),
                status: 'draft',
                postType: 'blogPost'
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            var postId = data.postId;
            var tagString = $('#csvHashTags').val();
            var category = $('#categories').val();

            $.ajax({
                type: 'POST',
                url: 'tag/' + postId,
                data:
                        tagString
                ,
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plain'

                },
                'dataType': 'json'
            });
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
            window.location = 'blog';
        });
    });
});

function loadTags() {
    clearTagsDiv();

    var contentDiv = $('#contentDiv');

    $.ajax({
        type: 'GET',
        url: 'tags'
    }).success(function (data, status) {

        $.each(data.tagList, function (index, post) {

            contentDiv
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append(post));
        });

    });
}

function clearTagsDiv() {
    $('#contentDiv').empty();
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

