$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + 'K',
		valueField : 'channelId',
		textField : 'channelName'
	});
	$('#brands').combobox({
		valueField : 'value',
		textField : 'text',
		multiple : true,
		editable : false,
		data : [ {
			value : 'X',
			text : '香飘飘'
		}, {
			value : 'L',
			text : '兰芳园'
		}, {
			value : 'M',
			text : 'MECO'
		}],
		onLoadSuccess : function() {
			$('#brands').combobox('setValue', '');
		}
	});
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						frozenColumns : [[
							{
								field : 'price',
								title : '操作',
								width : setColumnWidth(0.18),
								align : 'center',
								formatter : function(value, row, index) {
									var kunnr = row.kunnr;
									var status = row.status;
									var license = " <a href='#' onclick=viewLicense('"+ kunnr+ "')>证照</a>";
							
									var scan = "|<a href='#' onclick=viewInfo('"
											+ kunnr
											+ "')>详情</a>";
									var edit = status == 1 ? "|<a href='#' onclick=editInfo('"
											+ kunnr
											+ "')>修改</a>"
											: "&nbsp;";
									var freeze = status == 1 ? "|<a href='#' onclick=freezeKunnr('"
											+ kunnr
											+ "') >关户</a>"
											: "&nbsp;";
									var update = status == 1 ? "|<a href='#' onclick=updateStaff(this) >编制变更</a>"
											: "&nbsp;";
									if (row.editing) {
										update= '|<a href="#" onclick="saverow(this)">保存</a>|<a href="#" onclick="cancelrow(this)">取消</a>';
									}
									return license + scan + edit + freeze+update;
								}
							   },
									{
										field : 'kunnr',
										title : 'SAP代码',
										width : setColumnWidth(0.08),
										align : 'center',
										styler : function(value, record, index) {
											return "background:pink";
										}
									},
									{
										field : 'name1',
										title : '经分销商名称',
										width : setColumnWidth(0.12),
										align : 'center',
										sortable:true
									}]],
						columns : [ [	
								{
									field : 'orgId',
									title : '组织ID',
									width : setColumnWidth(0.05),
									hidden:true,
									sortable:true
								},
								{
									field : 'orgName',
									title : '所属组织',
									width : setColumnWidth(0.08),
									sortable:true
								},
								{
									field : 'brands',
									title : '经营品牌',
									width : setColumnWidth(0.08),
									sortable:true
								},{
									field : 'staffNumber',
									title : '雇员编制数量',
									width : setColumnWidth(0.08),
									sortable:true,
									editor : {
										type : 'text',
										options : {
											valueField : 'staffNumber',
											textField : 'staffNumber',
											required : true
										}
									}
								},{
									field : 'staffNumberY',
									title : '雇员已占编制数量',
									width : setColumnWidth(0.08),
									sortable:true,
									hidden:true
								},
								{
									field : 'name3',
									title : '法人',
									width : setColumnWidth(0.05),
									sortable:true
								},
								{
									field : 'mobNumber',
									title : '联系电话',
									width : setColumnWidth(0.08),
									sortable:true
								},
								{
									field : 'bukrs',
									title : '公司代码',
									width : setColumnWidth(0.06),
									sortable:true
								},
								{
									field : 'channelName',
									title : '客户渠道',
									width : setColumnWidth(0.07),
									sortable:true
								},
								{
									field : 'telNumber',
									title : '公司电话',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'street1',
									title : '公司地址',
									width : setColumnWidth(0.18),
									align : 'center',
									sortable:true
								},
								{
									field : 'businessManager',
									title : '城市经理',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'businessCompetent',
									title : '客户经理',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'status',
									title : '状态',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1) {
											return '正常客戶';
										} else if (value == 2) {
											return '<font color="red">已关客戶</font>';
										} else if (value == 3) {
											return '<font color="red">关户中</font>';
										}
									},
									sortable:true
								} ] ],
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

						toolbar : [ "-", {
							text : '导出经销商数据',
							iconCls : 'icon-excel',
							handler : function() {
								excel();
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

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $("#kunnr").val();
	queryParams.name1 = encodeURIComponent($("#name1").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.businessManager = encodeURIComponent($("#businessManager")
			.val());
	queryParams.businessCompetent = encodeURIComponent($("#businessCompetent")
			.val());
	queryParams.brands = $("#brands").combobox("getValues").toString();
	queryParams.status = $("#status").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}

function viewLicense(kunnr) {
	openWindow("经销商证照查看",
			"/kunnrAction!kunnrViewLicense.jspa?kunnrId=" + kunnr, 800, 480);
}

function viewInfo(kunnr) {
	openWindow("经销商信息查看", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=view", 1120, 480);
}

function editInfo(kunnr) {
	openWindow("经销商信息修改", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=edit", 1120, 480);
}

function freezeKunnr(kunnr) {
	openWindow("经销商关户申请", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=freeze", 600, 450);
}

function closeKunnr(kunnr) {
	openWindow("经销商关户申请", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=close", 600, 320);
}
function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function updateStaff(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}
function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function saverow(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	var y = '';
	var z = '';
	var k='';
	for ( var i = 0; i < rows.length; i++) {
		k = rows[i].kunnr;
		y = rows[i].staffNumberY;
		z = rows[i].staffNumber;
	}
	if (z - (y-1) < 0) {
		search();
		return alert("编制数量不能少于已占用数量！");
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/kunnrAction!updateKunnrUserStaff.jspa?kunnrCode="+k+"&staffNumber="+z;
		form.target = "hideFrame";
		form.submit();
	}
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}
// 创建窗口对象
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
// 关闭窗口
function closeWindow() {
	$("#hiddenWin").window('close');
}

// excel导出
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!kunnrExport.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!',
	'warning');
}
	
	/**
	 * 打开组织树
	 */
	function selectOrgTree() {
		openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
	}
	/**
	 * 组织树选组织返回到输入框
	 * 
	 * @param selectedId
	 * @param selectedName
	 */
	function returnValue(selectedId, selectedName) {
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}
	/**
	 * 组织树选择完之后关闭
	 */
	function closeOrgTree() {
		closeWindow();
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
