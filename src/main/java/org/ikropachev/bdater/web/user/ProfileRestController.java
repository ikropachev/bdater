package org.ikropachev.bdater.web.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ikropachev.bdater.View;
import org.ikropachev.bdater.model.User;
import org.ikropachev.bdater.to.UserTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.ikropachev.bdater.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for profiles")
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping
    @ApiOperation(value = "Get the authenticated user information")
    public User get() {
        return super.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete the authenticated user")
    public void delete() {
        super.delete(authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "User registration")
    public ResponseEntity<User> register(@Validated(View.Web.class) @RequestBody UserTo userTo) {
        User created = super.create(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the authenticated user")
    public void update(@Validated(View.Web.class) @RequestBody UserTo userTo) {
        super.update(userTo, authUserId());
    }
}
