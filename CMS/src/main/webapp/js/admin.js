/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadCategories();
    loadAdminTags();
    loadStaticPages();
    populateBlogPosts();
    populateUnpublishedBlogPosts();
    populateUnpublishedStaticPages();
    populateUsers();

});

function populateUsers() {

    var userPanel = $('#userContentRows');
    $('#userContentRows').empty();
    $.ajax({
        type: 'GET',
        url: 'users'

    }).success(function (data, status) {


        $.each(data, function (index, user) {

            var roleText = "";

            $.ajax({
                //this is not working because the data is parsed before the ajax call is done
                
                async: false,
                type: 'GET',
                url: 'userRoles/' + user.userId

            }).success(function (data, status) {

                $.each(data, function (index, role) {

                    roleText += role + "&nbsp;&nbsp;&nbsp;&nbsp;";
                });
            });

            userPanel
                    .append($('<tr>')
                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span>')
                                            .text(user.userName)
                                            .append($('<a>')

                                                    .append($('<a>')
                                                            .attr({
                                                                'onclick': 'deleteUser(' + user.userId + ')'
                                                            })
                                                            .html('<button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>'))


                                                    )))

                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span>')
                                            .html(roleText))

                                    )

                            );




        });
    });
}

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

var staticPageCategory;
function loadStaticPages(data, status) {

    var staticPagePanel = $('#staticPageContentRows');
    clearStaticPageTable();
    $.ajax({
        type: 'GET',
        url: '/CMS/staticPages'

    }).success(function (data, status) {

        $.each(data, function (index, staticPage) {

            var addOrRemoveSideBarButton = '';
            if (staticPage.sideBarPosition > 0) {
                addOrRemoveSideBarButton = '<a onclick="deletePageFromSideBar(' + staticPage.pageId + ')"><button type="button" class="btn btn-default btn-xs">'
                        + '<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button></a>';
            } else {
                addOrRemoveSideBarButton = '<a onclick="addPageToSideBar(' + staticPage.pageId + ')"><button type="button" class="btn btn-default btn-xs">'
                        + '<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button></a>';
            }

            staticPagePanel
                    .append($('<tr>')
                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<input type="hidden" id="page' + staticPage.pageId + 'isOnSideBar" value="' + staticPage.sideBarPosition + '"/>')
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span>')
                                            .append(addOrRemoveSideBarButton)
                                            .append($('<a href="/CMS/pageTinyMCE/' + staticPage.pageId + '"><button type="button" class="btn btn-default btn-xs">'
                                                    + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))


                                            .append($('<a>')
                                                    .attr({
                                                        'href': '/CMS/pagelink/' + staticPage.titleNumber
                                                    }).text(staticPage.title))


                                            .append($('<a onclick="deleteStaticPage(' + staticPage.pageId + ')"><button type="button" class="btn btn-default btn-xs">'
                                                    + '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></a>'))


                                            ))
                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span id="category' + staticPage.pageId + '">')

                                            )
                                    ));

            if (staticPage.categoryIdFK) {

                $.ajax({
                    type: 'GET',
                    url: '/CMS/category/' + staticPage.categoryIdFK

                }).success(function (category, status) {


                    $('#category' + staticPage.pageId).text(category.categoryName);

                });

            }
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
                                    .html('<button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>'))
                            .append($('<a>')
                                    .attr({
                                        'onclick': 'deleteCategory(' + category.categoryId + ')'
                                    })
                                    .html('<button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>'))
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
            loadStaticPages();

        }).error(function () {

            alert("You do not have permission to delete categories!")

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


// SIDE_BAR_ITEMS: items to be populated through javascript.
function loadSideBarItems() {

    $('#custom-sidebar-list').empty();
    var sideBar = $('#custom-sidebar-list');
    var row;
    $.ajax({
        type: 'GET',
        url: '/CMS/sideBarLinks'

    }).success(function (data, status) {
        $('#custom-sidebar-list').val(data.length);
        var count = 0;
        $.each(data, function (index, sideBarLink) {
            //nextSideBarId++;
            if (count !== sideBarLink.length) {
                row += '<div class="well span2 tile>';
                row += '<a href="/CMS/' + sideBarLink.sideBarLinkUrl + '">' + sideBarLink.sideBarLinkName + '</a>';
                row += '</div>';
            } else if (count === sideBarLink.length) {
                row += '<div class="well span4 tile">';
                row += '<a href="/CMS/' + sideBarLink.sideBarLinkUrl + '">' + sideBarLink.sideBarLinkName + '</a>';
                row += '</div>';
            }
            sideBar.append(row);
            count++;
        });

    });
}

function addPageToSideBar(pageId) {

    var numberOfLinks = $('#custom-sidebar-list').val();
    numberOfLinks++;
    $.ajax({
        type: 'PUT',
        url: '/CMS/staticPage/' + pageId + '/' + numberOfLinks + '/'

    }).success(function (data, status) {

        loadSideBarItems();
        loadStaticPages();
    }).success(function (data, status) {

        alert("You do not have permission to edit sidebar items!");

    });
}

function deletePageFromSideBar(pageId) {

    var answer = confirm("Do you really want to remove this page from the side bar?");
    if (answer === true) {
        $.ajax({
            type: 'PUT',
            url: '/CMS/staticPage/' + pageId + '/0/'

        }).success(function (data, status) {

            loadSideBarItems();
            loadStaticPages();

        }).success(function (data, status) {

            alert("You do not have permission to edit sidebar items!");

        });
    }

}

function populateBlogPosts(data, status) {
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'blogPostsAdmin'

    }).success(function (data, status) {

        $.each(data, function (index, blogPostContainer) {

            //var tagList = blogPostContainer.tagContainer.tagList;
            //var categoryList = blogPostContainer.categoryContainer.categoryList;

            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append('<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'
                                            + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'
                                            + blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.timeCreated + ')'
                                            + ' (Status: ' + blogPostContainer.blogPost.status + ')'))


                            .append($('<div>')
                                    .addClass('panel-footer')
                                    .attr({'id': 'post' + blogPostContainer.blogPost.postId})
                                    ));
        });
    });
}

function populateUnpublishedBlogPosts(data, status) {
    var blogPanel = $('#unpublished-blog-post-display');
    $('#unpublished-blog-post-display').empty();
    $.ajax({
        type: 'GET',
        url: 'blogPostsAdminUnpublished'

    }).success(function (data, status) {

        $.each(data, function (index, blogPostContainer) {

            //var tagList = blogPostContainer.tagContainer.tagList;
            //var categoryList = blogPostContainer.categoryContainer.categoryList;

            var statusDropDownFields = $('<select/>');

            statusDropDownFields.attr({id: "post-status" + blogPostContainer.blogPost.postId,
                name: "post-status" + blogPostContainer.blogPost.postId});
            var options = ["DRAFT", "READY_FOR_APPROVAL", "APPROVED", "UNPUBLISHED", "PUBLISHED"];
            for (var i in options) {
                statusDropDownFields.append($('<option/>').html(options[i]));
                if (options[i] === blogPostContainer.blogPost.status) {
                    statusDropDownFields.val(options[i]);

                }
            }


            blogPanel
                    .append($('<tr>')
                            .append($('<td>')

                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append($('<div>')
                                                    .addClass('panel-heading')
                                                    .append('<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'
                                                            + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                                            + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'
                                                            + blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.timeCreated + ')')
                                                    .append(statusDropDownFields).attr({onchange: 'quickChangeBlogPostStatus("' + blogPostContainer.blogPost.postId + '")'}
                                            )))));
        });
    });
}

function quickChangeBlogPostStatus(postId) {


    $.ajax({
        type: 'PUT',
        url: 'adminQuickChangeBlogPostStatus/' + postId + '/' + $('#post-status' + postId).val()

    }).success(function () {
        populateUnpublishedBlogPosts();
    }).error(function () {
        alert("You do not have permission to approve or publish posts!");
    });

}

function populateUnpublishedStaticPages(data, status) {
    var staticPagePanel = $('#unpublished-static-page-display');
    $('#unpublished-static-page-display').empty();
    $.ajax({
        type: 'GET',
        url: 'staticPagesAdminUnpublished'

    }).success(function (data, status) {

        $.each(data, function (index, staticPage) {

            //var tagList = blogPostContainer.tagContainer.tagList;
            //var categoryList = blogPostContainer.categoryContainer.categoryList;

            var statusDropDownFields = $('<select/>');

            statusDropDownFields.attr({id: "page-status" + staticPage.pageId,
                name: "page-status" + staticPage.pageId});
            var options = ["DRAFT", "READY_FOR_APPROVAL", "APPROVED", "UNPUBLISHED", "PUBLISHED"];
            for (var i in options) {
                statusDropDownFields.append($('<option/>').html(options[i]));
                if (options[i] === staticPage.status) {
                    statusDropDownFields.val(options[i]);

                }
            }


            staticPagePanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append($('<div>')
                                    .addClass('panel-heading')
                                    .append('<a href="/CMS/pageTinyMCE/' + staticPage.pageId + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'
                                            + '<a href="/CMS/pagelink/' + staticPage.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                            + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'
                                            + staticPage.title + ' by: Mayor McCheese ')
                                    .append(statusDropDownFields).attr({onchange: 'quickChangeStaticPageStatus("' + staticPage.pageId + '")'})));



        });
    });
}

function quickChangeStaticPageStatus(pageId) {


    $.ajax({
        type: 'PUT',
        url: 'adminQuickChangeStaticPageStatuss/' + pageId + '/' + $('#page-status' + pageId).val()

    }).success(function () {
        populateUnpublishedStaticPages();
    }).error(function () {
        alert("You do not have permission to approve or publish pages!");
    });

}


function deleteStaticPage(pageId) {

    var answer = confirm("Do you really want to delete this static page?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'staticPage/' + pageId

        }).success(function () {
            loadStaticPages();
            loadSideBarItems();
        }).error(function () {
            alert("You do not have permission to delete pages!");
        });
        ;
    }
}

function loadAdminTags() {


    var tagPanel = $('#tag-display');
    tagPanel.empty();
    $.ajax({
        type: 'GET',
        url: 'tags'

    }).success(function (data, status) {

        var tagList = data.tagList;
        $.each(tagList, function (index, tag) {

//var tagList = blogPostContainer.tagContainer.tagList;
//var categoryList = blogPostContainer.categoryContainer.categoryList;

            tagPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append('<span>')
                            .text(tag)
                            .append('<a onclick="deleteTag(\'' + tag + '\')"><button type="button" class="btn btn-default btn-xs">'
                                    + '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></a>'));
        });
    });
}

function deleteTag(tag) {

    var answer = confirm("Do you really want to delete this tag?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'tag/' + tag

        }).success(function () {
            loadAdminTags();
        }).error(function () {
            alert("You do not have permission to delete tags!");
        });

    }
}
