//获取当前时间并赋值给相应的字段
var date = new Date();
var Month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
//jQuery加载主数据数据
$(document).ready(function() {
	$('#startDate').val(date.getFullYear()+'年'+Month+'月');
	$('#endDate').val(date.getFullYear()+'年'+Month+'月');
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	$('#eventType').combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			id : 'A',
			text : '协议目标量提报',
			value : 'A'	
		}, {
			id : 'B',
			text : '协议目标量调整',
			value : 'B'
		}],
		multiple : false,
		editable : false,
		panelHeight : 'auto'
	});
});
/**
 * 加载主数据
 */
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/kunnrBusinessContact/kunnrBusinessAction!getDealerAdjustmentJsonList.jspa',
						queryParams : {
							startDate : encodeURIComponent($("#startDate").val()),
							endDate : encodeURIComponent($("#endDate").val()),
							eventStatus : $('#eventStatus').combobox('getValue')
						},
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height * 0.95,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row,index) {
										var id = row.eventId;
										if (id != undefined) {
											var linkA = '<a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="toEvent('
												+id+')">'
												+id+'</a>';
											return linkA;
											
										}
									}
								},
								{
									field : 'eventType',
									title : '事务类型',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value,row,index) {
										if (value == 'A') {
											return '协议目标量提报';
										} else if (value == 'B') {
											return '协议目标量调整';
										} else {
											return '';
										}
									}
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.25),
									align : 'center'

								},
								{
									field : 'applyYear',
									title : '申请年',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'applyMonth',
									title : '申请月',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'applyUser',
									title : '申请人',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'eventStatus',
									title : '事务状态',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value,row,index) {
										if (value == 'D') {
											return '已完成';
										} else if (value == 'Y') {
											return '流程中';
										} else if (value == 'N') {
											return '未完成'
										} else if (value == 'S'){
											return '取消';
										}
									}
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value,row,index){
										var id=row.id;
										if(row.eventStatus=='N'){
											returnStr =
												'<img style="cursor:pointer"  title="提交未完成事务" onclick="dealerAdjustmentSub('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}else if(row.eventStatus=='Y'){
											returnStr =
												'<img style="cursor:pointer"  title="查看未完成事务" onclick="dealerAdjustmentView('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
										}else if(row.eventStatus=='D'){
											returnStr =
												'<img style="cursor:pointer"  title="查看已完成事务" onclick="dealerAdjustmentView('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
										}else{
											
											returnStr='';
										}
										return returnStr;
									}
								}] ],
						toolbar : ["-", {
							text : '新增调整',
							iconCls : 'icon-add',
							handler : function() {
								addNewDealerAdjustment();
							}
						}, "-", {
							text : '导出数据',
							iconCls : 'icon-excel',
							handler : function() {
								exportData();
							}
						} ],
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
						onLoadSuccess : function(data) {
							$(".datagrid-header-check")[0].disabled = true;
							$(".datagrid-header-check input").unbind('click');
							selectedFile(data.rows);
						},
						onClickRow : function(rowIndex, rowData) {
							$('#datagrid').datagrid('unselectRow', rowIndex);
						}
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
/**
 * http://exp.zjxpp.com:8186/basisPlatform/wfe/eventAction!toProcessEvent.jspa?event_id=
 * http://exptest.zjxpp.com:7186/...
 * 注意，提交的正式机时候将这个域名修改过来
 */
function toEvent(id) {
	initMaintWindow('事务信息',
			'http://exptest.zjxpp.com:7186/basisPlatform/wfe/eventAction!toProcessEvent.jspa?event_id='
			+ id, 'maintWindow', '700', '400', true);
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function selectedFile(rows) {
	for ( var j = 0; j < rows.length; j++) {
		if ((rows[j]['documentNumber'] != undefined && rows[j]['documentNumber'] != '')) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
}
/**
 * 根据条件查询
 * queryParams.startDate = encodeURIComponent($("#startDate").val());
 * queryParams.endDate = encodeURIComponent($("#endDate").val());
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = $("#eventId").val();
	queryParams.applyUser = encodeURIComponent($("#applyUser").val());
	/*queryParams.applyYear = $("#applyYear").val();
	queryParams.applyMonth = $("#applyMonth").val();*/
	queryParams.startDate = encodeURIComponent($("#startDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	queryParams.eventStatus = $('#eventStatus').combobox('getValue');
	queryParams.eventType = $('#eventType').combobox('getValue');
	$("#datagrid").datagrid('load');
}
/**
 * 新增调整
 */
function addNewDealerAdjustment(){
	initMaintWindow('协议目标量批量调整', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toAddDealerAdjustment.jspa?orgId='+$('#orgId').val(),
			'maintWindow', '1000', '400', true);
}
/**
 * 提交未完成调整
 * @param id
 */
function dealerAdjustmentSub(id){
	initMaintWindow('协议目标量批量调整', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toDealerAdjustmentSub.jspa?id='+id,
			'maintWindow', '1000', '400', true);
}
/**
 * 查看已完成或者是流程中的调整
 * @param id
 */
function dealerAdjustmentView(id){
	initMaintWindow('协议目标量批量调整', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toDealerAdjustmentView.jspa?id='+id,
			'maintWindow', '1000', '400', true);
}
/**
 * 导出数据
 */
function exportData(){
	$.messager.confirm('Confirm','是否导出数据?',function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/kunnrBusinessContact/kunnrBusinessAction!exportDealerAdjustData.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

/**
 * 初始化新的窗口
 * @param title 主题
 * @param url 链接
 * @param id id
 * @param width 宽度
 * @param height 高度
 * @param flag 操作
 */
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
/**
 * 提示信息处理
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning', function() {
			search();
		});
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
function windowClose(){
	$("#maintWindow").window('close');
}
/**
 * 时间格式化处理
 * @param utcCurrTime
 * @returns {String}
 */
function utcToDate(utcCurrTime) {
	if (utcCurrTime.indexOf(" ") < 0) {
		return utcCurrTime;
	} else {
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
}