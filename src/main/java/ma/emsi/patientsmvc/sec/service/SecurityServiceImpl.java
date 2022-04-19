package ma.emsi.patientsmvc.sec.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.sec.entities.AppRole;
import ma.emsi.patientsmvc.sec.entities.AppUser;
import ma.emsi.patientsmvc.sec.repositories.AppRoleRepository;
import ma.emsi.patientsmvc.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserid(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole =
                appRoleRepository.findByRolename(roleName);
        if (appRole != null) throw new RuntimeException("Role" + roleName + "Already exist");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        appRoleRepository.save(appRole);
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return null;
    }
}
