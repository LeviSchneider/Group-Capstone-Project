/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var validationError = "";

$(document).ready(function () {



    if ($('#post-to-edit-id').val().length !== 0) {

        populateEditPostData();

    }

    $('#tiny-save').click(function (event) {

        if (formIsValid()) {
            event.preventDefault();
            $('#tiny-save').effect("highlight");
            createPost();
        } else {
            alert(validationError);
            validationError = "";
        }


    });

    $('#tiny-publish').click(function (event) {

        event.preventDefault();

        $('#post-status').val('PUBLISHED');

        if ($('#post-status').val()) {

            createPost();

        } else {

            $('#post-status').val('DRAFT');
            createPost();

        } 

        window.location = '/CMS/blog';
    });

    //autosaves every 1 minute
    //setInterval(createPost, 60000);

});

function createPost() {

    var postId = $('#tiny-blogpost-id').val();
    var url;
    var putOrPost;
    if (postId.length === 0) {
        url = '/CMS/blogPost';
        putOrPost = 'POST';
    } else {
        putOrPost = 'PUT';
        url = '/CMS/blogPost/' + postId;
    }

    $.ajax({
        type: putOrPost,
        url: url,
        data: JSON.stringify({
            timeCreated: "2016-04-27",
            timeEdited: "2016-04-27",
            startDate: $('#start-date').val(),
            endDate: $('#end-date').val(),
            title: $('#post-title').val(),
            postBody: tinyMCE.activeEditor.getContent(),
            status: $('#post-status').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {

//        $('#tiny-blogpost-id').val(data.postId);
//        var category = $('#categories').val();
//        if (category !== "none") {
//            $.ajax({
//                type: 'POST',
//                url: '/CMS/category/' + $('#tiny-blogpost-id').val(),
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


    });
}


function populateEditPostData() {

    $.ajax({
        type: 'GET',
        url: '/CMS/blogPostAdmin/' + $('#post-to-edit-id').val()

    }).success(function (blogPostContainer, status) {

        $('#post-title').val(blogPostContainer.blogPost.title);
        $('#htmlOutput').val(blogPostContainer.blogPost.postBody);
        $('#start-date').val(blogPostContainer.blogPost.startDate);
        $('#end-date').val(blogPostContainer.blogPost.endDate);
        $('#tiny-blogpost-id').val(blogPostContainer.blogPost.postId);
        $('select[name="post-status"]').find('option:contains("' + blogPostContainer.blogPost.status + '")').attr("selected", true);

    });


}
function formIsValid() {

    var result = true;
    if ($('#post-title').val().length <= 0) {
        validationError += "A title is required!\n";
        result = false;
    }
    if (tinyMCE.activeEditor.getContent().length <= 0) {
        validationError += "The body of your post is required!\n";
        result = false;
    }

    return result;
}
