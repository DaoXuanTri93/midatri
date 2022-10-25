export const bookingItemAPI = new BookingItemAPI();

function BookingItemAPI() {
    this.findAll = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/bookingItem`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.findAllByBooking = (bookingId, done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/bookingItem?bookingId=${bookingId}`
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
            url: `${location.origin}/api/bookingItem/create`,
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