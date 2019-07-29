dialogOpen = function(opt){
	var defaults = {
		id : 'layerForm',
		title : '',
		width: '',
		height: '',
		url : null,
		scroll : false,
		data : {},
		btn: ['确定', '取消'],
		success: function(){},
		yes: function(){}
	};
	var option = $.extend({}, defaults, opt), content = null;
	if(option.scroll){
		content = [option.url]
	}else{
		content = [option.url, 'no']
	}
	top.layer.open({
	  	type : 2,
	  	id : option.id,
		title : option.title,
		closeBtn : 1,
		anim: -1,
		isOutAnim: false,
		shadeClose : false,
		shade : 0.3,
		area : [option.width, option.height],
		content : content,
		btn: option.btn,
		success: function(){
			option.success(option.id);
		},
		yes: function(){
			option.yes(option.id);
		}
    });
};


function openList() {
	$('#mailListUpdate').css('display','none');
	$('#mailList').css('display','block')
}

function closeList() {
	$('#mailList').css('display','none');
}

function closeEditList() {
	$('#mailListUpdate').css('display','none');
}

function createList() {
	var formData=new FormData($('#mailListForm')[0]);
	var mail=document.getElementById('mailList');
	var name=document.getElementById('name').value;
	var email=document.getElementById('email').value;
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if (name==null||name==''){
		alert("联系人姓名不能为空！");
		return false;
	}
	if (!reg.test(email)){
		alert("联系人邮箱有误，请重新填写！");
		return false;
	}
	mail.style.display='none';
	$.ajax({
		url:'/mail/create',
		type:"post",
		data:formData,
		contentType:false,
		processData:false,
		success:function (data) {
			location.reload();
		},
		error:function (data) {
			location.reload();
		}
	})
}

function updateList() {
	var formData=new FormData($('#mailListFormUpdate')[0]);
	var mail=document.getElementById('mailListUpdate');
	var name=document.getElementById('update_name').value;
	var email=document.getElementById('update_email').value;
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if (name==null||name==''){
		alert("联系人姓名不能为空！");
		return false;
	}
	if (!reg.test(email)){
		alert("联系人邮箱有误，请重新填写！");
		return false;
	}
	mail.style.display='none';
	$.ajax({
		url:'/mail/update',
		type:"post",
		data:formData,
		contentType:false,
		processData:false,
		success:function (data) {
			location.reload();
		},
		error:function (data) {
			location.reload();
		}
	})
}

function deleteList(id) {
	$.ajax({
		url:'/mail/deleteList/'+id,
		type:"get",
		success:function () {
			location.reload();
		},
		error:function () {
			location.reload()
		}
	})
}


window.onload=function () {
	$.ajax({
		url:'/mail/allList',
		type:"post",
		dataType:'json',
		async:false,
		success:function (data) {
			var mailLists=data.msg;
			var divStr="";
			var allMailLists="<table><tbody class='ivu-table-body'><tr class='ivu-table-row-highlight' " +
				"style='text-align: center'><th class='ivu-table-cell'>姓名</th><th class='ivu-table-cell'>" +
				"邮箱</th><th class='ivu-table-cell'>电话</th><th class='ivu-table-cell'>备注</th><th " +
				"class='ivu-table-cell'>上次修改时间</th></tr>";
			$.each(mailLists,function (i,mailList) {

				var timestamp = mailList.create_time;
				var d = new Date(timestamp);
				var date = (d.getFullYear()) + "-" +
					(d.getMonth() + 1) + "-" +
					(d.getDate()) + " " +
					(d.getHours()) + ":" +
					(d.getMinutes()) + ":" +
					(d.getSeconds());

				divStr += "<div class='mailLists' title='"+ mailList.email + "'><a href='http://localhost:8081/mail/send/" +
					mailList.email +"'>"+mailList.name+"</a></div>";

				allMailLists += "<tr class=\"ivu-table-row-highlight\" style=\"height: 50px\">" +
					"<td class=\"ivu-table-cell\">"+mailList.name+"</td><td class=\"ivu-table-cell\">"+mailList.email+
					"</td><td class=\"ivu-table-cell\">"+mailList.phone+"</td><td class=\"ivu-table-cell\">"+mailList.note+
					"</td><td class=\"ivu-table-cell\">"+date+"</td>" +
					"<td class=\"ivu-table-cell\"><a onclick=\"editMailList("+mailList.id+")\" title=\"修改联系人信息\">修改</a></td>" +
					"<td class=\"ivu-table-cell\"><a href=\"http://localhost:8081/mail/deleteList/"+mailList.id+"\" onclick=\"return confirm('确认删除？')\" title=\"删除该联系人\"><span style='color:red'>删除</span></a></td>" +
					"<td class=\"ivu-table-cell\"><a href=\"http://localhost:8081/mail/send/"+mailList.email+"\" title=\"给该联系人发送邮件\">发送邮件</a></td></tr>"

			});
			allMailLists += "</tbody></table>";

			$("#mailLists").html(divStr);
			$("#allMailLists").html(allMailLists);

		},
		error:function () {
			$('#mailLists').html("暂无联系人");
			$('#allMailLists').html("暂无联系人");
		}
	});
}

function sendMail() {
	var formData = new FormData($('#mailForm')[0]);
	var email=document.getElementById("email").value;
	var subject=document.getElementById("subject").value;
	var content=document.getElementById("content").value;
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

	if(!reg.test(email)){
		alert("收件箱有误，请重新填写！");
		return false;
	}
	if(subject==null||subject==''){
		alert("邮件主题不能为空！");
		return false;
	}
	if (content==null||content==''){
		alert("邮件内容不能为空！");
		return false;
	}

	$.ajax({
		url:'/mail/send',
		type:"POST",
		data:formData,
		contentType:false,
		processData:false,
		success:function (result) {
			alert("发送成功！")
		},
		error:function () {
			alert("发送失败！")
		}
	});
}

function clearForm() {
	$('#mailForm')[0].reset();
	$('#email').attr("value","");
}

function editMailList(id) {
	$('#mailList').css('display','none');
	$('#mailListUpdate').css('display','block');
	$.ajax({
		url:'/mail/oneList/'+id,
		type:"post",
		dataType:'json',
		async:false,
		success:function (data) {
			var result=data.msg;
			$('#update_name').attr("value",result.name);
			$('#update_email').attr("value",result.email);
			$('#update_phone').attr("value",result.phone);
			$('#update_id').attr("value",result.id);
			document.getElementById('update_note').value=result.note;
		},
		error:function () {
		}
	});
}


function searchEvent(event) {
	event=event||window.event;
	if (event.keyCode==13){
		searchByName();
	}
}

function searchByName() {
	var searchName=$('#searchByName').val();
	var search_name=searchName.toLowerCase();
	// $('#searchByName').val("");
	if (!searchName){
		backList();
		return;
	}
	$.ajax({
		url:'/mail/allList',
		type:"post",
		dataType:'json',
		async:false,
		success:function (data) {
			var mailLists=data.msg;
			var num=0;
			var divStrNo="<strong>没有查到符合条件的联系人</strong>|<a href='javascript:backList()'>返回通讯录列表</a><br>";
			var divStrYes="<a href='javascript:backList()'>返回通讯录列表</a></div></br>" +
				"<table><tbody class='ivu-table-body'><tr class='ivu-table-row-highlight' style='text-align: center'><th class='ivu-table-cell'>姓名</th>" +
				"<th class='ivu-table-cell'>邮箱</th><th class='ivu-table-cell'>电话</th><th class='ivu-table-cell'>备注</th><th " +
				"class='ivu-table-cell'>上次修改时间</th></tr>";
			$.each(mailLists,function (i,mailList) {
				var str=mailList.name.toLowerCase();

				if (str.indexOf(search_name)!=-1){
					var timestamp = mailList.create_time;
					var d = new Date(timestamp);
					var date = (d.getFullYear()) + "-" +
						(d.getMonth() + 1) + "-" +
						(d.getDate()) + " " +
						(d.getHours()) + ":" +
						(d.getMinutes()) + ":" +
						(d.getSeconds());
					num++;
					divStrYes += "<tr class=\"ivu-table-row-highlight\" style=\"height: 50px\">" +
						"<td class=\"ivu-table-cell\">"+mailList.name+"</td><td class=\"ivu-table-cell\">"+mailList.email+
						"</td><td class=\"ivu-table-cell\">"+mailList.phone+"</td><td class=\"ivu-table-cell\">"+mailList.note+
						"</td><td class=\"ivu-table-cell\">"+date+"</td>" +
						"<td class=\"ivu-table-cell\"><a onclick=\"editMailList("+mailList.id+")\" title=\"修改联系人信息\">修改</a></td>" +
						"<td class=\"ivu-table-cell\"><a href=\"http://localhost:8081/mail/deleteList/"+mailList.id+"\" onclick=\"return confirm('确认删除？')\" title=\"删除该联系人\"><span style='color:red'>删除</span></a></td>" +
						"<td class=\"ivu-table-cell\"><a href=\"http://localhost:8081/mail/send/"+mailList.email+"\" title=\"给该联系人发送邮件\">发送邮件</a></td></tr>"
				}
			});
			divStrYes="<div>查找到包含\""+searchName+"\"的结果"+num+"个|"+divStrYes+"</tbody></table>";

			if(num==0){
				$('#allMailLists').html(divStrNo);
			}else {
				$('#allMailLists').html(divStrYes);
			}
		},error:function () {
			var divStrNo="<strong>没有查到符合条件的联系人</strong>|<a href='http://localhost:8081/main.shtml#mail/mailList.shtml'>返回通讯录列表</a><br>";
			$('#allMailLists').html(divStrNo);
		}
	})
}

function backList() {
	window.location.href='http://localhost:8081/main.shtml#mail/mailList.shtml';
	location.reload();
}