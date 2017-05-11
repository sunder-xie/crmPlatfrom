var flag;
var flagchag=0;
var editIndex = undefined;
var fields = [];
$(document).ready(function() {
	loadGrid();
	//	loadCustKunnr();
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
//function loadCustKunnr() {
//	// 二阶客户
//	$('#custParent').combogrid({
//		panelHeight : 250,
//		panelWidth : 380,
//		pagination : true,
//		method : 'post',
//		singleSelect : true,
//		url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
//		idField : 'custId',
//		textField : 'custName',
//		multiple : true,
//		columns : [ [ {
//			field : 'ck',
//			checkbox : true
//		}, {
//			field : 'custId',
//			title : '客户编号',
//			width : 120
//		}, {
//			field : 'custName',
//			title : '客户名称',
//			width : 200
//		} ] ],
//		toolbar : '#toolbarParent'
//	});
//}
/*******************************************************************************
 * 加载主体数据列表
 */
function loadGrid() {
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '查询结果',
				url : appUrl + '/customerAction!customerSearch.jspa?custKunnr='
						+ $("#custKunnr").val(),
				loadMsg : '数据远程载入中,请等待...',
				singleSelect : true,
				pagination : true,
				nowrap : true,
				height : height,
				striped : true,
				frozenColumns : [ [
				//											{
				//												field : 'price',
				//												title : '操作',
				//												width : setColumnWidth(0.08),
				//												align : 'center',
				//												formatter : function(value, row, index) {
				//													var id = row.custNumber;
				//													var state = row.custState;
				//													var type = row.custType;
				//													if (type == '') {
				//														type = 'Z';
				//													}
				//													var edit = "  <a href='#' onclick=editInfo('"
				//															+ id
				//															+ "','"
				//															+ state
				//															+ "','"
				//															+ type + "') >修改</a>";
				//													var freeze = "  <a href='#' onclick=freezeKunnr('"
				//															+ id
				//															+ "','"
				//															+ state
				//															+ "') >关户</a>";
				//													if (state == '4') {
				//														return '';
				//													} else {
				//														return edit;
				//													}
				//												}
				//											}, 
				{
					field : 'custNumber',
					title : '客户编号',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'custName',
					title : '客户名称',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				} ] ],
				columns : [ [ {
					field : 'orgName',
					title : '所属组织',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'channelName',
					title : '渠道',
					width : setColumnWidth(0.06),
					sortable : true
				}, {
					field : 'stationUserId',
					title : '岗位ID',
					width : setColumnWidth(0.06),
					hidden : false,
					sortable : true
				}, {
					field : 'stationName',
					title : '岗位名称',
					width : setColumnWidth(0.06),
					align : 'center',
					hidden : true
				}, {
					field : 'empName',
					title : '业代名称',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'kunnrUser',
					title : '经销商负责人',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'op',
					title : '经销商负责人操作',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, index) {
						var html="";
						if (flagchag==1){
							var save='<a href="javascript:saveChagKunnrUser()">保存</a>';
							html=save;
						}
						else{
							var clear='<a href="javascript:clear('+index+','+row.custNumber+')">清空</a>';
							var chg='<img style="cursor:pointer" onclick="chagKunnrUser()" title="修改" src='
								+ imgUrl
								+ '/images/actions/action_edit.png align="absMiddle"></img>';
							var ori ="<span onclick='chagKunnrUser()' style='color:#E6941A'>修改</span>&nbsp;&nbsp;"+"<span style='color:#E6941A' onclick='saveChagKunnrUser()'>保存</span>";
							html=clear+"&nbsp;&nbsp;"+chg;
						}
						return html;
						
						/*
						var id = row.bnumber_id;
						var number ="'"+row.budget_number +"'";
						// var subjectId = row.subjectId;
						// var costCentId =
						// "'"+row.costCentId+"'";
						return '<img style="cursor:pointer" onclick="queryBNumberDetail('
								+ number
								+ ')" title="账户详单" src='
								+ imgUrl
								+ '/images/actions/action_edit.png align="absMiddle"></img>   ';
						*/
					}
				},{
					field : 'contactName',
					title : '联系人',
					width : setColumnWidth(0.06),
					sortable : true
				}, {
					field : 'contactMobile',
					title : '联系人号码',
					width : setColumnWidth(0.06),
					sortable : true
				}, {
					field : 'custProvince',
					title : '行政区划',
					width : setColumnWidth(0.2),
					align : 'left',
					sortable : true
				}, {
					field : 'custTown',
					title : '村/社区',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'custStreet',
					title : '路/街/巷/里/弄',
					width : setColumnWidth(0.06),
					align : 'left',
					sortable : true
				}, {
					field : 'custHouserNumber',
					title : '门牌号',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'custAddress',
					title : '参考位置',
					width : setColumnWidth(0.1),
					align : 'center',
					hidden : false
				}, {
					field : 'custType',
					title : '类型',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, index) {
						var state = row.custType;
						var stateName = '';
						if (state == 'T') {
							stateName = '二阶客户';
						}
						if (state == 'Z') {
							stateName = '终端门店';
						}
						return stateName;
					},
					sortable : true
				}, {
					field : 'custState',
					title : '客户状态',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, index) {
						var state = row.custState;
						var stateName = '';
						if (state == '0') {
							stateName = '新客户';
						}
						if (state == '1') {
							stateName = '正常';
						}
						if (state == '2') {
							stateName = '间歇';
						}
						if (state == '3') {
							stateName = '冻结';
						}
						if (state == '4') {
							stateName = '已关户';
						}
						return stateName;
					},
					sortable : true
				}, {
					field : 'custReceive',
					title : '配送方式',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, index) {
						var s = row.custReceive;
						var name = '';
						if (s == '0') {
							name = '经销商配送';
						}
						if (s == '1') {
							name = '二级配送';
						}
						if (s == '2') {
							name = '总仓配送';
						}
						return name;
					},
					sortable : true
				}, {
					field : 'visitFreq',
					title : '拜访频次',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, index) {
						var s = row.visitFreq;
						var name = '';
						if (s == '1') {
							name = '一周一次';
						}
						if (s == '2') {
							name = '一周两次';
						}
						if (s == '3') {
							name = '一周三次';
						}
						if (s == '4') {
							name = '两周一次';
						}
						return name;
					},
					sortable : true
				}, {
					field : 'visitDays',
					title : '拜访日期',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'corporateDeputy',
					title : '法人代表',
					width : setColumnWidth(0.06),
					align : 'center',
					hidden : true
				}, {
					field : 'createDate',
					title : '创建日期',
					width : setColumnWidth(0.06),
					align : 'center',
					hidden : true
				}, {
					field : 'lastModify',
					title : '修改日期',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'modifyUser',
					title : '操作人',
					width : setColumnWidth(0.06),
					align : 'center',
					sortable : true
				}, {
					field : 'custKunnr',
					title : '上游经销商ID(可能二开)',
					width : setColumnWidth(0.2),
					align : 'left',
					hidden : true,
					sortable : true
				}, {
					field : 'custKunnrName',
					title : '上游经销商',
					width : setColumnWidth(0.2),
					align : 'left',
					hidden : false,
					sortable : true
				} ] ],
				toolbar : [ "-", {
					text : '导出客户数据',
					iconCls : 'icon-excel',
					handler : function() {
						excel();
					}
				},"-",
				{
					text : '模版下载',
					iconCls : 'icon-excel',
					handler : function() {
						exportMould();
					}
				},
				"-",
				{
					text : '批量导入修改',
					iconCls : 'icon-add',
					handler : function() {
						importCustDataToChg();
					}
				},
				"-"]

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

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/customerAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear(index,custNumber){
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {
			kunnrUser : ""
		}
	});
	
	fields=[];
	fields.push({
		"custNumber": custNumber,
		"kunnrUser": ""
	});
	
	saveChagKunnrUser();
}

function importCustDataToChg(){
	
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
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/customerAction!importCustDataToChg.jspa";
											var form = document
													.getElementById('fileForm');

											form.action = action;
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								},
								{
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

function saveChagKunnrUser() {
	if (fields.length>0){
		$('#jsonRows').val($.toJSON(fields));
		
		$.ajax({
			type : 'post',
			//plication/x-www-form-urlencoded; charset=utf-8", 
			url : appUrl
					+ '/customerAction!saveChagKunnrUser.jspa',
		    data: { jsonRows: $.toJSON(fields) },
					
		//	data:"jsonRows="+encodeURI("撒点粉"),
			success : function(data) {
				  flagchag=0;
				  $("#datagrid").datagrid("reload")
				 $.messager.alert("操作提示", "经销商负责人修改保存成功！","info");
				 fields = [];
			}
		});
		
		
		
	}else{
		$.messager.alert('Error', '数据没更改，不需要保存！', 'error');
		return;
	}

}

function chagKunnrUserData() {
	var row = $('#datagrid').datagrid('getSelected');
	if (row) {
		var editIndex = $('#datagrid').datagrid('getRowIndex', row);
		$('#datagrid').datagrid('beginEdit', editIndex);

		var custNumber=row.custNumber;
		// updateRow autolly endEdit
		
		fields.push({
			"custNumber": custNumber,
			"kunnrUser": encodeURIComponent($("#kunnrUser").val())
		});
		
		flagchag=1;
		
		$('#datagrid').datagrid('updateRow', {
			index : editIndex,
			row : {
				kunnrUser : $("#kunnrUser").val(),
			
			}
		});
		
	
	}
	
	
}


/**
 * zpf 修改经销商负责人
 */

function chagKunnrUser() {
	var row = $('#datagrid').datagrid('getSelected');
	if (row) {
		var editIndex = $('#datagrid').datagrid('getRowIndex', row);
		$('#datagrid').datagrid('beginEdit', editIndex);
		var custKunnr = row.custKunnr; // 门店可能双开
		selectUpKunnrUser(custKunnr);

		/*
		var row = $('#datagrid').datagrid('getSelected');
		if (row){
			var editIndex=$('#datagrid').datagrid('getRowIndex',row);
			$('#datagrid').datagrid('beginEdit',editIndex);
			var custKunnr = row.custKunnr; // 门店可能双开
		
			alert("choosed");
			// updateRow autolly endEdit
			var kunnrUser = $("#kunnrUser").val();
			//alert(kunnrUser);
			alert("change");
			$('#datagrid').datagrid('updateRow', {
				index: editIndex,
				row: {
					custName: 'new name'
				}
			});
		 */

		//row.datagrid('beginEdit',editIndex);
		/*
		$('#kunnrUser').combogrid({
			panelWidth : 450,
			panelHight : 500,
			width: 110,
			idField : 'orgId',
			textField : 'orgName',
			pagination : true,
			rownumbers : true,
			fit : true,
			method : 'post',
			multiple : false,
			editable : false,
			url : appUrl + '/reimburse/reimburseAction!getCostCenterJsonList.jspa',
			queryParams : {
				companyCode : $('#defaultCompanyCode').val(),
				orgFlag : $('#orgFlag').val()
			},
			columns : [ [ {
				field : 'orgId',
				title : 'ID',
				hidden : true
			}, {
				field : 'orgName',
				title : '组织',
				width : 220,
				align : 'center'
			}, {
				field : 'costCenter',
				title : '成本中心',
				width : 180,
				align : 'center'
			} ] ],
			toolbar : "#costToolbar_" + indexRow,
			required : true,
			onLoadSuccess : function(){
				if ($('#num').val() > 0) {
					var index = $(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
					$(this).combogrid('setText', $('#defaultOrgName').val());
					$('#costCenterValue_'+index).val($('#costCenterId').val());
				}
			},
			onChange : function(newValue, oldValue){
				var index = $(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
				$('#costCenterValue_'+index).val(newValue);
				getBudgetInfo(index);
			}
		});
		 */

	} else {
		$.messager.alert('Tips', '请先选择需要修改行！', 'warning');
	}

}

/**
 *  zpf 添加handler
 */
function addhandler() {

	/*
	$('#costCenter_'+indexRow).combogrid({
	panelWidth : 450,
	panelHight : 500,
	width: 110,
	idField : 'orgId',
	textField : 'orgName',
	pagination : true,
	rownumbers : true,
	fit : true,
	method : 'post',
	multiple : false,
	editable : false,
	url : appUrl + '/reimburse/reimburseAction!getCostCenterJsonList.jspa',
	queryParams : {
		companyCode : $('#defaultCompanyCode').val(),
		orgFlag : $('#orgFlag').val()
	},
	columns : [ [ {
		field : 'orgId',
		title : 'ID',
		hidden : true
	}, {
		field : 'orgName',
		title : '组织',
		width : 220,
		align : 'center'
	}, {
		field : 'costCenter',
		title : '成本中心',
		width : 180,
		align : 'center'
	} ] ],
	toolbar : "#costToolbar_" + indexRow,
	required : true,
	onLoadSuccess : function(){
		if ($('#num').val() > 0) {
			var index = $(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
			$(this).combogrid('setText', $('#defaultOrgName').val());
			$('#costCenterValue_'+index).val($('#costCenterId').val());
		}
	},
	onChange : function(newValue, oldValue){
		var index = $(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
		$('#costCenterValue_'+index).val(newValue);
		getBudgetInfo(index);
	}
	});
	 */
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
	//	queryParams.custParent = $("#custParent").combogrid("getValue");
	queryParams.custType = $("#custType").val();
	$("#datagrid").datagrid('load');
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
// 二阶客户下拉查询
//function searcherParent(name1) {
//	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
//	queryParams.search = encodeURIComponent(name1);
//	$('#custParent').combogrid("grid").datagrid('reload');
//}
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
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}

	//		$('#custParent').combogrid(
	//				{
	//					url : appUrl
	//							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
	//							+ $('#orgId').val()
	//				});
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
// zpf 选择门店上游经销商下门店s 
function selectUpKunnrUser(custKunnr) {
	var custKunnrId;
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	/*
	if (custKunnr.indexOf(",")!=-1){
		alert("two");
		custKunnrId = custKunnr.split(",")[0];
	}else {
		alert("one");
		custKunnrId = custKunnr;
	}
	alert(custKunnrId);
	 */

	openWindow('选择经销商负责人', '/customerAction!selectUpKunnrUser.jspa?node='
			+ node + '&orgName=' + orgName + "&custKunnrId=" + custKunnrId,
			520, 460);
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
// excel导出终端客户数据
function excel() {
	$.messager.confirm('Confirm', '是否确定导出?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!customerExport.jspa';
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
		}
	});
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
			var form = /* window.document.forms[0] */document
					.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportCustomerMouldCsv.jspa';
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
	//	$('#custParent').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
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
