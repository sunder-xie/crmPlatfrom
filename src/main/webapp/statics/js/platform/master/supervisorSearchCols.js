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
	$('#pageDetail').append("����"+totalNum+"������, ��ǰΪ��"+1+"ҳ");
	loadCustKunnr();
	$('#hideFrame').bind('load', promgtMsg);
	// �ͻ����� ����
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
 * ����������������
 */
function loadCustKunnr() {
	// ���׿ͻ�
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
			title : '�ͻ����',
			width : 120
		}, {
			field : 'custName',
			title : '�ͻ�����',
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
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
}

/*******************************************************************************
 * �������������б�
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
						title : '��ѯ���',
						// url : appUrl + '/master!getSupervisorItems.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
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
									title : '����',
									width : setColumnWidth(0.18),
									hidden : true,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = " <a href='#' onclick=viewLicense('"
												+ kunnr + "')>֤��</a>";

										var scan = "|<a href='#' onclick=viewInfo('"
												+ kunnr + "')>����</a>";
										var edit = status == 1 ? "|<a href='#' onclick=editInfo('"
												+ kunnr + "')>�޸�ҵ����ϵ</a>"
												: "&nbsp;";

										return license + scan + edit;
									}
								}, {
									field : 'checkId',
									title : '�����id',
									width : setColumnWidth(0.05),
									hidden : true,
									sortable : true
								}, {
									field : 'custId',
									title : '�ŵ�ID',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'custName',
									title : '�ŵ�',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'orgName',
									title : '�ŵ���֯',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'channelName',
									title : '����',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'createName',
									title : '����������',
									width : setColumnWidth(0.08),
									sortable : true
								}, {
									field : 'createTime',
									title : '����ʱ��',
									width : setColumnWidth(0.05),
									sortable : true
								} ] ],
//						columns : [ [
//{field:'checkId1',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue1',title:'Ҭ��ԭζ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice1',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice1',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true}						] ],
						toolbar : [ "-", {
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
								excelCols();
							}
						}, "-", {
							text : 'ģ������',
							iconCls : 'icon-excel',
							handler : function() {
								exportMouldCols();
							}
						}, "-", {
							text : '��������',
							iconCls : 'icon-add',
							handler : function() {
								importCheckItem();
							}
						},  "-" , {
							text : '��ʼ�޸�',
							iconCls : 'icon-edit',
							handler : function() {
								clickFlag();
							}
						},"-" , {
							text : '�����޸�',
							iconCls : 'icon-edit',
							handler : function() {
								saveChagCheckItem();
							}
						}, "-", {
							text : '�����̻�����дY �� N'
						
						}, "-"  ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
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
//	    	s = s + "{field:\'"+checkId+"\',title:'checkId',width:160,hidden:true},{field:\'"+checkValue+"\',title:\'"+matgArr[i]+"\',editor:{type:'checkbox',options:{on:'Y',off:'N'}},width:160},{field:\'"+minPrice+"\',title:'��С�۸�',editor:'numberbox',width:120}, {field:\'"+maxPrice+"\',title:'���۸�',editor:'numberbox',width:120},";
	    	s = s + "{field:\'"+checkId+"\',title:'checkId',width:160,hidden:true},{field:\'"+checkValue+"\',title:\'"+matgArr[i]+"\',editor:{type:'combobox',options:{data:[{value:'Y',text:'Y'},{value:'N',text:'N'}]}},width:160},{field:\'"+minPrice+"\',title:'��С�۸�',editor:'numberbox',width:120,hidden:true}, {field:\'"+maxPrice+"\',title:'���۸�',editor:'numberbox',width:120,hidden:true},";
	    	  
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
	        	 // ���������
	        	
	        	 
	        	 pageNow =pageNumber;
	        	 totalNum = data.total;
	        	 
	        	   gridArr=[];
	        		loadJsondataStr=loadJsondataStr+"\"total\":"+data.total+",\"rows\":[";
	  			  
					linedata="";
				      for (var i=0;i<data.rows.length;i++){
				    	  // ����ͬһ���ŵ�,
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

	
		// ģ������
		
//	    var temp = '{"total":1,"rows":[{"checkId0":"41","checkValue0":"Y","minPrice0":1,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101}]}';
//	    var temp = '{"total":1,"rows":[{"checkId'+0+'":"44","checkValue0":"Y","minPrice0":1,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101	}]}';
//	    var temp = '{"total":1,"rows":[{"checkId'+0+'":"41","checkValue0":Y,"minPrice0":undefined,"maxPrice0":10,"checkId1":"410","checkValue1":"Y","minPrice1":11,"maxPrice1":101	}]}';
		
        
	  
	    

}


/**
 *  ���� 
 */

function getIndex(matName,arr){
	for (var i=0;i<arr.length;i++){
		if (matName == arr[i])
			return i;
			
	}
	return -1;
}

function  addLineCheckItem(){
	initMaintAccount(false,400,200,'����������������', '/master!!toAddSupervisorItems.jspa');
}

/**
 * �����޸�
 */
function saveChagCheckItem(){

	$('#datagrid').datagrid('endEdit', editorline);
	var rows = $('#datagrid').datagrid('getChanges');
	
	var superCheckItemArr=[];
	var superCheckItem={};
	
	for(var i=0;i<rows.length;i++){
		// ����һ�� ����
	
		// json ר�� str
		var lineJsonstr = JSON.stringify(rows[i]);
		var lineJsonstrTrim = lineJsonstr.substring(1,lineJsonstr.length-1);
		var lineJsonArr = lineJsonstrTrim.split(",");
		// ѭ�� ��ζ����
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
		$.messager.alert('Tips', 'û�и��ĵ�����!', 'warning');
	
	//alert(rows.length+' rows are changed!');
}


function excelCols() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/master!itemsExportCols.jspa';
			form.submit();
			$.messager.alert('Tips', '���ڵ���,���Ժ�!', 'warning');
		}
	});
}


//excel�����ն˿ͻ�����
function excel() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������?', function(r) {
		if (r) {
			var form = window.document.forms[0];
//			form.action = appUrl + '/master!itemsExportCols.jspa';
			form.action = appUrl + '/master!itemsExport.jspa';
			form.submit();
			$.messager.alert('Tips', '���ڵ���,���Ժ�!', 'warning');
//			
//			$.messager.alert('Tips', '���ڵ���,���Ժ�!', 'warning');
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
 *  ��������
 */

function importCheckItem() {

	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>ѡ���ļ��ϴ���</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('����Ŀ��', html);
}

var mask_;
/* �򿪵�����ĿExcel�Ի��� */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '��������',
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
					text : 'ȷ��',
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
							$.messager.alert("��ʾ", "�뵼��csv�ļ�");
						}
						$.messager.progress('close');

					}
				}, {
					text : 'ȡ��',
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
 * ģ������
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
	openWindow("�ն��ŵ���Ϣ�鿴", "/customerAction!viewCustomer.jspa?custId=" + id,
			1000, 480);
}

function editInfo(id, state, type) {
	openWindow("�ͻ���Ϣ�޸�", "/customerAction!toUpdateCustomer.jspa?custId=" + id
			+ '&type=' + type, 1100, 480);
}
/**
 * �ر��ն��ŵ�
 * 
 * @param id
 */
function freezeKunnr(id, state) {
	var ids = [];
	ids.push(id);
	$("#ids").val(ids);
	if (state == "�ѹػ�") {
		$.messager.alert('Tips', '�ͻ�״̬Ϊ�ѹر�״̬�������ظ�������', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '�Ƿ�ȷ���ر��ն��ŵ�?', function(r) {
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
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
// ���׿ͻ�������ѯ
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}
// �������ڶ���
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
// �رմ���
function closeWindow() {
	$("#hiddenWin").window('close');
}
/**
 * ����֯��
 */
function selectOrgTree() {
	orgflag=1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ����֯��
 */
function selectOrgTree0() {
	orgflag=0;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
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
 * ��֯��ѡ����֮��ر�
 */
function closeMaintEvent() {
	closeWindow();
}
/**
 * ѡ��ҵ��
 */
function selectOrgTree4Station() {
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	openWindow('ѡ��ҵ�����', '/batChangeAction!selectOrgTree4Station.jspa?node='
			+ node + '&orgName=' + orgName, 520, 460);
}
/**
 * ҵ��ѡ��֮��ر�
 */
function closeOrgTree() {
	closeWindow();
}
/**
 * ����֯��
 */
function selectOrgTree1() {
	flag = 1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}

/*
 * ����Աѡ����
 */
function selectUser() {
	openWindow('ѡ��ҵ����Ա', '/batChangeAction!selectOrgTreeUser.jspa', 550, 350);
}
/*
 * ���о�������ѡ�񷵻�ֵ
 */
function returnUser(userId, userName) {
	document.getElementById('stationUserId01').value = userId;
	document.getElementById('stationUserName01').value = userName;
}


function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table><tr>'
			+ '<td class="head" noWrap>����ѡ��</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="Z" checked>--�ն��ŵ�--</option>'
			+ '<option value="T" selected>--���׿ͻ�--</option>'
			+ '</select></td></tr>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportCustomerMouldCsv();> 1������ģ��</a></td></tr>'
			/*
			 * + '<tr><td></td><td><a class="easyui-linkbutton"
			 * style="color:blue" data-options="plain:true,iconCls:"icon-excel""
			 * onclick=javascript:exportCustomerHelps();>2�����ؿͻ�����ĸ�����Ϣ����</a></td></tr>' + '<tr><td class="head" noWrap>������֯</td><td>' + '<input
			 * class="easyui-validatebox" id="orgName01" name="orgName01"
			 * readonly/>' + '<button onclick="javascript:selectOrgTree1()">ѡ��</button>' + '<input
			 * type="hidden" id="orgId01" name="orgId01"/></td></tr>' + '<tr><td class="head" noWrap>��˾ҵ������</td><td>' + '<input
			 * class="easyui-validatebox" id="stationUserName01"
			 * name="stationUserName01" readonly/>' + '<button
			 * onclick="javascript:selectUser()">ѡ��</button>' + '<input
			 * type="hidden" id="stationUserId01" name="stationUserId01"/></td></tr>'
			 */
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importCustomerCsv('��������', html);
}
// csv�����ն��ŵ굼��ģ��
function exportCustomerMouldCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form = window.document.forms[0];
				//document.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportMouldCsvWithCons.jspa';
			form.submit();
		}
	});

}
// excel�����ն��ŵ굼�븨����Ϣ
function exportCustomerHelps() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ�������ظ�����Ϣ?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!exportCustomerHelps.jspa';
			form.submit();
		}
	});
}
// ���������ն��ŵ���Ϣ
function importCustomerCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
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
									text : 'ȷ��',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("��ʾ", "��ѡ��������֯");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("��ʾ",
										 * "��ѡ��ҵ������"); return; }
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
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/*
 * function importCustomerCsv(){ $.messager.confirm('Confirm', '�Ƿ�ȷ�����������ն��ŵ�?',
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
 * ҳ�淵����ʾ��Ϣ
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
 *  ģ�嵼��
 */
function exportMould() {
	$.messager.alert('Tips', "��Ҫ���˵Ŀ�ζ��Ӧ���棬��Y��N����Ҭ��ԭζ��Ҫ���ˣ���дֵΪY. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithCons.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  ģ�嵼��cols
 */
function exportMouldCols() {
	$.messager.alert('Tips', "��Ҫ���˵Ŀ�ζ��Ӧ���棬��Y��N����Ҭ��ԭζ��Ҫ���ˣ���дֵΪY. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
//	form.action = appUrl + '/master!exportMouldCsvWithConsCols.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithConsCols2.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  ��������excel
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
 *   -1 ��һҳ  
 *   0 ��һҳ
 *   1-totalPage ҳ
 * @param pageDirect
 */
function getPage(pageDirect){
	
	var flag=false;
	var  total= totalNum;
	if (pageDirect ==0){
		if ((pageNow)*10>total)
			$.messager.alert('Tips', "��ǰ��Ϊβҳ�� ", 'info');
		else{
		pageNow =pageNow+1;
		flag =true;
		}
	}else if (pageDirect == -1){
		if ((pageNow)==1)
			$.messager.alert('Tips', "��ǰ��Ϊ��ҳ!", 'info');
		else{
		pageNow =pageNow-1;
		flag =true;
		}
	}
	
	if (flag){
		$('#datagrid').datagrid('getPager').data("pagination").options.pageNumber = pageNow;
		loadGrid(pageNow,10);
		
		$('#pageDetail').empty();
		$('#pageDetail').append("����"+total+"������, ��ǰΪ��"+pageNow+"ҳ");
		
		
	}
	
	
}