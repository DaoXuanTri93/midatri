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
    this.updateQuantity = (bookingItemId, data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/${bookingItemId}/quantity`,
            type: "PATCH",
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
    this.increaseQuantity = (bookingItemId, data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/${bookingItemId}/increaseQuantity`,
            type: "PATCH",
            contentType: 'application/json'
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.decreaseQuantity = (bookingItemId, data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/${bookingItemId}/decreaseQuantity`,
            type: "PATCH",
            contentType: 'application/json'
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.removeBookingItem = (bookingItemId, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/deleted/${bookingItemId}`,
            type: "DELETE",
            contentType: 'application/json'
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.removeBookingItemByBooking = (bookingId, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/deletedByBooking/${bookingId}`,
            type: "DELETE",
            contentType: 'application/json'
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.updateNote = (bookingItemId, data, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/bookingItem/updateNote/${bookingItemId}`,
            type: "PATCH",
            contentType: 'application/json'
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
}