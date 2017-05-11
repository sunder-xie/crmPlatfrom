$(document).ready(function() {
	loadData();
	init();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	$("#demandType").combobox("setValue",$("#typeValue").val());
	$("#demandLevel").combobox("setValue",$("#levelValue").val());
	
	$("#handleSpeed").append("<option value=''>未评价</option>");
	$("#handleQuality").append("<option value=''>未评价</option>");
	$("#handleAttitude").append("<option value=''>未评价</option>");
	for(var i=1;i<=10;i++){
		$("#handleSpeed").append("<option value='"+i+"'>"+i+"</option>");
		$("#handleQuality").append("<option value='"+i+"'>"+i+"</option>");
		$("#handleAttitude").append("<option value='"+i+"'>"+i+"</option>");
	}
	$("#handleSpeed").get(0).value = $("#handleSpeedValue").val();
	$("#handleQuality").get(0).value = $("#handleQualityValue").val();
	$("#handleAttitude").get(0).value = $("#handleAttitudeValue").val();
	
	var flag=0;
	var userFlag=0;
	for(var i=1;i<=detailId;i++){
		if($("#userId").val()==$("#userId_"+i).val()){
			userFlag=1;
			break;
		}
	}
	
	if($("#handleSpeed").val()!=""){
		flag=1;
	}else if(userFlag==1){
		flag=2;
	}else if($("#userId").val()!=$("#authorId").val()){
		flag=3;
	}
	
	if(flag==1){
		$("#phone").attr("readonly","true");
		$("#authorEmail").attr("readonly","true");
		$("#demandType").combobox("disable","true");
		$("#demandLevel").combobox("disable","true");
		$("#content").attr("readonly","true");
		$("#fileUp").remove();
		
		$("#btn_addHandleAuthor").remove();
		$("#addCommunit").remove();
		$("#advice").attr("readonly","true");
		$("#btn_submit").remove();
		
		$("#handleSpeed").attr("disabled","disabled");
		$("#handleQuality").attr("disabled","disabled");
		$("#handleAttitude").attr("disabled","disabled");
		
		for(var i=1;i<=detailId;i++){
			$("#sendEmail_"+i).remove();
			$("#state_"+i).attr("disabled","disabled");
		}
	}else if(flag==3){
		$("#phone").attr("readonly","true");
		$("#authorEmail").attr("readonly","true");
		$("#demandType").combobox("disable","true");
		$("#demandLevel").combobox("disable","true");
		$("#content").attr("readonly","true");
		$("#fileUp").remove();
		
		$("#btn_addHandleAuthor").remove();
		$("#advice").attr("readonly","true");
		$("#btn_submit").remove();
		
		$("#handleSpeed").attr("disabled","disabled");
		$("#handleQuality").attr("disabled","disabled");
		$("#handleAttitude").attr("disabled","disabled");
		
		for(var i=1;i<=detailId;i++){
			$("#sendEmail_"+i).remove();
			$("#state_"+i).attr("disabled","disabled");
		}
	}else if(flag==2){
		$("#phone").attr("readonly","true");
		$("#authorEmail").attr("readonly","true");
		$("#demandType").combobox("disable","true");
		$("#demandLevel").combobox("disable","true");
		$("#content").attr("readonly","true");
		$("#fileUp").remove();
		
		$("#userResult").remove();
	}
}

var detailId=0;

function addHandleAuthor(){
	detailId++;
	$("#handleAuthor").append(
			'<tr id="detail_'+detailId+'">'+
            '<td>&nbsp;</td>'+
            '<td id="td_user_'+detailId+'" style="width:15%">'+
            '<input type="hidden" id="userId_'+detailId+'" name="userIds" >'+
			 '<input style="width:80px" class="easyui-validatebox" data-options="required:false" id="userName_'+detailId+'" name="userNames" readonly>'+
			 '&nbsp;&nbsp;<a id="btnUser_'+detailId+'" class="easyui-linkbutton"  href="javascript:selectUser('+detailId+')">选择</a>'+
            '</td>'+
            '<td id="td_org_'+detailId+'" style="width:15%">'+
            '<input class="easyui-validatebox" data-options="required:false" id="orgName_'+detailId+'" name="orgNames" readonly>'+
            '</td>'+
            '<td id="td_expectDate_'+detailId+'" style="width:15%">'+
            '<input class="easyui-datebox" id="expectDate_'+detailId+'" data-options="editable:false" readonly>'+
            '</td>'+
            '<td style="width:15%">'+
            '<select id="state_'+detailId+'" name="states" readonly>'+
            '<option value="1">未处理</option>'+
            '</select>'+
            '</td>'+
            '<td style="width:15%"><a href="javascript:removeDetail(' + detailId
				+ ')"><img align="absMiddle" border="0" src="'
				+ imgUrl
				+ '/images/actions/action_del.png"/></a></td>'+
            '<td><a id="sendEmail_'+detailId+'" href="javascript:sendEmail('+detailId+');">发送邮件给处理人</a></td>'+
			'</tr>'
     );
     $("#sendEmail_"+detailId).linkbutton();
     $("#btnUser_"+detailId).linkbutton();
}

/*
 * 打开人员选择树
 */
function selectUser(num) {
	n=num;
	initMaintWindow4Post('选择处理人', '/batChangeAction!selectOrgTreeUserAndUserIdWithBusMobileAndOrg.jspa',
			550, 350);
}

/**
 * 弹出窗口
 * 
 * @param title
 * @param url
 * @param width
 * @param height
 */
function initMaintWindow4Post(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#nextUserDialog")
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
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}

function submitCommunit(){
	$.ajax({
	type : "post",
	url : appUrl + "/question/questionAction!createDemandCommunit.jspa",
	data : { demandId : $("#demandId").val(),
		demandContent : encodeURIComponent($("#communitContent").val())},
	success : function(data){
		$("#handleAuthor").empty();
		$("#handleResult").empty();
		loadData();
	}
});
}
/*
 * 处理人选择返回值
 */
function returnUser(userId, userName,moblie,orgName) {
	document.getElementById('userId_'+n).value = userId;
	document.getElementById('userName_'+n).value = userName;
	document.getElementById('orgName_'+n).value = orgName;
	$('#managerMobile').numberbox('setValue',moblie);
}

function closeOrgTree() {
	$("#nextUserDialog").window('close');
}

function removeDetail(id){
	$("#detail_"+id).remove();
}

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
		url : appUrl + "/question/questionAction!searchDemandUserList.jspa",
		async : false,
		data : { demandId : $("#demandId").val() },
		success : function(data){
			$("#handleAuthor").append('<tr><td style="font-size: 12px;font-weight: bold;width:10%;">处理人信息</td><td colspan="6">&nbsp;</td></tr>'+
                                      '<tr id="btn_addHandleAuthor"><td><a href="javascript:addHandleAuthor()"> 添加处理人</a></td><td colspan="6">&nbsp;</td></tr>'+
                                      '<tr>'+
                                           '<td>&nbsp;</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">需求处理人</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">处理部门</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">预计完成日期</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">处理状态</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">接收时间</td>'+
                                           '<td style="width:15%">&nbsp;</td>'+
                                      '</tr>'
	                                  );
			$.each(data,function(key,val){
				detailId++;
				var time=utcToDate(val.createDate.replace(
							       /\/Date\((\d+)\+\d+\)\//gi,
							       "new Date($1)"));
				var aStr="";
				var bStr="";
				if($("#userId").val()==val.userId){
				    aStr="<td id='td_expectDate_"+detailId+"' style='width:15%'>"+
	                     "<input class='easyui-datebox' id='expectDate_"+detailId+"' name='expectDates' value='"+val.expectDate+"' data-options='editable:false'>"+
	                     "</td>";
				    bStr= "<td class='even3' algin='center' width='15%'>"+
	                      "<select id='state_"+detailId+"' name='states'>"+
	                      "<option value='1'>未处理</option>"+
	                      "<option value='2'>处理中</option>"+
	                      "<option value='3'>已完成</option>"+
	                      "<option value='4'>不接受</option>"+
	                      "</select></td>";
				}else{
					var state="";
					switch(val.state){
					   case '1': state="未处理"; break;
					   case '2': state="处理中"; break;
					   case '3': state="已完成"; break;
					   case '4': state="不接受"; break;
					   
					}
					aStr="<td class='even3' algin='center' width='15%'>"+val.expectDate+"</td>"+
					     "<input type='hidden' id='expectDate_"+detailId+"' name='expectDates' value='"+val.expectDate+"'/>";
					bStr="<td class='even3' algin='center' width='15%'>"+state+"</td>"+
				         "<input type='hidden' id='state_"+detailId+"' name='states' value='"+val.state+"'/>";
				}
				$("#handleAuthor").append("<tr>" +
						                   "<td>&nbsp;</td>"+
						                   "<input type='hidden' id='userId_"+detailId+"' name='userIds' value='"+val.userId+"'/>"+
						                   "<td class='even3' algin='center' width='15%'>"+val.userName+"</td>" +
						                   "<td class='even3' algin='center' width='15%'>"+val.orgName+"</td>" +
						                   aStr+
						                   bStr+
						                   "<td class='even3' algin='center' width='15%'>"+time+"</td>" +
						                   "<td><a id='sendEmail_"+detailId+"' href='javascript:sendEmail("+detailId+","+val.userId+");'>发送邮件给处理人</a></td>"+
						                   "</tr>"
						                   );
				$("#sendEmail_"+detailId).linkbutton();
				if($("#userId").val()==val.userId){
					$("#expectDate_"+detailId).datebox();
					$("#state_"+detailId).get(0).value = val.state;
				}
			});
			$("#handleAuthor").append("</span>");
		}
	});
	
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!searchDemandCommunitList.jspa",
		async : false,
		data : { demandId : $("#demandId").val() },
		success : function(communitList){
			$("#handleResult").append('<tr><td style="font-size: 12px;font-weight: bold;width:8.5%;">处理进度</td><td colspan="5">&nbsp;</td></tr>'+
	                                  '<tr>'+
	                                      '<td>&nbsp;</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">所属部门</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">回复人</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">回复日期</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">回复内容</td>'+
	                                      '<td style="width:15%">&nbsp;</td>'+
	                                  '</tr>'
					);
			$.each(communitList,function(key,val){
				var time=utcToDate(val.createDate.replace(
							       /\/Date\((\d+)\+\d+\)\//gi,
							       "new Date($1)"));
				
				$("#handleResult").append("<tr>" +
						                   "<td>&nbsp;</td>"+
						                   "<td class='even3' algin='center' width='15%'>"+val.orgName+"</td>" +
						                   "<td class='even3' algin='center' width='15%'>"+val.userName+"</td>" +
						                   "<td class='even3' algin='center' width='15%'>"+time+"</td>" +
						                   "<td class='even3' algin='center' width='15%' colspan='2'>"+val.content+"</td>" +
						                   "</tr>"
						                   );
			});
		}
	});		
}

function chooseAnswerAuthor(){
	initMaintWindow('选择处理人',
			'/question/questionAction!selectAnswer.jspa',
			'maintWindow', 660, 430, false);
}


function sendEmail(detailId,userId){
	if($("#userId_"+detailId).val() == ""){
		$.messager.alert('Tips', '请选择处理人!', 'warning');
		return;
	}
	
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!sendDemandEmail.jspa",
		data : { demandId : $("#demandId").val() , userId : userId},
		success : function(data){
			if(data==true){
			    $.messager.alert('Tips', '发送成功!', 'info');
			}else{
				$.messager.alert('Tips', '发送失败，请联系管理员!', 'info');
			}
		}
	});
}

function submit() {
	if(($("#handleSpeed").val()!='' &&
		$("#handleQuality").val()!='' &&
		$("#handleAttitude").val()!='') ||
	   ($("#handleSpeed").val()=='' &&
		$("#handleQuality").val()=='' &&
		$("#handleAttitude").val()=='')){
		var form = window.document.forms[0];
		form.action = appUrl + "/question/questionAction!updateDemand.jspa";
		form.target = "hideFrame";
		form.submit();
	}else{
		$.messager.alert('Tips', '请填写完整评价!', 'warning');
	}
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
