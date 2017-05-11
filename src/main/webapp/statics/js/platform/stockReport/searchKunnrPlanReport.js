$(document).ready(function() {
	loadGrid();
	loadKunnr();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	var type=[{'value':'A','text':'A'},{'value':'B','text':'B'},{'value':'C','text':'C'},{'value':'D','text':'D'}];
	var notice=[{'value':'1','text':'��'},{'value':'2','text':'��'}];
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�����̷����б�',		
						url : appUrl + '/stockReportAction!searchKunnrInTypeList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							orgId : $("#orgId").val()
						},
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
						columns :[[ {
							field : 'orgName',
							title : '������֯',
							width : 80,
							align : 'center'
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
						}, {
							field : 'kunnrStartDate',
							title : '�����̿�������',
							width : 150,
							align : 'center'
						}, {
							field : 'telNumber',
							title : '��˾�绰',
							width : 100,
							align : 'center'
						}, {
							field : 'faxNumber',
							title : '����',
							width : 100,
							align : 'center'
						}, {
							field : 'mobNumber',
							title : '���˵绰',
							width : 100,
							align : 'center'
						}, {
							field : 'type',
							title : '�����̷���',
							width : 150,
							align : 'center',
						    editor : {
								type : 'combobox',
								    options : {
									    data : type,
									    valueField : 'value',
									    textField : 'text'
								    }
							    }
						}, {
							field : 'isNotice',
							title : '������Ϣ֪ͨ',
							width : 150,
							align : 'center',
							formatter : function(v) {
								if(v=="1"){
									return "��";
								}else if(v=="2"){
									return "��";
								}
							},
						    editor : {
							type : 'combobox',
							    options : {
								    data : notice,
								    valueField : 'value',
								    textField : 'text'
							    }
						    }
						}, {
							field : 'oper',
							title : '����',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
									if(row.editing){ 
										var s = '<a href="#" onclick="saverow(this)">����</a>';
										var c = '<a href="#" onclick="cancelrow(this)">ȡ��</a>';
										return s+'&nbsp;'+c;
									}else{
										var a='<img style="cursor:pointer" onclick="editrow(this)" title="�޸�" src='
											+ imgUrl
											+ '/images/actions/action_edit.png align="absMiddle"></img>';
										var b='<a href="javascript:clear('+row.kunnr+')">���</a>';
									    return a;
									}
							}
					    }]],
					toolbar : [ "-", {
						text : '�������뾭���̷���',
						iconCls : 'icon-excel',
						handler : function() {
							importCsv();
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

function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!updateKunnrInType.jspa",
		data : {
			kunnr : rows[0].kunnr,
			type : rows[0].type,
			isNotice : rows[0].isNotice
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				search();
//			}else{
//				$.messager.alert('Tips', '����ʧ��!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
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
}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportMouldCsv();> 1������ģ��</a></td></tr>'
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importGoalCsv('��������', html);
}

//csv�����ն��ŵ굼��ģ��
function exportMouldCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportMouldCsv.jspa';
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
													+ "/stockReportAction!importKunnrPlanCsv.jspa";
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.orgId = $('#orgId').val();
	queryParams.isNotice = $('#isNotice').combobox("getValue");
	queryParams.kunnrStartDate = $('#kunnrStartDate').datebox("getValue");
	queryParams.kunnrEndDate = $('#kunnrEndDate').datebox("getValue");
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportStockForExcel.jspa';
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
		// search();
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