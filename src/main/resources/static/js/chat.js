/**
 * 
 */

$(function(){
	$("#question").keyup(questionKeyuped);
});
function openChat(){
	setConnectStated(true);//챗창보이게처리
	connect();
	//mqConnect();
}

function showMessage(message) {
    $("#chat-content").append(message);
	//대화창 스크롤을 항상 최하위에 배치   
    $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

function setConnectStated(isTrue){
	if(isTrue){//true
		$("#btn-chat-open").hide();
		$("#chat-disp").show();
	}else{
		$("#btn-chat-open").show();
		$("#chat-disp").hide();
	}
	//챗봇창 화면 클리어
	$("#chat-content").html("");
}

function disconnect() {
    setConnectStated(false);
    console.log("Disconnected");
}
function hideconnect() {
	$("#chat-disp").hide();
	$("#btn-chat-open").show();
}
//버튼클릭시 접속
function connect() {
	sendMessage("안녕");
}

function sendMessage(message){
	$.ajax({
		url:"/hbot",
		type:"post",
		data:{message: message},
		success:function(responsedHtml){
			showMessage(responsedHtml);
		}
	});
}

function inputTagString(text){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var time= ampm + now.getHours()%12+":"+now.getMinutes();
	var message=`
		<div class="msg user flex end">
			<div class="message">
				<div class="part">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
	return message;
}
//메뉴클릭시 메뉴 텍스트 화면에 표현 
function menuclicked(el){
	var text=$(el).text().trim();
	var fToken=$(el).siblings(".f-token").val();
	console.log("-----> fToken:"+fToken+"----");
	var message=inputTagString(text);
	showMessage(message);
}

//엔터가 입력이되면 질문을 텍스트 화면에 표현 
function questionKeyuped(event){
	if(event.keyCode!=13)return;
	btnMsgSendClicked()
}

//전송버튼 클릭이되면 질문을 텍스트 화면에 표현
function btnMsgSendClicked(){
	var question=$("#question").val().trim();
	if(question=="" || question.length<2)return;
	//메세지 서버로 전달
	sendMessage(question);
	 
	var message=inputTagString(question);
	showMessage(message);//사용자가 입력한 메세지 채팅창에 출력
	$("#question").val("");//질문 input 리셋
}
