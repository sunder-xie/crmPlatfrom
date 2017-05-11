$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�����б�',		
						url : appUrl + '/visitInfo/visitInfoAction!searchStock.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						fitColumns:true,
						height : 420,
						width : 'auto',
						columns :[[ {
							field : 'RCOrgName',
							title : 'ʡ��',
							width : 80,
							align : 'center'
						}, {
							field : 'POrgName',
							title : 'ʡ��',
							width : 80,
							align : 'center'
						}, {
							field : 'orgName',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '������',
							width : 130,
							align : 'center'
						}, {
							field : 'custId',
							title : '�ŵ�id',
							width : 80,
							align : 'center'
						}, {
							field : 'custName',
							title : '�ŵ�',
							width : 100,
							align : 'center'
						}, {
							field : 'empOrgName',
							title : '��������֯',
							width : 80,
							align : 'center'
						}, {
							field : 'stationName',
							title : '�����˸�λ',
							width : 80,
							align : 'center'
						}, {
							field : 'empName',
							title : '������',
							width : 80,
							align : 'center'
						}, {
							field : 'skuName',
							title : 'Ʒ��',
							width : 120,
							align : 'center'
						}, {
							field : 'quantity',
							title : '����',
							width : 80,
							align : 'center'
						}, {
							field : 'unitDesc',
							title : '��λ',
							width : 60,
							align : 'center'
						}, {
							field : 'year',
							title : '��',
							width : 60,
							align : 'center'
						}, {
							field : 'month',
							title : '��',
							width : 60,
							align : 'center'
						}, {
							field : 'createDate',
							title : '��������',
							width : 150,
							align : 'center'
						}]], 
						toolbar : [ "-",{
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
								exportForExcel(); 
							}
						}, "-" ]
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
	
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C&kunnrId='+$('#isOffice').val(),
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
						+ '/visitInfoAction!getSkuName.jspa',
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
	
	$('#custId').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/customerAction!customerSearch.jspa?orgId='+$('#orgId').val()+'&custKunnr='+$('#isOffice').val(),
		idField : 'custId',
		textField : 'custName',
		multiple : false,
		columns : [ [ {
			field : 'custId',
			title : '�ͻ����',
			width : 120
		}, {
			field : 'custName',
			title : '�ͻ�����',
			width : 200
		} ] ],
		toolbar : '#toolbarCust'
	});
}

/**
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');

}

function searcherCust(val) {
	var queryParams = $('#custId').combogrid("grid").datagrid('options').queryParams;
	queryParams.custName = encodeURIComponent(val);
	$('#custId').combogrid("grid").datagrid('reload');

}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.custKunnr = $('#custKunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.custId = $('#custId').combobox("getValue");
	queryParams.s_month = $('#month').val();
	queryParams.s_year = $('#year').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/visitInfo/visitInfoAction!exportStockForExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/visitInfo/visitInfoAction!checkDownLoadOver.jspa?",
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
	initMaintAccount(false, '400', '400','��֯ѡ��', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
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