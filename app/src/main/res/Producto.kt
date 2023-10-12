class Producto(val nombre: String, var precio: Int, val id: Int) {
    // You can also define default values if needed
    constructor() : this("Producto", 1000, 0)
}

