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

let index = "*"
let tabletops;
let itemMap = new Map();
let bookingItemTableTopMap = new Map();
let bookingMap = new Map();
let categoryMap = new Map();

let grandTotal = 0;
let booking_id = 0;
let tableTopId = 0;

function fetchCategory() {
    let onCategoryClick = () => {
        $(".render-category").click((event) => {
            let categoryId = parseInt($(event.currentTarget).attr('data-id'));
            handleFilterCategory(categoryId);
        });
    };
    api.category.findAll((categorys) => {
        $.each(categorys, (i, category) => {
            categoryMap.set(category.id, category)
            renderCategory(category);
        })
        onCategoryClick();
    }, (jqXHR) => {

    })
}

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
                                                <span class="product-name"> ${tabletop.title} </span>
                                                    <div class="wrap-note" href="javascript:void(0)">
                                                    <label>
<!--                                                        <button class="btn-icon">-->
<!--                                                            <span class="note-hint note-hint-input">Nhập ghi chú...</span>-->
<!--                                                        </button>-->
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
            $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(grandTotal));
            tableTopId = parseInt($(event.currentTarget).attr('data-id'));
            $('#link-menu').trigger('click');
            $('#table-room').text(tabletops[tableTopId - 1].title)
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

function renderCategory(category) {
    let str = `<div class="swiper-slide product-category-item-parent render-category" data-id="${category.id}">
                 <a class="btn btn-text-gray" >${category.categoryName}</a>
                </div>
            `
    $("#renderCategory").append(str);


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

let onItemClick = () => {
    $(".btn-add-product").click((event) => {
        $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(grandTotal));
        let itemId = parseInt($(event.currentTarget).attr('data-id'));
        booking(tableTopId, itemId)
    });
};

function renderAllItems() {

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
        if (itemMap === undefined) {
            itemMap = new Map();
        } else {
            itemMap.clear()
        }

        $.each(data, (i, item) => {
            itemMap.set(item.id, item);
            renderItem(item);
        });
        onItemClick();
    }, error => {

    });
}

function renderBookingTabletop(tabletopId) {
    $("#render-tableTop").empty();
    let bookingItems = bookingItemTableTopMap.get(tabletopId);
    if (bookingItems !== undefined) {
        $.each(bookingItems, (i, bookingItem) => {
            renderBookingItem(index, bookingItem);
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
            createBookingItem(tabletopId, newBookingItem);
        }, (jqXHR) => {

        })
        return;
    }

    let bookingItems = bookingItemTableTopMap.get(tabletopId);
    for (const bookingItem of bookingItems) {
        if (itemId === bookingItem.itemId) {
            let quantity = parseInt($(`#${bookingItem.id} .item-quantity`).text().trim());
            let price = parseInt($(`#${bookingItem.id} .item-price`).text().trim());

            quantity += 1;
            $(`#${bookingItem.id} .item-quantity`).text(quantity)
            $(`#${bookingItem.id} .cell-price`).text(new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(quantity * price))
            api.bookingItem.updateQuantity(bookingItem.id, quantity, (data) => {
                $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                }).format(grandTotal));
            }, (jqXHR) => {
            });
            return;
        }
    }

    newBookingItem.bookingId = booking.id;
    createBookingItem(tabletopId, newBookingItem);
}

function createBookingItem(tabletopId, newBookingItem) {
    api.bookingItem.create(newBookingItem, (bookingItem) => {
        console.log(newBookingItem)
        let bookingItems = bookingItemTableTopMap.get(tabletopId);
        bookingItems.push(bookingItem);
        renderBookingItem(index, bookingItem);
    }, (jqXHR) => {

    })
}

function renderCategoryItem() {

}

function handleFilterCategory(categoryId) {
    $("#render-product li").empty();
    for (let i = 1; i <= itemMap.size; i++) {
        if (categoryId === itemMap.get(i).categoryId) {
            renderItem(itemMap.get(i));
        }
    }
    onItemClick();
}

function hanldeDeletedBookingItem(bookingIdItem) {
    // $.ajax({
    //     headers: {
    //         "Accept": "application/json", "Content-type": "application/json"
    //     }, type: "POST", url: "http://localhost:8080/api/bookingItem/" + bookingIdItem
    // })
    //     .done(() => {
    //         // $("#render-tableTop div").empty();
    //         $.ajax({
    //             headers: {
    //                 "Accept": "application/json", "Content-type": "application/json"
    //             }, type: "GET", url: "http://localhost:8080/api/booking/" + tableTopId
    //         }).done((data) => {
    //             $.each(data, (i, item) => {
    //                 booking_id = item.id;
    //                 $.ajax({
    //                     headers: {
    //                         "Accept": "application/json", "Content-type": "application/json"
    //                     }, type: "GET", url: "http://localhost:8080/api/bookingItem/" + booking_id
    //                 })
    //                     .done((data) => {
    //                         renderBookingItem(data);
    //                     })
    //             })
    //
    //         })
    //
    //     })

}

function renderBookingItem(index, bookingItem) {
    let item = itemMap.get(bookingItem.itemId);
    grandTotal += (item.price * bookingItem.quantity)
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
                                                    onclick="handleDecreaseQuantity(${item.id})">
                                                <i class="fa-regular fa-minus"></i>
                                            </button>
                                            <button class="form-control form-control-sm item-quantity" ">
                                                ${bookingItem.quantity}
                                                
                                                
                                            </button>
                                            <button class="btn-icon up" type="button"
                                                    title="Tăng số lượng món"
                                                    onclick="handleIncreaseQuantity(${item.id})">
                                                <i class="fa-regular fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="cell-change-price">
                                        <div class="popup-anchor">
                                            <button class="form-control form-control-sm item-price" id="${bookingItem.id}">
                                                ${item.price}
                                            </button>
                                        </div>
                                    </div>
                                    <div class="cell-price" id="${bookingItem.id}">
                              ${new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(item.price * bookingItem.quantity)}                  


</div>
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

function handleIncreaseQuantity(ItemId) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    for (const bookingItem of bookingItems) {
        if (ItemId === bookingItem.itemId) {
            let quantity = parseInt($(`#${bookingItem.id} .item-quantity`).text());
            let price = parseInt($(`#${bookingItem.id} .item-price`).text());
            quantity += 1;
            $(`#${bookingItem.id} .item-quantity`).text(quantity)
            $(`#${bookingItem.id} .cell-price`).text(quantity * price)
            api.bookingItem.increaseQuantity(bookingItem.id, quantity, (data) => {

            }, (jqXHR) => {
            });
            return;
        }

    }
}

function handleDecreaseQuantity(itemId) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId)
    for (const bookingItem of bookingItems) {
        if (itemId === bookingItem.itemId) {
            let quantity = $(`#${bookingItem.id} .item-quantity`).text();
            let price = parseInt($(`#${bookingItem.id} .item-price`).text());
            quantity -= 1;
            if (quantity <= 0) {
                api.bookingItem.removeBookingItem(bookingItem.id, (data) => {
                    $(`#render-tableTop #${bookingItem.id}`).remove();
                    fetchBookings();
                }, (jqXHR) => {

                })

            }
            $(`#${bookingItem.id} .item-quantity`).text(quantity);
            $(`#${bookingItem.id} .cell-price`).text(quantity * price)

            api.bookingItem.decreaseQuantity(bookingItem.id, quantity, (data) => {

            }, (jqXHR) => {

            });
            return;
        }
    }
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

function handelPay(tableTopId) {
    let onPayAllOffClick = () => {
        $("#payAllOffClick").click((e) => {
            cancelPay();
        })
    }
    let onPayAllClick = (bookingId) => {
        $("#payAllClick").click((e) => {
            api.bookingItem.removeBookingItemByBooking(bookingId, (data) => {
                iziToast.success(
                    {
                        timeout: 1500,
                        position: 'topRight',
                        title: 'OK',
                        message: "thanh toan thanh cong"
                    });
                $("#render-tableTop div").remove();
                fetchBookings();
                cancelPay();

            }, (jqXHR) => {

            })
        })
    }
    if (tableTopId === 0) {
        Swal.fire('Vui lòng chọn bàn đặt !!!')
    } else {
        // tableTopId = parseInt($(event.currentTarget).attr('data-id'));
        let tableTop = tabletops;
        $(".table-payment").text(`Bàn - ${tableTop[tableTopId - 1].title}`);
        $("#paypal").removeClass("d-none")
        let bookingItems = bookingItemTableTopMap.get(tableTopId);
        let bookingId = bookingMap.get(tableTopId).id;
        $("#renderPay div").remove();
        api.bookingItem.findAllByBooking(bookingId, (bookingItems) => {
                $.each(bookingItems, (i, bookingItem) => {
                    let str = `
                                         <div  class="row-list row-list-active">
                                            <div  class="cell-order"> ${i + 1} </div>
                                            <div  class="cell-name">
                                                <h4 >
                                                    ${null}
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
                                                    <span >${bookingItem.quantity}</span>
                                                    
                                                </div>
                                            </div>
                                            <div  class="cell-change-price">
                                                <div  class="popup-anchor">
                                                   ${bookingItem.price}
                                                </div>
                                            </div>
                                            <div  class="cell-price"> ${bookingItem.quantity * bookingItem.price} </div>
                                        </div>
                                        `
                    $('#renderPay').append(str);

                })
                onPayAllClick(bookingId);
                onPayAllOffClick(bookingId);
            },
            (jqXHR) => {

            })
        // api.bookingItem.findAllByBooking(tableTopId, (data) => {
        //     console.log(data)
        //     renderBookingItem(1, data)
        //
        // }, (jqXHR) => {
        //
        // })
        // $.ajax({
        //     headers: {
        //         "Accept": "application/json", "Content-type": "application/json"
        //     }, type: "GET", url: "http://localhost:8080/api/bookingItem/" + booking_id
        // })
        //     .done((data) => {
        //                 let totalGrand = 0;
        //                 $.each(data, (i, item) => {
        //                     let str = `
        //                                  <div  class="row-list row-list-active">
        //                                     <div  class="cell-order"> 1 </div>
        //                                     <div  class="cell-name">
        //                                         <h4 >
        //                                             ${item.item.title}
        //                                             <span  class="attr-wrapper">
        //                                    </span>
        //                                         </h4>
        //                                         <ul  class="comboset-list-item">
        //                                         </ul>
        //                                         <div  class="list-topping">
        //                                         </div>
        //                                     </div>
        //                                     <div  class="cell-quatity-static">
        //                                         <div  class="cell-quantity-inner">
        //                                             <span >${item.quantity}</span>
        //                                         </div>
        //                                     </div>
        //                                     <div  class="cell-change-price">
        //                                         <div  class="popup-anchor">
        //                                            ${item.item.price}
        //                                         </div>
        //                                     </div>
        //                                     <div  class="cell-price"> ${item.grandTotal} </div>
        //                                 </div>
        //                                 `
        //                     $('#renderPay').append(str);
        //                     totalGrand += item.grandTotal;
        //                 })
        //         $('#totalGrand').text(totalGrand);
        //         let total = $('#totalGrand').text();
        //         let priceSale = $('#priceSale').val();
        //         $('#customerPay').text(total - priceSale);
        //
        //         let payingAmountTxt = $('#payingAmountTxt').val();
        //         let customerPay = $('#customerPay').text();
        //         $('#moneySuperfluous').text(payingAmountTxt - customerPay);
        //
        //
        //     })
    }

}

function handelNotification(tableTopId, booking_id) {
    if (tableTopId === 0) {
        return alert("ko co don hang")
    } else {
        $("#notification").addClass("disabled")

    }

}

function cancelPay() {
    $("#paypal").addClass("d-none")
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

function renderAll() {
    fetchBookings();
    fetchCategory();
    renderAllItems();
    //  renderCategory()
    renderAllTableTop();

}

renderAll();

