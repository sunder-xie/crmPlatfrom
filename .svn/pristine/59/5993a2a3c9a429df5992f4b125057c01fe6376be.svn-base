$(document).ready(function() {
	init();
	loadData();
	$('#hideFrame').bind('load', promgtMsg);
});


/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function downLoad(fileId) {
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!downLoadFile.jspa?fileId="
			+ fileId;
	form.submit();
}

function loadData(){
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!getCommunitList.jspa",
		data : { qId : $("#qId").val() },
		success : function(communitList){
			$.each(communitList,function(key,val){
				var time=utcToDate(val.createTime.replace(
							       /\/Date\((\d+)\+\d+\)\//gi,
							       "new Date($1)"));
				
				$("#communit_list").append("<tr>" +
						                   "<td class='head2' algin='center' width='10%'>回复人：</td>" +
		                                   "<td class='even3' algin='center' width='10%'>"+val.author+"</td>" +
		                                   "<td class='head2' algin='center' width='10%'>内容：</td>" +
		                                   "<td class='even3' algin='center' width='45%'>"+val.content+"</td>" +
		                                   "<td class='head2' algin='center' width='10%'>回复时间：</td>" +
		                                   "<td class='even3' algin='center' width='15%'>"+time+"</td>" +
		                                   "</tr>"
						                   );
			});
		}
	});		
}

function updateQuestion() {
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!updateQuestion.jspa";
	form.target = "hideFrame";
	form.submit();
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
		search();
		return false;
	}
	return true;
};
$("body").on("click",'.combo-text',function(){
	$(this).siblings("span").find("span").click();
	});
function init(){
	var qTemp=$('#qTemp');
	var qTemp2=$('#qTemp2');
	var qLevel=$('#qLevel');
	var qType=$('#qType');
	var handleState=$('#handleState');
	if(qTemp.val()=='t100'){
		qTemp.val('软件系统');
	}else if(qTemp.val()=='t200'){
		qTemp.val('硬件网络');
	}else if(qTemp.val()=='t300'){
		qTemp.val('人事');
	}else if(qTemp.val()=='t400'){
		qTemp.val('营销系统');
	}else if(qTemp.val()=='t500'){
		qTemp.val('协同');
	}else if(qTemp.val()=='t600'){
		qTemp.val('电子报销');
	}else if(qTemp.val()=='t700'){
		qTemp.val('市场费用');
	}else if(qTemp.val()=='t800'){
		qTemp.val('经销商管理');
	}else if(qTemp.val()=='t900'){
		qTemp.val('手机系统');
	}
	
//	if(qTemp2.val()=='z101'){
//		qTemp2.val('邮箱');
//	}else if(qTemp2.val()=='z102'){
//		qTemp2.val('硬件');
//	}else if(qTemp2.val()=='z103'){
//		qTemp2.val('网络');
//	}else if(qTemp2.val()=='z201'){
//		qTemp2.val('SAP');
//	}else if(qTemp2.val()=='z301'){
//		qTemp2.val('人事');
//	}else if(qTemp2.val()=='z401'){
//		qTemp2.val('市场费用管理');
//	}else if(qTemp2.val()=='z402'){
//		qTemp2.val('客户关系管理');
//	}else if(qTemp2.val()=='z403'){
//		qTemp2.val('报表');
//	}else if(qTemp2.val()=='z501'){
//		qTemp2.val('电子报销系统');
//	}else if(qTemp2.val()=='z502'){
//		qTemp2.val('客诉管理');
//	}else if(qTemp2.val()=='z503'){
//		qTemp2.val('事务管理');
//	}
	
	if(qLevel.val()=='l101'){
		qLevel.val('非常紧急');
	}else if(qLevel.val()=='l102'){
		qLevel.val('紧急');
	}else if(qLevel.val()=='l103'){
		qLevel.val('一般');
	}
	
	if(qType.val()=='p101'){
		qType.val('系统缺陷');
	}else if(qType.val()=='p102'){
		qType.val('业务需求');
	}else if(qType.val()=='p103'){
		qType.val('数据错误');
	}else if(qType.val()=='p104'){
		qType.val('操作指导');
	}else if(qType.val()=='p105'){
		qType.val('权限');
	}else if(qType.val()=='p201'){
		qType.val('硬件');
	}else if(qType.val()=='p202'){
		qType.val('打印复印故障');
	}else if(qType.val()=='p203'){
		qType.val('网络、电话故障');
	}else if(qType.val()=='p204'){
		qType.val('电脑运行慢');
	}else if(qType.val()=='p205'){
		qType.val('操作培训');
	}else if(qType.val()=='p206'){
		qType.val('一卡通、监控');
	}
	
	if(handleState.val()=='s101'){
		handleState.val('未处理');
	}else if(handleState.val()=='s102'){
		handleState.val('进行中');
	}else if(handleState.val()=='s103'){
		handleState.val('已完成');
	}
}