from flask import Flask, request, jsonify
import sys, os
sys.path.append(os.path.dirname(os.path.abspath(__file__)).split("app")[0])
from service.tokenizer import tokenize
from service.Mem0.BuildArchive.build import build_all_archive

app = Flask(__name__)

@app.route('/check', methods=['GET'])
def check():
    return jsonify({'message': 'success!'})

@app.route('/tokenize', methods=['POST'])
def tokenize_text():
    data = request.json
    text = data.get('text')
    tokens = tokenize(text)
    return jsonify({'tokens': tokens})

@app.route('/generate_archive')
def generate_archive():
    build_all_archive()
    return jsonify({'message': 'success!'})

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=9999)