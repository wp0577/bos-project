<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
	<%--import all the --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<%--导入ztree必用包--%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>

	<script type="text/javascript">
        function a() {
            //通过title去判断是否存在该tab
            var e = $('#tt').tabs('exists','系统管理');
            //如果不存在则创建，存在则跳到选择的页面
			if(e) {
			    $('#tt').tabs('select','系统管理');
            }
			else {
				$('#tt').tabs('add', {
                    title: '系统管理',
                    iconCls: 'icon-edit',
                    closable: true,
                    content: '<iframe frameborder="0" height="100%" width="100%" src="https://www.github.com"></iframe>'
                });
            }
        }
	</script>

</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">西部区域
		<div class="easyui-accordion">
			<div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
				<h3 style="color:#0099FF;">Accordion for jQuery</h3>
				<input type="button" value="add a new tab" onclick="a()"/>
			</div>
			<div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;">
				<ul id="ztree2" class="ztree"></ul>
				<%--<script type="text/javascript">
                    $(function(){
                        //页面加载完成后，执行这段代码----动态创建ztree
                        var setting2 = {
                            data: {
                                simpleData: {
                                    enable: true//使用简单json数据构造ztree节点
                                }
                            }
                        };
                        //构造节点数据
                        var zNodes2 = [
                            {"id":"1","pId":"0","name":"节点一"},//每个json对象表示一个节点数据
                            {"id":"2","pId":"1","name":"节点二"},
                            {"id":"3","pId":"2","name":"节点三"},
							{"id":"4","pId":"0","name":"tree4"},
							{"id":"5","pId":"4","name":"tree5"},
							{"id":"6","pId":"0","name":"tree6"},
							{"id":"7","pId":"6","name":"tree7"}
                        ];
                        //调用API初始化ztree
                        $.fn.zTree.init($("#ztree2"), setting2, zNodes2);
                    });
				</script>--%>
				<%--使用ajax获得json数据形成菜单--%>
				<script type="text/javascript">
					$(function () {
                        var setting2 = {
                            data: {
                                simpleData: {
                                    enable: true//使用简单json数据构造ztree节点
                                }
                            },
                            callback: {
                                //为ztree节点绑定单击事件
                                onClick: function(event, treeId, treeNode){
                                    if(treeNode.page != undefined){
                                        //判断选项卡是否已经存在
                                        var e = $("#tt").tabs("exists",treeNode.name);
                                        if(e){
                                            //已经存在，选中
                                            $("#tt").tabs("select",treeNode.name);
                                        }else{
                                            //动态添加一个选项卡
                                            $("#tt").tabs("add",{
                                                title:treeNode.name,
                                                closable:true,
                                                content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
                                            });
                                        }
                                    }
                                }
                            }

                        };
                        $.post("${pageContext.request.contextPath}/json/menu.json", {},
                            function(data){
                                $.fn.zTree.init($("#ztree2"), setting2, data);
                            }, "json");
                    })

				</script>

			</div>
			<div title="Title3">
				content3
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" data-options="fit:true" >

			<div title="Tab1" style="padding:20px;display:none;">tab1 </div>

			<div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">tab2 </div>

			<div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="overflow: auto; padding:20px;display:none;">tab3 </div>

		</div>


	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>