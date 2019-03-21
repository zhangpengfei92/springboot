layui.define(['layer','tree'], function(exports) {

	var $ = layui.jquery, layer = layui.layer;

	var obj = {
		init : function(path,options,parentCheck) {
			
			
			
			$(".treeNo").click(function(){
				var index = index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
			$(".treeok").click(function(){
			      var $inputs=	$("#jcltree li ul input");
	           
			    
			      
			});
			
			
			var tree = layui.tree({
				elem: '#jcltree', 
				check: 'checkbox', 
				skin: 'as', 
				drag: true,
				checkboxName: 'aa[]',
				checkboxStyle: "checkbox",
				click: function(item) { 
				  
				},
				onchange: function (){
			    
				},
				nodes: [
					{
						name: '角色选择',
                        spread: true, 				
						href: "",
						target: "_self",
						alias: 'jcl',
						checked: parentCheck,
						children:options
					}
				]
			});

		}
	};
	exports('jclTree', obj);
});