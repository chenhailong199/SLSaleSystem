<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
	<div>
		<ul class="breadcrumb">
	         <li>
	             <a href="#">后台管理</a>
	         </li>
	         <li>
	             <a href="<%=request.getContextPath()%>/background/userlist.html">用户管理</a>
	         </li>
	     </ul>
	</div>
	<div class="box-inner">
		 <div class="box-header well" data-original-title="">
			<h2><i class="glyphicon glyphicon-user"></i> 用户列表</h2>
	        <div class="box-icon">
	            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-plus"></i></a>
	        </div>
	    </div>
		<div class="box-content">
			<form action="<%=request.getContextPath() %>/background/userlist.html" method="post">
				<div>
					用户名称:<input type="text" name="s_loginCode" value="${s_loginCode }">
					推荐人:<input type="text" name="s_referCode" value="${s_referCode }">
					角色:<select name="s_roleId" style="width:100px;height:30px;">
							<option value="" selected="selected">--请选择--</option>
							<c:forEach items="${roleList }" var="role">
								<option <c:if test="${role.id == s_roleId }"> selected="selected" </c:if> value="${role.id }">
									${role.roleName }
								</option>
							</c:forEach>
						</select>
					是否启用:<select name="s_status" style="width:100px;height:30px;">
								<option value="-1" selected="selected">--请选择--</option>
								<c:if test="${s_status == 1 }">
									<option value="1" selected="selected">启用</option>
									<option value="0" >未启用</option>
								</c:if>
								<c:if test="${s_status == 0 }">
									<option value="1" >启用</option>
									<option value="0" selected="selected">未启用</option>
								</c:if>
								<c:if test="${s_status == null || s_status == '' }">
									<option value="1" >启用</option>
									<option value="0" >未启用</option>
								</c:if>
					   	   </select>
					  <button class="btn btn-primary btn-sm"><i class="icon-search icon-white">查询</i></button> 	   	
				</div>
			</form><!-- datatable -->
			<table class="table table-striped table-bordered bootstrap-datatable  responsive">
			    <thead>
				    <tr>
				        <th>用户名</th>
				        <th>角色</th>
				        <th>会员类型</th>
				        <th>推荐人</th>
				        <th>状态</th>
				        <th>注册时间</th>
						<th>操作</th>
				    </tr>
			    </thead>
			    <tbody>
			    	<c:if test="${page.items != null }">
			    		<c:forEach items="${page.items }" var="user">
			    			<tr>
				        		<td class="center">${user.loginCode}</td>
				        		<td class="center">${user.role.roleName }</td>
				        		<td class="center">${user.userTypeId }</td>
				        		<td class="center">${user.referCode }</td>
				        		<td class="center">
				        			<c:if test="${user.status == 0 }"><span class="label-default label">未启用</span></c:if>
				        		    <c:if test="${user.status == 1 }"><span class="label-success label label-default">启用</span></c:if>
				        		</td>
				        		<td>
				        			<f:formatDate value="${user.createdTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				        		</td>
				        		 <td class="center">
						            <a class="btn btn-success" href="#">
						                <i class="glyphicon glyphicon-zoom-in icon-white"></i>查看 
						            </a>
						            <a class="btn btn-info" href="#">
						                <i class="glyphicon glyphicon-edit icon-white"></i>修改
						            </a>
						            <a class="btn btn-danger" href="#">
						                <i class="glyphicon glyphicon-trash icon-white"></i>删除
						            </a>
						        </td>
				        	</tr>	
			    		</c:forEach>
			    	</c:if>
			    
				    <tr>
				        <td>David R</td>
				        <td class="center">会员</td>
				        <td class="center">注册会员</td>
				        <td class="center">admin</td>
				        <td class="center">
				            <span class="label-success label label-default">启用</span>
				            <span class="label-default label">禁用</span>
				        </td>
				        <td class="center">2017/06/18</td>
				        <td class="center">
				            <a class="btn btn-success" href="#">
				                <i class="glyphicon glyphicon-zoom-in icon-white"></i>查看 
				            </a>
				            <a class="btn btn-info" href="#">
				                <i class="glyphicon glyphicon-edit icon-white"></i>修改
				            </a>
				            <a class="btn btn-danger" href="#">
				                <i class="glyphicon glyphicon-trash icon-white"></i>删除
				            </a>
				        </td>
				    </tr>
				 </tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:choose>
						<c:when test="${page.currentPage == 1 }">
							<li class="active"><a href="javascript:void();" title="首页">首页</a></li>          
						</c:when>
					
					</c:choose>
				
				</ul>
				
			</div>
			
		   <!--  <ul class="pagination pagination-centered">
	            <li><a href="#">Prev</a></li>
	            <li class="active">
	                <a href="#">1</a>
	            </li>
	            <li><a href="#">2</a></li>
	            <li><a href="#">3</a></li>
	            <li><a href="#">4</a></li>
	            <li><a href="#">Next</a></li>
	        </ul> -->
		</div>
	</div>

<%@ include file="../common/foot.jsp" %>