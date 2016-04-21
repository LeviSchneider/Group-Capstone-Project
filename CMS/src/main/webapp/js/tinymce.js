/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadTags();

    $('#tiny-submit').click(function (event) {
        event.preventDefault();
        alert(tinyMCE.activeEditor.getContent());
        $.ajax({
            type: 'POST',
            url: 'content',
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
            $('#htmlOutput').val('');
        });
    });
});

function loadTags() {
    clearTagsDiv();

    var contentDiv = $('#contentDiv');

    $.ajax({
        type: 'GET',
        url: '/tags'
    }).success(function (data, status) {
        $.each(data, function (index, content) {
            var tag = content.tag;
            var counter = 0;
            var row = '<div style="col-md-offset-8 col-md-4">';
            row += '<a href="${pageContext.request.contextPath}/blog>' + tag + '</a>';
            if(counter === data.length)
            {
                row += '</div>';
            }
        });
    });
}