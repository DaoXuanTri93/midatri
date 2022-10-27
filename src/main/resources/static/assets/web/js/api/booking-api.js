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
                Swal.fire({
                    grow : false,
                    position: 'center',
                    icon: 'error',
                    title: jqXHR.responseJSON.message,
                    showConfirmButton: false,
                    timer: 1500
                })
            })
    }
}