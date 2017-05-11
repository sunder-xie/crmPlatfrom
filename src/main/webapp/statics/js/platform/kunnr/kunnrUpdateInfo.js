var nextUser;
var processInstanceId;
var addressCount = $("#addressListSize").val();
var brandCount = $("#brandListSize").val();
var acountCount = $("#acountListSize").val() * 2;
var areaCount = $("#kunnrSalesAreaListSize").val();
var goalCount = $("#bCustomerTargetListSize").val();
var killSalesArea = [];
var month = [];
$(document)
		.ready(
				function() {
					if ($('#konzsTxt').val()) {
						$('.type2J').show();
					} else {
						$('.type2J').hide();
					}
					//initHide();
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
												if (level == 1) {
													$('#bgXzProvince')
															.val(
																	$(
																			'#region'
																					+ level)
																			.combobox(
																					'getText'));
												} else if (level == 2) {
													$('#bgXzCity')
															.val(
																	$(
																			'#region'
																					+ level)
																			.combobox(
																					'getText'));
												} else if (level == 3) {
													$('#bgXzArea')
															.val(
																	$(
																			'#region'
																					+ level)
																			.combobox(
																					'getText'));
												} else if (level == 4) {
													$('#bgXzTown')
															.val(
																	$(
																			'#region'
																					+ level)
																			.combobox(
																					'getText'));
												}
												++level;

												getRegion(level);
											},
											onSelect : function(re) {
												$('#region').val(re.id);
												if (level == 2) {
													$('#bgXzProvince').val(
															re.text);
												} else if (level == 3) {
													$('#bgXzCity')
															.val(re.text);
												} else if (level == 4) {
													$('#bgXzArea')
															.val(re.text);
												} else if (level == 5) {
													$('#bgXzTown')
															.val(re.text);
												}
												getRegion(level);
												$('#bgXzAddress').val(
														$('#bgXzProvince').val() + $('#bgXzCity').val()
																+ $('#bgXzArea').val()
																+ $('#bgXzTown').val());
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
						}, {
							value : 'male',
							text : '��'
						}, {
							value : 'female',
							text : 'Ů'
						} ]
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
//							var currTab = $('#tabs').tabs('getTab', '֤���б�');
							refreshLicense();
						}
					});

					// �ͻ����� ����
					$('#channelId')
							.combobox(
									{
										multiple : false,
										url : appUrl
												+ '/kunnrAction!getChannel.jspa?channelName='
												+ 'K',
										valueField : 'channelId',
										textField : 'channelName',
										onSelect : function(record) {
											var str = record.channelName;
											var kukla = 'Z2';
											if ('��Լ������' == str) {
												kukla = 'Z3';
											} else if ('KAֱӪ' == str
													|| '����ֱӪ' == str
													|| '����ֱӪ' == str) {
												kukla = 'Z4';
											}
											$('#kukla').val(kukla);
										}
									});

					// �ͻ���Ŀ��
					/** ��̨action���޴˷�����ע��  by cg.jiang 20161109
					$('#ktokd').combobox({
						url : appUrl + '/kunnrAction!getKtokd.jspa',
						valueField : 'itemValue',
						textField : 'itemValue'
					});*/
					//Ʒ��
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
					// �ϼ������̲�ѯ konzs
					$('#konzs')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										method : 'post',
										mode : 'remote',
										singleSelect : true,
										url : appUrl
												+ '/kunnrAction!kunnrSearch.jspa?orgId='
												+ $('#orgId').val()
												+ '&bhxjFlag=' + 'C',
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
										onLoadSuccess : function() {
											// $('#konzs').combogrid("setValue",$('#konzsTxt').val());
										},
										onSelect : function(index, record) {
											document.getElementById("konzsTxt").value = record.name1;
											if ($('#konzs').combogrid(
													"getValue")) {
												show();
											} else {
												hide();
											}
										}
									});
					$('#konzs').combogrid('setValue', $("#konzsId").val());
					$('#konzs').combogrid('setText', $("#konzsTxt").val());
					
					// ���������̲�ѯ
					$('#kunag')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										multiple : false,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/kunnrAction!kunnrSearch.jspa?orgId='
												+ $('#orgId').val()
												+ '&bhxjFlag=' + 'C',
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
										toolbar : '#toolbarKunag',
										onSelect : function(index, record) {
											$('#kunagTxt').val(record.name1);
											$("#kunagId").val(record.kunnr);
										}
									});
					$('#kunag').combogrid('setValue', $("#kunagId").val());
					$('#kunag').combogrid('setText', $("#kunagTxt").val());

					// ���۴���
					$('#vkgrp').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/salesMsgAction!getT171tJsonList.jspa?rows=100',
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
						$('#werks_' + i).combobox('getValue');
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
//	if(flag==undefined){
//	$('#tabs')
//		.tabs(
//				'update',
//				{
//					tab : currTab ? currTab : $('#tabs').tabs('getTab',
//							'֤���ϴ�'),
//					options : {
//						href : appUrl
//								+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//								+ $("#kverm").combobox('getValue')
//					}
//				});
//	}else{
//					$('#tabs')
//					.tabs(
//							'update',
//							{
//								tab : currTab ? currTab : $('#tabs').tabs('getTab',
//										'֤���б�'),
//								options : {
//									href : appUrl
//											+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//											+ $("#kverm").combobox('getValue')
//								}
//					});	
//				}
	
}
//�ϼ����������
function clearKonzs() {
	$('#konzs').combogrid('setValue', '');
	$('#konzs').combogrid('setText', '');
	document.getElementById('konzsTxt').value='';
	document.getElementById('konzsId').value='';
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// �����Ƿ�ѡ��
			if (document.getElementById("flag_" + i).checked) {
				document.getElementById("flag_" + i).checked = false;// boolea
				document.getElementById('type2A_' + i).value = '';
				document.getElementById('type2B_' + i).value = '';
				document.getElementById('type2C_' + i).value = '';
			}
		}

	}
	hide();
}

//�������������
function clearKunag() {
	$('#kunag').combogrid('setValue', '');
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// �����Ƿ�ѡ��
			if (document.getElementById("flag_" + i).checked) {
				document.getElementById("flag_" + i).checked = false;// boolea
				document.getElementById('type2A_' + i).value = '';
				document.getElementById('type2B_' + i).value = '';
				document.getElementById('type2C_' + i).value = '';
			}
		}
	}
	hide();
}
// ������һ�� ����Ƿ����ϼ�������
function checkKonzs(count) {
	if (!$('#konzs').combogrid('getValue')) {
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
//���������̲�ѯ��
function searcherKunag(name1) {
	var queryParams = $('#kunag').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunag').combogrid("grid").datagrid('reload');
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
	if ($('#name001').val() != $('#name1').val()) {
		if ($('#nameUpdateFile').val() == '') {
			$.messager.alert('Tips', "���ϴ��ͻ����Ʊ��֤����", 'error');
			return;
		} else {
			var paths = $('#nameUpdateFile').val().split("\\");
			var name = paths[paths.length - 1];
			$('#nameUpdateFileName').val(name);
		}
	} else {
		$('#nameUpdateFile').val("");
	}
	var region4 = $('#region4').combobox('getValue');
	if (region4 == '') {
		$.messager.alert('Tips', "�칫���ڵ�δѡ��", 'error');
		return;
	}
	// ��Լ�����̱���ѡ�ϼ�������
	if ($('#channelId').combobox('getValue')) {
		var k = $('#kukla').val();
		if ('Z3' == k
				&& ($('#konzs').combogrid('getValue') == null
						|| $('#konzs').combogrid('getValue') == '' || undefined == $(
						'#konzs').combogrid('getValue'))) {
			$.messager.alert('Tips', '�ͻ�Ϊ��Լ������,��ѡ��ѡ�ϼ�������!', 'warning');
			return;
		}
	}
		if($('#fpRecipient').val().length>6){
			$.messager.alert('Tips', '��Ʊ�ռ������ֹ���!',
			'warning');
	        return;
		}
		if($('#street').val()==''){
			$.messager.alert('Tips', '��ѡ���ջ�������ַ!',
			'warning');
	        return;
		}
		var len1=$("#bank").val().length;
		var len2=$("#bankAccount").val().length;
		var len=len1+len2;
		if(len>48){
			$.messager.alert('Tips', "���������������˺ų���֮�ͳ��ȱ�����48���ڣ�", 'error');
			return;
		}
	var matchFlag = true;
	var isValid = $('#ff').form('validate');
	if (isValid) {
		$.messager
				.confirm(
						'Confirm',
						'��ȷ���޸ľ�����������Ϣ,�ύ?',
						function(r) {
							if (r) {
								var text=$('#name001').val()+'_�������޸������ᱨ';
								$('#title').val(text);
								// ��֤�Ƿ��޸��˾���������
								if ($('#channelIdHide').val() != $('#channelId')
										.combobox('getValue')) {
									$('#updateFlag').val('Y');
								} else {
									$('#updateFlag').val('N');
								}
								var license = $("input[name='license']");
								if(license.length==0){
									$.messager.alert('Tips', "֤��δ�ϴ���",
									'error');
							         return;
								}else{
								  for ( var i = 0; i < license.length; i++) {
									if($('#kverm').combobox('getValue')!=$('#kvermTxt').val()){
									var file = license[i].value;
									if (file) {
										matchFlag = matchImage(file,
												"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
										if (!matchFlag)
											break;
									 }else{
											$.messager.alert('Tips', "֤��δ�ϴ���",
											'error');
											matchFlag=false;
									        return;
									        
									 }
								     } else{
								    	  if (file) {
												matchFlag = matchImage(file,
														"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
												if (!matchFlag)
													break;
										 }/*else{
											 
										 }*/
								      }
									
								}}
								//��֤���۷�Χ
								if (!array2json()) return;
								if (matchFlag) {
									// �¸�������
									$
											.ajax({
												type : "post",
												async : false,
												url : appUrl
														+ "/kunnrAction!selectNexUser.jspa?time="
														+ new Date()
														+ "&freezeOrClose=update",
												data : {
													userId : $("#userId").val(),
													updateFlag : $(
															'#updateFlag')
															.val()
												},
												success : function(obj) {
													nextUserHtml(obj);
													processInstanceId = obj.processInstanceId;
												}
											});
									$('#nextUserDialog')
											.dialog(
													{
														title : '�¸�������',
														modal : true,
														resizable : false,
														dragable : false,
														closable : false,
														width : 300,
														height : 160,
														content : nextUser,
														buttons : [
																{
																	text : 'ȷ��',
																	handler : function() {
																		if (!$(
																				"#nextUsers")
																				.val()) {
																			$.messager
																					.alert(
																							'Tips',
																							"û���¸������ˣ���ά����",
																							'error');
																			return;
																		}
																		// �¸�������ID
																		$(
																				"#nextUserId")
																				.val(
																						$(
																								"#nextUsers")
																								.val());
																		handelAction('kunnrUpdateApply');
																	}
																},
																{
																	text : 'ȡ��',
																	handler : function() {
																		handelAction('cancelNextUser');
																	}
																} ]
													});

								}
							}
						});
	} else {
		$.messager.alert('Tips', '�밴��ʾ��ȷ��д����Ϣ!', 'warning');
	}

}

function save(){
		if ($('#name001').val() != $('#name1').val()) {
			if ($('#nameUpdateFile').val() == '') {
				$.messager.alert('Tips', "���ϴ��ͻ����Ʊ��֤����", 'error');
				return;
			} else {
				var paths = $('#nameUpdateFile').val().split("\\");
				var name = paths[paths.length - 1];
				$('#nameUpdateFileName').val(name);
			}
		} else {
			$('#nameUpdateFile').val("");
		}
		var region4 = $('#region4').combobox('getValue');
		if (region4 == '') {
			$.messager.alert('Tips', "�칫���ڵ�δѡ��", 'error');
			return;
		}
		// ��Լ�����̱���ѡ�ϼ�������
		if ($('#channelId').combobox('getValue')) {
			var k = $('#kukla').val();
			if ('Z3' == k
					&& ($('#konzs').combogrid('getValue') == null
							|| $('#konzs').combogrid('getValue') == '' || undefined == $(
							'#konzs').combogrid('getValue'))) {
				$.messager.alert('Tips', '�ͻ�Ϊ��Լ������,��ѡ��ѡ�ϼ�������!', 'warning');
				return;
			}
		}
		
			if($('#street').val()==''){
				$.messager.alert('Tips', '��ѡ���ջ�������ַ!',
				'warning');
		        return;
			}
		if (!array2json()) return;
		var matchFlag = true;
		var isValid = $('#ff').form('validate');
		if (isValid) {
			$.messager
					.confirm(
							'Confirm',
							'��ȷ���޸ľ�����������Ϣ,����?',
							function(r) {
								if (r) {
									// ��֤�Ƿ��޸��˾���������
									if ($('#channelIdHide').val() != $('#channelId')
											.combobox('getValue')) {
										$('#updateFlag').val('Y');
									} else {
										$('#updateFlag').val('N');
									}
									var license = $("input[name='license']");
									if(license.length==0){
										$.messager.alert('Tips', "֤��δ�ϴ���",
										'error');
								         return;
									}else{
									  for ( var i = 0; i < license.length; i++) {
										if($('#kverm').combobox('getValue')!=$('#kvermTxt').val()){
										var file = license[i].value;
										if (file) {
											matchFlag = matchImage(file,
													"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
											if (!matchFlag)
												break;
										 }else{
												$.messager.alert('Tips', "֤��δ�ϴ���",
												'error');
												matchFlag=false;
										        return;
										        
										 }
									     } else{
									    	  if (file) {
													matchFlag = matchImage(file,
															"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
													if (!matchFlag)
														break;
											 }
									      }
										
									}}
									if (matchFlag) {
										//
										var form = window.document.forms[0];
										form.action = appUrl + "/kunnrAction!kunnrUpdateApplyWfe.jspa";
										form.target = "hideFrame";
										form.submit();
									}
								}
							});
		} else {
			$.messager.alert('Tips', '�밴��ʾ��ȷ��д����Ϣ!', 'warning');
		}
}
// ȡ��
function cancel() {
	window.parent.closeWindow();
}

// �б�htmlƴ��
function nextUserHtml(obj) {
	nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
			+ "<tr><td class='head' noWrap>�������б�</td>"
			+ "<td><select id='nextUsers'>";
	$.each(obj.result, function(i, v) {
		nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
				+ v.stationName + "</option>";
	});
	nextUser += "</select></td></tr></table>";
}

// �������б�ť����
function handelAction(action) {
	if (!array2json()) return;
	var form = window.document.forms[0];
	form.action = appUrl + "/kunnrAction!" + action + ".jspa?eventId="
			+ processInstanceId + '&updateFlag=' + $('#updateFlag').val();
	form.target = "hideFrame";
	form.submit();
}

//��ӵ�ַһ��
function addAddress() {
	addressCount++;
	var html = '';
	html += '<tr id="addressTr_' + addressCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAddress('
			+ addressCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;<font color="red">*</font>(�ʹ﷽)��ַ:</td>';
	html += '<td><a class="easyui-linkbutton" style="width:44px"  href="javascript:selectAddress('
			+ addressCount + ')">ѡ��</a></td>';
	html += '<td>'
			+ '<input type="hidden" id="sdProvince_'
			+ addressCount
			+ '"><input type="hidden" id="sdCity_'
			+ addressCount
			+ '" ><input type="hidden" id="sdArea_'
			+ addressCount
			+ '" ><input type="hidden" id="sdTown_'
			+ addressCount
			+ '" >'
			+ '<input class="easyui-validatebox" data-options="required:true"  style="width:300px" validType="length[0,50]" id="address_'
			+ addressCount
			+ '" readonly></td><td><input class="easyui-validatebox" data-options="required:true" id="address01_'
			+ addressCount + '" validType="length[0,50]" style="width:300px"></td>';
	html += '<td class="head" noWrap>�ջ���:';
	html += '<input class="easyui-validatebox" data-options="required:true" style="width:80px" validType="length[0,20]" id="name_'
			+ addressCount + '">';
	html += '<font class="head" noWrap>�ջ���ϵ�绰:';
	html += '<input class="easyui-validatebox" style="width:90px" validType="telephone" id="telephone_'
			+ addressCount + '"></font></td>';
	html += '<td class="head" noWrap>�ջ���ϵ�ֻ�:</td>';
	html += '<td><input class="easyui-numberbox" style="width:90px" validType="length[11,11]" invalidMessage="��������ȷ��11λ�ֻ���" id="mobile_'
			+ addressCount + '"></td>';
	html += '<td class="head" noWrap>����ͨ�г���:</td>';
	html += '<td><input class="easyui-combobox" id="maximum_'
			+ addressCount
			+ '" style="width:80px"  validType="length[0,20]" data-options="required:true,editable:false"></td>';
	html += '</tr>';
	$('#addressTable').append(html);
	$.parser.parse($('#addressTr_' + addressCount));
	$('#maximum_' + addressCount).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'carType'/*
									 * , onLoadSuccess : function() {
									 * $('#maximum_' +
									 * addressCount).combobox('setValue', 'C0'); }
									 */
			});
}
// �h����ַһ��
function removeAddress(count) {
	if ($("#addressTr_" + count)) {
		$("#addressTr_" + count).remove();
	}
}

// ���Ʒ��һ��
function addBrand() {
	brandCount++;
	var html = '';
	html += '<tr id="brandTr_' + brandCount + '">';
	html += '<td class="head" noWrap>Ʒ������:</td>';
	html += '<td><input class="easyui-validatebox" id="brand_' + brandCount
			+ '"></td>';
	html += '<td class="head" noWrap>���۶�:</td>';
	html += '<td colspan="3"><input class="easyui-validatebox" id="sales_'
			+ brandCount + '">(��Ԫ)</td>';
	html += '</tr>';
	$('#brandTable').append(html);
}

// �h��Ʒ��һ��
function removeBrand(count) {
	if ($("#brandTr_" + count)) {
		$("#killBrand").val(
				$("#killBrand").val() + ',' + $("#brandid_" + count).val());
		$("#brandTr_" + count).remove();

	}
}

// ����ۿ�һ��
function addAcount() {
	acountCount++;
	var html = '';
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAcount('
			+ acountCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;���ϻؿ���:';
	html += '<input class="easyui-combobox" id="bonus_' + acountCount
			+ '"></td>';
	html += '<td class="head" noWrap>';
	html += '�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeA_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeB_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeC_'
			+ acountCount
			+ '">&nbsp;Э���(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeMoney_'
			+ acountCount + '"></td>';
	if ($('#konzs').combogrid('getValue')) {
		html += '<td class="head" noWrap> <div class="type2J">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">&nbsp;Э���(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2Money_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+ '</div></td>';
	} else {
		html += '<td class="head" noWrap> <div class="type2J" style="display:none;">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;&nbsp;�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">&nbsp;Э���(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2Money_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')"></div></td>';
	}
	html += '<td width="20%">&nbsp;</td><td width="20%">&nbsp;</td><td width="20%">&nbsp;</td>';
	html += '</tr>';
	acountCount = acountCount + 1;
	var trCount = acountCount - 1;
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap>SAPЭ���:<input class="easyui-validatebox" id="text0_'
			+ trCount + '" validType="length[0,20]" readonly>';
	html += '<td  noWrap><div><font style="font-weight:bold;color:#006b8a">'
			+ '��ʼ��Ч����:<input class="easyui-validatebox" id="text1_' + trCount
			+ '" data-options="editable:false" readonly>';
	html += '&nbsp;��Ч��ֹ����:</font><input class="easyui-validatebox" id="text2_'
			+ trCount + '" data-options="editable:false" readOnly></div></td>';
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
// ������۷�Χһ��
function addArea(obj, areaCount) {
	// areaCount++;
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
// �h�����۷�Χһ��
function removeArea(i) {
	if ($("#saleAreaTr_" + i)) {
		if ('' != $("#salesAreaId_" + i).val()) {
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
		}
		$("#saleAreaTr_" + i).remove();
	}
}
// ���Ŀ����һ��
function addGoal() {
	goalCount++;
	var html = '';
	html += '<tr id="goalTr_' + goalCount + '">';
	html += '<td class="head" noWrap>&nbsp;&nbsp;<a href="javascript:removeGoal('
			+ goalCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;��� </td>';
	html += ' <td><input class="easyui-combobox" id="year_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" readonly>'
			+ '<font class="head">&nbsp;/&nbsp;�·� <input class="easyui-combobox" id="month_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" name="theMonth"></td>';
	html += ' <td class="head" noWrap>��Ӧ����</td>';
	html += '<td><input class="easyui-cmobogrid" id="wid_' + goalCount
			+ '"/></td>' + ' <div id="toolbar_' + goalCount
			+ '" class="datagrid-toolbar" >' + ' <input id="search_'
			+ goalCount
			+ '" class="easyui-searchbox" style="width:300px"></input>'
			+ '</div>';
	html += '<td class="head" noWrap>Ŀ������</td>';
	html += '<td><input class="easyui-numberbox" style="width:60px" id="box_'
			+ goalCount + '"  data-options="precision:2,min:0,fix"""/>'
			+ '</td>';
	html += ' <td class="head" noWrap>Ŀ����</td>';
	html += '<td> <input class="easyui-numberbox" id="targetNum_' + goalCount
			+ '" data-options="precision:4,min:0,fix:"""/></td>';
	html += '</tr>';
	$('#goalTable').append(html);
	$.parser.parse($('#goalTr_' + areaCount));
	// �·�
	$('#month_' + goalCount).combobox(
			{
				data : month,
				valueField : 'txt',
				textField : 'value',
				onLoadSuccess : function() {
					$('#month_' + goalCount).combobox("setValue",
							new Date().getMonth() + 1);
				}
			});
	// ���
	$('#year_' + goalCount).combobox(
			{
				url : appUrl + '/goalAction!getDictTypeList.jspa',
				textField : 'itemName',
				valueField : 'itemName',
				onLoadSuccess : function() {
					$('#year_' + goalCount).combobox("setValue",
							new Date().getFullYear());
				}
			});

	$('#search_' + goalCount).searchbox({
		searcher : function(value, name) {
			search(value, goalCount);
		},
		prompt : '����д���ϺŻ�������'
	});

	// ��Ӧ����
	$('#wid_' + goalCount).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// �Ƿ��ҳ collapsible : false,//
		// �Ƿ���۵���
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
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
		toolbar : '#toolbar_' + goalCount
	});

}
// ��Ӧ����toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}
// �h��Ŀ����һ��
function removeGoal(count) {
	if ($("#goalTr_" + count)) {
		$("#goalTr_" + count).remove();
	}
}
// ��ַƷ��array->json
function array2json() {
	var kunnrAddressList = [];
	//var kunnrBrandList = [];
	var kunnrAcountList = [];
	var kunnrSalesAreaList = [];
	//var bCustomerTargetList = [];
	for ( var i = 1; i <= addressCount; i++) {
		if (document.getElementById("addressTr_" + i)) {
			kunnrAddressList.push({
				"id" : $("#addressid_" + i).val(),
				"kunnrSd" : $("#kunnrSd_" + i).val(),
				"province":$("#sdProvince_" + i).val(),
				"city":$("#sdCity_" + i).val(),
				"area":$("#sdArea_" + i).val(),
				"town":$("#sdTown_" + i).val(),
				"xzAddress" : $("#address_" + i).val(),
				"address" : $("#address01_" + i).val(),
				"name" : $("#name_" + i).val(),
				"telephone" : $("#telephone_" + i).val(),
				"mobile" : $("#mobile_" + i).val(),
				"state":'U',
				"maximum" : $("#maximum_" + i).combobox('getValue')
			});
		}
	}
	for ( var i = 1; i <= brandCount; i++) {
		if (document.getElementById("brandTr_" + i)) {
			kunnrBrandList.push({
				"id" : $("#brandid_" + i).val(),
				"brand" : $("#brand_" + i).val(),
				"sales" : $("#sales_" + i).val()
			});
		}
	}
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
						"type2A" : $("#type2A_" + i).val(),
						"type2AId" : $("#type2AId_" + i).val(),
						"type2B" : $("#type2B_" + i).val(),
						"type2BId" : $("#type2BId_" + i).val(),
						"type2C" : $("#type2C_" + i).val(),
						"type2CId" : $("#type2CId_" + i).val(),
						"typeMoney" : $("#typeMoney_" + i).val(),
						"type2Money" : $("#typeMoney_" + i).val(),
						"bonus" : $("#bonus_" + i).combobox("getValue"),
						// "vtext" : $("#vtext_" + i).val(),
						"flag" : flag,
						//"acountSap" : $("#text0_" + i).val(),
						"startDate" : $("#text1_" + i).val(),
						"endDate" : $("#text2_" + i).val(),
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
			if ($("#vkorg_" + i).val() != $('#bukrs').combobox('getValue')) {
				$.messager.alert('Tips', "������֯��һ��,�����²�����", 'warning');
				return false;
			}
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

	for ( var i = 1; i <= goalCount; i++) {
		if (document.getElementById("goalTr_" + i)) {
			if ($("#wid_" + i).combogrid('getValue')) {
				bCustomerTargetList.push({
					"theYear" : $("#year_" + i).combobox('getValue'),
					"theMonth" : $("#month_" + i).combobox('getValue'),
					"targetNum" : $("#targetNum_" + i).val(),
					"box" : $("#box_" + i).val(),
					"matter" : $("#wid_" + i).combogrid('getValue')
				});
			}
		}
	}
	$("#kunnrAddressList").val($.toJSON(kunnrAddressList));
	//$("#kunnrBrandList").val($.toJSON(kunnrBrandList));
	$("#kunnrAcountList").val($.toJSON(kunnrAcountList));
	$("#kunnrSalesAreaList").val($.toJSON(kunnrSalesAreaList));
	//$("#bCustomerTargetList").val($.toJSON(bCustomerTargetList));
	return true;
}

// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			if($('#eventId').val()==''||undefined==$('#eventId').val()){
			window.parent.location.reload();}
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
	checkOnly();
	$('#konzs').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	$('#kunag').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
}
function checkOnly(){
	if($('#orgId').val()==null||$('#orgId').val()==''){
		return;
	}
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + '/kunnrAction!checkonly.jspa?&random=' + Math.random(),
		data : {
			orgId : $('#orgId').val()
		},
		success : function(obj) {
			if (obj.result == null ||obj.result=='' ) {
				
			} else{
				$.messager.alert('Tips', obj.result, 'warning');
				document.getElementById('orgName').value = '';
			}
		}

	});


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
 * ���о�������ѡ�񷵻�ֵ
 */
function returnUser(userId, userName,moblie) {
	if (userRole == 'M') {
		document.getElementById('businessManagerId').value = userId;
		document.getElementById('businessManager').value = userName;
		$('#managerMobile').numberbox('setValue',moblie);
	}
	if (userRole == 'D') {
		document.getElementById('businessCompetentId').value = userId;
		document.getElementById('businessCompetent').value = userName;
		$('#competentMobile').numberbox('setValue',moblie);
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
		addArea(list[i], areaCount);
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
function returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t, num) {
	if (undefined == num) {
		document.getElementById('shProvince').value=zwl01;
		document.getElementById('shCity').value=zwl02;
		document.getElementById('shArea').value=zwl03;
		document.getElementById('shTown').value=zwl04;
		document.getElementById('street').value = zwl01t + zwl02t + zwl03t
				+ zwl04t;
	} else {
		document.getElementById('sdProvince_'+num).value=zwl01;
		document.getElementById('sdCity_'+num).value=zwl02;
		document.getElementById('sdArea_'+num).value=zwl03;
		document.getElementById('sdTown_'+num).value=zwl04;
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