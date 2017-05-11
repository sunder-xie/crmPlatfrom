var nextUser;
var processInstanceId;
var addressCount = $("#addressListSize").val();
var brandCount = $("#brandListSize").val();
var acountCount = $("#acountListSize").val() * 2;
var areaCount = $("#kunnrSalesAreaListSize").val();
var goalCount = $("#bCustomerTargetListSize").val();
var killSalesArea =[];
var kunnrSalesAreaList = [];
var month = [];
$(document)
		.ready(
				function() {
					initHide();
					if($('#konzs').val()){
						$('.type2J').show();
					}else{
						$('.type2J').hide();
					}
					$('#hideFrame').bind('load', promgtMsg);
					// ����ͨ�г���
					$('#maximum02')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
												+ 'carType'
									});
					for ( var i = 1; i <= addressCount; i++) {
						$('#maximum_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'carType'
										});
					}

					// ��������
					function getRegion(level) {
						$('#region' + level)
								.combobox(
										{
											editable : false,
											url : appUrl
													+ '/salesMsgAction!searchRegion.jspa?level='
													+ level + '&pid='
													+ $('#region').val(),
											textField : 'text',
											valueField : 'id',
											onLoadSuccess : function() {
												$('#region')
														.val(
																$(
																		'#region'
																				+ level)
																		.combobox(
																				'getValue'));
												++level;
												getRegion(level);
											},
											onSelect : function(re) {
												$('#region').val(re.id);
												getRegion(level);
											}

										});

					}
					;
					getRegion(1);

					// �Ա�
					$('#sex').combobox({
						valueField : 'value',
						textField : 'text',
						data : [ {
							value : '',
							text : '��ѡ��'
						} ,{
							value : 'male',
							text : '��'
						}, {
							value : 'female',
							text : 'Ů'
						} ]
					});
					$('#brands').combobox({
						valueField : 'value',
						textField : 'text',
						multiple : true,
						editable : false,
						data : [ {
							value : 'X',
							text : '��ƮƮ'
						}, {
							value : 'L',
							text : '����԰'
						}, {
							value : 'M',
							text : 'MECO'
						}],
						onLoadSuccess : function() {
							$('#brands').combobox('setText', $('#brandsC').val());
						}
					});
					// ��˰������
					$('#kverm').combobox({
						valueField : 'text',
						textField : 'text',
						data : [ {
							value : 'С��ģ��˰��',
							text : 'С��ģ��˰��'
						}, {
							value : 'һ����˰��',
							text : 'һ����˰��'
						} ],
						onSelect : function(r) {
							refreshLicense();
						}
					});

					// �ͻ����� ����
					$('#channelId')
							.combobox(
									{
										url : appUrl
												+ '/kunnrAction!getChannel.jspa?channelName='
												+ 'K',
										valueField : 'channelId',
										textField : 'channelName'
									});

					// �ͻ���Ŀ��
					$('#ktokd').combobox({
						url : appUrl + '/kunnrAction!getKtokd.jspa',
						valueField : 'itemValue',
						textField : 'itemValue'
					});

					// �ϼ������̲�ѯ
					/*$('#konzs').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/kunnrAction!kunnrSearch.jspa',
						idField : 'kunnr',
						textField : 'name1',
						columns : [ [ {
							field : 'kunnr',
							title : '�����̱��',
							width : 120
						}, {
							field : 'name1',
							title : '����',
							width : 250
						} ] ],
						toolbar : '#toolbarKonzs',
						onSelect : function(index, record) {
							if ($('#konzs').combobox("getValue")) {
								show();
							} else {
								hide();
							}
						}
					});*/

					// ���۴���
					$('#vkgrp').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/salesMsgAction!getT171tJsonList.jspa',
						idField : 'bzirk',
						textField : 'bztxt',
						columns : [ [ {
							field : 'bzirk',
							title : '�������',
							width : 100
						}, {
							field : 'bztxt',
							title : '����',
							width : 120
						} ] ],
						toolbar : '#toolbarVkgrp',
						onSelect : function(index, record) {
							$('#bzirk').combogrid('setValue', '');
							$('#vkbur').combobox('setValue', '');
							$('#bzirk').combogrid({
								disabled : false
							});
							loadBzirk(record.bzirk);
						}
					});

					// ����ʡ��
					var bzirk = $('#bzirk').combogrid("getValue");
					loadBzirk(bzirk);
					if (bzirk == '') {
						$('#bzirk').combogrid({
							disabled : true
						});
						$('#vkbur').combobox({
							disabled : true
						});
					}

					function loadBzirk(bzirk) {
						$('#bzirk')
								.combogrid(
										{
											panelHeight : 280,
											panelWidth : 420,
											pagination : false,
											method : 'post',
											singleSelect : true,
											url : appUrl
													+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
													+ $('#vkgrp').combogrid(
															'getValue'),
											idField : 'vkgrp',
											textField : 'bezei',
											columns : [ [ {
												field : 'vkgrp',
												title : 'ʡ�ݱ��',
												width : 100
											}, {
												field : 'bezei',
												title : '����',
												width : 120
											} ] ],
											toolbar : '#toolbarBzirk',
											onSelect : function(index, record) {
												$('#vkbur').combobox(
														'setValue', '');
												$('#vkbur').combobox({
													disabled : false
												});
												loadVkbur(vkbur);
											}
										});
					}
					var vkbur = $('#bzirk').combogrid('getValue');
					if (vkbur == '') {
						$('#vkbur').combobox({
							disabled : true
						});
					} else {
						loadVkbur(vkbur);
					}
					// ���۲���
					function loadVkbur(vkbur) {
						$('#vkbur')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvkbtJsonListByTvbvk.jspa?vkgrp='
													+ $('#bzirk').combogrid(
															'getValue'),
											valueField : 'vkbur',
											textField : 'bezei'
										});
					}
					// ��˾
					$('#bukrs').combobox({
						url : appUrl + '/salesMsgAction!getTvkoJsonList.jspa',
						valueField : 'bukrs',
						textField : 'bukrs',
						onSelect : function(record) {
							$("#vkorg").val(record.vkorg);
						}
					});

					// Ʒ��
					/*
					 * $('#categories').combogrid({ panelWidth : 400, panelHight :
					 * 280, idField : 'matnr', textField : 'maktx', pagination :
					 * true,// �Ƿ��ҳ collapsible : false,// �Ƿ���۵��� method :
					 * 'post', multiple : true, singleSelect : false, url :
					 * appUrl + '/goal/goalAction!getMatList.jspa', columns : [ [ {
					 * field : 'matnr', title : '���Ϻ�', width : 120 }, { field :
					 * 'maktx', title : '��������', align : 'center', width : 120 }, {
					 * field : 'bezei', title : '����������', align : 'center', width :
					 * 100 } ] ], toolbar : '#toolbarCategories'
					 * 
					 * }); $('#categories').combogrid('setValues',
					 * $("#categoriesHide").val().split(","));
					 */
					for ( var i = 1; i <= areaCount; i++) {
						// ����
						$('#werks_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getT001wJsonList.jspa',
											valueField : 'werks',
											textField : 'name1',
											onSelect : function(title, index) {
												var werks = title.werks;
												if (werks != '6000') {
													var str = werks
															.substr(0, 2);
													$('#vsbed_' + i).combobox(
															'setValue', str);
												}
											}
										});

						// װ������
						$('#vsbed_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'zyc'
										});
					}
					// ��������Ȩ :01��KA�ͻ���02-��KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01��KA�ͻ�'
						}, {
							'id' : '02',
							'txt' : '02-��KA'
						} ],
						valueField : 'id',
						textField : 'txt'
					});

					// ���ϻؿ�
					if (acountCount > 0) {
						for ( var i = 1; i <= acountCount; i++) {

							$('#bonus_' + i)
									.combobox(
											{
												url : appUrl
														+ '/salesMsgAction!getTvbotJsonList.jspa',
												valueField : 'bonus',
												textField : 'vtext'
											});
						}
					} else {
						$('#bonus_1')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvbotJsonList.jspa',
											valueField : 'bonus',
											textField : 'vtext'
										});
						acountCount++;
					}

					// ֤��
//					$("#tabs").tabs({
//						onSelect : function(title, index) {
//							if (title == '֤���ϴ�') {
//								var currTab = $('#tabs').tabs('getTab', title);
//								refreshLicense(currTab);
//							}
//						}
//					});
					// Ŀ�����·�
					month = [ {
						'txt' : '01',
						'value' : '1��'
					}, {
						'txt' : '02',
						'value' : '2��'
					}, {
						'txt' : '03',
						'value' : '3��'
					}, {
						'txt' : '04',
						'value' : '4��'
					}, {
						'txt' : '05',
						'value' : '5��'
					}, {
						'txt' : '06',
						'value' : '6��'
					}, {
						'txt' : '07',
						'value' : '7��'
					}, {
						'txt' : '08',
						'value' : '8��'
					}, {
						'txt' : '09',
						'value' : '9��'
					}, {
						'txt' : '10',
						'value' : '10��'
					}, {
						'txt' : '11',
						'value' : '11��'
					}, {
						'txt' : '12',
						'value' : '12��'
					} ];

					for ( var i = 1; i <= goalCount; i++) {

						// �·�
						$('#month_' + i).combobox({
							data : month,
							valueField : 'txt',
							textField : 'value'
						});
						// ���
						$('#year_' + i).combobox({
							url : appUrl + '/goalAction!getDictTypeList.jspa',
							textField : 'itemName',
							valueField : 'itemName'
						});

						// ��Ӧ����
						$('#wid_' + i)
								.combogrid(
										{
											panelWidth : 400,
											panelHight : 600,
											idField : 'mvgr1',
											textField : 'bezei',
											pagination : true,// �Ƿ��ҳ
																// collapsible :
																// false,//
											// �Ƿ���۵���
											method : 'post',
											url : appUrl
													+ '/goal/goalAction!getMaterielList.jspa',
											columns : [ [ {
												field : 'ck',
												checkbox : true,
												hidden : true
											}, {
												field : 'mvgr1',
												title : '������',
												align : 'center',
												width : 120
											}, {
												field : 'bezei',
												title : '����������',
												align : 'center',
												width : 200
											} ] ],
											toolbar : '#toolbar_' + i
										});
						$('#search_' + i).searchbox({
							searcher : function(value, name) {
								search(value, i);
							},
							prompt : '����д���ϺŻ�������'
						});
					}
				});
// ��Ӧ����toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}

// ˢ��֤��ҳ��
function refreshLicense() {
	$('#panel').panel({    
	    href : appUrl
	           + '/kunnrAction!displayLicensePre.jspa?dictTypeName='
		       + $("#kverm").combobox('getValue')    
	});
//	$('#tabs')
//			.tabs(
//					'update',
//					{
//						tab : currTab ? currTab : $('#tabs').tabs('getTab',
//								'֤���ϴ�'),
//						options : {
//							href : appUrl
//									+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//									+ $("#kverm").combobox('getValue')
//						}
//					});
}

// ������һ�� ����Ƿ����ϼ�������
function checkKonzs(count) {
	if (!$('#konzs').val()) {
		$.messager.alert('Tips', 'û��ѡ���ϼ�������,��˲��ܶ�����һ��', 'warning');
		$("#flag_" + count).attr("checked", false);
	}
}

// �ϼ������̲�ѯ��
function searcherKonzs(name1) {
	var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#konzs').combogrid("grid").datagrid('reload');
}

// ������֯��ѯ��
function searcherVkorg(vkorg) {
	var queryParams = $('#vkorg').combogrid("grid").datagrid('options').queryParams;
	queryParams.vkorg = encodeURIComponent(vkorg);
	$('#vkorg').combogrid("grid").datagrid('reload');
}

// ʡ
function searcherBzirk(bztxt) {
	var queryParams = $('#bzirk').combogrid("grid").datagrid('options').queryParams;
	queryParams.bztxt = encodeURIComponent(bztxt);
	$('#bzirk').combogrid("grid").datagrid('reload');
}

// ����
function searcherKvgr1(bezei) {
	var queryParams = $('#kvgr1').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr1').combogrid("grid").datagrid('reload');
}

// ����
function searcherKvgr2(bezei) {
	var queryParams = $('#kvgr2').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr2').combogrid("grid").datagrid('reload');
}

// ��˾
function searcherBukrs(butxt) {
	var queryParams = $('#bukrs').combogrid("grid").datagrid('options').queryParams;
	queryParams.butxt = encodeURIComponent(butxt);
	$('#bukrs').combogrid("grid").datagrid('reload');
}

// Ʒ��
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#categories').combogrid("grid").datagrid('reload');
}
function submit() {
	    array2json();
//		if(acountCount>0){
//			for ( var i = 1; i <= acountCount; i++) {
//				if(i%2!=0){
//				if($('#text0_'+i).val()==''&&$('#text01_'+i).val()==''&&$('#text02_'+i).val()==''){
//					$.messager.alert('Tips', '��ҵ�ۿ۵�SAPδ��д!', 'warning');
//					return;
//				}
//				if($('#text1_'+i).datebox('getValue')==''){
//					$.messager.alert('Tips', '��ҵ�ۿ۵Ŀ�ʼ��Ч����δ��д!', 'warning');
//					return;
//				}
//                if($('#text2_'+i).datebox('getValue')==''){
//                	$.messager.alert('Tips', '��ҵ�ۿ۵���Ч��ֹ����δ��д!', 'warning');
//                	return;
//				}
//				}
//			}
//		}
		if(areaCount>0){
		for ( var i = 1; i <= areaCount; i++) {
		 if ($("#vkorg_" + i).val() != $('#bukrs').combobox('getValue')) {
			$.messager.alert('Tips', "������֯��һ��,�����²�����", 'warning');
			return;
		 }
		}}
		$.messager.confirm('Confirm', '��ȷ�Ͼ�����������Ϣ,����?', function(r) {
			if(r){
				$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/kunnrAction!updateKunnrSapMeg.jspa?bukrs="+$('#bukrs').combobox('getValue');
			form.target = "hideFrame";
			form.submit();
			}
			
		});
}


// �����ۿ�һ��
function addAcount() {
	acountCount++;
	var html = '';
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAcount('
			+ acountCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;���ϻؿ���:';
	html += '<input class="easyui-combobox" id="bonus_' + acountCount
			+ '"></td>';
	html += '<td class="head" noWrap>';
	html += '�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeA_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeB_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeC_'
			+ acountCount + '">'
			+'&nbsp;Э���(Ԫ):<input class="easyui-numberbox" id="typeMoney_'+ acountCount + '" onchange="changeRow('+acountCount+')"  style="width:60px"></td>';
	if ($('#konzs').val()) {
		html += '<td class="head" noWrap> <div class="type2J">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+'</div></td>';
	} else {
		html += '<td class="head" noWrap> <div class="type2J" style="display:none;">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+'</div></td>';
	}
	html += '<td width="20%">&nbsp;</td><td width="20%">&nbsp;</td><td width="20%">&nbsp;</td>';
	html += '</tr>';
	acountCount = acountCount + 1;
	var trCount = acountCount - 1;
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap>(SAPЭ���)�·�:<input class="easyui-validatebox" id="text0_'
			+ trCount + '" validType="length[0,20]">';
	html += '<td noWrap><font style="font-weight:bold;color:#006b8a">����:<input class="easyui-validatebox" id="text01_'
		        + trCount + '" validType="length[0,20]">�귵:<input class="easyui-validatebox" id="text02_'
		        + trCount + '" validType="length[0,20]">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;��ʼ��Ч����:</font></td>';
	html += '<td  noWrap><div><font style="font-weight:bold;color:#006b8a">'
			+ '<input class="easyui-datebox" id="text1_' + trCount
			+ '" data-options="editable:false">';
	html += '&nbsp;��Ч��ֹ����:</font><input class="easyui-datebox" id="text2_'
			+ trCount + '" data-options="editable:false"></div></td>';
	html += '</tr>';
	$('#salesAcountTable').append(html);
	$.parser.parse($('#salesAcountTr_' + acountCount));

	// ���ϻؿ�
	$('#bonus_' + trCount).combobox({
		url : appUrl + '/salesMsgAction!getTvbotJsonList.jspa',
		valueField : 'bonus',
		textField : 'vtext'
	});
}
function change() {
	if ($('#konzs').val()) {
		if ($('#typeMoney_1').val()) {
			var num = $('#typeMoney_1').val() * 1;
			$('#type2Money_1').numberbox('setValue', num);

		} else {
			$('#typeMoney_1').numberbox('setValue', 0);
			var num = $('#typeMoney_1').val() * 1;
			$('#type2Money_1').numberbox('setValue', num);
		}
	}
}
function changeRow(count) {
	if ($('#konzs').val()) {
		if ($('#typeMoney_' + count).val()) {
			var num = $('#typeMoney_' + count).val() * 1;
			$('#type2Money_' + count).val(num);
			//$('#type2Money_' + count).numberbox('setValue', num);

		} else {
			$('#typeMoney_' + count).numberbox('setValue', 0);
			var num = $('#typeMoney_' + count).val() * 1;
			$('#type2Money_' + count).val(num);
			//$('#type2Money_' + count).numberbox('setValue', num);
		}
	}
}
// ɾ���ۿ�һ��
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {
		var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		$("#salesAcountTr_" + n).remove();
	}
}

// ɾ���ۿ�һ��
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {

		$("#killAcount").val(
				$("#killAcount").val() + ',' + $("#typeAId_" + count).val()
						+ ',' + $("#typeBId_" + count).val() + ','
						+ $("#typeCId_" + count).val() + ','
						+ $("#type2AId_" + count).val() + ','
						+ $("#type2BId_" + count).val() + ','
						+ $("#type2CId_" + count).val());
		var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		$("#salesAcountTr_" + n).remove();
	}
}
// �������۷�Χһ��
function addArea(obj,areaCount) {
	//areaCount++;
	var html = '';
	html += '<tr id="saleAreaTr_' + areaCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeArea('
			+ areaCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></td>';
	html += '<td class="head" noWrap>������֯:</td>';
	html += '<td><input type="hidden" id="vkorg_'
			+ areaCount
			+ '" value="'
			+ obj.vkorg
			+ '" style="width:40px" readonly ></input>'
			+ '<input class="easyui-validatebox" id="vkorgTxt_'
			+ areaCount
			+ '" value="'
			+ obj.vkorgTxt
			+ '" data-options="required:true" style="width:40px" readonly ></input></td>';
	html += '<td class="head" noWrap>��������:</td>';
	html += '<td><input  type="hidden" id="vtweg_' + areaCount + '" value="'
			+ obj.vtweg + '" readonly>'
			+ '<input  class="easyui-validatebox" id="vtwegTxt_' + areaCount
			+ '" value="' + obj.vtwegTxt
			+ '" data-options="required:true" readonly></td>';
	html += '<td class="head" noWrap>��Ʒ��:</td>';
	html += ' <td><input  type="hidden" id="spart_' + areaCount + '" value="'
			+ obj.spart + '" readonly></input>'
			+ '<input  class="easyui-validatebox" id="spartTxt_' + areaCount
			+ '" value="' + obj.spartTxt
			+ '" data-options="required:true" readonly></input></td>';
	html += '<td class="head" noWrap>��������:</td>';
	html += '<td><input class="easyui-combobox" id="werks_' + areaCount
			+ '"  data-options="required:true,editable:false"></td>';
	html += '<td class="head" noWrap>װ������:</td>';
	html += '<td><input class="easyui-combobox" id="vsbed_'
			+ areaCount
			+ '"  data-options="required:true" style="width:60px" validType="length[2,2]"></td>';
	html += '</tr>';
	$('#saleAreaTable').append(html);
	$.parser.parse($('#saleAreaTr_' + areaCount));
	// ����
	var areaCounts = areaCount;
	$('#werks_' + areaCount).combobox({
		url : appUrl + '/salesMsgAction!getT001wJsonList.jspa',
		valueField : 'werks',
		textField : 'name1',
		onSelect : function(title, index) {
			var werks = title.werks;
			if (werks != '6000') {
				/*$.messager.alert('Tips', "�����ѡ,�����²�����", 'warning');
				$('#werks_' + areaCounts).combobox('setValue', '');
				$('#vsbed_' + areaCounts).combobox('setValue', '');
				return;
			} else {*/
				var str = werks.substr(0, 2);
				$('#vsbed_' + areaCounts).combobox('setValue', str);
			}
		}
	});
	// װ������
	$('#vsbed_' + areaCounts).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'zyc'
			});
}
function changeVkorg(count){
	$('#vkorg_'+count).val($('#vkorgTxt_'+count).val());
}
// �h�����۷�Χһ��
function removeArea(i) {
	if ($("#saleAreaTr_" + i)) {
		killSalesArea.push({
			"id" : $("#salesAreaId_" + i).val(),
			"vkorg" : $("#vkorg_" + i).val(),
			"vkorgTxt" : $("#vkorgTxt_" + i).val(),
			"spart" : $("#spart_" + i).val(),
			"spartTxt" : $("#spartTxt_" + i).val(),
			"werks" : $("#werks_" + i).combobox('getValue'),
			"vsbed" : $("#vsbed_" + i).combobox('getValue'),
			"vtweg" : $("#vtweg_" + i).val(),
			"vtwegTxt" : $("#vtwegTxt_" + i).val()
		});
		$("#killSalesArea").val($.toJSON(killSalesArea));
		$("#saleAreaTr_" + i).remove();
	}
}

// ��Ӧ����toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}

// ��ַƷ��array->json
function array2json() {
	var kunnrAcountList=[];
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			if (document.getElementById("salesAcountTr_" + i)) {
				if ($("#bonus_" + i).combobox("getValue")) {
					var flag;
					if ($("#flag_" + i).attr("checked")) {
						flag = 'Y';
					} else {
						flag = 'N';
					}
					kunnrAcountList.push({
						"typeA" : $("#typeA_" + i).val(),
						"typeAId" : $("#typeAId_" + i).val(),
						"typeB" : $("#typeB_" + i).val(),
						"typeBId" : $("#typeBId_" + i).val(),
						"typeC" : $("#typeC_" + i).val(),
						"typeCId" : $("#typeCId_" + i).val(),
						"typeMoney":$('#typeMoney_'+i).val(),
						"type2A" : $("#type2A_" + i).val(),
						"type2AId" : $("#type2AId_" + i).val(),
						"type2B" : $("#type2B_" + i).val(),
						"type2BId" : $("#type2BId_" + i).val(),
						"type2C" : $("#type2C_" + i).val(),
						"type2CId" : $("#type2CId_" + i).val(),
						"bonus" : $("#bonus_" + i).combobox("getValue"),
						// "vtext" : $("#vtext_" + i).val(),
						"flag" : flag,
						"type2Money":$('#typeMoney_'+i).val(),
						//"acountSap" : $("#text0_" + i).val(),
						"startDate" : $("#text1_" + i).datebox("getValue"),
						"endDate" : $("#text2_" + i).datebox("getValue"),
						"acountSapA" : $("#text0_" + i).val(),
						"acountSapB" : $("#text01_" + i).val(),
						"acountSapC" : $("#text02_" + i).val()
					});
				}
			}
		}
	}

	for ( var i = 1; i <= areaCount; i++) {
		if (document.getElementById("saleAreaTr_" + i)) {
			kunnrSalesAreaList.push({
				"id" : $("#salesAreaId_" + i).val(),
				"vkorg" : $("#vkorg_" + i).val(),
				"vkorgTxt" : $("#vkorgTxt_" + i).val(),
				"spart" : $("#spart_" + i).val(),
				"spartTxt" : $("#spartTxt_" + i).val(),
				"werks" : $("#werks_" + i).combobox('getValue'),
				"vsbed" : $("#vsbed_" + i).combobox('getValue'),
				"vtweg" : $("#vtweg_" + i).val(),
				"vtwegTxt" : $("#vtwegTxt_" + i).val()
			});
		}
	}
	$("#kunnrAcountList").val($.toJSON(kunnrAcountList));
	$("#kunnrSalesAreaList").val($.toJSON(kunnrSalesAreaList));
}

// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
		});
	}
}

// ��ʼ��ʱһ��Ҫ��һ��
function initHide() {
	$('.type2J').hide();
}

// ����
function hide() {
	$('.type2J').hide();
}

// ����
function show() {
	$('.type2J').show();
}
/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindow4Post('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
/**
 * ��֯��ѡ����֮��ر�
 */
function closeOrgTree() {
	$("#nextUserDialog").window('close');
}
/*
 * ����Աѡ����
 */
function selectManager() {
	userRole = 'M';
	initMaintWindow4Post('ѡ����о���', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * ����Աѡ����
 */
function selectDirectory() {
	userRole = 'D';
	initMaintWindow4Post('ѡ��ͻ�����', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * ���о���������ѡ�񷵻�ֵ
 */
function returnUser(userId, userName) {
	if (userRole == 'M') {
		document.getElementById('businessManagerId').value = userId;
		document.getElementById('businessManager').value = userName;
	}
	if (userRole == 'D') {
		document.getElementById('businessCompetentId').value = userId;
		document.getElementById('businessCompetent').value = userName;
	}
}
/*
 * �����۷�Χ
 */
function selectTvkbz(num) {
	initMaintWindow4Post('ѡ�����۷�Χ', '/salesMsgAction!toSearchTvkbz.jspa?vkorg='
			+ $('#bukrs').combobox('getValue') + '&num=' + num, 700, 350);
}
/*
 * ���۷�Χѡ�񷵻�ֵ vkorg������֯,vtweg��������,spart��Ʒ��,vkbur���۲���
 */
function returnTvkbz(vkorg, vkorgTxt, vtweg, vtwegTxt, spart, spartTxt, vkbur,
		vkburTxt, num) {
	document.getElementById('vkorg_' + num).value = vkorg;
	document.getElementById('vtweg_' + num).value = vtweg;
	document.getElementById('spart_' + num).value = spart;

	document.getElementById('vkorgTxt_' + num).value = vkorgTxt;
	document.getElementById('vtwegTxt_' + num).value = vtwegTxt;
	document.getElementById('spartTxt_' + num).value = spartTxt;
}

function returnTvkbzList(list) {
	for ( var i = 0; i < list.length; i++) {
		areaCount++;
		addArea(list[i],areaCount);
	}
}
/*
 * ѡ���ַ
 */
function selectAddress(num) {
	if (undefined == num) {
		initMaintWindow4Post('ѡ���ַ', '/kunnrAction!searchAddress.jspa', 700,
				550);
	} else {
		initMaintWindow4Post('ѡ���ַ', '/kunnrAction!searchAddress.jspa?num='
				+ num, 700, 550);
	}
}
/*
 * ��ַѡ�񷵻�ֵzwl01tʡ��zwl02t�У�zwl03t��zwl04t��
 */

function returnAddress(zwl01t, zwl02t, zwl03t, zwl04t, num) {
	if (undefined == num) {
		document.getElementById('street').value = zwl01t + zwl02t + zwl03t
				+ zwl04t;
	} else {
		document.getElementById('address_' + num).value = zwl01t + zwl02t
				+ zwl03t + zwl04t;
	}
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
/**
 * Ŀ������Ϣ����
 */
function exportGoals() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!exportGoals.jspa';
	form.target = "hideFrame";
	form.submit();
}