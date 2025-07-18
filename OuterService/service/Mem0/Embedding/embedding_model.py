from conf.conf import conf
from openai import OpenAI

embedding_client = OpenAI(
    api_key=conf["embedding_model_api_key"],  # 如果您没有配置环境变量，请在此处用您的API Key进行替换
    base_url=conf["embedding_model_url"] # 百炼服务的base_url
)

def embed(text):
    completion = embedding_client.embeddings.create(
        model=conf["embedding_model_name"],
        input=text,
        dimensions=conf["embedding_dimensions"], # 指定向量维度（仅 text-embedding-v3及 text-embedding-v4支持该参数）
        encoding_format="float"
    )
    return completion.model_dump_json()