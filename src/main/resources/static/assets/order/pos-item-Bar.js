

function renderAllItemBar(){
    api.bookingItem.findAllBookingItemStatus((data)=> {
       $.each(data, (i, item) => {
           renderItemCooking(item)
       })
    }, (jqXHR) => {

    })
}
renderAllItemBar();