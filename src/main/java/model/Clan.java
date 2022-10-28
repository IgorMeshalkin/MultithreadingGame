package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clan extends AbstractBaseModel {
    private String name;
    private int gold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public boolean equals(Object o) {
        Clan clan = (Clan) o;
        return this.getId().equals(clan.getId());
    }

    @Override
    public String toString() {
        return "Clan{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", gold=" + gold +
                '}';
    }
}
