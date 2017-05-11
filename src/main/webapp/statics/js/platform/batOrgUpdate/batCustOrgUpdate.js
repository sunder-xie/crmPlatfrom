$(document).ready(function() {
	$('#hideFrameCust').bind('load', promgtMsg);
	$('#cust_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/batOrgAction!searchCustListJson.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : false,
						nowrap : true,
						height : height,
						striped : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'custNumber',
									title : '客户编号',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'custName',
									title : '客户名称',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'orgId',
									title : '新组织Id',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden:true
								},
								{
									field : 'orgName',
									title : '新组织',
									width : setColumnWidth(0.2),
									align : 'center'
								}] ]
					});
});

// 取消
function cancel() {
	self.location.reload(true);
}

function search() {
	var queryParams = $('#cust_list').datagrid('options').queryParams;
	queryParams.userId=0;
	$("#cust_list").datagrid('load');
}

/**
 * 下载模版
 */
function exportExcel(){
	$.messager
			.confirm(
					'Confirm',
					'是否下载模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/batOrgAction!exportExcel.jspa?type=cust';
							form.target = "hideFrameCust";
							form.submit();

						}
					});
}
/**
 * 查看上传的数据
 */
function importCheck(){
	$.messager
			.confirm(
					'Confirm',
					'是否确认验证查看上传的数据?',
					function(r) {
						if (r) {
								var file = document.getElementById('uploadFile').value;
								if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
									var form = window.document.forms[0];
									form.action = appUrl
											+ '/batOrgAction!checkExcel.jspa?type=cust';
									form.target = "hideFrameCust";
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
function importExcel(){
	$.messager
			.confirm(
					'Confirm',
					'是否确认导入上传的数据?',
					function(r) {
						if (r) {
							var file = document.getElementById('uploadFile').value;
							if (/^.+\.(xls|xlsx|XLS|XLSX)$/.test(file)) {
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/batOrgAction!importExcel.jspa?type=cust';
								form.target = "hideFrameCust";
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

// 返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrameCust");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		search();
		});
		
	}
}


