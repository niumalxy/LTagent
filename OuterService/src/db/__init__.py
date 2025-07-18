from .mongodb import MongoDB

"""
MongoDB 连接配置
"""
# 连接本地MongoDB服务器，默认端口为27017
mongodb_client = MongoDB('mongodb://localhost:27017/', 'test')
#查询ChatHistory Collection是否存在，不存在则创建
collection_name ='Archive'
collection_names = mongodb_client.db.list_collection_names()
if collection_name not in collection_names:
    # 创建集合
    collection = mongodb_client.db.create_collection(collection_name)
    print(f"集合 {collection_name} 创建成功")
else:
    print(f"集合 {collection_name} 已经存在")
print("MongoDB connected.")

