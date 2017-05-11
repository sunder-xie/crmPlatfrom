var defaultOrgId=$('#orgId').val();
$(document).ready(function() {
	loadGrid();
	loadKunnr();
	$('#hideFrame').bind('load', promgtMsg);
});
function dateFormatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function loadGrid() {
var curr_time = new Date();   
$("#stockDate").datebox("setValue",dateFormatter(curr_time));
$("#stockDate").datebox({disabled:true});
$('#datagrid')
		.datagrid({
					iconCls : 'icon-list',
					title : '数据列表',		
					url : appUrl + '/stockReportAction!searchStockWarningList.jspa?flag=S',
					loadMsg : '数据远程载入中,请等待...',
					singleSelect : true,
					pagination : true,
					nowrap : true,
					rownumbers : false,
					height : 420,
					width : 'auto',
					frozenColumns : [[{
						field : 'orgRegion',
						title : '销售大区',
						width : 80,
						align : 'center'
						//sortable : true
					}, {
						field : 'orgProvince',
						title : '销售省份',
						width : 80,
						align : 'center'
						//sortable : true
					}, {
						field : 'orgCity',
						title : '销售城市',
						width : 80,
						align : 'center'
						//sortable : true
					},{
						field : 'kunnr',
						title : '经销商编号',
						width : 80,
						align : 'center',
						hidden : false
					},{
						field : 'kunnrName',
						title : '经销商名称',
						width : 80,
						align : 'center'
						//able : true
					}]],
					columns : [[{
						field : 'endDate',
						title : '需上单时间',
						width : 80,
						align : 'center'
						//sortable : true
					}, {
						field : 'needOrder',
						title : '需上单量',
						width : 80,
						align : 'center'
						//sortable : true
					},{
						field : 'orderDate',
						title : '实际上单时间',
						width : 80,
						align : 'center',
						hidden : false,
						formatter : function(value, row, rec) {
							return value.substr(0,10);
					}
					},{
						field : 'factOrder',
						title : '实际上单量',
						width : 80,
						align : 'center'
						//able : true
					}, {
						field : 'completePercent',
						title : '完成情况%',
						width : 80,
						align : 'center',
						formatter : function(value, row, rec) {
							var fact=parseFloat(row.factOrder);
							var need=parseFloat(row.needOrder);
							if(need==0 || need==''){
								var v=0;
							}else{
								var v=fact/need*100;
							}
							
							return v.toFixed(2)+"%";
					}
					}]],
					
					loadFilter:function(data){
						if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
			                data = {
			                    total: data.length,
			                    rows: data
			                }
			            }
			            var dg = $(this);
			            var opts = dg.datagrid('options');
			            var pager = dg.datagrid('getPager');
			            pager.pagination({
			                onSelectPage:function(pageNum, pageSize){
			                    opts.pageNumber = pageNum;
			                    opts.pageSize = pageSize;
			                    pager.pagination('refresh',{
			                        pageNumber:pageNum,
			                        pageSize:pageSize
			                    });
			                    dg.datagrid('loadData',data);
			                }
			            });
			            if (!data.originalRows){
			                data.originalRows = (data.rows);
			            }
			            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			            var end = start + parseInt(opts.pageSize);
			            data.rows = (data.originalRows.slice(start, end));
			            return data;
					},
					toolbar : [ /**"-" ,{
						text : '批量导出',
						iconCls : 'icon-download',
						handler : function() {
							exportForExcel();
						}
					}, */"-", {
						text : '重置',
						iconCls : 'icon-set',
						handler : function() {
							reSet();
						}
					}, "-",{
						text : '发送短信',
						iconCls : 'icon-email_go',
						handler : function() {
							sendMessage();
						}
					}]
				});
	// 分页控件
/**	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});	
	*/
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
function reSet(){
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/stockReport/stockReportAction!setStockWarningList.jspa?",
		success : function(data) {
				$.messager.progress('close');
				$("#datagrid").datagrid('load');
			}
		
	});
	
}
function sendMessage(){
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/stockReport/stockReportAction!sendStockWarningMessage.jspa?",
		success : function(data) {
				$.messager.progress('close');
				 /** var str = JSON.stringify(data);
				var arr=str.split(',split,');
				for(var i=1;i<arr.length-1;i++){
					alert(arr[i]);
				}*/
				if(data.indexOf("成功")!=-1){
					$.messager.show({
						title:'提示',
						msg:'短信发送成功',
						timeout:5000,
						showType:'slide'
					});
				}else{
					$.messager.show({
						title:'提示',
						msg:'短信发送成功',
						timeout:5000,
						showType:'slide'
					});
				}
				
				//$("#datagrid").datagrid('load');
			}
		
	});
	
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combogrid("getValue");
	queryParams.endDate = $('#stockDate').val();
	//queryParams.skuId = $('#skuId').combobox("getValue");
	//queryParams.categoryId = $('#categoryId').combobox("getValue");
	$("#datagrid").datagrid('load');
}


function clearValue(){
	$('#orgId').val(defaultOrgId);
	$('#kunnr').combogrid("setValue",'');
	//$('#skuId').combogrid("setValue",'');
	//$('#categoryId').combogrid("setValue",'');
	//$('#stockDate').datebox("setValue",dateFormatter(new Date()));
	
}

/**
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
}*/
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

// 创建窗口对象
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
/**
function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}
*/
function choseOrg() {
	initMaintAccount(false, '400', '400','组织选择', '/saleItemsAction!orgTreePage.jspa',353,70);
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