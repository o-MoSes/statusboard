package rocks.monsees.statusboard.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// maybe implemment UserDetails in Employee itself
public class EmployeePrincipal implements UserDetails{

	
	
	private final Employee employee;

	public EmployeePrincipal(Employee employee) {
		this.employee=employee;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		System.out.println(employee.getPassword());
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getPosition();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
