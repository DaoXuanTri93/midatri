$(document).ready(function () {
    $("#modalCreateTable").on('hidden.bs.modal', () => {
        $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide");
        $("#frmCreateTable").validate().resetForm();
    })
    $("#frmCreateTable").validate({
        rules: {
            capacityTable: {
                required: true,
                range: [2,16],
            },
            statusTable: {
                required: true,
            },
            titleTable: {
                required: true
            }
        }, messages: {
            capacityTable: {
                required: " Số ghế không được để trống",
                range: "Số ghế 1 bàn không được nhỏ hơn 1 và lớn hơn 16",
            },
            statusTable: {
                required: " Bạn phải lựa chọn trạng thái của bàn",
            },
            titleTable: {
                required: "Phải có tên cho bàn"
            }

        },
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
    })

    // validate edit table
    $("#modalEditTable").on('hidden.bs.modal', () => {
        $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide");
        $("#frmEditTable").validate().resetForm();
    })
    $("#frmEditTable").validate({
        rules: {
            titleEdit: {
                required: true,
            },
            capacityEdit: {
                required: true,
                range: [5,16],
                // min: 2
            }
        },
        messages: {
            titleEdit: {
                required: "Bạn phải đặt tên cho bàn"
            },
            capacityEdit: {
                required: " Số ghế không được để trống",
                range: " Số ghế 1 bàn không được nhỏ hơn 1 và lớn hơn 16",
                // min: "Số ghế phải lớn hơn 1"
            }

        },
        showErrors: function (errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalEditTable .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#frmEditTable input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        submitHandler: function () {
            updateTable();
        }

    })
})