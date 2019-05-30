package Services;

import Models.HttpResult;

public interface IHttpClientService<T, V>
{
    HttpResult<T> Get(String url);
    HttpResult<T> Post(String url, V obj);
    HttpResult<T> Put(String url, V obj);
    HttpResult<T> Delete(String url);
}
