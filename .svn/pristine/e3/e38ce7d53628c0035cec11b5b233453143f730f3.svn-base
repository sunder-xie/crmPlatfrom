$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					$('#parent_city_name')
							.combobox(
									{
										textField : 'cityName',
										valueField : 'cityName',
										onChange : function(newValue, oldValue) {
											if (newValue != null) {
												var cityl=encodeURIComponent($("#citylevel").combobox("getValue"));
												if(cityl==""){
												$.messager.alert('Tips', "请选择等级！", 'warning');
												}else{
												var urlStr = appUrl
														+ "/cityDictAction!blurSearchCityDict.jspa?citylevel="+cityl+"&&parent_city_name="
														+ encodeURIComponent(newValue);
												$("#parent_city_name").combobox("reload",
														urlStr);
												
												}
											}
										},
										onSelect : function(r) {
											$("#parent_city_id").val(r.dictId);
											$("#parent_city_number").val(r.cityNumber);
										}
									});
				});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search();
		});
		
	}
}

function submit() {
	var cc = $("#city_abbreviation").val();
		if(!(cc=="")){
		 var c= cc.match(/^[A-Z]+$/); 
		 if(c==null){
	 		$.messager.alert('Tips', "请根据区划简写格式填写", 'warning');
	 		 return ; 
		 }
		}
	var p = $("#citylevel").combobox('isValid');
	var n = $("#cityName").validatebox('isValid');
	var o = $("#cityNumber").validatebox('isValid');
	var t = $("#parent_city_name").combobox('isValid');
	if (!(p && n && o && t)) {
		$.messager.alert('Tips', "请将必选信息填写完整！", 'warning');
		return;
	}
		
	var form = window.document.forms[0];
	form.action = appUrl + "/cityDictAction!saveCityDict.jspa";
	form.submit();
}

function close() {
	window.parent.closeCityDict();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};