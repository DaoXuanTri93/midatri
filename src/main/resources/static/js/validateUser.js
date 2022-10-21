$(document).ready(function () {
    $("#modalCreate").on('hidden.bs.modal', () => {
        $("#mdCreate .modal-alert-danger").removeClass("show").addClass("hide");
        $("#frmCreateUser").validate().resetForm();
    })

    $("#frmCreateUser").validate({
        rules: {
            userName: {
                required: true,
                minlength: 5,
                maxlength: 25
            },
            password: {
                required: true,
                minlength: 8,
                maxlength: 30
            },
            password1: {
                required: true,
                minlength: 8,
                maxlength: 30,
                equalTo: "#password"
            },
            email : {
                required : true,
                minlength : 8,
                maxlength: 30,
            },
            phone :{
                required : true,
                minlength: 10,
                maxlength : 10
            }

        },
        messages: {
            userName: {
                required: " Tên đăng nhập không được để trống",
                minlength: "Tên đăng nhập phải có ít nhất 5 ký tự",
                maxlength: "Tên đăng nhập phải tối đa có 25 ký tự"
            },
            password: {
                required : "Mật khẩu không được để trống",
                minlength : "Mật khẩu phải có ít nhất 8 ký tự",
                maxlength : "Mật khẩu quá dài,tối đa 30 ký tự",
            },
            password1: {
                required : "Mật khẩu không được để trống",
                minlength : "Mật khẩu phải có ít nhất 8 ký tự",
                maxlength : "Mật khẩu quá dài,tối đa 30 ký tự",
                equalTo: "Mật khẩu không giống nhau"
            },
              email: {
                required : "Email không được để trống ",
                  minlength: "Độ dài email tối thiểu là 5",
                  maxlength: "ĐỘ dài email tối đa là 30"
              },
            phone: {
                required: "Số điện thoại không được để trống",
                minlength : "Số điện thoại phải đủ 10 số",
                maxlength : "Số điện thoại phải đủ 10 số"
            }

        },
        // errorLabelContainer: "#modalCreate .modal-alert-danger",
        // errorPlacement: function (error, element) {
        //     error.appendTo("#modalCreate .modal-alert-danger");
        // },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreate .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreate .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#frmCreateUser input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            createUser();
        }
    })

})