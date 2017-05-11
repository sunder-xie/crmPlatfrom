$(document)
		.ready(
				function() {
					$('#hideFrameKunnr').bind('load', promgtMsg);

					$('#kunnr_list')
							.datagrid(
									{
										iconCls : 'icon-list',
										title : '查询结果',
										url : appUrl
												+ '/batOrgAction!searchKunnrListJson.jspa',
										loadMsg : '数据远程载入中,请等待...',
										singleSelect : false,
										pagination : false,
										nowrap : true,
										height : height,
										striped : true,
										rownumbers : true,
										columns : [ [ {
											field : 'kunnr',
											title : '经销商编号',
											width : setColumnWidth(0.1),
											align : 'center'
										}, {
											field : 'name1',
											title : '经销商名称',
											width : setColumnWidth(0.2),
											align : 'center'
										}, {
											field : 'orgId',
											title : '新组织Id',
											width : setColumnWidth(0.1),
											align : 'center',
											hidden : true
										}, {
											field : 'orgName',
											title : '新组织',
											width : setColumnWidth(0.2),
											align : 'center'
										} ] ]
									});
					var urls = '/batOrgAction!toBatCustOrgUpdatePre.jspa';
					var Title = "批量调整终端客户组织";
					addTab(Title, urls);
					//$("#tt").tabs("select", 0);
					
				});

// 取消
function cancel() {
	self.location.reload(true);
}

function search() {
	var queryParams = $('#kunnr_list').datagrid('options').queryParams;
	queryParams.userId = 0;
	$("#kunnr_list").datagrid('load');
}

/**
 * 下载模版
 */
function exportExcel() {
	$.messager.confirm('Confirm', '是否下载模版信息?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/batOrgAction!exportExcel.jspa?type=kunnr';
			form.target = "hideFrameKunnr";
			form.submit();

		}
	});
}
/**
 * 查看上传的数据
 */
function importCheck() {
	$.messager.confirm('Confirm', '是否确认验证查看上传的数据?', function(r) {
		if (r) {
			var file = document.getElementById('uploadFile').value;
			if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/batOrgAction!checkExcel.jspa?type=kunnr';
				form.target = "hideFrameKunnr";
				form.submit();
			} else {
				$.messager.alert("提示", "请导入.xls或者.xlsx格式的文件");
			}
		}
	});
}
/*
 * 导入数据
 */
function importExcel() {
	$.messager.confirm('Confirm', '是否确认导入上传的数据?', function(r) {
		if (r) {
			var file = document.getElementById('uploadFile').value;
			if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/batOrgAction!importExcel.jspa?type=kunnr';
				form.target = "hideFrameKunnr";
				form.submit();
			} else {
				$.messager.alert("提示", "请导入.xls或者.xlsx格式的文件");
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
// 返回信息
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
