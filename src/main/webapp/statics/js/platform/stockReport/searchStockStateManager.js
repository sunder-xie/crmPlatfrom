//var dd={"total":1,"rows":[{"id":1,"name":"Chai","price":18.00}],"footer":[{"name":"orgRegion","price":18.00}]};
var defaultOrgId=$('#orgId').val();
var defaultOrgName=$('#orgName').val();
var defaultData=null;
var dd=null;
//var sqmax=0.00;
//var sqmin=0.00;
//var factq=0.00;
//var onway=0.00;
//var pnots=0.00;
//var notp=0.00;
//var needp=0.00;
//var needo=0.00;
var firstLoad=true;
$(document).ready(function() {
	
	loadGrid();
	loadKunnr();
	//loadData();
	$('#hideFrame').bind('load', promgtMsg);
});

function dateFormatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
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
	$("#endDate").datebox("setValue",dateFormatter(new Date()));
	$("#endDate").datebox({disabled:true});
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
	queryParams.categoryName = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');
}

function loadGrid() {
	$('#datagrid')
			.datagrid({
						iconCls : 'icon-list',
						title : '库存清单',		
						url : appUrl + '/stockReportAction!searchStockStateManagerList1.jspa',
						loadMsg : '数据远程载入中,请等待...',
						//data:dd,
						singleSelect : true,
						pagination : true,
						nowrap : true,
						//rownumbers : true,
						height : 420,
						//width : 'auto',
						showFooter : true,
						frozenColumns :[[ {
							field : 'orgRegion',
							title : '销售大区',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								if(row.orgRegion!='标箱合计： '){
									return '<u>'+value+'</u>';
								}else{
									return value;
								}
						}
							//sortable : true
						}, {
							field : 'orgProvince',
							title : '销售省份',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								if(row.orgRegion!='标箱合计： '){
									return '<u>'+value+'</u>';
								}else{
									return value;
								}
								
						}
							//sortable : true
						}, {
							field : 'orgCity',
							title : '销售城市',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								if(row.orgRegion!='标箱合计： '){
									return '<u>'+value+'</u>';
								}else{
									return value;
								}
						}
							//sortable : true
						},{
							field : 'orgRegionId',
							title : '销售大区',
							width : 80,
							align : 'center',
							hidden : true
							//sortable : true
						}, {
							field : 'orgProvinceId',
							title : '销售省份',
							width : 80,
							align : 'center',
							hidden : true
							//sortable : true
						}, {
							field : 'orgCityId',
							title : '销售城市',
							width : 80,
							align : 'center',
							hidden : true
							//sortable : true
						},{
							field : 'kunnr',
							title : '经销商编号',
							width : 80,
							align : 'center',
							hidden : false,
							formatter : function(value, row, rec) {
								if(row.orgRegion!='标箱合计： '){
									return '<u>'+value+'</u>';
								}else{
									return value;
								}
						}
						},{
							field : 'kunnrName',
							title : '经销商名称',
							width : 80,
							align : 'center'
							//able : true
						}, {
							field : 'skuId',
							title : 'SKU',
							width : 80,
							align : 'center',
							hidden : true
							//sortable : true
						} , {
							field : 'skuName',
							title : 'SKU名称',
							width : 80,
							align : 'center'
							//sortable : true
						} ]],
						columns : 
						         [[ {
									title : '（单位：箱）',
									colspan : 9
								},{
							        field : 'psdxCountStore',
								    title : '操作',
								    width : 120,
						    		align : 'center',
									//sortable : true,
									rowspan : 2,
									formatter : function(value, row, rec) {
										if(row.orgRegion!='标箱合计： '){
											return '<a href="javascript:searchDetail1(\''
											+ row.kunnr+'\',\''+row.kunnrName+'\',\''+row.orgCityId+
											'\')">明细</a>';
											//return '<a  href="http://exptest.zjxpp.com:7186/crmPlatform/stockReportAction!toStockWarning.jspa" tager="_parent,_blank">明细</a>';
										}
								}}],
								[{
									field : 'safeQuantityMax',
									title : '安全库存上限',
									width : 80,
									align : 'center',
									
									sortable : true
								},
								{
									field : 'safeQuantityMin',
									title : '安全库存下限',
									width : 80,
									align : 'center',
									
									sortable : true
								},
								{
								    field : 'quantity',
								    title : '实时库存',
									width : 80,
									align : 'center',
									
									sortable : true
								},{
								    field : 'createDate',
								    title : '最后提报时间',
									width : 80,
									align : 'center',
									hidden : true,
									sortable : true
								},
								{
									field : 'onWayNum',
									title : '已发未到',
									width : 80,
									align : 'center',
									
									sortable : true
								},
								{
								    field : 'planNotSend',
								    title : '已排未发',
						     		width : 80,
									align : 'center',
									
									sortable : true
								},
								{
									field : 'notPlan',
									title : '未排',
									width : 80,
									align : 'center',
									
									sortable : true
								},
								{
								    field : 'needPlan',
								    title : '需排产',
						     		width : 80,
									align : 'center',
									
									sortable : true
								},
								{
									field : 'needOrder',
									title : '需上单',
									width : 80,
									
									align : 'center'
									//sortable : true
								}]],
								onDblClickCell:function(index,field,value){
									var row=$('#datagrid').datagrid('getData').rows[index];
									if(field=="orgRegion"){
										$("#orgId").val(row.orgRegionId);
										$("#orgName").val(row.orgRegion);
										$('#kunnr').combogrid("setValue",'');
										$('#kunnr').combogrid('setText','');
										search();
									}else if(field=="orgProvince"){
										$("#orgId").val(row.orgProvinceId);
										$("#orgName").val(row.orgProvince);
										$('#kunnr').combogrid("setValue",'');
										$('#kunnr').combogrid('setText','');
										search();
									}else if(field=="orgCity"){
										$("#orgId").val(row.orgCityId);
										$("#orgName").val(row.orgCity);
										$('#kunnr').combogrid("setValue",'');
										$('#kunnr').combogrid('setText','');
										search();
									}else if(field=="kunnr" || field=="kunnrName"){
										$('#kunnr').combogrid('setValue',row.kunnr);
										$('#kunnr').combogrid('setText',row.kunnrName);
										search();
									}
									
									
								},/**
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
								},*/
						onLoadSuccess:function(data){
						
						},

						toolbar : [ /**"-" ,{
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
								exportForExcel();
							}
						},*/ "-", {
							text : '更新数据',
							iconCls : 'icon-reload',
							handler : function() {
								loadData();
							}},"-", {
							text : '下单',
							iconCls : 'icon-add',
							handler : function() {
								concludeOrder();
							}}]
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

function loadData(){
	$.messager.progress();
	$.ajax({
        type: "post",
        async: true,
        url : appUrl + '/stockReportAction!searchStockStateManagerList.jspa',
        data: {
        	orgId:defaultOrgId
        }, 
        success: function(data) {
        	$.messager.progress('close');
        	    //loadGrid();
        	$("#datagrid").datagrid('load');
        },
        error: function(data) {
        	$.messager.progress('close');
        	$.messager.alert('Tips', '加载数据失败', 'warning');
        }
    });
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combogrid("getValue");
	//queryParams.checkTime = $('#endDate').datebox("getValue");
	queryParams.skuId = $('#skuId').combogrid("getValue");
	queryParams.categoryId = $('#categoryId').combogrid("getValue");
	$("#datagrid").datagrid('load');
	//searchData(queryParams);
}

function clearValue(){
	$('#orgId').val(defaultOrgId);
	$('#orgName').val(defaultOrgName);
	$('#kunnr').combogrid("setValue",'');
	$('#skuId').combogrid("setValue",'');
	$('#categoryId').combogrid("setValue",'');
	$('#endDate').datebox("setValue",dateFormatter(new Date()));
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
// 创建窗口对象
function initMaintWindow(title, url, id, width, height, flag) {
	var WWidth = width;
	var WHeight = height;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						fit : flag,
						draggable : true
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
	initMaintAccount(false, '400', '400','组织选择', '/question/questionAction!orgTreePage.jspa',353,70);
}
function searchDetail1(kunnr,kunnrName,orgId) {
	//alert(kunnrName);
	var  kname =encodeURI(kunnrName);
	//alert("**********:"+kname);
	initMaintWindow('订单明细',
			 '/crmPlatform/stockReportAction!toSearchOrderFollowByKunnr.jspa?kunnr='
					+ kunnr+'&kunnrName='+kname+'&orgId='+orgId+'&flag=6', 'maintActivityPlanInfo', '1200', '550', false);
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