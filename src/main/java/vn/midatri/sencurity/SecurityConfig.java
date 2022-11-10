//package vn.midatri.sencurity;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}123456").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("{noop}123456").roles("ADMIN","DBA");
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().antMatchers("/").permitAll()
////                .and()
////                .authorizeRequests().antMatchers("/orders").hasRole("USER")
////                .and()
////                .authorizeRequests().antMatchers("/products").hasRole("ADMIN")
////                .and()
////                .formLogin()
////                .and()
////                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
////    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/orders").access("hasRole('ROLE_USER')")
//                .antMatchers("/products").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .loginPage("/login")
//                .defaultSuccessUrl("/login")
//                .successHandler(new CustomSuccessHandler())
//                .usernameParameter("ssoId")
//                .passwordParameter("password")
//                .and()
//                .csrf()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/accessDenied");
//
//    }
//
//}
