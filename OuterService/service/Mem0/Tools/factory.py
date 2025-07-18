"""
在这里提供openai所需的所有工具的描述
"""

from service.Mem0.Tools.ArchiveSaver import update_archive

tools = [
    {   "type": "function",
        "function": {
            "name": "update_archive",
            "description": "用于将患者的病情报告存入数据库。",
            "parameters": {
                "type": "object",
                "properties": {
                    "name": {
                        "type": "string",
                        "description": "患者姓名。",
                    },
                    "idcard": {
                        "type": "string",
                        "description": "患者身份证。",
                    },
                    "disease": {
                        "type": "string",
                        "description": "患者病情。",
                    },
                    "department": {
                        "type": "string",
                        "description": "患者挂号部门。",
                    },
                    "time": {
                        "type": "string",
                        "description": "患者问诊时间。",
                    },
                },
                "required": ["name", "idcard", "disease", "department", "time"],
            },
        },
    },
]

tools2function = {
    "update_archive": update_archive,
}