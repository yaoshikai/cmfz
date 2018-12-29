<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">


        $(function () {
            /*初始化输入框*/
            $("#inputTwo").textbox({
                required: true,
                width: 211
            });

            /*初始化发送按钮*/
            $("#sendBtnTwo").linkbutton({
                text: "发送消息",
                plain: true,
                iconCls: "icon-ok",
                onClick: function () {
                    var goEasy = new GoEasy({
                        appkey: "BC-a65c5d1a24504b399fb5c85e23aa7b9e"
                    });
                    goEasy.publish({
                        channel: "chat2",
                        message: $("#inputTwo").val()
                    });
                    $("#text2").append("赵彩虹(我):" + $("#inputTwo").val() + "&nbsp;" + new Date().getFullYear() + "/" + new Date().getMonth() + "/" + new Date().getDate() + "&nbsp;" + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds() + "&#10;");
                    $("#inputTwo").textbox("clear");
                }
            });


            /*接收消息*/
            var goEasy = new GoEasy({
                appkey: "BC-a65c5d1a24504b399fb5c85e23aa7b9e"
            });
            goEasy.subscribe({
                channel: "chat1",
                onMessage: function (message) {
                    $("#text2").append("姚世凯:" + message.content + "&nbsp;" + new Date().getFullYear() + "/" + new Date().getMonth() + "/" + new Date().getDate() + "&nbsp;" + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds() + "&#10;");
                }
            });
        });
    </script>
</head>
<body>
<p1><b>Chat2</b></p1>
<table>
    <tbody id="tbody">
    <tr>
        <td><textarea id="text2" style="width: 300;height: 200" cols="500" rows="500"></textarea></td>
    </tr>
    </tbody>
</table>
<table style="width: 300">
    <tr>
        <td align="left"><input id="inputTwo"/></td>
        <td align="right"><a id="sendBtnTwo"></a></td>
    </tr>
</table>

</body>
</html>
