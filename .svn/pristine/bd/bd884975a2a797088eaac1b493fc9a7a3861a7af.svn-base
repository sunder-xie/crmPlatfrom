$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					$('#isOffice').combobox({
						multiple : true,
						valueField : 'status',
						textField : 'text',
						data : [{
							status : 'Y',
							text : '员工'
						}, {
							status : 'N',
							text : '经销商'
						}],
						multiple : false, 
						editable : false, 
						required : true,
						panelHeight: 'auto',
						onLoadSuccess : function() {
							$('#isOffice').combobox("setValue", 'Y');
						}
					});
					
					$('#stationIds').combogrid({
						panelWidth:480,
						idField:'stationId',
						textField:'stationName',
						pagination : true,//
		                rownumbers:true,//
		                collapsible:false,//
		                fit: true,//
		                method:'post',  
		                multiple:false,
		                editable:false,
						url:appUrl + '/smsAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val(),  
						columns:[[
							  {field:'id',title:'POSID',width:100,hidden:true},  
		                    {field:'stationId',title:'岗位ID',width:60},  
		                    {field:'stationName',title:'岗位名称',width:150},  
		                    {field:'orgName',title:'公司名',width:120}
						]],
						toolbar:'#toolbar'
						

					});
					var q = $('#stationIds').combogrid("grid").datagrid();
					$('#stationIds').combo({  
						multiple:true  
					});
					$(q).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});	
					/**
					 * datagrid
					 */

					$('#datagrid1').datagrid({
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/smsAction!searchEmpInfo.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '385',
						width : 'auto',
						queryParams:{ orgId:'50919',bhxjFlag:'C'},
						columns : [ [ 
						{
							field : 'id',
							title : '编号ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'userName',
							title : '名称',
							width : 150,
							align : 'center',
							sortable : true
						}, {
							field : 'kunnr',
							title : '经销商编码',
							width : 150,
							align : 'center',
							hidden : true,
							sortable : true							
						}, {
							field : 'bus_mobilephone',
							title : '工作号码',
							width : 120,
							align : 'center',
							sortable : true
						}, {
							field : 'pri_mobilephone',
							title : '私人号码',
							width : 120,
							align : 'center',
							sortable : true							
						}, {
							field : 'mobile',
							title : '电话号码',
							width : 120,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'orgName',
							title : '组织',
							width : 150,
							align : 'center',
							sortable : true
						}, {
							field : 'orgId',
							title : '组织Id',
							width : 80,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'stationName',
							title : '岗位',
							width : 150,
							align : 'center',
							sortable : true
						}, {
							field : 'stationId',
							title : '岗位Id',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'isOffice',
							title : '人员类别',
							width : 80,
							align : 'center',
							sortable : true
						} ] ],						
						toolbar : [ "-", {
							text : '显示全部',
							handler : function() {
								$('#datagrid1').datagrid({
									pagination : false
								});
							}
						}, "-", {
							text : '下载模板',
							handler : function() {
								exportModel();
							}
						}, "-" ]
					});

					// 分页控件
					var p = $('#datagrid1').datagrid('getPager');
					$(p).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});
				});


function search() {
	var queryParams =$('#datagrid1').datagrid('options').queryParams;
	queryParams.stationId = $('#stationIds').combogrid("getValue");
	queryParams.isOffice = $("#isOffice").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	queryParams.userName=encodeURIComponent($("#userName").val());
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}
	if($("#isOffice").combobox("getValue")=='N'){
		$('#datagrid1').datagrid('hideColumn', 'bus_mobilephone');
		$('#datagrid1').datagrid('hideColumn', 'pri_mobilephone');
		$('#datagrid1').datagrid('hideColumn', 'stationName');
		$('#datagrid1').datagrid('showColumn', 'kunnr');
		$('#datagrid1').datagrid('showColumn', 'mobile');
	}else{
		$('#datagrid1').datagrid('showColumn', 'bus_mobilephone');
		$('#datagrid1').datagrid('showColumn', 'pri_mobilephone');
		$('#datagrid1').datagrid('showColumn', 'stationName');
		$('#datagrid1').datagrid('hideColumn', 'kunnr');
	}
	$('#datagrid1').datagrid({pagination: true});
}


function searcher(val){
	val =encodeURIComponent(val);
	$('#stationIds').combogrid({url : appUrl + '/smsAction!getSelectedStationsJSON.jspa?stationId='+val+"&orgId="+$('#orgId').val()});
	$('#stationIds').combogrid("grid").datagrid('reload');

}


function clearValue() {
	$('#orgId').val('50919');
	$('#orgName').val('香飘飘食品股份有限公司');
	$('#isOffice').combobox("setValue", 'Y');
	$('#userName').val('');
	isSelectedOrg();
}

//返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
//创建窗口对象
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt")
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
	$("#maintModelAtt").window('close');
}


function selectOrgTree() {
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}


function isSelectedOrg(){
	$('#stationIds').combogrid('clear');
	$('#stationIds').combogrid({url : appUrl + '/smsAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val()});
	$('#stationIds').combogrid("grid").datagrid('reload');
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
	isSelectedOrg();
}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	closeWindow();
}

/**
 * 去空格
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
  }


function exportModel(){
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	var stationId = $('#stationIds').combogrid("getValue");
	if(stationId=='undefined'){
		stationId = ''
	}
	if($("#isOffice").combobox("getValue")=='N'){
		form.action = appUrl + '/smsAction!exportKunnrModel.jspa?stationId='+stationId;
	}else{
		form.action = appUrl + '/smsAction!exportEmpModel.jspa?stationId='+stationId;
	}
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

function submitImport() {
	if (document.all.fileContent.value == "") {
		alert("请选择文件！");
		return;
	}
	$.messager.progress();
	$('#fileName').val(document.all.fileContent.value);
	var form = window.document.forms[0];
	form.action = appUrl + "/smsAction!importEmpMobile.jspa";
	form.target = "hideFrame";
	form.submit();
}

function cancel(){
	window.location.reload();
}