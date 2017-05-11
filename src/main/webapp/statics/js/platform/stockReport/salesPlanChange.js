var indexRow=1;
var inSideArr=new Array();

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

var myDate = new Date();
var month = [];
var year = [];
var y = myDate.getFullYear()-1;
var m = myDate.getMonth()+1;

for ( var i = 0; i < 4; i++) {
	year.push({
		'txt' : y,
		'value' : y
	});
	y++;
}

for ( var i = 1; i <= 12; i++) {
	var mString;
	if(i<10){
		mString="0"+i;
	}else{
		mString=i;
	}
	month.push({
		'txt' : mString,
		'value' : mString
	});
}

$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	init();
});

function init(){
	
}

/**
 * 添加行
 */
function addRow() {
	inSideArr.push(indexRow);
	var htmlHead_1 = "<tr id=\"tr_" + indexRow
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + indexRow
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";
	var htmlTr = "";
	
	htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
		+ indexRow
		+ "\" type=\"checkbox\" /></td>"
		+ "<td nowrap style=\"text-align: center;width:10%\"><input id=\"OorgName_"
		+ indexRow
		+ "\" type=\"text\" style=\"width:60%\" readonly />&nbsp;"
		+ "<a id=\"Obtn_"
		+ indexRow +"\" href=\"javascript:choseOrgTo(" 
		+ indexRow +")\">选择</a>&nbsp;</td>"
		+ "<input name=\"orgIdTos\" id=\"OorgId_"
		+ indexRow
		+ "\" type=\"hidden\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Okunnr_" 
		+ indexRow
		+ "\"  type=\"text\" name=\"kunnrTos\" readonly style=\"width:90%\"/>"
		+ "<div id=\"OtoolbarKunnr_"+indexRow+"\">"
		+ "<input id=\"OsearchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherKunnrO\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Oyear_" 
		+ indexRow
		+ "\" name=\"yearTos\"  type=\"text\" readonly style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Omonth_" 
		+ indexRow
		+ "\" name=\"monthTos\"  type=\"text\" readonly style=\"width:90%\" /></td>"
		
		+ "<td nowrap style=\"text-align: center;width:10%\"><input id=\"IorgName_"
		+ indexRow
		+ "\" type=\"text\" style=\"width:60%\" readonly />&nbsp;"
		+ "<a id=\"Ibtn_"
		+ indexRow +"\" href=\"javascript:choseOrg(" 
		+ indexRow +")\">选择</a>&nbsp;</td>"
		+ "<input name=\"orgIds\" id=\"IorgId_"
		+ indexRow
		+ "\" type=\"hidden\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Ikunnr_" 
		+ indexRow
		+ "\"  type=\"text\" name=\"kunnrs\" readonly style=\"width:90%\"/>"
		+ "<div id=\"ItoolbarKunnr_"+indexRow+"\">"
		+ "<input id=\"IsearchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherKunnrI\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Iyear_" 
		+ indexRow
		+ "\" name=\"years\"  type=\"text\" readonly style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Imonth_" 
		+ indexRow
		+ "\" name=\"months\"  type=\"text\" readonly style=\"width:90%\" /></td>"

		+ "<td style=\"text-align: center;width:15%\"><input id=\"skuId_" 
		+ indexRow
		+ "\" name=\"skuIds\"  type=\"text\" readonly />"
		+ "<div id=\"toolbarSku_"+indexRow+"\">"
		+ "<input id=\"searchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherSku\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:10%\"><input id=\"money_" 
		+ indexRow
		+ "\" name=\"quantitys\"  type=\"text\" style=\"width:90%\" /></td>"
		+ "</tr>";
			
	var htmlData = "";
	if (indexRow % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler();
	indexRow++;
}

function addImportRow(val) {
	inSideArr.push(indexRow);
	var htmlHead_1 = "<tr id=\"tr_" + indexRow
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + indexRow
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";
	var htmlTr = "";
	
	htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
		+ indexRow
		+ "\" type=\"checkbox\" /></td>"
		+ "<td nowrap style=\"text-align: center;width:10%\"><input id=\"OorgName_"
		+ indexRow
		+ "\" type=\"text\" value=\""+val.orgNameTo+"\" style=\"width:60%\" readonly />&nbsp;</td>"
		+ "<input name=\"orgIdTos\" value=\""+val.orgIdTo+"\" id=\"OorgId_"
		+ indexRow
		+ "\" type=\"hidden\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Okunnr_" 
		+ indexRow
		+ "\"  type=\"text\" name=\"kunnrTos\" value=\""+val.kunnrTo+"\" readonly style=\"width:90%\"/>"
		+ "<div id=\"OtoolbarKunnr_"+indexRow+"\">"
		+ "<input id=\"OsearchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherKunnrO\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Oyear_" 
		+ indexRow
		+ "\" name=\"yearTos\" value=\""+val.yearTo+"\" type=\"text\" readonly style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Omonth_" 
		+ indexRow
		+ "\" name=\"monthTos\" value=\""+val.monthTo+"\" type=\"text\" readonly style=\"width:90%\" /></td>"
		
		+ "<td nowrap style=\"text-align: center;width:10%\"><input id=\"IorgName_"
		+ indexRow
		+ "\" type=\"text\" value=\""+val.orgName+"\" style=\"width:60%\" readonly />&nbsp;</td>"
		+ "<input name=\"orgIds\" value=\""+val.orgId+"\" id=\"IorgId_"
		+ indexRow
		+ "\" type=\"hidden\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Ikunnr_" 
		+ indexRow
		+ "\"  type=\"text\" name=\"kunnrs\" value=\""+val.kunnr+"\" readonly style=\"width:90%\"/>"
		+ "<div id=\"ItoolbarKunnr_"+indexRow+"\">"
		+ "<input id=\"IsearchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherKunnrI\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Iyear_" 
		+ indexRow
		+ "\" name=\"years\" value=\""+val.year+"\" type=\"text\" readonly style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Imonth_" 
		+ indexRow
		+ "\" name=\"months\" value=\""+val.month+"\" type=\"text\" readonly style=\"width:90%\" /></td>"

		+ "<td style=\"text-align: center;width:15%\"><input id=\"skuId_" 
		+ indexRow
		+ "\" name=\"skuIds\" value=\""+val.skuId+"\" type=\"text\" readonly />"
		+ "<div id=\"toolbarSku_"+indexRow+"\">"
		+ "<input id=\"searchBox_"+indexRow+"\" class=\"easyui-searchbox\" searcher=\"searcherSku\" prompt=\"请输入客户名称\" style=\"width:300px\" />"
		+ "</div></td>"
		+ "<td style=\"text-align: center;width:10%\"><input id=\"money_" 
		+ indexRow
		+ "\" name=\"quantitys\" value=\""+val.quantity+"\" type=\"text\" style=\"width:90%\" /></td>"
		+ "</tr>";
			
	var htmlData = "";
	if (indexRow % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addImportHandler();
	indexRow++;
}

function addHandler(){
	$("#OsearchBox_"+indexRow).searchbox();
	$("#IsearchBox_"+indexRow).searchbox();
	$("#searchBox_"+indexRow).searchbox();
	
	$('#Okunnr_'+indexRow).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		idField : 'kunnr',
		textField : 'name1',
		disabled : true,
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#OtoolbarKunnr_'+indexRow
	});
	
	$('#Ikunnr_'+indexRow).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		idField : 'kunnr',
		textField : 'name1',
		disabled : true,
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#ItoolbarKunnr_'+indexRow
	});
	
	$("#Oyear_" + indexRow).combobox({
		data : year,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Omonth_" + indexRow).combobox({
		data : month,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Iyear_" + indexRow).combobox({
		data : year,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Imonth_" + indexRow).combobox({
		data : month,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$('#skuId_' + indexRow).combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				editable : false,
				url : appUrl
						+ '/stockReportAction!searchSku.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : '品项编号',
					width : 60
				}, {
					field : 'skuName',
					title : '品项名称',
					width : 200
				} ] ],
				toolbar : '#toolbarSku_'+indexRow
			});
	
	var queryParams = $('#skuId_'+indexRow).combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = "";
	$('#skuId_'+indexRow).combogrid("grid").datagrid('reload');
}

function addImportHandler(){
	$("#OsearchBox_"+indexRow).searchbox();
	$("#IsearchBox_"+indexRow).searchbox();
	$("#searchBox_"+indexRow).searchbox();
	
	$('#Okunnr_'+indexRow).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		pageSize : 1000,
		pageList : [1000],
		method : 'post',
		singleSelect : true,
		multiple : false,
		disabled : false,
		editable : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#OorgId_'+indexRow).val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#OtoolbarKunnr_'+indexRow
	});
	
	$('#Ikunnr_'+indexRow).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		pageSize : 1000,
		pageList : [1000],
		method : 'post',
		singleSelect : true,
		multiple : false,
		disabled : false,
		editable : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#IorgId_'+indexRow).val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#ItoolbarKunnr_'+indexRow
	});
	
	$("#Oyear_" + indexRow).combobox({
		data : year,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Omonth_" + indexRow).combobox({
		data : month,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Iyear_" + indexRow).combobox({
		data : year,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$("#Imonth_" + indexRow).combobox({
		data : month,
		valueField : 'txt',
		textField : 'value',
		editable : false,
		onSelect : function(r) {
			var id=$(this).attr('id');
			var ids=id.split('_');
		}
	});
	
	$('#skuId_' + indexRow).combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				pageSize : 1000,
				pageList : [1000],
				method : 'post',
				singleSelect : false,
				multiple : false,
				editable : false,
				url : appUrl
						+ '/stockReportAction!searchSku.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : '品项编号',
					width : 60
				}, {
					field : 'skuName',
					title : '品项名称',
					width : 200
				} ] ],
				toolbar : '#toolbarSku_'+indexRow
			});
	
	var queryParams = $('#skuId_'+indexRow).combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = "";
	$('#skuId_'+indexRow).combogrid("grid").datagrid('reload');
}

//选择组织
function choseOrgTo(k){
	orgFlag = 1;
	kValue=k;
	initMaintAccount('400','400','组织选择', '/questionAction!orgTreePage.jspa');
}
function choseOrg(k){
	orgFlag = 2;
	kValue=k;
	initMaintAccount('400','400','组织选择', '/questionAction!orgTreePage.jspa');
}

//组织返回
function returnValue(selectedId, selectedName) {
	if(orgFlag == 1){
		$("#OorgId_"+kValue).val(selectedId);
		$("#OorgName_"+kValue).val(selectedName);
		loadOKunnr(kValue);
	}else if(orgFlag == 2){
		$("#IorgId_"+kValue).val(selectedId);
		$("#IorgName_"+kValue).val(selectedName);
		loadIKunnr(kValue);
	}
}

function loadOKunnr(k){
	$('#Okunnr_'+k).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		disabled : false,
		editable : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#OorgId_'+k).val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#OtoolbarKunnr_'+k
	});
	
	var queryParams = $('#Okunnr_'+k).combogrid("grid").datagrid('options').queryParams;
	queryParams.orgId = $('#OorgId_'+k).val();
	queryParams.name1 = "";
	$('#Okunnr_'+k).combogrid("grid").datagrid('reload');
}

function loadIKunnr(k){
	$('#Ikunnr_'+k).combogrid({
		panelHeight : 250,
		panelWidth : 380,
		width : 150,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		disabled : false,
		editable : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#IorgId_'+k).val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#ItoolbarKunnr_'+k
	});
	
	var queryParams = $('#Ikunnr_'+k).combogrid("grid").datagrid('options').queryParams;
	queryParams.orgId = $('#IorgId_'+k).val();
	queryParams.name1 = "";
	$('#Ikunnr_'+k).combogrid("grid").datagrid('reload');
}

function searcherKunnrO(itemName) {
	var id=$(this).attr('id');
	var ids=id.split('_');
	var queryParams = $('#Okunnr_'+ids[1]).combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(itemName);
	$('#Okunnr_'+ids[1]).combogrid("grid").datagrid('reload');
}

function searcherKunnrI(itemName) {
	var id=$(this).attr('id');
	var ids=id.split('_');
	var queryParams = $('#Ikunnr_'+ids[1]).combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(itemName);
	$('#Ikunnr_'+ids[1]).combogrid("grid").datagrid('reload');
}

function searcherSku(itemName) {
	var id=$(this).attr('id');
	var ids=id.split('_');
	var queryParams = $('#skuId_'+ids[1]).combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(itemName);
	$('#skuId_'+ids[1]).combogrid("grid").datagrid('reload');
}

function initMaintAccount(wWidth, wHeight, title, url) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var $win = $("#mainFrame")
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});
	$win.window('open');
}

/**
 * 删除行
 */
function removeRow() {
	for(var i=1;i<indexRow;i++){
		if ($('#item_' + i).attr('checked') == 'checked') {
			$("#tr_" + i).remove();
			inSideArr.remove(i);
		}
	}
}

function importRow() {
	if(inSideArr.length>0){
		$.messager.alert("提示", "请先删除已有行项目，再做导入操作！");
		return;
	}
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importStockCsv('批量导入', html);
}

//csv下载导入模板
function exportCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportSalesPlanChangeCsv.jspa';
			form.submit();
		}
	});
}

//批量导入信息
function importStockCsv(t, html) {
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
											openTime();
											var form = document.getElementById('fileForm');
											form.action = appUrl
													+ "/stockReportAction!importSalesPlanChangeCsv.jspa";
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

function checkAll() {
	for(var i=0;i<inSideArr.length;i++){
		$('#item_' + inSideArr[i]).attr('checked', ($('#item_all').attr('checked') == 'checked'));
	}
}

function submit(){
	if($("#title").val()==""){
		$.messager.alert('Tips', "标题不能为空!", 'warning');
		return;
	}
	if(inSideArr==null || inSideArr.length==0){
		$.messager.alert('Tips', "请添加明细!", 'warning');
		return;
	}
	
	for(var i=0;i<inSideArr.length;i++){
		if($("#Okunnr_"+inSideArr[i]).combobox('getValue')=="" || $("#Ikunnr_"+inSideArr[i]).combobox('getValue')==""){
			$.messager.alert('Tips', "第"+(i+1)+"行经销商不能为空!", 'warning');
			return;
		}
		if($("#Oyear_"+inSideArr[i]).combobox('getValue')=="" || $("#Iyear_"+inSideArr[i]).combobox('getValue')==""){
			$.messager.alert('Tips', "第"+(i+1)+"行年份不能为空!", 'warning');
			return;
		}
		if($("#Omonth_"+inSideArr[i]).combobox('getValue')=="" || $("#Imonth_"+inSideArr[i]).combobox('getValue')==""){
			$.messager.alert('Tips', "第"+(i+1)+"行月份不能为空!", 'warning');
			return;
		}
		if($("#skuId_"+inSideArr[i]).combobox('getValue')==""){
			$.messager.alert('Tips', "第"+(i+1)+"行品项不能为空!", 'warning');
			return;
		}
		if($("#money_"+inSideArr[i]).val()==""){
			$.messager.alert('Tips', "第"+(i+1)+"行数量不能为空!", 'warning');
			return;
		}
	}
	
	$('#dialogWindow').dialog({    
	    title: '流程说明',    
	    width: 800,    
	    height: 200,
	    top: 50,
	    closed: false,    
	    cache: false,
	    buttons:[{
			text:'确定',
			handler:function(){
				$("#dialogWindow").dialog('close');
				toEvent();
			}
		}],
	    content: "半自动流程提交后，需手工指定每一环节的处理人（由上往下依次处理）</br></br>" +
	           "本流程处理节点如下（<font color='red'><b>注：节点必须按以下流程节点流转至财务，否则分销数据将不做调整</b></font>）</br></br>"+
	           "<font color='red'><b>注：流程最后节点必须为财务分析师：薛晶晶！</b></font></br></br>"+
	           "<font color='red'><b>同一省区流程流转</b></font>：调入方客户经理--调入方高级经理--调出方客户经理--调出方高级经理--省级经理--财务分析师</br></br>"+
	           "<font color='red'><b>不同省区流程流转</b></font>：调入方客户经理--调入方高级经理--调出方客户经理--调入方省级经理--调出方高级经理--省级经理--财务分析师 </br></br>"
	});
}

function toEvent(){
	initMaintEvent('半自动化流程绘制',
			'/stockReportAction!toSemiAutomatic.jspa', 750,
			450);
}

function accMul(arg1,arg2){//乘法
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function accDiv(arg1,arg2){//除法
	  var t1=0,t2=0,r1,r2;
	  try{t1=arg1.toString().split(".")[1].length}catch(e){}
	  try{t2=arg2.toString().split(".")[1].length}catch(e){}
	  with(Math){
	   r1=Number(arg1.toString().replace(".",""))
	   r2=Number(arg2.toString().replace(".",""))
	   return (r1/r2)*pow(10,t2-t1);
	  }
}

function accAdd(arg1,arg2){//加法
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m+arg2*m)/m
}

function accSub(arg1,arg2){//减法
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m-arg2*m)/m
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult,function(r){
			if (r) {
				var str=successResult.split("！");
				if(str!=null && str.length>1){
					if(str[0]=='导入成功'){
						$('#excelDialog').dialog('close');
						loadImportData();
					}
				}
				var str1=successResult.split(",");
				if(str1!=null && str1.length>1){
					if(str1[0]=='事务启动成功'){
						window.location.reload();
					}
				}
			}
		});
	}
}

function loadImportData(){
	$.ajax({
		async : false,
		type : "post",
		url : appUrl + "/stockReportAction!getImportSalesPlanChangeDetail.jspa",
		success : function(data) {
			$.each(data,function(k,v){
				addImportRow(v);
			});
		}
	});
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

function cencel() {
	window.parent.closeMaintWindow();
}

//创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	if(WWidth==0 && WHeight==0){
		var $win1 = $("#maintWindow")
		.window(
				{
					title : title,
					fit : true,
					content : '<iframe frameborder="no" width="100%" height="100%" src='
						+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						left : 0,
						top : 0
						//
				});
		$win1.window('open');
	}else{
		var $win2 = $("#maintWindow")
		.window(
				{
					title : title,
					width : WWidth,
					height : WHeight,
					content : '<iframe frameborder="no" width="100%" height="100%" src='
						+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
						//
				});
		$win2.window('open');
	}

}

function initMaintEvent(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintWindow")
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});
	$win.window('open');
}

function closeMaintEvent() {
	$("#maintWindow").window('close');
	handelAction('createSalesPlanChange');
}

function handelAction(action) {
    var form = window.document.forms[0];
	form.action = appUrl + "/stockReportAction!" + action + ".jspa";
	form.target = "hideFrame";
	form.submit();
}

//关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function closeOrgTree() {
	$("#mainFrame").window('close');
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		return false;
	}
	return true;
};
