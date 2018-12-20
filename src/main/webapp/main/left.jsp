<%@ page isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<script type="text/javascript">
    $(function () {
        $.get("${pageContext.request.contextPath}/menu/queryAll", function (result) {
            for (var i = 0; i < result.length; i++) {
                var s1 = "<ul type='circle'>";
                var s2 = "</ul>";
                for (var j = 0; j < result[i].menuList.length; j++) {
                    s1 += "<li><a class='easyui-linkbutton' onclick=\"addTabs('" + result[i].menuList[j].title + "','" + result[i].menuList[j].iconcls + "','" + result[i].menuList[j].url + "')\" data-options=\"iconCls:'" + result[i].menuList[j].iconcls + "'\">" + result[i].menuList[j].title + "</a></li>"
                }
                var s = s1 + s2;
                $("#left-accordion").accordion("add", {
                    title: result[i].title,
                    iconCls: result[i].iconcls,
                    content: s,
                    selected: false
                });
            }
        }, "JSON");

    });

    function addTabs(title, iconcls, url) {
        if ($("#tt").tabs("exists", title)) {
            $("#tt").tabs("select", title);
        } else {
            $("#tt").tabs("add", {
                title: title,
                href: "${pageContext.request.contextPath}" + url,
                closable: true,
                iconCls: iconcls,
                pill: true,
                fit: true
            });
        }
    }

</script>


<div id="left-accordion" class="easyui-accordion" data-options="fit:true"></div>