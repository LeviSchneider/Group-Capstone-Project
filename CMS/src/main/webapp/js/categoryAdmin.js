/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();


});

function addCategoryButton() {
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
        loadCategories();
    }).error(function (data, status) {
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {

            alert(validationError.message);

        });
    });

}

function loadCategories(data, status) {
    var categoryPanel = $('#contentRows');
    clearCategoryTable();

    $.ajax({
        type: 'GET',
        url: 'categories'

    }).success(function (data, status) {

        $.each(data, function (index, category) {
            categoryPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append('<span>')
                            .text(category.categoryName)
                            .append($('<a>')
                                    .text('|Edit '))
                            .append($('<a>')
                                    .attr({
                                        'onClick': 'deleteCategory(' + category.categoryId + ')'
                                    })
                                    .text(' |Delete'))
                            );

        });

    });

}

function deleteCategory(categoryId) {
    var answer = confirm("Do you really want to delete this category?");

    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'category/' + categoryId

        }).success(function () {
            loadCategories();
        });
    }
}

function clearCategoryTable() {
    $('#contentRows').empty();
}