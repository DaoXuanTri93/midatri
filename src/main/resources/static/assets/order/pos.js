function renderTableTop() {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/table-top"
    }).done((data) => {

        $.each(data, (i, item) => {
            let str = `
                         <li class="tableAndRoom using" style="text-align: center;" onclick="handelTableTop(${item.id})">
                            <div class="tableroom-actions"></div>
                                <a container="body" placement="right top" class="using">
                                    <div class="table-room">
                                        <span></span>
                                            </div>
                                                <div class="product-info">
                                                <span class="product-name"> ${item.id} </span>
                                                    <div class="wrap-note" href="javascript:void(0)">
                                                    <label>
                                                        <button class="btn-icon">
                                                            <span class="note-hint note-hint-input">Nhập ghi chú...</span>
                                                        </button>
                                                    </label>
                                            </div>
                                    </div>
                                </a>
                        </li>
                     `

            $("#render-table").append(str);
        })
        handelRemoveEvent();

    }).fail(error => {

    })
}

function renderCategory() {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/category"
    }).done((data) => {

        $.each(data, (i, item) => {
            let str = `
                    <div class="swiper-slide product-category-item-parent render-category" data-id="${item.id}"
                    onclick="handleFilterCategory(${item.id})">
                        <a class="btn btn-text-gray" >${item.categoryName}</a>
                    </div>
                `
            $("#renderCategory").append(str);

        })
        handelRemoveEvent();

    }).fail(error => {

    })
}

function renderProducts() {
    $("#render-product li").remove();
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/item"
    }).done((data) => {
        $.each(data, (i, item) => {
            let str = `
                        <li data-id="${item.id}" class="btn-add-product" onclick="handleAddBookingItem(${item.id})">
                        <a title=${item.title}>
                            <div class="product-img" onclick="handleEventOnClick(${item.id})">
                                <img src=${item.img}>
                                <div class="product-price">${item.price}</div>
                            </div>
                            <div class="product-info">
                                <div class="product-name">${item.title}</div>
                            </div>
                        </a>
                        </li>
                `
            $("#render-product").prepend(str);
        })
        handelRemoveEvent();
        // handelAll();
    }).fail(error => {

    })
}

function handleRenderAll() {
    $("#render-product li").remove();
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/item"
    }).done((data) => {
        $.each(data, (i, item) => {
            let str = `
                        <li data-id="${item.id}" class="btn-add-product">
                        <a title=${item.title}>
                            <div class="product-img" onclick="handleEventOnClick(${item.id})">
                                <img src=${item.img}>
                                <div class="product-price">${item.price}</div>
                            </div>
                            <div class="product-info">
                                <div class="product-name">${item.title}</div>
                            </div>
                        </a>
                        </li>
                `
            $("#render-product").prepend(str);
        })
        handelRemoveEvent();
        // handelAll();
    }).fail(error => {

    })
}

function handleFilterCategory(categoryId) {
    $("#render-product li").remove();
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/item/category/" + categoryId
    }).done((data) => {
        $.each(data, (i, item) => {
            let str = `
                        <li data-id="${item.id}" class="btn-add-product" onclick="handleEventOnClick(${item.id})">
                        <a title=${item.title}>
                            <div class="product-img">
                                <img src=${item.img}>
                                <div class="product-price">${item.price}</div>
                            </div>
                            <div class="product-info">
                                <div class="product-name">${item.title}</div>
                            </div>
                        </a>
                        </li>
                        `
            $("#render-product").append(str);
            handelRemoveEvent();
        })

    }).fail(error => {

    }).always(function () {
        // $("#render-product").removeClass('d-none');
    });
}

function handleEventOnClick(id) {
    alert(id)
}

function handleAddBookingItem(bookingItemId) {
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "POST",
        url: "http://localhost:8080/api/bookingItem/" + bookingItemId
    })
}

function handelTableTop(tableTopId) {
    alert(tableTopId);
}

function handelRemoveEvent() {
    $(".btn-add-product").off();
}

function renderAll() {
    renderProducts();
    renderCategory()
    renderTableTop();
}

renderAll();

