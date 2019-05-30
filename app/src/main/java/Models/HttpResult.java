package Models;

import java.util.ArrayList;
import java.util.List;

public class HttpResult<T>
{
    public HttpResult(Exception ex)
    {
        innerException = ex;
        succes = false;
        isException = true;
    }

    public HttpResult(T res)
    {
        succes = true;
        result = res;
    }

    public HttpResult(String error)
    {
        errors = new ArrayList<String>();
        errors.add(error);
        succes = false;
    }

    public T result;
    public boolean succes;
    public boolean isException;
    public List<String> errors;
    public Exception innerException;
}
