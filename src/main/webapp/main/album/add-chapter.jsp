<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#addChapterTitle").textbox({
            required: true
        });
        $("#addChapterUrl").filebox({
            required: true,
            buttonText: "选择文件"
        });
        $("#addChapterCancleBtn").linkbutton({
            text: "返回上级",
            iconCls: "icon-back",
            plain: true,
            onClick: function () {
                $("#addChapterDialog").dialog("close");
            }
        });
        $("#addChapterSubmitBtn").linkbutton({
            text: "提交",
            iconCls: "icon-save",
            plain: true,
            onClick: function () {
                $("#addChapterForm").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/addChapter?albumId=" + albumId,
                    onSubmit: function () {
                        return $("#addChapterForm").form("validate");
                    },
                    success: function () {
                        $("#addChapterDialog").dialog("close");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功!",
                            showType: "show",
                            width: 300,
                            height: 200
                        });
                        $("#showAlbumTable").treegrid("reload");
                    }
                });
            }
        });
    });
</script>

<form id="addChapterForm" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>章节名称：</td>
            <td><input id="addChapterTitle" name="title"/></td>
        </tr>
        <tr>
            <td>章节名称：</td>
            <td><input id="addChapterUrl" name="file"/></td>
        </tr>
    </table>
</form>

<a id="addChapterSubmitBtn"></a>
<a id="addChapterCancleBtn"></a>