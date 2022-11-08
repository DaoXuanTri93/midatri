$("#submitRes").on('click', () => {
    registerUser();
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



