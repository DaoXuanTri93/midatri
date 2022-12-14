
let all;

function renderAllBookingItem() {
    $("#spinner-div").show();
    if (all !== undefined) {
    }

    api.bookingItem.findAll((data) => {
        all = data;
        $("#renderKitchen div").remove();
        $("#renderCooking div").remove();
        renderAllKitchen(all);
        renderAllCooking(all);
        $("#spinner-div").hide();
        // setInterval(renderAllBookingItem, 3000)
    }, (jqXHR) => {

    })
}

// setInterval(() => {
//     $.ajax({
//         url: `${location.origin}/api/bookingItem/hasKitchenItem`,
//         type: "GET",
//         contentType: 'application/json'
//     })
//     .done((data) => {
//         console.log(data)
//         if (data) {
//             $.ajax({
//                 url: `${location.origin}/api/bookingItem/resetKitchenItem`,
//                 type: "POST",
//                 contentType: 'application/json'
//             }).done((data) => {
//                 window.location.reload();
//             })
//         }
//     })
//     .fail((jqXHR) => {
//         fail(jqXHR);
//     })
// }, 10000)

function renderAllKitchen(all) {
    $.each(all, (i, item) => {
        if (item.status === "KITCHEN") {
            renderKitchen(i, item)
        }
    })

}


// function renderAllCookingStart() {
//     api.bookingItem.findAllBookingItemStatusCooking((done) => {
//         $.each(done, (i, item) => {
//             renderCooking(i, item)
//         })
//     }, (jqXHR) => {
//
//     })
// }

function renderAllCooking(all) {
    $.each(all, (i, cooked) => {
        if (cooked.status === "COOKING") {
            renderCooking(i, cooked);
        }
    })
}

function renderKitchen(index, bookingItem) {
    let item = itemMap.get(bookingItem.itemId);
    let nameUser = userMap.get(item.userId).userName
    let result = `
            <div class="billItem" id="${bookingItem.id}_kitchen">
                <div  class="row-list">
                    <div  class="cell-kc-name">
                        <h4 style="color: #f4557e" class="name">
                            ${item.title}<a  class="notify-no-dish" title="G???i th??ng b??o"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- B???i ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div style="color: #f4557e" class="cell-kc-quantity">
                        S??? l?????ng : ${bookingItem.quantity} 
                    </div>
                    
                    <div style="color: #f4557e" class="cell-kc-table">
                        <h4  class="ng-binding">${bookingItem.tableTopTitle}</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
                        <button  class="btn btn-danger" onclick="handleRemoveItem(${index},${bookingItem.id})" title="Th??ng b??o h???t m??n">
                           <i class="fa-regular fa-xmark"></i>
                        </button>
                        <button  class="btn btn-all-order" type="button" onclick="handleCooking(${index},${bookingItem.id})" title="Ch??? bi???n xong t???t c???">
                            <i  class="far fa-angle-double-right"></i>
                        </button>
                    </div>
                </div>
            </div>
    `
    $("#renderKitchen").append(result)
}

function renderCooking(index, bookingItem) {
    let item = itemMap.get(bookingItem.itemId);
    let nameUser = userMap.get(item.userId).userName
    let result = `
            <div class="billItem" id="${bookingItem.id}_cooking">
                <div  class="row-list">
                    <div  class="cell-kc-name">
                        <h4 style="color: forestgreen" class="name">
                            ${item.title}<a  class="notify-no-dish" title="G???i th??ng b??o"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- B???i ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div style="color: forestgreen" class="cell-kc-quantity">
                        S??? l?????ng : ${bookingItem.quantity} 
                    </div>
                    
                    <div  class="cell-kc-table">
                        <h4 style="color: forestgreen" class="ng-binding">${bookingItem.tableTopTitle}</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
<!--                        <button  class="btn btn-one-part" type="button" title="Ch??? bi???n xong m???t">-->
<!--                            <i  class="far fa-angle-right"></i>-->
<!--                        </button>-->
                        <button  class="btn btn-all-order" type="button" style="background-color: forestgreen" onclick="handleCooked(${index},${bookingItem.id})" title="Ch??? bi???n xong t???t c???">
                            <i  class="far fa-angle-double-right"></i>
                        </button>
                    </div>
                </div>
            </div>
    `
    $("#renderCooking").append(result)
}

function handleCooked(index, bookingItemId) {
    all[index].status = "COOKED";
    $(`#${bookingItemId}_cooking`).remove();
    api.bookingItem.updateStatusCooking(bookingItemId, (data) => {
        iziToast.success(
            {
                timeout: 2000,
                position: 'bottomLeft',
                title: 'Xong',
                message: "M??n ??n ho??n th??nh !!!"
            });

    }, (jqXHR) => {

    })


}

function handleCooking(index, bookingItemId) {
    all[index].status = "COOKING";
    $(`#${bookingItemId}_kitchen`).remove();
    let bookingItem = all[index];
    let item = itemMap.get(bookingItem.itemId);
    let nameUser = userMap.get(item.userId).userName;
    api.bookingItem.updateStatusCooking(bookingItemId, (data) => {
        let result = `
            <div class="billItem" id="${bookingItem.id}_cooking">
                <div  class="row-list">
                    <div  class="cell-kc-name">
                        <h4 style="color: forestgreen" class="name">
                            ${item.title}<a  class="notify-no-dish" title="G???i th??ng b??o"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- B???i ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div style="color: forestgreen" class="cell-kc-quantity">
                        S??? l?????ng : ${bookingItem.quantity} 
                    </div>
                    
                    <div style="color: forestgreen" class="cell-kc-table">
                        <h4  class="ng-binding">${bookingItem.tableTopTitle}</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
<!--                        <button  class="btn btn-one-part" type="button" title="Ch??? bi???n xong m???t">-->
<!--                            <i  class="far fa-angle-right"></i>-->
<!--                        </button>-->
                        <button  class="btn btn-all-order" type="button" style="background-color: forestgreen" onclick="handleCooked(${index},${bookingItem.id})" title="Ch??? bi???n xong t???t c???">
                            <i  class="far fa-angle-double-right"></i>
                        </button>
                    </div>
                </div>
            </div>
    `
        $("#renderCooking").append(result)
    }, (jqXHR) => {

    })

}

function handleRemoveItem(index, bookingItemId){
    iziToast.question({
        close: false,
        overlay: true,
        timeout:  false,
        displayMode: 'once',
        titleColor: '#d21e1e',
        backgroundColor: '#fff',
        id: 'question',
        titleLineHeight: '20',
        zindex: 999,
        title: 'Th??ng B??o H???t M??n ?',
        position: 'center',
        buttons: [
            ['<button><b>YES</b></button>', function (instance, toast) {

                api.bookingItem.removeBookingItem(bookingItemId, (data)=>{
                    $(`#${bookingItemId}_kitchen`).remove();
                },(jqXHR) => {

                })
                instance.hide({ transitionOut: 'fadeOut' }, toast, 'button');

            }, true],
            ['<button>NO</button>', function (instance, toast) {
                instance.hide({ transitionOut: 'fadeOut' }, toast, 'button');

            }],
        ]

    });
    // all[index].status = "NEW";

    // api.bookingItem.updateStatusCooking(bookingItemId, (data) => {
    //     iziToast.success(
    //         {
    //             timeout: 2000,
    //             position: 'bottomLeft',
    //             title: 'Xong',
    //             message: "M??n ??n ho??n th??nh !!!"
    //         });
    //
    // }, (jqXHR) => {
    //
    // })
}

renderAllBookingItem();
