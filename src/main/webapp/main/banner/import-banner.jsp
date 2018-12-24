<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#importTable").datagrid({
            columns: [[
                {field: 'title', title: '标题', width: 100, align: 'center'},
                {
                    field: 'status',
                    title: '是否显示',
                    formatter: myStatus,
                    width: 100,
                    align: 'center',
                    editor: {type: "text", options: {required: true}}
                },
                {field: 'pubDate', title: '发布日期', width: 100, align: 'center'}
            ]],
            singleSelect: true,
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
            }
        });

        $("#importTable").datagrid("load", "${pageContext.request.contextPath}/banner/importBanner");

    });
</script>

<table id="importTable"></table>
