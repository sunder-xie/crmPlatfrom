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
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						rownumbers : true,
						pageSize : 20,
						height : 420,
						width : 'auto',
						queryParams : {
							orgIds : $("#orgId").val()
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
						columns : [[{
							title : '',
							align : 'center',
							colspan : 8
						},{
							title : '���ܲ���ģ��',
							align : 'center',
							colspan : 14
						},{
							title : '���ܲ���Ԥ��',
							align : 'center',
							colspan : 4
						},{
							title : '��Ԥ��׷��',
							align : 'center',
							colspan : 2
						}],
						[{
							field : 'skuId',
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
							width : 100,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '������',
							width : 150,
							align : 'center'
						}, {
							field : 'skuName',
							title : 'Ʒ��',
							width : 150,
							align : 'center'
						}, {
							field : 'skuVolume',
							title : '�������',
							width : 80,
							align : 'center'
						}, {
							field : 'skuPrice',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'isImportantSku',
							title : '�Ƿ��ص�Ʒ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockWarning',
							title : 'Ԥ�����',
							width : 80,
							align : 'center'
						}, {
							field : 'salesPlanNextWeek',
							title : 'δ��һ�ܷ���Ԥ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockStandard',
							title : '��׼�����',
							width : 80,
							align : 'center'
						}, {
							field : 'estimateStock',
							title : 'ʵʱ���',
							width : 80,
							align : 'center'
						}, {
							field : 'orderOnWay',
							title : '��;����',
							width : 80,
							align : 'center'
						}, {
							field : 'putNotTakeOrder',
							title : '����δ��',
							width : 80,
							align : 'center'
						}, {
							field : 'stockTotal',
							title : '���С��',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeed',
							title : '�������󶩵�',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeedVolume',
							title : '�������󶩵����',
							width : 80,
							align : 'center'
						}, {
							field : 'productSum',
							title : '�ճ��󶩵�',
							width : 80,
							align : 'center'
						}, {
							field : 'productSumVolume',
							title : '�ճ������',
							width : 80,
							align : 'center'
						}, {
							field : 'notPutOrder',
							title : 'δ�Ŷ���',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrder',
							title : '���ϵ���',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderPrice',
							title : '�貹����',
							width : 80,
							align : 'center'
						}, {
							field : 'stockStandardNextWeek',
							title : '���ܱ�׼�����',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeedNextWeek',
							title : '������',
							width : 80,
							align : 'center'
						}, {
							field : 'productSumNextWeek',
							title : '��������ƻ�',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderPriceNextWeek',
							title : '�������',
							width : 80,
							align : 'center'
						}, {
							field : 'stockWarningTwoWeek',
							title : '���Ԥ����14�죩',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderFirst',
							title : '�������ȷ�����',
							width : 80,
							align : 'center'
						}]], 
						toolbar : [ "-",{
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
		pageSize : 20,
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
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgIds='+$('#orgId').val()+'&bhxjFlag='+'C',
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
}

/**
 * ������������ѯ
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgIds = $('#orgId').val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.isImportant = $('#isImportant').combobox('getValue');
	$("#datagrid").datagrid({
		url : appUrl + '/kunnrManager/kunnrManagerAction!searchProductionNeedList.jspa'
	});
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/kunnrManager/kunnrManagerAction!exportForExcelStockSafety.jspa';
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
				$('#excelProductionPlanDialog').dialog('close');
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
//	initMaintAccount(false, '400', '400','��֯ѡ��', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
	initMaintAccount(false, '400', '400','��֯ѡ��', '/newOrgAction!newOrgTree.jspa',353,70);
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
		editeEnd();
		return false;
	}
	return true;
};