$(document).ready(function () {
    $("#modalCreate").on('hidden.bs.modal', () => {
        $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide");
        $("#frmCreateTable").validate().resetForm();
    })
    $("#frmCreateTable").validate({
        rules: {
            statusTable: {
                required: true,
                minValue: 1,
                maxValue: 3
            },
            capacityTable: {
                required: true,
                minValue: 4,
                maxValue: 8
            },
            messages: {
                statusTable: {
                    required: " Trạng thái bàn không được để trống",
                    // minValue: "Bàn chỉ có 3 trạng thái",
                    // maxlength: "Tên đăng nhập phải tối đa có 25 ký tự"
                },
                password: {
                    required: "Sức chứa của bàn không được để trống",

                }
            },
            // errorLabelContainer: "#modalCreate .modal-alert-danger",
            // errorPlacement: function (error, element) {
            //     error.appendTo("#modalCreate .modal-alert-danger");
            // },
            showErrors: function (errorMap, errorList) {
                if (this.numberOfInvalids() > 0) {
                    $("#modalCreateTable .modal-alert-danger").removeClass("hide").addClass("show");
                } else {
                    $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide").empty();
                    $("#frmCreateTable input.error").removeClass("error");
                }
                this.defaultShowErrors();
            },
            submitHandler: function () {
                CreateTable();
            }
        }
    })
})