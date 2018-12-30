<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $.post("${pageContext.request.contextPath}/firstPage/queryMember", "uid=1", function (res) {
                console.log(res);
            }, "JSON");
        });
    </script>
</head>
<body>
<h2>Hello World!
</h2>
</body>
</html>
