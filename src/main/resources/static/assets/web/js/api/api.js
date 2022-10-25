import { bookingAPI } from "./booking-api.js";
import { tabletopAPI } from "./tabletop-api.js";
import { itemAPI } from "./item-api.js";
window.api = {
    // category: new CategoryAPI(),
     item: itemAPI,
    tabletop:  tabletopAPI,
    booking: bookingAPI,
}
