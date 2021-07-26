// 2.js
/* 동적인 부분(DB연동 없는)
            * => 자바스크립트(함수형언어,인터프리터언어)를 사용 */
name = prompt("이름은 ?", "홍길동");
document.write(name + '~ Welcome <br>'); /* <br> 개행 */
function chk() {
    if (frm.tel.value.length < 4) {
        alert("전화번호는 4자리 이상 입력해야 해");
        return false; /* submit 이벤트가 제한 */
    }
}
