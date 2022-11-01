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
