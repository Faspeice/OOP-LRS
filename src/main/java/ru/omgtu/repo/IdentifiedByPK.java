package ru.omgtu.repo;

import java.io.Serializable;

public interface IdentifiedByPK<PK extends Serializable> {

    PK getPK();
}
