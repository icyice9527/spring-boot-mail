function check() {
    var email=document.getElementById("email").value;
    var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

    if(!reg.test(email)){
        alert("收件箱格式错误，请重新填写！");
        return false;
    }
    return true;
}

