<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#addArticleSubmitBtn").linkbutton({
            text: "提交",
            iconCls: "icon-save",
            plain: true,
            onClick: function () {
                $("#addArticleForm").form("submit", {
                    url: "${pageContext.request.contextPath}/article/addArticle",
                    onSubmit: function () {
                        return $("#addArticleForm").form("validate");
                    },
                    success: function () {
                        $("#showArticleTable").datagrid("reload");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功!",
                            showType: "show",
                            width: 300,
                            height: 200
                        });
                        $("#addArticleDialog").dialog("close");
                    }
                });
            }
        });

        $("#addArticleBackBtn").linkbutton({
            text: "返回上级",
            iconCls: "icon-back",
            plain: true,
            onClick: function () {
                $("#addArticleDialog").dialog("close");
            }
        });


        $("#articleTitle").textbox({
            required: true
        });
        $("#articleImg").filebox({
            required: true,
            editable: false,
            buttonText: "选择文件"
        });
        $("#guruCombobox").combobox({
            url: "${pageContext.request.contextPath}/guru/queryAllGuru",
            valueField: "id",
            textField: "dharma",
            editable: false,
            onLoadSuccess: function (data) {
                $("#guruCombobox").combobox("setValue", data[0].id);
            }
        });
    })
</script>


<form id="addArticleForm" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>文章标题：</td>
            <td><input id="articleTitle" name="title"/></td>
        </tr>
        <tr>
            <td>文章封面：</td>
            <td><input id="articleImg" name="file"/></td>
        </tr>
        <tr>
            <td>文章内容：</td>
            <td><textarea style="height: 100;width: 180" id="articleContent" rows="500" cols="500"
                          name="content"></textarea></td>
        </tr>
        <tr>
            <td>上师名称：</td>
            <td><input id="guruCombobox" name="guruId"/></td>
        </tr>
    </table>
</form>

<a id="addArticleSubmitBtn"></a>
<a id="addArticleBackBtn"></a>