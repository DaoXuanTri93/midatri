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
    alert(id + "aa")
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
    $("#render-tableTop div").remove();
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/bookingItem/" + tableTopId
    })
        .done((data) => {
            $.each(data,(i,item) => {
                let str = `
                <div class="product-cart-item" id="cab7a4b0-ebdc-4383-876a-dd5f885f094d">
                                                <kv-cashier-cart-item class="row-list row-list-active active">
                                                    <div class="row-list-content">
                                                        <div class="cell-action">
                                                            <a class="btn-icon btn-trash" href="javascript:void(0);"
                                                               title="Xóa món">
                                                                <i class="fa-regular fa-trash-can"></i>
                                                            </a>
                                                        </div>
                                                        <div class="cell-order"> 1.
                                                            <div>
                                                                <button class="btn-icon" type="button"
                                                                        title="Món ưu tiên">
                                                                    <i class="fa-star fa-regular"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <div class="row-product">
                                                            <div class="cell-name full">
                                                                <div class="wrap-name">
                                                                    <h4 title="APEROL SPRITZ"> APEROL SPRITZ </h4>
                                                                    <span class="wrap-icons"></span>
                                                                    <div class="attr-wrapper">
                                                                    </div>
                                                                </div>
                                                                <ul class="comboset-list-item">

                                                                </ul>
                                                                <div class="wrap-note" href="javascript:void(0)">
                                                                    <button class="btn btn-sm btn-light has-Update"
                                                                            style="cursor: pointer;">
                                                                        <i class="far fa-pen"></i>
                                                                        <span class="note-hint"
                                                                              style="cursor: pointer;">Ghi chú món</span>
                                                                    </button>
                                                                </div>
                                                                <div class="list-topping">

                                                                </div>
                                                            </div>
                                                            <div class="cell-quatity">
                                                                <div class="cell-quantity-inner">
                                                                    <button class="btn-icon down" type="button"
                                                                            title="Giảm số lượng món">
                                                                        <i class="fa-regular fa-minus"></i>
                                                                    </button>
                                                                    <button class="form-control form-control-sm item-quantity">
                                                                        1
                                                                    </button>
                                                                    <button class="btn-icon up" type="button"
                                                                            title="Tăng số lượng món">
                                                                        <i class="fa-regular fa-plus"></i>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                            <div class="cell-change-price">
                                                                <div class="popup-anchor">
                                                                    <button class="form-control form-control-sm">
                                                                        30,000
                                                                    </button>
                                                                </div>
                                                            </div>
                                                            <div class="cell-price"> 30,000</div>
                                                            <div class="cell-actions">
                                                                <div class="btn-group" dropdown="">
                                                                    <button class="dropdown-toggle" type="button"
                                                                            title="Thêm dòng mới">
                                                                        <i class="fas fa-plus"></i>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </kv-cashier-cart-item>
                                            </div>
                `
                $("#render-tableTop").append(str)
            })
        })
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

