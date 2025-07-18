from service.Mem0.BuildArchive.chat_model import call_with_messages
from service.Mem0.Prompt.build_archive import UPDATE_ARCHIVE_PROMPT
from src.db import mongodb_client
import json

def build_archive(patient_dialog):
    prompt = UPDATE_ARCHIVE_PROMPT.format(patient_dialog=patient_dialog)
    response = call_with_messages(prompt)
    return response

def get_all_patient_dialog():
    info = mongodb_client.find_all({"status": {"$exists": False}}, "Chat")
    return info

def build_all_archive():
    info = get_all_patient_dialog().to_list()
    print("对话总数量:", len(info))
    for i in info:
        messages = json.loads(i["messages"])[1:]
        build_archive(messages)
        mongodb_client.update_one({"memoryId": i["memoryId"]}, {'$set': {'status': 1}}, "Chat")

