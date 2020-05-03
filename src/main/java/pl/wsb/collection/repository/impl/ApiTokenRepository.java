package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.ApiToken;
import pl.wsb.collection.repository.AbstractRepository;

public class ApiTokenRepository extends AbstractRepository<ApiToken, Integer> {

    @Override
    protected Class<ApiToken> getPersistentClass() {
        return ApiToken.class;
    }
}
