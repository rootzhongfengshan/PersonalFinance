
layui.define([ 'element','jquery'], function(exports){
    var $ = layui.jquery,
        element = layui.element();
    // 点击跳转页面显示
    var srcVal = '';
    $('.left-wrapper .layui-nav .layui-nav-item').each(function(){
        $(this).find('a').click(function(){
            srcVal = $(this).attr('data-src');
            //console.log(srcVal)
            $('#mainFrame').attr('src',srcVal);
        })
    })

    exports('layouts-main', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
