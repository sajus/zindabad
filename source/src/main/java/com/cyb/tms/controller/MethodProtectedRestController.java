package com.cyb.tms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/protected")
public class MethodProtectedRestController {

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProtectedGreeting() {
        return ResponseEntity.ok("Greetings from admin protected method!");
    }

   /* @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getAll() {
        List<Entity> entityList = entityManager.findAll();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Entity n : entityList) {
            JSONObject Entity = new JSONObject();
            entity.put("id", n.getId());
            entity.put("address", n.getAddress());
            entities.add(entity);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }*/
}
