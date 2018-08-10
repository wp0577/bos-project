<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView(){
		alert("查看...");
	}

	function doDelete(){
		var row = $("#grid").datagrid("getSelections");
		if(row.length == 0) {
		    $.messager.alert("warning information", "you should select one row", "warning");
		}
		else {
            $.messager.confirm('Confirm', 'Are you sure to delete selected staff?',
				function(r){if (r){ var array = new Array();
                    for(var i = 0; i < row.length; i++) {
                        var v = row[i];
                        array.push(v.id);
                    }
                    var e = array.join(",");
                    location.href = "${pageContext.request.contextPath}/staffAction_deleteById?ids=" + e; } });

		}
	}

	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : 'name',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : 'telephone',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : 'PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "yes";
			}else{
				return "no";
			}
		}
	}, {
		field : 'deltag',
		title : 'valid',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "valid"
			}else{
				return "invalid";
			}
		}
	}, {
		field : 'standard',
		title : 'standard',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : 'station',
		width : 200,
		align : 'center'
	} ] ];

	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});

		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [5,10,15,20,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_list",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: 'add staff',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });

	});

	function doDblClickRow(rowIndex, rowData){
		alert("双击表格数据...");
	}
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffAddForm" action="staffAction_save" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<%--为手机添加校验规则,必须为10位--%>
						<td>
						<script type="text/javascript">
                            $(function () {
                                $('#save').click(function () {
                                    var e = $('#staffAddForm').form("validate");
                                    if(e){
                                        /*$('#staffAddForm').form("submit");
                                        * 使用上述方法会跳用ajax请求，页面不会刷新*/
                                        $('#staffAddForm').submit();
                                    }
                                });
								/*父类的click方法重写了子类checkbox的的click方法*/
                               /* var check=document.getElementById("haspda");
                                check.onclick=function(){
                                    return true;
                                }*/

                                $.extend($.fn.validatebox.defaults.rules, {
                                    telephone: {
                                        validator: function(value,param){
                                            return value.match(/\d/g).length===10;
                                        },
                                        message: 'password formate is wrong.'
                                    }
                                });
                            })
						</script>
						<input type="text" name="telephone" data-options="validType:'telephone'"
								   class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input  type="checkbox" name="haspda" value="1"  />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>