package Models;

import java.util.List;

public class Permission
{
    public int Id;

    public String Name;
    public String Controller;
    public String Actions;

    public void SetActions(List<String> actions)
    {
        String res = "";
        for(int i = 0; i < actions.size(); i++)
        {
            res += (i != actions.size() - 1) ? actions.get(i) + ";" : actions.get(i);
        }

        Actions = res;
    }
}
