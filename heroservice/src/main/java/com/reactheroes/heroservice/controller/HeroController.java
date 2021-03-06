package com.reactheroes.heroservice.controller;

import com.reactheroes.heroservice.entity.Hero;
import com.reactheroes.heroservice.model.IdGroup;
import com.reactheroes.heroservice.model.HeroPack;
import com.reactheroes.heroservice.repository.HeroRepository;
import com.reactheroes.heroservice.service.PackGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeroController {

    public HeroRepository heroRepository;
    private final PackGenerator packGenerator;

    @Autowired
    public HeroController(HeroRepository heroRepository, PackGenerator packGenerator) {
        this.heroRepository = heroRepository;
        this.packGenerator = packGenerator;
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable Long id){
        return ResponseEntity.ok(heroRepository.findById(id).get());
    }

    @GetMapping("/heroes")
    public ResponseEntity<Object> getHeroesPerPage(@RequestParam Integer page){
        return ResponseEntity.ok(heroRepository.findAll(PageRequest.of(page, 18)));
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getHeroByName(@RequestParam String value){
        return ResponseEntity.ok(heroRepository.getByName(value, PageRequest.of(0,10)));
    }

    @GetMapping("/getrandom")
    public ResponseEntity<?> getRandomHeroes(@RequestParam int pack){
        if (pack == 4) return ResponseEntity.ok(packGenerator.getPack4());
        if (pack == 6) return ResponseEntity.ok(packGenerator.getPack6());
        if (pack == 8) return ResponseEntity.ok(packGenerator.getPack8());
        return new ResponseEntity<>("Invalid pack size!", HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/getheroes")
    public ResponseEntity<?> getHeroesByIdList(@RequestBody IdGroup idGroup) {
        return ResponseEntity.ok(new HeroPack(packGenerator.getHeroesByIds(idGroup)));
    }
}


