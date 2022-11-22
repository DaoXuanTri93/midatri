$("#submitRes").on('click', () => {
    registerUser();
})
$("#submitLogin").on('click', () => {
    login();
})

window.localStorage.setItem("userLogin", undefined);

let userLogin;
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
        .done((user) => {
            alert("Đăng ký thành công")
            window.location.href = "/login"
        })
}

function login() {
    let userLogin = {
        userName: $("#userNameLogin").val(),
        password: $("#passwordLogin").val()
    };
    $.ajax({
        url: `${location.origin}/api/users/loginUser`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(userLogin)
    })
        .done((data) => {
            setLoginUserToLocalStored(data);
            iziToast.success(
                        {
                            timeout: 2000,
                            position: 'topRight',
                            title: 'OK',
                            message: "Đăng nhập thành công !"
                        });
            window.location.href = "/orders"
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

function setLoginUserToLocalStored(loginUser) {
    window.localStorage.setItem("userLogin", JSON.stringify(loginUser));

}


function notLoginDashboard(){
    let userLogin = {
        userName: "admin2",
        password: "123456789"
    };
    $.ajax({
        url: `${location.origin}/api/users/loginUser`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(userLogin)
    })
        .done((data) => {
            setLoginUserToLocalStored(data);
            iziToast.success(
                {
                    timeout: 2000,
                    position: 'topRight',
                    title: 'OK',
                    message: "Đăng nhập thành công !"
                });
            window.location.href = "/dashboard"
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
function notLoginOrder(){
    let userLogin = {
        userName: "admin2",
        password: "123456789"
    };
    $.ajax({
        url: `${location.origin}/api/users/loginUser`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(userLogin)
    })
        .done((data) => {
            setLoginUserToLocalStored(data);
            iziToast.success(
                {
                    timeout: 2000,
                    position: 'topRight',
                    title: 'OK',
                    message: "Đăng nhập thành công !"
                });
            window.location.href = "/orders"
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

