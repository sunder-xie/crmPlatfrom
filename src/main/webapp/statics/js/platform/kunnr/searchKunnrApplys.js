$(document).ready(function() {
	var state = [ {
		'txt' : '未提交',
		'value' : 'Y'
	}, {
		'txt' : '已删除',
		'value' : 'N'
	}, {
		'txt' : '已提交',
		'value' : 'S'
	} ];
	/*$('#state').combobox({
		data:state,
		editable : false,
		valueField : 'value',
		textField : 'txt',
		onLoadSuccess : function() {
			$('#state').combobox('setValue','Y');	
		}
	});*/
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '经销商开户列表',
		url : appUrl + '/kunnrAction!kunnrApplySaveSearch.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [{
			field : 'ck',
			checkbox : true
		}, {
			field : 'xmlId',
			title : '编号',
			align : 'center',
			width:100
		},
		{
			field : 'xmlName',
			title : '开户申请事务名称',
			align : 'center',
			width:200
		}, {
			field : 'orgName',
			title : '提交人所属组织',
			align : 'center',
			width:100
		}, {
			field : 'orgId',
			title : '组织id',
			align : 'center',
			hidden:true
		}, {
			field : 'empName',
			title : '提交人',
			align : 'center',
			width:100
		}, {
			field : 'empId',
			title : '提交人Id',
			align : 'center',
			hidden:true
		},{
			field : 'state',
			title : '事务状态',
			align : 'center',
			width:80,
			formatter : function(value, row, index) {
				var s=row.state;
				var state='未提交';
				if(s=='N'){
					state='已删除';
				}else if('S'==s){
					state='已提交';
				}
				return state;
			}
		},{
			field : 'createDate',
			title : '创建时间',
			align : 'center',
			width:100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'modifyDate',
			title : '修改时间',
			align : 'center',
			width:100,
			formatter : function(value, row, index) {
				return value;
			}
		}, {
			field : 'eventId',
			title : '开户申请事务编号',
			align : 'center',
			width:120,
			hidden:true
		}, {
			field : 'oper',
			title : '操作',
			width : setColumnWidth(0.12),
			align : 'center',
			formatter : function(value, row, index) {
				var status = row.state;
				var xmlId=row.xmlId;
				var subFolders=row.subfolders;
				if('Y'==status){
					return "<a href='#' onclick=updateKunnrApply('"+xmlId +"','"+subFolders+"')>修改</a> ";
				}else{
					return '';
				}
				
			}
		} ] ]/*,
		toolbar : [ "-", {
			text : '删除',
			iconCls : 'icon-save',
			handler : function() {
				//save();
			}
		}, "-" ]*/
	});
	
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.xmlId = encodeURIComponent($("#xmlId").val());
	queryParams.xmlName = encodeURIComponent($("#xmlName").val());
	//queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.status = encodeURIComponent($("#status").val());
	//$("#state").combobox('getValue')
	$("#datagrid").datagrid('load');
}
function updateKunnrApply(xmlId,subFolders) {
	//initMaintWindow('经销商开户申请', '/kunnrAction!kunnrApplyPre.jspa?xmlId='+xmlId+'&subFolders='+subFolders, 700, 800);
	window.open(appUrl+'/kunnrAction!kunnrApplyPre.jspa?xmlId='+xmlId+'&subFolders='+subFolders, '', 'width=1200,height=700,scrollbars=yes,location=no', '');
}

function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#mainWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}

//返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#mainWindow').dialog('close');
	} else if (successResult == "导入成功!") {
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#mainWindow').dialog('close');
			$('.goalFlag').show();
			$('#importFlag').val('Y');
		});
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}