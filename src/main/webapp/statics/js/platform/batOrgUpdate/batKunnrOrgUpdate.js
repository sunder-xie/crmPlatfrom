$(document)
		.ready(
				function() {
					$('#hideFrameKunnr').bind('load', promgtMsg);

					$('#kunnr_list')
							.datagrid(
									{
										iconCls : 'icon-list',
										title : '��ѯ���',
										url : appUrl
												+ '/batOrgAction!searchKunnrListJson.jspa',
										loadMsg : '����Զ��������,��ȴ�...',
										singleSelect : false,
										pagination : false,
										nowrap : true,
										height : height,
										striped : true,
										rownumbers : true,
										columns : [ [ {
											field : 'kunnr',
											title : '�����̱��',
											width : setColumnWidth(0.1),
											align : 'center'
										}, {
											field : 'name1',
											title : '����������',
											width : setColumnWidth(0.2),
											align : 'center'
										}, {
											field : 'orgId',
											title : '����֯Id',
											width : setColumnWidth(0.1),
											align : 'center',
											hidden : true
										}, {
											field : 'orgName',
											title : '����֯',
											width : setColumnWidth(0.2),
											align : 'center'
										} ] ]
									});
					var urls = '/batOrgAction!toBatCustOrgUpdatePre.jspa';
					var Title = "���������ն˿ͻ���֯";
					addTab(Title, urls);
					//$("#tt").tabs("select", 0);
					
				});

// ȡ��
function cancel() {
	self.location.reload(true);
}

function search() {
	var queryParams = $('#kunnr_list').datagrid('options').queryParams;
	queryParams.userId = 0;
	$("#kunnr_list").datagrid('load');
}

/**
 * ����ģ��
 */
function exportExcel() {
	$.messager.confirm('Confirm', '�Ƿ�����ģ����Ϣ?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/batOrgAction!exportExcel.jspa?type=kunnr';
			form.target = "hideFrameKunnr";
			form.submit();

		}
	});
}
/**
 * �鿴�ϴ�������
 */
function importCheck() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ����֤�鿴�ϴ�������?', function(r) {
		if (r) {
			var file = document.getElementById('uploadFile').value;
			if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/batOrgAction!checkExcel.jspa?type=kunnr';
				form.target = "hideFrameKunnr";
				form.submit();
			} else {
				$.messager.alert("��ʾ", "�뵼��.xls����.xlsx��ʽ���ļ�");
			}
		}
	});
}
/*
 * ��������
 */
function importExcel() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ�ϵ����ϴ�������?', function(r) {
		if (r) {
			var file = document.getElementById('uploadFile').value;
			if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/batOrgAction!importExcel.jspa?type=kunnr';
				form.target = "hideFrameKunnr";
				form.submit();
			} else {
				$.messager.alert("��ʾ", "�뵼��.xls����.xlsx��ʽ���ļ�");
			}
		}
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function addTab(Title, urls) {
	if ($('#tt').tabs('exists', Title)) {
		return;
	}
	var url = appUrl + urls;
	$('#tt')
			.tabs(
					'add',
					{
						title : Title,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>'
					});
}
// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrameKunnr");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});

	}
}
