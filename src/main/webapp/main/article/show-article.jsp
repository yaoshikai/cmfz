<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script type="text/javascript">
    var toolbar = [{
        text: "文章详情",
        iconCls: 'icon-search',
        handler: function () {
            var row = $("#showArticleTable").datagrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请选择文章!"
                );
            } else {
            }
        }
    }, '-', {
        text: "添加文章",
        iconCls: 'icon-add',
        handler: function () {
            $("#addArticleDialog").dialog("open");
        }
    }];

    $(function () {
        $("#showArticleTable").datagrid({
            url: "${pageContext.request.contextPath}/article/queryAllArticle",
            fit: true,
            fitColumns: true,
            singleSelect: true,
            columns: [[
                {field: 'title', title: '标题', width: 100},
                {field: 'pubDate', title: '发布日期', width: 100},
                {field: 'guru', title: '上师法号', formatter: myDharma}
            ]],
            pagination: true,
            pageSize: 3,
            pageList: [3, 5, 7, 9],
            toolbar: toolbar
        });

        $("#addArticleDialog").dialog({
            title: "添加文章",
            iconCls: "icon-tip",
            closed: true,
            modal: true,
            cache: false,
            width: 400,
            height: 500,
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            /*加载添加页面*/
            href: "${pageContext.request.contextPath}/main/article/add-article.jsp"
        });


    });

    function myDharma(value, row, index) {
        return value.dharma;
    }
</script>


<table id="showArticleTable"></table>
<div id="addArticleDialog"></div>