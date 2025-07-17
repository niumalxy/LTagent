from flask import Flask, request, jsonify
import sys, os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from service.tokenizer import tokenize

app = Flask(__name__)

@app.route('/check', methods=['GET'])
def check():
    return jsonify({'message': 'success!'})

@app.route('/tokenize', methods=['POST'])
def tokenize_text():
    data = request.json
    text = data.get('text')
    tokens = tokenize(text[1:-1])
    return jsonify({'tokens': tokens})

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=9999)