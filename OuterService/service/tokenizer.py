# Load model directly
from transformers import AutoTokenizer
tokenizer = AutoTokenizer.from_pretrained("Qwen/Qwen3-Reranker-0.6B", cache_dir="./model")
print("tokenizer loaded.")

def tokenize(text):
    tokens = tokenizer.encode(text)
    return tokens
