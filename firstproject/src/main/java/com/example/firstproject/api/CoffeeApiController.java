package com.example.firstproject.api;

// 데이터의 생성, 조회, 수정, 삭제 REST API 구현

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {
//    @Autowired
//    private CoffeeRepository coffeeRepository;
//
//    //GET
//    /**
//     * 전체 커피 데이터 조회 API
//     * @return
//     */
//    @GetMapping("/api/coffees")
//    public List<Coffee> index() {
//        return coffeeRepository.findAll();
//    }
//
//    /**
//     * 단일 커피 데이터 조회 API
//     * @param id
//     * @return
//     */
//    @GetMapping("/api/coffees/{id}")
//    public Coffee show (@PathVariable Long id) {
//        return coffeeRepository.findById(id).orElse(null);
//    }
//    //POST
//
//    /**
//     * 데이터 생성 API
//     * @param dto
//     * @return
//     */
//    @PostMapping("/api/coffees")
//    public Coffee create (@RequestBody CoffeeDto dto) {
//        Coffee coffee = dto.toEntity();
//        return coffeeRepository.save(coffee);
//    }
//
//    //PATCH
//    @PatchMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> update (@PathVariable Long id,
//                                          @RequestBody CoffeeDto dto) {
//        //1. DTO => 엔티티 변환하기
//        Coffee coffee = dto.toEntity();
//        log.info("id: {}, coffee: {}", id, coffee.getId());
//        //2. 타깃 조회하기
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        //3. 잘못된 요청 처리하기
//        if (target == null || id != coffee.getId()) {
//            //400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id:{}, coffee:{}", id, coffee.getId());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //4. 업데이트 및 정상 응답(200) 처리하기
//        target.patch(coffee); //일부 데이터만 삭제할 경우
//        Coffee updated = coffeeRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//    //DELETE
//    @DeleteMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
//        //1. 대상 찾기
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        //2. 잘못된 요청 처리하기
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //3. 대상 삭제 처리하기
//        coffeeRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
    //Service 계층을 추가한 구현
    @Autowired
    private CoffeeService coffeeService;

    //GET
    /**
     * 전체 커피 조회 API
     * @return
     */
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index () {
        return coffeeService.index();
    }

    /**
     * 단일 커피 조회하기
     * @param id
     * @return
     */
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    //POST
    /**
     * 데이터 생성 API
     * @param dto
     * @return
     */
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create (CoffeeDto dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    /**
     * 커피 데이터 수정하기 API
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update (@PathVariable Long id,
                                          @RequestBody CoffeeDto dto) {
        Coffee updated = coffeeService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    /**
     * 커피 데이터 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete (@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
