function join_check() {

	const uPwd = document.getElementById("uPwd").value; // 비밀번호
	const uPwd2 = document.getElementById("uPwd2").value; // 비밀번호 확인
	const uEmail = document.getElementById("uEmail").value; // 이메일(아이디 역할)
	const uName = document.getElementById("uName").value; // 이름
	const uPhone = document.getElementById("uPhone").value; // 전화번호
	const uAgreecheck0 = document.getElementById("uAgreecheck0").value; // 이용약관
	// 동의 X
	const uAgreecheck1 = document.getElementById("uAgreecheck1").value; // 이용약관 동의 O

	let uPwd_passed = true;
	
	const pwd_msg = "";
	const Pass_pattern1 = /[0-9]/;
	const Pass_pattern2 = /[a-zA-z]/;
	const Pass_pattern3 = /[~!@#$%<>^&*]/;
	const Email_pattern = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	const Name_pattern = /^[가-힣]{2-15}$/;
	const Phone_pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/;

	// ================ 유효성 검사1(이메일 : uEmail) ================
	if (uEmail.length == 0) {
		alert("이메일을 입력해주세요");
		return false;
	} else {
		if (!Email_pattern.test(uEmail)) {
			alert("이메일 형식이 올바르지 않습니다");
			form.uEmail.vlaue = "";
			form.uEmail.focus();
			return false;
		}
	}
	// ================ 유효성 검사2(비밀번호 : uPwd) ================
	if (uPwd.length == 0) {
		alert("비민번호를 입력해주세요");
		return false;
	} else {
		if (uPwd != uPwd2) {
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
	}
	if (!Pass_pattern1.test(uPwd) || !Pass_pattern2.test(uPwd)
			|| !Pass_pattern3.test(uPwd) || uPwd.length < 8 || uPwd.length > 50) {
		alert("영문+숫자+특수기호 8자리 이상으로 구성하여야 합니다");
		return false;
	}
	// if(uPwd.indexOf(uEmail) > -1) {
	// alert("비밀번호는 E-mail을 포함할 수 없습니다");
	// return false;
	// }
	if (uEmail == uPad) {
		alert("이메일과 비밀번호는 같을 수 없습니다");
		form.uPwd.value = "";
		form.uPwd2.value = "";
	}
	// ================ 유효성 검사3(이름 : uName) ================
	if (uName.length == 0) {
		alert("이름을 입력해주세요");
		return false;
	}
	if (!Name_pattern.test(uName)) {
		alert("이름은 한글만 입력해주세요");
		return false;
	}
	// ================ 유효성 검사4(이름 : uPhone) ================
	if (uPhone.length == 0) {
		alert("전화번호를 입력해주세요");
		return false;
	}
	if (!Phone_pattern.test(uPhone)) {
		alert("전화번호 형식이 올바르지 않습니다");
		return false;
	}
	// ================ 유효성 검사5(이용약관 : uAgreecheck) ================
	if (document.getElementById("uAgreecheck1").checked) {
		document.getElementById("uAgreecheck0").disabled = true;
	}
	return true;
}

$(document).ready(function() {
	thumbnail();
	certification();

})

function thumbnail(event) {
	$("#user-thumb").change(function() {
		if (this.files && this.files[0]) {
			var reader = new FileReader;
			reader.onload = function(data) {
				$("#thumb-img").attr("src", data.target.result);
			}
			reader.readAsDataURL(this.files[0]);
		}
	})
}

function certification(event) {
	$(".form-control").change(function() {
		if (this.files && this.files[0]) {
			var reader = new FileReader;
			reader.onload = function(data) {
				$("#file-img").attr("src", data.target.result);
			}
			reader.readAsDataURL(this.files[0]);
		}
	})
}


//인증번호 알림창 가림
$("#alert-success-email").hide();
$("#alert-danger-email").hide();
//true일시 전송

//var checkCode = false;

////회원가입 버튼 클릭
//$("#btn-join").on("click", function(e) {
//	e.preventDefault();
//	var id = $("input[name='userid']").val();
//	var name = $("input[name='userName']").val();
//	var pw = $("input[name='userpw']").val();
	
//			console.log("회원가입_아이디: "+id);
//			console.log("회원가입_이름: "+name);
//			console.log("회원가입_비밀번호: "+pw);
	
//			//아이디,비밀번호 유효성 검사
//			if (id == null || id == "") { 
//				alert("아이디를 입력해주세요");
//				return false;
//			} 
//			if (pw == null || pw == "") {
//				alert("비밀번호를 입력해주세요");
//				return false;
//			} 
//			if (name == null || name == "") {
//				alert("이름을 입력해주세요");
//				return false;
//			} 
//			if (checkId == false || id != finalId) { 
//				alert("아이디 중복 체크를 해주세요");
//				return false;
//			} 
//			if(checkPw == false || pw != finalPw) {
//				alert("비밀번호를 정확히 입력하세요");
//				return false;
//			}
//	if(checkCode == false){
//		alert("인증번호를 확인해주세요");
//		return false;
//	}
//	$("form").submit();
//});

//인증번호를 저장할 변수
const code = "";

		//인증번호 이메일 전송
$("#mail_check_button").on("click",function(e){
	e.preventDefault();
	const uEmail = $("input[name='uEmail']").val();
	const checkBox = $(".mail_check_input");
	
	$.ajax({
		type:"GET",
		url : "/mailCheck",
		data : {email : email},
		contentType :"text/plain;charset=UTF-8",
		success : function(data){ //인증번호를 가져옴
			checkBox.attr("disabled",false); //인증번호 입력 가능
			checkBox.val(''); // 기존에 값이 있었으면 지워줌
			$("#alert-success-email").hide();
			$("#alert-danger-email").hide();
			checkCode = false;
			code = data; // 인증번호를 변수에 저장
		}
	});
});

//인증코드 입력 시 동일성 확인
$(".mail_check_input").keyup(function() {
	var inputCode = $(".mail_check_input").val();
	if (inputCode != "" || code != "") {
		if (inputCode == code) {
			$("#alert-success-email").show();
			$("#alert-danger-email").hide();
			$(".mail_check_input").attr("disabled",true); //인증번호 입력 멈춤
			checkCode = true;
		} else {
			$("#alert-success-email").hide();
			$("#alert-danger-email").show();
			checkCode = false;
		}
	}
});