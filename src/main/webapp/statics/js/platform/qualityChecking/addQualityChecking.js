$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
var submitFlag=true;
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�ύ���',
						//url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa',
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
								}
								 ] ],
						toolbar : [
								{
									text : 'ɾ��ѡ����',
									iconCls : 'icon-remove',
									handler : function() {
										deleteChageRow();
									}
								},
								"-"],
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 1,
		pageList : [1],
		beforePageText : '��',
		afterPageText : 'ҳ    �� 1 ҳ',
		displayMsg : '��ǰ��ʾ   �� {total} ����¼'
	});
}
/**
 * ���棨��ʱ���棩
 */
function saveToCreate(){
	var dealerAdujstDetailList=[];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '�뵼����ϸ��');
		return;
	}
	for(var i = 0; i < rows.length; i++){
		dealerAdujstDetailList.push({
			"qualityCheckingId":rows[i].qualityCheckingId,
			"dateOfManufacture":rows[i].dateOfManufacture,
			"productionTimeStart":rows[i].productionTimeStart,
			"productionTimeEnd":rows[i].productionTimeEnd,
			"materielId":rows[i].materielId
		});
	}
	
	var form = window.document.forms[0];
	form.action = appUrl + "/qualityChecking/qualityCheckingAction!saveQualityChecking.jspa";
	form.target = "hideFrame";
	form.submit();
}

/**
 * �ᱨ����ģ��excel����
 */
function qualityCheckingImport() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>ѡ���ļ��ϴ���</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('excel����', html);
}
var mask_;
/**
 * �򿪵�����ĿExcel�Ի��� 
 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '��������',
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
										var file = document.getElementById('uploadFile').value;
										if (/^.+\.(xls|XSL)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/qualityChecking/qualityCheckingAction!qualityCheckingImportCsv.jspa";
											var form = document.getElementById('fileForm');

											form.action = action;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��xls�ļ�");
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
 * ɾ��ѡ����
 */
function deleteChageRow() {
	var rows = $('#datagrid').datagrid('getSelections');
	console.info(rows);
	if (rows == null || rows.length == 0) {
		$.messager.alert('Tips', 'ѡ�в˵���������!');
		return;
	}
	var index = $('#datagrid').datagrid('getRowIndex', $("#datagrid").datagrid('getSelected'));
	$('#datagrid').datagrid('deleteRow',index);

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
/**
 * excelDialog����
 */
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		submitFlag=true;
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
			}
			$('#datagrid').datagrid({
				url : appUrl + '/qualityChecking/qualityCheckingAction!getAddQualityCheckingJsonList.jspa?'
			});
			if ($('#nextUserDialog').length > 0) {
				$('#nextUserDialog').dialog('close');
			}
		});
	}
}
function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
document.onkeydown = function(e) {// ����¼����û������κμ��̼�������ϵͳ��ť�����ͷ���͹��ܼ���ʱ����
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
