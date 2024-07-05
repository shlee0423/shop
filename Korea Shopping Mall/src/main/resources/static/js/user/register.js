const [registerBtn, cancelBtn] = document.getElementById("login-btn-section").querySelectorAll('button');

const emailHidden = document.getElementById("email-input-div").querySelector('input');
const emailHead = document.getElementById("email-input-addr").querySelector('input');
const emailBody = document.getElementById("email-input-domain-manual").querySelector('input');
const emailChoose = document.getElementById("email-input-domain-auto").querySelector('select');

// const phoneHidden = document.getElementById("phone-input-div").querySelector('input');
// const phoneHead = document.getElementById("phone-input-head").querySelector('select');
// const phoneMiddle = document.getElementById("phone-input-middle").querySelector('input');
// const phoneTail = document.getElementById("phone-input-tail").querySelector('input');

const impUidInput = document.getElementById('imp-uid');
const certificationBtn = document.getElementById("certification-btn");

emailChoose.onchange = () => {
    if(emailChoose.value === "직접입력"){
        emailBody.readOnly = false;
    }else{
        emailBody.readOnly = true;
        emailBody.value = emailChoose.value;
    }
}

registerBtn.onclick = () =>{
    emailHidden.value = emailHead.value + '@' + emailBody.value;
    // phoneHidden.value = phoneHead.value + '-' + phoneMiddle.value + '-' + phoneTail.value;

    document.forms.item(0).submit();
}
cancelBtn.onclick = () => {
    location.href = '/user/login'
}

// POST ONE 사이트
IMP.init("imp33162554"); // 예: 'imp00000000'
// 본인인증 요청
certificationBtn.onclick = () => {
    // IMP.certification(param, callback) 호출
    IMP.certification(
        {
            // param
            pg: "inicis_unified.MIIiasTest", //본인인증 설정이 2개이상 되어 있는 경우 필수
        },
        function (response) {
            // callback
            console.log(response);
            if (response.success) {
                // 인증 성공 시 로직
                impUidInput.value = response["imp_uid"];
                certificationBtn.textContent = "본인인증완료";
                certificationBtn.disabled = true;
            } else {
                // 인증 실패 시 로직
                alert('본인인증이 완료되지 못했습니다');
            }
        },
    );
}