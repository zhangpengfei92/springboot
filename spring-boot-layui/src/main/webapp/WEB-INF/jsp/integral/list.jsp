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
                    <label class="layui-form-label">用户名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
		               <div class="layui-inline">
					      <label class="layui-form-label">积分来源</label>
					      <div class="layui-input-inline">
					        <select name="integraltype" lay-verify="required" lay-search="">
					          <option value="0">全部</option>
					          <option value="1">签到</option>
					          <option value="2">消费</option>
					          <option value="3">排名奖励</option>
					        </select>
					      </div>
                    <button class="layui-btn" lay-submit
                            lay-filter="formDemo">搜索</button>

                    <button type="reset" class="layui-btn ">重置</button>

                </div>
            </form>
        </div>
        <div class="layui-row" style="background-color: white; padding: 10px">      
            <table class="layui-table"
                   lay-data="{height:450, url:'integral/list', page:true, id:'test'}"
                   lay-filter="test">
                <thead>
                <tr>
                    <th lay-data="{type:'numbers'}">序号</th>
                    <th lay-data="{field:'id',space:true}">id</th>
                    <th lay-data="{field:'username',minWidth:120}">用户名</th>
                    <th lay-data="{field:'integraltype',minWidth:120,templet:'#fmttype'}">积分来源</th>
                    <th lay-data="{field:'integralnum',minWidth:120}">积分</th>
                    <th lay-data="{field:'createtime',minWidth:120,templet:'#updatetime'}">时间</th>
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
            jclBase.init('table', 'id', {}, dateElems);

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
        
        function formattype(cellvalue) {
        	if (cellvalue != null && cellvalue != "") {
    			if(cellvalue==1){
    				cellvalue="签到";
    			}else{
    				cellvalue="消费"
    			}
    			return cellvalue;
    		}
    		return "";
		}
        
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
    
    <script type="text/template" id="fmttype">
        {{#  return formattype(d.integraltype);
        }}
    </script>
</div>

</body>
</html>