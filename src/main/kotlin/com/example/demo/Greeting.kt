package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController {

    @Autowired
    val repo : CustomerRepository? = null;

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) : Greeting {
        val ids = repo?.findByTxt(name);
        if (ids != null && ids.isNotEmpty()) {
            val id = ids.get(0).w1_id;
            return Greeting(counter.incrementAndGet(), "Hello, $name: $id")
        }
        return  Greeting(counter.incrementAndGet(), "Hello, $name")

    }

}