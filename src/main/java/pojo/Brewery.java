package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Brewery {
    private int id;
    private String name;
    private String breweryType;
    private String street;
    private String state;
    private String postalCode;
    private String country;
    // Type should be considered depending on used DB or contact:
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String phone;
    private String websiteUrl;
    // Type should be considered depending on used DB or contact:
    private Timestamp updatedAt;
}
