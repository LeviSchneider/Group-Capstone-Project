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


]

$(document).ready(function () {
    populateBlogPosts(samplePosts);

    $(document).ready(function () {
        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas-left').toggleClass('active');
        });

        $('[data-toggle=offcanvasright]').click(function () {
            $('.row-offcanvas-right').toggleClass('active');
        });
    });

})



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

