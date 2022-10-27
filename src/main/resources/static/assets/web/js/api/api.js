import {bookingAPI} from "./booking-api.js";
import {bookingItemAPI} from "./booking-item-api.js";
import {tabletopAPI} from "./tabletop-api.js";
import {itemAPI} from "./item-api.js";
import {categoryAPI} from "./category-api.js";

window.api = {
    bookingItem: bookingItemAPI,
    item: itemAPI,
    tabletop: tabletopAPI,
    booking: bookingAPI,
    category: categoryAPI
}
