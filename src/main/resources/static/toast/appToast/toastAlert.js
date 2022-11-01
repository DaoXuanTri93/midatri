<<<<<<< HEAD
  class Toast {
    static success(message) {
     return iziToast.success({
             title: 'OK',
             message: message
         });
    }
}
=======
class Toast{
    static success(message){
        iziToast.success(
            {
                timeout: 1500,
                position: 'topRight',
                title: 'OK',
                message: message
            });
    };
}
>>>>>>> f5116a793fc17ed78a6224827e1d515de48a948f
