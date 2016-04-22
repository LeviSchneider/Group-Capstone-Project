/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadTags();

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
            
            $.ajax({
                type: 'POST',
                url: 'tag/' + postId,
                data:
                        tagString
                ,
                headers: {
                    'Accept': 'text/plain',
                    'Content-Type': 'text/plainn'
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
//    }).success(function (data, status) {
//        $.each(data.tagList, function (index, tag) {
//            var tag = tag;
//            var counter = 0;
//            var row = '<div style="col-md-offset-8 col-md-4">';
//            row += '<a href="${pageContext.request.contextPath}/blog>' + tag + '</a>';
//            if(counter === data.length)
//            {
//                row += '</div>';
//            }
//        });
//    });
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

