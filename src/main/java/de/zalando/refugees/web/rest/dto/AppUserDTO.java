package de.zalando.refugees.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AppUser entity.
 */
public class AppUserDTO implements Serializable
{

	private Long id;

	private Long branchId;
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String langKey;
	public String getLangKey()
	{
		return langKey;
	}

	public void setLangKey( String langKey )
	{
		this.langKey = langKey;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin( String login )
	{
		this.login = login;
	}

	private String login;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Long getBranchId()
	{
		return branchId;
	}

	public void setBranchId( Long branchId )
	{
		this.branchId = branchId;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId( Long userId )
	{
		this.userId = userId;
	}

	@Override
	public boolean equals( Object o )
	{
		if ( this == o )
		{
			return true;
		}
		if ( o == null || getClass() != o.getClass() )
		{
			return false;
		}

		AppUserDTO appUserDTO = (AppUserDTO) o;

		if ( !Objects.equals( id, appUserDTO.id ) )
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "AppUserDTO{" + "id=" + id + '}';
	}
}
