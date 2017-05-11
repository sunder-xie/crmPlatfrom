$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadKunnr();
	loadGrid();
	getSum();
});

function getSum(){
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!getOrderFollowSum.jspa",
		data : {
			kunnr : $('#kunnr').combobox("getValue"),
			orgId : $('#orgId').val(),
		    skuId : $('#skuId').combobox("getValue"),
		    categoryId : $('#categoryId').combobox("getValue"),
		    state : $('#orderState').combobox("getValue"),
		    startDate : $('#startDate').val(),
		    endDate : $('#endDate').val()
		},
		success : function(data) {
			$("#boxSum").val(data.POSNR1);
			$("#boxSumCo").val(data.POSNR2);
		}
	});
}

function loadGrid() {
	$('#orderState').combobox({
		valueField:'id',    
	    textField:'text',
	    panelHeight:'auto',
	    data:[{    
    	    "id":0,    
    	    "text":"��ѡ��",
    	    "selected":true
    	    },{
	    	 "id":1,    
	    	    "text":"δ�Ų�"   
	    	},{    
	    	    "id":2,    
	    	    "text":"����δ��"   
	    	},{    
	    	    "id":3,    
	    	    "text":"�ѷ�δ����" 
	    	},{    
	    	    "id":4,    
	    	    "text":"δ��Ʊ"   
	    	},{    
	    	    "id":5,    
	    	    "text":"�����"   
	    	}
	    ]
	});
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�����б�',		
						url : appUrl + '/stockReport/stockReportAction!searchOrderFollowList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : false,
						height : 420,
						width : 'auto',
						frozenColumns :[[{
							field : 'orgRegion',
							title : '���۴���',
							width : 80,
							align : 'center',
							sortable : true
						}, {
							field : 'orgProvince',
							title : '����ʡ��',
							width : 80,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '���۳���',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '����������',
							width : 80,
							align : 'center'
						}]],
						columns : 
								[[{
									field : 'ERDAT',
									title : '��������',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'AUDAT',
									title : '��������', 
									width : 80,
									align : 'center',
									sortable : true
								},
								{
								    field : 'POSNR1',
								    title : '�Ӷ�����',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'skuName',
									title : 'SKU',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
								    field : 'KWMENG',
								    title : '����',
						     		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'PEDTR',
									title : '�Ų�����',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'outOfDate',
									title : '��������',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
								    field : 'VBELN2',
								    title : '��������',
						     		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'TKNUM',
									title : '�˵���',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
							        field : 'WADAT_IST',
								    title : '��������',
						    		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'PODAT',
									title : 'POD����',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
							        field : 'VBELN3',
								    title : '��Ʊ��',
						    		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'WBSTK',
									title : '����״̬',
									width : 80,
									align : 'center',
									formatter: function(value,row,index){
										if(row.PEDTR==null){
											return "δ�Ų�";
										}else if(value!='C' && row.PEDTR!=null){
											return "����δ��";
										}else if(value=='C' && row.PDSTK!='C'){
											return "�ѷ�δ����";
										}else if(row.PDSTK=='C' && row.VBELN3==null){
											return "δ��Ʊ";
										}else if(row.VBELN3!=null){
											return "�����";
										}
									}
								}]],
						toolbar : [ "-" ,{
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
//								exportActivityPlanReport();
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
				panelWidth : 320,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchSku.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
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
				panelWidth : 320,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchCategory.jspa',
				idField : 'categoryId',
				textField : 'categoryName',
				columns : [
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
	queryParams.skuName = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');
}
 
function search() {
	if($('#orgId').val()=="" && $('#kunnr').combobox("getValue")==""){
		$.messager.alert('��ʾ', '��֯���߾����̲���Ϊ�գ�', '��ʾ');
		return;
	}
	if($('#startDate').val()=="" || $('#endDate').val()==""){
		$.messager.alert('��ʾ', '�������ڲ���Ϊ�գ�', '��ʾ');
		return;
	}
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	queryParams.state = $('#orderState').combobox("getValue");
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	$("#datagrid").datagrid('load');
	getSum();
}
function exportActivityPlanReport() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/activityPlanReport/activityPlanReportAction!searchaActivityPlanReportList2downLoad.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/activityPlanReport/activityPlanReportAction!checkDownLoadOver.jspa?",
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
	var $win = $("#maintActivityPlanReport")
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
	$("#maintActivityPlanReport").window('close');
}

function choseOrg() {
	initMaintAccount(false, '400', '400','��֯ѡ��', '/question/questionAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#maintActivityPlanReport").window('close');
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