layui.define(['layer', 'form','jquery'], function(exports){
  var layer = layui.layer,
      $ = layui.jquery,
      form = layui.form();
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    },
    pass: [/(.+){5,12}$/, '密码必须5到12位']
  });
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });
  
  exports('login', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});    
