export const bookingAPI = new BookingAPI();
function BookingAPI() {
    this.findAll = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/bookings`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.create = (data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/booking/create`,
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