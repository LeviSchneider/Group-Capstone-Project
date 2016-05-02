/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


tinymce.PluginManager.add('respanner', function (editor, url) {
    // Add a button that opens a window
    editor.addButton('respanner', {
        text: 'Auto-resize',
        icon: false,
        onclick: function () {
            tinymce.activeEditor.dom.addClass(tinymce.activeEditor.dom.select('img'), 'img-responsive');
        }
    });


    // Adds a menu item to the tools menu
    editor.addMenuItem('respanner', {
        text: 'Example plugin',
        context: 'tools',
        onclick: function () {
            // Open window with a specific url
            editor.windowManager.open({
                title: 'TinyMCE site',
                url: 'http://www.tinymce.com',
                width: 800,
                height: 600,
                buttons: [{
                        text: 'Close',
                        onclick: 'close'
                    }]
            });
        }
    });
});