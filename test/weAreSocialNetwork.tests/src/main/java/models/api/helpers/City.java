package models.api.helpers;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class City {
    @SerializedName("id")
    private String cityId;

}
