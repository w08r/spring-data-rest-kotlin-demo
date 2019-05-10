package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer




@Repository
interface CustomerRepository : JpaRepository<W1, Long> {

    fun findByTxt(w1_txt: String): List<W1>

    override fun findAll(): List<W1>

}

@Entity
@Table(name = "w1")
data class W1 (@Id val w1_id: Long? = null, @Column(name = "w1_txt") val txt: String? = null){}


@Configuration
internal class CustomRestMvcConfiguration {

    @Bean
    fun repositoryRestConfigurer(): RepositoryRestConfigurer {

        return object : RepositoryRestConfigurerAdapter() {

            override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
                config!!.setBasePath("/api")
            }
        }
    }
}

