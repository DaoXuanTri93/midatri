import { userAPI } from "./user-api.js";
import { bookingAPI } from "./booking-api.js";
window.api = {
    // category: new CategoryAPI(),
    // size: new SizeAPI(),
    // color: new ColorAPI(),
    booking: bookingAPI,
    user: userAPI
}
