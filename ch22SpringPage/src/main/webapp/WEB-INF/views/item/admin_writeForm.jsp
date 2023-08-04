<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 상품 등록 시작 -->
<!-- include libraries (jquery, bootstrap) : CKEditor 사용을 위해 필요 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include CKEditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shop.item-form.js"></script>
<div id="item_form" class="page-main">
	<h2>상품 등록</h2>
	<form:form modelAttribute="itemVO" action="admin_write.do" id="item_register" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label>상품표시여부</label>
				<form:radiobutton path="status" value="1" id="status1"/>미표시
				<form:radiobutton path="status" value="2" id="status2"/>표시
			</li>
			<li>
				<form:label path="name">상품명</form:label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="price">가격</form:label>
				<form:input path="price" type="number"/>
				<form:errors path="price" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="quantity">수량</form:label>
				<form:input path="quantity" type="number"/>
				<form:errors path="quantity" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1">상품사진1</label>
				<input type="file" name="upload1" id="upload1" accept="image/gif,image/png,image/jpeg">
				<form:errors path="photo1" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload2">상품사진2</label>
				<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
				<form:errors path="photo2" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="delivery_fee">배송비</form:label>
				<form:input path="delivery_fee" type="number"/>
				<form:errors path="delivery_fee" cssClass="error-color"/>
			</li>
			<li style="display:none;" class="deli-limit">
				<form:label path="delivery_limit">배송비 면제 금액</form:label>
				<input type="radio" name="delivery_radio" id="delivery_radio1" value="0">배송비 면제 없음
				<input type="radio" name="delivery_radio" id="delivery_radio2" value="1">배송비 면제 있음
				<form:hidden path="delivery_limit"/>
				<form:errors path="delivery_limit" cssClass="error-color"/>
			</li>
			<li><b>상품설명</b></li>
			<li>
				<form:textarea path="detail"/>
				<form:errors path="detail" cssClass="error-color"/>
				<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#detail'),{
									extraPlugins:[MyCustomUploadAdapterPlugin]
								 })
								 .then(editor => {
									 window.editor = editor;
								 })
								 .catch(error => {
									 console.error(error);
								 });
				</script>
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록" onclick="location.href='admin_list.do'">
		</div>
	</form:form>
</div>
<!-- 상품 등록 끝 -->