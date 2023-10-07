package models.api.helpers;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Category {
    @SerializedName("id")
    private int categoryId;
    @SerializedName("name")
    private String name;
}
