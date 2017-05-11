var format_id = "";
var isOffice = "";
isOffce = $("#isOffice").val();
$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					var kunnr = ""
					if(isOffce!=""){
						document.getElementById( "ss").style.display= "none"; //"inline"
						kunnr = $("#default_kunnr").val()
					}
					$('#datagrid').datagrid({
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/orderNewAction!getPrintFormatInfo.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '385',
						width : 'auto',
						queryParams:{ kunnr:kunnr,orgId:$("#orgId").val(),bhxjFlag:$("#bhxjFlag").val()},
						columns : [ [ 
						{
							field : 'ck',
							checkbox : true
						}, {
							field : 'format_id',
							title : '编号ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'kunnr',
							title : '经销商编码',
							width : 80,
							align : 'center',
							sortable : true,
//							hidden : true
						}, {
							field : 'kunnr_name',
							title : '经销商名称',
							width : 250,
							align : 'center',
							sortable : true	,
						}, {
							field : 'properties_code',
							title : '单据显示字段编码',
							width : 120,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'properties_name',
							title : '单据显示内容',
							width : 120,
							align : 'center',
							sortable : true	,
						}, {
							field : 'type',
							title : '类型',
							width : 120,
							align : 'center',
							sortable : true,
							formatter : function(v) {
								if (v == 'T') {
									return '主题';
								} else if (v == 'H') {
									return '抬头'
								} else if (v == 'L') {
									return 'SKU清单'
								} else if (v == 'B') {
									return '合计'
								} else if (v == 'F') {
									return '页脚'
								}
								
							}
						}, {
							field : 'order_desc',
							title : '排列顺序',
							width : 50,
							align : 'center',
							sortable : true,
							editor : {
								type : 'numberbox', 
								options : {min:0, max:100, required : true}
						    }
						}, {
							field : 'create_date',
							title : '创建时间',
							width : 115,
							align : 'center',
							sortable : true
						}, {
							field : 'modify_date',
							title : '修改时间',
							width : 115,
							align : 'center',
							sortable : true
						}, {
							field : 'operator',
							title : '创建人Id',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'operator_name',
							title : '创建人',
							width : 80,
							align : 'center',
							sortable : true
						},{field : 'operation', 
							title : '操作',
							align : 'center', 
							width : 90, 
							formatter : function(value, row, index){ 
								var id = row.format_id;
								var e ='<img style="cursor:pointer" title="修改信息" onclick="modifyFormat('+id+')" src= '
								+ imgUrl
								+'/images/actions/action_edit.png align="absMiddle"></img>'; 
								var y ='<img style="cursor:pointer" title="预览" onclick="formatView('+id+')" src= '
								+ imgUrl
								+'/images/actions/action_view.png align="absMiddle"></img>'; 
										return e+'&nbsp;&nbsp'+y;
								}
//							}
						} ] ],
						toolbar : [ 
//						    "-", {
//							text : '显示全部',
//							handler : function() {
//								$('#datagrid1').datagrid({
//									pagination : false
//								});
//							}
//						}, 
						"-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createFormat();
							}
						}, "-" 
						, {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteFormat();
							}
						}, "-" 
						]
					});

					// 分页控件
					var p = $('#datagrid').datagrid('getPager');
					$(p).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});
				});


function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	if(isOffce!=""){
		queryParams.kunnr=$("#default_kunnr").val()
	}else{
		queryParams.orgId=$("#orgId").val();
		queryParams.kunnr=$("#kunnr").val();
		queryParams.kunnr_name=encodeURIComponent($("#kunnr_name").val());
		if (document.getElementById("bhxjFlag").checked) {
			queryParams.bhxjFlag = $("#bhxjFlag").val();
		}else{
			queryParams.bhxjFlag='';
		}
	}
	$("#datagrid").datagrid('load');
}


function searcher(val){
	val =encodeURIComponent(val);
	$('#stationIds').combogrid({url : appUrl + '/smsAction!getSelectedStationsJSON.jspa?stationId='+val+"&orgId="+$('#orgId').val()});
	$('#stationIds').combogrid("grid").datagrid('reload');

}


function clearValue() {
	$('#orgId').val($("#default_orgId").val());
	$('#orgName').val($("#default_orgName").val());
	$('#kunnr_name').val('');
	$('#kunnr').val('');
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
			if(isOffce!=""){
				window.location.reload();
			}else{
				search();
			}
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

/**
 * 去空格
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
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


function cancel(){
	window.location.reload();
}

function modifyFormat(format_id){
	openWindow('修改打印格式','/orderNewAction!toModifyFormatPre.jspa?format_id='+format_id, 500, 500);
}

function createFormat(){
	openWindow('新增打印格式','/orderNewAction!toModifyFormatPre.jspa', 500, 500);
}

function formatView(format_id){
	openWindow('预览','/orderNewAction!toFormatView.jspa?format_id='+format_id, 500, 500);
}

function deleteFormat(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	$.messager.confirm('Confirm', '是否批量刪除格式?', function(r) {
		if (r) {
			var Ids = [];
			for (var i = 0; i < rows.length; i++) {
				Ids.push(rows[i].format_id);
			}
			$("#ids").val(Ids);
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/orderNewAction!deleteFormat.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});	
	
}



