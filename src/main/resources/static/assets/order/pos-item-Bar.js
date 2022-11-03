let bookingItemAll;
let setLocalKitchen;
let getLocalKitChen;
let all;

function renderAllBookingItem() {
    if (all !== undefined) {
    }

    api.bookingItem.findAll((data) => {
        all = data;
        renderAllKitchen(all);
        renderAllCooking(all);
    }, (jqXHR) => {

    })
}

function renderAllKitchen(all) {
    $.each(all, (i, item) => {
        if (item.status === "KITCHEN") {
            renderKitchen(i, item)
        }
    })
}

// if (getLocalKitChen !== undefined) {
//     console.log("1")
//     $.each(getLocalKitChen, (i, item) => {
//         if (item.status === "KITCHEN") {
//             renderKitchen(i, item)
//         }
//     })
// }
// api.bookingItem.findAllBookingItemStatusKitchen((data) => {
//     bookingItemAll = data
//     setLocalKitchen = JSON.stringify(bookingItemAll);
//     localStorage.setItem("bookingKitChen", setLocalKitchen)
//     getLocalKitChen = JSON.parse(setLocalKitchen)
//     $.each(getLocalKitChen, (i, item) => {
//         renderKitchen(i, item)
//     })
//
// }, (jqXHR) => {
//
// })


function renderAllCookingStart() {
    api.bookingItem.findAllBookingItemStatusCooking((done) => {
        $.each(done, (i, item) => {
            renderCooking(i, item)
        })
    }, (jqXHR) => {

    })
}

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
                        <h4  class="name">
                            ${item.title}<a  class="notify-no-dish" title="Gửi thông báo"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- Bởi ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div  class="cell-kc-quantity">
                        ${bookingItem.quantity} 
                    </div>
                    
                    <div  class="cell-kc-table">
                        <h4  class="ng-binding">Bàn 1111</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
<!--                        <button  class="btn btn-one-part" type="button" title="Chế biến xong một">-->
<!--                            <i  class="far fa-angle-right"></i>-->
<!--                        </button>-->
                        <button  class="btn btn-all-order" type="button" onclick="handleCooking(${index},${bookingItem.id})" title="Chế biến xong tất cả">
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
                        <h4  class="name">
                            ${item.title}<a  class="notify-no-dish" title="Gửi thông báo"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- Bởi ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div  class="cell-kc-quantity">
                        ${bookingItem.quantity} 
                    </div>
                    
                    <div  class="cell-kc-table">
                        <h4  class="ng-binding">Bàn 1111</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
<!--                        <button  class="btn btn-one-part" type="button" title="Chế biến xong một">-->
<!--                            <i  class="far fa-angle-right"></i>-->
<!--                        </button>-->
                        <button  class="btn btn-all-order" type="button" style="background-color: forestgreen" onclick="handleCooked(${index},${bookingItem.id})" title="Chế biến xong tất cả">
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
                position: 'topRight',
                title: 'OK',
                message: "Món ăn hoàn thành !!!"
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
        iziToast.success(
            {
                timeout: 2000,
                position: 'topRight',
                title: 'OK',
                message: "Món ăn đang nấu !!!"
            });
        let result = `
            <div class="billItem" id="${bookingItem.id}_cooking">
                <div  class="row-list">
                    <div  class="cell-kc-name">
                        <h4  class="name">
                            ${item.title}<a  class="notify-no-dish" title="Gửi thông báo"><i  class="fas fa-volume-high"></i></a>
                        </h4>
                        <div  class="topping-note">
                            
                        </div>
                        <span  class="code-time">${moment(bookingItem.createAt).format('L')} --- Bởi ${nameUser} </span>
                    </div>
                    <div  class="cell-kc-favorite">
                        
                    </div>
                    <div  class="cell-kc-quantity">
                        ${bookingItem.quantity} 
                    </div>
                    
                    <div  class="cell-kc-table">
                        <h4  class="ng-binding">Bàn 1111</h4>
                        <span  class="time">${moment(bookingItem.createAt).format('h:mm:ss a')}</span>
                    </div>
                    
                    <div  class="cell-kc-button">
<!--                        <button  class="btn btn-one-part" type="button" title="Chế biến xong một">-->
<!--                            <i  class="far fa-angle-right"></i>-->
<!--                        </button>-->
                        <button  class="btn btn-all-order" type="button" style="background-color: forestgreen" onclick="handleCooked(${index},${bookingItem.id})" title="Chế biến xong tất cả">
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

renderAllBookingItem();
// renderAllKitchen();
// renderAllCookingStart();

