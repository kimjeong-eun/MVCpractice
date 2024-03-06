/**
 * 
 */
 
 console.log("Reply Module");
 
 var replyService= (function(){ //즉시 실행함수 (익명함수)
	
  function add(reply , callback){ //자바스크립트 객체와 콜백 함수를 넘김
	
	console.log("reply.....")
	
	$.ajax({
		
		type : 'post', //post방식으로 
		url	 : '/replies/new', // 컨트롤러로 보냄
		data : JSON.stringify(reply), //JSON.stringify( )는 자바스크립트의 값을 JSON 문자열로 변환한다.
		contentType :"application/json; charset=utf-8",
		success : function(result,status,xhr){
			
					if(callback){ //콜백함수가 있다면 result 삽입
						
						callback(result); //result는 ajax의 결과 
					}
			
				},
		error : function(xhr,status,er){
					if(error){
						error(er);
					}
				}
			
		})	
	}
	
	return { add:add };
})();