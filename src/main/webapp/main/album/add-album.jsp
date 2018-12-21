<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#addAlbumTitle").textbox({
            required: true
        });
        $("#addAlbumAuthor").textbox({
            required: true
        });
        $("#addAlbumBroadcast").textbox({
            required: true
        });
        $("#addAlbumBrief").textbox({
            required: true
        });
        $("#addAlbumCoverImg").filebox({
            required: true,
            buttonText: "选择文件"
        });
        $("#addAlbumCancleBtn").linkbutton({
            text: "返回上级",
            plain: true,
            iconCls: "icon-back",
            onClick: function () {
                $("#addAlbumDialog").dialog("close");
            }
        });
        $("#addAlbumSubmitBtn").linkbutton({
            text: "提交",
            plain: true,
            iconCls: "icon-save",
            onClick: function () {
                $("#addAlbumForm").form("submit", {
                    url: "${pageContext.request.contextPath}/album/addAlbum",
                    onSubmit: function () {
                        return $("#addAlbumForm").form("validate");
                    },
                    success: function () {
                        $("#addAlbumDialog").dialog("close");
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


<form id="addAlbumForm" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>专辑标题：</td>
            <td><input id="addAlbumTitle" name="title"/>
            <td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input id="addAlbumAuthor" name="author"/>
            <td>
        </tr>
        <tr>
            <td>播音员：</td>
            <td><input id="addAlbumBroadcast" name="broadcast"/>
            <td>
        </tr>
        <tr>
            <td>简介：</td>
            <td><input id="addAlbumBrief" name="brief"/>
            <td>
        </tr>
        <tr>
            <td>封面：</td>
            <td><input id="addAlbumCoverImg" name="file"/>
            <td>
        </tr>
    </table>
</form>

<a id="addAlbumSubmitBtn"></a>
<a id="addAlbumCancleBtn"></a>