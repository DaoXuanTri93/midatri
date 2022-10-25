class Product {
    constructor(id, title, img, content, price, quantity, category_id, user_id, deleted) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
        this.user_id = user_id;
        this.deleted = deleted;
    }
}

class Category {
    constructor(id, category) {
        this.id = id;
        this.category_name = category;
    }
}

class User {
    constructor(id) {
        this.id = id;
    }
}