$(document).ready(function() {
	calcu();
});

function calcu(){
	if($("input[name=cardType]:checked").val()=="3"){
		var num1=accDiv($("#orderPrice").val(),"0.997");
		var num2=accSub(num1,$("#orderPrice").val());
		num2 = num2.toFixed(2);
		$("#fee").val(num2);
		$("#fee_span").empty();
		$("#fee_span").append(num2);
	}else{
		$("#fee").val("0");
		$("#fee_span").empty();
		$("#fee_span").append("0");
	}
	$("#totalPrice").val(accAdd($("#orderPrice").val(),$("#fee").val()));
	$("#totalPrice_span").empty();
	$("#totalPrice_span").append(accAdd($("#orderPrice").val(),$("#fee").val()));
}

function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function accDiv(arg1,arg2){
	  var t1=0,t2=0,r1,r2;
	  try{t1=arg1.toString().split(".")[1].length}catch(e){}
	  try{t2=arg2.toString().split(".")[1].length}catch(e){}
	  with(Math){
	   r1=Number(arg1.toString().replace(".",""))
	   r2=Number(arg2.toString().replace(".",""))
	   return (r1/r2)*pow(10,t2-t1);
	  }
}

function accAdd(arg1,arg2){
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m+arg2*m)/m
}

function accSub(arg1,arg2){
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m-arg2*m)/m
}

function submit(){
	$.ajax({
		type : "post",
		url : appUrl + "/orderNewAction!pay.jspa",
		data : { orderId : $("#orderId").val() ,payType : $("input[name=payType]:checked").val() ,
			     cardType : $("input[name=cardType]:checked").val(), totalPrice : $("#totalPrice").val() },
		success : function(data) {
			window.open(data);
			cencel();
		}
	});
}

function cencel(){
	window.parent.closeMaintWindow();
}

function load(){
	var radio=$("#radio").attr("checked");
	if(radio){
		$("#tbody").empty();
		$("#tbody").append(
				  '<tr><td>&nbsp;</td> '+
				  '<td>¥¢–Óø®:<input type="radio" name="cardType" value="1" id="radio" checked onclick="calcu()"></td> '+
				  '</tr> '+
				  '<tr><td>&nbsp;</td> '+
		          '<td>–≈”√ø®:<input type="radio" name="cardType" value="3" onclick="calcu()"></td> '+
		          '</tr> '
				);
	}else{
		$("#tbody").empty();
		$("#tbody").append(
				  '<tr><td>&nbsp;</td> '+
				  '<td>&nbsp;</td> '+
				  '</tr> '+
				  '<tr><td>&nbsp;</td> '+
		          '<td>&nbsp;</td> '+
		          '</tr> '
				);
	}
}