$(document).ready(function() {
	loadGrid();
	loadCust();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '����б�',		
						url : appUrl + '/stockReport/stockReportAction!searchStockTotalList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							orgId : $("#orgId").val(),
							userType : $("#userType").val()
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
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'stockId',
							hidden : true
						},{
							field : 'orgRegion',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : 'ʡ��',
							width : 80,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '�����̱��',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '������',
							width : 150,
							align : 'center'
						}, {
							field : 'userName',
							title : '�ᱨ��',
							width : 80,
							align : 'center'
						}, {
							field : 'userId',
							hidden : true
						}, {
							field : 'skuName',
							title : 'Ʒ��',
							width : 150,
							align : 'center'
						}, {
							field : 'categoryName',
							title : 'Ʒ��',
							width : 150,
							align : 'center'
						}, {
							field : 'quantity',
							title : '����',
							width : 80,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : { precision : 2 }
							}
						}, {
							field : 'unitDesc',
							title : '��λ',
							width : 80,
							align : 'center'
						}, {
							field : 'productionDate',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'checkTime',
							title : '�����������',
							width : 80,
							align : 'center'
						}, {
							field : 'createDate',
							title : '�ᱨ����',
							width : 200,
							align : 'center'
						}
//						{
//							field : 'oper',
//							title : '����',
//							width : 60,
//							align : 'center',
//							formatter : function(value, row, index) {
//								var loginId=$("#loginId").val();
//								if(loginId=="88647" || (loginId==row.userId && $("#state").val()=="1" && 
//										$("#productionDate").val()==row.checkTime)){
//									if(row.editing){ 
//										var s = '<a href="#" onclick="saverow(this)">����</a>';
//										var c = '<a href="#" onclick="cancelrow(this)">ȡ��</a>';
//										return s+'&nbsp;'+c;
//									}else{
//										return '<img style="cursor:pointer" onclick="editrow(this)" title="�޸�" src='
//										+ imgUrl
//										+ '/images/actions/action_edit.png align="absMiddle"></img>';
//									}
//								}
//							}
//						}
						]], 
						toolbar : [ "-", {
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
								exportForExcel(); 
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
	
	$('#categoryId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchCategory.jspa',
				idField : 'categoryId',
				textField : 'categoryName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'categoryId',
					title : 'Ʒ����',
					width : 60
				}, {
					field : 'categoryName',
					title : 'Ʒ������',
					width : 200
				} ] ],
				toolbar : '#toolbarCategory'
			});
	
}

function getSum(){
//	$.ajax({
//		type : "post",
//		url : appUrl + "/stockReportAction!getStockReportKunnrAndKunagSum.jspa",
//		data : {
//			kunnr : $('#kunnr').combobox("getValue"),
//            productionStartDate : $('#productionStartDate').val(),
//			productionEndDate : $('#productionEndDate').val(),
//			flag : 'kunnr_week'
//		},
//		success : function(data) {
//			$("#boxSum").val(data.flag);
//			$("#kunagName").val(data.kunag);
//		}
//	});
}

function loadCust(){
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
		url : appUrl + "/stockReportAction!updateStockReport.jspa",
		data : {
			stockId : rows[0].stockId,
			quantity : rows[0].quantity
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				loadGrid();
//			}else{
//				$.messager.alert('Tips', '����ʧ��!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
	});
}

function add() {
	if($("#state").val()=="1"){
		initMaintWindow('�������', '/stockReportAction!toCreateStockReportWeek.jspa?orgId='+$("#orgId").val()+'&&userType='+$("#userType").val(), 500, 500);
	}else{
		$.messager.alert('��ʾ', '�����ᱨʱ�䣡', '��ʾ');
	}
}

function remove() {
	var ids = new Array();
	var rows = $('#datagrid').datagrid('getSelections');
	if($("#state").val()!="1"){
		$.messager.alert('��ʾ', '�����ᱨʱ�䣡', '��ʾ');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].stockId;
		if($("#loginId").val()!="88647" && ($("#loginId").val()!=rows[i].userId || 
				$("#productionDate").val()!=rows[i].checkTime)){
			$.messager.alert('��ʾ', '��ѡ����ȷ��¼��', '��ʾ');
			return;
		}
	}
	if (ids.length==0) {
		$.messager.alert('��ʾ', 'δѡ���κ�Ȩ�޵㣡', '��ʾ');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'�Ƿ�ȷ������ɾ��?',
						function(r) {
							if (r) {
								var idsJson=ids;
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/stockReportAction!deleteStock.jspa?deleteStockIds='
										+ idsJson;
								form.target = "hideFrame";
								form.submit();
							}
						});
	}

}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportStockCsv();> 1������ģ��</a></td></tr>'
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importStockCsv('��������', html);
}

//csv���ص���ģ��
function exportStockCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportStockCsv.jspa';
			form.submit();
		}
	});

}

//����������Ϣ
function importStockCsv(t, html) {
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
													+ "/stockReportAction!importStockCsv.jspa";
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
	var regx = /^\d+$/;
	if(regx.test(name1)){
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = name1;
		queryParams.name1 = null;
		$('#kunnr').combogrid("grid").datagrid('reload');
	}else{
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = null;
		queryParams.name1 = encodeURIComponent(name1);
		$('#kunnr').combogrid("grid").datagrid('reload');
	}
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');

}

function searcherCategory(val) {
	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
	queryParams.categoryName = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');

}


function search() {
	getSum();
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	queryParams.productionStartDate = $('#productionStartDate').val();
	queryParams.productionEndDate = $('#productionEndDate').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelStockTotal.jspa';
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
//		$.messager.alert('Tips', successResult, 'info');
//		$('#datagrid').datagrid('reload');
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				search();
			}
		});
	}
}

//�������ڶ���
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

//�������ڶ���
function initMaintAccount(ffit, wWidth, wHeight, title, url, l, t) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var Ffit = ffit;
	var $win = $("#maintWindow")
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

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function choseOrg() {
	initMaintAccount(false, '400', '400','��֯ѡ��', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
	loadCust();
}

function closeOrgTree() {
	$("#maintWindow").window('close');
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