layui.define([ 'form', 'layer', 'tree','layedit','upload' ], function(exports) {

	var form = layui.form, $ = layui.jquery, layer = layui.layer,layedit = layui.layedit,upload=layui.upload;

	form.verify({
		// 证件号表单验证
		idNo : function(value) {
			var reg = /^[A-Za-z0-9]+$/;
			if (reg.test(value) == false) {
				return '证件号输入有误，请重新输入';
			}
		},
		// 密码及确认密码表单验证(没有原密码的验证)
		pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
		repass : function(value) {
			var passvalue = $('#L_pass').val();
			if (value != passvalue) {
				return '两次输入的密码不一致!';
			}
		},

		// 密码及确认密码表单验证(有原密码的验证)
		oldPass : function(value) {
			var reg = /(.+){6,12}$/;
			if (reg.test(value) == false) {
				return '密码必须6到12位';
			}
		},
		newPass : function(value) {
			var oldPass = $('#L_oldPass').val();
			if (value == oldPass) {
				return '原密码不能与新密码一致';
			}

			var reg = /(.+){6,12}$/;
			if (reg.test(value) == false) {
				return '密码必须6到12位';
			}

		},
		affirmPass : function(value) {
			var newPass = $('#L_newPass').val();
			if (value != newPass) {
				return '新密码与确认密码不一致';
			}
		},
		// 手机号表单验证
		phone : function(value) {
			var reg = /^(13|15|18|17|14)\d{9}$/;
			if (value.length <= 0) {
				return '不能为空';
			}
			if (reg.test(value) == false) {
				return '手机号格式输入不正确请重新输入';
			}

		},
		
		isNumber : function(value) {
			if (value.length <= 0) {
				return '不能为空';
			}
			if (isNaN(value)) {
				return '请输入数字';
			}
		},
		isValue : function(value) {
			var reg = /^(\d+|\d+\.\d{1,2})$/;
			if (isNaN(value)) {
				return '请输入数字';
			}
			if(reg.test(value) == false){
				return '至多输入小数点后两位';
			}
		},
		isInteger : function(value){
			if (isNaN(value)) {
				return '请输入数字';
			}
			if (value % 1 != 0) {
				return '请输入整数';
			}
		},

		
	});
	var obj = {
		init : function(path, datecount) {

            layedit.set({
                uploadImage: {
                    url: 'common/upload' ,//接口url
                    type: 'post' //默认post
                }
            });


			var layinit=layedit.build('demo',{tool:[
                'strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线
                ,'|' //分割线
                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'image'//上传图片
                ,'link'//超链接

            ]});
			
			//编辑页面保存成功后，刷新父页面的表格数据
			function refuseTable(){
		          var pars ='?';
                  $(".layui-form input",window.parent.document).each(function(){
               	   if($(this).attr('name')!=undefined){
               		   pars+=$(this).attr('name');
               		   pars+='=';
               		   pars+=$(this).val();
               		   pars+='&';
               	   }
                  });
                  $("select",window.parent.doucment).each(function(){
                	  if($(this).attr('name')!=undefined){
                		  pars+=$(this).attr('name');
                		  pars+='=';
                		  pars+=$(this).find('option:selected').val();
                		  pars+='&';
                	  }
                  });
                  pars=pars.substring(0,pars.length-1);
               
					var closeindex=	parent.layer.msg('数据请求中', {
						icon: 16,
						anim: -1,
						fixed: !1
					});
				  var data=  $(".layui-table",window.parent.document).attr('lay-data');
			         
			 		eval('var layData2='+data);
			    	var options={ 
			    			  height: parent.ConstantHeight 
			    			  ,url:layData2.url+pars
			    			  ,done:function(res, curr, count){
				    				  parent.layer.close(closeindex); 
				    				  parent.layer.msg('成功');
				    			  }
			    			 };
			        var index = parent.layer
			    	.getFrameIndex(window.name);
			    	parent.layer.close(index);
			    	parent.layui.table.reload('test',options);
				
				
			}

            var uploadInst = upload.render({
                elem: '#iconupload' //绑定元素
                ,url: 'region/upload' //上传接口
                ,done: function(res){
                    if(res.code==0){
                    	layer.msg('上传成功');
                        var data = res.data;
                        $('#iconUrl').html(data.src);
                        $('#show').html("");
                        $('#iconImg').attr('src',data.src);  
                        layer.msg(data.src);
					}else{
                    	layer.msg(res.msg);
					}
                }
                ,error: function(){
                    layer.msg('上传图片异常');
                }
            });
			
			
			//编辑页面点击取消
			$(".Cancel").click(function() {
				var index = index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
			
			//编辑页面的保存动作
			form.on('submit(formDemo)', function(data) {
				var field = data.field;
				var arguments = "{";
				var index = 0;

				var msg=layedit.getContent(layinit);
				
				for ( var i in field) {
				
					arguments += '"'+i + '":"' + field[i]+'"';
					arguments+=",";
				}
				
				arguments=arguments.substring(0,arguments.length-1);
                arguments+="}";
                var log ;
                var sss= "var log ="+arguments;
                try{
                eval(sss);
                }catch(error){
                	log=data.field;
            	}
            	if(msg!=undefined||msg!=""){
					log.content=msg;
				}            	          	
            	if(path=="region/addregion"){
            	    var rightrule="";       
            	    $("[name=rightrules]:checked").each(function (index,data) {
            	     rightrule+=data.value+",";
            	   	})  
            	   rightrule= rightrule.substring(0,rightrule.length-1); 
            	   path=path+"/?rightrule="+rightrule;           	   
            	}
            	
            	if(msg!=undefined||msg!=""){
					log.content=msg;
				}   
				
            	          		 
                if(path=="doc/add"){
                 	var content=ue.getContent(); 
                 	alert(content);
                 	path=path+"?content="+content;           	   
                 	}
            		$.ajax({
    					url : path,
    					type : 'post',
    					async : false,
    					data:log,
    					success : function(res) {
    						if (res.code=="200") {
    							refuseTable();
    						}
    						if (res.code=="400") {
    							layer.msg(res.msg);
    						}
    					},
    					error : function() {
    						layer.msg('系统异常');
    					}
    				});
				return false;
			});
		}
	};
	exports('jclAdd', obj);
});
function refresh(){
	window.location.reload();
}