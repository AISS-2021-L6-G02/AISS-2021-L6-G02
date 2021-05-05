package com.redhat.todo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.redhat.todo.model.Pet;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-06T15:44:10.513+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.openAPIPetstore.base-path:/v2}")
public class PetApiController implements PetApi {

    private final NativeWebRequest request;
    private @Valid boolean completed;

    @org.springframework.beans.factory.annotation.Autowired
    public PetApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
public ResponseEntity<Pet> getPet(String todoId) {
    Pet response = new Pet();
    response.setName("Pet example");
    response.setId(1l);
    return ResponseEntity.ok(response);
}

}
