<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    var toolbar = [{
        text: "冻结用户",
        iconCls: 'icon-no',
        handler: function () {
            var row = $("#showUserTable").edatagrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请先选择用户"
                );
            } else {
                if (row.status == "Y") {
                    $.post("${pageContext.request.contextPath}/user/updateUser", "id=" + row.id + "&status=N", function () {
                        $("#showUserTable").edatagrid("reload");
                        $.messager.show({
                            title: "系统提示",
                            msg: "冻结成功!",
                            showType: "show",
                            width: 300,
                            height: 200
                        });
                    });
                } else {
                    $.messager.alert(
                        "提示",
                        "此用户是冻结状态，不能再次冻结!"
                    );
                }
            }
        }
    }, {
        text: "解冻用户",
        iconCls: 'icon-ok',
        handler: function () {
            var row = $("#showUserTable").edatagrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请先选择用户"
                );
            } else {
                if (row.status == "N") {
                    $.post("${pageContext.request.contextPath}/user/updateUser", "id=" + row.id + "&status=Y", function () {
                        $("#showUserTable").edatagrid("reload");
                        $.messager.show({
                            title: "系统提示",
                            msg: "解冻成功!",
                            showType: "show",
                            width: 300,
                            height: 200
                        });
                    });
                } else {
                    $.messager.alert(
                        "提示",
                        "此用户是自由状态，不能再次解冻!"
                    );
                }
            }
        }
    }, {
        text: "导出数据",
        iconCls: 'icon-back',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/user/exportUser";
        }
    }];

    $(function () {
        $("#showUserTable").edatagrid({
            url: "${pageContext.request.contextPath}/user/queryAllUser",
            columns: [[
                {title: "用户手机", field: "phone", width: 1, align: "center"},
                {title: "密码", field: "password", align: "center"},
                {title: "法名", field: "dharma", width: 1, align: "center"},
                {title: "性别", field: "sex", width: 1, formatter: mySex, align: "center"},
                {title: "所在省", field: "province", width: 1, align: "center"},
                {title: "所在市", field: "city", width: 1, align: "center"},
                {title: "状态", field: "status", width: 1, formatter: myStatus, align: "center"}
            ]],
            fitColumns: true,
            fit: true,
            striped: true,
            singleSelect: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 7, 9],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/head-pic/' + rowData.headPic + '" style="height:90px;"></td>' +
                    '<td style="border:0">' +
                    '<p>名字: ' + rowData.name + '</p>' +
                    '<p>个性签名: ' + rowData.sign + '</p>' +
                    '<p>注册日期: ' + rowData.regDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            toolbar: toolbar
        });
    });


    function mySex(value, row, index) {
        if (value == "M") return "男";
        if (value == "F") return "女";
    }

    function myStatus(value, row, index) {
        if (value == "Y") return "未冻结";
        if (value == "N") return "已冻结";
    }
</script>


<table id="showUserTable"></table>