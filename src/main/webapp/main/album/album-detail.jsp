<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#albumDetailForm").form("load", album);

        var path = "${pageContext.request.contextPath}/album-cover/" + album.coverImg;
        $("#detailAlbumCoverImg").prop("src", path);

        $("#back").linkbutton({
            text: "返回上级",
            iconCls: "icon-back",
            plain: true,
            onClick: function () {
                $("#lookAlbumDetail").dialog("close");
            }
        });
    });
</script>

<form id="albumDetailForm">
    <table>
        <tr>
            <td>封面：</td>
            <td><img id="detailAlbumCoverImg" src="" name="coverImg"/></td>
        </tr>
        <tr>
            <td>专辑标题：</td>
            <td><input id="detailAlbumTitle" name="title"/></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input id="detailAlbumAuthor" name="author"/></td>
        </tr>
        <tr>
            <td>播音员：</td>
            <td><input id="detailAlbumBroadcast" name="broadcast"/></td>
        </tr>
        <tr>
            <td>评价：</td>
            <td><input id="detailAlbumScore" name="score"/></td>
        </tr>
        <tr>
            <td>集数：</td>
            <td><input id="detailAlbumCount" name="count"/></td>
        </tr>
        <tr>
            <td>发布日期：</td>
            <td><input id="detailAlbumPubDate" name="pubDate"/></td>
        </tr>
        <tr>
            <td>简介：</td>
            <td><textarea id="detailAlbumBrief" name="brief"/></td>
        </tr>
    </table>
</form>

<a id="back"></a>