$(document).ready(function() {
	$('#fiscalYear').combobox({
		valueField : 'id',
		textField : 'text',
		data : [{
					id : '2013',
					text : '2013'
				}, {
					id : '2014',
					text : '2014'
				}, {
					id : '2015',
					text : '2015'
				}, {
					id : '2016',
					text : '2016'
				}],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto'
	});
	$('#cupType').combobox({
		valueField : 'id',
		textField : 'text',
		data : [{
					id : '',
					text : '所有'
				},{
					id : '普通杯',
					text : '普通杯'
				}, {
					id : '高级杯',
					text : '高级杯'
				}],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto'
	});
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '预算物料定价列表',
						url : appUrl + '/goalAction!searchMatterPriceBo.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'year',
									title : '财年',
									width : setColumnWidth(0.07),
									align : 'center'
								},{
									field : 'mvgr1',
									title : '品项or四级科目(SKU)',
									width : setColumnWidth(0.07),
									align : 'center'
								},
								{
									field : 'mvgTxt',
									title : '品项or四级科目(SKU)描述',
									width : setColumnWidth(0.20),
									align : 'center'
								},
								{
									field : 'mvgPrice',
									title : '目标量单价(元)',
									width : setColumnWidth(0.09),
									align : 'center',
//									formatter : function(value, row, rec) {
//										var s = value * 1;
//										return s.toFixed(6);
//									}
								},
								{
									field : 'salesPrice',
									title : '销售单价(元)',
									width : setColumnWidth(0.09),
									align : 'center',
								},
								{
									field : 'mvgRatio',
									title : '标箱系数',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'cupId',
									title : '杯种类ID',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'cupType',
									title : '杯种类',
									width : setColumnWidth(0.08),
									align : 'center'
								} ,{
									field : 'sku',	
									title : '协议目标打印组',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'skuText',
									title : '协议目标打印组描述',
									width : setColumnWidth(0.12),
									align : 'center'
								}] ],
						toolbar : [ "-", {
							text : '批量导入模板',
							iconCls : 'icon-excel',
							handler : function() {
								importMvgBo();
							}
						}, "-" ,
						{
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
								downloadMatterPriceBo();
							}
						}, "-" 
						]
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.fiscalYear = $("#fiscalYear").combobox('getValue');
	queryParams.mvgr1 = encodeURIComponent($("#mvgr1").val());
	queryParams.mvgTxt = encodeURIComponent($("#mvgTxt").val());
	queryParams.cupType = encodeURIComponent($("#cupType").combobox('getValue'));
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
function importMvgBo() {
	initMaint('批量导入','/goalAction!toImportMvgBo.jspa?', 450, 400);
}
function downloadMatterPriceBo() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/goal/goalAction!downloadMatterPriceBo.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/goal/goalAction!checkDownLoadOver.jspa?",
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
function initMaint(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maint")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}


function closeMain() {
$("#maint").window('close');
}
//重新加载
function reloadDatagrid(){
	$("#datagrid").datagrid('load');
}
//重置
function clearValue(){
	 $("#fiscalYear").combobox('setValue','');
	$("#mvgr1").val('');
	$("#mvgTxt").val('');
	$("#cupType").combobox('setValue','');
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};