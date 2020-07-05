/*
 * package br.com.adm.clinica.security;
 * 
 * import javax.inject.Inject;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Inject public void configureGlobal(AuthenticationManagerBuilder auth) throws
 * Exception {
 * auth.inMemoryAuthentication().withUser("op").password("password").roles(
 * "OPERATOR").and().withUser("admin") .password("password").roles("ADMIN"); }
 * 
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable();
 * http.authorizeRequests().antMatchers("resources/**").permitAll()
 * .antMatchers("plugins/**").permitAll() .antMatchers("https/**").permitAll()
 * .anyRequest().authenticated().and().logout()
 * .logoutSuccessUrl("/login.xhtml?logout").permitAll().and().formLogin().
 * loginPage("/login.xhtml") .failureUrl("/login.xhtml?erro").permitAll(); } }
 */