package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Status.
 */
@Entity
@Table( name = "status" )
@Cache( usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
public class Status implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Column( name = "value" )
	private String value;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
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
		Status status = (Status) o;
		if ( status.id == null || id == null )
		{
			return false;
		}
		return Objects.equals( id, status.id );
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "Status{" + "id=" + id + ", value='" + value + "'" + '}';
	}
}
