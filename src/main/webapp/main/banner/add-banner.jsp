<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">
    $(function () {
        $("#bannerTitleInput").textbox({
            required: true
        });
        $("#bannerDescriptionInput").textbox({
            required: true
        });
        $("#addBannerCancleBtn").linkbutton({
            text: "返回上级",
            plain: true,
            iconCls: "icon-back",
            onClick: function () {
                location.href = "${pageContext.request.contextPath}/main/main.jsp";
            }
        });
        $("#addBannerSubmitBtn").linkbutton({
            text: "提交",
            plain: true,
            iconCls: "icon-ok",
            onClick: function () {
                /*提交表单*/
                $("#addBannerForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/addBanner",
                    onSubmit: function () {
                        return $("#addBannerForm").form("validate");
                    },
                    success: function () {
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功!",
                            showType: "show",
                            width: 300,
                            height: 200
                        });
                        $("#showBannerTable").datagrid("reload");
                        $("#addBannerDialog").dialog("close");
                    }
                });
            }
        });
    });
</script>


<form id="addBannerForm" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>标题</td>
            <td><input id="bannerTitleInput" name="title"/></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><input id="bannerDescriptionInput" name="description"/></td>
        </tr>
        <tr>
            <td>图片</td>
            <td><input type="file" name="file"/></td>
        </tr>
    </table>
</form>
<a id="addBannerSubmitBtn"></a>
<a id="addBannerCancleBtn"></a>