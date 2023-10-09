package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SkillsRequestModel {
    @SerializedName("categoryId")
    private String professionID;
    @SerializedName("skill")
    private String skill;
}
