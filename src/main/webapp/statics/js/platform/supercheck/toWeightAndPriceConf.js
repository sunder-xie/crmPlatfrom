//var parentRoles = [
//		[ "departmentManager", "departmentDirector", "fenguanfuzong",
//				"financeManager_HQ", "engineering", "hrManager_approval",
//				"technologyManager", "SCM", "financeDirector",
//				"purchaseDirector", "dongshizhang" ],
//		[ "部门经理", "部门总监", "分管副总", "财务", "工程部负责人", "人资部负责人", "技术中心", "供应链",
//				"财务副总", "采购副总", "董事长s" ] ];
//var subRoles = [
//		[ "departmentManager", "fiance_manager", "changzhang", "fenguanfuzong",
//				"engineering", "hrManager_approval", "technologyManager",
//				"financeDirector", "purchaseDirector", "dongshizhang" ],
//		[ "部门经理", "财务经理", "子公司厂长", "分管副总", "工程部负责人", "人资部负责人", "技术中心", "财务副总",
//				"采购副总", "董事长" ] ];

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

//var curRoles = new Array();
var columns = new Array();
$(document).ready(function() {
//	$('#companyType').combobox({
//		valueField : 'id',
//		textField : 'text',
//		width : 100,
//		data : [ {
//			id : 'parentCompany',
//			text : '总公司'
//		}, {
//			id : 'subsidiary',
//			text : '分工厂'
//		} ],
//		multiple : false,
//		editable : false,
//		panelHeight : 'auto',
//		value : 'parentCompany',
//		onLoadSuccess : function() {
//			curRoles = parentRoles;
//		},
//		onChange : function(newValue, oldValue) {
//			// 增加标题行
//			if ("parentCompany" == newValue) {
//				curRoles = parentRoles;
//			} else {
//				curRoles = subRoles;
//			}
//			createColumn();
//			loadgrid();
//		}
//	});
//	$('#catName').combobox({
//		valueField : 'catId',
//		textField : 'catName',
//		width : 130,
//		multiple : false,
//		editable : false,
//		panelHeight : 'auto',
//		url : appUrl + '/purchase/purchaseAction!getCategoryList.jspa',
//		onLoadSuccess : function(data) {
//
//		},
//		onChange : function(newValue, oldValue) {
//			$('#itemName').combogrid('setValue', '');
//			var queryParams = $('#itemName').combogrid('options').queryParams;
//			queryParams.catId = newValue;
//			$('#itemName').combogrid('grid').datagrid('load');
//		}
//	});
//	$('#itemName').combogrid({
//		panelWidth : 290,
//		panelHight : 500,
//		width : 130,
//		idField : 'itemId',
//		textField : 'itemName',
//		pagination : true,
//		rownumbers : true,
//		fit : true,
//		method : 'post',
//		multiple : false,
//		editable : false,
//		url : appUrl + '/purchase/purchaseAction!getItemsList.jspa',
//		columns : [ [ {
//			field : 'itemId',
//			title : 'ID',
//			hidden : true
//		}, {
//			field : 'itemName',
//			title : '项目名称',
//			width : 220,
//			align : 'center'
//		} ] ],
//
//		onLoadSuccess : function() {
//
//		}
//	});
	$('#hideFrame').bind('load', promgtMsg);

	// 获取口味
	getKw();

	createColumn();
	loadgrid();
});

function appendRow(){
//	$('#myTab').datagrid('appendRow', {         
//        "checkChannelName": 'new name',
//        "A01": 30                
//    });
	
	$('#myTab').datagrid('appendRow',{isNewRecord:true});
}

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
			$('#myTab').datagrid('selectRow', index).datagrid('beginEdit', index);
			
			
			editIndex = index;
		}

	}
	if (editedRows.indexOf(index)<0){
		editedRows.push(index);
		var row = $('#myTab').datagrid('getData').rows[index];
		var channel ={}; channel.checkChannelName = encodeURIComponent(row.checkChannelName);
		channelList.push(channel);
	}
	
}

function loadgrid() {
	$('#myTab')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						method : 'post',
						url : appUrl + '/supercheck!getWeightAndPrices.jspa?'
								+ new Date(),
						loadMsg : '数据远程载入中,请等待...',
						striped : true,
						fit : false,
						// height : 'auto',
						pagination : true,
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
						}

					});
}
function createColumn() {
	columns.length = 0;
	var col1 = {};
	col1["title"] = "考核渠道";
	col1["field"] = "checkChannelName";
	col1["width"] = 80;
	col1["align"] = "center";

	var col2 = {};
	col2["title"] = "铺货权重(%)";
	col2["field"] = "phWeight";
	col2["width"] = 80;
	col2["align"] = "center";
	col2["editor"] = {};
	col2["editor"]["type"] ="numberbox";
//	col2["editor"]["options"]={};
//	col2["editor"]["options"]["precision"]=2;

	var col3 = {};
	col3["title"] = "陈列权重(%)";
	col3["field"] = "clWeight";
	col3["width"] = 80;
	col3["align"] = "center";
	col3["editor"] = {};
	col3["editor"]["type"] ="numberbox";
//	col3["editor"]["options"]={};
//	col3["editor"]["options"]["precision"]=2;

	columns.push(col1);
	columns.push(col2);
	columns.push(col3);

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
		column["editor"]["type"] ="numberbox";
		column["editor"]["options"]={};
		column["editor"]["options"]["precision"]=2;

		column["formatter"] = function(value, row, index) {
			for (var j = 0; j < row.matgs.length; j++) {
				if (row.matgs[j] == (this.field)) {
//					return this.title;
					return row.prices[j];
				}
			}
			return null;
		};

		columns.push(column);
	}
	var col5 = {};
	col5["title"] = "创建人";
	col5["field"] = "creator";
	col5["width"] = 80;
	col5["align"] = "center";
	columns.push(col5);
}

function validateWeight(){
//	var rows = $('#myTab').datagrid('getData').rows;
	var rows = $('#myTab').datagrid('getRows')//获取当前的数据行

	var phPercent =0;
	var clPercent =0;
	
	var flag =true;
	
	for (var i = 0 ; i< rows.length ;i++){
		if (rows[i]["phWeight"]==undefined || rows[i]["clWeight"]==undefined){
			$.messager.alert('Tips', "铺货权重 陈列权重不能为空！", 'info');
			return false;
		}
		else {
		phPercent = Number(rows[i]["phWeight"])+phPercent;
		clPercent= clPercent+ Number(rows[i]["clWeight"]);
		}
	}
	if (phPercent !=100 ){
		$.messager.alert('Tips', "铺货权重总和必须100%", 'info');
		flag =false;
	}
	if (clPercent !=100 ){
		$.messager.alert('Tips', "陈列权重总和必须100%", 'info');
		flag = false;
	}
	return flag;
	
}

function save(){
//	var rows = $('#myTab').datagrid('getChanges');
//	alert(rows.length+' rows are changed!');
//	alert(editedRows);
	

	
	
	$('#myTab').datagrid('acceptChanges');
	
	// 验证权重
	var validateflag = validateWeight();
	if (validateflag){
	
		$.messager.progress();
	var weightprices=[];
	
	    // 
	for (var i = 0;i<editedRows.length;i++){
		var editedRowsIndex = editedRows[i];
		var row = $('#myTab').datagrid('getData').rows[editedRowsIndex];
		
		for (var j =0;j<kwArr.length;j++){
			
				var weightprice ={}; 
				weightprice.checkChannelName = encodeURIComponent(row.checkChannelName);
				weightprice.phWeight = row.phWeight;
				weightprice.clWeight = row.clWeight;
				
				var matg = kwArr[j].matg;
				weightprice.matg = matg;
				weightprice.price = row[matg];
				
				weightprices.push(weightprice);
				
		}
		
	}
	// ajax 提交weightprices
	$.ajax({
		   type: "POST",
		   url : appUrl + '/supercheck!saveWeightprices.jspa?'+new Date(),
		   data: {
			      "weightAndPriceList":JSON.stringify(weightprices), // 
			      "channelList":JSON.stringify(channelList)
			      },
		  dataType: "json",
		  success: function(msg){
			  $.messager.progress('close');
			  cleanPara();
			   }
		});
	}
	
//	$('#myTab').datagrid('reload');
//	reset();
//	reset();
	
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
			form.action = appUrl + '/supercheck!excelDownload.jspa?'+new Date(),
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