<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %> <!-- 외부파일 삽입용 -->
    
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판 게시글</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                           
                            <div class="form-group">
                           		<label >Bno</label><input class="form-control" name="bno" value='<c:out value = "${board.bno }"/>' readonly="readonly"/>       
                           	</div>

                           	<div class="form-group">
                           		<label >Title</label><input class="form-control" name="title"  value='<c:out value = "${board.title }"/>' readonly="readonly"/>
                           	</div>
                           	<div class="form-group">
                           		<label >Text Area</label><textarea class="form-control" rows="3" name="content"  readonly="readonly" ><c:out value = "${board.content }"/></textarea>
                           	</div>
                           	 <div class="form-group">
                           		<label >Writer</label><input class="form-control" name="writer" value="${board.writer }" readonly="readonly"/>
                           	</div>
                           	<button data-oper='modify' class="btn btn-default" >수정</button>
                           	<button data-oper='list' class="btn btn-info">글목록</button>
                           	
                           	<form id="operForm" action="/board/modify" method="get">
                           		<input type="hidden" id="bno" name="bno" value ='<c:out value="${board.bno }" />' />
                           		<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum }'/>"/>
                           		<input type="hidden" name="amount" value="<c:out value='${cri.amount }'/>"/>
                           		<input type="hidden" name="keyword" value="<c:out value='${cri.keyword }'/>"/>
                           		<input type="hidden" name="type" value="<c:out value='${cri.type }'/>"/>
   		
                           	</form> <!-- 링크를 직접 처리해도되지만 폼을 이용해서 처리하는 방법 (히든용) -->
                           	
                         </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
        </div>
        
        <script type="text/javascript">
        	
        	$(document).ready(function() {
				
        		var operForm = $("#operForm"); //id가 operForm인 요소를 가져옴
        		
        		$("button[data-oper='modify']").on("click" , function(e) {

        			e.preventDefault();  //기본 이벤트 막음
        			operForm.attr("action","/board/modify").submit();
        			/* 수정 버튼을 누르면 bno 값을 hidden태그로 전함 */	
				});
        		
        		$("button[data-oper='list']").on("click" , function(e) {
					
        			e.preventDefault();  //기본 이벤트 막음
        			operForm.find("#bno").remove(); /*히든태그 지움*/
        			operForm.attr("action" , "/board/list");
        			operForm.submit();
        			
				});	
			});
   
        </script>
        

 <%@ include file="../includes/footer.jsp" %> <!-- 외부파일 연결 -->       
