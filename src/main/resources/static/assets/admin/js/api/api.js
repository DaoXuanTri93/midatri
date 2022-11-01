import { userAPI } from "./user-api.js";
import { bookingAPI } from "./booking-api.js";
window.api = {
    booking: bookingAPI,
    user: userAPI
}
