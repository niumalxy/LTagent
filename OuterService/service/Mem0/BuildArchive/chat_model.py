from conf.conf import conf
from openai import OpenAI
from service.Mem0.Tools.factory import *
import json

chat_client = OpenAI(
    api_key=conf["chat_model_api_key"],  # 如果您没有配置环境变量，请在此处用您的API Key进行替换
    base_url=conf["chat_model_url"] # 百炼服务的base_url
)

def get_response(messages):
    #print(messages)
    completion = chat_client.chat.completions.create(
        model=conf["chat_model_name"],
        messages=messages,
        tools=tools,
    )
    return completion

def call_with_messages(text):
    messages = [
        {
            "content": text,
            "role": "user",
        }
    ]
    first_response = get_response(messages)
    assistant_output = first_response.choices[0].message
    if assistant_output.content is None:
        assistant_output.content = ""
    messages.append(assistant_output)
    # 如果不需要调用工具，则直接返回最终答案
    if (
        assistant_output.tool_calls == None
    ):  # 如果模型判断无需调用工具，则将assistant的回复直接打印出来，无需进行模型的第二轮调用
        return assistant_output
    # 如果需要调用工具，则进行模型的多轮调用，直到模型判断无需调用工具
    while assistant_output.tool_calls != None:
        # 如果判断需要调用查询天气工具，则运行查询天气工具
        tool_info = {
            "content": "",
            "role": "tool",
            "tool_call_id": assistant_output.tool_calls[0].id,
        }
        argumens = json.loads(assistant_output.tool_calls[0].function.arguments)
        result = tools2function[assistant_output.tool_calls[0].function.name](argumens)
        tool_info["content"] = result
        messages.append(tool_info)
        assistant_output = get_response(messages).choices[0].message
        if assistant_output.content is None:
            assistant_output.content = ""
        messages.append(assistant_output)
    return assistant_output.content