var flag;
$(document).ready(function() {
	loadGrid();
	loadCustKunnr();
	loadCustSystem();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa',
		valueField : 'channelId',
		textField : 'channelName'
	});
});
/**
 * 加载所属经分销商
 */
function loadCustKunnr() {
	// 二阶客户
	$('#custParent').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
		idField : 'custId',
		textField : 'custName',
		multiple : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'custId',
			title : '客户编号',
			width : 120
		}, {
			field : 'custName',
			title : '客户名称',
			width : 200
		} ] ],
		toolbar : '#toolbarParent'
	});
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ { field : 'ck', checkbox : true } ],
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

function loadCustSystem() {
	// 所属系统
	$('#custSystem').combogrid(
			{
				panelHeight : 250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl
						+ '/crmdictAction!getDictJsonList.jspa?dictTypeValue='
						+ 'system@customer',
				idField : 'itemId',
				textField : 'itemName',
				columns : [ 
				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'itemId',
					title : '系统编号',
					width : 60
				}, {
					field : 'itemName',
					title : '系统名称',
					width : 200
				}, {
					field : 'itemValue',
					title : '描述',
					width : 200
				} ] ],
				toolbar : '#toolbarSys'
			});
}
/*******************************************************************************
 * 加载主体数据列表
 */
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/saleItemsAction!saleItemsSearch.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						rownumbers : true,
						multiple : false,
						columns : [ [
						        {
						        	field : 'ck',
						        	align : 'center',
						        	checkbox : true
						        },						        
								{
									field : 'channelName',
									title : '渠道',
									width : setColumnWidth(0.2),
									sortable : true
								}, {
									field : 'saleItemsKunnrName',
									title : '所属经销商',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								},{
									field : 'saleItemsSystemName',
									title : '所属系统',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								} ,{
									field : 'skuNames',
									title : '物料名',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								},
								{
									field : 'oper',
									title : '操作',
									width : 140,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.saleItemsId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								}
								] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								addItems();
							}
						},
						"-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								delItems();
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

/**
 * 模糊搜索
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
//	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	$("#datagrid").datagrid('load');
}





function setColumnWidth(percent) {
	return $(this).width() * percent;
}




/**
 * 所属经分销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

function searcherSys() {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

// 二阶客户下拉查询
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}

//创建窗口对象
function openMaintWindow(title, url, WWidth, WHeight) {
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

/**
 * 打开组织树
 */
function selectOrgTree() {
	openMaintWindow('选择组织', '/saleItemsAction!orgTreePage.jspa', 400, 460);
}
/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	if (flag == 1) {
		document.getElementById('orgId01').value = selectedId;
		document.getElementById('orgName01').value = selectedName;
	} else {
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}
	$('#custKunnr').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	
		$('#custParent').combogrid(
				{
					url : appUrl
							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
							+ $('#orgId').val()
				});
}
/**
 * 组织树选择完之后关闭
 */
function closeMaintEvent() {
	closeWindow();
}

/**
 * 业代选择之后关闭
 */
function closeOrgTree() {
	closeWindow();
}
/**
 * 打开组织树
 */
function selectOrgTree1() {
	flag = 1;
	openMaintWindow('选择组织', '/saleItemsAction!orgTreePage.jspa', 400, 460);
}

function addItems(){
	openMaintWindow('新增销售品项', '/saleItemsAction!addSaleItems.jspa', 400, 460);
}

function edit(id){
	openMaintWindow('修改销售品项', '/saleItemsAction!toUpdateSaleItems.jspa?saleItemsId=' + id, 400, 460);
}

function delItems(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  没有行被选中!');
		return;
	}
	$.messager.confirm('Confirm', '是否批量删除目标?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].saleItemsId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "saleItemsAction!deleteSaleItems.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
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
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
//	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#orgId').combobox('setValue', '');
}
/**
 * 页面返回提示信息
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		// search();
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid('reload');

	}
}
