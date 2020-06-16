package com.example.demo.security;

/*
 * @Configuration
 * 
 * @EnableWebSecurity public class LoginSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder
 * authenticationMgr) throws Exception {
 * authenticationMgr.inMemoryAuthentication()
 * .withUser("user").password("user@123").authorities("ROLE_USER") .and()
 * .withUser("raj").password("raj@123").authorities("ROLE_USER","ROLE_ADMIN"); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * 
 * http.authorizeRequests() .antMatchers("/homePage").
 * access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
 * .antMatchers("/userPage").access("hasRole('ROLE_USER')")
 * .antMatchers("/adminPage").access("hasRole('ROLE_ADMIN')") .and()
 * .formLogin().loginPage("/loginPage") .defaultSuccessUrl("/homePage")
 * .failureUrl("/loginPage?error")
 * .usernameParameter("username").passwordParameter("password") .and()
 * .logout().logoutSuccessUrl("/loginPage?logout");
 * 
 * } }
 */
