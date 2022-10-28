package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractBaseModel {
    public String name;
    private Clan clan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + super.getId() + '\'' + ", name='" + name + '\'' + ", clan=" + clan.getName() + '}';
    }
}
