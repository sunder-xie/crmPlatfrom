$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

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
			                 '<input type="hidden" id="state_'+detailId+'" name="states" value="1">'+
		                     '<td style="width:15%"><a href="javascript:removeDetail(' + detailId
								+ ')"><img align="absMiddle" border="0" src="'
								+ imgUrl
								+ '/images/actions/action_del.png"/></a></td>'+
							 '</tr>'
			);
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

function chooseAnswerAuthor(){
	initMaintWindow('选择处理人',
			'/question/questionAction!selectAnswer.jspa',
			'maintWindow', 660, 430, false);
}

function saveUser(x){
	closeMaintWindow()
	$("#answerAuthor").val(x[1]);
	$("#answerAuthorId").val(x[0]);
}

function sendEmail(){
	if($("#answerAuthor").val()== ""){
		$.messager.alert('Tips', '请选择处理人!', 'warning');
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!sendEmail.jspa",
		data : { answerAuthorId : $("#answerAuthorId").val() , qId : $("#qId").val() , answerAuthorEmail : $("#answerAuthorEmail").val() ,
			     orgName : encodeURIComponent($("#orgName").val()) , author : encodeURIComponent($("#author").val()) },
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
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!createDemand.jspa";
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
