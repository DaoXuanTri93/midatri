  class Toast {
    static success(message) {
     return iziToast.success({
             title: 'OK',
             message: message
         });
    }
}