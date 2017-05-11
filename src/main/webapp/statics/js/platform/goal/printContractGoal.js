$(document).ready(function() {
	loadGrid();
	
});

function selectOrgTree() {
	initMaintGoal('选择组织', '/goalAction!orgTreePage.jspa', 400, 460);
}
function closeDtPlan() {
	$("#MaintGoal").window('close');
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}

function initMaintGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintGoal")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}
function searcher(val) {
	val = encodeURIComponent(val);
	$('#custId').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#custId').combogrid("grid").datagrid('reload');

}
function loadGrid() {
	$('#hideFrame').bind('load', promgtMsg);
	$('#custId').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'id',
			title : '客户ID',
			hidden : true,
			width : 60
		}, {
			field : 'kunnr',
			title : '客户编号',
			align : 'center',
			width : 120
		}, {
			field : 'status',
			title : '客户状态',
			align : 'center',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return '正常客戶';
				} else if (value == 2) {
					return '<font color="red">已关客戶</font>';
				}
			}
		}, {
			field : 'name1',
			title : '客户名称',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '手机',
			align : 'center',
			width : 150
		} ] ],
		toolbar : '#toolbar2'
	});
	$('#yearFlag').combobox({
		valueField : 'value',
		textField : 'text',
		multiple : false,
		editable : false,
		data : [ {
			value : '2012',
			text : '2012年'
		}, {
			value : '2013',
			text : '2013年'
		}, {
			value : '2014',
			text : '2014年'
		}, {
			value : '2015',
			text : '2015年'
		}, {
			value : '2016',
			text : '2016年'
		}, {
			value : '2017',
			text : '2017年'
		}, {
			value : '2018年',
			text : '2018年'
		} ],
		onLoadSuccess : function() {
			$('#yearFlag').combobox('setValue', '');
		}
	});
	$('#brand').combobox({
		valueField : 'value',
		textField : 'text',
		multiple : false,
		editable : false,
		data : [ {
			value : 'XPP',
			text : '香飘飘'
		}, {
			value : 'LFYMECO',
			text : '兰芳园&MECO'
		}],
		onLoadSuccess : function() {
			$('#yearFlag').combobox('setValue', '');
		}
	});
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		$.messager.progress('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$.messager.progress('close');
		window.parent.reloadDatagrid();
	}
}



function submit() {
	var custId = $('#custId').combobox('getValue');
	if (custId == "") {
		$.messager.alert('Tips', '经销商不能为空!', 'warning');
		return;
	}
	var yearFlag = $('#yearFlag').combobox('getValue');
	if (yearFlag == "") {
		$.messager.alert('Tips', '财年不能为空!', 'warning');
		return;
	}
	var brand = $('#brand').combobox('getValue');
	if(brand=="XPP"){
		initMaintWindowForPostion('打印功能', '/goalAction!printContractGoal.jspa?custId='+custId+'&yearFlag='+yearFlag+'&orgId='+$('#orgId').val(),800,380,true);
	}else if(brand == "LFYMECO"){
		initMaintWindowForPostion('打印功能', '/goalAction!printContractGoal2.jspa?custId='+custId+'&yearFlag='+yearFlag+'&orgId='+$('#orgId').val(),800,380,true);
	}else{
		$.messager.alert('Tips', '品牌选择出错!', 'warning');
		return;
	}
	
}
function initMaintWindowForPostion(title, url,WWidth,WHeight,maximized) {
	var url = appUrl + url;
	var $win = $("#MaintGoal")
			.window(
					{   maximized:maximized,
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function close() {
	$('#brand').combobox('setValue','');
	$('#yearFlag').combobox('setValue','');
	$('#custId').combobox('setValue','');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};
