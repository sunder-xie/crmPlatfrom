$(document).ready(function() {
//    loadGrid();
//	loadGrid1();
	for(var i=0; i<$("#size").val(); i++){
		addHandler(i);
	}
//	$('#year').combobox({
//		onSelect : function(d) {
//				$('#year').val(d.itemName);
//		}
//	});
//	
//	$('#month').combobox({
//		onSelect : function(d) {
//				$('#month').val(d.value);
//		}
//	});
	$('#hideFrame1').bind('load', promgtMsg);
});
var i = $("#size").val()-1;
var outsideArr = new Array();


function loadGrid() {
	for(var i=0; i<$("#size").val(); i++){
	$('#id_'+i).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'id',
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
		onLoadSuccess : function() {
			$('#expType_'+i).combogrid("setText", $("#custNameZH"+i).val());
			$('#expType_'+i).combogrid("setValue", $("#custId"+i).val());
		},
		toolbar : '#toolbar'
	});
	}
}
function loadGrid1() {
	for(var i=0; i<$("#size").val(); i++){
		$('#wid_'+i).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'wid',
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
			title : '品项or四级科目（SKU）ID',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '品项or四级科目（SKU）',
			align : 'center',
			width : 100
		}] ],
		toolbar : '#toolbar1'
	});
	}
}


function addHandler(i){
	var divHtml = "<div id='Toolbar_"+
		+ i
		+ "' class='datagrid-toolbar' > <input id='search_"
		+ i
		+"' style='width:300px'></input>"	
		+ "</div>";
	$('#ToolBar').append(divHtml);
	
	var divHtml1 = "<div id='Toolbar1_"+
		+ i
		+ "' class='datagrid-toolbar' > <input id='search1_"
		+ i
		+"' style='width:300px'></input>"	
		+ "</div>";
	$('#ToolBar1').append(divHtml1);
	

	$('#search_'+i).searchbox({   
	    searcher:function(value,name){  
	        search(value, i);  
	    },   
	    prompt:'请填写客户代码或客户名称'  
	}); 
	$('#search1_'+i).searchbox({   
	    searcher1:function(value,name){  
	        search1(value, i);  
	    },   
	    prompt:'请填写品项or四级科目（SKU）代码或名称'  
	}); 
	if(curStaId=="start"){
		$("#targetNum_"+i).numberbox({
			required: true
		});
	    $("#box_"+i).numberbox({
			required: true
		});
		$('#theYear_'+i).combobox({
		valueField : 'itemName',
		textField : 'itemName',
		data : [
			{'itemName' : '2013', 'itemName' : '2013'},
			{'itemName' : '2014', 'itemName' : '2014'},
			{'itemName' : '2015', 'itemName' : '2015'}
		],
		editable : false,
		required: true
	});
	$('#theMonth_'+i).combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : '1', 'flagText' : '一月'},
			{'flagValue' : '2', 'flagText' : '二月'},
			{'flagValue' : '3', 'flagText' : '三月'},
			{'flagValue' : '4', 'flagText' : '四月'},
			{'flagValue' : '5', 'flagText' : '五月'},
			{'flagValue' : '6', 'flagText' : '六月'},
			{'flagValue' : '7', 'flagText' : '七月'},
			{'flagValue' : '8', 'flagText' : '八月'},
			{'flagValue' : '9', 'flagText' : '九月'},
			{'flagValue' : '10', 'flagText' : '十月'},
			{'flagValue' : '11', 'flagText' : '十一月'},
			{'flagValue' : '12', 'flagText' : '十二月'}
		],
		editable : false,
		required: true
	});
	}	
$('#id_'+i).combogrid({
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
		onChange : function() {
			document.getElementById("kunnr_"+i).value = $('#id_'+i).combobox("getValue");
			//document.getElementById("id_"+i).value = $('#id_'+i).combobox("getText");
		},
		toolbar : '#Toolbar_'+i
	});
	$('#wid_'+i).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// 是否分页
		collapsible : false,// 是否可折叠的
		method : 'post',
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, 
		{
			field : 'matnr',
			title : '物料号',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '品项or四级科目（SKU）ID',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '品项or四级科目（SKU）',
			align : 'center',
			width : 100
		}] ],
		onChange : function() {
			//document.getElementById("wid_"+i).value = $('#wid_'+i).combobox("getText");
			document.getElementById("matnr_"+i).value = $('#wid_'+i).combobox("getValue");		
		},
		toolbar : '#Toolbar1_'+i
	});


	
}

function addRow(){
	i++;
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#f4f4f4\" >";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#ffffff\" >";	
			
	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"id_"
			+ i
			+ "\" type=\"text\" />"
			+ "<td style=\"text-align: center\"><input id=\"kunnr_"
			+ i
			+ "\" type=\"text\"  style=\"width:80px\" /></td>"
			+ "<td style=\"text-align: center\"><input style=\"width:80px\" id=\"theYear_"  
			+ i
			+ "\" type=\"text\" /></td>"+
			 "</td>"
			+ "<td style=\"text-align: center\"><input   style=\"width:80px\" id=\"theMonth_"  
			+ i
			+"\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"wid_"
			+ i+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"matnr_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"targetNum_" 
			+ i
			+ "\"class=\"easyui-numberbox\"  precision=\"4\" type=\"text\" style=\"width:80px\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"box_" 
			+ i
			+ "\"class=\"easyui-numberbox\"  precision=\"2\" type=\"text\" style=\"width:80px\" /></td>"
			
	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler(i);	
//	alert(i);
}

function removeRow() {
	for(var k = 0; k <= i;k++){
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}	
	}
	$('#item_all').attr('checked', false);
}

function search(value, index){
	value = encodeURIComponent(value);
	$('#id_'+index).combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + value
	});
	$('#id_'+index).combogrid("grid").datagrid('reload');
}
function search1(value, index){
	value = encodeURIComponent(value);
	$('#wid_'+index).combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + value
	});
	$('#wid_'+index).combogrid("grid").datagrid('reload');
}
//function searcher1(val) {
//	val = encodeURIComponent(val);
//	$('#wid').combogrid({
//		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
//	});
//	$('#wid').combogrid("grid").datagrid('reload');
//}
//
//function searcher(val) {
//	val = encodeURIComponent(val);
//	$('#id').combogrid({
//		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
//	});
//	$('#id').combogrid("grid").datagrid('reload');
//
//}


function save() {
	if(curStaId=="start"){
		//var m = $('#costCenter').combogrid('isValid');
		var idArr = new Array();
		var kunnrArr = new Array();
		var yearArr = new Array();
		var monthArr = new Array();
		var widArr = new Array();
		var targetArr = new Array();
		var boxArr = new Array();
		var detailStr = "";
		for(var r=0;r<=i;r++){
			if(!checkInOutsideArr(r)){
				idArr.push($("#id_"+r).combobox('isValid'));
				widArr.push($("#wid_"+r).combobox('isValid'));
				targetArr.push($("#targetNum_"+r).numberbox('isValid'));	
				boxArr.push($("#box_"+r).numberbox('isValid'));	
			}
		}
		if (!( checkBooleanArr(idArr) 
		&& checkBooleanArr(widArr)
		&& checkBooleanArr(targetArr)
		&& checkBooleanArr(boxArr))) {
			return;
		}
	}	
	var detailStr = "";
	detailStr += "[";
	var x = 0;
	for(var j=0; j<=i; j++){
		if(!checkInOutsideArr(j)){
			x++;
			detailStr += "{"
			if(curStaId=="start"){
				detailStr +="\"custId\" : \"" +$("#kunnr_"+j).val()+ "\",";
				detailStr +="\"custNameZH\" : \"" +$("#id_"+j).combobox("getText")+ "\",";
			}else{
				detailStr += "\"custId\" : \"" +$("#kunnr_"+j).val()+ "\",";
				detailStr +="\"custNameZH\" : \"" +$("#id_"+j).combobox("getText")+ "\",";
			}
			detailStr += "\"theYear\" : \"" +$("#theYear_"+j).combobox("getValue")+ "\","
				+ "\"theMonth\" : \"" +$("#theMonth_"+j).combobox("getValue")+ "\","
				+ "\"bezei\" : \"" +$("#wid_"+j).combobox("getText")+ "\","
				+ "\"matter\" : \"" +$("#matnr_"+j).val()+ "\","
				+ "\"targetNum\" : \"" +$("#targetNum_"+j).val()+ "\","
				+ "\"orgId\" : \"" +$("#orgId").val()+ "\","
				+ "\"box\" : \"" +$("#box_"+j).val()+ "\""
				+ "},";
		}
	}
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	   var form = window.document.forms[0];
		form.action =appUrl + "/goal/goalAction!updateGoalForm.jspa" ;
		form.target = "hideFrame1";
		form.submit();
}
function exportGoal(){
$.messager
			.confirm(
					'Confirm',
					'是否导出目标值?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ "/goal/goalAction!exportGoalCsv.jspa";
							form.target = "hideFrame1";
							form.submit();
						}
					});
}
function checkAll() {
	for ( var k = 0; k <= i; k++) {
		if(!checkInOutsideArr(k)){
			$('#item_' + k).attr('checked',
				($('#item_all').attr('checked') == 'checked'));
		}
	}
}

function importGoal() {
	    $.ajax({
					type : "post",
					async : false,
					url : appUrl + "/goalAction!selectNexUser.jspa?time="
							+ new Date(),
					data : {
						userId : $("#userId").val()
					},
					success : function(obj) {
//					nextUserHtml(obj);
						var subFolders= $('#subFolders').val();
						 
					 html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
						+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' 
						+  '<table><tr>'
						+  '<td class="head" noWrap>批量导入</td>'
						+  '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
						+  '</tb></tr>'
						+ '<input type="hidden" id="subFolders" name="subFolders" value="'+subFolders+'" />'
						+ '<td><input  id="nextUsers" type="hidden" name="nextUserId" style="width:150px">';
//		      		$.each(obj.result, function(i, v) {
//			  			html += '<option value="' + v.userId + '">' + v.userName + '----'+ v.stationName + '</option>';
//		     		});
					//	processInstanceId = obj.processInstanceId
					}
				});
				
					html+='</select></td></tr></table>';
	 openImportDialog('导入目标', html);
}

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
					.height($('#excelDialog').height() - 80);
		},
		buttons : [{
					text : '确定',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						var nextUsers = document.getElementById('nextUsers').value;
						if (/^.+\.(xls|xlsx)$/.test(file)) {
							$("#nextUserId").val($("#nextUsers").val());
							$.messager.progress();
							var form = document.getElementById('fileForm');
							form.action = appUrl
									+ "/goal/goalAction!updateXMLGoal.jspa?eventId="+$('#eventId').val();
							form.target = "hideFrame1";
							form.submit();  
						} else {
							$.messager.alert("提示", "请导入Excel文件");
						}

					}
				}, {
					text : '取消',
					handler : function() {
						$('#excelDialog').dialog('close');
					}
				}],

		width : document.documentElement.clientWidth * 0.30,
		height : document.documentElement.clientHeight * 0.60
	});
}
function checkBooleanArr(arr){
	var flag = true;
	for(var t=0;t<arr.length;t++){
		if(!arr[t]){
			flag = false;
			break;
		}
	}
	return flag;
}

function checkInOutsideArr(index){
	var flag = false;
	for(var a=0;a<outsideArr.length;a++){
		if(outsideArr[a]==index){
			flag = true;
			break;
		}
	}
	return flag;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame1");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}