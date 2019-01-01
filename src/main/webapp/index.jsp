<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            /*$.post("
            ${pageContext.request.contextPath}/identify/obtain", "phone=15286826080", function (result) {
                console.log(result);
            });*/
            $.post("${pageContext.request.contextPath}/identify/check", "phone=15286826080&code=330393", function (result) {
                console.log(result);
            });
        });
    </script>
</head>
<body>
<h2>Hello World!
</h2>
</body>
</html>
