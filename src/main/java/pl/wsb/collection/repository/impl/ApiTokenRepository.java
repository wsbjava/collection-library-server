package pl.wsb.collection.repository.impl;

import com.google.protobuf.Api;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.plexus.util.StringUtils;
import pl.wsb.collection.exceptions.ApiException;
import pl.wsb.collection.model.ApiToken;
import pl.wsb.collection.model.UserAccount;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class ApiTokenRepository extends AbstractRepository<ApiToken, Integer> {

    @Override
    protected Class<ApiToken> getPersistentClass() {
        return ApiToken.class;
    }


    /**
     *
     * Method that allow to get ApiToken object by requested Token
     *
     * @param token - requested Token
     *
     * @return ApiToken
     *
     */
    public static ApiToken findByAccessToken(String token){
        if(StringUtils.isBlank(token)){
            return null;
        }

        //Pobieramy budulca zapytań
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        //Tworzymy instancje zapytania na podstanie budulca zapytań i klasy z modelu
        CriteriaQuery<ApiToken> criteriaQuery = criteriaBuilder.createQuery(ApiToken.class);
        Root<ApiToken> tokens = criteriaQuery.from(ApiToken.class);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(tokens.<String>get("accessToken")),
                        token.toLowerCase()
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                        tokens.<Date>get("validTo"),
                        new Date()
                )
        );


        List<ApiToken> results = EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();

        if(results == null){
            return null;
        }

        if(results.isEmpty()){
            return null;
        }

        return  results.get(0);

    }

    /**
     *
     * This method is used to generate new Token for the user
     *
     * @param userAccount - user for which it`s to be generated Token
     *
     * @return ApiToken
     *
     * @throws ApiException
     *
     */
    public ApiToken generateApiToken(UserAccount userAccount) throws ApiException{
        if(userAccount == null){
            throw new ApiException("Undefined user account ...");
        }

        ApiToken apiToken = new ApiToken();
        apiToken.setCreated(new Date());
        apiToken.setModified(new Date());
        apiToken.setAccessToken(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(255)
                )
        );
        apiToken.setRefreshToken(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(255)
                )
        );
        apiToken.setUserAccount(userAccount);
        apiToken.setValidTo(
                DateUtils.addMinutes(new Date(), 30)
        );
        EntityManagerHelper.startTransaction();
        this.merge(apiToken);
        EntityManagerHelper.commitTransaction();
        return apiToken;

    }
}
