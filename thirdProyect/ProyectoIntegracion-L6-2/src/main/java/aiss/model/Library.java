
package aiss.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "location",
    "availableComputers",
    "isOpen",
    "books",
    "films"
})
@Generated("jsonschema2pojo")
public class Library {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("location")
    private String location;
    @JsonProperty("availableComputers")
    private Integer availableComputers;
    @JsonProperty("isOpen")
    private Boolean isOpen;
    @JsonProperty("books")
    private List<Book> books = null;
    @JsonProperty("films")
    private Object films;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("availableComputers")
    public Integer getAvailableComputers() {
        return availableComputers;
    }

    @JsonProperty("availableComputers")
    public void setAvailableComputers(Integer availableComputers) {
        this.availableComputers = availableComputers;
    }

    @JsonProperty("isOpen")
    public Boolean getIsOpen() {
        return isOpen;
    }

    @JsonProperty("isOpen")
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    @JsonProperty("books")
    public List<Book> getBooks() {
        return books;
    }

    @JsonProperty("books")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @JsonProperty("films")
    public Object getFilms() {
        return films;
    }

    @JsonProperty("films")
    public void setFilms(Object films) {
        this.films = films;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Library.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("availableComputers");
        sb.append('=');
        sb.append(((this.availableComputers == null)?"<null>":this.availableComputers));
        sb.append(',');
        sb.append("isOpen");
        sb.append('=');
        sb.append(((this.isOpen == null)?"<null>":this.isOpen));
        sb.append(',');
        sb.append("books");
        sb.append('=');
        sb.append(((this.books == null)?"<null>":this.books));
        sb.append(',');
        sb.append("films");
        sb.append('=');
        sb.append(((this.films == null)?"<null>":this.films));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
