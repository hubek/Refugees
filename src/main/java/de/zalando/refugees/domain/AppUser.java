package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AppUser.
 */
@Entity
@Table( name = "app_user" )
@Cache( usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
public class AppUser implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@ManyToOne
	@JoinColumn( name = "branch_id" )
	private Branch branch;

	@OneToOne
	private User user;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public void setBranch( Branch branch )
	{
		this.branch = branch;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
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
		AppUser appUser = (AppUser) o;
		if ( appUser.id == null || id == null )
		{
			return false;
		}
		return Objects.equals( id, appUser.id );
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "AppUser{" + "id=" + id + '}';
	}
}
