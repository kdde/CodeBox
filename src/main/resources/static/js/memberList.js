/**
 * ready function
 */
function onclickDepartment(el){
	var dno = $(el).val();//부서번호 가져오기
	$.ajax({
		url:`/member/departmentMemberList/${dno}`,//링크 생성
		success:function(result){
			$("departmentMemberList").html(result);//리스트를 뛰어줄 위치
		},
		error:function(){
			alert("비정상 작동");
		}
	});
}
