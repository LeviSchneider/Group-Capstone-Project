/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var validationError = "";

$(document).ready(function () {
    loadCategories();


    if ($('#staticpage-to-edit-id').val().length !== 0) {

        populateEditStaticPageData();

    }

    $('#tiny-save').click(function (event) {

        if (formIsValid()) {
            event.preventDefault();
            $('#tiny-save').effect("highlight");
            createStaticPage();
        } else {
            alert(validationError);
            validationError = "";
        }


    });

    $('#tiny-publish').click(function (event) {

        event.preventDefault();



        createStaticPage();

        $('#staticpage-status').val('PUBLISHED');

        if ($('#staticpage-status').val()) {

            createStaticPage();

        } else {

            $('#staticpage-status').val('DRAFT');
            createStaticPage();

        }

    });

    //autosaves every 1 minute
    //setInterval(createStaticPage, 60000);

});

function createStaticPage() {
    var pageId = $('#tiny-staticpage-id').val();
    var url;
    var putOrPost;
    if (pageId.length === 0) {
        url = '/CMS/staticPage';
        putOrPost = 'POST';
    } else {
        putOrPost = 'PUT';
        url = '/CMS/staticPage/' + pageId;
    }

    $.ajax({
        type: putOrPost,
        url: url,
        data: JSON.stringify({
            timeCreated: '2016-12-28',
            timeEdited: '2016-12-28',
            startDate: $('#start-date').val(),
            endDate: $('#end-date').val(),
            title: $('#staticpage-title').val(),
            pageBody: tinyMCE.activeEditor.getContent(),
            status: $('#staticpage-status').val(),
            categoryIdFK: $('#categories').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {


        $('#tiny-staticpage-id').val(data.pageId);


//        var category = $('#categories').val();
//        if (category !== "none") {
//            alert($('#tiny-staticpage-id').val());
//            $.ajax({
//                type: 'POST',
//                url: '/CMS/category/' + $('#tiny-staticpage-id').val(),
//                data: JSON.stringify({
//                    categoryId: category
//
//                }),
//                headers: {
//                    'Accept': 'application/json',
//                    'Content-Type': 'application/json'
//                },
//                'dataType': 'json'
//            });
//        }
        //  window.location = '/CMS/pagelink/' + data.titleNumber;

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
        url: '/CMS/category',
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

function populateEditStaticPageData() {

    $.ajax({
        type: 'GET',
        url: '/CMS/staticPage/' + $('#staticpage-to-edit-id').val()

    }).success(function (staticPage, status) {

        $('#staticpage-title').val(staticPage.title);
        $('#htmlOutput').val(staticPage.pageBody);
        $('#start-date').val(staticPage.startDate);
        $('#end-date').val(staticPage.endDate);
        $('#tiny-staticpage-id').val(staticPage.pageId);
        $('select[name="staticpage-status"]').find('option:contains("' + staticPage.status + '")').attr("selected", true);

        if (staticPage.categoryIdFK) {

            $.ajax({
                type: 'GET',
                url: '/CMS/category/' + staticPage.categoryIdFK

            }).success(function (category, status) {

                $('select[name="categories"]').find('option:contains("' + category.categoryName + '")').attr("selected", true);

            });
        }
    });


}
function formIsValid() {

    var result = true;
    if ($('#staticpage-title').val().length <= 0) {
        validationError += "A title is required!\n";
        result = false;
    }
    if (tinyMCE.activeEditor.getContent().length <= 0) {
        validationError += "The body of your page is required!\n";
        result = false;
    }

    return result;
}
