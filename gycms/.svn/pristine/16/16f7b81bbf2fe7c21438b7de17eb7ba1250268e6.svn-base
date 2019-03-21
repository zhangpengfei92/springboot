<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>欢迎页</title>
    <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-fluid">

        <div class="layui-row" style="background-color: white; padding: 10px">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">开始时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="startDate" id="startTime" placeholder="yyyy-MM-dd HH:mm:ss">
                    </div>
                    <label class="layui-form-label">结束日期:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="endDate" id="endTime" placeholder="yyyy-MM-dd HH:mm:ss">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">关键字:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="ucname" autocomplete="off"
                               class="layui-input">
                    </div>
                    <button class="layui-btn" lay-submit
                            lay-filter="formDemo">搜索</button>

                    <button type="reset" class="layui-btn ">重置</button>

                </div>
            </form>
        </div>
        <div class="layui-row" style="background-color: white; padding: 10px">
                		  	
	            <button class="layui-btn layui-btn-sm addbtn"
	                    lay-data="{url:'keyword/alert/add/{{d.id}}',width:490,height:450,title:'用户新增'}">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>	          
            <table class="layui-table"
                   lay-data="{height:450, url:'keyword/list', page:true, id:'test'}"
                   lay-filter="test">
                <thead>
                <tr>
                    <th lay-data="{type:'numbers'}">序号</th>
                    <th lay-data="{field:'id',space:true}">id</th>
                    <th lay-data="{field:'keyword',minWidth:120}">关键字</th>                   
                    <th lay-data="{field:'createtime',minWidth:120,templet:'#updatetime'}">创建时间</th>
                    <th lay-data="{field:'del',templet:'#jdel',width:100}">操作</th>
                    <th lay-data="{field:'edit',templet:'#jedit',width:100}"></th>                   
                </tr>
                </thead>
            </table>
        </div>
    </div>
    
    <script type="text/javascript" src="static/layui/layui.js"></script>
    <script type="text/javascript">
        try {
            layui.config({
                base : 'static/layui/lay/extension/'
            }).use([ 'jclBase'], function() {
                var jclBase = layui.jclBase;
                var dateElems = [ '#startTime', '#endTime' ];
                jclBase.init('table', 'id', {
                    'del':'',
                    'edit':""
                }, dateElems);                 

            });
        } catch (e) {
            if ('layui is not defined' == e.message
                || '$ is not defined' == e.message) {
                console.log('页面重载了...');
                document.location.reload();
            }
        }
        function formatterdata(value) {        
            var date = new Date(value);
            var y = date.getFullYear();
            var M = date.getMonth() + 1;
            var d = date.getDate();
            var h = date.getHours();
            var m = date.getMinutes();
            var s = date.getSeconds();
            var str = y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
            if (str == "1970-1-1 8:0:0")
                return "";
            return str;
        }
        function keyToDecimal(x) {
            var f = parseFloat(x);
            if (isNaN(f)) {
                return "";
            }
            var f = Math.round(x * 100) / 100;
            var s = f.toString();
            var rs = s.indexOf('.');
            if (rs < 0) {
                rs = s.length;
                s += '.';
            }
            while (s.length <= rs + 2) {
                s += '0';
            }
            return s;
        }
    </script>

      <script type="text/template" id="jdel">
        <a  lay-event="del" class="layui-btn layui-btn-danger layui-btn-xs "
            lay-data="{url:'keyword/delkeyword/{{d.id}}',type:'3'}">删除</a>
    </script>
    <script type="text/template" id="jedit">
        <a  lay-event="edit" class="layui-btn layui-btn-normal layui-btn-xs "
            lay-data="{url:'keyword/alert/edit/{{d.id}}',type:'2',width:490,height:450,title:'修改关键字'}">修改</a>
    </script>
    <script type="text/template" id="updatetime">
        {{#  return formatterdata(d.createtime);
        }}
    </script>
</div>

</body>
</html>