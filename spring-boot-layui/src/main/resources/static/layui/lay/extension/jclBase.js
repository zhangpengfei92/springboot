layui.define(
	[ 'form', 'layer','table','laytpl','laydate'],function(exports){

		var form = layui.form, $ = layui.jquery, layer = layui.layer,table=layui.table,laytpl=layui.laytpl,laydate = layui.laydate;
		 var obj = {
				 init:function(path,id,parameters,dateelem){

					 
					var ConstantHeight=480;
					var clientHeight = document.body.clientHeight;
					$(".layui-row").each(function(){
						clientHeight=	clientHeight-this.clientHeight;
					});
					
					if(clientHeight>0){
					    var layData= $(".layui-table").attr('lay-data');
					    eval('var obj = '+layData);
					    var options = {

								 height: obj.height+clientHeight
					    };
					    table.reload('test',options)
					    ConstantHeight=obj.height+clientHeight;
					}					
					
						document.onkeydown = function (e) {
				            var ev = window.event || e;
				            var code = ev.keyCode || ev.which;
				            if (code == 116) {
				                ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
				                cancelBubble = true;
				                return false;
				            }
				        }
					 
					 
					    function	DownLoadFile(options) {
						    var config = $.extend(true, { method: 'post' }, options);
						    var $iframe = $('<iframe id="down-file-iframe" />');
						    var $form = $('<form target="down-file-iframe" method="' + config.method + '" accept-charset="UTF-8"/>');
						    $form.attr('action', config.url);
						    for (var key in config.data) {
						        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
						    }
						    $iframe.append($form);
						    $(document.body).append($iframe);
						    $form[0].submit();
						    $iframe.remove();
						}
					 
					   function closeIndex(){
							var index = index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
					   };
					   function MoveSortTable(layData,context,pars){
						   var closeindex=	layer.msg('数据请求中', {
								icon: 16,
								anim: -1,
								fixed: !1
							});
						   
						   
						   if(layData.state=='3'){
							
						          var h= $(context).find("input").val();
					
						   }
						   var value =h==undefined?"":"&value="+h;
						   
							  $.ajax({
							    	url:layData.url+'?state='+layData.state+"&"+id+"="+pars+value,
							    	type:'post',
							    	async:false,
							    	success:function(res){
							    		if(res.code=="200"){
							    			 layer.msg(res.msg);
							    			    var data=  $(".layui-table").attr('lay-data');
										 		eval('var layData2='+data);
								    		 	var options={
										    			  height: ConstantHeight 
										    			  ,url:layData2.url
										    			  ,done:function(res, curr, count){
			               					    				  layer.close(closeindex); 
			               					    			  }
										    			 };
										    	table.reload('test',options);
							    		}
							    		if (res.code=="400") {
											layer.msg(res.msg);
										}
							    	},
							    	error:function(){
							    		layer.msg('系统异常');
							    	}
							    });
						   
					   }
					   
					   function SimpleConfirmAlert(layData){
					   		 layer.confirm('是否操作', {
                   			  btn: ['确定', '取消'] 
                   			  ,btn1: function(index, layero){
                   				  //隐藏确定键，避免重复点击
                   				$(".layui-layer-btn0")[0].style.display="none";
                   				  $.ajax({
 								    	url:layData.url,
 								    	type:'post',
 								    	async:false,
 								    	success:function(res){
 								    		console.log(res);
 								    		if(res.code=="200"){
 								    			console.log('进进进');
 								    			var closeindex=	layer.msg('数据请求中', {
 													icon: 16,
 													anim: -1,
 													fixed: !1
 												});
 								    	        var data=  $(".layui-table").attr('lay-data');
 										 		eval('var layData2='+data);
 								    		 	var options={
 										    			  height: ConstantHeight 
 										    			  ,url:layData2.url
 										    			  ,done:function(res, curr, count){
 		               					    				  layer.close(closeindex); 
 		               					    			  }
 										    			 };
                                                var jclok= $('.jclok');

                                                if(jclok.length==0){
                                                    table.reload('test',options);
												}else{
                                                    layer.close(closeindex);
                                                    $('.layui-laypage-btn').click();
												}
 								    		}
 								    		if (res.code=="400") {
 												layer.msg(res.msg);
 											}
 								    	},
 								    	error:function(){
 								    		layer.msg('系统异常');
 								    	}
 								    });
                   				
                   			  }
                   			  ,btn2: function(index, layero){
                   				  closeIndex();
                   			  }
                   			  
                   		 });
						   
					   }

                     eachLaydate(dateelem);

					   //格式化搜索条件中的日期查询 的日期框的文本格式
					    function eachLaydate(elems) {

					    	if(elems==undefined){
					    		return;
					    	}

							for ( var i = 0; i < elems.length; i++) {
								laydate.render({
									elem : elems[i],
									theme : '#009688',
									format: 'yyyy-MM-dd HH:mm:ss',
									type: 'datetime'
								});
							}
						}

					    
					    //批量审核操作,发起签约,解约,初始化用户资金密码
					    $("#jclbatch1,#jclbatch3,#jclbatch2,#sign,#unsign,#init").click(function(){
					  	  var closeindex=	layer.msg('数据请求中', {
								icon: 16,
								anim: -1,
								fixed: !1
							});
					    	var str=	$(this).attr('lay-data');
					    	var checkStatus = table.checkStatus('test');
					    	 if(checkStatus.data.length!=0){
					    		 var pars="";
									var index=0;
									$(checkStatus.data).each(function(){
									
									   for(var i in this){
										   if(i==id){
											   pars+=this[i]+",";										   
										   }
									   }
									});
									var str=$(this).attr('lay-data');
									eval('var layData='+str);
		                            pars=pars.substring(0, pars.length-1);
		                      	  $.ajax({
								    	url:layData.url,
								    	type:'post',
								    	async:false,
								    	dataType:'json',
								    	data:{ids:pars},
								    	success:function(res){
								    		if(res.code=="200"){
								    		   layer.msg(res.msg);
								    	        var data=  $(".layui-table").attr('lay-data');
										 		eval('var layData2='+data);
								    		 	var options={
										    			  height: ConstantHeight 
										    			  ,url:layData2.url
										    			  ,done:function(res, curr, count){
                     					    				  layer.close(closeindex); 
                     					    			  }
										    			 };
										    	table.reload('test',options);
								    		}
								    		if (res.code=="400") {
												layer.msg(res.msg);
											}
								    	},
								    	error:function(){
								    		layer.msg('系统异常');
								    	}
								    });
					    	 }else{
					    		 layer.msg('请勾选要操作的数据');
					    	 }
					    });

					    //修改
					    $('.updatebtn').click(function(){
                            var str=	$(this).attr('lay-data');
                            var checkStatus = table.checkStatus('test');
                            if(checkStatus.data.length!=0){
                            	if(checkStatus.data.length!=1){
                                    layer.msg('只能选择一条数据');
                                    return;
								}
                                var pars="";
                                var pes="";
                                var tar="";
                                var type="";
                                var index=0;
                                $(checkStatus.data).each(function(){

                                    for(var i in this){
                                        if(i==id){
                                            pars+=this[i]+",";
                                        }
                                        if(i=='commTrade'){
                                            pes+=this[i]+",";
                                        }
                                        if(i=='trade'){
                                            tar+=this[i]+",";
                                        }
                                        if(i=='type'){
                                            type+=this[i]+",";
                                        }
                                    }

                                });
                                var str=$(this).attr('lay-data');
                                eval('var layData='+str);
                                pars=pars.substring(0, pars.length-1);
                                pes=pes.substring(0, pes.length-1);
                                tar=tar.substring(0,tar.length-1);
                                type=type.substring(0,type.length-1);

                                layer.open({
                                    title: layData.title
                                    ,type:2
                                    ,content:layData.url+"?ids="+pars+"&ts="+pes+"&tar="+tar+"&type="+type
                                    ,area:[layData.width+"px",layData.height+"px"]
                                    ,moveOut:false
                                    ,end:function(){

                                    }
                                });
                            }else{
                                layer.msg('请勾选要操作的数据');
                            }
						});


                        //批量修改
					    $("#lotupdate").click(function(){
					  
					   	 var str=	$(this).attr('lay-data');
				    	 var checkStatus = table.checkStatus('test');
				    	 if(checkStatus.data.length!=0){
				    			var pars="";
				    			var pes="";
				    			var tar="";
				    			var type="";
								var index=0;
								$(checkStatus.data).each(function(){
								
								   for(var i in this){
									   if(i==id){
										   pars+=this[i]+",";										   
									   }
									   if(i=='commTrade'){
										   pes+=this[i]+",";
									   }
									   if(i=='trade'){
										   tar+=this[i]+",";
									   }
									   if(i=='type'){
										   type+=this[i]+",";
									   }
								   }
								   
								});
								var str=$(this).attr('lay-data');
								eval('var layData='+str);
	                            pars=pars.substring(0, pars.length-1);
	                            pes=pes.substring(0, pes.length-1);
	                            tar=tar.substring(0,tar.length-1);
	                            type=type.substring(0,type.length-1);
	                            
	                            layer.open({
									  title: layData.title
									  ,type:2
									  ,content:layData.url+"?ids="+pars+"&ts="+pes+"&tar="+tar+"&type="+type
									  ,area:[layData.width+"px",layData.height+"px"]
									  ,moveOut:false
									  ,end:function(){
									
									  }
								});
				    	 }else{
				    		 layer.msg('请勾选要操作的数据');
				    	 }
					    });
					    
						//新增操作skip
						$("#addbtn").click(function(){
							
						    var str=	$(this).attr('lay-data');
						    eval('var layData='+str);
						    layer.open({
								  title: layData.title
								  ,type:2
								  ,content:layData.url
								  ,area:[layData.width+"px",layData.height+"px"]
								  ,moveOut:true
								  ,maxmin:true
								  ,end:function(){
							
								  }
							});
						});
                     //新增操作skip
                     $(".addbtn").click(function(){                   	 
                         var str=	$(this).attr('lay-data');
                         eval('var layData='+str);
                         layer.open({
                             title: layData.title
                             ,type:2
                             ,content:layData.url
                             ,area:[layData.width+"px",layData.height+"px"]
                             ,moveOut:true
                             ,maxmin:true
                             ,end:function(){

                             }
                         });
                     });
                     //删除操作
						$("#delete").click(function(){
							var closeindex=	layer.msg('数据请求中', {
								icon: 16,
								anim: -1,
								fixed: !1
							});
							var checkStatus = table.checkStatus('test');
							if(checkStatus.data.length!=0){
								var pars="";
								var pps="";
								var index=0;
								$(checkStatus.data).each(function(){
								
								   for(var i in this){
									   if(i==id){
										   pars+=this[i]+",";
									   }
									   if(i=='trade'){
										   pps+=this[i]+",";
									   }
								   }
								});
								var str=$(this).attr('lay-data');
								eval('var layData='+str);	
	                            pars=pars.substring(0, pars.length-1);
	                            pps=pps.substring(0, pps.length-1);
								  $.ajax({
								    	url:layData.url,
								    	type:'post',
								    	async:false,
								    	dataType:'json',
								    	data:{
								    		ids:pars,
								    		trades:pps
								    		},
								    	success:function(res){
								    		if(res.code=="200"){
								    			 layer.msg(res.msg);
								    			  var data=  $(".layui-table").attr('lay-data');
											 		eval('var layData2='+data);
									    		 	var options={
											    			  height: ConstantHeight 
											    			  ,url:layData2.url
											    			  ,done:function(res, curr, count){
	                       					    				  layer.close(closeindex); 
	                       					    			  }
											    			 };
											    	table.reload('test',options);
								    		}
								    		if (res.code=="400") {
												layer.msg(res.msg);
											}
								    	},
								    	error:function(){
								    		layer.msg('系统异常');
								    	}
								    });
								 
							}else{
								layer.msg('请勾选要操作的数据');
							}
						});
					  
					    //对表格的监听事件
					    table.on('tool(test)',function(obj){
					    	var data = obj.data;
					    	var tr = obj.tr;
					    	for(var i in parameters){
					    		if(obj.event==i){
					    			var tds=  $(tr).find('td');
						    		tds.each(function(){
						    			if($(this).attr('data-field')==id){
						    			     var tdss= $(tr).find('td');
						    			     var pars = $(this).find('div').text();		   
						    			     tdss.each(function(){
						    			    	 
						    			    	 if($(this).attr('data-field')==i){
						    			    		 var str=$(this).find("div").find('a').attr('lay-data');
						    			    		 
						    			    		 eval('var layData='+str);
						    			    	     
                                                     try{
                                                    	 if(layData.type=='2'){
                                                    		  layer.open({
        														  title: layData.title
        														  ,type:2
        														  ,content:layData.url+"?id="+pars
        														  ,area:[layData.width+"px",layData.height+"px"]
        														  ,moveOut:true
        														  ,end:function(){
        														
        														  }
        													  });
                                                    	 }
                                                    	 if(layData.type=='3'){
                                                    		 //简单的弹出
                                                    		 SimpleConfirmAlert(layData);
                                                    	 }
                                                    	 if(layData.type=="4"){
                                                    		MoveSortTable(layData,this,pars);
                                                    	 }
                                       	    	
                                                     }catch(error){
                                                    	 eval('var layData3='+parameters[i]);
                                                    
                                                    	 if(layData3.type=='2'){
                                                    		 
                                                    		 layer.open({
                                                    			 title: layData3.title
                                                    			 ,type:2
                                                    			 ,content:layData3.url+"?id="+pars
                                                    			 ,area:[layData3.width+"px",layData3.height+"px"]
                                                    		 ,moveOut:true
                                                    		 ,end:function(){
                                           
                                                    		 }
                                                    		 });
                                                    	 }else if(layData3.type=='3'){
                                                    		 SimpleConfirmAlert(layData3);
                                                    	 }
                                                     }
						    		
						    			    	 }
						    			     });
						    			}
						    		});
					    		}
					    	}
					     });
					    //对导出表格的按钮的监听
						form.on('submit(formexport)',function(data){
					
							var field = data.field;
							var Gcount = [];
							for(var i in field){
								Gcount.push('"'+i+'":"'+field[i]+'"');
					    	}
							$("thead tr th").each(function(){
							     if($(this).attr("data-field")!=undefined){
							    	 Gcount.push('"'+"*"+$(this).attr("data-field")+'":"'+$(this).find("div").find("span").text()+'"');
							     }
							});
							var obj="{";
							for(var i=0;i<Gcount.length;i++){
								
								obj+=Gcount[i];
								if(i!=Gcount.length-1){
									obj+=",";
								}else{
									obj+="}";
								}
							}
							eval('var obj0='+obj);
							var options = {
									url:path+"/partExport", 
									data:obj0
							};
							DownLoadFile(options);
							return;
						});
					    
					    //对搜索栏的监听事件
					    form.on('submit(formDemo)',function(data){

					        var closeindex=	layer.msg('数据请求中', {
								icon: 16,
								anim: -1,
								fixed: !1
							});
					    	var field = data.field;
					    	var pars="";
					    	var index=0;
					    	for(var i in field){
					    		pars+=(index++)==0?'?':'&';
					    		pars+=i+"="+field[i];
					    	}
					        var data=  $(".layui-table").attr('lay-data');
					 		eval('var layData='+data);
					 		var url=layData.url;
					 		if(url.indexOf("?")>0){
					 			url=url.substring(0,url.indexOf("?"));
					 		}
					    	var options={
					    			  height: ConstantHeight 
					    			  ,url:url+pars
					    			  ,done:function(res, curr, count){
					    				  layer.close(closeindex); 
					    			  }
					    			 };
					    	
					    	table.reload('test',options);
					    	return false;
					    });
					    
					    //对当前时间的监听
						form.on('select(at)', function(data){
							  if(data.value==0){
								  layer.msg('查询当前实时数据,只显示今日实时数据');
								  $("#entrusttimestart").val('');
								  
								  $("#entrusttimestart").attr("disabled",true);
								  
								  $("#entrusttimeend").val('');
								  
								  $("#entrusttimeend").attr("disabled",true);
							  }else{
								  $("#entrusttimestart").attr("disabled",false);
								  $("#entrusttimeend").attr("disabled",false);
							  }
							 
							 
					    }); 
				 }
		   
	     };
		exports('jclBase',obj);
});