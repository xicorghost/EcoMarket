# productos/app.py
from flask import Flask, jsonify, request

app = Flask(__name__)

productos = [
    {"id": 1, "nombre": "Manzanas", "stock": 100},
    {"id": 2, "nombre": "Leche", "stock": 50}
]

@app.route("/productos", methods=["GET"])
def listar_productos():
    return jsonify(productos)

@app.route("/productos", methods=["POST"])
def agregar_producto():
    nuevo = request.json
    nuevo["id"] = len(productos) + 1
    productos.append(nuevo)
    return jsonify(nuevo), 201

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001)
