package com.balamaci.hztest.domain;

import java.io.Serializable;

/**
 * @author Serban Balamaci
 */
public class User implements Serializable {

    private Long id;

    private Integer clientNr;

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientNr(Integer clientNr) {
        this.clientNr = clientNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
