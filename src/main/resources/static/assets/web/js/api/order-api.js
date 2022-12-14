export const orderAPI = new OrderAPI();

function OrderAPI() {
    this.create = (data,done, fail) => {
        $.ajax({
            type: "POST",
            url: `${location.origin}/api/order/create`,
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