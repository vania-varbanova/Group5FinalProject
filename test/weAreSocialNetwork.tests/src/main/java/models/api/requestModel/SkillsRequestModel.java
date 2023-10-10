package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.api.helpers.Category;

@Data
public class SkillsRequestModel {
    @SerializedName("category")
    private Category category;
    @SerializedName("skill")
    private String skill;
}
