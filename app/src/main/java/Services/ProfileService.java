package Services;

import API.EndPoints;
import API.IdentityAPIEndPoints;
import Models.ApplicationUser;
import Models.HttpResult;
import Models.Owner;
import Models.TokenRequest;
import Models.TokenResponse;
import Models.Worker;

public class ProfileService implements IProfileService
{

    @Override
    public HttpResult<TokenResponse> Login(String username, String password) {
        try
        {
            IHttpClientService<TokenResponse, TokenRequest> service = new HttpClientService<TokenResponse, TokenRequest>();
            TokenRequest sendObj = new TokenRequest();
            sendObj.username = username;
            sendObj.password = password;
            HttpResult<TokenResponse> response = service.Post(IdentityAPIEndPoints.LoginEndPoint, sendObj);
            return response;
        }
        catch(Exception ex)
        {
            return new HttpResult<TokenResponse>(ex);
        }
    }

    @Override
    public HttpResult<Owner> Register(Owner owner) {
        return null;
    }

    @Override
    public HttpResult<Worker> Register(Worker worker) {
        return null;
    }

    @Override
    public HttpResult<ApplicationUser> Register(ApplicationUser user) {
        try
        {
            IHttpClientService<ApplicationUser, ApplicationUser> service = new HttpClientService<ApplicationUser, ApplicationUser>();
            HttpResult<ApplicationUser> response = service.Post(IdentityAPIEndPoints.RegisterEndPoint, user);
            return response;
        }
        catch(Exception ex)
        {
            return new HttpResult<ApplicationUser>(ex);
        }
    }
}
