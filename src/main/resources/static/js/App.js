class Product {
    constructor(id, title, img, content, price, quantity, deleted) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
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
    constructor(id,userName,password,phone,email,deleted) {
        this.id= id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.deleted = deleted;
    }
}