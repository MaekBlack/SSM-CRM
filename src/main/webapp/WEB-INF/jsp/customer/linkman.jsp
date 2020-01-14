<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div id="linkman">
	<div>
		<form class="form-inline">
			<div class="form-group" style="padding-left: 10px;">
				<label style="font-size: 18px;">联系人信息:</label>
			</div>
			<input class="btn btn-default" type="button" value="新增" id="add">

		</form>
	</div>

	<div>
		<table class="table table-hover">
			<thead>
				<tr style="background: #E8E8E8;">
					<th>姓名</th>
					<th>性别</th>
					<th>职位</th>
					<th>办公室电话</th>
					<th>手机</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${linkmaninfo.list}" var="linkman">
					<tr>
						<td>${linkman.lkmName}</td>
						<td>${linkman.lkmSex}</td>
						<td>${linkman.lkmPostion}</td>
						<td>${linkman.lkmTel}</td>
						<td>${linkman.lkmMobile}</td>
						<td>${linkman.lkmMemo}</td>
						<td><i id="${linkman.lkmId}"
							class="layui-icon layui-icon-edit editUser" title="编辑"></i> <i
							id="${linkman.lkmId}"
							class="layui-icon layui-icon-delete deleteUser" title="删除"></i></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="fenye">
		<table class="footTable">
			<tr>
				<td colspan="5">共${linkmaninfo.total } 条记录 每页${linkmaninfo.size }
					条 第${linkmaninfo.pageNum } 页 <a href="javascript:void(0)"
					onclick="pageSelect(1)">首页</a> <c:choose>
						<c:when test="${linkmaninfo.hasPreviousPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${linkmaninfo.prePage})">上一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">上一页</font>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${linkmaninfo.hasNextPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${linkmaninfo.nextPage})">下一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">下一页</font>
						</c:otherwise>
					</c:choose> <a href="javascript:void(0)"
					onclick="pageSelect(${linkmaninfo.lastPage })">尾页</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="back">
		<button class="btn" style="background: #ccc">返回</button>
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
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-body">
						<div class="form-group">
							<label>姓名:</label> <input type="text" name="name"
								class="form-control">
						</div>
						<div class="form-group">
							<label>性别:</label> <select name="sex" id="" class="form-control">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						<div class="form-group">
							<label>办公室电话:</label> <input type="text" name="tel"
								class="form-control">
						</div>
						<div class="form-group">
							<label>手机:</label> <input type="text" name="mobile"
								class="form-control">
						</div>
						<div class="form-group">
							<label>备注:</label> <input type="text" name="memo"
								style="height: 60px" class="form-control">
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
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	background-color: #ccc;
	border-color: #ccc;
}

.back {
	float: right;
}

.pagination>li>a, .pagination>li>span {
	color: black;
}

#linkman {
	width: 1140px;
	overflow: hidden;
}

.fenye {
	margin-left: 400px;
}

.form-inline {
	margin-top: 15px;
	margin-left: 10px;
	margin-bottom: 20px;
}

.form-inline {
	margin-top: 15px;
	margin-left: 10px;
	margin-bottom: 20px;
}

input[type=submit] {
	margin-left: 50px;
}

#add {
	margin-left: 1050px;
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
	var custId = ${custId};
	function pageSelect(curPage){
		var url ="linkman/findLinkmanByCustId/" + custId + "/" + curPage;
		$('.layui-body').load(url);
	}
	$(function(){
		var lkmId;
		// 返回
		$('button:contains(返回)').on('click',function(){
			$(".layui-nav-item dd:contains('客户信息查询')").trigger('click');  
		})
		// 显示删除模态框
		$('.deleteUser').click(function(){
			lkmId = $(this).attr("id");
			$('#deleteModal').show();
		})
		// 关闭删除模态框
		$('.close').click(function(){
			lkmId == null;
			$('#deleteModal').hide();
		})
		// 关闭删除模态框
		$('.btn-default').click(function(){
			$('#deleteModal').hide();
		})
		// 确定删除
		$('.btn-primary').click(function(){
			var url;
			url ="linkman/deleteLinkmanById/" + lkmId;
			$.get(url,function(data){
				alert(data);
				url = "linkman/findLinkmanByCustId/" + custId + "/1";
				$('.layui-body').load(url);  
				lkmId == null;
			});
			$('#deleteModal').hide();
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
			var url;
			url = "linkman/saveOrUpdate";
			var lkmName = $("input[name=name]").val();
			var lkmSex = $("select[name=sex] option:selected").val();
			var lkmTel = $("input[name=tel]").val();
			var lkmMobile = $("input[name=mobile]").val();
			var lkmMemo = $("input[name=memo]").val();
			var data = {
					lkmName : lkmName, lkmSex : lkmSex, lkmTel : lkmTel,
					lkmMobile : lkmMobile, lkmMemo : lkmMemo
			};
			$.post(url,data,function(data){
				alert(data);
				url = "linkman/findLinkmanByCustId/" + custId + "/1";
				$('.layui-body').load(url);
				$("input[name=name]").val("");
				$("select[name=sex]").val("");
				$("input[name=tel]").val("");
				$("input[name=mobile]").val("");
				$("input[name=memo]").val("");
			});
			$('#toAdd').hide();
		})	
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			lkmId = $(this).attr("id");
			var url = "linkman/findLinkmanById/" + lkmId;
			$.get(url, function(data){
				$("input[name=name]").val(data.lkmName);
				$("select[name=sex]").val(data.lkmSex);
				$("input[name=tel]").val(data.lkmTel);
				$("input[name=mobile]").val(data.lkmMobile);
				$("input[name=memo]").val(data.lkmMemo);
			});
			$('#toAdd').show();
		})
	})
</script>