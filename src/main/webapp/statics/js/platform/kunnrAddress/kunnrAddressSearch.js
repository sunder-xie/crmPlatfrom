$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/kunnrAddressAction!kunnrAddressSearch.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'kunnrSd',
									title : '送达方SAP代码',
									width : setColumnWidth(0.08),
									align : 'center'

								},
								{
									field : 'kunnr',
									title : '经分销商SAP代码',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'kunnrName',
									title : '经分销商名称',
									width : setColumnWidth(0.12),
									align : 'center'
								},
								{
									field : 'xzAddress',
									title : '送达方行政区划地址',
									width : setColumnWidth(0.15),
									align : 'center'
								},
								{
									field : 'address',
									title : '详细地址',
									width : setColumnWidth(0.15),
									align : 'center'
								},
								{
									field : 'name',
									title : '收货人',
									width : setColumnWidth(0.08)
								},
								{
									field : 'telephone',
									title : '电话',
									width : setColumnWidth(0.1)
								},
								{
									field : 'mobile',
									title : '手机',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'maximum',
									title : '最大通行车型',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden : true
								},
								{
									field : 'maximumTxt',
									title : '最大通行车型',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'state',
									title : '状态',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value, row, index) {
										var s = row.state;
										if ('U' == s) {
											return '正常';
										} else if ('N' == s) {
											return '已删除';
										}

									}
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										var kunnrSd = row.kunnrSd;
                                        var s=row.state;
                                        if('U'==s){
										return "<img style='cursor:pointer' onclick=editInfo('"
												+ kunnrSd
												+ "') title='资料修改' src="
												+ imgUrl
												+ "/images/actions/action_edit.png align='absMiddle'></img>";
                                        }}
								} ] ],
						toolbar : "#toolbar"
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
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrSd = $("#kunnrSd").val();
	queryParams.kunnrId = $("#kunnr").val();
	queryParams.kunnrName = encodeURIComponent($("#kunnrName").val());
	$("#datagrid").datagrid('reload');
}

function createKunnrAddress() {
	initMaintKunnrAddress('送达方创建',
			'/kunnrAddressAction!createKunnrAddressPrepare.jspa', 800, 500);
}

function deleteKunnrAddress() {
	$.messager.confirm('Confirm', '是否批量删除送达方?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			$.messager.progress();
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].kunnrSd);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "kunnrAddressAction!deleteKunnrAddress.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function initMaintKunnrAddress(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintKunnrAddress")
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

function closeMaintKunnrAddress() {
	$("#MaintKunnrAddress").window('close');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	$.messager.progress('close');
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

function editInfo(kunnr) {
	initMaintKunnrAddress("送达方信息修改",
			"/kunnrAddressAction!updateKunnrAddressPre.jspa?kunnrSd=" + kunnr,
			800, 400);
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
