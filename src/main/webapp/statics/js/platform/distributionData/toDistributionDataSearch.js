var processInstanceId;
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/distributionDataAction!getDistributionDataList.jspa',
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
									field : 'dgId',
									title : 'ID',
									width : setColumnWidth(0.04),
									align : 'center',
									hidden : true
								},
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var createDate = row.createDate;
										var thisSysdate = row.thisSysdate;
										if(createDate == thisSysdate){
										    if (row.editing) {
											    var s = '<a href="#" onclick="saverow(this)">����</a> ';
											    var c = '<a href="#" onclick="cancelrow(this)">ȡ��</a>';
											    return s + c;
										    } else {
											    var e = '<img style="cursor:pointer" onclick="updaterow(this)" title="�޸�" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>&nbsp&nbsp'
											    return e;
										    }
										}
									}
								},
								{
									field : 'inputName',
									title : '������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'createDate',
									title : '��������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgRegion',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgProvince',
									title : 'ʡ��',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgCity',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'kunnrId',
									title : '�����̱��',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								},
								{
									field : 'kunnrName',
									title : '����������',
									width : setColumnWidth(0.18),
									align : 'center'

								},
								{
									field : 'inputDate',
									title : '��������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'firstUser',
									title : '���о���',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'secondUser',
									title : 'ʡ������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'thirdUser',
									title : '��������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'aOneYW',
									title : 'A1Ҭ������(ԭζ)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneXY',
									title : 'A1Ҭ������(����)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneMX',
									title : 'A1Ҭ������(����)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneCM',
									title : 'A1Ҭ������(��ݮ)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneQKL',
									title : 'A1Ҭ������(�ɿ���)-80g*30',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneKF',
									title : 'A1Ҭ������(����)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneLC',
									title : 'A1Ҭ������(�̲�)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThree',
									title : 'A3Ҭ������װ-80g*30',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOne',
									title : 'B1�춹����-64g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOne',
									title : 'C1��Բ���浥��-65g*30',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFour',
									title : 'A4Ҭ�����װ-80g*12*8',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFive',
									title : 'A5Ҭ�����װ-80g*8*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSix',
									title : 'A6Ҭ����ͥ����װ-80g*16*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSix',
									title : 'C6��Բ�����ͥ����װ-65g*16*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSeven',
									title : 'A7Ҭ����ͥ����װ-80g*15*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSeven',
									title : 'C7��Բ�����ͥ����װ-65g*15*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoQKL',
									title : 'A2Ҭ������װ(�ɿ���)-80g*6*6',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoXY',
									title : 'A2Ҭ������װ(����)-80g*6*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoYW',
									title : 'A2Ҭ������װ(ԭζ)-80g*6*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightYW',
									title : 'A8Ҭ������װ(ԭζ)-80g*3*10',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightXY',
									title : 'A8Ҭ������װ(����)-80g*3*10',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEight',
									title : 'B8�춹����װ-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEight',
									title : 'C8��Բ��������װ-65g*3*10',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSeven',
									title : 'H7�����̲赥ƿ-400ml*15',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine9',
									title : 'D9��ֵ���װ(ȫ�Ҹ���Ͼ������ר��1*9)',
									width : setColumnWidth(0.22),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine12',
									title : 'D9��ֵ���װ(�μ��˺춹��Բ�������ר��1*12)',
									width : setColumnWidth(0.25),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine20',
									title : 'D9��ֵ���װ(����ζ�ͷ�Ҭ��ϵ�е���ר��1*20)',
									width : setColumnWidth(0.25),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine30',
									title : 'D9��ֵ���װ(��ֵ���װ1*30)',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTen',
									title : 'D10�������װ-15*6',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOne',
									title : 'E1�������װ-12*6',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'lastDate',
									title : '����޸�ʱ��',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden : true
								},
								{
									field : 'thisSysdate',
									title : 'ϵͳʱ��',
									align : 'center',
									hidden : true
								}
								/*
								 * { field : 'resign_date', title : 'ҵ����Ա��ְ����',
								 * width : setColumnWidth(0.10), align :
								 * 'center' },
								 */
								 ] ],
						onBeforeEdit : function(index, row) {
							row.editing = true;
							updateActions(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						toolbar : [
								"-",
								{
									text : 'ɾ��',
									iconCls : 'icon-remove',
									handler : function() {
										deleteDistributionData();
									}
								},
								"-",
								{
									text : 'ģ������',
									iconCls : 'icon-excel',
									handler : function() {
										exportMould();
									}
								},
								"-",
								{
									text : '��������',
									iconCls : 'icon-add',
									handler : function() {
										importDistributionData();
									}
								},
								"-",
								{
									text : '���ݵ���',
									iconCls : 'icon-download',
									handler : function() {
										exportDistributionData();
									}
								},
								"-",
								{
									text : '1.¼��������������Ǳ��䣻2.���о���ʡ������Ϊ�����û��������|��������Ϊ�Ǳ�����',
									handler : function() {
									}
								}, "-" ]
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

// excel����
function exportDistributionData() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataAction!exportDistributionData.jspa';
	form.submit();
	$.messager.alert('Tips', '���ڵ���,���Ժ�!', 'warning');
}

function closeDtPlan() {
	$("#MaintDistributionData").window('close');
}

function initMaintDistributionData(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionData")
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

function search() {
	$("#datagrid").datagrid('reload', {
		orgId : $('#orgId').val(),
		kunnrId : $('#kunnrId').val(),
		orgName : encodeURIComponent($('#orgName').val()),
		kunnrName : encodeURIComponent($("#kunnrName").val()),
		startDate : document.getElementsByName("startDate")[0].value,
		endDate : document.getElementsByName('endDate')[0].value
	});
}

function updateDistributionData(ctId) {//
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintData('Ŀ�����޸�', '/goalAction!updateDataPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importDistributionData() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>ѡ���ļ��ϴ���</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('����Ŀ��', html);
}
var mask_;
/* �򿪵�����ĿExcel�Ի��� */
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
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/distributionData/distributionDataAction!importDistributionDataCsv.jspa";
											var form = document
													.getElementById('fileForm');

											form.action = action;
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
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

/* ���û�������ĿExcel���м�� */
function importCheck() {
	var file = document.getElementById('projectImportFile').value;
	if (/^.+\.(xls|xlsx)$/.test(file)) {
		document.getElementById('fileForm').submit();
		$('#import').dialog('close');
		mask_ = new mask({
			tip : '��ȴ��������...'
		});
		mask_.show();
		window.setTimeout(getImportStateInfo, 2000);
	} else {
		$.messager.alert("��ʾ", "�뵼��Excel�ļ�");
	}
}
function importContractMould() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>��������</td>'
			+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="type" id="type" value="Y"  style="width:200px"/>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openImportDialog('����Ŀ��', html);
}
function selectOrgTree() {
	initMaintDistributionData('ѡ����֯',
			'/distributionDataAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
function importMatterModel() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionData/distributionDataAction!importDistributionDataModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deleteDistributionData() {
	var rows = $('#datagrid').datagrid('getSelections');
	console.info(rows);
	if (rows == null || rows.length == 0) {
		$.messager.alert('Tips', 'ѡ�в˵���������!');
		return;
	}
	$.messager.progress();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {

		ids.push(rows[i].dgId);

	}
	$("#ids").val(ids);
	var form = window.document.forms[0];
	form.action = "distributionDataAction!deleteDistributionData.jspa";
	form.target = "hideFrame";
	form.submit();

}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function updaterow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}
function saverow(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rowss = $('#datagrid').propertygrid('getChanges');

	var dgId = rowss[0].dgId;
	var aOneYW = rowss[0].aOneYW;
	var aOneXY = rowss[0].aOneXY;
	var aOneMX = rowss[0].aOneMX;
	var aOneCM = rowss[0].aOneCM;
	var aOneQKL = rowss[0].aOneQKL;
	var aOneKF = rowss[0].aOneKF;
	var aOneLC = rowss[0].aOneLC;
	var aTwoQKL = rowss[0].aTwoQKL;
	var aTwoXY = rowss[0].aTwoXY;
	var aTwoYW = rowss[0].aTwoYW;
	var aThree = rowss[0].aThree;
	var aFour = rowss[0].aFour;
	var aFive = rowss[0].aFive;
	var aSix = rowss[0].aSix;
	var aSeven = rowss[0].aSeven;
	var aEightYW = rowss[0].aEightYW;
	var aEightXY = rowss[0].aEightXY;
	var bOne = rowss[0].bOne;
	var bSix = rowss[0].bSix;
	var bEight = rowss[0].bEight;
	var cOne = rowss[0].cOne;
	var cSix = rowss[0].cSix;
	var cSeven = rowss[0].cSeven;
	var cEight = rowss[0].cEight;
	var dNine9 = rowss[0].dNine9;
	var dNine12 = rowss[0].dNine12;
	var dNine20 = rowss[0].dNine20;
	var dNine30 = rowss[0].dNine30;
	var dTen = rowss[0].dTen;
	var eOne = rowss[0].eOne;
	var hSeven = rowss[0].hSeven;

	$("#dgId").val(dgId);
	$("#aOneYW").val(aOneYW);
	$("#aOneXY").val(aOneXY);
	$("#aOneMX").val(aOneMX);
	$("#aOneCM").val(aOneCM);
	$("#aOneQKL").val(aOneQKL);
	$("#aOneKF").val(aOneKF);
	$("#aOneLC").val(aOneLC);
	$("#aTwoQKL").val(aTwoQKL);
	$("#aTwoXY").val(aTwoXY);
	$("#aTwoYW").val(aTwoYW);
	$("#aThree").val(aThree);
	$("#aFour").val(aFour);
	$("#aFive").val(aFive);
	$("#aSix").val(aSix);
	$("#aSeven").val(aSeven);
	$("#aEightYW").val(aEightYW);
	$("#aEightXY").val(aEightXY);
	$("#bOne").val(bOne);
	$("#bSix").val(bSix);
	$("#bEight").val(bEight);
	$("#cOne").val(cOne);
	$("#cSix").val(cSix);
	$("#cSeven").val(cSeven);
	$("#cEight").val(cEight);
	$("#dNine9").val(dNine9);
	$("#dNine12").val(dNine12);
	$("#dNine20").val(dNine20);
	$("#dNine30").val(dNine30);
	$("#dTen").val(dTen);
	$("#eOne").val(eOne);
	$("#hSeven").val(hSeven);

	var form = window.document.forms[0];
	form.action = "distributionDataAction!updateDistributionData.jspa";
	form.target = "hideFrame";
	form.submit();
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionData/distributionDataAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear() {
	$('#kunnrId').val("");
	$('#kunnrName').val("");
	$("#startDate").datebox('setValue', '');
	$("#endDate").datebox('setValue', '');
	$("#orgId").val("");
	$("#orgName").val("");
	$("#trFlag").combobox('setValue', '');
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
				$('#excelDialog').dialog('close');
				search();
			}

		});
	}
}

function updateDataList() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>�����޸ĵ���</td>'
			+ '<td><input type="hidden" id="flag" value="update"/><input type="hidden" name="kunnrDataType" id="kunnrDataType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>��������</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--��֯Ŀ��--</option>'
			+ '<option value="Y" selected>--������Ŀ��--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('�����޸�Ŀ��', html);
}
function change() {
	$('#orgId').val('');
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
