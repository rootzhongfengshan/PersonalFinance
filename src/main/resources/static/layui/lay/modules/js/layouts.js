
layui.define([ 'element','jquery'], function(exports){
    var $ = layui.jquery;

    var bigSrcVal="";
    $('.big-ul .big-li').each(function(){
        $(this).find('a').click(function(){
            bigSrcVal = $(this).attr('data-src');
            $(this).parent('li').addClass('active').siblings().removeClass('active');
            //console.log(srcVal)
            $('#allFrame').attr('src',bigSrcVal);
        })
    })


    exports('layouts', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
