
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
		return "Library [id=" + id + ", name=" + name + ", location=" + location + ", availableComputers="
				+ availableComputers + ", isOpen=" + isOpen + ", books=" + books + ", films=" + films
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
