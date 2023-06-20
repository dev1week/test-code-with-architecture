package com.example.demo.repository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.example.demo.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@DataJpaTest(showSql = true)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_정상동작체크() throws Exception{
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("the_1_week@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("rondo");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa");

        //when
        UserEntity result = userRepository.save(userEntity);

        //then
        assertThat(result.getId()).isNotNull();
    }
    @Test
    public void findByIdAndStatus_테스트() throws Exception{
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("the_1_week@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("rondo");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa");

        userRepository.save(userEntity);

        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByIdAndStatus_db에_없는_값을_조회_했을때() throws Exception{
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("the_1_week@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("rondo");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa");

        userRepository.save(userEntity);

        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.PENDING);
        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void findByUserEmail_테스트() throws Exception{
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("the_1_week@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("rondo");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa");

        userRepository.save(userEntity);

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("the_1_week@naver.com", UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByUserEmail_db에_없는_값을_조회_했을때() throws Exception{
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("the_1_week@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("rondo");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa");

        userRepository.save(userEntity);

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("the_1_week@naver.com", UserStatus.PENDING);
        //then
        assertThat(result.isEmpty()).isTrue();
    }

}