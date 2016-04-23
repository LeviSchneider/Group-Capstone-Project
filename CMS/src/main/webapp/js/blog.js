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

function readMap() {
    $.ajax({
        type: 'GET',
        url: 'tag/3',
    }).success(function (data, tagMap) {
        var tagRanking = [];
        for (var key in tag.rankedTags)
        {
            tagRanking.push(tag.rankedTags[key] + " :" + key);
        }
        $.each(tagRanking, function (index, tag) {
            $.append();
        });
    });
}


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
    loadTags();
    $(document).ready(function () {
        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas-left').toggleClass('active');
        });

        $('[data-toggle=offcanvasright]').click(function () {
            $('.row-offcanvas-right').toggleClass('active');
        });
    });

});

function populateBlogPosts(data, status) {
    var blogPanel = $('#blog-post-display');
    $.ajax({
        type: 'GET',
        url: 'blogPosts'

    }).success(function (data, status) {

        $.each(data, function (index, post) {

            blogPanel
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append(post.postBody)
                            .append($('<div>')
                                    .attr({'id': 'post-tags' + post.postId})
                                    ));

            $.ajax({
                type: 'GET',
                url: 'postTags/' + post.postId

            }).success(function (data, status) {

                var tagList = data.tagList;

                $.each(tagList, function (index, tag) {

                    $('#post-tags' + post.postId)
                            .append($('<span>')
                                    .addClass("panel-body-blogtags")
                                    .append(tag));
                }); //duplicates blog posts
            });

        });
    });
}

function loadTags() {
    var tagCloud = $('#tagcloud');
    var tagString = "";
    var counter = 0;
    $.ajax({
        type: 'GET',
        url: 'tags/10'
    }).success(function (data, status) {
        $.each(data, function (index, tagMap) {
            if (counter === 2)
            {
                for (var key in tagMap) {
                    if (tagMap[key] <= 3) {
                        tagString += "<a>";
                        tagString += " #" + key;
                        tagString += "</a>";
                    } else if (tagMap[key] <= 7) {
                        tagString += "<a style='font-size:150%'>";
                        tagString += " #" + key;
                        tagString += "</a>";
                    } else {
                        tagString += "<a style='font-size:200%'>";
                        tagString += " #" + key;
                        tagString += "</a>";
                    }
                }

                tagCloud.addClass("panel panel-default")
                        .append('<div>')
                        .addClass("panel-body")
                        .append(tagString)
                        .append($('<div>'));
            }
            counter++;
        });
    });
}
