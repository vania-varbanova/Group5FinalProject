package models.ui;

import lombok.Data;
import models.BaseUser;

@Data
public class AdminUserUiModel extends BaseUser {
    private String confirmationPassword;
    private String profession;
}
