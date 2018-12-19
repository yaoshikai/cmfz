<%@ page isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<script type="text/javascript">
    $(function () {
        $.get("${pageContext.request.contextPath}/menu/queryAll", function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#left-accordion").accordion("add", {
                    title: result[i].title,
                    iconCls: result[i].iconcls,
                    content: showSecond(result[i].menuList),
                    selected: false
                });
            }
        }, "JSON");

    });

    function showSecond(list) {
        var ul = $("<ul></ul>");
        for (var i = 0; i < list.length; i++) {
            var li1 = $("<li><a class='easyui-linkbutton' onclick='clickSecond(" + result[i].title + "," + result[i].url + ")'>" + list[i].title + "</a></li>");
            ul.append(li1);
        }
        return ul;
    }

    function clickSecond(title, url) {
        if (("#tt").tabs("exists", title)) {
            $("#tt").tabs("select", title);
        } else {
            $("#tt").tabs("add", {
                title: title,
                closable: true,
                pill: true,
                fit: true
            });
        }
    }
</script>


<div id="left-accordion" class="easyui-accordion" data-options="fit:true"></div>
