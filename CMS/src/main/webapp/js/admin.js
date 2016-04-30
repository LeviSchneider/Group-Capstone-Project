/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();
    loadStaticPages();
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

function loadStaticPages(data, status) {

    var staticPagePanel = $('#staticPageContentRows');
    clearStaticPageTable();
    $.ajax({
        type: 'GET',
        url: '/CMS/staticPages'

    }).success(function (data, status) {

        $.each(data, function (index, staticPage) {
            staticPagePanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append('<span>')
                            .append($('<a onclick="addPageToNavBar(' + staticPage.pageId + ')"><button type="button" class="btn btn-default btn-xs">'
                                    + '<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button></a>'))
                            .append($('<a href="/CMS/pageTinyMCE/' + staticPage.pageId + '"><button type="button" class="btn btn-default btn-xs">'
                                    + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))

     
                            .append($('<a>')
                                    .attr({
                                        'href': '/CMS/pagelink/' + staticPage.titleNumber
                                    }).text(staticPage.title))


                            .append($('<a>')
                                    .attr({
                                        'onclick': 'deleteStaticPage(' + staticPage.pageId + ')'
                                    })
                                    .text(' |Delete'))

                            );
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

function clearStaticPageTable() {
    $('#staticPageContentRows').empty();
}

function loadSideBarItems() {

    $('#custom-sidebar-list').empty();
    var sideBar = $('#custom-sidebar-list');

    $.ajax({
        type: 'GET',
        url: '/CMS/sideBarLinks'

    }).success(function (data, status) {
        $('#custom-sidebar-list').val(data.length);
        $.each(data, function (index, sideBarLink) {

            //nextNavBarId++;
            sideBar.append($('<li>')
                    .append('<a href="/CMS/' + sideBarLink.sideBarLinkUrl + '">' + sideBarLink.sideBarLinkName + '</a>')
                    );
        });

    });
}
function addPageToNavBar(pageId) {

    var numberOfLinks = $('#custom-sidebar-list').val();
    numberOfLinks++;
    $.ajax({
        type: 'PUT',
        url: '/CMS/staticPage/' + pageId + '/' + numberOfLinks + '/'

    }).success(function (data, status) {

        loadSideBarItems();
    });
}
