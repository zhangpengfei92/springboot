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
                    <label class="layui-form-label">比赛名称:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="regionname" autocomplete="off"
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
                    lay-data="{url:'doc/alert/edit/${id}',width:800,height:700,title:'用户新增'}">
                <i class="layui-icon">&#xe654;</i>新增
            </button>
            <table class="layui-table"
                   lay-data="{height:450, url:'doc/list/${id}', page:true, id:'test'}"
                   lay-filter="test">
                <thead>
                <tr>
                    <th lay-data="{type:'numbers'}">序号</th>
                    <th lay-data="{field:'id',space:true}">id</th>
                    <th lay-data="{field:'title',minWidth:120}">标题</th>
                    <th lay-data="{field:'summary',minWidth:80}">简介</th>
                    <th lay-data="{field:'status',minWidth:80,templet:'#fmtStatus'}">栏目</th>
                    <th lay-data="{field:'type',minWidth:120,templet:'#fmttype'}">状态</th>            
                    <th lay-data="{field:'createtime',minWidth:160,templet:'#updatetime'}">创建时间</th>                          
                    <th lay-data="{field:'update',templet:'#update',width:80,rowspan:'4'}">操作</th>
                    <th lay-data="{field:'importuser',templet:'#importuser',width:100}"></th>
                    <th lay-data="{field:'importdata',templet:'#importdata',width:100}"></th>
                    <th lay-data="{field:'rulelist',templet:'#rulelist',width:100}"></th>
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
                    'update':'',
                    'importuser':"",
                    'importdata':"",
                    'rulelist':""
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
        	//alert(value)
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

    <script type="text/template" id="update">
        <a  lay-event="update" class="layui-btn layui-btn-normal1 layui-btn-xs "
            lay-data="{url:'doc/alert/update/{{d.id}}',type:'2',width:800,height:700,title:'修改'}">修改</a>
    </script>
    <script type="text/template" id="importuser">
			        <a  lay-event="importuser" class="layui-btn layui-btn-norma layui-btn-xs "
            lay-data="{url:'doc/ispublic/1/{{d.id}}',type:'3'}">发布</a>		
    </script>
    <script type="text/template" id="importdata">	
			 <a  lay-event="importdata" class="layui-btn layui-btn-normal2 layui-btn-xs "
            lay-data="{url:'doc/ispublic/0/{{d.id}}',type:'3'}">取消发布</a>		
    </script>
    <script type="text/template" id="rulelist">			
			<a  lay-event="rulelist" class="layui-btn layui-btn-normal2 layui-btn-xs "
            lay-data="{url:'doc/delete/{{d.id}}',type:'3'}">删除</a>
    </script>
    
    <script type="text/template" id="updateStartTime">
        {{#  return formatterdata(d.starttime);
        }}
    </script>
    <script type="text/template" id="updateEndTime">
        {{#  return formatterdata(d.endtime);
        }}
    </script>
    <script type="text/template" id="updatetime">
        {{#  return formatterdata(d.createtime);
        }}
    </script>
    

</div>

</body>
</html>