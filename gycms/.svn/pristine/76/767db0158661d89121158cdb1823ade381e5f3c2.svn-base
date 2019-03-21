<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>规则管理</title>
    <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-fluid">

        <div class="layui-row" style="background-color: white; padding: 10px">
            <form class="layui-form" action="">                
                <div class="layui-form-item">
                    <label class="layui-form-label">名称:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="rulename" autocomplete="off"
                               class="layui-input">
                    </div>
                    <button class="layui-btn" lay-submit
                            lay-filter="formDemo">搜索</button>

                    <button type="reset" class="layui-btn ">重置</button>

                </div>
            </form>
        </div>
        <div class="layui-row" style="background-color: white; padding: 10px">
            <table class="layui-table"
                   lay-data="{height:430, url:'region/rulelist/${region.id}', page:true, id:'test'}"
                   lay-filter="test">
                <thead>
                <tr>
                    <th lay-data="{type:'numbers'}">序号</th>
                    <th lay-data="{field:'id',space:true}">id</th>
                    <th lay-data="{field:'rulename',minWidth:120}">名称</th>
                    <th lay-data="{field:'rulenum',minWidth:80}">信息1</th>
                    <th lay-data="{field:'rulenum1',minWidth:80}">信息2</th>
                    <th lay-data="{field:'rulenum2',minWidth:80}">信息3</th>
                    <th lay-data="{field:'rulenum3',minWidth:80}">信息4</th>
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
                    'sendmsg':'',
                    'updataintegral':"",
                    'updata':""
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
        function formatterStatus(cellvalue) {
    		if (cellvalue != null && cellvalue != "") {
    			if(cellvalue == "0"){
    				cellvalue = "未开赛";
    			} else if(cellvalue == "1"){
    				cellvalue = "进行中";
    			} else {
    				cellvalue = "已结束";
    			}
    			return cellvalue;
    		}
    		return "";
    	} 
        function formattertype(cellvalue) {
        	if (cellvalue != null && cellvalue != "") {
    			if(cellvalue == "1"){
    				cellvalue = "证券赛场";
    			} else {
    				cellvalue = "高校赛场";
    			}
    			return cellvalue;
    		}
    		return "";
    	}  
    </script>

    <script type="text/template" id="update">
        <a  lay-event="sendmsg" class="layui-btn layui-btn-normal1 layui-btn-xs "
            lay-data="{url:'region/alert/updata',type:'2',width:490,height:450,title:'修改'}">修改</a>
    </script>
    <script type="text/template" id="importuser">
		{{# if(d.type==3){ }}
			        <a  lay-event="updataintegral" class="layui-btn layui-btn-norma layui-btn-xs "
            lay-data="{url:'region/alert/importuser',type:'2',width:490,height:450,title:'导入用户'}">导入用户</a>
		
		{{#  } }}
    </script>
    <script type="text/template" id="importdata">
		{{# if(d.ispublic==2){}}
			 <a  lay-event="updata" class="layui-btn layui-btn-normal2 layui-btn-xs "
            lay-data="{url:'region/alert/importdata',type:'2',width:490,height:450,title:'导入数据'}">导入数据</a>
			
			<a  lay-event="updata" class="layui-btn layui-btn-normal2 layui-btn-xs "
            lay-data="{url:'region/alert/rulelist',type:'2',width:800,height:600,title:'规则管理'}">规则管理</a>
		{{#  } }}
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
    
    <script type="text/template" id="fmtStatus">
        {{#  return formatterStatus(d.status);
        }}
    </script>
    
    <script type="text/template" id="fmttype">
        {{#  return formattertype(d.type);
        }}
    </script>
</div>

</body>
</html>