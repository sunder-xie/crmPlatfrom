$(document).ready(function() {
	loadGrid();
	loadGrid1();
		$('#year').combobox({
		onSelect : function(d) {
				$('#year').val(d.itemName);
		}
	});
	

	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#id').combogrid({
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
		},  {
			field : 'id',
			title : '客户ID',
			hidden : true,
			width : 60
		},
			{
			field : 'kunnr',
			title : '客户编号',
			align : 'center',
			width : 120
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
		}] ],
		toolbar : '#toolbar2'
	});

}
function loadGrid1() {
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'matnr',
			title : '物料号',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '物料组',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '物料组描述',
			align : 'center',
			width : 100
		}] ],
		toolbar : '#toolbar1'
	});
}

function close() {
	window.parent.closeMaintModelAtt();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search_1();
});
	}
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}


function initOpenGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#OpenGoal").window({
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

function selectOrgTree() {
	initOpenGoal('选择组织', '/goalAction!orgTreePage.jspa', 400, 350);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	$("#OpenGoal").window('close');
}

//function closeDtPlan() {
//	$("#OpenGoal").window('close');
//}
function submit() {
	//var pa = $("#instock_total_id").validatebox('isValid');
	var ft = $("#instock_send_place").validatebox('isValid');
	var pq = $("#id").combobox('isValid');
	if (! (ft && pq )) {
		return;
	}$.messager.confirm('Confirm', '是否确认提交?', function(r) {
		if (r) {
		//	$("#matnr").val() 
			document.getElementById("instock_provide_id").value=$("#id").combobox("getValue");
			document.getElementById("instock_provide_name").value=$("#id").combobox("getText");
			var form = window.document.forms[0];
			form.action = appUrl + "/stock/instockAction!updateStock.jspa";
			form.target = "hideFrame";
			form.submit();
		}
		
	});
}
function closeDtPlan() {
	$("#WorkPlan").window('close');
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
}