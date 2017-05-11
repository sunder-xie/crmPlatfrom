$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					// 最大可通行车型
					$('#maximum')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
												+ 'carType'
									});
					// 经销商查询
					$('#kunnr')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/kunnrAction!kunnrSearch.jspa',
										idField : 'kunnr',
										textField : 'name1',
										columns : [ [ {
											field : 'kunnr',
											title : '经销商编号',
											width : 120
										}, {
											field : 'name1',
											title : '名称',
											width : 250
										} ] ],
										toolbar : '#toolbarKonzs',
										onSelect : function(index, record) {
											$
													.ajax({
														type : "post",
														url : appUrl
																+ "/kunnrAddressAction!getMaxKunnrSd.jspa",
														data : {
															kunnrId : record.kunnr
														},
														success : function(obj) {
															$("#kunnrSd").val(
																	obj);
														}
													});
										}
									});
				});

// 经销商查询框
function searcherKonzs(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnr').combogrid("grid").datagrid('reload');
}
/*
 * 选择地址
 */
function selectAddress() {
	initMaintWindow('选择地址', '/kunnrAction!searchAddress.jspa', 600,
				500);
}
/*
 * 地址选择返回值zwl01t省，zwl02t市，zwl03t区zwl04t镇
 */

function returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t) {
		document.getElementById('sdProvince').value=zwl01;
		document.getElementById('sdCity').value=zwl02;
		document.getElementById('sdArea').value=zwl03;
		document.getElementById('sdTown').value=zwl04;
		document.getElementById('street').value = zwl01t + zwl02t
				+ zwl03t + zwl04t;
}
/**
 * 选择完之后关闭对话框
 */
function closeOrgTree() {
	$("#maintWindow").window('close');
}
// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
						
					});

	$win.window('open');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('提示', successResult, 'info', function() {
			close();
			window.parent.search();
		});
	}
}

function close() {
	window.parent.closeMaintKunnrAddress();
}

function submit() {

	var isValid = $('#form1').form('validate');
	if (isValid) {
		$.messager.confirm('Confirm', '请核对信息,确认提交?', function(r) {
			if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl
						+ "/kunnrAddressAction!createKunnrAddress.jspa";
				form.target = "hideFrame";
				form.submit();
			}
		});
	} else {
		$.messager.alert('Tips', '请按提示正确填写表单信息!', 'warning');
		return;
	}
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};