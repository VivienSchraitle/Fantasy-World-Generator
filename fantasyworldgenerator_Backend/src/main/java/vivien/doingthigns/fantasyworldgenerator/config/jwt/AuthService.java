package vivien.doingthigns.fantasyworldgenerator.config.jwt;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(LoginDto loginDto);
}
