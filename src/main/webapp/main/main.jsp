﻿<%@ page isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" id="s_superplus_css_lnk" type="text/css"
          href="${pageContext.request.contextPath}/index_files/super_min_0cb4b166.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index_files/card_min_e8bcf60d.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/ubase_83c8f0ba.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/mt_min_d0e7c6d2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/nsguide_a8cbc2e7.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/super_ext_c02dfc40.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#logout").click(function () {
                $.post("${pageContext.request.contextPath}/admin/logout", function () {
                    location.href = "${pageContext.request.contextPath}/login.jsp";
                });
            });
        });
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <shiro:authenticated>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:<shiro:principal></shiro:principal> &nbsp;<a href="#" class="easyui-linkbutton"
                                                         data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
            id="logout" class="easyui-linkbutton" data-options="iconCls:'icon-no'">退出系统</a></div>
</div>
</shiro:authenticated>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true,href:'${pageContext.request.contextPath}/main/left.jsp'"
     style="width:220px;">
</div>

<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-save',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>
