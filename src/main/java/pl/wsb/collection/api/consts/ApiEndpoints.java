package pl.wsb.collection.api.consts;

public class ApiEndpoints {

    public static final String QUERY_PARAM_ID = "/{id}";

    //Definionts of cons path
    public static final String AUTHENTICATE = "/authenticate";

    public static final String COLLECTION_ENTRY = "/collection_entry";
    public static final String COLLECTION_ENTRY_TYPE = "/type";
    public static final String COLLECTION_ENTRY_GENRE = "/genre";



    public static final String COLLECTION_ENTRY_ID_ACCEPT = ApiEndpoints.QUERY_PARAM_ID + "/accept";
    public static final String COLLECTION_ENTRY_ID_REJECT = ApiEndpoints.QUERY_PARAM_ID + "/reject";


    public static final String COLLECTION_TYPE = "/collection_type";
    public static final String COLLECTION_LIBRARY = "/collection_library";
    public static final String GENRE = "/genre";
    public static final String AUTHOR = "/author";
    public static final String PUBLISHER = "/publisher";
    public static final String USER = "/user";
    public static final String ROLE = "/role";


    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_SEARCH = "search";

}
