$(document).ready(function() {
	loadData();
	init();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	$("#demandType").combobox("setValue",$("#typeValue").val());
	$("#demandLevel").combobox("setValue",$("#levelValue").val());
	
	$("#handleSpeed").append("<option value=''>δ����</option>");
	$("#handleQuality").append("<option value=''>δ����</option>");
	$("#handleAttitude").append("<option value=''>δ����</option>");
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
			 '&nbsp;&nbsp;<a id="btnUser_'+detailId+'" class="easyui-linkbutton"  href="javascript:selectUser('+detailId+')">ѡ��</a>'+
            '</td>'+
            '<td id="td_org_'+detailId+'" style="width:15%">'+
            '<input class="easyui-validatebox" data-options="required:false" id="orgName_'+detailId+'" name="orgNames" readonly>'+
            '</td>'+
            '<td id="td_expectDate_'+detailId+'" style="width:15%">'+
            '<input class="easyui-datebox" id="expectDate_'+detailId+'" data-options="editable:false" readonly>'+
            '</td>'+
            '<td style="width:15%">'+
            '<select id="state_'+detailId+'" name="states" readonly>'+
            '<option value="1">δ����</option>'+
            '</select>'+
            '</td>'+
            '<td style="width:15%"><a href="javascript:removeDetail(' + detailId
				+ ')"><img align="absMiddle" border="0" src="'
				+ imgUrl
				+ '/images/actions/action_del.png"/></a></td>'+
            '<td><a id="sendEmail_'+detailId+'" href="javascript:sendEmail('+detailId+');">�����ʼ���������</a></td>'+
			'</tr>'
     );
     $("#sendEmail_"+detailId).linkbutton();
     $("#btnUser_"+detailId).linkbutton();
}

/*
 * ����Աѡ����
 */
function selectUser(num) {
	n=num;
	initMaintWindow4Post('ѡ������', '/batChangeAction!selectOrgTreeUserAndUserIdWithBusMobileAndOrg.jspa',
			550, 350);
}

/**
 * ��������
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
 * ������ѡ�񷵻�ֵ
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
 * �ر�ҳ��
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
			$("#handleAuthor").append('<tr><td style="font-size: 12px;font-weight: bold;width:10%;">��������Ϣ</td><td colspan="6">&nbsp;</td></tr>'+
                                      '<tr id="btn_addHandleAuthor"><td><a href="javascript:addHandleAuthor()"> ��Ӵ�����</a></td><td colspan="6">&nbsp;</td></tr>'+
                                      '<tr>'+
                                           '<td>&nbsp;</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">��������</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">������</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">Ԥ���������</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">����״̬</td>'+
                                           '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">����ʱ��</td>'+
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
	                      "<option value='1'>δ����</option>"+
	                      "<option value='2'>������</option>"+
	                      "<option value='3'>�����</option>"+
	                      "<option value='4'>������</option>"+
	                      "</select></td>";
				}else{
					var state="";
					switch(val.state){
					   case '1': state="δ����"; break;
					   case '2': state="������"; break;
					   case '3': state="�����"; break;
					   case '4': state="������"; break;
					   
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
						                   "<td><a id='sendEmail_"+detailId+"' href='javascript:sendEmail("+detailId+","+val.userId+");'>�����ʼ���������</a></td>"+
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
			$("#handleResult").append('<tr><td style="font-size: 12px;font-weight: bold;width:8.5%;">�������</td><td colspan="5">&nbsp;</td></tr>'+
	                                  '<tr>'+
	                                      '<td>&nbsp;</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">��������</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">�ظ���</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">�ظ�����</td>'+
	                                      '<td style="font-size: 12px;font-weight: bold;COLOR: #006b8a;width:15%">�ظ�����</td>'+
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
	initMaintWindow('ѡ������',
			'/question/questionAction!selectAnswer.jspa',
			'maintWindow', 660, 430, false);
}


function sendEmail(detailId,userId){
	if($("#userId_"+detailId).val() == ""){
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!sendDemandEmail.jspa",
		data : { demandId : $("#demandId").val() , userId : userId},
		success : function(data){
			if(data==true){
			    $.messager.alert('Tips', '���ͳɹ�!', 'info');
			}else{
				$.messager.alert('Tips', '����ʧ�ܣ�����ϵ����Ա!', 'info');
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
		$.messager.alert('Tips', '����д��������!', 'warning');
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

//�رմ���ҳ��
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

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
