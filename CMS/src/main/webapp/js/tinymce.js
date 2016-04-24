/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();

    $('#tiny-submit').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'blogPost',
            data: JSON.stringify({
                dateSubmitted: '2016-12-28',
                startDate: $('#start-date').val(),
                endDate: $('#end-date').val(),
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
