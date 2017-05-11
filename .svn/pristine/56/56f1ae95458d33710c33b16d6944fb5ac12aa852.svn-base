var flag;
var orgflag=1;
var matgArr=new Array();
var matgKeyArr=new Array();
var linedata;
var custIdNow="";
var custNameNow="";
var orgNameNow="";
var channelIdNow="";
var channelNameNow="";
var createNameNow="";
var createTimeNow="";
var searchobj ={};
var pageNow=1;
var totalNum =0;

var gridArr=new Array();
$(document).ready(function() {
	
//	alert(getIndex("a",new Array("b","a")));
	
	var colsurl =appUrl + '/master!getMasterCols.jspa';
//	$.post(colsurl, { name: "John", time: "2pm" },
//			   function(data){
//		       for (var i=0;i<data.total;i++){
//		    	   matgArr[i] = data.rows[i].matg;
//		       }    
//		 
//			   });
	
	$.ajax({  
        type : "post",  
         url : colsurl,
         async : false,  
         success : function(data){  
        	  for (var i=0;i<data.total;i++){
		    	   matgArr[i] = data.rows[i].matg;
		    	   matgKeyArr[i] = data.rows[i].bezei40;
		       }    
         }  
    }); 
	
	loadGrid(1,10);
	$('#pageDetail').empty();
	$('#pageDetail').append("共有"+totalNum+"条数据, 当前为第"+1+"页");
	loadCustKunnr();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa',
		valueField : 'channelId',
		textField : 'channelName'
	});
	
	
	$('#allchannel').combobox({
		valueField : 'allChannelName',
		textField : 'allChannelName',
		width : 130,
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
			url : appUrl + '/kunnrAction!getAllChannel.jspa',
		onLoadSuccess : function(data){
			
		},
	onChange : function(newValue, oldValue){
	
		
		$('#channelId').combobox('setValue','');
		var url = appUrl + '/kunnrAction!getAllUnderChannel.jspa?allChannelName='+encodeURIComponent(newValue) ;
//		var url = appUrl + '/kunnrAction!getChannel.jspa?allChannelName='+newValue ;
		$('#channelId').combobox('reload', url);  
		
		
	}
	});
	
	
	
	

});
/**
 * 加载所属经分销商
 */
function loadCustKunnr() {
	// 二阶客户
	$('#custParent').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
		idField : 'custId',
		textField : 'custName',
		multiple : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'custId',
			title : '客户编号',
			width : 120
		}, {
			field : 'custName',
			title : '客户名称',
			width : 200
		} ] ],
		toolbar : '#toolbarParent'
	});
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/master!kunnrSearchFromMaster.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ /*
					 * [ { field : 'ck', checkbox : true } ],
					 */[ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
}

/*******************************************************************************
 * 加载主体数据列表
 */

var editorline = -1;
var editFlag =0;

function clickFlag(){
	editFlag =1; 
}
function onClickCell(index, field) {

	//	var row =  $("#datagrid").datagrid('getSelected');
	//alert(JSON.stringify(row));
	//alert(row.scale);

	if (editFlag ==1 ){
		if (editorline == -1) {
			$('#datagrid').datagrid('beginEdit', index);
			editorline = index;
		} else {
			$('#datagrid').datagrid('endEdit', editorline);
			$('#datagrid').datagrid('beginEdit', index);
			editorline = index;
		}
	}

}

function loadGrid( pageNumber, pageSize) {
	
	
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						// url : appUrl + '/master!getSupervisorItems.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						onClickCell : onClickCell,
						striped : true,
						queryParams : {
							orgId : $("#orgId").val(),
							bhxjFlag : $("#bhxjFlag").val()
						},
						frozenColumns : [ [
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.18),
									hidden : true,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = " <a href='#' onclick=viewLicense('"
												+ kunnr + "')>证照</a>";

										var scan = "|<a href='#' onclick=viewInfo('"
												+ kunnr + "')>详情</a>";
										var edit = status == 1 ? "|<a href='#' onclick=editInfo('"
												+ kunnr + "')>修改业务联系</a>"
												: "&nbsp;";

										return license + scan + edit;
									}
								}, {
									field : 'checkId',
									title : '检查项id',
									width : setColumnWidth(0.05),
									hidden : true,
									sortable : true
								}, {
									field : 'custId',
									title : '门店ID',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'custName',
									title : '门店',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'orgName',
									title : '门店组织',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'channelName',
									title : '渠道',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'createName',
									title : '创建人名称',
									width : setColumnWidth(0.08),
									sortable : true
								}, {
									field : 'createTime',
									title : '创建时间',
									width : setColumnWidth(0.05),
									sortable : true
								} ] ],
//						columns : [ [
//{field:'checkId1',title:'单个口味考核项ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue1',title:'椰果原味',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice1',title:'最低价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice1',title:'最高价格',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true}						] ],
						toolbar : [ "-", {
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
								excelCols();
							}
						}, "-", {
							text : '模板下载',
							iconCls : 'icon-excel',
							handler : function() {
								exportMouldCols();
							}
						}, "-", {
							text : '批量导入',
							iconCls : 'icon-add',
							handler : function() {
								importCheckItem();
							}
						},  "-" , {
							text : '开始修改',
							iconCls : 'icon-edit',
							handler : function() {
								clickFlag();
							}
						},"-" , {
							text : '保存修改',
							iconCls : 'icon-edit',
							handler : function() {
								saveChagCheckItem();
							}
						}, "-", {
							text : '导入铺货请填写Y 或 N'
						
						}, "-"  ]
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	
	
	 	options={};
//	    options.url = appUrl + '/master!getSupervisorItemsCols.jspa';
	    
	 
	 	var s = "";
	    s = "[[";
	    for(var i = 0;i < matgArr.length; i++) {
//	    	alert(i);
	    	 var checkId ="checkId"+i;
	    	 var checkValue="checkValue"+i;
	    	 var minPrice ="minPrice"+i;
	    	 var maxPrice ="maxPrice"+i;
//	    	s = s + "{field:\'"+checkId+"\',title:'checkId',width:160,hidden:true},{field:\'"+checkValue+"\',title:\'"+matgArr[i]+"\',editor:{type:'checkbox',options:{on:'Y',off:'N'}},width:160},{field:\'"+minPrice+"\',title:'最小价格',editor:'numberbox',width:120}, {field:\'"+maxPrice+"\',title:'最大价格',editor:'numberbox',width:120},";
	    	s = s + "{field:\'"+checkId+"\',title:'checkId',width:160,hidden:true},{field:\'"+checkValue+"\',title:\'"+matgArr[i]+"\',editor:{type:'combobox',options:{data:[{value:'Y',text:'Y'},{value:'N',text:'N'}]}},width:160},{field:\'"+minPrice+"\',title:'最小价格',editor:'numberbox',width:120,hidden:true}, {field:\'"+maxPrice+"\',title:'最大价格',editor:'numberbox',width:120,hidden:true},";
	    	  
	 }
		    s = s + "]]";
	    options.columns = eval(s);

	 	

	    $('#datagrid').datagrid(options);
	    
	    

	    $(".datagrid-pager")[0].hidden=true; 
	 
	    
	   
	    
//	    var pageNumber = $('#datagrid').datagrid('getPager').data("pagination").options.pageNumber;
//	    var pageSize = $('#datagrid').datagrid('getPager').data("pagination").options.pageSize;
	    //var total = $('#datagrid').datagrid('getPager').data("pagination").options.total;
	    // 
	    
	    searchobj.page= pageNumber;
	    searchobj.rows=pageSize;
	    
	    var loadJsondataStr="{";
	    
		var colsDataurl =appUrl + '/master!getSupervisorItemsCols.jspa';
		
		$.ajax({  
	        type : "post", 
//	        data: { page: pageNumber, rows: pageSize },
	        data: searchobj,
	         url : colsDataurl,
	         async : false,  
	         success : function(data){ 
	        	 // 先清空数据
	        	
	        	 
	        	 pageNow =pageNumber;
	        	 totalNum = data.total;
	        	 
	        	   gridArr=[];
	        		loadJsondataStr=loadJsondataStr+"\"total\":"+data.total+",\"rows\":[";
	  			  
					linedata="";
				      for (var i=0;i<data.rows.length;i++){
				    	  // 不是同一个门店,
				    	  if (data.rows[i].custId!=custIdNow){
				    		  if (linedata.length>0){
				    			  linedata =linedata+"\"custId\":"+custIdNow+",\"custName\":\""+custNameNow+"\",\"orgName\":\""+orgNameNow+"\",\"channelName\":\""+channelNameNow+"\",\"createName\":\""+createNameNow+"\",\"createTime\":\""+createTimeNow+"\",";
				    			  linedata = linedata.substring(0,linedata.length-1);
				    			  linedata=linedata+"}";
				    		  }
				    		  gridArr.push(linedata);
				    		  custIdNow = data.rows[i].custId;
				    		  custNameNow = data.rows[i].custName;
				    		  orgNameNow = data.rows[i].orgName;
				    		  channelNameNow = data.rows[i].channelName;
				    		  createNameNow = data.rows[i].createName;
				    		  createTimeNow = data.rows[i].createTime;
				    		  linedata="{";
				    	  }
				    	  
				    	  
//				    	  var index = getIndex(data.rows[i].matName, matgArr);
				    	  var index = getIndex(data.rows[i].matName, matgKeyArr);
				    	 linedata=linedata+"\"checkId"+index+"\":" +data.rows[i].checkId+",\"checkValue"+index+"\":\""+data.rows[i].checkValue+"\",\"minPrice"+index+"\":"+(data.rows[i].minPrice==undefined?"\"\"":data.rows[i].minPrice)+",\"maxPrice"+index+"\":"+(data.rows[i].maxPrice==undefined?"\"\"":data.rows[i].maxPrice)+",";
				    	  
				    	  
				      }
				      linedata =linedata+"\"custId\":"+custIdNow+",\"custName\":\""+custNameNow+"\",\"orgName\":\""+orgNameNow+"\",\"channelName\":\""+channelNameNow+"\",\"createName\":\""+createNameNow+"\",\"createTime\":\""+createTimeNow+"\",";
				      linedata=linedata.substring(0,linedata.length-1);
	    			  linedata=linedata+"}";
	    			 
				      gridArr.push(linedata);  linedata="";
				   
				      
	                for (var i=1;i<gridArr.length;i++){
	                	loadJsondataStr=	loadJsondataStr+gridArr[i]+",";
	                }
	                
	                if (data.rows.length>1){
	                	  loadJsondataStr= loadJsondataStr.substring(0,loadJsondataStr.length-1);
	                }
	                	
	                loadJsondataStr=loadJsondataStr+"]}"
	                
	                var jsonData = $.parseJSON(loadJsondataStr);
	        	    $('#datagrid').datagrid('loadData',jsonData);   
	         }  
	    }); 

	
		// 模拟数据
		
//	    var temp = '{"total":1,"rows":[{"checkId0":"41","checkValue0":"Y","minPrice0":1,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101}]}';
//	    var temp = '{"total":1,"rows":[{"checkId'+0+'":"44","checkValue0":"Y","minPrice0":1,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101	}]}';
//	    var temp = '{"total":1,"rows":[{"checkId'+0+'":"41","checkValue0":Y,"minPrice0":undefined,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101	}]}';
		
        
	  
	    

}


/**
 *  新增 
 */

function getIndex(matName,arr){
	for (var i=0;i<arr.length;i++){
		if (matName == arr[i])
			return i;
			
	}
	return -1;
}

function  addLineCheckItem(){
	initMaintAccount(false,400,200,'新增督导考核数据', '/master!!toAddSupervisorItems.jspa');
}

/**
 * 保存修改
 */
function saveChagCheckItem(){

	$('#datagrid').datagrid('endEdit', editorline);
	var rows = $('#datagrid').datagrid('getChanges');
	
	var superCheckItemArr=[];
	var superCheckItem={};
	
	for(var i=0;i<rows.length;i++){
		// 处理一行 数据
	
		// json 专程 str
		var lineJsonstr = JSON.stringify(rows[i]);
		var lineJsonstrTrim = lineJsonstr.substring(1,lineJsonstr.length-1);
		var lineJsonArr = lineJsonstrTrim.split(",");
		// 循环 口味数组
		for (var j=0;j<matgArr.length;j++){
			var index = "";
			if(lineJsonArr[j*4].indexOf("checkId")>0){
				
				// checkid1  checkid13
				if (lineJsonArr[j*4].split(":")[0].length==10){
					  index =lineJsonArr[j*4].substring(8,9);
				}else{
				     index =lineJsonArr[j*4].substring(8,10);
				}
			  
//				superCheckItem.checkId = lineJsonArr[j*4].split(":")[1].substring(1,lineJsonArr[j*4].split(":")[1].length-1);
				superCheckItem.checkId = parseInt(lineJsonArr[j*4].split(":")[1]);
				superCheckItem.checkValue = lineJsonArr[j*4+1].split(":")[1].substring(1,lineJsonArr[j*4+1].split(":")[1].length-1);
				superCheckItem.minPrice = lineJsonArr[j*4+2].split(":")[1].substring(1,lineJsonArr[j*4+2].split(":")[1].length-1);
				superCheckItem.maxPrice = lineJsonArr[j*4+3].split(":")[1].substring(1,lineJsonArr[j*4+3].split(":")[1].length-1);
				superCheckItem.matName = matgKeyArr[index];
				
				superCheckItem.custId = rows[i].custId;
//				superCheckItem.custName = rows[i].custName;
				
				superCheckItemArr.push(superCheckItem);
				superCheckItem={};
				
			}else{
				var leftnum= lineJsonArr.length - (j*4+6);
				
				for (var jj=0; jj<leftnum/3; jj++){
					

			       index =lineJsonArr[j*4+7+jj*3].substring(9,10);
			       superCheckItem.matName = matgKeyArr[index];
					superCheckItem.checkValue = lineJsonArr[j*4+6+jj*3].split(":")[1].substring(1,lineJsonArr[j*4+6+jj*3].split(":")[1].length-1);
					superCheckItem.minPrice = lineJsonArr[j*4+7+jj*3].split(":")[1].substring(1,lineJsonArr[j*4+7+jj*3].split(":")[1].length-1);
					superCheckItem.maxPrice = lineJsonArr[j*4+8+jj*3].split(":")[1].substring(1,lineJsonArr[j*4+8+jj*3].split(":")[1].length-1);
					
					superCheckItem.custId = rows[i].custId;
					superCheckItem.custName = rows[i].custName;
					
					superCheckItemArr.push(superCheckItem);
					superCheckItem={};
					
				}
			
				break;
				
			}
			;
			
		}
		
	}
	
	if (rows.length>0){
		
		$.ajax({
			   type: "POST",
			   url: appUrl + '/master!saveChagCheckItemCols.jspa',
			   data: {"supervisorCheckItemListjson":$.toJSON(superCheckItemArr)},
			   success: function(data){
//				   $("#datagrid").datagrid('load');
				   loadGrid(1,10);
				   editFlag = 0 ;
			   }
			});
		
	}
	else 
		$.messager.alert('Tips', '没有更改的数据!', 'warning');
	
	//alert(rows.length+' rows are changed!');
}


function excelCols() {
	$.messager.confirm('Confirm', '是否确定导出?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/master!itemsExportCols.jspa';
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
		}
	});
}


//excel导出终端客户数据
function excel() {
	$.messager.confirm('Confirm', '是否确定导出?', function(r) {
		if (r) {
			var form = window.document.forms[0];
//			form.action = appUrl + '/master!itemsExportCols.jspa';
			form.action = appUrl + '/master!itemsExport.jspa';
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
//			
//			$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
//			
//			var colsDataurl = appUrl + '/master!itemsExportCols.jspa';
//			
//			searchobj.custNumber = $("#custNumber").val();
//			searchobj.custName = encodeURIComponent($("#custName").val());
//			searchobj.channelId = $('#channelId').combobox("getValue");
//			searchobj.orgId = $("#orgId").val();
//			searchobj.stationUserId = $("#stationUserId").val();
//			searchobj.contactName = encodeURIComponent($("#contactName").val());
//			searchobj.custState = $("#custState").val();// $("#custState").combobox("getValue");
//			searchobj.custKunnr = $("#custKunnr").combogrid("getValue");
//			searchobj.custParent = $("#custParent").combogrid("getValue");
//			searchobj.custType = $("#custType").val();
//			searchobj.createOrgId = $("#createOrgId").val();
//			searchobj.createDateStart = $("#createDateStart").datebox("getValue");
//			searchobj.createDateEnd = $("#createDateEnd").datebox("getValue");
//			
//			$.ajax({  
//		        type : "post", 
////		        data: { page: pageNumber, rows: pageSize },
//		        data: searchobj,
//		         url : colsDataurl,
//		         async : false,  
//		         success : function(data){ 
//		        	  alert("hello");
//		         }
//			});
			
		}
	});
}

/**
 *  批量导入
 */

function importCheckItem() {

	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('导入目标', html);
}

var mask_;
/* 打开导入项目Excel对话框 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '批量导入',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	}
	$('#excelDialog').dialog(
			{
				modal : true,
				resizable : false,
				dragable : false,
				closable : false,
				open : function() {
					$('#excelDialog').css('padding', '0.8em');
					$('#excelDialog .ui-accordion-content').css('padding',
							'0.4em').height($('#excelDialog').height() - 100);
				},
				buttons : [ {
					text : '确定',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						if (/^.+\.(csv|CSV)$/.test(file)) {
							$.messager.progress();
							var action = '';
//							action = appUrl + "/master!ImportItemsCols.jspa";
							action = appUrl + "/master!ImportItemsCols2.jspa";
							var form = document.getElementById('fileForm');

							form.action = action;
							// "eventId="+processInstanceId;
							form.target = "hideFrame";
							form.submit();
						} else {
							$.messager.alert("提示", "请导入csv文件");
						}
						$.messager.progress('close');

					}
				}, {
					text : '取消',
					handler : function() {
						window.location.href = window.location.href;
						$('#excelDialog').dialog('close');

					}
				} ],

				width : document.documentElement.clientWidth * 0.28,
				height : document.documentElement.clientHeight * 0.50
			});
}

/**
 * 模糊搜索
 */
function search() {

	searchobj.custNumber = $("#custNumber").val();
	searchobj.custName = encodeURIComponent($("#custName").val());
	// allChannelName
	searchobj.allChannelName = encodeURIComponent($('#allchannel').combobox("getValue"));
	searchobj.channelId = $('#channelId').combobox("getValue");
	
	searchobj.orgId = $("#orgId").val();
	searchobj.stationUserId = $("#stationUserId").val();
	searchobj.contactName = encodeURIComponent($("#contactName").val());
	searchobj.custState = $("#custState").val();// $("#custState").combobox("getValue");
	searchobj.custKunnr = $("#custKunnr").combogrid("getValue");
	searchobj.custParent = $("#custParent").combogrid("getValue");
	searchobj.custType = $("#custType").val();
	searchobj.createOrgId = $("#createOrgId").val();
	searchobj.createDateStart = $("#createDateStart").datebox("getValue");
	searchobj.createDateEnd = $("#createDateEnd").datebox("getValue");
	//$("#datagrid").datagrid('reload');
	loadGrid(1,10);
	
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function viewInfo(id) {
	openWindow("终端门店信息查看", "/customerAction!viewCustomer.jspa?custId=" + id,
			1000, 480);
}

function editInfo(id, state, type) {
	openWindow("客户信息修改", "/customerAction!toUpdateCustomer.jspa?custId=" + id
			+ '&type=' + type, 1100, 480);
}
/**
 * 关闭终端门店
 * 
 * @param id
 */
function freezeKunnr(id, state) {
	var ids = [];
	ids.push(id);
	$("#ids").val(ids);
	if (state == "已关户") {
		$.messager.alert('Tips', '客户状态为已关闭状态，不需重复操作！', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '是否确定关闭终端门店?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/customerAction!closeCustomer.jspa?custState1=' + '4';
			form.target = "hideFrame";
			form.submit();

		}
	});
}

/**
 * 所属经分销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
// 二阶客户下拉查询
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}
// 创建窗口对象
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
// 关闭窗口
function closeWindow() {
	$("#hiddenWin").window('close');
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	orgflag=1;
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * 打开组织树
 */
function selectOrgTree0() {
	orgflag=0;
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	if (flag == 1) {
		document.getElementById('orgId01').value = selectedId;
		document.getElementById('orgName01').value = selectedName;
	} else {
		if (orgflag==1){
		    document.getElementById('orgId').value = selectedId;
		    document.getElementById('orgName').value = selectedName;
		}else{
		    document.getElementById('createOrgId').value = selectedId;
		    document.getElementById('createOrgName').value = selectedName;
		}
	}
	$('#custKunnr').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	
		$('#custParent').combogrid(
				{
					url : appUrl
							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
							+ $('#orgId').val()
				});
}
/**
 * 组织树选择完之后关闭
 */
function closeMaintEvent() {
	closeWindow();
}
/**
 * 选择业代
 */
function selectOrgTree4Station() {
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	openWindow('选择业务代表', '/batChangeAction!selectOrgTree4Station.jspa?node='
			+ node + '&orgName=' + orgName, 520, 460);
}
/**
 * 业代选择之后关闭
 */
function closeOrgTree() {
	closeWindow();
}
/**
 * 打开组织树
 */
function selectOrgTree1() {
	flag = 1;
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}

/*
 * 打开人员选择树
 */
function selectUser() {
	openWindow('选择业务人员', '/batChangeAction!selectOrgTreeUser.jspa', 550, 350);
}
/*
 * 城市经理、主管选择返回值
 */
function returnUser(userId, userName) {
	document.getElementById('stationUserId01').value = userId;
	document.getElementById('stationUserName01').value = userName;
}


function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table><tr>'
			+ '<td class="head" noWrap>类型选择</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="Z" checked>--终端门店--</option>'
			+ '<option value="T" selected>--二阶客户--</option>'
			+ '</select></td></tr>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportCustomerMouldCsv();> 1、下载模版</a></td></tr>'
			/*
			 * + '<tr><td></td><td><a class="easyui-linkbutton"
			 * style="color:blue" data-options="plain:true,iconCls:"icon-excel""
			 * onclick=javascript:exportCustomerHelps();>2、下载客户导入的辅助信息数据</a></td></tr>' + '<tr><td class="head" noWrap>所属组织</td><td>' + '<input
			 * class="easyui-validatebox" id="orgName01" name="orgName01"
			 * readonly/>' + '<button onclick="javascript:selectOrgTree1()">选择</button>' + '<input
			 * type="hidden" id="orgId01" name="orgId01"/></td></tr>' + '<tr><td class="head" noWrap>我司业务负责人</td><td>' + '<input
			 * class="easyui-validatebox" id="stationUserName01"
			 * name="stationUserName01" readonly/>' + '<button
			 * onclick="javascript:selectUser()">选择</button>' + '<input
			 * type="hidden" id="stationUserId01" name="stationUserId01"/></td></tr>'
			 */
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importCustomerCsv('批量导入', html);
}
// csv下载终端门店导入模板
function exportCustomerMouldCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form = window.document.forms[0];
				//document.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportMouldCsvWithCons.jspa';
			form.submit();
		}
	});

}
// excel下载终端门店导入辅助信息
function exportCustomerHelps() {
	$.messager.confirm('Confirm', '是否确定关下载辅助信息?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!exportCustomerHelps.jspa';
			form.submit();
		}
	});
}
// 批量导入终端门店信息
function importCustomerCsv(t, html) {
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
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/customerAction!importCustomerCsv.jspa";
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

/*
 * function importCustomerCsv(){ $.messager.confirm('Confirm', '是否确定批量导入终端门店?',
 * function(r) { if (r) { var form = window.document.forms[0]; form.action =
 * appUrl + '/customerAction!importCustomerCsv.jspa'; form.submit(); } }); }
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#custState').val('1');
	$('#orgId').val(50919);
	$('#createOrgId').val('');
	$('#createDateStart').datebox('setValue','');
	$('#createDateEnd').datebox('setValue','');
}
/**
 * 页面返回提示信息
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		// search();
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid('reload');

	}
}
/**
 *  模板导出
 */
function exportMould() {
	$.messager.alert('Tips', "需要考核的口味对应下面，填Y或N，如椰果原味需要考核，填写值为Y. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithCons.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  模板导出cols
 */
function exportMouldCols() {
	$.messager.alert('Tips', "需要考核的口味对应下面，填Y或N，如椰果原味需要考核，填写值为Y. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
//	form.action = appUrl + '/master!exportMouldCsvWithConsCols.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithConsCols2.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  导出数据excel
 */

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrBusinessAction!exportForExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}

/**
 *   -1 上一页  
 *   0 下一页
 *   1-totalPage 页
 * @param pageDirect
 */
function getPage(pageDirect){
	
	var flag=false;
	var  total= totalNum;
	if (pageDirect ==0){
		if ((pageNow)*10>total)
			$.messager.alert('Tips', "当前已为尾页！ ", 'info');
		else{
		pageNow =pageNow+1;
		flag =true;
		}
	}else if (pageDirect == -1){
		if ((pageNow)==1)
			$.messager.alert('Tips', "当前已为首页!", 'info');
		else{
		pageNow =pageNow-1;
		flag =true;
		}
	}
	
	if (flag){
		$('#datagrid').datagrid('getPager').data("pagination").options.pageNumber = pageNow;
		loadGrid(pageNow,10);
		
		$('#pageDetail').empty();
		$('#pageDetail').append("共有"+total+"条数据, 当前为第"+pageNow+"页");
		
		
	}
	
	
}