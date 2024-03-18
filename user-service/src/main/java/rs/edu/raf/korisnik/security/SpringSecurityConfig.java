package rs.edu.raf.korisnik.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig implements WebSecurityCustomizer {

    private final JwtFilter jwtFilter;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomPermissionEvaluator permissionEvaluator() {
        return new CustomPermissionEvaluator();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();//defaultni AuthenticationManager
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .cors()
//                .and()
                .authorizeRequests(authorize -> authorize

                        // /radnik/** obuhvata i /radnik

                        //ako je isti domen /korisnik uvek prvo definisemo ** pa konkretne putanje
                        //.requestMatchers("/korisnik/**")
                        //.requestMatchers("/korisnik/login")

                        //vazi za sve putanje u /korisnik/ ali samo sa GET metodom
                        //.requestMatchers(HttpMethod.GET,"/korisnik/**")

                        //vazi za sve putanje u /korisnik/ svih metoda
                        //.requestMatchers("/korisnik/**")

                        .requestMatchers("/swagger-ui/**","/api-docs/**","/swagger-resources/**").permitAll()//dozvoljava se sve
                        .requestMatchers("/korisnik/login","/korisnik/generate-login","/korisnik/generate-reset","/korisnik/verifikacija","/korisnik/reset-password").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/korisnik","/korisnik/change-password").authenticated()//mora biti autentifikovan odnosno imati token u request
                        .requestMatchers(HttpMethod.GET,"/korisnik","/korisnik/**","/racuni/izlistajSveFirme").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.listanje_korisnika + "')")//provera autorizacije a to znaci privilegija prethodno autentifikovanog odnosno obuhvata i autentifikaciju
                        .requestMatchers(HttpMethod.POST,"/korisnik/add","/racuni/kreirajFirmu").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.dodavanje_korisnika + "')")
                        .requestMatchers(HttpMethod.GET,"/radnik","/radnik/**").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.listanje_radnika + "')")
                        .requestMatchers(HttpMethod.PUT,"/radnik").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.editovanje_radnika + "')")
                        .requestMatchers(HttpMethod.POST,"/radnik").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.dodavanje_radnika + "')")
                        .requestMatchers("/racuni/dodajDevizni","/racuni/dodajPravni","/racuni/dodajTekuci").access("@permissionEvaluator.hasPermission(authentication, null,'" + Permisije.kreiranje_racuna + "')")
                        .anyRequest().authenticated()//defaultno za ostale rute svih putanja zahteva se autentifikacija
                )
                .csrf().disable()
                                    //jwtFilter je pre UsernamePasswordAuthenticationFilter.class
                                    //jwtFilter proverava token iz requesta i setuje globalno da je korisnik ulogovan u servis odnosno UsernamePasswordAuthenticationToken za pristup njegovim podacima
                                    //jwtFilter se zove za rute .authenticated()
                                    //ako jwtFilter uspe da autentifikuje onda se UsernamePasswordAuthenticationFilter ne izvrsava
                                    //UsernamePasswordAuthenticationFilter.class je defaultni filter koji proverava username i password, a manuelno ga pokrecemo pri /korisnik/login jer smo za ovu rutu stavili .permitAll()
                .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

    @Override
    public void customize(WebSecurity web) {
        web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

}
