package org.ikropachev.bdater.web.item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ikropachev.bdater.View;
import org.ikropachev.bdater.model.Item;
import org.ikropachev.bdater.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for items")
public class ItemController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemService service;

    static final String REST_URL = "/rest/items";
    private static final String ITEM_ID_STR = "100003";
    private static final String ITEM_FOR_DELETE_ID_STR = "100002";
    private static final String ITEM_FOR_UPDATE_ID_STR = "100003";

    @GetMapping
    @ApiOperation(value = "View a list of all items")
    public List<Item> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View the item by id")
    public Item get(@PathVariable @ApiParam(example = ITEM_ID_STR, required = true) int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create the item")
    public ResponseEntity<Item> createWithLocation(@Validated(View.Web.class) @RequestBody Item item) {
        Item created = service.create(item);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete the item by id")
    public void delete(@PathVariable @ApiParam(example = ITEM_FOR_DELETE_ID_STR, required = true) int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the item by id")
    public void update(@Validated(View.Web.class) @RequestBody Item item,
                       @PathVariable @ApiParam(example = ITEM_FOR_UPDATE_ID_STR, required = true) int id) {
        service.update(item);
    }
}
