var bhxjFlag = ""
$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					document.getElementById( "m").style.display= "none "; 
					var isOffice = "";
					isOffce = $("#isOffice").val()
					if(isOffce!=""){
						document.getElementById( "org").style.display= "none "; 
						document.getElementById( "k").style.display= "none"; //"inline"
					}
					
					if (document.getElementById("bhxjFlag").checked) {
						bhxjFlag = $("#bhxjFlag").val();
					}
					

					
					//选择类型
					$('#type').combobox({
						multiple : true,
						valueField : 'status',
						textField : 'text',
						data : [{
							status : 'T',
							text : '主题'
						}, {
							status : 'H',
							text : '抬头'
						}, {
							status : 'L',
							text : 'SKU清单'
						}, {
							status : 'B',
							text : '合计'
						}, {
							status : 'F',
							text : '页脚'
						}],
						multiple : false, 
						editable : false, 
						required : true,
						panelHeight: 'auto',
						onLoadSuccess : function() {
		
						},
		                onSelect:function(){ 
		                	var m = $('#type').combobox('getValue');
		                	if(m=='F'){
		                		$('#properties').combogrid("clear")
		                		document.getElementById( "m").style.display= "";
		                	}else{
		                		document.getElementById( "m").style.display= "none";
		                	}
		                }  
					});					
					
					
					$('#type').combobox("setValue",$("#default_type").val());
                	var m = $("#default_type").val()
                	if(m=='F'){
                		document.getElementById( "m").style.display= "";
                		$("#memo").val($("#default_memo").val());
                	}else{
                		document.getElementById( "m").style.display= "none";
                	}
					
					
					// 选择经销商
					$('#kunnrs')
							.combogrid(
									{
										panelWidth : 480,
										// panelHight : 480,
										idField : 'kunnr',
										textField : 'name1',
										pagination : true,// 是否分页
										rownumbers : true,// 序号
										collapsible : false,// 是否可折叠的
										// fit : true,// 自动大小
										method : 'post',
										// multiple : true,
										editable : false,
										url : appUrl
												+ '/orderNewAction!kunnrSearch.jspa?orgId='
												+ $('#orgId').val()
												+ "&bhxjFlag=" + bhxjFlag
												+ "&status=1",
										columns : [ [ {
											field : 'kunnr',
											title : '经销商编码',
											width : 70,
										}, {
											field : 'name1',
											title : '经销商',
											width : 280
										}, {
											field : 'orgName',
											title : '组织',
											width : 80
										}, ] ],
										onSelect : function(rowIndex, rowData) {
											$("#kunnr").val(rowData.kunnr)
											$("#kunnr_name").val(rowData.name1)
										},

										toolbar : '#toolbar'
									});

					$('#kunnrs').combogrid('setValue', $("#kunnr_name").val());

					// 选择单据显示内容
					$('#properties')
							.combogrid(
									{
										panelWidth : 480,
										idField : 'properties_code',
										textField : 'properties_name',
										pagination : false,//
										rownumbers : true,//
										collapsible : false,//
										fit : true,//
										method : 'post',
										multiple : false,
										editable : false,
										url : appUrl
												+ '/orderNewAction!getPropertiesJSON.jspa',
										columns : [ [
										// {field:'id',title:'POSID',width:100,hidden:true},
										{
											field : 'properties_code',
											title : '单据显示内容ID',
											width : 60,
											hidden : true
										}, {
											field : 'properties_name',
											title : '单据显示内容',
											width : 400
										}, ] ],
										onSelect : function(rowIndex, rowData) {
											$("#properties_name").val(
													rowData.properties_name)
											$("#properties_code").val(
													rowData.properties_code)
										    if(rowData.properties_code=='foot'){
										    	document.getElementById( "m").style.display= "";
										    	$("#type").combobox({ disabled: true });
										    	$('#type').combobox('setValue','F')
										    }else{
										    	$("#type").combobox({ disabled: false });
										    	document.getElementById( "m").style.display= "none";
										    	$('#type').combobox('clear')
										    }
										},
									// toolbar : '#toolbar'

									});
					$('#properties').combogrid('setValue',
							$("#properties_name").val());
					var q = $('#properties').combogrid("grid").datagrid();
					$('#properties').combo({
						multiple : true
					});
					$(q).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});
				});

function searcher(val) {
	bhxjFlag = ""
	if (document.getElementById("bhxjFlag").checked) {
		bhxjFlag = $("#bhxjFlag").val();
	}
	val = encodeURIComponent(val);
	$('#kunnrs').combogrid(
			{
				url : appUrl + '/orderNewAction!kunnrSearch.jspa?value=' + val
						+ "&bhxjFlag=" + bhxjFlag + "&orgId="
						+ $('#orgId').val() + "&status=1"
			});
	$('#kunnrs').combogrid("grid").datagrid('reload');
}

// 返回信息
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
				window.parent.location.reload();
			}else{
				window.parent.search();
			}
			close();
		});
	}
}


function change_bhxjFlag() {
	bhxjFlag = ""
	if (document.getElementById("bhxjFlag").checked) {
		bhxjFlag = $("#bhxjFlag").val();
	}
	$('#kunnrs').combogrid(
			{
				url : appUrl + '/orderNewAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + "&bhxjFlag=" + bhxjFlag
						+ "&status=1"
			});
	$('#kunnrs').combogrid("grid").datagrid('reload');
}

// 创建窗口对象
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
	bhxjFlag = ""
	if (document.getElementById("bhxjFlag").checked) {
		bhxjFlag = $("#bhxjFlag").val();
	}
	$('#kunnrs').combogrid(
			{
				url : appUrl + '/orderNewAction!kunnrSearch.jspa?orgId='
						+ selectedId + "&bhxjFlag=" + bhxjFlag + "&status=1"
			});
	$('#kunnrs').combogrid("grid").datagrid('reload');
}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	closeWindow();
}

function submit() {
	var properties_name = "";
	var properties_code = "";
	var kunnr = "";
	var type = "";
	var order_desc = "";
	properties_name = $("#properties_name").val()
	properties_code = $("#properties_code").val()
	kunnr = $("#kunnr").val()
	type = $('#type').combobox("getValue");
	order_desc = $("#order_desc").val()
	if(isOffce!=""){
		kunnr = $("#default_kunnr").val()
		$("#kunnr").val(kunnr)
	}
	if (properties_name == "" || properties_code == "" || kunnr == ""
			|| type == "" || order_desc == "") {
		$.messager.alert('Tips', '信息未填写完整!', 'warning');
		return;
	}
	if(type=='F'){
		var memo = "";
		memo = $("#memo").val()
		if(memo == ""){
			$.messager.alert('Tips', '信息未填写完整!', 'warning');
			return;
		}
	}else{
		$("#memo").val("")
	}
	var format_id = "";
	format_id = $("#format_id").val()

	$.messager.progress();
	var form = window.document.forms[0];
	if (format_id != "") {
		form.action = appUrl + "/orderNewAction!modifyFormat.jspa";
	} else {
		form.action = appUrl + "/orderNewAction!createFormat.jspa";
	}
	form.target = "hideFrame";
	form.submit();

	// window.parent.document.getElementById("selectGroupName").value =
	// groupName;
	// window.parent.document.getElementById("selectIsPubGroup").value =
	// isPubGroup;
	// window.parent.returnGroup();
}

function close() {
	window.parent.closeWindow();
}

function cancel() {
	window.location.reload();
}
