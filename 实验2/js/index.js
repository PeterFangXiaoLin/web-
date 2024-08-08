document.getElementById('userId').addEventListener('blur', function () {
    let len = this.value.length;
    let error = document.getElementById('e_userId');

    if (len < 6 || len > 8) {
        error.innerHTML = "6-8位";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('username').addEventListener('blur', function () {
    let len = this.value.length;
    let error = document.getElementById('e_username');

    if (len < 2 || len > 10) {
        error.innerHTML = "2-10位";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('word').addEventListener('blur', function () {
    let len = this.value.length;
    let error = document.getElementById('e_word');
    let userId = document.getElementById('userId').value;

    if (len < 6 || len > 8 || userId === this.value) {
        error.innerHTML = "6-8位，不能与用户ID相同";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('confirm').addEventListener('blur', function () {
    let error = document.getElementById('e_confirm');
    let userId = document.getElementById('word').value;

    if (userId !== this.value) {
        error.innerHTML = "与口令相同";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('birthday').addEventListener('blur', function () {
    let error = document.getElementById('e_birthday');
    const dateRegEx = /^\d{4}-\d{2}-\d{2}$/;

    if (!dateRegEx.test(this.value)) {
        error.innerHTML = "格式为：yyyy-mm-dd";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('email').addEventListener('blur', function () {
    let error = document.getElementById('e_email');
    const value = this.value;

    if (!value.includes('@')) {
        error.innerHTML = "邮箱格式错误";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});

document.getElementById('phone').addEventListener('blur', function () {
    let error = document.getElementById('e_phone');
    const phoneReg = /^\d+-\d+$/;
    const value = this.value;

    if (!phoneReg.test(value)) {
        error.innerHTML = "数字和连字符，例如123456-123";
    } else {
        error.innerHTML = "<img width='25' height='25' src='../pic/勾.png'/>";
    }
});