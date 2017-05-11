$(document).ready(function() {
	$('#tabs').tabs({
		onSelect : function(title,index){
			if($("#kverm").val()!="小规模纳税人"){
//				$("#type2").attr("checked","checked");
				if(index==0){
					$("#type2").attr("checked","checked");
				}else{
					$("#type1").attr("checked","checked");
					$("#type2").removeAttr("checked","checked");
					$("#type5").attr("checked","checked");
				}
			}else{
				if(index==0){
					$("#type1").attr("checked","checked");
					$("#type3").attr("checked","checked");
				}else if(index==1){
					$("#type2").attr("checked","checked");
				}else{
					$("#type1").attr("checked","checked");
					$("#type5").attr("checked","checked");
				}
			}
			calcu();
		}
	})
});

function calcu(){
	if($("input[name=cardType]:checked").val()=="3" && $("input[name=payType]:checked").val()=="A"){
		var num1=accDiv($("#price").val(),"0.997");
		var num2=accSub(num1,$("#price").val());
		num2 = num2.toFixed(2);
		$("#fee").val(num2);
		$("#fee_span").empty();
		$("#fee_span").append(num2);
	}else if($("input[name=cardType]:checked").val()=="6" && $("input[name=payType]:checked").val()=="A"){
		var num1=accDiv($("#price").val(),"0.996");
		var num2=accSub(num1,$("#price").val());
		num2 = num2.toFixed(2);
		$("#fee").val(num2);
		$("#fee_span").empty();
		$("#fee_span").append(num2);
	}else{
		$("#fee").val("0");
		$("#fee_span").empty();
		$("#fee_span").append("0");
	}
	$("#totalPrice").val(accAdd($("#price").val(),$("#fee").val()));
	$("#totalPrice_span").empty();
	$("#totalPrice_span").append(accAdd($("#price").val(),$("#fee").val()));
}

function accMul(arg1,arg2){//乘法
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function accDiv(arg1,arg2){//除法
	  var t1=0,t2=0,r1,r2;
	  try{t1=arg1.toString().split(".")[1].length}catch(e){}
	  try{t2=arg2.toString().split(".")[1].length}catch(e){}
	  with(Math){
	   r1=Number(arg1.toString().replace(".",""))
	   r2=Number(arg2.toString().replace(".",""))
	   return (r1/r2)*pow(10,t2-t1);
	  }
}

function accAdd(arg1,arg2){//加法
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m+arg2*m)/m
}

function accSub(arg1,arg2){//减法
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m-arg2*m)/m
}

function submit(){
//	var form = window.document.forms[0];
//	form.action = appUrl
//			+ '/orderNewAction!accountPay.jspa';
//	form.target = "hideFrame";
//	form.submit();
	if($("#price").val()==""){
		$.messager.alert('提示', '金额不能为空！', '提示');
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/orderNewAction!accountPay.jspa",
		data : $('#form1').serialize(),
		success : function(data) {
			window.parent.search();
			cencel();
			window.open(data);
		}
	});
}

function cencel(){
	window.parent.closeMaintWindow();
}
//
//function load(){
//	var radio=$("#radio").attr("checked");
//	if(radio){
//		$("#tbody").empty();
//		$("#tbody").append(
//				  '<tr><td>&nbsp;</td> '+
//				  '<td>储蓄卡:<input type="radio" name="cardType" value="1" id="radio" checked onclick="calcu()"></td> '+
//				  '</tr> '+
//				  '<tr><td>&nbsp;</td> '+
//		          '<td>信用卡:<input type="radio" name="cardType" value="3" onclick="calcu()"></td> '+
//		          '</tr> '
//				);
//	}else{
//		$("#tbody").empty();
//		$("#tbody").append(
//				  '<tr><td>&nbsp;</td> '+
//				  '<td>&nbsp;</td> '+
//				  '</tr> '+
//				  '<tr><td>&nbsp;</td> '+
//		          '<td>&nbsp;</td> '+
//		          '</tr> '
//				);
//	}
//}