class Product {
    constructor(id, title, img, content, price, quantity, category ,user, deleted) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.user = user;
        this.deleted = deleted;
    }
}

class Category {
    constructor(id, categoryName , parenId) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentId = parenId;
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
class TableTop{
    constructor(id,status,updateAt,createAt,capacity,content) {
        this.id = id;
        this.status = status;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.capacity = capacity;
        this.content = content;
    }
}


