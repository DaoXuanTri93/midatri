class Booking {
    constructor(id, tableTop, user) {
        this.id = id;
        this.tableTop = tableTop;
        this.user = user;
    }
}

class BookingItemCreate {
    constructor(id, discount, grandTotal, price, quantity, booking_id, item_id) {
        this.id = id;
        this.discount = discount;
        this.grandTotal = grandTotal;
        this.price = price;
        this.quantity = quantity;
        this.booking_id = booking_id;
        this.item_id = item_id;
    }
}

let tabletops;
let itemMap = new Map();
let bookingItemTableTopMap = new Map();
let bookingMap = new Map();


let booking_id = 0;
let tableTopId = 0;
let arrayBooking = [];

function fetchBookings() {
    api.booking.findAllByStatusNotComplete((bookings) => {
        $.each(bookings, (i, booking) => {

            bookingMap.set(booking.tableTopId, booking);
            fetchBookingItems(booking.tableTopId, booking.id);
        })
    }, (jqXHR) => {

    })
}

function fetchBookingItems(tableTopId, bookingId) {
    api.bookingItem.findAllByBooking(bookingId, (bookingItems) => {
        bookingItemTableTopMap.set(tableTopId, bookingItems);
    }, (jqXHR) => {

    })
}

function renderTabletop(tabletop) {
    let result = `<li class="tableAndRoom using" id="${tabletop.id}" data-id="${tabletop.id}" style="text-align: center;"">
                            <div class="tableroom-actions"></div>
                                <a container="body" placement="right top" class="using">
                                    <div class="table-room">
                                        <span></span>
                                            </div>
                                                <div class="product-info">
                                                <span class="product-name"> ${tabletop.id} </span>
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
    $("#render-table").append(result);

}

function renderAllTableTop() {
    let onTabletopClick = () => {
        $(".tableAndRoom").click((event) => {
            tableTopId = parseInt($(event.currentTarget).attr('data-id'));
            $('#link-menu').trigger('click');
            $('#table-room').text('Bàn ' + tableTopId)
            renderBookingTabletop(tableTopId);
        });
    };
    if (tabletops !== undefined) {
        for (let tabletop of tabletops) {
            renderTabletop(tabletop);
        }
        onTabletopClick();
        return;
    }

    api.tabletop.findAll((data) => {
        tabletops = data;
        $.each(data, (i, tabletop) => {
            renderTabletop(tabletop);
        });
        onTabletopClick();
    }, error => {

    });


}

function renderCategory() {
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "GET", url: "http://localhost:8080/api/category"
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

function renderItem(item) {
    let result = `
                        <li data-id="${item.id}" class="btn-add-product">
                        <a title=${item.title}>
                            <div class="product-img" >
                                <img src=${item.img}>
                                <div class="product-price">${item.price}</div>
                            </div>
                            <div class="product-info">
                                <div class="product-name">${item.title}</div>
                            </div>
                        </a>
                        </li>
                `
    $("#render-product").prepend(result);
}


function renderAllItems() {
    let onItemClick = () => {
        $(".btn-add-product").click((event) => {
            let itemId = parseInt($(event.currentTarget).attr('data-id'));
            booking(tableTopId, itemId)
        });
    };

    $("#render-product li").empty();
    if (itemMap.size > 0) {
        $.each(itemMap.values(), (i, item) => {
            itemMap.set(item.id, item);
            renderItem(item);
        });
        onItemClick();
        return;
    }

    api.item.findAll((data) => {
        if (itemMap === undefined) itemMap = new Map(); else itemMap.clear();
        $.each(data, (i, item) => {
            itemMap.set(item.id, item);
            renderItem(item);
        });
        onItemClick();
    }, error => {

    });
}

function renderBookingTabletop(tabletopId) {
    console.log(tabletopId)
    $("#render-tableTop").empty();
    let bookingItems = bookingItemTableTopMap.get(tabletopId);
    if (bookingItems !== undefined) {
        $.each(bookingItems, (i, bookingItem) => {
            renderBookingItem(1, bookingItem);
        })
    }
}

function booking(tabletopId, itemId) {
    let newBookingItem = {
        quantity: 1,
        itemId: itemId
    }
    let booking = bookingMap.get(tabletopId);
    if (booking === undefined) {
        let createBooking = {
            tabletopId: tabletopId
        }
        api.booking.booking(createBooking, (booking) => {
            newBookingItem.bookingId = booking.id;
            createBookingItem(newBookingItem);
        }, (jqXHR) => {
        })
        return;
    }
    let bookingItems = bookingItemTableTopMap.get(tabletopId);
    for (const bookingItem of bookingItems) {
        if (itemId === bookingItem.itemId) {
            let quantity = parseInt($(`#${bookingItem.id} .item-quantity`).text());
            quantity += 1;
            $(`#${bookingItem.id} .item-quantity`).text(quantity)

            return;
        }
    }
    newBookingItem.bookingId = booking.id;
    createBookingItem(tabletopId, newBookingItem);
}

function createBookingItem(tabletopId, newBookingItem) {
    api.bookingItem.create(newBookingItem, (bookingItem) => {
        console.log(bookingItem)
        let bookingItems = bookingItemTableTopMap.get(tabletopId);
        bookingItems.push(bookingItem);
        renderBookingItem(bookingItem);
    }, (jqXHR) => {

    })
}

function renderCategoryItem() {
    $("#render-product li").empty();
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "GET", url: "http://localhost:8080/api/item"
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
        // handelRemoveEvent();
        // handelAll();
    }).fail(error => {

    })
}

function handleFilterCategory(categoryId) {
    $("#render-product li").empty();
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "GET", url: "http://localhost:8080/api/item/category/" + categoryId
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
    }).fail(error => {

    }).always(function () {
        // $("#render-product").removeClass('d-none');
    });
}

function hanldeDeletedBookingItem(bookingIdItem) {
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "POST", url: "http://localhost:8080/api/bookingItem/" + bookingIdItem
    })
        .done(() => {
            // $("#render-tableTop div").empty();
            $.ajax({
                headers: {
                    "Accept": "application/json", "Content-type": "application/json"
                }, type: "GET", url: "http://localhost:8080/api/booking/" + tableTopId
            }).done((data) => {
                $.each(data, (i, item) => {
                    booking_id = item.id;
                    $.ajax({
                        headers: {
                            "Accept": "application/json", "Content-type": "application/json"
                        }, type: "GET", url: "http://localhost:8080/api/bookingItem/" + booking_id
                    })
                        .done((data) => {
                            renderBookingItem(data);
                        })
                })

            })

        })

}

function renderBookingItem(index, bookingItem) {
    let item = itemMap.get(bookingItem.itemId);
    let result = `
                     <div class="product-cart-item" id="${bookingItem.id}">
                        <kv-cashier-cart-item class="row-list row-list-active active">
                            <div class="row-list-content">
                                <div class="cell-action">
                                    <a class="btn-icon btn-trash" href="javascript:void(0);"
                                       title="Xóa món">
                                        <i class="fa-regular fa-trash-can" onclick="hanldeDeletedBookingItem(${bookingItem.id})"></i>
                                    </a>
                                </div>
                                <div class="cell-order"> ${index}
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
                                            <h4 title="APEROL SPRITZ">${item.title}</h4>
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
                                                    title="Giảm số lượng món"
                                                    onclick="handleReduceQuantity(${bookingItem.id})">
                                                <i class="fa-regular fa-minus"></i>
                                            </button>
                                            <button class="form-control form-control-sm item-quantity" >
                                                ${bookingItem.quantity}
                                            </button>
                                            <button class="btn-icon up" type="button"
                                                    title="Tăng số lượng món"
                                                    onclick="handleIncreaseQuantity(${bookingItem.id})">
                                                <i class="fa-regular fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="cell-change-price">
                                        <div class="popup-anchor">
                                            <button class="form-control form-control-sm">
                                                ${item.price}
                                            </button>
                                        </div>
                                    </div>
                                    <div class="cell-price">${item.price * bookingItem.quantity}</div>
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
                     
                `;
    $("#render-tableTop").prepend(result);
}

function handleIncreaseQuantity(bookingItem_id) {
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "PATCH", url: `http://localhost:8080/api/bookingItem/${bookingItem_id}/increaseQuantity`
    })
        .done((bookingItem) => {
            handelTableTop(tableTopId)
            // let str = handelTableTop(tableTopId);
            // let current = $("#item.id");
            // current.replaceWith(str)
        })
}

function handleReduceQuantity(bookingItem_id) {

    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "PATCH", url: `http://localhost:8080/api/bookingItem/${bookingItem_id}/reduceQuantity`
    })
        .done(() => {
            handelTableTop(tableTopId)
        })
}

function handelTableTop(tableTopId) {
    $("#render-tableTop div").empty();
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "GET", url: "http://localhost:8080/api/booking?tabletopId=" + tableTopId
    })
        .done((data) => {
            booking_id = data[0].id;
            $.ajax({
                headers: {
                    "Accept": "application/json", "Content-type": "application/json"
                }, type: "GET", url: "http://localhost:8080/api/bookingItem/" + booking_id
            })
                .done((data) => {
                    renderBookingItem(data)
                })
        })


}

function handelPay(tableTop, booking_id) {
    if (tableTop == 0 || booking_id == 0) {
        alert("Chưa có ĐƠN HÀNG thanh toán")
    } else {
        $(".table-payment").text(`Bàn - ${tableTop}`);
        $("#paypal").removeClass("d-none")
        $("#renderPay div").empty();
        $.ajax({
            headers: {
                "Accept": "application/json", "Content-type": "application/json"
            }, type: "GET", url: "http://localhost:8080/api/bookingItem/" + booking_id
        })
            .done((data) => {
                let totalGrand = 0;
                $.each(data, (i, item) => {
                    let str = `
                                 <div  class="row-list row-list-active">
                                    <div  class="cell-order"> 1 </div>
                                    <div  class="cell-name">
                                        <h4 >
                                            ${item.item.title}
                                            <span  class="attr-wrapper">
                                   </span>
                                        </h4>
                                        <ul  class="comboset-list-item">
                                        </ul>
                                        <div  class="list-topping">
                                        </div>
                                    </div>
                                    <div  class="cell-quatity-static">
                                        <div  class="cell-quantity-inner">
                                            <span >${item.quantity}</span>
                                        </div>
                                    </div>
                                    <div  class="cell-change-price">
                                        <div  class="popup-anchor">
                                           ${item.item.price}
                                        </div>
                                    </div>
                                    <div  class="cell-price"> ${item.grandTotal} </div>
                                </div>
                                `
                    $('#renderPay').append(str);
                    totalGrand += item.grandTotal;
                })
                $('#totalGrand').text(totalGrand);
                let total = $('#totalGrand').text();
                let priceSale = $('#priceSale').val();
                $('#customerPay').text(total - priceSale);

                let payingAmountTxt = $('#payingAmountTxt').val();
                let customerPay = $('#customerPay').text();
                $('#moneySuperfluous').text(payingAmountTxt - customerPay);


            })
    }

}


function cancelPay() {
    $("#paypal").addClass("d-none")
}

function handlePayAll(booking_id) {
    $.ajax({
        headers: {
            "Accept": "application/json", "Content-type": "application/json"
        }, type: "DELETE", url: "http://localhost:8080/api/bookingItem/deleted/" + booking_id
    })
        .done((data) => {
            // alert("Thanh Toan Thanh Cong")
            $("#renderPay div").empty();
            cancelPay()
            handelTableTop(tableTopId)
        })
}

function handelSplitTable(tableTop, booking_id) {
    if (tableTop == 0 || booking_id == 0) {
        alert("Chưa chon ban can tach")
    } else {
        $("#splitTable").removeClass('d-none')
        $("#tableSplit").text(`Bàn - ${tableTop}`)

    }
}

function closeSplitTable() {
    $("#splitTable").addClass('d-none')
}

function handelRemoveEvent() {
    $(".btn-add-product").off();
}

function renderAll() {
    fetchBookings();
    // fetchBookingItems();
    renderAllItems();
    //  renderCategory()
    renderAllTableTop();

}

renderAll();

