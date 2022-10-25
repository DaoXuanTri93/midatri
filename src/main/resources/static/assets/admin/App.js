class Product {
    constructor(id, title, img, content, price, quantity, categoryId, userId, deleted) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.userId = userId;
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