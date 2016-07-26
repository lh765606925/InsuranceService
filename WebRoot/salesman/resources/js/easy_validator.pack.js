﻿/*
	Copyright (C) 2009 - 2012
	WebSite:	Http://wangking717.javaeye.com/
	Author:		wangking
*/
$(function(){
	var xOffset = -20; // x distance from mouse
    var yOffset = 20; // y distance from mouse
	
	
	// input action
	$("[reg],[url]:not([reg]),[tip]").hover(
		function(e) {
//			if($(this).attr('tip') != undefined&&!validateCom($(this))){
			if($(this).attr('tip') != undefined){
				var top = (e.pageY + yOffset);
				var left = (e.pageX + xOffset);
				$('body').append( '<p id="vtip"><img id="vtipArrow" src="resources/images/vtip_arrow.png"/>' + $(this).attr('tip') + '</p>' );
				$('p#vtip').css("top", top+"px").css("left", left+"px");
				$('p#vtip').bgiframe();
			}
		},
		function() {
			if($(this).attr('tip') != undefined){
				$("p#vtip").remove();
			}
		}
	).mousemove(
		function(e) {
			if($(this).attr('tip') != undefined){
				var top = (e.pageY + yOffset);
				var left = (e.pageX + xOffset);
				$("p#vtip").css("top", top+"px").css("left", left+"px");
			}
		}
	).blur(function(){
		if($(this).attr("url") != undefined){
			ajax_validate($(this));
		}else if($(this).attr("reg") != undefined){
			validate($(this));
		}
	});
	
	
	$("form").submit(function(){
		if(!validate($("#name"))){
			$("#name").focus();
			return false;
		}else if(!validate($("#realname"))){
			$("#realname").focus();
			return false;
		}else if(!validate($("#phone"))||!checkExit()){
			$("#phone").focus();
			return false;
		}else if(!validate($("#email"))){
			$("#email").focus();
			return false;
		}else if(!validate($("#password"))){
			$("#password").focus();
			return false;
		}else if(!validate($("#repassword"))&&$("#password").val()!=$("#repassword").val()){
			$("#repassword").focus();
			return false;
		}else{
			return true;
		}
	});
	
	$("ul a").click(function(event){
		   return false;//阻止链接跳转
	});
	
});

function validate(obj){
	var reg = new RegExp(obj.attr("reg"));
	var objValue = obj.val();
	if(validateCom(obj)){
		if(obj.attr("url") == undefined){
			change_error_style(obj,"remove");
			change_tip(obj,null,"remove");
			return true;
		}else{
			 return ajax_validate(obj);
		}
	}else{
		change_error_style(obj,"add");
		change_tip(obj,null,"remove");
		return false;
	}
}

function validateCom(obj){
	var reg = new RegExp(obj.attr("reg"));
	var objValue = obj.val();
	if(!reg.test(objValue)){
		return false;
	}else{
			return true;
	}
}

function ajax_validate(obj){
	var url_str = obj.attr("url");
	if(url_str.indexOf("?") != -1){
		url_str = url_str+"&"+obj.attr("name")+"="+obj.attr("value");
	}else{
		url_str = url_str+"?"+obj.attr("name")+"="+obj.attr("value");
	}
	var feed_back = $.ajax({url: url_str,cache: false,async: false}).responseText;
	feed_back = feed_back.replace(/(^\s*)|(\s*$)/g, "");
	if(feed_back == 'success'){
		change_error_style(obj,"remove");
		change_tip(obj,null,"remove");
		return true;
	}else{
		change_error_style(obj,"add");
		change_tip(obj,feed_back,"add");
		return false;
	}
}

function change_tip(obj,msg,action_type){
	
	if(obj.attr("tip") == undefined){// 初始化判断TIP是否为空
		obj.attr("is_tip_null","yes");
	}
	if(action_type == "add"){
		if(obj.attr("is_tip_null") == "yes"){
			obj.attr("tip",msg);
		}else{
			if(msg != null){
				if(obj.attr("tip_bak") == undefined){
					obj.attr("tip_bak",obj.attr("tip"));
				}
				obj.attr("tip",msg);
			}
		}
	}else{
		if(obj.attr("is_tip_null") == "yes"){
			obj.removeAttr("tip");
			obj.removeAttr("tip_bak");
		}else{
			obj.attr("tip",obj.attr("tip_bak"));
			obj.removeAttr("tip_bak");
		}
	}
}

function change_error_style(obj,action_type){
	if(action_type == "add"){
		obj.parent().addClass("has-error");
	}else{
		obj.parent().removeClass("has-error");
	}
}


  

$.fn.validate_callback = function(msg,action_type,options){
	this.each(function(){
		if(action_type == "failed"){
			
($(this),"add");
			change_tip($(this),msg,"add");
		}else{
			change_error_style($(this),"remove");
			change_tip($(this),null,"remove");
		}
	});
};

