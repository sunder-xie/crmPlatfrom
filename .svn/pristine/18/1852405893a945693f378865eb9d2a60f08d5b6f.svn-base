$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '预算物料定价列表',
						url : appUrl + '/goalAction!searchMatterPrice.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'matter',
									title : '预算口径编码',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'bezei',
									title : '预算口径描述',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'price',
									title : '单价(元)',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.price * 1;
										return s.toFixed(6);
									}
								},
								{
									field : 'startDate',
									title : '有效起始日期',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'endDate',
									title : '有效截止日期',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'remark',
									title : '备注',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'oper',
									title : '操作',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										return '<img style="cursor:pointer" onclick="updatePrice('
												+ row.matPriceId
												+ ')" title="修改" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';

									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								addPrice();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.matter = encodeURIComponent($("#matter").val());
	queryParams.bezei = encodeURIComponent($("#bezei").val());
	queryParams.remark = encodeURIComponent($("#remark").val());
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
/**
 * 修改
 */
function updatePrice(id) {
	initMaint('物料定价修改', '/goalAction!toCreateOrUpdatePrice.jspa?type='+'update'+'&id='+id, 550, 400);
}
/**
 * 新增
 */
function addPrice() {
	initMaint('物料定价创建', '/goalAction!toCreateOrUpdatePrice.jspa?type='+'insert', 550, 400);
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};