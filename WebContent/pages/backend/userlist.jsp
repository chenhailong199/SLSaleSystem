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
	            <a href="#" class="btn btn-setting btn-round btn-default addUser"><i class="glyphicon glyphicon-plus"></i></a>
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
					  <button class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-search"> 查询</i></button> 	   	
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
				        		<td class="center">${user.userTypeName }</td>
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
				 </tbody>
			</table>
			<div>
				<ul class="pagination pagination-centered">
					<c:choose>
						<c:when test="${page.currentPage == 1 }">
							<li class="active"><a href="javascript:void();" title="首页">首页</a></li>          
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getContextPath()%>/background/userlist.html?currentPage=1&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_status=${s_status}" title="首页">首页</a></li>
						</c:otherwise>
					</c:choose>
					<c:if test="${page.prevPages != null}">
						<c:forEach items="${page.prevPages }" var="num">
							<li><a href="<%=request.getContextPath()%>/background/userlist.html?currentPage=${num }&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_status=${s_status}" title="${num }">${num }</a></li>
						</c:forEach>
					</c:if>
					<li class="active"><a href="javascript:void();" title="${page.currentPage }">${page.currentPage }</a></li>
					<c:if test="${page.nextPages != null}">
						<c:forEach items="${page.nextPages }" var="num">
							<li><a href="<%=request.getContextPath()%>/background/userlist.html?currentPage=${num }&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_status=${s_status}" title="${num }">${num }</a></li>
						</c:forEach>
					</c:if>
					<c:if test="${page.pageCount != null }">
						<c:choose>
							<c:when test="${page.currentPage == page.pageCount }">
								<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>          
							</c:when>
							<c:otherwise>
								<li><a href="<%=request.getContextPath()%>/background/userlist.html?currentPage=${page.pageCount }&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_status=${s_status}" title="尾页">尾页</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.pageCount == null}">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
					</c:if>
				</ul>	
			</div>
		</div>
		
	</div>
	<!-- addUser start -->
	<div class="modal fade" id="adduserdiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <form action="<%=request.getContextPath() %>/background/adduser.html" method="get" enctype="multipart/form-data" onsubmit="return addUser();">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>添加用户信息</h3>
                </div>
                <div class="modal-body">
                    <ul id="add_prompt"></ul>
					<ul class="">
						<li>
							<label>角色:</label>
							<input id="selectrolename" type="hidden" name="roleName" value="">
							<select id="selectrole" name="roleId" style="width:100px;">
								<option value="-1" selected="selected">--请选择--</option>
								<c:if test="${roleList != null }">
									<c:forEach items="${roleList }" var="role">
										<option value="${role.id }">${role.roleName }</option>	
									</c:forEach>
								</c:if>
							</select>
							<span style="color:red;font-weight:bold;">*</span>
						</li>
						<li>
							<label>会员类型:</label>
							<input id="selectusertypename" type="hidden" name="userTypeName" value="">
							<select id="selectusertype" name="userType" style="width:100px;">
								<option value="-1" selected="selected">--请选择--</option>
								<!-- 使用 ajax 动态填充 -->
							</select>
							<span style="color:red;font-weight:bold;">*</span>
						</li>
						<li>
							<label>用户名:</label>
							<input id="a_logincode" type="text" name="loginCode" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
								<!-- 用户名 ajax判断是否已存在 -->
							<span style="color:red;font-weight:bold;">*</span>
						</li>
						<li>
							<label>姓名:</label>
							<input id="a_username" type="text" name="userName" />
							<span style="color:red;font-weight:bold;">*</span>
						</li>
						<li>
							<label>证件类型:</label>
							<input id="selectcardtypename" type="hidden" name="cardTypeName" value="">
							<select id="selectcardtype" name="cardType" style="width:100px;">
								<option value="-1" selected="selected">--请选择--</option>
								<c:if test="${cardTypeList != null }">
									<c:forEach items="${cardTypeList }" var="cardType">
										<option value="${cardType.valueId }">${cardType.valueName }</option>
									</c:forEach>
								</c:if>
							</select>
							<span style="color:red;font-weight:bold;">*</span>
						</li>
						<li>
							<label>生日:</label>
							<input type="text" class="Wdate" id="a_birthday" size="15" name="birthday" readonly="readonly" onclick="WdatePicker();"/>
						</li>
						<li>
							<label>推荐人:</label>
							<input type="text" name="referCode" value="${currentUser.userName}" readonly="readonly" />
						</li>
						<li>
							<label>邮箱:</label>
							<input type="text" id="a_email" name="email" />
						</li>
					</ul>
					<div class="clear"></div>
					<ul class="">
						<li>
							<label>上传身份证图片:</label>
						</li>
					</ul>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default addusercancel" data-dismiss="modal">取消</a>
                    <button type="submit" class="btn btn-primary" data-dismiss="modal">保存</button>
                </div>
            </form>    
            </div>
        </div>      
    </div>
	<!-- addUser end -->
		
<script type="text/javascript" src="/SL/statics/local/js/userlist.js"></script>


<%@ include file="../common/foot.jsp" %>