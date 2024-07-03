package com.freshPick.FreshPick

import com.freshPick.FreshPick.model.Role
import com.freshPick.FreshPick.repository.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FreshPickApplication{


@Bean
fun runner(roleRepository: RoleRepository): CommandLineRunner {
	return CommandLineRunner {
		if (roleRepository.findByName("USER").isEmpty) {
			roleRepository.save(
				Role(
					id = 0,
					name = "USER"
				)
			)
		}
	}
}
}

fun main(args: Array<String>) {
	runApplication<FreshPickApplication>(*args)

}
