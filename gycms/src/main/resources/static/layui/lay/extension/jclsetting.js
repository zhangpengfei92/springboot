layui.define([ 'form', 'layer','tree'], function(exports) {

	var form = layui.form, $ = layui.jquery, layer = layui.layer;

	var obj = {
		init : function(path) {
			$(".Cancel").click(function() {
				var index = index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
			form.on('submit(jclsettingFrom)', function(data) {
                
				$.ajax({
					url : path,
					type : 'post',
					async : false,
					data:{
					 psaId:$("#psaId").val(),
					 state:$(data.elem).attr("par")
					},
					success : function(res) {
                  
						if (res == "ok") {

					
						          var pars ='?';
				                  $(".layui-form input",window.parent.document).each(function(){
				               	   if($(this).attr('name')!=undefined){
				               		   pars+=$(this).attr('name');
				               		   pars+='=';
				               		   pars+=$(this).val();
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
					},
					error : function() {
						layer.msg('系统异常');
					}
				});
				return false;
			});
		}
	};
	exports('jclsetting', obj);
});