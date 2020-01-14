<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 用户 -->
<div id="user">
	<div>
		<form class="form-inline">
			<div class="form-group">
				<select name="searchroleid" id="" class="form-control">
					<option value="">请选择用户角色类型</option>
					<option value="1">管理员</option>
					<option value="2">高管</option>
					<option value="3">部门主管</option>
					<option value="4">客户经理</option>
				</select>
			</div>
			<input class="btn btn-default search" type="button" value="查询">
			<input class="btn btn-default" type="reset" value="重置"> <input
				class="btn btn-default" type="button" value="新增" id="add">

		</form>
	</div>

	<div>
		<table class="table table-hover">
			<thead>
				<tr style="background: #E8E8E8;">
					<th>序号</th>
					<th>用户名</th>
					<th>角色名</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userInfo.list}" var="user">
					<tr>
						<td>${user.usrId}</td>
						<td>${user.usrName}</td>
						<c:if test="${user.usrRoleId==1}">
							<td>管理员</td>
						</c:if>
						<c:if test="${user.usrRoleId==2}">
							<td>高管</td>
						</c:if>
						<c:if test="${user.usrRoleId==3}">
							<td>部门主管</td>
						</c:if>
						<c:if test="${user.usrRoleId==4}">
							<td>客户经理</td>
						</c:if>
						<c:if test="${user.usrFlag==0}">
							<td>注销</td>
						</c:if>
						<c:if test="${user.usrFlag==1}">
							<td>正常</td>
						</c:if>
						<td><i value="${user.usrId}"
							class="layui-icon layui-icon-edit editUser"></i> <i
							value="${user.usrId}"
							class="layui-icon layui-icon-delete deleteUser"></i></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="fenye">
		<table class="footTable">
			<tr>
				<td colspan="5">共${userInfo.total } 条记录
					每页${userInfo.size } 条 第${userInfo.pageNum } 页 <a
					href="javascript:void(0)" onclick="pageSelect(1)">首页</a> <c:choose>
						<c:when test="${userInfo.hasPreviousPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${userInfo.prePage})">上一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">上一页</font>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${userInfo.hasNextPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${userInfo.nextPage})">下一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">下一页</font>
						</c:otherwise>
					</c:choose> <a href="javascript:void(0)"
					onclick="pageSelect(${userInfo.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
	</div>
	<!-- 删除模态框 -->
	<div class="modal" id="deleteModal">
		<div class="modal-dialog" style="width: 350px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">是否确定删除</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default " data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增模态框 -->
	<div class="modal" id="toAdd">
		<div class="modal-dialog" style="height: 400px; overflow: auto;">
			<div class="modal-content">
				<form>
					<div class="modal-body">
						<div class="form-group">
							<label>用户名:</label> <input type="text" name="name"
								class="form-control">
						</div>
						<div class="form-group">
							<label>密码:</label> <input type="password" name="password"
								class="form-control">
						</div>
						<div class="form-group">
							<label>角色:</label> <select name="roleid" id=""
								class="form-control">
								<option value="">请选择用户角色类型</option>
								<option value="1">管理员</option>
								<option value="2">高管</option>
								<option value="3">部门主管</option>
								<option value="4">客户经理</option>
							</select>
						</div>
						<div class="form-group">
							<label>状态:</label> <select name="flag" id=""
								class="form-control">
								<option value="1">正常</option>
								<option value="0">注销</option>
							</select>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button class="btn" style="background: #ccc">取消</button>
					<button class="btn" style="background: #ccc">保存</button>
				</div>
			</div>
		</div>
	</div>

</div>
<style>
#user {
	width: 1140px;
	overflow: hidden;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	background-color: #ccc;
	border-color: #ccc;
}

.pagination>li>a, .pagination>li>span {
	color: black;
}

.fenye {
	margin-left: 400px;
}

.form-inline {
	margin-top: 15px;
	margin-left: 10px;
	margin-bottom: 20px;
}

.search {
	margin-left: 50px;
}

#add {
	margin-left: 680px;
}

.table th, td {
	text-align: center;
}

#deleteModal {
	margin-top: 100px;
}

#toAdd {
	margin-top: 50px;
}
</style>
<script type="text/javascript">
	function pageSelect(curPage){
		$('.layui-body').load('manage/toUser/'+curPage)
	}
</script>
<script type="text/javascript">
	$(function() {
		var usrId;
		// 显示删除模态框
		$('.deleteUser').click(function() {
			usrId = $(this).attr("value");
			$('#deleteModal').show();
		})
		// 关闭删除模态框
		$('.close').click(function() {
			$('#deleteModal').hide();
		})
		// 关闭删除模态框
		$('.btn-default').click(function() {
			$('#deleteModal').hide();
		})
		// 确定删除
		$('.btn-primary').click(function() {
			var url = "manage/deleteUserById/" + usrId;
			$.get(url,function(data){
				alert(data);
				$(".layui-nav-item dd:contains('用户管理')").trigger('click');
			});
			$('#deleteModal').hide();
		})
		// 点击新增显示模态框
		$('#add').click(function() {
			$("input[name=name]").val("");
			$("input[name=password]").val("");
			$("select[name=roleid]").val("");
			$("input[name=flag]").val("");
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click', function() {
			$('button[type=reset]').trigger('click');
			$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click', function() {
			var usrName = $("input[name=name]").val();
			var usrPassword = $("input[name=password]").val();
			var usrRoleId = $("select[name=roleid] option:selected").val();
			var usrFlag = $("select[name=flag] option:selected").val();
			var url = "manage/saveOrUpdateUser";
			var data = {
					usrId:usrId,
					usrName:usrName,usrPassword:usrPassword,usrRoleId:usrRoleId,usrFlag:usrFlag
			};
			$.post(url,data,function(data){
				alert(data);
				$(".layui-nav-item dd:contains('用户管理')").trigger('click');
			});
			$('#toAdd').hide();
		})
		// 点击编辑显示模态框
		$('.editUser').click(function() {
			usrId = $(this).attr("value");
			var url = "manage/findUserById/" + usrId;
			$.get(url,function(data){
				$("input[name=name]").val(data.usrName);
				$("input[name=password]").val(data.usrPassword);
				$("select[name=roleid]").val(data.usrRoleId);
				$("input[name=flag]").val(data.usrFlag);
			});
			$('#toAdd').show();
		})
		// 查询
		$('.search').click(function() {
			var usrRoleId = $("select[name=searchroleid] option:selected").val();
			var url = "manage/findUserByRoleId/" + usrRoleId +"/1";
			var data = {
					usrRoleId:usrRoleId
			};
			$(".layui-body").load(url,data);
		})
	})
</script>