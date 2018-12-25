<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var activeUser = echarts.init(document.getElementById('activeUser'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持明法洲App活跃用户',
                left: 'center'
            },
            tooltip: {},
            legend: {},
            xAxis: {
                data: ["近一周", "近两周", "近三周"]
            },
            yAxis: {}

        };
        // 使用刚指定的配置项和数据显示图表。
        activeUser.setOption(option);

        $.post("${pageContext.request.contextPath}/user/queryActiveUser", "condition1=" + option.xAxis.data[0] + "&condition2=" + option.xAxis.data[1] + "&condition3=" + option.xAxis.data[2], function (result) {
            activeUser.setOption({
                series: [{
                    type: 'bar',
                    data: result
                }]
            });
        }, "JSON");


    });

</script>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="activeUser" style="width: 600px;height:400px;"></div>
