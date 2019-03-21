<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="static/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="layui-fluid" style="background-color: white; padding: 10px">
    <div class="layui-row" >
        <form class="layui-form" action="" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" id="regionid" value="${region.id}">
            <div class="layui-form-item">
                <label class="layui-form-labels">比赛名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="regionname" value="${region.regionname}" placeholder="请输入比赛名称" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>

           <div class="layui-upload">
			  <button type="button" class="layui-btn" id="iconupload">比赛图标</button>
			  <div class="layui-upload-list">
			    <img class="layui-upload-img" id="iconImg" style="float: right;">
			    <c:if test="${!empty  region.headpicture}">
			    	<p id="show">图片地址：${region.headpicture}</p>
			    </c:if>			    
			    <p id="iconUrl"></p>
			    <input type="hidden" id="iconUrl" name="headpicture" value="${region.headpicture}">
			  </div>
			</div>  
           <div class="layui-inline">
				<label class="layui-form-label">类型</label>
				<div class="layui-input-inline">
				<select name="type" value="${region.type}" lay-verify="required" lay-search="">
					<option value="1" <c:if test="${region.type==1}">selected='selected'</c:if> >证券赛场</option>
					<option value="2" <c:if test="${region.type==2}">selected='selected'</c:if> >高校赛场</option>
				 </select>
			</div>
			</div>
			
			 <div class="layui-inline">
				<label class="layui-form-label">是否公开</label>
				<div class="layui-input-inline">
				<select name="ispublic" value="${region.ispublic}" lay-verify="required" lay-search="">
					<option value="1" <c:if test="${region.ispublic==1}">selected='selected'</c:if> >公开</option>
					<option value="2" <c:if test="${region.ispublic==2}">selected='selected'</c:if> >不公开</option>
				 </select>
			</div>
			</div>
			
			 <div class="layui-form-item">
			    <label class="layui-form-label">验证信息</label>
			    <div class="layui-input-block">
			      <input type="checkbox" name="rightrules" value="身份证" <c:if test="${fn:contains(region.rightrule,'身份证')||empty  region.rightrule}"> checked="" </c:if> title="身份证">
			      <input type="checkbox" name="rightrules" value="学号" <c:if test="${fn:contains(region.rightrule,'学号')}"> checked="" </c:if> title="学号">
			      <input type="checkbox" name="rightrules" value="证券编号" <c:if test="${fn:contains(region.rightrule,'证券编号')}"> checked="" </c:if> title="证券编号">
			      <input type="checkbox" name="rightrules" value="其他" <c:if test="${fn:contains(region.rightrule,'其他')}"> checked="" </c:if> title="其他"><span>不公开时选择验证信息</span>
			    </div>
			 </div>		
			 <div class="layui-form-item">
                <label class="layui-form-labels">初始资金:</label>
                <div class="layui-input-inline">
                    <input type="text" name="initmoney" value="${region.initmoney}" placeholder="请输入初始资金"   lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
			 
			<div class="layui-inline">
				<label class="layui-form-label">状态</label>
				<div class="layui-input-inline">
				<select name="status" value="${region.status}" lay-verify="required" lay-search="">
					<option value="0" <c:if test="${region.status==0}">selected="selected"</c:if>  >未开赛</option>
					<option value="1" <c:if test="${region.status==1}">selected="selected"</c:if>  >进行中</option>
					<option value="2" <c:if test="${region.status==2}">selected="selected"</c:if>  >已结束</option>
				 </select>
			</div>
			</div>
			<br><br><br>
			<div class="layui-form-item">
                    <label class="layui-form-label">开始注册时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="<fmt:formatDate value="${region.startregister}" pattern="yyyy-MM-dd"/>"  name="startregister" id="startregister" placeholder="yyyy-MM-dd">
                    </div>
                    <label class="layui-form-label">结束注册日期:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="<fmt:formatDate value="${region.endregister}" pattern="yyyy-MM-dd"/>" name="endregister" id="endregister" placeholder="yyyy-MM-dd">
                    </div>
            </div>
            
            <div class="layui-form-item">
                    <label class="layui-form-label">开始比赛时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="<fmt:formatDate value="${region.starttime}" pattern="yyyy-MM-dd"/>" name="starttime" id="starttime" placeholder="yyyy-MM-dd">
                    </div>
                    <label class="layui-form-label">结束比赛日期:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="<fmt:formatDate value="${region.endtime}" pattern="yyyy-MM-dd"/>" name="endtime" id="endtime" placeholder="yyyy-MM-dd">
                    </div>
            </div>
			 
			 <div class="layui-form-item">
                <label class="layui-form-labels">参赛人数:</label>
                <div class="layui-input-inline">
                    <input type="text" name="totalpeople" value="${region.totalpeople}"  placeholder="请输入参赛人数"  lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-labels">比赛介绍:</label>
                <div class="layui-input-inline">
                    <input type="text" name="description" value="${region.description}"  placeholder="请输入比赛简介" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
			
			<div class="layui-inline">
				<label class="layui-form-label">交易观点</label>
				<div class="layui-input-inline">
				<select name="isidea" value="${region.isidea}" lay-verify="required" lay-search="">
					<option value="1" <c:if test="${region.isidea==1}">selected="selected"</c:if>>有</option>
					<option value="2" <c:if test="${region.isidea==2}">selected="selected"</c:if>>无</option>					
				 </select>
			</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">是否默认选中</label>
				<div class="layui-input-inline">
				<select name="isideacheck" value="${region.isideacheck}" lay-verify="required" lay-search="">
					<option value="1" <c:if test="${region.isideacheck==1}">selected="selected"</c:if> >是</option>
					<option value="2" <c:if test="${region.isideacheck==2}">selected="selected"</c:if> >否</option>
				 </select>
			</div>
			</div> 
			 
            <div class="layui-layer-btn layui-layer-btn-">
                <button class="layui-btn layui-btn-sm" lay-submit
                        lay-filter="formDemo">确定</button>
                <button type="button" class="layui-btn layui-btn-sm  layui-btn-primary Cancel">取消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base:'static/layui/lay/extension/'
    }).use(['jclAdd','laydate','jclUpload'], function() {
        var jclAdd =layui.jclAdd,laydate =layui.laydate;
        
        var regionid=$("#id").val();
        if(regionid!=""){
        	
        }
        
        jclAdd.init('region/addregion');      
        
        laydate.render({
            elem: '#startregister'
        });
        laydate.render({
            elem: '#endregister'
        });        
        laydate.render({
            elem: '#starttime'
        });
        laydate.render({
            elem: '#endtime'
        });			
    });   
</script>

</body>
</html>