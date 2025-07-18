import pymongo

class MongoDB:
    def __init__(self, base_url, db_name):
        self.client = pymongo.MongoClient(base_url)
        self.db = self.client[db_name]
        
    def insert_one(self, data, collection_name):
        collection = self.db[collection_name]
        collection.insert_one(data)

    def find_one(self, query, collection_name):
        collection = self.db[collection_name]
        return collection.find_one(query)

    def find_all(self, query, collection_name):
        collection = self.db[collection_name]
        return collection.find(query)

    def update_one(self, query, data, collection_name):
        collection = self.db[collection_name]
        collection.update_one(query, data, upsert=True)  # upsert=True 表示如果数据不存在，则插入数据

