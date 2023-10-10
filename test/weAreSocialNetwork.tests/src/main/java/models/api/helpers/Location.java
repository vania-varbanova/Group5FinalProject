package models.api.helpers;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Location {
    @SerializedName("id")
    private String locationId;
    @SerializedName("city")
    private City city;


}
