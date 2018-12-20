<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    var toolbar = [{
        text: "添加",
        iconCls: 'icon-add',
        handler: function () {
            $("#addBannerDialog").dialog("open");
        }
    }, '-', {
        text: "修改",
        iconCls: 'icon-edit',
        handler: function () {
            var row = $("#showBannerTable").datagrid("getSelected");
            if (row != null) {
                var index = $("#showBannerTable").edatagrid("getRowIndex", row);
                $("#showBannerTable").edatagrid("editRow", index);
            } else {
                $.messager.alert(
                    "提示",
                    "请先选择您要修改的行!",
                    "error"
                );
            }
        }
    }, '-', {
        text: "取消修改",
        iconCls: 'icon-no',
        handler: function () {
            $("#showBannerTable").edatagrid("cancelRow");
        }
    }, '-', {
        text: "删除",
        iconCls: 'icon-remove',
        handler: function () {
            var row = $("#showBannerTable").datagrid("getSelected");
            if (row !== null) {
                $.post("${pageContext.request.contextPath}/banner/deleteBanner", "bannerId=" + row.bannerId, function () {
                    $.messager.show({
                        title: "系统提示",
                        msg: "删除成功!",
                        showType: "show",
                        width: 300,
                        height: 200
                    });
                    $("#showBannerTable").datagrid("reload");
                });
            } else {
                $.messager.alert(
                    "提示",
                    "请先选择您要删除的行!",
                    "error"
                );
            }
        }
    }, '-', {
        text: "保存",
        iconCls: 'icon-save',
        handler: function () {
            $("#showBannerTable").edatagrid("saveRow");
            $.messager.show({
                title: "系统提示",
                msg: "修改成功!",
                showType: "show",
                width: 300,
                height: 200
            });
        }
    }];


    $(function () {
        $("#showBannerTable").edatagrid({
            url: "${pageContext.request.contextPath}/banner/queryAllBanner",
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            columns: [[
                {field: 'title', title: '标题', width: 100, align: 'center'},
                {
                    field: 'status',
                    title: '是否显示',
                    formatter: myStatus,
                    width: 100,
                    align: 'center',
                    editor: {type: "text"}
                },
                {field: 'pubDate', title: '发布日期', width: 100, align: 'center'}
            ]],
            fitColumns: true,
            fit: true,
            singleSelect: true,
            pagination: true,
            pageSize: 3,
            pageList: [3, 5, 7, 9],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:90px;"></td>' +
                    '<td style="border:0">' +
                    '<p>标题: ' + rowData.title + '</p>' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>发布日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            toolbar: toolbar
        });

        /*初始化添加对话框*/
        $("#addBannerDialog").dialog({
            title: "添加轮播图",
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
            href: "${pageContext.request.contextPath}/main/banner/add-banner.jsp"
        });

    });


    function myStatus(value, row, index) {
        if (value == "Y") {
            return "显示";
        } else {
            return "不显示";
        }
    }
</script>

<table id="showBannerTable"></table>
<div id="addBannerDialog"></div>