package cinema.util;

import cinema.model.Movie;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.MovieService;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final MovieService movieService;

    public DataInitializer(RoleService roleService,
                           UserService userService,
                           AuthenticationService authenticationService, MovieService movieService) {
        this.roleService = roleService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.movieService = movieService;
    }

    @PostConstruct
    public void inject() {
        Role admin = new Role();
        admin.setName("ADMIN");
        roleService.add(admin);
        Role user = new Role();
        user.setName("USER");
        User alex = new User();
        alex.setEmail("alex@mail.ru");
        alex.setPassword("12345");
        alex.setRoles(Set.of(roleService.get("ADMIN")));
        userService.add(alex);
        authenticationService.register("alina@mail.ua", "qwerty");

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setDescription("good film");
        movieService.add(movie);
    }
}
