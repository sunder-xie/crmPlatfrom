$(document).ready(function() {
//	$.messager.progress({ title:'Please waiting', msg:'Loading data...' });
//	$.ajax({
//		type : "post",
//		async : false,
//		url : appUrl + '/smsAction!toSynchPayment.jspa',
//		loadMsg : 'SAP数据远程载入中,请等待...',
//		success : function(synchPayment) {
//			if(synchPayment == 'error'){
//				$.messager.progress('close');
//				$.messager.alert('Tips', 'sap数据同步失败', 'warning');	
//			}else{
//				$.messager.progress('close');
//				$.messager.alert('Tips', 'sap数据同步成功', 'warning');	
//			};
//		}
//		});

	synchPayment();
	$('#hideFrame').bind('load', promgtMsg);

	// 客户分类 渠道
//	$('#channelId').combobox({
//		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + 'K',
//		valueField : 'channelId',
//		textField : 'channelName'
//	});
});

function loadGrid() {

	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/smsAction!searchPaymentNoticeInfo.jspa',
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
						        },
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
									align : 'center',
									sortable:true
								},
								{
									field : 'name3',
									title : '法人',
									width : setColumnWidth(0.05),
									align : 'center',
									sortable:true
								},
								{
									field : 'mobNumber',
									title : '法人手机号码',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable:true
								},
//								{
//									field : 'bukrs',
//									title : '公司代码',
//									width : setColumnWidth(0.06),
//									sortable:true
//								},
//								{
//									field : 'businessManager',
//									title : '城市经理',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									sortable:true
//								},
//								{
//									field : 'managerMobile',
//									title : '城市经理手机',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									sortable:true
//								},
//								{
//									field : 'businessCompetent',
//									title : '客户经理',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									sortable:true
//								},
//								{
//									field : 'competentMobile',
//									title : '客户经理手机',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									sortable:true
//								},
								{
									field : 'salesman',
									title : '业务员',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'salesmanMoblie',
									title : '业务员手机号码',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable:true
								},
								{
									field : 'bank_etydat',
									title : '付款日期',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'bank_trsamtd',
									title : '收到货款金额',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable:true
								},
								{
									field : 'bank_belnr',
									title : '会计凭证',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable:true
								},
								{
									field : 'send_date',
									title : '发送日期',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable:true
								},
								{
									field : 'paymentId',
									title : '货款到账Id',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden:true,
									sortable:true
								},
									] ],
//								onBeforeEdit : function(index, row) {
//									row.editing = true;
//									updateActions(index);
//								},
//								onAfterEdit : function(index, row) {
//									row.editing = false;
//									updateActions(index);
//								},
//								onCancelEdit : function(index, row) {
//									row.editing = false;
//									updateActions(index);
//								},
						toolbar : [ "-", {
							text : 'Excel导出数据',
							iconCls : 'icon-excel',
							handler : function() {
								excel();
							}
						}, "-", {
							text : '显示全部',
							handler : function() {
								$('#datagrid').datagrid({pagination: false});
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


function synchPayment(){
	function ajaxLoading(){ 
	    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body"); 
	    $("<div class=\"datagrid-mask-msg\"></div>").html("SAP数据正在同步，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 260) / 2,top:($(window).height() + 70) / 2}); 
	 } 
	function ajaxLoadEnd(){ 
	     $(".datagrid-mask").remove(); 
	     $(".datagrid-mask-msg").remove();             
	} 
	$('#datagrid')
	.datagrid(
			{
				iconCls : 'icon-list',
				title : '查询结果',
				height : height,
			});

	$.ajax({ 
	type: 'POST', 
	url: appUrl + '/smsAction!toSynchPayment.jspa', 
	beforeSend:ajaxLoading,
	success: function(synchPayment){ 
		if(synchPayment == 'error'){
			ajaxLoadEnd();
			$.messager.alert('Tips', 'SAP数据同步失败', 'warning');	
			loadGrid();
		}else{
			ajaxLoadEnd();
			loadGrid();
		};
	} 
	}); 	
}


//excel导出
function excel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/smsAction!paymentNoticeInfoExport.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	var timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : appUrl + "/smsAction!checkDownLoadOver.jspa",
			success : function(data) {
				if (data == 'Yes') {
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
	}, 500);
}

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $("#kunnr").val();
	queryParams.name1 = encodeURIComponent($("#name1").val());
//	queryParams.businessManager = encodeURIComponent($("#businessManager")
//			.val());
//	queryParams.businessCompetent = encodeURIComponent($("#businessCompetent")
//			.val());
	queryParams.startDate = $('#startDate').datebox('getValue');
	queryParams.endDate = $('#endDate').datebox('getValue');
	queryParams.status = $("#status").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}
	$('#datagrid').datagrid({pagination: true});
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function reset() {
	document.forms[0].reset();
	$('#startDate').datebox('setValue', '');
	$('#endDate').datebox('setValue', '');
	$('#status').combobox('setValue', '');
	$("#orgId").val('');
	$("#orgName").val('');
}

function cancel() {
	self.location.reload(true);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');
	}
}

function save() {
		var sendInfoList=[];
		var paymentIdList = [];
		$.messager.confirm('Confirm', '是否确认发送短信?', function(r) {
			if (r) {
				var rows = $('#datagrid').datagrid('getSelections');
				if (rows == '') {
					$.messager.alert('Tips', '请选择数据!', 'warning');
					return;
				}
				$.messager.progress();
				for ( var i = 0; i < rows.length; i++) {
					sendInfoList.push({
						"mobile" : rows[i].mobNumber,
						//"mobile" : "13904419465",
						"content" : '流水号'+rows[i].bank_belnr+
									'尊敬的经销商朋友，您好！'+
									rows[i].name1+'汇入我司账户的'+
									rows[i].bank_trsamtd+
									'元货款已经到账，任何疑问及问题，请随时联系我们各区域财务人员。——”香飘飘营销中心财务部”。',
					});
					sendInfoList.push({
						"mobile" : rows[i].salesmanMoblie,
						//"mobile" : "13904419465",
						"content" : '流水号'+rows[i].bank_belnr+
									'尊敬的经销商朋友，您好！'+
									rows[i].name1+'汇入我司账户的'+
									rows[i].bank_trsamtd+
									'元货款已经到账，任何疑问及问题，请随时联系我们各区域财务人员。——”香飘飘营销中心财务部”。',
					});	
					paymentIdList.push({
						"paymentId" :rows[i].paymentId
					});
				}
				$('#sendInfoList').val($.toJSON(sendInfoList));
				$('#paymentIdList').val($.toJSON(paymentIdList));
				var form = window.document.forms[0];
				form.action = appUrl + '/smsAction!sendPaymentNotice.jspa';
				form.target = "hideFrame";
				form.submit();
			}
		});
	

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
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}
	/**
	 * 组织树选择完之后关闭
	 */
	function closeOrgTree() {
		closeWindow();
	}

//document.onkeydown = function(e) {
//	var theEvent = e || window.event;
//	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
//	if (code == 13) {
//		search();
//		return false;
//	}
//	return true;
};
