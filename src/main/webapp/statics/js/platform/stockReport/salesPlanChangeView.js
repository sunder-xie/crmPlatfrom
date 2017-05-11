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
var y = myDate.getFullYear();
var m = myDate.getMonth()+1;

for ( var i = 0; i < 3; i++) {
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
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!getSalesPlanChangeDetail.jspa",
		data : { 
			eventId : $("#eventId").val()
		},
		success : function(data) {
			$.each(data,function(k,v){
				addRow(k,v);
			});
		}
	});
}

/**
 * 添加行
 */
function addRow(k,v) {
	var htmlHead_1 = "<tr id=\"tr_" + k
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + k
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";
	var htmlTr = "";
	
	htmlTr = "<td nowrap style=\"text-align: center;width:10%\"><input id=\"OorgName_"
		+ k
		+ "\" type=\"text\" style=\"width:60%\" readonly value=\""+v.orgNameTo+"\" /></td>"
		+ "<input name=\"orgIdTos\" id=\"OorgId_"
		+ k
		+ "\" type=\"hidden\" value=\""+v.orgNameTo+"\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Okunnr_" 
		+ k
		+ "\"  type=\"text\" name=\"kunnrTos\" readonly value=\""+v.kunnrNameTo+"\" style=\"width:90%\"/>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Oyear_" 
		+ k
		+ "\" name=\"yearTos\"  type=\"text\" readonly value=\""+v.yearTo+"\" style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Omonth_" 
		+ k
		+ "\" name=\"monthTos\"  type=\"text\" readonly value=\""+v.monthTo+"\" style=\"width:90%\" /></td>"
		
		+ "<td nowrap style=\"text-align: center;width:10%\"><input id=\"IorgName_"
		+ k
		+ "\" type=\"text\" style=\"width:60%\" value=\""+v.orgName+"\" readonly /></td>"
		+ "<input name=\"orgIds\" id=\"IorgId_"
		+ k
		+ "\" type=\"hidden\" value=\""+v.orgName+"\" />"
		+ "<td style=\"text-align: center;width:15%\"><input id=\"Ikunnr_" 
		+ k
		+ "\"  type=\"text\" name=\"kunnrs\" readonly value=\""+v.kunnrName+"\" style=\"width:90%\"/>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Iyear_" 
		+ k
		+ "\" name=\"years\"  type=\"text\" readonly value=\""+v.year+"\" style=\"width:90%\" /></td>"
		+ "<td style=\"text-align: center;width:3%\"><input id=\"Imonth_" 
		+ k
		+ "\" name=\"months\"  type=\"text\" readonly value=\""+v.month+"\" style=\"width:90%\" /></td>"

		+ "<td style=\"text-align: center;width:15%\"><input id=\"skuId_" 
		+ k
		+ "\" name=\"skuIds\"  type=\"text\" readonly value=\""+v.skuName+"\" style=\"width:90%\" />"
		+ "<td style=\"text-align: center;width:10%\"><input id=\"money_" 
		+ k
		+ "\" name=\"quantitys\"  type=\"text\" style=\"width:90%\" readonly value=\""+v.quantity+"\" /></td>"
		+ "</tr>";
			
	var htmlData = "";
	if (indexRow % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
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
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId=51120&bhxjFlag='+'C',
//		disabled : true,
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
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId=51120&bhxjFlag='+'C',
//		disabled : true,
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

//选择组织
function choseOrgTo(k){
	orgFlag = 1;
	kValue=k;
	initMaintAccount('400','400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa');
}
function choseOrg(k){
	orgFlag = 2;
	kValue=k;
	initMaintAccount('400','400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa');
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

function checkAll() {
	for(var i=0;i<inSideArr.length;i++){
		$('#item_' + inSideArr[i]).attr('checked', ($('#item_all').attr('checked') == 'checked'));
	}
}

function submit(){
	initMaintEvent('半自动化流程绘制',
			'/stockReportAction!toSemiAutomatic.jspa', 750,
			450);
}

function downLoad(fileId) {
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!downLoadFile.jspa?fileId="
			+ fileId;
	form.submit();
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
		$.messager.confirm('Tips', successResult,'info');
	}
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
	
	var form = window.document.forms[0];
	form.action = appUrl + "/stockReportAction!createSalesPlanChange.jspa";
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
