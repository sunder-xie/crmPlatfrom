$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
//*************20170110增加三个层级编码及描述 删除物料组及描述 by cg.jiang************
//*************同步到sku：skuTypeId改为二级编码skutype改为二级系列*****************
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '物料列表',
						url : appUrl
								+ '/master!getMaterielJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : height,
						width : 'auto',
						columns : [ [{
							field : 'oper',
							title : '操作',
							width : setColumnWidth(0.06),
							align : 'center',
							formatter : function(value, row, index) {
								var id = row.matnr;
								var txt = row.maktx;
								var type = row.maktx02;
								var typeId=row.matnr02;
								return '<img style="cursor:pointer" onclick=sysToSku("'
												+ id
												+ '","'+txt+'","'+typeId+'","'+type+'") title="同步至销售品项" src='
												+ imgUrl
												+ '/images/actions/redo.png align="absMiddle"></img>';
							}
						},{
									field : 'matnr',
									title : '物料编号',
									align : 'center',
									width : setColumnWidth(0.15),
									sortable : true
								},{
									field : 'maktx',
									title : '物料短文本',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'meins',
									title : '物料单位',
									width : setColumnWidth(0.06),
									align : 'center'
								},
//								{
//									field : 'matkl',
//									title : '物料组',
//									width : setColumnWidth(0.06),
//									align : 'center'
//								},
//								{
//									field : 'wgbez',
//									title : '物料组描述',
//									width : setColumnWidth(0.2),
//									align : 'center'
//								},
//								{
//									field : 'mvgr1',
//									title : '预算口径代码',
//									width : setColumnWidth(0.08),
//									align : 'center'
//								},{
//									field : 'bezei',
//									title : '预算口径',
//									width : setColumnWidth(0.2),
//									align : 'center'
//								},
								{
									field : 'matnr03',
									title : '三级编码',
									align : 'center',
									width : setColumnWidth(0.08),
									sortable : true
								},{
									field : 'maktx03',
									title : '三级-包装',
									width : setColumnWidth(0.12),
									align : 'center'
								},{
									field : 'matnr02',
									title : '二级编码',
									align : 'center',
									width : setColumnWidth(0.08),
									sortable : true
								},{
									field : 'maktx02',
									title : '二级-系列',
									width : setColumnWidth(0.12),
									align : 'center'
								},{
									field : 'matnr01',
									title : '一级编码',
									align : 'center',
									width : setColumnWidth(0.08),
									sortable : true
								},{
									field : 'maktx01',
									title : '一级-品牌',
									width : setColumnWidth(0.12),
									align : 'center'
								}] ],
						toolbar : [ "-", {
							text : '同步',
							iconCls : 'icon-reload',
							handler : function() {
								synchMateriel();
							}
						} ]
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
	queryParams.matnr = $("#matnr").val();
	queryParams.maktx = encodeURIComponent($("#maktx").val());
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
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function synchMateriel() {
	$.messager.progress(); 
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/master!synchMateriel.jspa";
	form.target = "hideFrame";
	form.submit();
}
function sysToSku(id,txt,typeId,type){
	$('#skuId').val(id);
	$('#skuName').val(txt);
	$('#skuTypeId').val(typeId);
	$('#skuType').val(type);
	$.messager.progress(); 
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/master!synchToSku.jspa";
	form.target = "hideFrame";
	form.submit();
}