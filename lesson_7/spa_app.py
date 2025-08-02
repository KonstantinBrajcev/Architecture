from flask import Flask, render_template, jsonify

app = Flask(__name__)

# Моковые данные
products = [
    {"id": 1, "name": "Ноутбук", "price": 50000,
        "description": "Мощный игровой ноутбук", "category": "Электроника"},
    {"id": 2, "name": "Смартфон", "price": 30000,
        "description": "Флагманский смартфон", "category": "Электроника"},
    {"id": 3, "name": "Наушники", "price": 5000,
        "description": "Беспроводные наушники", "category": "Аксессуары"},
    {"id": 4, "name": "Клавиатура", "price": 2500,
        "description": "Механическая клавиатура", "category": "Аксессуары"}
]


@app.route('/')
def index():
    return render_template('spa_index.html')


@app.route('/api/products')
def api_products():
    return jsonify(products)


@app.route('/api/product/<int:id>')
def api_product(id):
    product = next((p for p in products if p['id'] == id), None)
    if product:
        return jsonify(product)
    return jsonify({"error": "Product not found"}), 404


if __name__ == '__main__':
    app.run(debug=True)
