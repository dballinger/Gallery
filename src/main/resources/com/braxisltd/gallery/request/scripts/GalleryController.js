var braxis = braxis || {};

braxis.GalleryController = function () {

};

braxis.GalleryController.prototype = {

    initialise:function () {
        Galleria.loadTheme('/styles/lib/galleria.classic.js');
        $("#gallery").galleria({
            width:1400,
            height:800
        });
        $("#navigation li.categories").mouseover(function () {
            $("#categories").removeClass("hidden");
        });
        $("#navigation li.categories").mouseout(function () {
            $("#categories").addClass("hidden");
        });
    }
};