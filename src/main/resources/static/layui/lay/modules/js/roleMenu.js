
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
    $('.layui-upload-icon').html('<i class="layui-icon">&#xe621;</i> 批量导入')

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
    var _table2=null;
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
                            url: 'layui/media/js/roleMenu.json',
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
                            data: "ROLE_ID",
                            defaultContent: "",
                            orderable: false,
                            render: function (data, type, row, meta) {
                                //console.log(data,row)
                                return  '<a class="js-a-del" href="javascript:;" onclick="layer.queryHandle(this)">'+data+'</a>'
                            }
                        },
                        {
                            data: "ROLE_NAME",
                            defaultContent: "",
                            width: '',
                            orderable: false,
                        },
                        {
                            data: "PROVINCE_CODE",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            orderable: false,
                            data: "UPDATE_STAFF_ID",
                            defaultContent: "",
                            width: ""
                        },
                        {
                            orderable: false,
                            data: "UPDATE_TIME",
                            defaultContent: "",
                            width: ""
                        },
                        {
                            orderable: false,
                            data: "UPDATE_DEPART",
                            defaultContent: "",
                            width: ""
                        },
                        {
                            orderable: false,
                            defaultContent: "",
                            width: "150px",
                            render: function (data, type, row, meta) {
                                return  '<a class="layui-btn layui-btn-normal layui-btn-mini" href="javascript:;" onclick="layer.allotHandle(this)">权限分配</a>' +
                                        '<a class="layui-btn layui-btn-danger layui-btn-mini" href="javascript:;" onclick="layer.recoverHandle(this)">权限回收</a>';
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



    function  queryListDepart(){
        var $table1 = $('#dateTables1');
        if(_table1==null){
            _table1 = $table1.dataTable($.extend(true, {
                    "scrollY": 190,
                    "scrollX": true},
                CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
                    ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                        //封装请求参数
                        var param = paramsMethod.getQueryCondition(data);
                        $.ajax({
                            type: "GET",
                            url: 'layui/media/js/menu.json',
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
                                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                                callback(returnData);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert("查询失败");
                            }
                        });
                    },
                    columns: [
                        {
                            orderable: false,
                            defaultContent: "",
                            width: "30px",
                            render: function (data, type, row, meta) {
                                return  '<input type="checkbox" name="" lay-skin="primary" lay-filter="itemChoose">' ;
                            }
                        },
                        {
                            width: '',
                            data: "MENU_ID",
                            defaultContent: "",
                            orderable: false,
                        },
                        {
                            data: "MENU_NAME",
                            defaultContent: "",
                            width: '',
                            orderable: false,
                        },
                        {
                            data: "MENU_LEVEL",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            data: "MENU_URL",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            data: "VALID_TAG",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        }

                    ],

                    "drawCallback": function (settings) {
                        form.render();
                    }
                })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        }else{
            _table1.draw();

        }

    }

    function  queryShow(){
        var $table2 = $('#dateTables2');
        if(_table2==null){
            _table2 = $table2.dataTable($.extend(true, {
                    "scrollY": 190,
                    "scrollX": true},
                CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
                    ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                        //封装请求参数
                        var param = paramsMethod.getQueryCondition(data);
                        $.ajax({
                            type: "GET",
                            url: 'layui/media/js/menu.json',
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
                                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                                callback(returnData);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert("查询失败");
                            }
                        });
                    },
                    columns: [
                        {
                            width: '',
                            data: "MENU_ID",
                            defaultContent: "",
                            orderable: false,
                        },
                        {
                            data: "MENU_NAME",
                            defaultContent: "",
                            width: '',
                            orderable: false,
                        },
                        {
                            data: "MENU_LEVEL",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            data: "MENU_URL",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        },
                        {
                            data: "VALID_TAG",
                            orderable: false,
                            defaultContent: "",
                            width: '',
                        }

                    ],

                    "drawCallback": function (settings) {
                        form.render();
                    }
                })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        }else{
            _table2.draw();

        }

    }
    $(function () {
        queryList();
        queryListDepart();
        queryShow();
        $(window).resize(function (){
            queryList();
            queryListDepart();
            queryShow()
        });
    })

    allCheckbox('allChooseMenu');
    function allCheckbox(name){
        form.on('checkbox('+name+')', function(data){
            var child = $(data.elem).parents('.dataTables_scrollHead').siblings('.dataTables_scrollBody').find('tbody input[type="checkbox"]');
            child.each(function(index, item){
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });
    }
    form.on('checkbox(itemChoose)',function(data){
        var allCheckbox = $(data.elem).parents('.dataTables_scrollBody').siblings('.dataTables_scrollHead').find('input[type="checkbox"]');
        var itemList= $(data.elem).parents('tbody').find('input[type="checkbox"]');
        var checkFLag=true;
        itemList.each(function(index,item){
            checkFLag=checkFLag&&item.checked;
        })
        $(allCheckbox[0]).prop('checked',checkFLag);
        form.render('checkbox');
    })

    //权限分配
    layer.allotHandle=function (obj) {
        layer.open({
            title:'<span>权限分配</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '530px'], //宽高
            content: $('.chose-oepart-wrap'),
            move: false,
            // shade: 0,
            btn: ['分配','取消'] //按钮
        });
        queryListDepart();

    }

    //权限回收
    layer.recoverHandle=function (obj) {
        layer.open({
            title:'<span>权限回收</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '530px'], //宽高
            content: $('.chose-oepart-wrap'),
            move: false,
            // shade: 0,
            btn: ['回收','取消'] //按钮
        });
        queryListDepart();

    }

    //角色已有权限查看
    layer.queryHandle=function (obj) {
        layer.open({
            title:'<span>角色已有权限查看</span>',
            type: 1,
            skin: 'layer-skin-news ', //加上边框
            area: ['870px', '530px'], //宽高
            content: $('.query-wrap'),
            move: false,
            // shade: 0,
            btn: ['确定'] //按钮
        });
        queryShow();

    }



    exports('roleMenu', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
