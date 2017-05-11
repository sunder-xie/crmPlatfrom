$(document).ready(function() {
	loadGrid();
	loadGrid1();
	$('#hideFrame').bind('load', promgtMsg);
});
/**
 * 主数据
 */
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/qualityChecking/qualityCheckingAction!qualityCheckingList.jspa',
						queryParams : {
						// 这里可以设置查看的角色
						},
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'qualityCheckingId',
									title : '质检报告编号',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'dateOfManufacture',
									title : '生产日期',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeStart',
									title : '时间从',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeEnd',
									title : '时间至',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielId',
									title : '物料',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielName',
									title : '物料描述',
									width : setColumnWidth(0.20),
									align : 'center'
								},
								{
									field : 'qualityChecking',
									title : '质检报告',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										var qualityChecking = row.qualityChecking;
										var url = "/upload_file/crm/qualityChecking/"
										if (qualityChecking != null) {
											var linkA = '<a href="'+url+qualityChecking+'" target="view_window" class="easyui-linkbutton" data-options="plain:true" title="在线预览")">'
													+ '在线预览'
													+ '</a>'
													+ '&nbsp&nbsp&nbsp'
													+'<a href="#" class="easyui-linkbutton" data-options="plain:true" title="下载" onclick="toDownload('
													+ id
													+ ')">'
													+ '下载附件'
													+ '</a>';
											return linkA;
										} 
									}
								},
								{
									field : 'qualityCheckingUpload',
									title : '质检报告上载',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										var qualityChecking = row.qualityChecking;
										if (qualityChecking == null) {
											var linkB = '<a href="#" class="easyui-linkbutton" data-options="plain:true" title="上传附件" onclick="toUpload('
												+ id + ')">' + '上传附件' + '</a>';
											return linkB;
										} else {
											return "附件已上传";
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增质检报告',
							iconCls : 'icon-add',
							handler : function() {
								addNewQualityChecking();
							}
						}, "-", {
							text : '质检报告导入模板下载',
							iconCls : 'icon-excel',
							handler : function() {
								exportTemplate();
							}
						}, "-", {
							text : '导出数据',
							iconCls : 'icon-excel',
							handler : function() {
								exportData();
							}
						} ]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
/**
 * 在线预览
 * @param id
 */
function toView(id){
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/qualityChecking/qualityCheckingAction!viewQualityCheckingFile.jspa?id='+id;
	form.target = "hideFrame";
	form.submit();
}
/**
 * 下载附件
 * @param id
 */
function toDownload(id){
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/qualityChecking/qualityCheckingAction!dowmloadQualityCheckingFile.jspa?id='+id;
	form.target = "hideFrame";
	form.submit();
}

/**
 * 上传附件
 * @param id
 */
function toUpload(id) {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('上传附件', html, id);
}
/**
 * 打开导入项目Excel对话框
 */
function openImportDialog(t, html,id) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '上传附件',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
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
									text : '确定',
									handler : function() {
										var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(jpg|jpeg|gif|bmp|png|JPG|JPEG|GIF|BMP|PNG|)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/qualityChecking/qualityCheckingAction!qualityCheckingUploadtFile.jspa?id="+id;
											var form = document
													.getElementById('fileForm');

											form.action = action;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为");
										}

									}
								},
								{
									text : '取消',
									handler : function() {
										window.location.href = window.location.href;
										$('#excelDialog').dialog('close');

									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}
/**
 * 物料描述
 */
function loadGrid1() {
	$('#brand').combobox(
			{
				onChange : function(newValue, oldValue) {
					var queryParams = $('#wid').combogrid("grid").datagrid(
							'options').queryParams;
					queryParams.brand = newValue;
					$('#wid').combogrid("grid").datagrid('reload');
				}
			});
	$('#wid')
			.combogrid(
					{
						panelWidth : 400,
						panelHight : 600,
						idField : 'matnr',
						textField : 'maktx',
						pagination : true,// 是否分页
						collapsible : false,// 是否可折叠的
						method : 'post',
						url : appUrl
								+ '/qualityChecking/qualityCheckingAction!getMaterielList.jspa',
						columns : [ [ {
							field : 'ck',
							checkbox : true,
							hidden : true
						}, {
							field : 'matnr',
							title : '物料编号',
							align : 'center',
							width : 120
						}, {
							field : 'maktx',
							title : '物料描述',
							align : 'center',
							width : 250
						} ] ],
						toolbar : '#toolbar1'
					});
}
/**
 * 新增质检报告
 */
function addNewQualityChecking() {
	initMaintWindow(
			'协议目标量批量调整',
			appUrl
					+ '/qualityChecking/qualityCheckingAction!toAddQualityCheking.jspa',
			'maintWindow', '1000', '400', true);
}
/**
 * 质检报告导入模板下载
 */
function exportTemplate() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/qualityChecking/qualityCheckingAction!exportTemplate.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 * 导出数据
 */
function exportData() {

}

function closeDtPlan() {
	$("#MaintGoal").window('close');
}

/**
 * 初始化新的窗口
 * 
 * @param title
 *            主题
 * @param url
 *            链接
 * @param id
 *            id
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param flag
 *            操作
 */
function initMaintWindow(title, url, id, width, height, flag) {
	var WWidth = width;
	var WHeight = height;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						fit : flag,
						draggable : true
					});

	$win.window('open');
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
/**
 * 查询物料
 * 
 * @param val
 */
function searcher1(val) {
	var queryParams = $('#wid').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#wid').combogrid("grid").datagrid('reload');
}
/**
 * 查询数据
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.qualityCheckingId = encodeURIComponent($("#qualityCheckingId")
			.val());
	queryParams.dateOfManufactureStart = encodeURIComponent($(
			"#dateOfManufactureStart").val());
	queryParams.dateOfManufactureEnd = encodeURIComponent($(
			"#dateOfManufactureEnd").val());
	queryParams.materielId = encodeURIComponent($("#wid").combobox("getValue"));
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
