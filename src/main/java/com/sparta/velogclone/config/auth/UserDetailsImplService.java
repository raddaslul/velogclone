package com.sparta.velogclone.config.auth;

import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.handler.ex.EmailNotFoundException;
import com.sparta.velogclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsImplService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new EmailNotFoundException("가입되지 않은 이메일입니다.")
        );

        return new UserDetailsImpl(findUser);
    }
}