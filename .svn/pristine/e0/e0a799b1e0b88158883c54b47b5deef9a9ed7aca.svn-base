$(document).ready(function() {
	loadData();
	$('#hideFrame').bind('load', promgtMsg);
});

var headId=0;
var agentId=0;
var businessManagerId=0;

function loadData(){
	$.ajax({
		type : "post",
		url : appUrl + "/kunnrBusinessContact/kunnrBusinessAction!searchbusinessManager.jspa",
		data : { kunnrId : $("#kunnr").val() },
		success : function(data) {
			$.each(data,function(key,val){
				var mobile=val.managerMobile;
				if(!val.managerMobile){
					mobile="";
				}
			
					businessManagerId++;
					$("#tbody_businessManager").append('<tr>'+
	                        '<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;高级经理(兼):</td>'+
	                        '<td>'+
	                        '<input type="hidden" class="easyui-validatebox" id="businessManagerId_'+businessManagerId+'" name="businessManagerIds" value="'+val.businessManagerId+'">'+
	                        '<input style="width:130px" class="easyui-validatebox" id="businessManager_'+businessManagerId+'" name="businessManagers" value="'+val.businessManager+'" readonly>'+
	                        '&nbsp;<a id="btn1_businessManager_'+businessManagerId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'M\','+businessManagerId+')">选择</a>'+
	                        '&nbsp;<a id="btn2_businessManager_'+businessManagerId+'" class="easyui-linkbutton"  href="javascript:clearManager('+businessManagerId+')">清空</a>'+
	                        '</td>'+
	                        '<td class="head" noWrap>高级经理手机:</td>'+
	                        '<td><input class="easyui-validatebox" id="managerMobile_'+businessManagerId+'" name="managerMobiles" value="'+mobile+'" readonly></td>'+
	                        '</tr>'
					);
					$("#btn1_businessManager_"+businessManagerId).linkbutton();
					$("#btn2_businessManager_"+businessManagerId).linkbutton();
				
			});
		}
	});
	$.ajax({
		type : "post",
		url : appUrl + "/kunnrBusinessContact/kunnrBusinessAction!searchHead.jspa",
		data : { kunnrId : $("#kunnr").val() },
		success : function(data) {
			$.each(data,function(key,val){
				var mobile=val.headMobile;
				if(!val.headMobile){
					mobile="";
				}
				if(key==0){
					$("#businessHeadId_0").val(val.businessHeadId);
					$("#businessHead_0").val(val.businessHead);
					$("#headMobile_0").val(val.headMobile);
				}else if(key>0){
					headId++;
					$("#tbody_head").append('<tr>'+
							'<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;销售客户经理:</td>'+
							'<td>'+
							'<input type="hidden" class="easyui-validatebox" id="businessHeadId_'+headId+'" name="businessHeadIds" value="'+val.businessHeadId+'">'+
							'<input style="width:130px" class="easyui-validatebox" id="businessHead_'+headId+'" name="businessHeads" value="'+val.businessHead+'" readonly>'+
							'&nbsp;<a id="btn1_head_'+headId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'H\','+headId+')">选择</a>&nbsp;'+
							'&nbsp;<a id="btn2_head_'+headId+'" class="easyui-linkbutton"  href="javascript:clearHead('+headId+')">清空</a>'+
							'</td>'+
							'<td class="head" noWrap>销售客户经理手机:</td>'+
							'<td><input class="easyui-validatebox" id="headMobile_'+headId+'" name="headMobiles" value="'+mobile+'" readonly></td>'+
							'</tr>'
					);
					$("#btn1_head_"+headId).linkbutton();
					$("#btn2_head_"+headId).linkbutton();
				}
			});
		}
	});
	$.ajax({
		type : "post",
		url : appUrl + "//kunnrBusinessContact/kunnrBusinessAction!searchAgent.jspa",
		data : { kunnrId : $("#kunnr").val() },
		success : function(data) {
					$.each(data,function(key,val){
						var mobile=val.agentMobile;
						if(!val.agentMobile){
							mobile="";
						}
						if(key==0){
							$("#businessAgentId_0").val(val.businessAgentId);
							$("#businessAgent_0").val(val.businessAgent);
							$("#agentMobile_0").val(val.agentMobile);
						}else if(key>0){
							agentId++;
							$("#tbody_agent").append('<tr>'+
									'<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;业代:</td>'+
									'<td>'+
									'<input type="hidden" class="easyui-validatebox" id="businessAgentId_'+agentId+'" name="businessAgentIds" value="'+val.businessAgentId+'">'+
									'<input style="width:130px" class="easyui-validatebox" id="businessAgent_'+agentId+'" name="businessAgents" value="'+val.businessAgent+'" readonly>'+
									'&nbsp;<a id="btn1_agent_'+agentId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'A\','+agentId+')">选择</a>'+
									'&nbsp;<a id="btn2_agent_'+agentId+'" class="easyui-linkbutton"  href="javascript:clearAgent('+agentId+')">清空</a>&nbsp;'+
									'</td>'+
									'<td class="head" noWrap>业代手机:</td>'+
									'<td><input class="easyui-validatebox" id="agentMobile_'+agentId+'" name="agentMobiles" value="'+mobile+'" readonly></td>'+
									'</tr>'
							);
							$("#btn1_agent_"+agentId).linkbutton();
							$("#btn2_agent_"+agentId).linkbutton();
						}
					});
				}
	});
}

function save_business(){
	var kunnr = $('#kunnr').validatebox('isValid');
//	var businessManager = $('#businessManager').validatebox('isValid');
//	var managerMobile = $('#managerMobile').validatebox('isValid');
//	var businessCompetent = $('#businessCompetent').validatebox('isValid');
//	var businessCompetentId = $('#businessCompetentId').validatebox('isValid');
//	var competentMobile = $('#competentMobile').validatebox('isValid');
	var kunnrLeader = $('#kunnrLeader').validatebox('isValid');
	var kunnrPhone = $('#kunnrPhone').validatebox('isValid');
    var form = window.document.forms[0];
    form.action = appUrl + "/kunnrBusinessContact/kunnrBusinessAction!saveKunnrBusiness.jspa";
    form.target = "hideFrame";
    form.submit();
}
function cencel() {
	window.parent.closeWindow();
}
/*
 * 打开人员选择树
 */
function selectDirectory(value,num) {
	var posId;
	if(value=='H'){
		posId='h_zg';
	}else if(value=='A'){
		posId='h_yd';
	}else if(value=='M'){
	posId='h_csjl';
}
	
	userRole = value;
	n=num;
	initMaintWindow4Post('选择人员', '/batChangeAction!selectOrgTreeUserAndUserIdWithBusMobile.jspa?posId='+posId,
			550, 350);
}

function selectManager(value,num) {
	userRole = value;
	n=num;
	initMaintWindow4Post('选择人员', '/batChangeAction!selectOrgTreeUserWithBusMobile.jspa',
			550, 350);
}

/*
 * 城市经理、主管选择返回值
 */
function returnUser(userId, userName,moblie) {
	if (userRole == 'M') {
		document.getElementById('businessManagerId_'+n).value = userId;
		document.getElementById('businessManager_'+n).value = userName;
		$('#managerMobile_'+n).val(moblie);
		
		
	}
	if (userRole == 'D') {
		document.getElementById('businessCompetentId').value = userId;
		document.getElementById('businessCompetent').value = userName;
		$('#competentMobile').val(moblie);
		
	}
	if (userRole == 'H') {
		var flag=true;
		for(var i=0;i<headId;i++){
			if($('#businessHeadId_'+i).val()==userId){
				$.messager.alert('提示', '该人员已维护！', 'error');
				flag=false;
				return;
			}
		}
		if(flag){
			document.getElementById('businessHeadId_'+n).value = userId;
			document.getElementById('businessHead_'+n).value = userName;
			$('#headMobile_'+n).val(moblie);
		}
	}
	if (userRole == 'A') {
		var flag=true;
		for(var i=0;i<agentId;i++){
			if($('#businessAgentId_'+i).val()==userId){
				$.messager.alert('提示', '该人员已维护！', 'error');
				flag=false;
				return;
			}
		}
		if(flag){
			document.getElementById('businessAgentId_'+n).value = userId;
			document.getElementById('businessAgent_'+n).value = userName;
			$('#agentMobile_'+n).val(moblie);
		}
	}
}

function addHead(){
	headId++;
	$("#tbody_head").append('<tr>'+
	                        '<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;销售客户经理:</td>'+
	                        '<td>'+
	                        '<input type="hidden" class="easyui-validatebox" id="businessHeadId_'+headId+'" name="businessHeadIds">'+
	                        '<input style="width:130px" class="easyui-validatebox" id="businessHead_'+headId+'" name="businessHeads" readonly>'+
	                        '&nbsp;<a id="btn1_head_'+headId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'H\','+headId+')">选择</a>'+
	                        '&nbsp;<a id="btn2_head_'+headId+'" class="easyui-linkbutton"  href="javascript:clearHead('+headId+')">清空</a>'+
	                        '</td>'+
	                        '<td class="head" noWrap>销售客户经理手机:</td>'+
	                        '<td><input class="easyui-validatebox" id="headMobile_'+headId+'" name="headMobiles"></td>'+
	                        '</tr>'
	);
	$("#btn1_head_"+headId).linkbutton();
	$("#btn2_head_"+headId).linkbutton();
}

function addbusinessManager(){
	businessManagerId++;
	$("#tbody_businessManager").append('<tr>'+
	                        '<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;高级经理(兼):</td>'+
	                        '<td>'+
	                        '<input type="hidden" class="easyui-validatebox" id="businessManagerId_'+businessManagerId+'" name="businessManagerIds">'+
	                        '<input style="width:130px" class="easyui-validatebox" id="businessManager_'+businessManagerId+'" name="businessManagers" readonly>'+
	                        '&nbsp;<a id="btn1_businessManager_'+businessManagerId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'M\','+businessManagerId+')">选择</a>'+
	                        '&nbsp;<a id="btn2_businessManager_'+businessManagerId+'" class="easyui-linkbutton"  href="javascript:clearManager('+businessManagerId+')">清空</a>'+
	                        '</td>'+
	                        '<td class="head" noWrap>高级经理手机:</td>'+
	                        '<td><input class="easyui-validatebox" id="managerMobile_'+businessManagerId+'" name="managerMobiles"></td>'+
	                        '</tr>'
	);
	$("#btn1_businessManager_"+businessManagerId).linkbutton();
	$("#btn2_businessManager_"+businessManagerId).linkbutton();
}

function addAgent(){
	agentId++;
	$("#tbody_agent").append('<tr>'+
	                        '<td class="head" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;业代:</td>'+
	                        '<td>'+
	                        '<input type="hidden" class="easyui-validatebox" id="businessAgentId_'+agentId+'" name="businessAgentIds">'+
	                        '<input style="width:130px" class="easyui-validatebox" id="businessAgent_'+agentId+'" name="businessAgents" readonly>'+
	                        '&nbsp;<a id="btn1_agent_'+agentId+'" class="easyui-linkbutton"  href="javascript:selectDirectory(\'A\','+agentId+')">选择</a>'+
	                        '&nbsp;<a id="btn2_agent_'+agentId+'" class="easyui-linkbutton"  href="javascript:clearAgent('+agentId+')">清空</a>'+
	                        '</td>'+
	                        '<td class="head" noWrap>业代手机:</td>'+
	                        '<td><input class="easyui-validatebox" id="agentMobile_'+agentId+'" name="agentMobiles"></td>'+
	                        '</tr>'
	);
	$("#btn1_agent_"+agentId).linkbutton();
	$("#btn2_agent_"+agentId).linkbutton();
}

function clearAgent(num){
	$("#businessAgentId_"+num).val("");
	$("#businessAgent_"+num).val("");
	$("#agentMobile_"+num).val("");
}

function clearHead(num){
	$("#businessHeadId_"+num).val("");
	$("#businessHead_"+num).val("");
	$("#headMobile_"+num).val("");
}

function clearManager(num){
	$("#businessManagerId_"+num).val("");
	$("#businessManager_"+num).val("");
	$("#managerMobile_"+num).val("");
}

/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	$("#nextUserDialog").window('close');
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
