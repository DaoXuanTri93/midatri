// class Booking {
//     constructor(id, tableTop, user) {
//         this.id = id;
//         this.tableTop = tableTop;
//         this.user = user;
//     }
// }
//
// class BookingItemCreate {
//     constructor(id, discount, grandTotal, price, quantity, booking_id, item_id) {
//         this.id = id;
//         this.discount = discount;
//         this.grandTotal = grandTotal;
//         this.price = price;
//         this.quantity = quantity;
//         this.booking_id = booking_id;
//         this.item_id = item_id;
//     }
// }
//
// let tabletops;
// let items;
// let bookings;
// let bookingItems;
//
//
// let booking_id = 0;
// let tableTopId = 0;
// let arrayBooking = [];
//
// function renderTabletop(tabletop) {
//     let result = `
//                          <li class="tableAndRoom using" id="${tabletop.id}" style="text-align: center;" onclick="handelTableTop(${tabletop.id})">
//                             <div class="tableroom-actions"></div>
//                                 <a container="body" placement="right top" class="using">
//                                     <div class="table-room">
//                                         <span></span>
//                                             </div>
//                                                 <div class="product-info">
//                                                 <span class="product-name"> ${tabletop.id} </span>
//                                                     <div class="wrap-note" href="javascript:void(0)">
//                                                     <label>
//                                                         <button class="btn-icon">
//                                                             <span class="note-hint note-hint-input">Nhập ghi chú...</span>
//                                                         </button>
//                                                     </label>
//                                             </div>
//                                     </div>
//                                 </a>
//                         </li>
//                      `
//     $("#render-table").append(result);
//     $('#' + tabletop.id).on('click', () => {
//         tableTopId = tabletop.id;
//         $('#link-menu').trigger('click');
//         $('#table-room').text('Bàn ' + tabletop.id)
//     });
// }
//
// function renderAllTableTop() {
//
//     if (tabletops !== undefined) {
//         for (let tabletop of tabletops) {
//             renderTabletop(tabletop);
//         }
//         return;
//     }
//
//     api.tabletop.findAll((data) => {
//             tabletops = data;
//             $.each(data, (i, item) => {
//                 renderTabletop(item);
//             });
//         },
//         error => {
//
//         });
//
//     // $.ajax({
//     //     headers: {
//     //         "Accept": "application/json",
//     //         "Content-type": "application/json"
//     //     },
//     //     type: "GET",
//     //     url: "http://localhost:8080/api/table-top"
//     // }).done((data) => {
//     //     $.each(data, (i, item) => {
//     //         let str = `
//     //                      <li class="tableAndRoom using" id="${item.id}" style="text-align: center;" onclick="handelTableTop(${item.id})">
//     //                         <div class="tableroom-actions"></div>
//     //                             <a container="body" placement="right top" class="using">
//     //                                 <div class="table-room">
//     //                                     <span></span>
//     //                                         </div>
//     //                                             <div class="product-info">
//     //                                             <span class="product-name"> ${item.id} </span>
//     //                                                 <div class="wrap-note" href="javascript:void(0)">
//     //                                                 <label>
//     //                                                     <button class="btn-icon">
//     //                                                         <span class="note-hint note-hint-input">Nhập ghi chú...</span>
//     //                                                     </button>
//     //                                                 </label>
//     //                                         </div>
//     //                                 </div>
//     //                             </a>
//     //                     </li>
//     //                  `
//     //         $("#render-table").append(str);
//     //         $('#' + item.id).on('click', () => {
//     //             tableTopId = item.id
//     //             $('#link-menu').trigger('click');
//     //             $('#table-room').text('Bàn ' + tableTopId)
//     //         })
//     //     })
//     // }).fail(error => {
//     //
//     // })
// }
//
// function renderCategory() {
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "GET",
//         url: "http://localhost:8080/api/category"
//     }).done((data) => {
//
//         $.each(data, (i, item) => {
//             let str = `
//                     <div class="swiper-slide product-category-item-parent render-category" data-id="${item.id}"
//                     onclick="handleFilterCategory(${item.id})">
//                         <a class="btn btn-text-gray" >${item.categoryName}</a>
//                     </div>
//                 `
//             $("#renderCategory").append(str);
//
//         })
//         handelRemoveEvent();
//
//     }).fail(error => {
//
//     })
// }
//
// function renderItem(item) {
//     let result = `
//                         <li data-id="${item.id}" onclick="handleEventOnClick(${item.id})" class="btn-add-product">
//                         <a title=${item.title}>
//                             <div class="product-img" >
//                                 <img src=${item.img}>
//                                 <div class="product-price">${item.price}</div>
//                             </div>
//                             <div class="product-info">
//                                 <div class="product-name">${item.title}</div>
//                             </div>
//                         </a>
//                         </li>
//                 `
//     $("#render-product").prepend(result);
// }
//
// function renderAllItems() {
//     $("#render-product li").empty();
//     if (items !== undefined) {
//         for (let item of items) {
//             renderItem(item);
//         }
//         handelRemoveEvent();
//         return;
//     }
//
//     api.item.findAll((data) => {
//             items = data;
//             $.each(data, (i, item) => {
//                 renderItem(item);
//             });
//             handelRemoveEvent();
//         },
//         error => {
//
//         });
//
//
//     // $.ajax({
//     //     headers: {
//     //         "Accept": "application/json",
//     //         "Content-type": "application/json"
//     //     },
//     //     type: "GET",
//     //     url: "http://localhost:8080/api/item"
//     // }).done((data) => {
//     //     $.each(data, (i, item) => {
//     //         let str = `
//     //                     <li data-id="${item.id}" onclick="handleEventOnClick(${item.id})" class="btn-add-product">
//     //                     <a title=${item.title}>
//     //                         <div class="product-img" >
//     //                             <img src=${item.img}>
//     //                             <div class="product-price">${item.price}</div>
//     //                         </div>
//     //                         <div class="product-info">
//     //                             <div class="product-name">${item.title}</div>
//     //                         </div>
//     //                     </a>
//     //                     </li>
//     //             `
//     //         $("#render-product").prepend(str);
//     //     })
//     //     handelRemoveEvent();
//     //     // handelAll();
//     // }).fail(error => {
//     //
//     // })
// }
//
// function selectedItem(itemId) {
//     if (tableTopId == 0) {
//         return alert("vui long chon ban")
//     }
//     // let itemElement = $("#" + itemId);
//
//
//
// }
//
// function renderCategoryItem() {
//     $("#render-product li").empty();
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "GET",
//         url: "http://localhost:8080/api/item"
//     }).done((data) => {
//         $.each(data, (i, item) => {
//             let str = `
//                         <li data-id="${item.id}" class="btn-add-product">
//                         <a title=${item.title}>
//                             <div class="product-img" onclick="handleEventOnClick(${item.id})">
//                                 <img src=${item.img}>
//                                 <div class="product-price">${item.price}</div>
//                             </div>
//                             <div class="product-info">
//                                 <div class="product-name">${item.title}</div>
//                             </div>
//                         </a>
//                         </li>
//                 `
//             $("#render-product").prepend(str);
//         })
//         handelRemoveEvent();
//         // handelAll();
//     }).fail(error => {
//
//     })
// }
//
// function handleFilterCategory(categoryId) {
//     $("#render-product li").empty();
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "GET",
//         url: "http://localhost:8080/api/item/category/" + categoryId
//     }).done((data) => {
//         $.each(data, (i, item) => {
//             let str = `
//                         <li data-id="${item.id}" class="btn-add-product">
//                         <a title=${item.title}>
//                             <div class="product-img" onclick="handleEventOnClick(${item.id})">
//                                 <img src=${item.img}>
//                                 <div class="product-price">${item.price}</div>
//                             </div>
//                             <div class="product-info">
//                                 <div class="product-name">${item.title}</div>
//                             </div>
//                         </a>
//                         </li>
//                 `
//             $("#render-product").prepend(str);
//         })
//     }).fail(error => {
//
//     }).always(function () {
//         // $("#render-product").removeClass('d-none');
//     });
// }
//
// function handleEventOnClick(item_id) {
//
//     if (tableTopId == 0) {
//         return alert("vui long chon ban")
//     }
//     let booking = new Booking();
//     booking.tableTop = {
//         id: tableTopId
//     };
//     booking.user = {
//         id: 1
//     };
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "POST",
//         url: "http://localhost:8080/api/booking/create/" + tableTopId,
//         data: JSON.stringify(booking)
//     })
//         .done((booking) => {
//             let bookingItem = new BookingItemCreate();
//             bookingItem.booking_id = booking.id
//             bookingItem.item_id = item_id
//             $.ajax({
//                 headers: {
//                     "Accept": "application/json",
//                     "Content-type": "application/json"
//                 },
//                 type: "POST",
//                 url: "http://localhost:8080/api/bookingItem/create",
//                 data: JSON.stringify(bookingItem)
//             })
//                 .done(() => {
//                     // $("#render-tableTop div").empty();
//                     $.ajax({
//                         headers: {
//                             "Accept": "application/json",
//                             "Content-type": "application/json"
//                         },
//                         type: "GET",
//                         url: "http://localhost:8080/api/booking/" + tableTopId
//                     }).done((data) => {
//                         console.log(data)
//                         $.each(data, (i, item) => {
//                             booking_id = item.id;
//                             $.ajax({
//                                 headers: {
//                                     "Accept": "application/json",
//                                     "Content-type": "application/json"
//                                 },
//                                 type: "GET",
//                                 url: "http://localhost:8080/api/bookingItem/" + booking_id
//                             })
//                                 .done((item) => {
//
//                                     let getItem = item[item.length - 1];
//                                     let str = `
//                                     <div class="product-cart-item" id="${getItem.id}">
//                                         <kv-cashier-cart-item class="row-list row-list-active active">
//                                             <div class="row-list-content">
//                                                 <div class="cell-action">
//                                                     <a class="btn-icon btn-trash" href="javascript:void(0);"
//                                                        title="Xóa món">
//                                                         <i class="fa-regular fa-trash-can"
//                                                         onclick="hanldeDeletedBookingItem(${getItem.id})"
//                                                         ></i>
//                                                     </a>
//                                                 </div>
//                                                 <div class="cell-order"> ${getItem.id}
//                                                     <div>
//                                                         <button class="btn-icon" type="button"
//                                                                 title="Món ưu tiên">
//                                                             <i class="fa-star fa-regular"></i>
//                                                         </button>
//                                                     </div>
//                                                 </div>
//                                                 <div class="row-product">
//                                                     <div class="cell-name full">
//                                                         <div class="wrap-name">
//                                                             <h4 title="APEROL SPRITZ">${getItem.item.title}</h4>
//                                                             <span class="wrap-icons"></span>
//                                                             <div class="attr-wrapper">
//                                                             </div>
//                                                         </div>
//                                                         <ul class="comboset-list-item">
//
//                                                         </ul>
//                                                         <div class="wrap-note" href="javascript:void(0)">
//                                                             <button class="btn btn-sm btn-light has-Update"
//                                                                     style="cursor: pointer;">
//                                                                 <i class="far fa-pen"></i>
//                                                                 <span class="note-hint"
//                                                                       style="cursor: pointer;">Ghi chú món</span>
//                                                             </button>
//                                                         </div>
//                                                         <div class="list-topping">
//
//                                                         </div>
//                                                     </div>
//                                                     <div class="cell-quatity">
//                                                         <div class="cell-quantity-inner">
//                                                             <button class="btn-icon down" type="button"
//                                                                     title="Giảm số lượng món"
//                                                                     onclick="handleReduceQuantity(${getItem.id})">
//                                                                 <i class="fa-regular fa-minus"></i>
//                                                             </button>
//                                                             <button class="form-control form-control-sm item-quantity" >
//                                                                 ${getItem.quantity}
//                                                             </button>
//                                                             <button class="btn-icon up" type="button"
//                                                                     title="Tăng số lượng món"
//                                                                     onclick="handleIncreaseQuantity(${getItem.id})">
//                                                                 <i class="fa-regular fa-plus"></i>
//                                                             </button>
//                                                         </div>
//                                                     </div>
//                                                     <div class="cell-change-price">
//                                                         <div class="popup-anchor">
//                                                             <button class="form-control form-control-sm">
//                                                             ${getItem.item.price}
//                                                             </button>
//                                                         </div>
//                                                     </div>
//                                                     <div class="cell-price">${getItem.item.price * getItem.quantity}</div>
//                                                     <div class="cell-actions">
//                                                         <div class="btn-group" dropdown="">
//                                                             <button class="dropdown-toggle" type="button"
//                                                                     title="Thêm dòng mới">
//                                                                 <i class="fas fa-plus"></i>
//                                                             </button>
//                                                         </div>
//                                                     </div>
//                                                 </div>
//                                             </div>
//                                         </kv-cashier-cart-item>
//                                     </div>
//                                             `;
//
//                                     // renderBookingItem(item);
//                                     $("#render-tableTop").prepend(str);
//                                     // console.log(arrayBooking)
//
//                                 })
//                         })
//
//                     })
//
//                 })
//         })
//
// }
//
// function hanldeDeletedBookingItem(bookingIdItem) {
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "POST",
//         url: "http://localhost:8080/api/bookingItem/" + bookingIdItem
//     })
//         .done(() => {
//             // $("#render-tableTop div").empty();
//             $.ajax({
//                 headers: {
//                     "Accept": "application/json",
//                     "Content-type": "application/json"
//                 },
//                 type: "GET",
//                 url: "http://localhost:8080/api/booking/" + tableTopId
//             }).done((data) => {
//                 $.each(data, (i, item) => {
//                     booking_id = item.id;
//                     $.ajax({
//                         headers: {
//                             "Accept": "application/json",
//                             "Content-type": "application/json"
//                         },
//                         type: "GET",
//                         url: "http://localhost:8080/api/bookingItem/" + booking_id
//                     })
//                         .done((data) => {
//                             renderBookingItem(data);
//                         })
//                 })
//
//             })
//
//         })
//
// }
//
// function renderBookingItem(data) {
//     let totalBookingItem = 0;
//     $.each(data, (i, item) => {
//         let str = `
//                      <div class="product-cart-item" id="${item.id}">
//                         <kv-cashier-cart-item class="row-list row-list-active active">
//                             <div class="row-list-content">
//                                 <div class="cell-action">
//                                     <a class="btn-icon btn-trash" href="javascript:void(0);"
//                                        title="Xóa món">
//                                         <i class="fa-regular fa-trash-can" onclick="hanldeDeletedBookingItem(${item.id})"></i>
//                                     </a>
//                                 </div>
//                                 <div class="cell-order"> ${item.id}
//                                     <div>
//                                         <button class="btn-icon" type="button"
//                                                 title="Món ưu tiên">
//                                             <i class="fa-star fa-regular"></i>
//                                         </button>
//                                     </div>
//                                 </div>
//                                 <div class="row-product">
//                                     <div class="cell-name full">
//                                         <div class="wrap-name">
//                                             <h4 title="APEROL SPRITZ">${item.item.title}</h4>
//                                             <span class="wrap-icons"></span>
//                                             <div class="attr-wrapper">
//                                             </div>
//                                         </div>
//                                         <ul class="comboset-list-item">
//        
//                                         </ul>
//                                         <div class="wrap-note" href="javascript:void(0)">
//                                             <button class="btn btn-sm btn-light has-Update"
//                                                     style="cursor: pointer;">
//                                                 <i class="far fa-pen"></i>
//                                                 <span class="note-hint"
//                                                       style="cursor: pointer;">Ghi chú món</span>
//                                             </button>
//                                         </div>
//                                         <div class="list-topping">
//        
//                                         </div>
//                                     </div>
//                                     <div class="cell-quatity">
//                                         <div class="cell-quantity-inner">
//                                             <button class="btn-icon down" type="button"
//                                                     title="Giảm số lượng món"
//                                                     onclick="handleReduceQuantity(${item.id})">
//                                                 <i class="fa-regular fa-minus"></i>
//                                             </button>
//                                             <button class="form-control form-control-sm item-quantity" >
//                                                 ${item.quantity}
//                                             </button>
//                                             <button class="btn-icon up" type="button"
//                                                     title="Tăng số lượng món"
//                                                     onclick="handleIncreaseQuantity(${item.id})">
//                                                 <i class="fa-regular fa-plus"></i>
//                                             </button>
//                                         </div>
//                                     </div>
//                                     <div class="cell-change-price">
//                                         <div class="popup-anchor">
//                                             <button class="form-control form-control-sm">
//                                                 ${item.item.price}
//                                             </button>
//                                         </div>
//                                     </div>
//                                     <div class="cell-price">${item.grandTotal}</div>
//                                     <div class="cell-actions">
//                                         <div class="btn-group" dropdown="">
//                                             <button class="dropdown-toggle" type="button"
//                                                     title="Thêm dòng mới">
//                                                 <i class="fas fa-plus"></i>
//                                             </button>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </kv-cashier-cart-item>
//                      </div>
//                     
//                 `;
//         $("#render-tableTop").prepend(str);
//         totalBookingItem += item.grandTotal;
//     })
//     $('.total-price').text(totalBookingItem);
//
// }
//
// function handleIncreaseQuantity(bookingItem_id) {
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "PATCH",
//         url: `http://localhost:8080/api/bookingItem/${bookingItem_id}/increaseQuantity`
//     })
//         .done((bookingItem) => {
//             handelTableTop(tableTopId)
//             // let str = handelTableTop(tableTopId);
//             // let current = $("#item.id");
//             // current.replaceWith(str)
//         })
// }
//
// function handleReduceQuantity(bookingItem_id) {
//
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "PATCH",
//         url: `http://localhost:8080/api/bookingItem/${bookingItem_id}/reduceQuantity`
//     })
//         .done(() => {
//             handelTableTop(tableTopId)
//         })
// }
//
// function handelTableTop(tableTopId) {
//     $("#render-tableTop div").empty();
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "GET",
//         url: "http://localhost:8080/api/booking?tabletopId=" + tableTopId
//     })
//         .done((data) => {
//             booking_id = data[0].id;
//             $.ajax({
//                 headers: {
//                     "Accept": "application/json",
//                     "Content-type": "application/json"
//                 },
//                 type: "GET",
//                 url: "http://localhost:8080/api/bookingItem/" + booking_id
//             })
//                 .done((data) => {
//                     renderBookingItem(data)
//                 })
//         })
//
//
// }
//
// function handelPay(tableTop, booking_id) {
//     if (tableTop == 0 || booking_id == 0) {
//         alert("Chưa có ĐƠN HÀNG thanh toán")
//     } else {
//         $(".table-payment").text(`Bàn - ${tableTop}`);
//         $("#paypal").removeClass("d-none")
//         $("#renderPay div").empty();
//         $.ajax({
//             headers: {
//                 "Accept": "application/json",
//                 "Content-type": "application/json"
//             },
//             type: "GET",
//             url: "http://localhost:8080/api/bookingItem/" + booking_id
//         })
//             .done((data) => {
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
//                 $('#totalGrand').text(totalGrand);
//                 let total = $('#totalGrand').text();
//                 let priceSale = $('#priceSale').val();
//                 $('#customerPay').text(total - priceSale);
//
//                 let payingAmountTxt = $('#payingAmountTxt').val();
//                 let customerPay = $('#customerPay').text();
//                 $('#moneySuperfluous').text(payingAmountTxt - customerPay);
//
//
//             })
//     }
//
// }
//
//
// function cancelPay() {
//     $("#paypal").addClass("d-none")
// }
//
// function handlePayAll(booking_id) {
//     $.ajax({
//         headers: {
//             "Accept": "application/json",
//             "Content-type": "application/json"
//         },
//         type: "DELETE",
//         url: "http://localhost:8080/api/bookingItem/deleted/" + booking_id
//     })
//         .done((data) => {
//             // alert("Thanh Toan Thanh Cong")
//             $("#renderPay div").empty();
//             cancelPay()
//             handelTableTop(tableTopId)
//         })
// }
//
// function handelSplitTable(tableTop, booking_id) {
//     if (tableTop == 0 || booking_id == 0) {
//         alert("Chưa chon ban can tach")
//     } else {
//         $("#splitTable").removeClass('d-none')
//         $("#tableSplit").text(`Bàn - ${tableTop}`)
//
//     }
// }
//
// function closeSplitTable() {
//     $("#splitTable").addClass('d-none')
// }
//
// function handelRemoveEvent() {
//     $(".btn-add-product").off();
// }
//
// function renderAll() {
//     renderAllItems();
//     renderCategory()
//     renderAllTableTop();
//
// }
//
// renderAll();
//
