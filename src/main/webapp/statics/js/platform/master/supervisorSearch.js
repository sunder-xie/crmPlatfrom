var flag;
var orgflag=1;
$(document).ready(function() {
	
	loadGrid();
	loadCustKunnr();
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
		url : appUrl + '/master!kunnrSearchFromMaster.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ /*
					 * [ { field : 'ck', checkbox : true } ],
					 */[ {
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

/*******************************************************************************
 * 加载主体数据列表
 */

var editorline = -1;
function onClickCell(index, field) {

	//	var row =  $("#datagrid").datagrid('getSelected');
	//alert(JSON.stringify(row));
	//alert(row.scale);

	if (editorline == -1) {
		$('#datagrid').datagrid('beginEdit', index);
		editorline = index;
	} else {
		$('#datagrid').datagrid('endEdit', editorline);
		$('#datagrid').datagrid('beginEdit', index);
		editorline = index;
	}

}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						 url : appUrl + '/master!getSupervisorItems.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						onClickCell : onClickCell,
						striped : true,
						queryParams : {
							orgId : $("#orgId").val(),
							bhxjFlag : $("#bhxjFlag").val()
						},
						frozenColumns : [ [
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.18),
									hidden : true,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = " <a href='#' onclick=viewLicense('"
												+ kunnr + "')>证照</a>";

										var scan = "|<a href='#' onclick=viewInfo('"
												+ kunnr + "')>详情</a>";
										var edit = status == 1 ? "|<a href='#' onclick=editInfo('"
												+ kunnr + "')>修改业务联系</a>"
												: "&nbsp;";

										return license + scan + edit;
									}
								}, {
									field : 'checkId',
									title : '检查项id',
									width : setColumnWidth(0.05),
									hidden : true,
									sortable : true
								}, {
									field : 'custId',
									title : '门店ID',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'custName',
									title : '门店',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'orgName',
									title : '门店组织',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'channelName',
									title : '渠道',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'createName',
									title : '创建人名称',
									width : setColumnWidth(0.08),
									sortable : true
								}, {
									field : 'createTime',
									title : '创建时间',
									width : setColumnWidth(0.05),
									sortable : true
								} ] ],
						columns : [ [ {
							title : '单杯',
							colspan : 45
						}, {
							title : '3连杯',
							colspan : 36
						}, {
							title : '家庭分享装',
							colspan : 12
						}, {
							title : '礼盒',
							colspan : 6
						}, {
							title : '组合装',
							colspan : 6
						} , {
							title : '家庭礼盒组合装',
							colspan : 1
						} ], [
{field:'checkId1',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue1',title:'椰果原味',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice1',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice1',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId2',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue2',title:'椰果香芋',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice2',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice2',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId3',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue3',title:'椰果草莓',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice3',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice3',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId4',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue4',title:'椰果麦香',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice4',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice4',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId5',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue5',title:'椰果咖啡',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice5',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice5',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId6',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue6',title:'椰果巧克力',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice6',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice6',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId7',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue7',title:'桂圆红枣',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice7',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice7',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId8',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue8',title:'红豆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice8',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice8',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId9',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue9',title:'芝士燕麦',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice9',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice9',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId10',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue10',title:'黑米椰浆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice10',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice10',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId11',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue11',title:'焦糖仙草',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice11',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice11',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId12',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue12',title:'原汁红豆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice12',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice12',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId13',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue13',title:'芒果布丁',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice13',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice13',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId14',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue14',title:'蓝莓',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice14',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice14',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId15',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue15',title:'雪糕椰浆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice15',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice15',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId16',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue16',title:'原味',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice16',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice16',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId17',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue17',title:'香芋',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice17',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice17',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId18',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue18',title:'草莓',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice18',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice18',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId19',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue19',title:'麦香',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice19',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice19',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId20',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue20',title:'桂圆红枣',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice20',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice20',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId21',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue21',title:'红豆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice21',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice21',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId22',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue22',title:'芝士燕麦',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice22',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice22',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId23',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue23',title:'黑米椰浆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice23',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice23',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId24',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue24',title:'焦糖仙草',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice24',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice24',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId25',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue25',title:'芒果布丁',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice25',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice25',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId26',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue26',title:'蓝莓',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice26',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice26',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId27',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue27',title:'雪糕椰浆',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice27',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice27',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId28',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue28',title:'椰果16杯装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice28',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice28',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId29',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue29',title:'椰果12杯装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice29',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice29',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId30',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue30',title:'美味16杯装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice30',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice30',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId31',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue31',title:'美味12杯装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice31',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice31',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId32',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue32',title:'椰果12杯装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice32',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice32',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId33',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue33',title:'新品礼盒装',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice33',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice33',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId34',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue34',title:'椰果经典',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice34',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice34',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId35',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue35',title:'美味新品',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice35',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice35',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId36',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue36',title:'家庭礼盒',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice36',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice36',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true}

						] ],
						toolbar : [ "-", {
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
								excel();
							}
						}, "-", {
							text : '模板下载',
							iconCls : 'icon-excel',
							handler : function() {
								exportMould();
							}
						}, "-", {
							text : '批量导入',
							iconCls : 'icon-add',
							handler : function() {
								importCheckItem();
							}
						}, "-" , {
							text : '保存修改',
							iconCls : 'icon-edit',
							handler : function() {
								saveChagCheckItem();
							}
						}, "-", {
							text : '导入铺货请填写Y 或 N'
						
						}, "-"  ]
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
 *  新增 
 */

function  addLineCheckItem(){
	initMaintAccount(false,400,200,'新增督导考核数据', '/master!!toAddSupervisorItems.jspa');
}

/**
 * 保存修改
 */
function saveChagCheckItem(){

	$('#datagrid').datagrid('endEdit', editorline);
	var rows = $('#datagrid').datagrid('getChanges');
	
	
	for(var i=0;i<rows.length;i++){
		rows[i].channelName="";
		rows[i].custName="";
		rows[i].orgName="";
		
	}
	
	if (rows.length>0){
		
		$.ajax({
			   type: "POST",
			   url: appUrl + '/master!saveChagCheckItem.jspa',
			   data: {"supervisorLineCheckItemListjson":$.toJSON(rows)},
			   success: function(data){
				   $("#datagrid").datagrid('load');
			   }
			});
		
	}
	else 
		$.messager.alert('Tips', '没有更改的数据!', 'warning');
	
	//alert(rows.length+' rows are changed!');
}



//excel导出终端客户数据
function excel() {
	$.messager.confirm('Confirm', '是否确定导出?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/master!itemsExport.jspa';
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
		}
	});
}

/**
 *  批量导入
 */

function importCheckItem() {

	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('导入目标', html);
}

var mask_;
/* 打开导入项目Excel对话框 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '批量导入',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	}
	$('#excelDialog').dialog(
			{
				modal : true,
				resizable : false,
				dragable : false,
				closable : false,
				open : function() {
					$('#excelDialog').css('padding', '0.8em');
					$('#excelDialog .ui-accordion-content').css('padding',
							'0.4em').height($('#excelDialog').height() - 100);
				},
				buttons : [ {
					text : '确定',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						if (/^.+\.(csv|CSV)$/.test(file)) {
							$.messager.progress();
							var action = '';
							action = appUrl + "/master!ImportItems.jspa";
							var form = document.getElementById('fileForm');

							form.action = action;
							// "eventId="+processInstanceId;
							form.target = "hideFrame";
							form.submit();
						} else {
							$.messager.alert("提示", "请导入csv文件");
						}
						$.messager.progress('close');

					}
				}, {
					text : '取消',
					handler : function() {
						window.location.href = window.location.href;
						$('#excelDialog').dialog('close');

					}
				} ],

				width : document.documentElement.clientWidth * 0.28,
				height : document.documentElement.clientHeight * 0.50
			});
}

/**
 * 模糊搜索
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.custNumber = $("#custNumber").val();
	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.stationUserId = $("#stationUserId").val();
	queryParams.contactName = encodeURIComponent($("#contactName").val());
	queryParams.custState = $("#custState").val();// $("#custState").combobox("getValue");
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	queryParams.custParent = $("#custParent").combogrid("getValue");
	queryParams.custType = $("#custType").val();
	queryParams.createOrgId = $("#createOrgId").val();
	queryParams.createDateStart = $("#createDateStart").datebox("getValue");
	queryParams.createDateEnd = $("#createDateEnd").datebox("getValue");
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function viewInfo(id) {
	openWindow("终端门店信息查看", "/customerAction!viewCustomer.jspa?custId=" + id,
			1000, 480);
}

function editInfo(id, state, type) {
	openWindow("客户信息修改", "/customerAction!toUpdateCustomer.jspa?custId=" + id
			+ '&type=' + type, 1100, 480);
}
/**
 * 关闭终端门店
 * 
 * @param id
 */
function freezeKunnr(id, state) {
	var ids = [];
	ids.push(id);
	$("#ids").val(ids);
	if (state == "已关户") {
		$.messager.alert('Tips', '客户状态为已关闭状态，不需重复操作！', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '是否确定关闭终端门店?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/customerAction!closeCustomer.jspa?custState1=' + '4';
			form.target = "hideFrame";
			form.submit();

		}
	});
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
// 二阶客户下拉查询
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
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
/**
 * 打开组织树
 */
function selectOrgTree() {
	orgflag=1;
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * 打开组织树
 */
function selectOrgTree0() {
	orgflag=0;
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
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
		if (orgflag==1){
		    document.getElementById('orgId').value = selectedId;
		    document.getElementById('orgName').value = selectedName;
		}else{
		    document.getElementById('createOrgId').value = selectedId;
		    document.getElementById('createOrgName').value = selectedName;
		}
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
 * 选择业代
 */
function selectOrgTree4Station() {
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	openWindow('选择业务代表', '/batChangeAction!selectOrgTree4Station.jspa?node='
			+ node + '&orgName=' + orgName, 520, 460);
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
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}

/*
 * 打开人员选择树
 */
function selectUser() {
	openWindow('选择业务人员', '/batChangeAction!selectOrgTreeUser.jspa', 550, 350);
}
/*
 * 城市经理、主管选择返回值
 */
function returnUser(userId, userName) {
	document.getElementById('stationUserId01').value = userId;
	document.getElementById('stationUserName01').value = userName;
}


function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table><tr>'
			+ '<td class="head" noWrap>类型选择</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="Z" checked>--终端门店--</option>'
			+ '<option value="T" selected>--二阶客户--</option>'
			+ '</select></td></tr>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportCustomerMouldCsv();> 1、下载模版</a></td></tr>'
			/*
			 * + '<tr><td></td><td><a class="easyui-linkbutton"
			 * style="color:blue" data-options="plain:true,iconCls:"icon-excel""
			 * onclick=javascript:exportCustomerHelps();>2、下载客户导入的辅助信息数据</a></td></tr>' + '<tr><td class="head" noWrap>所属组织</td><td>' + '<input
			 * class="easyui-validatebox" id="orgName01" name="orgName01"
			 * readonly/>' + '<button onclick="javascript:selectOrgTree1()">选择</button>' + '<input
			 * type="hidden" id="orgId01" name="orgId01"/></td></tr>' + '<tr><td class="head" noWrap>我司业务负责人</td><td>' + '<input
			 * class="easyui-validatebox" id="stationUserName01"
			 * name="stationUserName01" readonly/>' + '<button
			 * onclick="javascript:selectUser()">选择</button>' + '<input
			 * type="hidden" id="stationUserId01" name="stationUserId01"/></td></tr>'
			 */
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importCustomerCsv('批量导入', html);
}
// csv下载终端门店导入模板
function exportCustomerMouldCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form = window.document.forms[0];
				//document.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportMouldCsvWithCons.jspa';
			form.submit();
		}
	});

}
// excel下载终端门店导入辅助信息
function exportCustomerHelps() {
	$.messager.confirm('Confirm', '是否确定关下载辅助信息?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!exportCustomerHelps.jspa';
			form.submit();
		}
	});
}
// 批量导入终端门店信息
function importCustomerCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("提示", "请选择所属组织");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("提示",
										 * "请选择业务负责人"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/customerAction!importCustomerCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/*
 * function importCustomerCsv(){ $.messager.confirm('Confirm', '是否确定批量导入终端门店?',
 * function(r) { if (r) { var form = window.document.forms[0]; form.action =
 * appUrl + '/customerAction!importCustomerCsv.jspa'; form.submit(); } }); }
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
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#custState').val('1');
	$('#createOrgId').val('');
	$('#createDateStart').datebox('setValue','');
	$('#createDateEnd').datebox('setValue','');
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
/**
 *  模板导出
 */
function exportMould() {
	$.messager.alert('Tips', "需要考核的口味对应下面，填Y或N，如椰果原味需要考核，填写值为Y. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithCons.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  导出数据excel
 */

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrBusinessAction!exportForExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}