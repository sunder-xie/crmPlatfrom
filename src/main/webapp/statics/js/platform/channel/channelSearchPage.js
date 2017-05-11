$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : 390,
						width : $(this).width(),
						striped : true,
						url : appUrl
								+ '/channelTreeAction!getChannelList.jspa?ran='
								+ Math.random(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						fitColumns : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'dictTypeId',
									title : '渠道ID',
									field : 'channelId',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									title : '渠道名称',
									field : 'channelName',
									width : $(this).width() * 0.14,
									align : 'center'
								},
								{
									title : '父级渠道ID',
									field : 'channelParentId',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '父级渠道',
									field : 'parentChannelName',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '备注',
									field : 'remark',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '最后更新时间',
									field : 'lastModify',
									width : $(this).width() * 0.14,
									align : 'center',
									formatter : function(v) {
										if (v)
											return utcToDate(v.replace(
													/\/Date\((\d+)\+\d+\)\//gi,
													"new Date($1)"));
									}
								},
								{
									field : 'edit',
									title : '修改',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.channelId;
										var state = row.channelState;
										if (state == 'Y') {
											return '<img style="cursor:pointer" onclick="edit('
													+ id
													+ ')" title="修改信息" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>';
										}
									}
								},
								{
									title : '状态',
									field : 'channelState',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var state = row.channelState;
										if (state == 'Y') {
											return "<font color='green'>正常</font>";
										} else {
											return "<font color='red'>禁用</font>";
										}
									}
								},
								{
									field : 'oper',
									title : '渠道状态操作',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.channelId;
										var state = row.channelState;
										if (state == 'Y') {
											return '<img style="cursor:pointer" onclick=forbidden("'
													+ id
													+ '") title="禁用渠道" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>';
										} else {
											return '<img style="cursor:pointer" onclick=startup("'
													+ id
													+ '") title="启用渠道" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						} ]
					});
	// 分页控件
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	search();
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.channelName = encodeURIComponent($("#channelName").val());
	queryParams.channelState = encodeURIComponent($("#channelState").val());
	queryParams.channelParentId = encodeURIComponent($("#channelParentId")
			.val());
	$("#con_list").datagrid('load');
}

// 创建窗口对象
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

function selectChannelListTree() {
	initMaintWindow('选择渠道', '/channelTreeAction!channelTreePage.jspa', 450, 320);
}

/**
 * 渠道创建
 */
function add() {
	initMaintWindow('渠道创建', '/channelTreeAction!toCreateChannel.jspa', 600, 400);
}

/**
 * 修改渠道信息
 */
function edit(id) {
	initMaintWindow('渠道修改',
			'/channelTreeAction!toUpdateChannelInfo.jspa?channelId=' + id, 600,
			400);

}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('提示', successResult, 'info');
		search();
	}
}
/**
 * 组织树的返回值接受
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('channelParentId').value = selectedId;
	document.getElementById('channelParentName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}

function forbidden(id) {
	$.messager.confirm("提示", "确认要禁用该渠道么？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/channelTreeAction!forbidden.jspa?channelId=' + id;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}

function startup(id) {
	$.messager.confirm("提示", "确认要启用该渠道么？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/channelTreeAction!startup.jspa?channelId='
					+ id;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}

/**
 * 回车键默认时间绑定
 * 
 * @param {}
 *            e
 * @return {Boolean}
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
$(window).resize(function() {
	$('#datagrid').datagrid('resize', {
		width : $(".f_main").width()
	});
});
