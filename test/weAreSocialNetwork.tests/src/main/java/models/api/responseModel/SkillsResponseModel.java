package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.api.helpers.Category;

@Data
public class SkillsResponseModel {
    @SerializedName("category")
    private Category category;
    @SerializedName("skill")
    private String skill;
    @SerializedName("skillId")
    private int skillId;
}

