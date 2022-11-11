$("#submitRes").on('click', () => {
    registerUser();
})
$("#submitLogin").on('click', () => {
    login();
})

function registerUser() {
    let rePass = $("#RePasswordCre").val();
    let user = {
        userName: $("#userNameCre").val(),
        fullName: $("#fullNameCre").val(),
        password: $("#passwordCre").val(),
        phone: $("#phoneCre").val(),
        email: $("#emailCre").val()
    };
    $.ajax({
        url: `${location.origin}/api/users/create`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(user)
    })
        .done((data) => {
            alert("dang ky thanh cong")
            window.location.href = "/login"
        })
}

function login() {
    let userLogin = {
        userName: $("#userNameLogin").val(),
        password: $("#passwordLogin").val()
    };
    $.ajax({
        url: `${location.origin}/api/users/login`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(userLogin)
    })
        .done((data) => {
            iziToast.success(
                        {
                            timeout: 2000,
                            position: 'topRight',
                            title: 'OK',
                            message: "Đăng nhập thành công !"
                        });
            setTimeout(()=>{
                window.location.href = "/orders"
            },1000)
        })
        .fail((jqXHR) => {
            iziToast.error(
                {
                    timeout: 2000,
                    position: 'topRight',
                    title: 'Lỗi',
                    message: 'Tài khoản không đúng, xin nhập lại.'
                });
        })
}



