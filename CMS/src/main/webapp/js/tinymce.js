/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#tiny-submit').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'content',
            data: JSON.stringify({
                dateSubmitted: '2016-12-28',
                title: 'This is a title',
                postBody: $('#htmlOutput').val(),
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