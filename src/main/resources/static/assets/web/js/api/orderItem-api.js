export const orderItemAPI = new OrderItemAPI();

function OrderItemAPI() {
    this.create = (data,done, fail) => {
        $.ajax({
            type: "POST",
            url: `${location.origin}/api/orderItem/create`,
            contentType: 'application/json',
            data : JSON.stringify(data)
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
}