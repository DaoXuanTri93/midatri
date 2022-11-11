export const bookingAPI = new BookingAPI();

function BookingAPI() {
    this.findAll = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/booking`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                console.log(jqXHR)
            })

    }
    this.findAllByStatusNotComplete = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/booking/findAllByStatusNotComplete`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.booking = (data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/booking`,
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data)
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR)
                iziToast.question({
                    close: false,
                    overlay: true,
                    timeout:  false,
                    displayMode: 'once',
                    titleColor: '#d21e1e',
                    backgroundColor: '#fff',
                    id: 'question',
                    titleLineHeight: '20',
                    zindex: 999,
                    title: 'Vui Lòng Chọn Bàn Để Chọn Món.',
                    position: 'center',
                    buttons: [
                        ['<button><b>YES</b></button>', function (instance, toast) {
                            instance.hide({ transitionOut: 'fadeOut' }, toast, 'button');
                        }, true]
                    ]
                });
            })
    }
    this.savingCustomer = (bookingId,data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/booking/savingCustomer/${bookingId}`,
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data)
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR)
            })
    }
}