$(document).ready(function() {
	//列表
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	$('#datagrid').datagrid({
		singleSelect : false,
		pagination : true,
		pageSize : 10,
		url : appUrl + '/ordersAction!searchReturnLists.jspa',
		columns : [ [{
			field : 'ck',
			checkbox : true
		},{
			field : 'relistId',
			title : '序号',
			width : 60
		},{
			field : 'kunnrNum',
			title : '经销商编码',
			width : 80
		},{
			field : 'kunnrName',
			title : '经销商名称',
			width : 150
		},{
			field : 'materielName',
			title : '物料',
			width : 150
		}, {
			field : 'cataName',
			title : '品项',
			width : 150
		}, {
			field : 'yearPayPrice',
			title : '2015年财年打款',
			width : 100
		}, {
			field : 'totalBouns',
			title : '考核总奖金',
			width : 80
		}, {
			field : 'monthAsses',
			title : '月度考核',
			width : 80
		}, {
			field : 'quarterAsses',
			title : '季度考核',
			width : 80
		}, {
			field : 'yearAsses',
			title : '年度考核',
			width : 80
		}, {
			field : 'yearNakedPrice',
			title : '2015年裸价',
			width : 80
		} ] ],
		toolbar:'#toolbar'
	})
}
function closeDtPlan() {
	$("#maintWindow").window('close');
}

function initmaintWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
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
						closed : true,
						closable : true,
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}

var mask_;
/*导入窗口*/
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '数据导入',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	} else {
		 $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确认导入',
									handler : function() {
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/ordersAction!importExcel.jspa"
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv格式的文件.");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}
/*
 * 打开导入的窗口
 */
function importExcel(){
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
		+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
		+ '<table><tr>'
		+ '<td class="head" noWrap>导入数据</td>'
		+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
		+ '</tb></tr><table></form>';
     openImportDialog('导入数据', html);
}


function closeOrgTree() {
	closeDtPlan();
}
//删除
function delRowsData() {
	$.messager.confirm('Confirm', '是否确定删除?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '未选中行数据!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].relistId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "ordersAction!delReturnList.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

/**
 * 下载模板
 */
function downloadExcel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/ordersAction!downloadExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}
//重置
function clear() {
	$("#kunnr").val('');
	$("#wid").val('');
}
/**
 * 查询
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrName = encodeURIComponent($("#kunnr").val());
	queryParams.materielName = encodeURIComponent($("#wid").val());
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function getWidth(percent) {
	if (jQuery.browser.safari) {
		return document.body.clientWidth * percent - 2;
	} else {
		return document.body.clientWidth * percent;
	}
}
function getHeight(percent) {
	return document.body.clientHeight * percent;
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close')
				search();
			}

		});
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