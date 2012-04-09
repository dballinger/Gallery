var braxis = braxis || {};

braxis.Navigation = function () {

};

braxis.Navigation.prototype = {

    initialise:function () {
        $("#navigation li.categories").mouseover(function () {
            $("#categories").removeClass("hidden");
        });
        $("#navigation li.categories").mouseout(function () {
            $("#categories").addClass("hidden");
        });
    }
};