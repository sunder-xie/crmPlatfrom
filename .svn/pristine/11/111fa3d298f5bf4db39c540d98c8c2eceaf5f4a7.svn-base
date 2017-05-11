var processInstanceId;
$(document).ready(function() {
			loadGrid();
			loadGrid1();
			loadGrid2();
			$('#year').combobox({
						onSelect : function(d) {
							$('#year').val(d.itemName);
						}
					});

			$('#month').combobox({
						onSelect : function(d) {
							$('#month').val(d.value);
						}
					});
		   $('#mark').combobox({
						onSelect : function(d) {
							$('#mark').val(d.value);
						}
					});		
//           $('#id').combobox({
//						onSelect : function(d) {
//							$('#id').val(d.kunnr);
//						}
//					});
//		    $('#wid').combobox({
//						onSelect : function(d) {
//							$('#wid').val(d.matnr);
//						}
//					});
			if ($("#flagTemp").val() != "Y") {
				document.getElementById("add").style.display = "none";
				document.getElementById("addOrg").style.display = "none";
				document.getElementById("approve").style.display = "none";
				document.getElementById("remove").style.display = "none";
				document.getElementById("mould").style.display = "none";
				document.getElementById("import").style.display = "none";
			}
			$('#hideFrame').bind('load', promgtMsg);
		});
function loadGrid() {
	var toolbar = $('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/stock/instockAction!getInStockList.jspa',
		queryParams: {
//		orgId: $("#orgId").val(),
//		year:$("#year").val(),
//		mark:$("#mark").val()
	    },
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		height : height,
		striped : true,
		columns : [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'instock_id',
					title : '序列号',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'instock_send_place',
					title : '出货地点',
					width : setColumnWidth(0.12),
					align : 'center'
					
				},{
					field : 'instock_total_id',
					title : '总单Id',
					width : setColumnWidth(0.12),
					align : 'center'
					
				},{
					field : 'instock_good_place',
					title : '库存地点',
					width : setColumnWidth(0.12),
					align : 'center',
					formatter : function(value,row,rec){
									var s = row.instock_good_place;
									if(s=='1'){
									return "公共库存点";
									}
									}
				}, 
				{
					field : 'instock_provide_id',
					title : '经销商ID',
					width : setColumnWidth(0.15),
					align : 'center'
				}, {
					field : 'instock_provide_name',
					title : '经销商名称',
					width : setColumnWidth(0.15),
					align : 'center'
				},{
					field : 'instock_provide_name',
					title : '状态',
					width : setColumnWidth(0.15),
					align : 'center'
				},{
					field : 'price',
					title : '操作',
					width : setColumnWidth(0.15),
					align : 'center',
					formatter : function(value, row, index) {
						var id = row.instock_total_id
							return '<img style="cursor:pointer" onclick="updateInstock('
									+ id
									+ ')" title="修改经销商目标" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';			
					}
				}]],
		toolbar : "#toolbar"
	});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
	//search();
}

function loadGrid2() {
	$('#id').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'id',
			title : '客户ID',
			hidden : true,
			width : 60
		},
			{
			field : 'kunnr',
			title : '客户编号',
			align : 'center',
			width : 120
		}, {
			field : 'name1',
			title : '客户名称',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '手机',
			align : 'center',
			width : 150
		}] ],
		toolbar : '#toolbar2'
	});

}
function loadGrid1() {
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'matnr',
			title : '物料号',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '物料组',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '物料组描述',
			align : 'center',
			width : 100
		}] ],
		toolbar : '#toolbar1'
	});
}
function closeDtPlan() {
	$("#MaintGoal").window('close');
}

function initMaintGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintGoal").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : false,
		minimizable : false,
		maximizable : false,
		collapsible : true,
		draggable : true
	});
	$win.window('open');
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}
function createInstock() {
	//getWorkPlanDetailListByTotalId
	initMaintGoal('入库单创建', '/instockAction!searchStockPrepare.jspa', 820, 475);
}
function updateInstock(instock_total_id) {
	var instock_total_id = encodeURIComponent(instock_total_id);
	initMaintGoal('目标数修改', '/instockAction!updateStockPrepare.jspa?instock_total_id=' + instock_total_id,
			760, 400)
}
//function updateOrgGoal(ctId) {
//	var ctId = encodeURIComponent(ctId);
//	initMaintGoal('组织目标数修改', '/goalAction!updateGoalPrepare.jspa?ctId=' + ctId+'&mark'+mark,
//			490, 400)
//}
function importMould() {
	
	//    $.ajax({
//					type : "post",
//					async : false,
//					url : appUrl + "/goalAction!selectNexUser.jspa?time="
//							+ new Date(),
//					data : {
//						userId : $("#userId").val()
//					},
//					success : function(obj) {
	//				nextUserHtml(obj);
					 html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
						+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' 
						+  '<table><tr>'
						+  '<td class="head" noWrap>批量导入</td>'
						+  '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
						+  '</tb></tr>'
						+ '<tr><td class="head" noWrap>导入类型</td>'
						+ '<td><select id="type" name="type" style="width:130px">'
						+'<option value="N">--组织目标--</option>'
						+'<option value="Y" selected>--经销商目标--</option>'
                        +'</select>'
				        +'</td></tr><table></form>';
//		      		$.each(obj.result, function(i, v) {
//			  			html += '<option value="' + v.userId + '">' + v.userName + '----'+ v.stationName + '</option>';
//		     		});
//						processInstanceId = obj.processInstanceId
//					}
	//			});
				
					
	 openImportDialog('导入目标', html);
}
var mask_;
/*打开导入项目Excel对话框*/
function openImportDialog(t, html){ 
	
	if ($('#excelDialog').length < 1) {
		$('<div/>', {
			id : 'excelDialog',
			title : '选择上传文件',
			html :  "<div id='import'>" + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ html + "</div>" + "</div>"					
		}).appendTo('body');
	} else {
//		$('#import').html(html);
	}
	$('#excelDialog').dialog({
		modal : true,
		resizable : false,
		dragable : false,
		closable : false,
		open : function() {
			$('#excelDialog').css('padding', '0.8em');
			$('#excelDialog .ui-accordion-content').css('padding', '0.4em')
					.height($('#excelDialog').height() - 100);
		},
		buttons : [{
					text : '确定',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						var type = document.getElementById('type').value;
						if (/^.+\.(csv|CSV)$/.test(file)) {
							$("#type").val($("#type").val());
							$.messager.progress();
							var form = document.getElementById('fileForm');			
							form.action = appUrl
									+ "/goal/goalAction!importGoalCsv.jspa" 
//											"eventId="+processInstanceId;
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
				}],

		width : document.documentElement.clientWidth * 0.28,
		height : document.documentElement.clientHeight * 0.50
	});
}

/*对用户导入项目Excel进行检查*/
function importCheck() {
	var file = document.getElementById('projectImportFile').value;
	if (/^.+\.(xls|xlsx)$/.test(file)) {
		document.getElementById('fileForm').submit();
		$('#import').dialog('close');
		mask_ = new mask({
					tip : '请等待导入完成...'
				});
		mask_.show();
		window.setTimeout(getImportStateInfo, 2000);
	} else {
		$.messager.alert("提示", "请导入Excel文件");
	}
}

function selectOrgTree() {
	initMaintGoal('选择组织', '/goalAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
//function approveGoal() {
//	$.messager.confirm('Confirm', '是否批量审批目标?', function(r) {
//				if (r) {
//					var rows = $('#datagrid').datagrid('getSelections');
//					if (rows == '') {
//						$.messager.alert('Tips', '  没有行被选中!');
//						return;
//					}
////					for (var i = 0; i < rows.length; i++) {
////						if (rows[i].trFlag == 't') {
////							$.messager.alert('Tips', '  选中行内有项目已审批不能驳回!');
////							return;
////						}
////					}
//					var ids = [];
//					for (var i = 0; i < rows.length; i++) {
//						if(rows[i].trFlag == 'S'){
//						ids.push(rows[i].ctId);
//						}
//					}
//					$("#ids").val(ids);
//					var form = window.document.forms[0];
//					form.action = "goalAction!approveGoal.jspa";
//					form.target = "hideFrame";
//					form.submit();
//				}
//			});
//}

function deleteGoal() {
	$.messager.confirm('Confirm', '是否批量删除目标?', function(r) {
				if (r) {
					var rows = $('#datagrid').datagrid('getSelections');
					if (rows == '') {
						$.messager.alert('Tips', '  没有行被选中!');
						return;
					}
					for (var i = 0; i < rows.length; i++) {
						if (rows[i].trFlag == 't') {
							$.messager.alert('Tips', '  选中行内有项目已审批不能删除!');
							return;
						}
					}
					var ids = [];
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].ctId);
					}
					$("#ids").val(ids);
					var form = window.document.forms[0];
					form.action = "goalAction!deleteGoal.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
}

function rejectGoal(){
$.messager.confirm('Confirm', '是否批量驳回目标?', function(r) {
				if (r) {
					var rows = $('#datagrid').datagrid('getSelections');
					if (rows == '') {
						$.messager.alert('Tips', '  没有行被选中!');
						return;
					}
//					for (var i = 0; i < rows.length; i++) {
//						if (rows[i].trFlag == 'S') {
//							$.messager.alert('Tips', '请勿重复驳回!');
//							return;
//						}
//					}
					var ids = [];
					for (var i = 0; i < rows.length; i++) {
						if(rows[i].trFlag == 't'){
						ids.push(rows[i].ctId);
						}
					}
					$("#ids").val(ids);
					var form = window.document.forms[0];
					form.action = "goalAction!rejectGoal.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear(){
			$('#month').val("");
			$('#mark').val("");
			$('#month').combobox('setValue','');
			$("#id").combobox('setValue',''); ;
			$("#wid").combobox('setValue','');	
			$("#mark").combobox('setValue','')
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.beginDate = encodeURIComponent($("#beginDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	//queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function getWidth(percent) {
	if (jQuery.browser.safari) {
		return document.body.clientWidth * percent - 2;
	} else {
		return document.body.clientWidth * percent;
	}
}
function getHeight(percent) {
	return document.body.clientHeight * percent;
}
//function nextUserHtml(obj) {
//	nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
//			+ "<tr><td class='head' noWrap>处理人列表</td>"
//			+ "<td><select id='nextUsers'>";
//	$.each(obj.result, function(i, v) {
//		nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
//				+ v.stationName + "</option>";
//	});
//	nextUser += "</select></td></tr></table>";
//}

function promgtMsg() {	
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
           $('#excelDialog').dialog('close')
           search();
			}

	});
}
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