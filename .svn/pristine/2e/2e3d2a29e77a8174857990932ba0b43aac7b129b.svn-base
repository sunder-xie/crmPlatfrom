$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadCust();
});

/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function calcu(id){
	$("#totalPriceDetail_"+id).val(accMul($("#price_"+id).val(),$("#quantity_"+id).val()));
}

function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function createOrder() {
	var form = window.document.forms[0];
	form.action = appUrl + "/order/orderNewAction!createOrderNew.jspa";
	form.target = "hideFrame";
	form.submit();
}

var detailId=0;

function addDetail(){
	detailId++;
	$("#detailList").append('<table width="100%" border="0" id="detail_'+detailId+'">'+
	                         '<tr>'+
			                 '<td class="head" nowrap>品项：</td>'+
		                     '<td id="td_sku_'+detailId+'" width="250px">'+
			                 '<input id="skuId_'+detailId+'" name="skuIds" data-options="required:true,editable:false">'+
//			                 '<span id="sku_sel_'+detailId+'"></span> '+
			                 '<select id="skuUnit_'+detailId+'" name="unitDescs">'+
							 '<option value="箱">箱</option>' +
							 '</select>' +
			                 '</td>'+
			                 '<div id="toolbarSku_'+detailId+'" class="datagrid-toolbar" >'+
			                 '<input id="searchBox_'+detailId+'" class="easyui-searchbox" searcher="searcherSku" prompt="请填写物料名"  style="width:300px" />'+
			                 '</div>'+
		                     '<td class="head" nowrap>单价：</td>'+
		                     '<td >'+
			                 '<input style="width:70px" type="text"  id="price_'+detailId+'" name="prices" class="easyui-validatebox"'+
                             'onblur="calcu('+detailId+')">'+
		                     '</td>'+
                             '<td class="head" nowrap>数量：</td>'+
                             '<td >'+
                             '<input style="width:70px" type="text"  id="quantity_'+detailId+'" name="quantitys" class="easyui-validatebox"onblur="calcu('+detailId+')">'+
                             '</td>'+
                             '<td class="head" nowrap>总价：</td>'+
                             '<td >'+
                             '<input style="width:100px" type="text"  id="totalPriceDetail_'+detailId+'" name="totalPriceDetails" class="easyui-validatebox"readonly="true">'+
                             '</td>'+
//                           '<td class="head" nowrap>订单类型：</td>'+
//		                     '<td>'+
//			                 '<select id="orderType" name="orderTypes">'+
//			                 '<option value="A">本品</option>'+
//			                 '<option value="B">赠品</option>'+
//			                 '</select>'+
//		                     '</td>'+
//		                     '<td class="head" nowrap>实际数量：</td>'+
//		                     '<td >'+
//			                 '<input type="text"  id="realQuantity" name="realQuantitys"  class="easyui-validatebox">'+
//		                     '</td>'+
		                     '<td><a href="javascript:removeDetail(' + detailId
								+ ')"><img align="absMiddle" border="0" src="'
								+ imgUrl
								+ '/images/actions/action_del.png"/></a></td>'+
							 '</tr><tr><td>&nbsp;</td></tr></table>'
			);
	$('#searchBox_'+detailId).searchbox();
	
	$('#skuId_'+detailId).combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/orderNewAction!getSkuName.jspa',
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
				}, {
					field : 'skuUnit',
					title : '单位',
					width : 80
				} ] ],
				toolbar : '#toolbarSku_'+detailId
			});
//	$('#sku').combogrid('setValue', $("#sku_id").val());
//	$('#skuId_'+detailId).combogrid('setText', $("#skuId_"+detailId).val());
//	$('#skuId_'+detailId).combogrid({
//		onClickRow :  function(rowIndex,rowData){
//			var unit=rowData.skuUnit;
//			var units=unit.split(",");
//			var str="<option value='箱'>箱</option>";
////			for(var i=0;i<units.length;i++){
////				str=str+'<option value="'+units[i]+'">'+units[i]+'</option>';
////			}
//			$("#sku_sel_"+detailId).empty();
//			$("#sku_sel_"+detailId).append(
//					'<select id="skuUnit_'+detailId+'" name="unitDescs">'+
//					'<option value="箱">箱</option>' +
//					'</select>'
//			);
//		}
//	});
}

function loadCust(){
	$('#kunnrId').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$("#orgId").val()+'&bhxjFlag='+'C',
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
		}] ],
		onClickRow : function(rowIndex, rowData){
			alert(rowData.street);
			$("#kunnr").val(rowData.kunnr);
			$("#name2").val(rowData.name3);
			$("#mobile").val(rowData.mobNumber);
			$("#carType").val(rowData.maximumTxt);
			$("#address").val(rowData.street);
		},
		toolbar : '#toolbarKonzs'
	});
}
function loadUnit(id){
//	alaert("ok");
//	$('#skuId_'+detailId).combogrid({
//		onClickRow :  function(rowIndex,rowData){
//			var unit=rowData.skuUnit;
//	        this.dataGridViewitem
//			if(unit=="箱,提,杯"){
//				$("#skuUnit_"+detailId).remove();
//				$("#td_sku_"+detailId).append(
//						'<select id="skuUnit_'+detailId+'" name="unitDescs">'+
//						'<option value="杯">杯</option>'+
//						'<option value="提">提</option>'+
//						'<option value="箱">箱</option>'+
//						'</select>'
//				);
//			}else{
//				$("#skuUnit_"+detailId).remove();
//				$("#td_sku_"+detailId).append(
//						'<input type="hidden" id="skuUnit_'+detailId+'" name="unitDescs" value="'+unit+'">'
//				);
//			}
//		}
//	});
}

function removeDetail(id){
	$("#detail_"+id).remove();
}

function searcherSku(val) {
	var id=$(this).attr('id');
	var ids=id.split('_');
	var queryParams = $('#skuId_'+ids[1]).combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId_'+ids[1]).combogrid("grid").datagrid('reload');

}

function searcherKonzs(name1) {
	var queryParams = $('#kunnrId').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnrId').combogrid("grid").datagrid('reload');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

function initMaintWindow(title, url, id, WWidth, WHeight, fit) {
	var url = appUrl + url;
	var $win = $("#" + id)
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
						fit : fit,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

//关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
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
