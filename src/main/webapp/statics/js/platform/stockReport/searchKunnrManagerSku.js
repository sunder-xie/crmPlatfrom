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
						title : '�����б�',		
						url : appUrl + '/stockReport/stockReportAction!searchKunnrManagerSkuList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 'auto',
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
						frozenColumns : [[{
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'orgRegion',
							title : '����',
							width : 30,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : 'ʡ��',
							width : 30,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '����',
							width : 40,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '�����̱��',
							width : 60,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '������',
							width : 100,
							align : 'center'
						}, {
							field : 'skuName',
							title : '������',
							width : 100,
							align : 'center'
						}, {
							field : 'categoryName',
							title : '������',
							width : 100,
							align : 'center'
						}, {
							field : 'year',
							title : '��',
							width : 40,
							align : 'center'
						}, {
							field : 'month',
							title : '��',
							width : 40,
							align : 'center'
						}]],
						columns :[[{
							title : 'Ŀ���ᱨ',
							width : 330,
							align : 'center',
							colspan : 3
						}, {
							title : 'Ҫ���ƻ�',
							width : 320,
							align : 'center',
							colspan : 3
						}, {
							title : '��ȫ���',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : 'Ԥ�����',
							width : 80,
							align : 'center'
						}, {
							title : 'ͬ�ڷ���',
							width : 240,
							align : 'center',
							colspan : 3
						}, {
							title : '���ۼƷ���',
							width : 340,
							align : 'center',
							colspan : 3
						}], [{
							field : 'salesPlan',
							title : '���·���Ԥ��(�հ�)',
							width : 110,
							align : 'center'
						}, {
							field : 'salesPlanNext',
							title : '���·���Ԥ��(����)',
							width : 110,
							align : 'center'
						}, {
							field : 'salesPlanLast',
							title : '���·���Ԥ��(����չʾ)',
							width : 110,
							align : 'center'
						}, {
							field : 'productionPlan',
							title : '���£��հ壩',
							width : 80,
							align : 'center'
						}, {
							field : 'productionPlanNext',
							title : '���£����棩',
							width : 80,
							align : 'center'
						}, {
							field : 'productionPlanLast',
							title : '���£�����չʾ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyLimit',
							title : '��������',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafety',
							title : '��������',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyNextLimit',
							title : '��������',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyNext',
							title : '��������',
							width : 80,
							align : 'center'
						}, {
							field : 'lastEstimateStock',
							title : 'Ԥ�����µ׿��',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYearLast',
							title : 'ͬ����һ����ʵ�ʷ���',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYear',
							title : 'ͬ�ڵ���ʵ�ʷ���',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYearNext',
							title : 'ͬ�ڴ���ʵ�ʷ���',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYear',
							title : '���ۼƷ�����',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYearGoalEstimate',
							title : '���ۼƷ���Ŀ��(�����հ�Ŀ��)',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYearPercent',
							title : '���ۼƴ��%',
							width : 100,
							align : 'center'
						}
//						,{
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
//					}
					]], 
						toolbar : [ "-",{
							text : '��������',
							iconCls : 'icon-excel ',
							handler : function() {
								exportForExcel();
							}
						}, "-",{
							text : '<font color="red">��λ����Ȼ��</font>'
						}]
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
					+ '/stockReportAction!exportKunnrManagerCsv.jspa';
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
													+ "/stockReportAction!importKunnrManagerCsv.jspa";
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

function searcherCategory(val) {
	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
	queryParams.categoryName = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');
}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcel.jspa';
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
function clearValue(){
	$('#kunnr').combobox("clear");
	$('#categoryId').combobox("clear");
	$('#skuId').combobox("clear");
	$('#startDate').val("");
	$('#endDate').val("");
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