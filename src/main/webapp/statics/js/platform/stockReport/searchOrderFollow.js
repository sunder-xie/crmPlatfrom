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
    	    "text":"请选择",
    	    "selected":true
    	    },{
	    	 "id":1,    
	    	    "text":"未排产"   
	    	},{    
	    	    "id":2,    
	    	    "text":"已排未发"   
	    	},{    
	    	    "id":3,    
	    	    "text":"已发未到货" 
	    	},{    
	    	    "id":4,    
	    	    "text":"未开票"   
	    	},{    
	    	    "id":5,    
	    	    "text":"已完成"   
	    	}
	    ]
	});
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '数据列表',		
						url : appUrl + '/stockReport/stockReportAction!searchOrderFollowList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : false,
						height : 420,
						width : 'auto',
						frozenColumns :[[{
							field : 'orgRegion',
							title : '销售大区',
							width : 80,
							align : 'center',
							sortable : true
						}, {
							field : 'orgProvince',
							title : '销售省份',
							width : 80,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '销售城市',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商名称',
							width : 80,
							align : 'center'
						}]],
						columns : 
								[[{
									field : 'ERDAT',
									title : '订单日期',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'AUDAT',
									title : '单据日期', 
									width : 80,
									align : 'center',
									sortable : true
								},
								{
								    field : 'POSNR1',
								    title : '子订单号',
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
								    title : '数量',
						     		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'PEDTR',
									title : '排产日期',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'outOfDate',
									title : '逾期天数',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
								    field : 'VBELN2',
								    title : '交货单号',
						     		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'TKNUM',
									title : '运单号',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
							        field : 'WADAT_IST',
								    title : '出库日期',
						    		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'PODAT',
									title : 'POD日期',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
							        field : 'VBELN3',
								    title : '发票号',
						    		width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'WBSTK',
									title : '订单状态',
									width : 80,
									align : 'center',
									formatter: function(value,row,index){
										if(row.PEDTR==null){
											return "未排产";
										}else if(value!='C' && row.PEDTR!=null){
											return "已排未发";
										}else if(value=='C' && row.PDSTK!='C'){
											return "已发未到货";
										}else if(row.PDSTK=='C' && row.VBELN3==null){
											return "未开票";
										}else if(row.VBELN3!=null){
											return "已完成";
										}
									}
								}]],
						toolbar : [ "-" ,{
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
//								exportActivityPlanReport();
							}
						}, "-" ]
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
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
					title : '品项编号',
					width : 60
				}, {
					field : 'skuName',
					title : '品项名称',
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
					title : '品类编号',
					width : 60
				}, {
					field : 'categoryName',
					title : '品类名称',
					width : 200
				} ] ],
				toolbar : '#toolbarCategory'
			});
}


/**
 * 所属经分销商下拉查询
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
		$.messager.alert('提示', '组织或者经销商不能为空！', '提示');
		return;
	}
	if($('#startDate').val()=="" || $('#endDate').val()==""){
		$.messager.alert('提示', '订单日期不能为空！', '提示');
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

// 创建窗口对象
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
	initMaintAccount(false, '400', '400','组织选择', '/question/questionAction!orgTreePage.jspa',353,70);
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}

document.onkeydown = function(e) {// 这个事件在用户按下任何键盘键（包括系统按钮，如箭头键和功能键）时发生
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};