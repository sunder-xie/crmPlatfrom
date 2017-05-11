$(document).ready(function() {
	$('#matter').combogrid({
		panelWidth : 350,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// �Ƿ��ҳ
		collapsible : false,// �Ƿ���۵���
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'mvgr1',
			title : 'Ʒ��or�ļ���Ŀ��SKU��ID',
			align : 'center',
			width : 80
		}, {
			field : 'bezei',
			title : 'Ʒ��or�ļ���Ŀ��SKU��',
			align : 'center',
			width : 200
		} ] ],
		toolbar : '#toolbar1'
	});
	if ($('#type').val() == 'update') {
		$('#matter').combogrid('setValue', $('#matterId').val());
		$('#matter').combogrid('setText', $('#bezei').val());
	}
	$('#hideFrame').bind('load', promgtMsg);
});


function close() {
	window.parent.closeMain();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			close();
			window.parent.search();
		});
	}
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#matter').combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + val
	});
	$('#matter').combogrid("grid").datagrid('reload');

}
function submit() {
	var isVail = $('#ff').form('validate');
	if (isVail) {
		$.messager.confirm('Confirm', '�Ƿ�ȷ���ύ?', function(r) {
			if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl
						+ "/goal/goalAction!createOrUpdatePrice.jspa";
				form.target = "hideFrame";
				form.submit();
			}

		});
	} else {
		$.messager.alert('Tips', '����ҳ���������Ƿ�δ����߸�ʽ�Ƿ���ȷ!', 'warning');
		return;
	}
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