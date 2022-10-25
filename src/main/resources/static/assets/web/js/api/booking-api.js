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
                fail(jqXHR);
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
                fail(jqXHR);
            })
    }
}