let index = "*"
let tabletops;
let itemMap = new Map();
let bookingItemTableTopMap = new Map();
let bookingMap = new Map();
let categoryMap = new Map();

let booking_id = 0;
let tableTopId = 0;

let onItemClick = () => {
    $(".btn-add-product").click((event) => {
        let itemId = parseInt($(event.currentTarget).attr('data-id'));
        booking(tableTopId, itemId);
    });
};

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
            console.log("3 lan")
            tableTopId = parseInt($(event.currentTarget).attr('data-id'));
            $('#link-menu').trigger('click');
            let a = tabletops;
            let titleTable = "";
            for (const b of a) {
                if (tableTopId === b.id) {
                    titleTable = b.title
                }
            }
            $('#table-room').text(titleTable)
            $('#payAllMoney').off();
            $('#payAllMoney').on('click', () => {
                handlePay(tableTopId);
            })

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
                                <div class="product-price">
                                
                    ${new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(item.price)}
                    </div>
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
        // if (itemMap === undefined) {
        //     itemMap = new Map();
        // } else {
        //     itemMap.clear()
        // }

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
    handleGrandTotal(tabletopId);

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
            bookingMap.set(tabletopId, booking);
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
            }, (jqXHR) => {

            });
            return;
        }
        console.log("item " + itemId + " not found")
    }

    newBookingItem.bookingId = booking.id;
    createBookingItem(tabletopId, newBookingItem);
}

function createBookingItem(tabletopId, newBookingItem) {
    api.bookingItem.create(newBookingItem, (bookingItem) => {
        let bookingItems = bookingItemTableTopMap.get(tabletopId);
        if (bookingItems === undefined) {
            bookingItems = [bookingItem];
            bookingItemTableTopMap.set(tabletopId, bookingItems)
        } else
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
                                                onclick="handleNoteItem(${item.id})"
                                                style="cursor: pointer;">${(item.content != null) ? item.content : 'Ghi chú món ăn'}</span>
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
                     
                     <modal-container class="modal fade in d-none" role="dialog" id="noteItemUpdate" tabindex="-1" aria-modal="true" style="display: block;">
                            <div role="document" class="modal-dialog modal-note ui-draggable">
                                <div class="modal-content">
                                    <kv-modal _nghost-bwu-c36="">
                                        <!---->
                                        <div ="" class="modal-header ng-star-inserted ui-draggable-handle">
                                            <h4 ="" class="modal-title pull-left">Ghi chú / Món thêm</h4>
                                            <button ="" aria-label="Close" class="close pull-right" skip-disable="" type="button"><i ="" class="fa-light fa-xmark"></i></button>
                                        </div>
                                        <!---->
                                        <div ="" class="ng-star-inserted"></div>
                                        <kv-cashier-cart-item-note-and-toping class="ng-star-inserted">
                                            <div ="" class="modal-body">
                                                <!---->
                                                <div ="" class="form-group form-note">
                                                    <input ="" id="${bookingItem.id}"  class="form-control ng-valid ng-dirty ng-touched getNodeInput" value="" maxlength="300" style="height: 32px;"></input></div>
                                                <!---->
                                                <div ="" class="product-cart-list product-cart-list--addMoreTopping">
                                                    <!---->
                                                </div>
                                            </div>
                                            <div ="" class="modal-footer"><a ="" class="btn btn-primary" href="javascript:void(0);"><i ="" class="fa fa-check-square"></i><span ="" translate="">Xong</span>
                                            </a>
                                            <a ="" class="btn btn-default" href="javascript:void(0);" onclick="onCancelClick(${item.id})">>
                                            <i ="" class="fa fa-ban"></i>
                                            <span ="" translate="">Bỏ qua</span>
                                            </a>
                                            </div>
                                        </kv-cashier-cart-item-note-and-toping>
                                        <!----><!---->
                                    </kv-modal>
                                </div>
                            </div>
                    </modal-container>
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

function handleNoteItem(itemId) {
    $("#noteItemUpdate").removeClass('d-none')
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    for (const bookingItem of bookingItems) {
        if (itemId === bookingItem.itemId) {
            let noteItem = $(`#${bookingItem.id} .getNodeInput`).val();

            api.bookingItem.updateNote(bookingItem.id, noteItem, (data) => {
                alert("ok")
            }, (jqXHR) => {

            })

            $(`#${bookingItem.id} .getNodeInput`).val(noteItem);

            return;
        }
    }
}

function handleTableTop(tableTopId) {
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

let grandTotalAll = new Map();
let grandTotal = 0;

function handleGrandTotal(tableId) {
    let bookingItem = bookingItemTableTopMap.get(tableId);
    $.each(bookingItem, (i, item) => {
        grandTotal += (item.quantity * item.price)
    })
    $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(grandTotal));
}

function handlePay(tableTopId) {
    let onPayAllClick = (bookingId) => {
        $("#payAllClick").click((event, c = bookingId) => {
            api.bookingItem.removeBookingItemByBooking(c, (data) => {
                // success("Xóa Thành Công")
                $("#render-tableTop div").remove();
                $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                }).format(0));
                bookingMap.clear()
                bookingItemTableTopMap.clear()
                cancelPay();

            }, (jqXHR) => {

            })
        })
    }
    if (tableTopId === 0) {
        Swal.fire('Vui lòng chọn bàn đặt !!!')
    } else {
        let a = tabletops;
        let titleTable = "";
        for (const b of a) {
            if (tableTopId === b.id) {
                titleTable = b.title
            }
        }
        $(".table-payment").text(`Bàn - ${titleTable}`);

        if (tableTopId === 0) {
            alert()
        }
        let bookingItems = bookingItemTableTopMap.get(tableTopId);
        if (bookingItems === undefined) {
            alert()
        }
        console.log(bookingMap)
        let bookingId = bookingMap.get(tableTopId).id;
        console.log(bookingId)
        $("#paypal").removeClass("d-none")
        $("#renderPay div").remove();
        api.bookingItem.findAllByBooking(bookingId, (bookingItems) => {
                $.each(bookingItems, (i, bookingItem) => {
                    let str = `
                                         <div  class="row-list row-list-active">
                                            <div  class="cell-order"> ${i + 1} </div>
                                            <div  class="cell-name">
                                                <h4 >
                                                    ${itemMap.get(bookingItem.itemId).title}
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
            },
            (jqXHR) => {

            })
        onPayAllClick(bookingId);
    }


}

function handleNotification(tableTopId, booking_id) {
    if (tableTopId === 0) {
        return alert("ko co don hang")
    } else {
        $("#notification").addClass("disabled")

    }

}

function onCancelClick(){
    $("#noteItemUpdate").addClass('d-none')
}
function cancelPay() {
    $("#paypal").addClass("d-none")
    $("#payAllClick").off();
}

function handleSplitTable(tableTop, booking_id) {
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

