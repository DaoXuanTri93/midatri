export const orderAPI = new OrderAPI();

function OrderAPI(){
    this.create = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/order`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
}