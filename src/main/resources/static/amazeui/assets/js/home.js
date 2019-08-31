$(document).ready(function () {

    $.get("/selfmanagement/weight", function (data, status) {


        //alert(data.height);


        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数
        var option = {
            title: {
                text: '我的体重折线图'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['', '体重(公斤)']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: data.times
            },
            yAxis: {
            	//scale: true,
                type: 'value',
                min: 70,
                max: 80,
                
            },
            series: [
                {
                    name: '身高(厘米)',
                    type: 'line',
                    stack: '总量',
                    data: data.height
                },
                {
                    name: '体重(公斤)',
                    type: 'line',
                    stack: '总量',
                    data: data.weight
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);

    });
});
