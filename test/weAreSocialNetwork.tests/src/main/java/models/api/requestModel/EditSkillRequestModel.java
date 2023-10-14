package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
@Data
public class EditSkillRequestModel {
    @SerializedName("skill")
    private String skill;
}
