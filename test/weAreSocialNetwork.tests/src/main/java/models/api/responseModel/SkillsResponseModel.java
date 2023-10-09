package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SkillsResponseModel {
    @SerializedName("skillId")
    private String skillId;
    @SerializedName("skill")
    private String skill;
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("categoryName")
    private String categoryName;

}
