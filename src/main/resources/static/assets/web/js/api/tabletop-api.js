export const tabletopAPI = new TabletopAPI();

function TabletopAPI() {
    this.findAll = (done, fail) => {
        $.ajax({
            type: "GET",
            url: `${location.origin}/api/table-top/unavaliable`,
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

    this.findAllPage = (numberPage, done, fail) => {
        $.ajax({
            url: `${location.origin}/api/table-top/page?page=${numberPage}`,
            type: "GET",
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



