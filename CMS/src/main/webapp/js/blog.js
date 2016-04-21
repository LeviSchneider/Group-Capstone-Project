/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var samplePosts = [
    post1 = {
        title: "stuff",
        body: "Here's some custom content!",
        author: "ted bob"
    },
    post2 = {
        title: "more stuff",
        body: "Here's some extra special custom content!",
        author: "billy bob"
    },
    post3 = {
        title: "this stuff",
        body: "<h1>Here's some super special custom content!</h1>",
        author: "bob bob"
    },
    post4 = {
        title: "super amazing title",
        body: "super amazing content here dave",
        author: "dunno lol"
    }


];

function initMenu() {
    $('#menu ul').hide();
    $('#menu ul').children('.current').parent().show();
    //$('#menu ul:first').show();
    $('#menu li a').click(
            function () {
                var checkElement = $(this).next();
                if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                    return false;
                }
                if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                    $('#menu ul:visible').slideUp('normal');
                    checkElement.slideDown('normal');
                    return false;
                }
            }
    );
}
$(document).ready(function () {
    populateBlogPosts(samplePosts);

});



function populateBlogPosts(data, status) {
    var blogPanel = $('#blog-post-display');


    $.ajax({
        type: 'GET',
        url: 'allcontent'

    }).success(function (data, status) {

        $.each(data, function (index, post) {
            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append(post.postBody));
        });

    });

}
