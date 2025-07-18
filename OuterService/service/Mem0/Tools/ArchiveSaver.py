from src.db import mongodb_client

def update_archive(args):
    name = args['name']
    idcard = args['idcard']
    disease = args['disease']
    department = args['department']
    time = args['time']
    mongodb_client.insert_one(
        {
            'name': name,
            'idcard': idcard,
            'disease': disease,
            'department': department,
            'time': time
        },
        "Archive"
    )
    print(f"{idcard} 保存成功！")
    return "保存数据库成功"