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
                                    .attr({
                                        'onclick': 'showEditCategoryField($(\'#edit-category-span' + category.categoryId + '\'))'
                                    })
                                    .text('|Edit '))

                            .append($('<a>')
                                    .attr({
                                        'onclick': 'deleteCategory(' + category.categoryId + ')'
                                    })
                                    .text(' |Delete'))
                            .append($('<span>')
                                    .attr({
                                        'id': 'edit-category-span' + category.categoryId

                                    })
                                    .css({
                                        'display': 'none'
                                    })
                                    .append($('<input>')
                                            .attr({
                                                'type': 'text',
                                                'value': category.categoryName,
                                                'id': 'edit-category-name' + category.categoryId
                                            }))
                                    .append($('<input>')
                                            .attr({
                                                'type': 'button',
                                                'id': 'edit-category-button',
                                                'onclick': 'editCategory(' + category.categoryId + ')'

                                            })
                                            .val('Submit'))


                                    ));
        });
    });
}

function showEditCategoryField(span) {

    $(span).toggle();

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

function editCategory(categoryId) {
    var answer = confirm("Do you really want to rename this category?");
    if (answer === true) {
        $.ajax({
            type: 'PUT',
            url: 'category/' + categoryId,
            data: JSON.stringify({
                categoryName: $('#edit-category-name' + categoryId).val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            loadCategories();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {

                alert(validationError.message);
            });
        });
    }

}


function clearCategoryTable() {
    $('#contentRows').empty();
}