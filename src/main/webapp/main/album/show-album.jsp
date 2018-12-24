<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    var album;
    var albumId;
    var toolbar = [{
        text: "专辑详情",
        iconCls: 'icon-search',
        handler: function () {
            var row = $("#showAlbumTable").treegrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请选择专辑!"
                );
            } else if (!isNaN(row.id)) {
                $("#lookAlbumDetail").dialog("open");
                album = row;
            } else {
                $.messager.alert(
                    "提示",
                    "请选择专辑!"
                );
            }
        }
    }, '-', {
        text: "添加专辑",
        iconCls: 'icon-add',
        handler: function () {
            $("#addAlbumDialog").dialog("open");
        }
    }, '-', {
        text: "添加章节",
        iconCls: 'icon-add',
        handler: function () {
            var row = $("#showAlbumTable").treegrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请选择专辑!"
                );
            } else if (!isNaN(row.id)) {
                $("#addChapterDialog").dialog("open");
                albumId = row.id;
            } else {
                $.messager.alert(
                    "提示",
                    "请选择专辑!"
                );
            }
        }
    }, '-', {
        text: "下载音频",
        iconCls: 'icon-save',
        handler: function () {
            var row = $("#showAlbumTable").treegrid("getSelected");
            if (row == null) {
                $.messager.alert(
                    "提示",
                    "请选择章节!"
                );
            } else if (isNaN(row.id)) {
                location.href = "${pageContext.request.contextPath}/chapter/download?name=" + row.url + "&title=" + row.title;
            } else {
                $.messager.alert(
                    "提示",
                    "请选择章节!"
                );
            }
        }
    }, '-', {
        text: "导出数据",
        iconCls: 'icon-back',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/album/exportAlbum";
        }
    }];


    $(function () {
        $("#showAlbumTable").treegrid({
            url: "${pageContext.request.contextPath}/album/queryAllAlbum",
            idField: "id",
            treeField: "title",
            fit: true,
            fitColumns: true,
            singleSelect: true,
            columns: [[
                {field: 'title', title: '标题', width: 100},
                {field: 'size', title: '大小'},
                {field: 'duration', title: '时长', width: 100},
                {field: 'uploadDate', title: '发布日期', width: 100},
                {field: 'url', title: '播放', formatter: run, width: 100}
            ]],
            pagination: true,
            pageSize: 3,
            pageList: [3, 5, 7, 9],
            toolbar: toolbar,
            onDblClickRow: function (row) {
                if (row.children == null) {
                    var path = "${pageContext.request.contextPath}/audio/" + row.url;
                    $("#" + row.id).prop("autoplay", "autoplay");
                    $("#" + row.id).prop("src", path);
                }
            }
        });

        /*初始化专辑详情对话框*/
        $("#lookAlbumDetail").dialog({
            title: "专辑详情",
            iconCls: "icon-search",
            closed: true,
            modal: true,
            cache: false,
            width: 400,
            height: 500,
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            /*加载显示页面*/
            href: "${pageContext.request.contextPath}/main/album/album-detail.jsp"
        });

        /*添加专辑对话框*/
        $("#addAlbumDialog").dialog({
            title: "添加专辑",
            iconCls: "icon-add",
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
            href: "${pageContext.request.contextPath}/main/album/add-album.jsp"
        });

        /*添加章节对话框*/
        $("#addChapterDialog").dialog({
            title: "添加章节",
            iconCls: "icon-add",
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
            href: "${pageContext.request.contextPath}/main/album/add-chapter.jsp"
        });
    });

    function run(value, row, index) {
        if (row.children == null) {
            var path = "${pageContext.request.contextPath}/audio/" + value;
            return "<audio id='" + row.id + "' style='height:30px;width:220px' controls='controls' src='" + path + "'/>";
        }
    }
</script>


<table id="showAlbumTable"></table>
<div id="lookAlbumDetail"></div>
<div id="addAlbumDialog"></div>
<div id="addChapterDialog"></div>