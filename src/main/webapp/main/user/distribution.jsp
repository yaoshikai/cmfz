<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script type="text/javascript">
    $(function () {
        var distribution = echarts.init(document.getElementById("distributionUser"));

        var option = {
            title: {
                text: '持明法洲用户分布图',
                subtext: '实地考察',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 4,
                left: 'left',
                top: 'middle',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            }, series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    }
                }, {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    }
                }
            ]
        };

        distribution.setOption(option);

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/queryDistributionUser",
            data: "sex=男",
            dataType: "JSON",
            success: function (data) {
                distribution.setOption({
                    series: [
                        {data: data}, {}
                    ]
                });
            }
        });
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/queryDistributionUser",
            data: "sex=女",
            dataType: "JSON",
            success: function (data) {
                distribution.setOption({
                    series: [
                        {}, {data: data}
                    ]
                });
            }
        });
    });
</script>


<div id="distributionUser" style="width: 600px;height:400px;"></div>