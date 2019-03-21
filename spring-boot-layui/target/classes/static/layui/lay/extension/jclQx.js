layui.define([ 'form', 'layer', 'jquery' ], function(exports) {
	var form = layui.form, $ = layui.jquery;
	
	
	

	var obj = {
		init : function(url) {
			isadmin=$('input[name=isadmin]:checked').val();
			
			if(isadmin!='1'&&isadmin!='4'){
				
			}else{
				$('#jclyc').css("display","none");
			}

			$(".Cancel").click(function() {
				var index = index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
			
			form.on('checkbox()', function(data) {	
				
				var bparent = $(data.othis).parent();
				var b = bparent.hasClass('jclQt');
				if (data.elem.checked) {
					// 如果是被选中 判断其它是否都被选中
					if (b) {
						// 点中了标题
					/*	bparent.next().find('.layui-form-checkbox').addClass(
								'layui-form-checked');*/
						 
						bparent.next().find('.layui-form-checkbox').each(function(){
						
							if(!$(this).hasClass('layui-checkbox-disbaled')){
								$(this).addClass('layui-form-checked');
							}
						});
					} else {

                        	bparent.prev().find('.layui-form-checkbox').addClass('layui-form-checked');
					}
				} else {
					
					if (b) {
						// 点中了标题
						bparent.next().find('.layui-form-checkbox').removeClass(
								'layui-form-checked');
					} else {
						  var count= bparent.find('.layui-form-checkbox');
	                      var scount = bparent.find('.layui-form-checked');
	                      
	                      if(count.length!=scount.length&&scount.length==0){
	                    	  bparent.prev().find('.layui-form-checkbox').removeClass('layui-form-checked');
	                      }
					}
				}
				
				
				

			});

			
			
			form.on('radio()', function(data){
				  console.log(data.value);
				  if(data.value==4){
					  $('#jclyc').css("display","none");
				  }else{
					  $('#jclyc').css("display","");
				  }
			});  
			
			
			form.on('submit(formDemo)', function(data) {
				
				var field =data.field;
				
				var checks = $('input[name=qx]');
				var arr = new Array();
				var index = 0;
				checks.each(function(){
					if($(this).next().hasClass('layui-form-checked')){
						arr[index++]=$(this).val();
					}
				});
               
				var log ={
					arr:arr.toString(),
					isadmin:field.isadmin,
					username:field.username,
					id:field.id,
					openid:field.openid,
					flag:field.flag
				};
				
				$.ajax({
					url : url,
					type : 'post',
					async : false,
					data:log,
					success : function(res) {
                      if(res.code=='200'){
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
					  if(res.code=='400'){
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

	exports('jclQx', obj);
});