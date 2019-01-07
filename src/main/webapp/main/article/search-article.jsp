<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<style id="style_super_inline">
    html {
        color: #000;
        overflow-y: scroll;
        overflow: -moz-scrollbars
    }

    body, button, input, select, textarea {
        font: 12px arial
    }

    #head_wrapper .s-p-top {
        height: 61.8%;
        min-height: 181px;
        position: relative;
        z-index: 0;
        text-align: center
    }

    #s_lg_img {
        position: absolute;
        bottom: 10px;
        left: 50%;
        margin-left: -135px
    }

    #form {
        z-index: 1
    }

    #lm a {
        text-decoration: underline;
        color: #666
    }

    #nv a, #nv b {
        margin-left: 19px
    }

    .s_form {
        width: 641px;
        height: 50px;
        min-height: 293px;
        margin: 0 auto 0 auto;
        text-align: left;
        z-index: 100
    }

    .s-down .s_form {
        padding-left: 0;
        margin-top: 0;
        min-height: 0
    }

    .s_form_wrapper {
        height: 100%
    }

    #head_wrapper.s-down #kw, #kw {
        width: 500px;
        height: 20px;
        padding: 9px 7px;
        font: 16px arial;
        border: 1px solid #b8b8b8;
        border-bottom: 1px solid #ccc;
        border-right: 0;
        vertical-align: top;
        outline: none;
        box-shadow: none
    }

    .btn {
        color: white;
        background-color: #38f;
        width: 102px;
        height: 38px;
        font-size: 16px;
        border: 0
    }

</style>
<script type="text/javascript">
    $(function () {
        $("#su").click(function () {
            var value = $("#kw").val();
            $.post("${pageContext.request.contextPath}/article/queryArticleIndex", "params=" + value, function (s) {
                $("#tbody_index").empty();
                for (var i = 0; i < s.length; i++) {
                    var id = $("<tr><td>文章编号:</td><td>" + s[i].id + "</td></tr>");
                    var title = $("<tr><td>文章标题:</td><td>" + s[i].title + "</td></tr>");
                    var dharma = $("<tr><td>上师法号:</td><td>" + s[i].guru.dharma + "</td></tr>");
                    var content = $("<tr><td>文章内容:</td><td>" + s[i].content + "</td></tr>");
                    var pubDate = $("<tr><td>发布日期:</td><td>" + s[i].pubDate + "</td></tr>");
                    var path = "http://192.168.169.135/" + s[i].insertImg;
                    var insertImg = $("<tr><td>文章图片:</td><td><img style='height:70;width:60' src='" + path + "'></td></tr>");
                    var br = $("<tr><td colspan='2'><hr style='width:100%'/></td></tr>");
                    $("#tbody_index").append(insertImg);
                    $("#tbody_index").append(id);
                    $("#tbody_index").append(title);
                    $("#tbody_index").append(dharma);
                    $("#tbody_index").append(content);
                    $("#tbody_index").append(pubDate);
                    $("#tbody_index").append(br);
                }
            }, "JSON");
        });

    })

</script>

<div id="head_wrapper" class="s-isindex-wrap head_wrapper s-title-img ">
    <div id="s_fm" class="s_form">
        <div class="s_form_wrapper" id="s_form_wrapper">
            <div id="lg" class="s-p-top">
                <img id="s_lg_img" src="${pageContext.request.contextPath}/img/logo.png">
            </div>
            <form id="form" method="post" class="fm">
                <input type="text" class="s_ipt" name="params" id="kw" style="height: 38" maxlength="100"
                       autocomplete="off">
                <input type="button" value="搜索一下" id="su" class="btn self-btn bg s_btn">
            </form>
        </div>
    </div>
</div>


<hr/>
<div id="ff" style="width: 100%">
    <table id="table_index">
        <tbody id="tbody_index">
        </tbody>
    </table>
</div>

