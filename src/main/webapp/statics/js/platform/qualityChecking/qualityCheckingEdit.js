$(document).ready(function() {
	loadGrid();
	loadGrid1();
	$('#hideFrame').bind('load', promgtMsg);
});
/**
 * ������
 */
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/qualityChecking/qualityCheckingAction!qualityCheckingList.jspa',
						queryParams : {
						// ����������ò鿴�Ľ�ɫ
						},
						loadMsg : '����Զ��������,��ȴ�...',
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
									title : '�ʼ챨����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'dateOfManufacture',
									title : '��������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeStart',
									title : 'ʱ���',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeEnd',
									title : 'ʱ����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielId',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielName',
									title : '��������',
									width : setColumnWidth(0.20),
									align : 'center'
								},
								{
									field : 'qualityChecking',
									title : '�ʼ챨��',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										var qualityChecking = row.qualityChecking;
										var url = "/upload_file/crm/qualityChecking/"
										if (qualityChecking != null) {
											var linkA = '<a href="'+url+qualityChecking+'" target="view_window" class="easyui-linkbutton" data-options="plain:true" title="����Ԥ��")">'
													+ '����Ԥ��'
													+ '</a>'
													+ '&nbsp&nbsp&nbsp'
													+'<a href="#" class="easyui-linkbutton" data-options="plain:true" title="����" onclick="toDownload('
													+ id
													+ ')">'
													+ '���ظ���'
													+ '</a>';
											return linkA;
										} 
									}
								},
								{
									field : 'qualityCheckingUpload',
									title : '�ʼ챨������',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										var qualityChecking = row.qualityChecking;
										if (qualityChecking == null) {
											var linkB = '<a href="#" class="easyui-linkbutton" data-options="plain:true" title="�ϴ�����" onclick="toUpload('
												+ id + ')">' + '�ϴ�����' + '</a>';
											return linkB;
										} else {
											return "�������ϴ�";
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '�����ʼ챨��',
							iconCls : 'icon-add',
							handler : function() {
								addNewQualityChecking();
							}
						}, "-", {
							text : '�ʼ챨�浼��ģ������',
							iconCls : 'icon-excel',
							handler : function() {
								exportTemplate();
							}
						}, "-", {
							text : '��������',
							iconCls : 'icon-excel',
							handler : function() {
								exportData();
							}
						} ]
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 50, 100 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}
/**
 * ����Ԥ��
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
 * ���ظ���
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
 * �ϴ�����
 * @param id
 */
function toUpload(id) {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>ѡ���ļ��ϴ���</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('�ϴ�����', html, id);
}
/**
 * �򿪵�����ĿExcel�Ի���
 */
function openImportDialog(t, html,id) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '�ϴ�����',
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
									text : 'ȷ��',
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
											$.messager.alert("��ʾ", "���ļ����Ͳ������ϴ������ϴ� " + allImgExt + " ���͵��ļ�����ǰ�ļ�����Ϊ");
										}

									}
								},
								{
									text : 'ȡ��',
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
 * ��������
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
						pagination : true,// �Ƿ��ҳ
						collapsible : false,// �Ƿ���۵���
						method : 'post',
						url : appUrl
								+ '/qualityChecking/qualityCheckingAction!getMaterielList.jspa',
						columns : [ [ {
							field : 'ck',
							checkbox : true,
							hidden : true
						}, {
							field : 'matnr',
							title : '���ϱ��',
							align : 'center',
							width : 120
						}, {
							field : 'maktx',
							title : '��������',
							align : 'center',
							width : 250
						} ] ],
						toolbar : '#toolbar1'
					});
}
/**
 * �����ʼ챨��
 */
function addNewQualityChecking() {
	initMaintWindow(
			'Э��Ŀ������������',
			appUrl
					+ '/qualityChecking/qualityCheckingAction!toAddQualityCheking.jspa',
			'maintWindow', '1000', '400', true);
}
/**
 * �ʼ챨�浼��ģ������
 */
function exportTemplate() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/qualityChecking/qualityCheckingAction!exportTemplate.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 * ��������
 */
function exportData() {

}

function closeDtPlan() {
	$("#MaintGoal").window('close');
}

/**
 * ��ʼ���µĴ���
 * 
 * @param title
 *            ����
 * @param url
 *            ����
 * @param id
 *            id
 * @param width
 *            ���
 * @param height
 *            �߶�
 * @param flag
 *            ����
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
 * ��ѯ����
 * 
 * @param val
 */
function searcher1(val) {
	var queryParams = $('#wid').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#wid').combogrid("grid").datagrid('reload');
}
/**
 * ��ѯ����
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
