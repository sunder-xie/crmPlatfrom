$(document).ready(function() {
//	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});


function  uploadyd(){
	var cc=encodeURIComponent($("#userId").val())
	$.ajax({
		type: 'post',
		url: appUrl+'/batChangeAction!getCustList.jspa?fiyId='+cc,
		dataType: 'text',
		success: function(data){			
//			var dataObj=eval("("+data+")");   
//			for(var i=0;i<dataObj.length;i++){    
//				alert(dataObj[i].id+" "+dataObj[i].name);   
//			}
			$("#source").empty();	
			$("#source").append("业代客户列表");
			var jsonObj=eval("("+data+")");
			$.each(jsonObj, function (i, item) {
				$("#source")
									.append(
											'<div class="drag-item" ondblclick=javascript:getOpen(this) userId="'
													+ item.custId
													+ '"><img src="'
													+ imgUrl
													+ '/images/actions/action_roles.png" align="absMiddle"></img>'
													+ item.custName
													+ '</div>');
							reloadST();
				//alert(item.id + ","  + item.name);
			});
		},
		error: function(text) {}
	});

}
function  uploaddt(){
	var cc=encodeURIComponent($("#userId1").val())
	$.ajax({
		type: 'post',
		url: appUrl+'/batChangeAction!getCustList.jspa?fiyId='+cc,
		dataType: 'text',
		success: function(data){			
//			var dataObj=eval("("+data+")");   
//			for(var i=0;i<dataObj.length;i++){    
//				alert(dataObj[i].id+" "+dataObj[i].name);   
//			}
			var jsonObj=eval("("+data+")");
			$("#target").empty();
			$("#target").append("目标业代客户列表");
			$.each(jsonObj, function (i, item) {
				$("#target")
									.append(
											'<div class="drag-item" ondblclick=javascript:getOpen(this) userId="'
													+ item.custId
													+ '"><img src="'
													+ imgUrl
													+ '/images/actions/action_roles.png" align="absMiddle"></img>'
													+ item.custName
													+ '</div>');
							reloadST();
				//alert(item.id + ","  + item.name);
			});
		},
		error: function(text) {}
	});

}
function getOpen(bf){
	//给双击事件加移动的方法
var this_id = $(bf).parent().attr("id");
if(this_id=="source"){
	$(bf).appendTo('#target');
}else if (this_id=="target"){
	$(bf).appendTo('#source');
}

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function initWorkPlan(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#upDemo")
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
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
function closeDtPlan() {
	$("#upDemo").window('close');
}
function chosePerson1(){
	var cc=encodeURIComponent($("#userId").val())
	initWorkPlan('选择处理人','/batChangeAction!toSearchMan1.jspa?fiyId='+cc,550,300);
}
function chosePerson(){
	var cc=encodeURIComponent($("#userId1").val())
	initWorkPlan('选择处理人','/batChangeAction!toSearchMan.jspa?fiyId='+cc,550,300);
}
function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	// queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.orgId = encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			//window.close();
		});
	}
}




// ///
function reloadST() {
	//元素不支持双击方法
	$('.drag-item').draggable({
		revert : true,
		deltaX : 0,
		deltaY : 0,
		proxy : 'clone',
		revert : true,
		cursor : 'auto',
		onStartDrag : function() {
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag : function() {
			$(this).draggable('options').cursor = 'auto';
		}
	}).droppable({
		onDragOver : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(source).insertAfter(this);
			$(source).removeClass('over');
		}
	});
	$('#target,#source').droppable({
		onDragEnter : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(this).append(source);
			$(this).removeClass('over');
		}
	});
}

function toRight(){
	var context = "";
	$("#source div").each(function() {
		if (context.length > 0) {
				context = context + "," + $(this).attr("userId");
		$("#target")
									.append(
											'<div class="drag-item" ondblclick=javascript:getOpen(this) userId="'
													+ $(this).attr("userId")
														+ '">'
													+$(this).html()
													+ '</div>');
							reloadST();
		} else {
			context = $(this).attr("userId");
			$("#target")
									.append(
											'<div class="drag-item"  ondblclick=javascript:getOpen(this) userId="'
													+ $(this).attr("userId")
													+ '">'
													+$(this).html()
													+ '</div>');
			reloadST();
		}
	});
	$("#source").empty();
	$("#source").append("业代客户列表");
}
function toLeft(){
	var context="";
	$("#target div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		$("#source")
									.append(
											'<div class="drag-item" ondblclick=javascript:getOpen(this) userId="'
													+ $(this).attr("userId")
													+ '">'
													+$(this).html()
													+ '</div>');
							reloadST();
		} else {
			context = $(this).attr("userId");
			$("#source")
									.append(
											'<div class="drag-item" ondblclick=javascript:getOpen(this) userId="'
													+ $(this).attr("userId")
													+ '">'
													+$(this).html()
													+ '</div>');
			reloadST();
		}
	});
	$("#target").empty();
	$("#target").append("目标业代客户列表");
}
function save() {
	var context = "";
	var totext="";
		var p = $("#userName").validatebox('isValid');
		var c=$("#userName1").validatebox('isValid');
	$("#source div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		} else {
			context = $(this).attr("userId");
		}
	});
	$("#target div").each(function() {
		if (totext.length > 0) {
			totext = totext + "," + $(this).attr("userId");
		} else {
			totext = $(this).attr("userId");
		}
	});
//	if ("" == context) {
//		alert("业代客户列表！");
//		return;
//	}if ("" == totext) {
//		alert("请维护目标业代客户列表!");
//		return;
//	}  else {
	if(!(p&&c)){
		$.messager.alert('Tips', '请选择业代信息！', 'warning');
		return;
	}
		$.messager
			.confirm(
					'Confirm',
					'是否维护业代客户信息?',
					function(r) {
						if (r) {
						$("#custIdList").val(context);
					 	$("#custIds").val(totext);
						var form = window.document.forms[0];
						form.action =appUrl+'/batChangeAction!SaveBatChange.jspa';
						form.submit();
					}});
//		close();
//	}
}
function close() {
	window.parent.closeMaintEvent();
}