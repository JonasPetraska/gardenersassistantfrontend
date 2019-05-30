package Services;

import Models.ApplicationUser;
import Models.HttpResult;
import Models.Owner;
import Models.TokenResponse;
import Models.Worker;

public interface IProfileService
{
    HttpResult<TokenResponse> Login(String username, String password);
    HttpResult<Owner> Register(Owner owner);
    HttpResult<Worker> Register(Worker worker);
    HttpResult<ApplicationUser> Register(ApplicationUser user);
}
