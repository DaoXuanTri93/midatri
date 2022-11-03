let index = "*"
let tabletops;
let itemMap = new Map();
let bookingItemTableTopMap = new Map();
let bookingMap = new Map();
let categoryMap = new Map();
let userMap = new Map();
const getDate = new Date();

let booking_id = 0;
let tableTopId = 0;


class OrderParam {

    constructor(id, userId, grandTotal, status, discount, fullName, phone, email, address, content) {
        this.id = id;
        this.userId = userId;
        this.grandTotal = grandTotal;
        this.status = status;
        this.discount = discount;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.content = content;

    }
}

let onItemClick = () => {
    $(".btn-add-product").click((event) => {
        let itemId = parseInt($(event.currentTarget).attr('data-id'));
        booking(tableTopId, itemId);
    });

};

function fetchUsers() {
    $("#spinner-div").show();
    api.user.findAll((data) => {
        $("#spinner-div").hide();
        $.each(data, (i, user) => {
            userMap.set(user.id, user)
        })
    }, (jqXHR) => {

    })
}

function fetchCategory() {
    $("#spinner-div").show();
    let onCategoryClick = () => {
        $(".render-category").click((event) => {
            let categoryId = parseInt($(event.currentTarget).attr('data-id'));
            handleFilterCategory(categoryId);
        });
    };
    api.category.findAll((categorys) => {
        $("#spinner-div").hide();
        $.each(categorys, (i, category) => {
            categoryMap.set(category.id, category)
            renderCategory(category);
        })
        onCategoryClick();
    }, (jqXHR) => {

    })
}

function fetchBookings() {
    $("#spinner-div").show();
    api.booking.findAllByStatusNotComplete((bookings) => {
        $("#spinner-div").hide();
        $.each(bookings, (i, booking) => {
            bookingMap.set(booking.tableTopId, booking);
            fetchBookingItems(booking.tableTopId, booking.id);
        })
    }, (jqXHR) => {

    })
}

function fetchBookingItems(tableTopId, bookingId) {
    $("#spinner-div").show();
    api.bookingItem.findAllByBooking(bookingId, (bookingItems) => {
        bookingItemTableTopMap.set(tableTopId, bookingItems);
        $("#spinner-div").hide();

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
    $("#spinner-div").show();
    let onTabletopClick = () => {
        $(".tableAndRoom").click((event) => {
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
        $("#spinner-div").hide();
        tabletops = data;
        $.each(data, (i, tabletop) => {
            renderTabletop(tabletop);
        });
        setLocal(tabletops);
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
    $("#spinner-div").show();
    $("#render-product li").empty();
    let TATCA = () => $(".TATCA").on('click', () => {
        itemMap.forEach(item => {
            renderItem(item)
        })

        onItemClick();
    })
    if (itemMap.size > 0) {
        $.each(itemMap.values(), (i, item) => {
            itemMap.set(item.id, item);
            renderItem(item);
        });
        onItemClick();
        TATCA();
        return;
    }

    api.item.findAll((data) => {

        $.each(data, (i, item) => {
            itemMap.set(item.id, item);
            setLocal(i, item);
            renderItem(item);
        });
        onItemClick();
        TATCA();
        $("#spinner-div").hide();
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
        $("#spinner-div").show();
        let createBooking = {
            tabletopId: tabletopId
        }

        api.booking.booking(createBooking, (booking) => {
            $("#spinner-div").hide();
            bookingMap.set(tabletopId, booking);
            newBookingItem.bookingId = booking.id;
            createBookingItem(tabletopId, newBookingItem);
            handleGrandTotal(tableTopId);

        }, (jqXHR) => {

        })
        return;
    }

    let bookingItems = bookingItemTableTopMap.get(tabletopId);
    console.log("tabletopId",tabletopId)
    console.log("bookingItemTableTopMap",bookingItemTableTopMap)
    console.log("bookingItems",bookingItems)
    for (const bookingItem of bookingItems) {
        if (itemId === bookingItem.itemId) {
            let quantity = parseInt($(`#${bookingItem.id} .item-quantity`).text());
            let price = parseInt($(`#${bookingItem.id} .item-price`).text());
            quantity += 1;
            if (isNaN(quantity)) {
                quantity = 1;
            }
            console.log("here", quantity)
            $(`#${bookingItem.id} .item-quantity`).text(quantity)
            $(`#${bookingItem.id} .cell-price`).text(new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(quantity * price))
            api.bookingItem.updateQuantity(bookingItem.id, quantity, (data) => {
                console.log("1", quantity)
                bookingItems.map((item) => {
                    if (bookingItem.id === item.id) {
                        item.quantity = quantity
                    }
                    return item
                })
                bookingItemTableTopMap.set(tableTopId, bookingItems)
                handleGrandTotal(tableTopId)
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
        handleGrandTotal(tableTopId)
    }, (jqXHR) => {

    })
}

function handleFilterCategory(idCategory) {
    $("#render-product li").empty();
    itemMap.forEach((item) => {
        if (idCategory === item.categoryId) {
            renderItem(item)
        }
    })
    onItemClick();
}

function hanldeDeletedBookingItem(bookingIdItem) {
}

let total = 0;


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
                                        ${(bookingItem.status === "COOKED") ? `
        <h4 title="APEROL SPRITZ" style="color: red">${item.title}</h4>` : `
        <h4 title="APEROL SPRITZ">${item.title}</h4>`}
                                            <span class="wrap-icons"></span>
                                            <div class="attr-wrapper">
                                            </div>
                                        </div>
                                        <ul class="comboset-list-item">
        
                                        </ul>
                                        <div class="wrap-note" href="javascript:void(0)">
                                            <button class="btn btn-sm btn-light has-Update"
                                             onclick="handleNoteItem(${bookingItem.id})"
                                                    style="cursor: pointer;">
                                                <i class="far fa-pen"></i>
                                                <span class="note-hint"
                                                id="${item.id}"
                                               
                                                style="cursor: pointer;">
            ${(bookingItem.content != null) ? bookingItem.content.split("\"").join('') : 'Ghi chú món ăn'}</span>
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
                                        <div class="btn-group" dropdown>
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


    total += bookingItem.quantity * item.price
    $("#render-tableTop").prepend(result);

}

function updateQuantityBookingItem(bookingItemId, quantity) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    bookingItems.map((item) => {
        if (bookingItemId === item.id) {
            item.quantity = quantity
        }
        return item
    })
}

function updateStatusBookingItem(bookingItemId, status) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    bookingItems.map((item) => {
        if (bookingItemId === item.id) {
            item.status = status
        }
        return item
    })
}

function updateNoteBookingItem(bookingItemId, note) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    bookingItems.map((item) => {
        if (bookingItemId === item.id) {
            item.content = note
        }
        return item
    })
}

function deletedItemBookingItem(bookingItemId) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    bookingItems.map((item, i) => {
        if (bookingItemId === item.id) {
            bookingItems.splice(i, 1)
        }
        return bookingItems
    })
}

function handleIncreaseQuantity(ItemId) {
    let bookingItems = bookingItemTableTopMap.get(tableTopId);
    for (const bookingItem of bookingItems) {
        if (ItemId === bookingItem.itemId) {
            let quantity = parseInt($(`#${bookingItem.id} .item-quantity`).text());
            let price = parseInt($(`#${bookingItem.id} .item-price`).text());
            quantity += 1;
            $(`#${bookingItem.id} .item-quantity`).text(quantity)
            $(`#${bookingItem.id} .cell-price`).text(new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            }).format(quantity * price))
            api.bookingItem.increaseQuantity(bookingItem.id, quantity, (data) => {
                updateQuantityBookingItem(bookingItem.id, quantity)
                handleGrandTotal(tableTopId);
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
            if (quantity < 1) {
                api.bookingItem.removeBookingItem(bookingItem.id, (data) => {
                    updateQuantityBookingItem(bookingItem.id, quantity)
                    deletedItemBookingItem(bookingItem.id)
                    handleGrandTotal(tableTopId);
                    $(`#render-tableTop #${bookingItem.id}`).remove();

                    // fetchBookings();
                }, (jqXHR) => {

                })
            } else {
                $(`#${bookingItem.id} .item-quantity`).text(quantity);
                $(`#${bookingItem.id} .cell-price`).text(new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                }).format(quantity * price))
                api.bookingItem.decreaseQuantity(bookingItem.id, quantity, (data) => {
                    updateQuantityBookingItem(bookingItem.id, quantity)
                    handleGrandTotal(tableTopId);

                }, (jqXHR) => {

                });
            }

            return;
        }
    }
}

function handleNoteItem(bookingItemId) {
    $("#noteItemUpdate").removeClass('d-none')
    $(`.form-note textarea`).val("");
    let onDoneClick = (bookingItemId) => {
        $("#onDoneClick").off();
        $("#onDoneClick").on('click', () => {
            let bookingItems = bookingItemTableTopMap.get(tableTopId);
            let noteItem = $(`.getNoteInput textarea`).val();
            $(`#${bookingItemId} .note-hint`).text(noteItem);
            api.bookingItem.updateNote(bookingItemId, noteItem, () => {
                updateNoteBookingItem(bookingItemId, noteItem)
                $("#noteItemUpdate").addClass('d-none')
            }, (jqXHR) => {

            })
        })
    }
    onDoneClick(bookingItemId);

}


function handleGrandTotal(tableId) {
    let grandTotal = 0;
    let bookingItem = bookingItemTableTopMap.get(tableId);
    if (bookingItem !== undefined) {
        $.each(bookingItem, (i, item) => {
            grandTotal += (item.quantity * item.price)
        })
    }

    $(".badge-total-quantity").text(new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(grandTotal));
}

function handlePay(tableTopId) {
    let onPayAllClick = (bookingId) => {
        $("#payAllClick").click((event, c = bookingId) => {
            let orderParam = bookingMap.get(tableTopId);
            let orderItemParam = bookingItemTableTopMap.get(tableTopId);
            api.order.create(orderParam, (data) => {
                api.orderItem.create(orderItemParam, (data) => {
                    api.bookingItem.removeBookingItemByBooking(c, (data) => {
                        iziToast.success(
                            {
                                timeout: 2000,
                                position: 'topRight',
                                title: 'OK',
                                message: "Thanh toán thành công !!!"
                            });
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
                }, (jqXHR) => {

                })

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
            return iziToast.warning(
                {
                    timeout: 2000,
                    position: 'topRight',
                    title: 'Warning',
                    message: "Vui lòng chọn Bàn."
                });
        }
        let bookingItems = bookingItemTableTopMap.get(tableTopId);
        if (bookingItems === undefined) {
            return iziToast.warning(
                {
                    timeout: 2000,
                    position: 'topRight',
                    title: 'Warning',
                    message: "Vui lòng chọn món ăn."
                });
        }
        $("#getDateCurrent").attr('placeholder', `${getDate.getDate()}/${getDate.getMonth() + 1}/${getDate.getFullYear()} ${getDate.getHours()}:${getDate.getMinutes()}`)
        let bookingId = bookingMap.get(tableTopId).id;
        $("#paypal").removeClass("d-none")
        $("#renderPay div").remove();
        api.bookingItem.findAllByBooking(bookingId, (bookingItems) => {
                let total = 0;
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
                    total += (bookingItem.quantity * bookingItem.price)
                })

                $("#totalGrand").text(total);
                $("#customerPay").text(total);
                $('#payingAmountTxt').on('input', function () {
                    khachThanhToan = $('#payingAmountTxt').val();
                    $("#moneySuperfluous").text(new Intl.NumberFormat('vi-VI', {
                        style: 'currency',
                        currency: 'VND',
                        maximumFractionDigits: 9
                    }).format(khachThanhToan - $("#customerPay").text()))
                });


            },
            (jqXHR) => {

            })
        onPayAllClick(bookingId);
    }

    function khachThanhToan() {

    }

}

function handleNotification(tableTopId) {
    if (tableTopId === 0) {
        return iziToast.warning(
            {
                timeout: 2000,
                position: 'topRight',
                title: 'Nhắc Nhở',
                message: "Chưa chọn món !!!"
            });
    } else {
        iziToast.success(
            {
                timeout: 2000,
                position: 'bottomRight',
                title: 'OK',
                message: "Bếp đã nhận được thông báo !!!"
            });
        let bookingItems = bookingItemTableTopMap.get(tableTopId);
        api.bookingItem.updateStatus(bookingItems, (data) => {
            // updateStatusBookingItem();

        }, (jqXHR) => {

        })

    }

}

function onCancelClick() {
    $("#noteItemUpdate").addClass('d-none')
    $(`#bookingItemId .note-hint`).text("");

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

function setLocal(itemMap) {
    let itemMapLocal = JSON.stringify(itemMap);
    localStorage.setItem("tabletops", itemMapLocal);

}

function openBookingCooking(tableTopId) {
    if (tableTopId === 0) {
        alert("vui long chon ban")
    } else {

        $("#addCustomer").modal('show')
    }
}

class BookingCustomer {

    constructor(fullName, phone, email, address, content) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.content = content;
    }
}

function savingCustomer(tableTopId) {
    let bookingId = bookingMap.get(tableTopId).id
    let savingCustomer = {
        fullName: $("#customerName").val(),
        phone: $("#telephone").val(),
        email: $("#email").val(),
        address: $("#address").val(),
        content: $("#note").val()
    }
    api.booking.savingCustomer(bookingId, savingCustomer, (done) => {
        iziToast.success(
            {
                timeout: 2000,
                position: 'topRight',
                title: 'OK',
                message: "Thêm khách hàng thành công !!!"
            });
    }, (jqXHR) => {

    })
}


// window.history.pushState(bookingMap,"","http://localhost:8080/orders/bill")
function renderAll() {
    fetchUsers();
    fetchBookings();
    fetchCategory();
    renderAllItems();
    renderAllTableTop();
}

renderAll();

