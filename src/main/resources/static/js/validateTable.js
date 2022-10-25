// $(document).ready(function () {
//     $("#modalCreate").on('hidden.bs.modal', () => {
//         $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide");
//         $("#frmCreateTable").validate().resetForm();
//     })
//     $("#frmCreateTable").validate({
//         rules: {
//             capacityTable: {
//                 required: true,
//             },
//             messages: {
//                 capacityTable: {
//                     required: " Sức chứa bàn không được để trống",
//
//
//                 },
//
//             },
//             // errorLabelContainer: "#modalCreate .modal-alert-danger",
//             // errorPlacement: function (error, element) {
//             //     error.appendTo("#modalCreate .modal-alert-danger");
//             // },
//             showErrors: function (errorMap, errorList) {
//                 if (this.numberOfInvalids() > 0) {
//                     $("#modalCreateTable .modal-alert-danger").removeClass("hide").addClass("show");
//                 } else {
//                     $("#modalCreateTable .modal-alert-danger").removeClass("show").addClass("hide").empty();
//                     $("#frmCreateTable input.error").removeClass("error");
//                 }
//                 this.defaultShowErrors();
//             },
//             submitHandler: function () {
//                 CreateTable();
//             }
//         }
//     })
//
//     // validate edit table
//     $("#modalEditTable").on('hidden.bs.modal', () => {
//         $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide");
//         $("#frmEditTable").validate().resetForm();
//     })
//     $("#frmEditTable").validate({
//         rules: {
//             capacityTable: {
//                 required: true,
//             },
//             messages: {
//                 capacityTable: {
//                     required: " Sức chứa bàn không được để trống",
//                 },
//
//             },
//             showErrors: function (errorMap, errorList) {
//                 if (this.numberOfInvalids() > 0) {
//                     $("#modalEditTable .modal-alert-danger").removeClass("hide").addClass("show");
//                 } else {
//                     $("#modalEditTable .modal-alert-danger").removeClass("show").addClass("hide").empty();
//                     $("#frmEditTable input.error").removeClass("error");
//                 }
//                 this.defaultShowErrors();
//             },
//             submitHandler: function () {
//                 editTable();
//             }
//         }
//     })
// })