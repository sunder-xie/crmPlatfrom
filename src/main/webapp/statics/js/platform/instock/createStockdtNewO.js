$(document).ready(function() {
	loadGrid();
	loadGrid1();
		$('#year').combobox({
		onSelect : function(d) {
				$('#year').val(d.itemName);
		}
	});
	
	$('#month').combobox({
		onSelect : function(d) {
				$('#month').val(d.value);
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
		pagination : true,// �Ƿ��ҳ
		// rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		// fit : true,// �Զ���С
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'id',
			title : '�ͻ�ID',
			hidden : true,
			width : 60
		},
			{
			field : 'kunnr',
			title : '�ͻ����',
			align : 'center',
			width : 120
		}, {
			field : 'name1',
			title : '�ͻ�����',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '�ֻ�',
			align : 'center',
			width : 150
		}] ],
		toolbar : '#toolbar'
	});

}
function loadGrid1() {
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// �Ƿ��ҳ
		// rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		// fit : true,// �Զ���С
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'matnr',
			title : '���Ϻ�',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '������',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '����������',
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
		window.parent.search_2();
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
	initOpenGoal('ѡ����֯', '/goalAction!orgTreePage.jspa', 400, 350);
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
	var pa = $("#instockdet_number").numberbox('isValid');
	var ft = $("#instockdet_unumber").numberbox('isValid');
	var aw=  $("#instockdetBatch").numberbox('isValid');
	var bt = $("#instockdet_anumber").numberbox('isValid');
	var pq = $("#wid").combobox('isValid');
	if (! (pa && bt && ft && pq && aw)) {
		return;
	}else if(($("#instockdet_unumber").val()*1+$("#instockdet_anumber").val()*1)!=$("#instockdet_number").val()){
		$.messager.alert('Tips', '���պ��������������������!');
	     return;
	}$.messager.confirm('Confirm', '�Ƿ�ȷ���ύ?', function(r) {
		if (r) {
			document.getElementById("matnr").value=$("#wid").combobox("getValue");
			document.getElementById("bezei").value=$("#wid").combobox("getText");
			var form = window.document.forms[0];
			var type = 'O';
			form.action = appUrl + "/stock/instockAction!addStockdt.jspa?type="+type;
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