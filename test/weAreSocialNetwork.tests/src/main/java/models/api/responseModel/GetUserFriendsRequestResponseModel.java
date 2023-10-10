package models.api.responseModel;

import lombok.Data;
import models.api.helpers.GetUserRequest;

import java.util.List;

@Data
public class GetUserFriendsRequestResponseModel {

    private GetUserRequest getUserRequests;
}
