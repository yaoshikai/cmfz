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
            $("#inputOne").textbox({
                required: true,
                width: 211
            });


            /*初始化发送按钮*/
            $("#sendBtnOne").linkbutton({
                text: "发送消息",
                plain: true,
                iconCls: "icon-ok",
                onClick: function () {
                    var goEasy = new GoEasy({
                        appkey: "BC-a65c5d1a24504b399fb5c85e23aa7b9e"
                    });
                    goEasy.publish({
                        channel: "chat1",
                        message: $("#inputOne").val()
                    });
                    $("#text1").append("姚世凯(我):" + $("#inputOne").val() + "&nbsp;" + new Date().getFullYear() + "/" + new Date().getMonth() + "/" + new Date().getDate() + "&nbsp;" + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds() + "&#10;");
                    $("#inputOne").textbox("clear");
                }
            });


            /*接收消息*/
            var goEasy = new GoEasy({
                appkey: "BC-a65c5d1a24504b399fb5c85e23aa7b9e"
            });
            goEasy.subscribe({
                channel: "chat2",
                onMessage: function (message) {
                    $("#text1").append("赵彩虹:" + message.content + "&nbsp;" + new Date().getFullYear() + "/" + new Date().getMonth() + "/" + new Date().getDate() + "&nbsp;" + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds() + "&#10;");
                }
            });
        });
    </script>
</head>
<body>
<p1><b>Chat1</b></p1>
<table>
    <tbody id="tbody">
    <tr>
        <td><textarea id="text1" style="width: 300;height: 200" cols="500" rows="500"></textarea></td>
    </tr>
    </tbody>
</table>
<table style="width: 300">
    <tr>
        <td align="left"><input id="inputOne"/></td>
        <td align="right"><a id="sendBtnOne"></a></td>
    </tr>
</table>

</body>
</html>
