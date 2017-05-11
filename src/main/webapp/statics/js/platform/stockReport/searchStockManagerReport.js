$(document).ready(function() {
	loadGrid();
	loadKunnr();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�б�',		
						url : appUrl + '/stockReportAction!searchStockManagerReportList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						columns :[[ {
							field : 'orgRegion',
							title : '����',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'orgProvince',
							title : 'ʡ��',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'orgCity',
							title : '����',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'kunnr',
							title : '�����̱��',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '����������',
							width : 150,
							align : 'center'
						}, 
//						{
//							field : 'year',
//							title : '���',
//							width : 80,
//							align : 'center'
//						}, {
//							field : 'month',
//							title : '�·�',
//							width : 80,
//							align : 'center'
//						}, 
						{
							field : 'lastStock',
							title : '���ڿ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafe1',
							title : '��ȫ���',
							width : 80,
							align : 'center'
						}, {
							field : 'stockNotSafe',
							title : 'ʵ�ʿ��-��ȫ���',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafeFlag',
							title : '�����',
							width : 80,
							align : 'center'
						}, {
							field : 'onWayNum',
							title : '������;',
							width : 80,
							align : 'center'
						}, {
							field : 'outPutNotCheck',
							title : '����δ����',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafeExpect',
							title : 'չ����ȫ���',
							width : 80,
							align : 'center'
						}, {
							field : 'stockRealExpect',
							title : 'չ��ʵ�ʿ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockNotSafeExpect',
							title : 'չ������',
							width : 80,
							align : 'center'
						}, {
							field : 'outPutNotNum',
							title : 'δ�Ų�����',
							width : 80,
							align : 'center'
						}]],
					toolbar : [ "-", {
						text : '��������',
						iconCls : 'icon-excel',
						handler : function() {
//							exportForExcel();
						}
					}, "-"]
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	
	
}

function loadKunnr(){

	$('#kunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
	
	$('#skuId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchSku.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : 'Ʒ����',
					width : 60
				}, {
					field : 'skuName',
					title : 'Ʒ������',
					width : 200
				} ] ],
				toolbar : '#toolbarSku'
			});
	
//	function getRegion(level) {
//		$('#region' + level).combobox(
//						{
//							editable : false,
//							multiple : false,
//							url : appUrl
//									+ '/salesMsgAction!searchRegion.jspa?level='
//									+ level + '&pid='
//									+ $('#region').val(),
//							textField : 'text',
//							valueField : 'id',
//							onLoadSuccess : function() {
//								if ($('#bgXzAddress').val()!='') {
//									$('#region').val($('#region'+ level).combobox('getValue'));
//									if (level == 1) {
//										$('#bgXzProvince').val($('#region'+ level).combobox('getText'));
//									} else if (level == 2) {
//										$('#bgXzCity').val($('#region'+ level).combobox('getText'));
//									}
//									++level;
//									getRegion(level);
//								} else {
//									$('#region' + level).combobox("setText",'��ѡ��');
//									++level;
//								}
//
//							},
//							onSelect : function(re) {
//								$('#region').val(re.id);
//								if (level == 2) {
//									$('#bgXzProvince').val(re.text);
//								} else if (level == 3) {
//									$('#bgXzCity').val(re.text);
//								}
//								$('#bgXzAddress').val($('#bgXzProvince').val()+ $('#bgXzCity').val());
//								getRegion(level);
//							}
//
//						});
//
//	};
//	getRegion(1);
}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportGoalCsv();> 1������ģ��</a></td></tr>'
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importGoalCsv('��������', html);
}

//csv�����ն��ŵ굼��ģ��
function exportGoalCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportGoalCsv.jspa';
			form.submit();
		}
	});

}

//���������ն��ŵ���Ϣ
function importGoalCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
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
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("��ʾ", "��ѡ��������֯");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("��ʾ",
										 * "��ѡ��ҵ������"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											openTime();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/stockReportAction!importSalesGoalCsv.jspa?goalType=A";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/**
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnr').combogrid("grid").datagrid('reload');
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');

}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.checkTime = $('#checkTime').val();
	queryParams.productionDate = $('#productionDate').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelStockManager.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$('#excelDialog').dialog('close');
		$('#datagrid').datagrid('reload');
	}
}

// �������ڶ���
function initMaintAccount(ffit, wWidth, wHeight, title, url, l, t) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var Ffit = ffit;
	var $win = $("#maintActivityPlanInfo")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						fit : Ffit,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');

}
function closeMaintActivityPlanRepoty() {
	$("#maintActivityPlanInfo").window('close');
}

function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}

function choseOrg() {
	initMaintAccount(false, '400', '400','��֯ѡ��', '/question/questionAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#maintActivityPlanInfo").window('close');
}
function reloadDatagrid() {
	self.location.reload(true);
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