    $(document).ready(function () {
    $("#modalCreateTable").on('hidden.bs.modal', () => {
        $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide");
        $("#frmCreateTable").validate().resetForm();
    })
    $("#frmCreateTable").validate({
        rules: {
            capacityTable: {
                required: true,
                minlength : 1,
                maxlength : 20
            },
            statusTable : {
                required : true,
            },
            titleTable : {
                required : true
            },
            messages: {
                capacityTable: {
                    required: " Số ghế không được để trống",
                    minlength: "Số ghế 1 bàn không được nhỏ hơn 1",
                    maxlength: "Số ghế 1 bàn không thể vượt quá 20"
                },
                statusTable: {
                    required : " Bạn phải lựa chọn trạng thái của bàn",
                },
                titleTable: {
                    required : "Phải có tên cho bàn"
                }

            },
            // errorLabelContainer: '#modalCreateTable .modal-body .modal-alert-danger',
            // errorPlacement: function (error, element) {
            //     error.appendTo("#modalCreateTable .modal-body .modal-alert-danger");
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

    // validate edit table
    // $("#modalEditTable").on('hidden.bs.modal', () => {
    //     $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide");
    //     $("#frmEditTable").validate().resetForm();
    // })
    // $("#frmEditTable").validate({
    //     rules: {
    //         capacityTable: {
    //             required: true,
    //         },
    //         messages: {
    //             capacityTable: {
    //                 required: " Sức chứa bàn không được để trống",
    //             },
    //
    //         },
    //         showErrors: function (errorMap, errorList) {
    //             if (this.numberOfInvalids() > 0) {
    //                 $("#modalEditTable .modal-alert-danger").removeClass("hide").addClass("show");
    //             } else {
    //                 $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide").empty();
    //                 $("#frmEditTable input.error").removeClass("error");
    //             }
    //             this.defaultShowErrors();
    //         },
    //         submitHandler: function () {
    //             editTable();
    //         }
    //     }
    // })
})