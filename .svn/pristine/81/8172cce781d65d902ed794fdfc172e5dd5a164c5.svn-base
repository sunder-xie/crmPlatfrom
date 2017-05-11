
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
    	if (this[i] == val) return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
// 口味
var kwArr = [];
var editIndex = undefined;
var editedRows =  new Array();
var channelList = [];
var url ="";

//var curRoles = new Array();
var columns = new Array();
$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	 
	$('#dlg').dialog('close') ;
	

	// 获取口味
	getKw();

	createColumn();
	loadgrid();
});

function getKw() {
	$.ajax({
		type : "post",
		url : appUrl + '/supercheck!getMateriels.jspa',
		async : false,
		success : function(data) {
			kwArr = data.rows;
		}
	});

}

function onClickRow(index) {
	
	var row = $('#myTab').datagrid('getData').rows[index];
	
	if (editIndex == undefined) {
		$('#myTab').datagrid('selectRow', index).datagrid('beginEdit', index);
		editIndex = index;
		
		
	} else {
		if (editIndex!=index){
			// 结束原来行编辑 ，开启新行编辑
//			$('#myTab').datagrid('endEdit', editIndex);
			$('#myTab').datagrid('acceptChanges');
			$('#myTab').datagrid('selectRow', index).datagrid('beginEdit', index);
			
			
			editIndex = index;
		}

	}
	if (editedRows.indexOf(index)<0){
		editedRows =[];
		editedRows.push(index);
	
	}
	
}

function loadgrid() {
	$('#myTab')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						method : 'post',
						url : appUrl + '/supercheck!getAllitemConfs.jspa?'
								+ new Date(),
						loadMsg : '数据远程载入中,请等待...',
						striped : true,
						fit : false,
						// height : 'auto',
						pagination : true,
						height: 400,
						singleSelect : true,
						onClickRow : onClickRow,
						pageSize : 10,
						rownumbers : true,
//						queryParams : {
//							comTypeId : $('#companyType').combobox('getValue'),
//							itemId : $('#itemName').combogrid('getValue')
//						},
						columns : [ columns ],
						loadFilter : function(data) {
							if (typeof data.length == 'number'
									&& typeof data.splice == 'function') { // is
																			// array
								data = {
									total : data.length,
									rows : data
								}
							}
							var dg = $(this);
							var opts = dg.datagrid('options');
							var pager = dg.datagrid('getPager');
							pager.pagination({
								onSelectPage : function(pageNum, pageSize) {
									opts.pageNumber = pageNum;
									opts.pageSize = pageSize;
									pager.pagination('refresh', {
										pageNumber : pageNum,
										pageSize : pageSize
									});
									dg.datagrid('loadData', data);
								}
							});
							if (!data.originalRows) {
								data.originalRows = (data.rows);
							}
							var start = (opts.pageNumber - 1)
									* parseInt(opts.pageSize);
							var end = start + parseInt(opts.pageSize);
							data.rows = (data.originalRows.slice(start, end));
							return data;
						},
						rowStyler : function(rowIndex, rowData) {
							if (rowIndex % 2 == 1) {
								return 'background-color:#FFDAB9;';
							}
						},
						onLoadSuccess : function(data) {
							// $('#myTab').datagrid({loadFilter:pagerFilter}).datagrid('loadData',
							// data);
							var a =2;
							
							
//							 var phFlagCol = $('#myTab').datagrid('getColumnOption',"phFlag");
//							 phFlagCol.editor.options = "on";
							
						}

					});
}
function createColumn() {
	columns.length = 0;
	
	
	var col0 = {};
	col0["field"] = "ck";
	col0["width"] = 80;
	col0["editor"] = "checkbox";
	
	
	var col1 = {};
	col1["title"] = "综合品项";
	col1["field"] = "allitemName";
	col1["width"] = 80;
	col1["align"] = "center";

	var col2 = {};
	col2["title"] = "铺货考核综合品项";
	col2["field"] = "phFlag";
	col2["width"] = 80;
	col2["align"] = "center";
	col2["editor"] = {};
	col2["editor"]["type"] ="checkbox";
	col2["editor"]["options"]={};
	col2["editor"]["options"]["on"]=1;
	col2["editor"]["options"]["off"]=0;

	var col2 = {};
	col2["title"] = "铺货考核综合品项";
	col2["field"] = "phFlag";
	col2["width"] = 100;
	col2["align"] = "center";
	col2["editor"] = {};
	col2["editor"]["type"] ="checkbox";
	col2["editor"]["options"]={};
	col2["editor"]["options"]["on"]=1;
	col2["editor"]["options"]["off"]=0;
	
	var col3 = {};
	col3["title"] = "陈列考核综合品项";
	col3["field"] = "clFlag";
	col3["width"] = 100;
	col3["align"] = "center";
	col3["editor"] = {};
	col3["editor"]["type"] ="checkbox";
	col3["editor"]["options"]={};
	col3["editor"]["options"]["on"]=1;
	col3["editor"]["options"]["off"]=0;
	
	var col4 = {};
	col4["title"] = "货龄考核综合品项";
	col4["field"] = "hlFlag";
	col4["width"] = 100;
	col4["align"] = "center";
	col4["editor"] = {};
	col4["editor"]["type"] ="checkbox";
	col4["editor"]["options"]={};
	col4["editor"]["options"]["on"]=1;
	col4["editor"]["options"]["off"]=0;
	
	var col5 = {};
	col5["title"] = "价格考核综合品项";
	col5["field"] = "jgFlag";
	col5["width"] = 100;
	col5["align"] = "center";
	col5["editor"] = {};
	col5["editor"]["type"] ="checkbox";
	col5["editor"]["options"]={};
	col5["editor"]["options"]["on"]=1;
	col5["editor"]["options"]["off"]=0;
	

//	columns.push(col0);
	columns.push(col1);
	columns.push(col2);
	columns.push(col3);
	columns.push(col4);
	columns.push(col5);

	// 循环口味
	for (var i = 0; i < kwArr.length; i++) {
		var column = {};
		column["title"] = kwArr[i].bezei40;
		column["field"] = kwArr[i].matg;
		column["width"] = 100;
		column["align"] = "center";
		// column["editor"]="{type:'numberbox',options:{precision:2}}";

		/*
		 * var dEditor = { type : 'numberbox',//根据条件改变 options : { editable :
		 * true , precision:2 } }; column["editor"]=dEditor;
		 */
//		column["editor"] = "numberbox";
//		column["editor"]["precision"] = 2;
		
		column["editor"] = {};
		column["editor"]["type"] ="checkbox";
		column["editor"]["options"]={};
		column["editor"]["options"]["on"]=1;
		column["editor"]["options"]["off"]=0;

		column["formatter"] = function(value, row, index) {
			for (var j = 0; j < row.allitemList.length; j++) {
				if (row.allitemList[j] == (this.field)) {
//					return this.title;
					
					return 1;
				}
			}
			return 0;
		};

		columns.push(column);
	}

}

function del(){
	if (editedRows.length>0){
	var dellineIndex =editedRows[0];
	
	
	$.messager.progress();
	var row = $('#myTab').datagrid('getData').rows[dellineIndex];
	
	$.ajax({
		   type: "POST",
		   url : appUrl + '/supercheck!delAllitemConf.jspa?'+new Date(),
		   data: {
			      "allitemName":encodeURIComponent(row["allitemName"]) // 
			      },
		  dataType: "json",
		  success: function(msg){
			  $.messager.progress('close');
			  $('#myTab').datagrid('reload');	// reload the user data
//			  cleanPara();
			   }
		});
	}else{
		
		$.messager.alert('Tips', '删除前，请先选中一行!', 'warning');
	}
	
	
}



function save(){

if (editedRows.length>0){
	$('#myTab').datagrid('acceptChanges');
	
      var dellineIndex =editedRows[0];
	
	
	$.messager.progress();
	var allitemConfs=[];
	var row = $('#myTab').datagrid('getData').rows[dellineIndex];
	var allitemlist = row["allitemList"];
	for (var i =0 ;i<allitemlist.length;i++){
		
		var poser =allitemlist[i];
		var alltiemsValue = row[poser];
		if (alltiemsValue==0){
			continue;
		}
		
		var allitemConf ={};
		allitemConf.allitemName= encodeURIComponent(row["allitemName"]);
		allitemConf.phFlag =row["phFlag"];
		allitemConf.clFlag =row["clFlag"];
		allitemConf.hlFlag =row["hlFlag"];
		allitemConf.jgFlag =row["jgFlag"];
		allitemConf.allitems = poser;
		
		allitemConfs.push(allitemConf);
		
	}
	
	
	
	
	$.ajax({
		   type: "POST",
		   url : appUrl + '/supercheck!changeAllitemConf.jspa?'+new Date(),
		   data: {
			      "allItemList":JSON.stringify(allitemConfs)
			      },
		  dataType: "json",
		  success: function(msg){
			  $.messager.progress('close');
			  cleanPara();
			   }
		});
	
}else{
	$.messager.alert('Tips', '请先修改再保存!', 'warning');
}
	
	
	
	
}



function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

function excelDownload() {
	$.messager.confirm('Confirm', '是否确定导出?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/supercheck!exportAllitems.jspa?'+new Date(),
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
		}
	});
}

function cleanPara(){
    editIndex = undefined;
    editedRows =  new Array();
    channelList = new Array();
    $('#myTab').datagrid('reload');		   
}


function reject(){
	$('#myTab').datagrid('rejectChanges');
	editIndex = undefined;
}


function search() {
	var queryParams = $('#myTab').datagrid('options').queryParams;
	queryParams.comTypeId = $('#companyType').combobox('getValue');
	queryParams.catId = $('#catName').combobox('getValue');
	queryParams.itemId = $('#itemName').combogrid('getValue');
	$('#myTab').datagrid('load');
}
function reset() {
	window.location.reload();
}

//--------------------------------------
function addAllitemConf(){
	$('#dlg').dialog('open').dialog('setTitle','添加综合品项系列');
	$('#fm').form('clear');
	url = appUrl + '/supercheck!saveAllitemConf.jspa?'+new Date();
}

function submitAllitemConf(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.errorMsg){
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#myTab').datagrid('reload');	// reload the user data
			}
		}
	});
}

