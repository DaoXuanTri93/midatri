
export const userAPI=new UserAPI();
function UserAPI() {
    this.findAll = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/users`,
        })
            .done((data) => {
                done(data);
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
    this.create = (data,done, fail) => {
        $.ajax({
            url: `${location.origin}/api/users/create`,
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data)
        })
            .done((data) => {
                done(data);
                window.location.href="/user";
            })
            .fail((jqXHR) => {
                fail(jqXHR);
            })
    }
}