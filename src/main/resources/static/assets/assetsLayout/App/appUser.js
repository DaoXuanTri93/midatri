class User{
    constructor(id,admin,bartender,deleted,email,phone,username,vendor ) {
        this.id = id;
        this.admin = admin;
        this.bartender = bartender;
        // this.password = password;
        this.deleted = deleted;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.vendor = vendor;
        // this.id_staff = id_staff;
    }
}

function handlerActionUser() {
    $("button.editUser").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/users/" + id
        })
            .done(function (data) {
                $("#idEdit").val(data.id);
                $("#usernameEdit").val(data.username);
                $("#passEdit").val(data.password);
                $("#staffEdit").val(data.staff.name);
                $("#modalEdit").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });

    $("button.deleteUser").on("click", function () {
        let id = $(this).data("id");
        $.ajax({
            type: "GET",
            url: "/api/users/" + id
        })
            .done(function (data) {
                $("#idDelete").val(data.id);
                $("#usernameDelete").val(data.username);
                $("#passDelete").val(data.password);
                $("#staffDelete").val(data.staff.name);
                $("#modalDelete").modal("show");
            })
            .fail(function (resp) {
                window.location = "/errors";
            })
    });


}