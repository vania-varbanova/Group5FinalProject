package models.ui;

import lombok.Data;
import models.BaseUser;

@Data
public class UserUiModel extends BaseUser {
    private String confirmationPassword;
    private String profession;
}
