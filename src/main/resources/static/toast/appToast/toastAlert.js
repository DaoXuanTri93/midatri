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
    static warning(message) {
        iziToast.warning({
            title: 'Caution',
            position: 'topRight',
            message: message,
            timeout : 1500
        });
    }
}
