
layui.define(['layer', 'form','element','jquery','laydate','upload'], function(exports){
    var layer = layui.layer,
        form = layui.form(),
        $ = layui.jquery,
        element = layui.element(),
        laydate = layui.laydate,
        upload=layui.upload();
    //上传
    layui.upload({
        url: '' //上传接口
        ,success: function(res){ //上传成功后的回调
            console.log(res)
        }
    });
    $('.layui-upload-icon').html('<i class="layui-icon">&#xe621;</i> 批量导入区域')

    //时间
    var start = {
        min: laydate.now()
        ,max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };

    var end = {
        min: laydate.now()
        ,max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    $('#LAY_demorange_s').click(function(){
        start.elem = this;
        laydate(start);
    });
    $('#LAY_demorange_e').click(function(){
        end.elem = this
        laydate(end);
    });

    // 点击跳转页面显示
    var _table=null;
    var _table1=null;
    /**
     * 参数封装
     *
     */
    var paramsMethod = {
        getQueryCondition: function (data) {
            var param = {};
            //组装排序参数
            param.draw = data.draw;
            param.currentPage = this.currentPage(data);
            param.pageSize = data.length;
            return param;
        },
        currentPage: function (data) {
            return (data.start / data.length) + 1;
        }
    };
    function  queryList(){
        var $wrapper = $('#div-table-container');
        var $table = $('#dateTables');
        if(_table==null){
            _table = $table.dataTable($.extend(true, {
                    "scrollY": 230,
                    "scrollX": true},
                CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
                    ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                        //手动控制遮罩
//				$wrapper.spinModal();
                        //封装请求参数
                        var param = paramsMethod.getQueryCondition(data);
                        $.ajax({
                            type: "GET",
                            url: 'layui/media/js/area.json',
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            cache: false,	//禁用缓存
                            data: param,	//传入已封装的参数
                            dataType: "json",
                            success: function (msg) {
                                var totalRows = msg.totalRows;
                                var items = msg.items;
                                //封装返回数据，这里仅演示了修改属性名
                                var returnData = {};
                                returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = totalRows;
                                returnData.recordsFiltered = totalRows;//后台不实现过滤功能，每次查询均视作全部结果
                                returnData.data = items;
                                //关闭遮罩
                                //$wrapper.spinModal(false);
                                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                                callback(returnData);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert("查询失败");
                                //$wrapper.spinModal(false);
                            }
                        });
                    },
                    columns: [
                        {
                            width: '',
                            data: "AREA_CODE",
                            defaultContent: "",
                            orderable: false,
                        },
                        {
                            data: "AREA_NAME",
                            defaultContent: "",
                            width: '',
                            orderable: false,
                        },
                        {
                            data: "PARENT_AREA_CODE",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            data: "AREA_LEVEL",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            orderable: false,
                            data: "VALID_TAG",
                            defaultContent: "",
                            width: ""
                        },
                        {
                            orderable: false,
                            data: "PROVINNCE_CODE",
                            defaultContent: "",
                            width: ""
                        },

                        {
                            orderable: false,
                            defaultContent: "",
                            width: "150px",
                            render: function (data, type, row, meta) {
                                return  '<a class="set-a" href="javascript:;" onclick="layer.queryHandle(this)">查看详情</a>' +
                                    '<a class="set-a" href="javascript:;" onclick="layer.editHandle(this)">编辑</a>' +
                                    '<a class="set-a" href="javascript:;" onclick="layer.delHandle(this)">删除</a>';
                            }
                        }
                    ],

                    "drawCallback": function (settings) {
                        //默认顶部分页隐藏
                        $(".row-fluid", $wrapper).eq(0).hide();
                    }
                })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        }else{
            _table.draw();
        }
    }

    //删除
    layer.delHandle=function (obj) {
        layer.alert('确定要删除该区域？', {
            title:'删除',

            icon: 3,
            skin: 'layer-ext-moon'
        })
    }
    //查看详情
    layer.queryHandle=function (obj) {
        //页面层
        layer.open({
            title:'<span>查看详情</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '460px'], //宽高
            content: $('.query-wrap'),
            move: false,
            btn: ['确定','取消'] //按钮
        });
    }
    //编辑
    layer.editHandle=function (obj) {
        //页面层
        layer.open({
            title:'<span>编辑</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '420px'], //宽高
            content: $('.edit-wrap'),
            move: false,
            btn: ['确定','取消'] //按钮
        });
    }
    //新增
    $('.news-add').click(function(){
        //页面层
        layer.open({
            title:'<span>新增</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '420px'], //宽高
            content: $('.edit-wrap'),
            move: false,
            btn: ['确定','取消'] //按钮
        });
    })
    $(function () {
        queryList();
    })
    $(window).resize(function (){
        queryList();
    });


    exports('area', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
