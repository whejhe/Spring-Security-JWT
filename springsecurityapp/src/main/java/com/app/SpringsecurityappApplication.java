package com.app;

import java.util.Set;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.entity.PermissionEntity;
import com.app.entity.RoleEntity;
import com.app.entity.RoleEnum;
import com.app.entity.UserEntity;
import com.app.repository.UserRepository;

@SpringBootApplication
public class SpringsecurityappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityappApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// CREATE PERMISSION
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			
			PermissionEntity otherPermission = PermissionEntity.builder()
					.name("OTHER")
					.build();

			// CREATE ROLE
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(readPermission, createPermission))
					.build();
			
			RoleEntity roleGuest = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITADO)
					.permissionList(Set.of(readPermission))
					.build();
			
			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, otherPermission))
					.build();
			

			// CREATE USER
			UserEntity userCarlos = UserEntity.builder()
					.username("carlos")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleAdmin, roleDeveloper))
					.build();
			
			UserEntity userSara = UserEntity.builder()
					.username("Sara")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userJuan = UserEntity.builder()
					.username("Juan")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleGuest))
					.build();

			UserEntity userAna = UserEntity.builder()
					.username("Ana")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();


			userRepository.saveAll(List.of(userCarlos, userSara, userJuan, userAna));

		};
	}

}
